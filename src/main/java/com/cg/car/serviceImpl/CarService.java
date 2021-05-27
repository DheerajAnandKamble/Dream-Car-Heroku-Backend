package com.cg.car.serviceImpl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.car.entity.Car;
import com.cg.car.entity.Customer;
import com.cg.car.exception.CarException;
import com.cg.car.repository.ICarRepository;
import com.cg.car.service.ICarService;

@Service
@Transactional
public class CarService implements ICarService {

	@Autowired
	ICarRepository carRepo;

	@Override
	public Car addCar(Car car) {
		return carRepo.save(car);
	}

	@Override
	public Car updateCar(Car car) {
		int id = car.getCarId();
		if (!carRepo.existsById(id)) {
			throw new CarException("Car you are trying to update is unavailable");
		}
		return carRepo.save(car);
	}

	@Override
	public Car viewCarById(int carId) {

		Car car = carRepo.findById(carId).orElse(null);
		if (!carRepo.existsById(carId))
			throw new CarException("Car you are trying to view is unavailable");
		return car;
	}

	@Override
	public Car removeCar(int carId) {
		Car car = carRepo.findById(carId).orElse(null);
		if(!carRepo.existsById(carId))
			throw new CarException("Car you are trying to delete is unavailable");
		carRepo.deleteById(carId);
		return car;
	}

	@Override
	public List<Car> viewAllCars() {
		List<Car> cars=carRepo.findAll();
		if(cars.size()==0)
			throw new CarException("No cars are available in the list");
		return cars;
	}

	@Override
	public List<Car> viewAllCarsByCarName(String carName) {
		List<Car> cars=carRepo.findAllByCarName(carName);
		if(cars.size()==0)
			throw new CarException("No cars are available with the name '"+carName+"'");
		return cars;
	}

	@Override
	public List<Car> viewAllCarsByBrand(String brand) {
		List<Car> cars=carRepo.findAllByBrand(brand);
		if(cars.size()==0)
			throw new CarException("No cars are available with the brand '"+brand+"'");
		return cars;
	}

	@Override
	public List<Car> viewAllCarsByShowroom(int showroomId) {
		List<Car> cars=carRepo.findAllByShowroom(showroomId);
		if(cars.size()==0)
			throw new CarException("No cars are available with the showroom Id '"+showroomId+"'");
		return cars;
	}

	@Override
	public List<Car> viewAllCarsBetweenPrices(double minPrice, double maxPrice) {
			List<Car> cars = carRepo.findAllByPriceBetween(minPrice,maxPrice );
			if (cars.size() == 0) {
				throw new CarException("There are no cars in the list between given prices");
			}
			return cars;
	}

}