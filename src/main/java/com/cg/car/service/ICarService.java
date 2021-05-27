package com.cg.car.service;

import java.util.List;

import com.cg.car.entity.Car;

public interface ICarService {

	public Car addCar(Car car);

	public Car updateCar(Car car);

	public Car viewCarById(int carId);

	public Car removeCar(int carId);

	public List<Car> viewAllCars();

	public List<Car> viewAllCarsByCarName(String carName);

	public List<Car> viewAllCarsByBrand(String brand);

	public List<Car> viewAllCarsByShowroom(int showroomId);

	public List<Car> viewAllCarsBetweenPrices(double minPrice, double maxPrice);
}
