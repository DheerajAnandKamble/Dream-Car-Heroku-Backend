package com.cg.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.car.entity.Car;

@Repository
public interface ICarRepository extends JpaRepository<Car, Integer> {

	public List<Car> findAllByCarName(String carName);
	public List<Car> findAllByBrand(String brand);
	@Query("SELECT c FROM Car c where showroom_id =:id")
	public List<Car> findAllByShowroom(@Param("id") int showroomId);
	public List<Car> findAllByPriceBetween(double minPrice, double maxPrice);
}
