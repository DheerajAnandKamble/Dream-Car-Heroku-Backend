package com.cg.car.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.car.entity.Car;
import com.cg.car.service.ICarService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/DreamCarApp/Car")
public class CarController {

	@Autowired
	ICarService carService;

	@PostMapping("/addCar")
	public ResponseEntity<String> addCar(@RequestBody Car car) {
		car = carService.addCar(car);
		return new ResponseEntity<String>("Car has been added successfully with id: " + car.getCarId() + ", " + car,
				HttpStatus.OK);
	}

	@PutMapping("/updateCar")
	public ResponseEntity<String> updateCar(@RequestBody Car car) {
		Car updatedCar = carService.updateCar(car);
		return new ResponseEntity<String>("Car has been updated successfully ," + updatedCar, HttpStatus.OK);
	}

	@GetMapping("/viewCar/{id}")
	public ResponseEntity<Car> viewCarById(@PathVariable int id) {
		Car carById = carService.viewCarById(id);
		return new ResponseEntity<Car>(carById, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCar/{id}")
	public ResponseEntity<String> removeCar(@PathVariable int id) {
		Car deletedCar = carService.removeCar(id);
		return new ResponseEntity<String>("Planter deleted with the id: " + deletedCar.getCarId(), HttpStatus.OK);
	}

	@GetMapping("/viewAllCars")
	public ResponseEntity<List<Car>> viewAllCars() {
		List<Car> cars = carService.viewAllCars();
		return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
	}

	@GetMapping("/viewAllCarsByName/{carName}")
	public ResponseEntity<List<Car>> viewAllCarsByCarName(@PathVariable String carName) {
		List<Car> carsByCarName = carService.viewAllCarsByCarName(carName);
		return new ResponseEntity<List<Car>>(carsByCarName, HttpStatus.OK);

	}

	@GetMapping("/viewAllCarsByBrand/{brand}")
	public ResponseEntity<List<Car>> viewAllCarsByBrand(@PathVariable String brand) {
		List<Car> carsByBrand = carService.viewAllCarsByBrand(brand);
		return new ResponseEntity<List<Car>>(carsByBrand, HttpStatus.OK);
	}

	@GetMapping("/viewAllCarsByShowroomId/{showroomId}")	
	public ResponseEntity<List<Car>> viewAllCarsByShowroom(@PathVariable int showroomId) {
		List<Car> carsByShowroomId=carService.viewAllCarsByShowroom(showroomId);
		return new ResponseEntity<List<Car>>(carsByShowroomId, HttpStatus.OK);
	}

	@GetMapping("/viewAllCarsBetweenMinimumAndMaximumPrices/{minPrice}/{maxPrice}")
	public ResponseEntity<List<Car>> viewAllCarsBetweenPrices(@PathVariable double minPrice, @PathVariable double maxPrice) {
		List<Car> carsBetweenPrices=carService.viewAllCarsBetweenPrices(minPrice, maxPrice);
		return new ResponseEntity<List<Car>>(carsBetweenPrices, HttpStatus.OK);
	}

}
