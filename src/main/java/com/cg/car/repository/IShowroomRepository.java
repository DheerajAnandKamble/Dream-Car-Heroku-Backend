package com.cg.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.car.entity.Showroom;

@Repository
public interface IShowroomRepository extends JpaRepository<Showroom, Integer> {

	List<Showroom> findShowroomByLocation(String location);
	
	List<Showroom> findShowroomByShowroomName(String showroomName);

	Showroom findShowroomByEmail(String email);

}
