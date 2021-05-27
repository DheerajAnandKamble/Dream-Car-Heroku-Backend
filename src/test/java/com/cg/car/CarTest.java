package com.cg.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.car.entity.Car;
import com.cg.car.entity.Showroom;
import com.cg.car.repository.ICarRepository;
import com.cg.car.service.ICarService;

@SpringBootTest
public class CarTest {
	@Autowired
	private ICarService carService;

	@MockBean
	private ICarRepository carRepository;

	// test case for adding car

	@Test
	public void testAddCar() {

		Car car = getCar();
		Mockito.when(carRepository.save(car)).thenReturn(car);
		Car result = carService.addCar(car);
		assertEquals(car, result); // to check whether the objects are equal

	}

	@Test
	public void testVerifyUpdateCar() {
		Car car = getCar();
		Mockito.when(carRepository.existsById(car.getCarId())).thenReturn(true);
		carService.updateCar(car);
		verify(carRepository, times(1)).existsById(car.getCarId());

	}
	// test case for getting car by id

	@Test
	public void testViewCarById() {
		Car car = getCar();
		Mockito.when(carRepository.existsById(car.getCarId())).thenReturn(true);
		carService.viewCarById(car.getCarId());
		verify(carRepository, times(1)).existsById(car.getCarId());
	}

	// test case for removing car by id

	@Test
	public void testRemoveCar() {
		Car car = getCar();
		Mockito.when(carRepository.existsById(car.getCarId())).thenReturn(true);
		carService.removeCar(car.getCarId());
		verify(carRepository, times(1)).existsById(car.getCarId());
	}

	// test case for fetching all cars
	@Test
	public void testViewAllcars() {
		when(carRepository.findAll()).thenReturn(Stream.of(getCar()).collect(Collectors.toList()));
		assertEquals(1, carService.viewAllCars().size());
	}

	// test case for fetching all cars by car name
	@Test
	public void testViewAllCarsByCarName() {
		Car car = getCar();
		when(carRepository.findAllByCarName(car.getCarName())).thenReturn(Stream.of(car).collect(Collectors.toList()));
		assertEquals(1, carService.viewAllCarsByCarName(car.getCarName()).size());
	}

	// test case for fetching all cars by brand
	@Test
	public void testViewAllCarsByBrand() {
		Car car = getCar();
		when(carRepository.findAllByBrand(car.getBrand())).thenReturn(Stream.of(car).collect(Collectors.toList()));
		assertEquals(1, carService.viewAllCarsByBrand(car.getBrand()).size());
	}

	// test case for fetching all cars by showroom
	@Test
	public void testViewAllCarsByShowroomId() {
		Car car = getCar();
		when(carRepository.findAllByShowroom(car.getShowroom().getShowroomId()))
				.thenReturn(Stream.of(car).collect(Collectors.toList()));
		assertEquals(1, carService.viewAllCarsByShowroom(car.getShowroom().getShowroomId()).size());
	}

	// test case for fetching all cars by price
	@Test
	public void testViewAllCarsBetweenPrices() {
		double min = 200000;
		double max = 500000;
		when(carRepository.findAllByPriceBetween(min, max))
				.thenReturn(Stream.of(getCar()).collect(Collectors.toList()));
		assertEquals(1, carService.viewAllCarsBetweenPrices(min, max).size());
	}

	private Car getCar() {
		Car car = new Car();
		car.setCarId(1);
		car.setCarName("alto");
		car.setModelNo("45A");
		car.setColour("Blue");
		car.setFuelType("petrol");
		car.setPrice(400000);
		car.setBrand("suzuki");
		car.setShowroom(getShowroom());
		return car;
	}

	private Showroom getShowroom() {
		Showroom showroom = new Showroom();
		showroom.setShowroomId(1);
		showroom.setContactNo("9463745363");
		showroom.setEmail("suma@gmail.com");
		showroom.setLocation("Hyderabad");
		showroom.setManagerName("Suma");
		showroom.setPassword("suma234");
		showroom.setShowroomName("Bajaj");
		return showroom;
	}

}