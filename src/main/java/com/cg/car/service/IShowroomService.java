package com.cg.car.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.car.entity.Showroom;

public interface IShowroomService {

	Showroom addShowroom(@Valid Showroom showroom);

	Showroom updateShowroom(@Valid Showroom showroom);

	Showroom getShowroomById(int id);

	Showroom removeShowroomById(int id);

	List<Showroom> getShowroomList();

	List<Showroom> getAllShowroomByLocation(String location);

	List<Showroom> getAllShowroomByName(String showroomName);

	Showroom validateShowroom(String email, String password);

}
