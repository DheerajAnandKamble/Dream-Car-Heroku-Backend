package com.cg.car.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.car.entity.Showroom;
import com.cg.car.repository.IShowroomRepository;
import com.cg.car.service.IShowroomService;

@Service
@Transactional
public class ShowroomService implements IShowroomService {

	@Autowired
	IShowroomRepository repository;

	Logger logger = LoggerFactory.getLogger(ShowroomService.class);

	@Override
	public Showroom addShowroom(@Valid Showroom showroom) {

		Showroom showroom1 = repository.save(showroom);
		logger.info("*** Service :  Showroom added successfully. ***");
		return showroom1;
	}

	@Override
	public Showroom updateShowroom(@Valid Showroom showroom) {

		Showroom showroom1 = repository.save(showroom);
		logger.info("*** Service :  Showroom updated successfully. ***");
		return showroom1;

	}

	@Override
	public Showroom getShowroomById(int id) {

		Showroom showroom1 = repository.findById(id).orElse(null);
		logger.info("*** Service : Displaying showroom with id ***" + id);
		return showroom1;
	}

	@Override
	public Showroom removeShowroomById(int id) {

		logger.warn("*** Removing Customer ***");
		Showroom showroom = getShowroomById(id);
		if (showroom != null) {
			repository.delete(showroom);
		}
		return showroom;
	}

	@Override
	public List<Showroom> getShowroomList() {
		List<Showroom> list = repository.findAll();
		logger.info("*** Service : Displaying all showroom information ***");
		return list;
	}

	@Override
	public List<Showroom> getAllShowroomByLocation(String location) {
		List<Showroom> list = repository.findShowroomByLocation(location);
		logger.info("*** Service : Displaying all showroom in location ***");
		return list;
	}

	@Override
	public List<Showroom> getAllShowroomByName(String showroomName) {
		List<Showroom> list = repository.findShowroomByShowroomName(showroomName);
		logger.info("*** Service : Displaying all showroom with name ***");
		return list;
	}

	@Override
	public Showroom validateShowroom(String email, String password) {
		logger.info("*** Service : Validating showroom ***");
		Showroom showroom = repository.findShowroomByEmail(email);
		if (showroom == null) {
			return null;
		} else {
			if (showroom.getPassword().equals(password)) {
				return showroom;
			} else {
				return null;
			}
		}

	}

}
