package com.cg.car.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.cg.car.entity.Showroom;
import com.cg.car.exception.ShowroomException;
import com.cg.car.service.IShowroomService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/DreamCar/Showroom")
public class ShowroomController {

	@Autowired
	IShowroomService showroomService;
	
	Logger logger = LoggerFactory.getLogger(ShowroomController.class);

	// Method to add showroom
	@PostMapping("/addShowroom")
	public ResponseEntity<Showroom> addShowroom(@Valid @RequestBody Showroom showroom) {

		Showroom showroomData = showroomService.addShowroom(showroom);
		if (showroomData == null) {
			logger.error("Controller: Failed to add showroom");
			throw new ShowroomException("Showroom not added");
		}
		logger.info("*** Controller : Showroom added successfully. ***");
		return new ResponseEntity<Showroom>(showroomData, HttpStatus.CREATED);
	}

	// Method to update showroom
	@PutMapping("/updateShowroom")
	public ResponseEntity<Showroom> updateShowroom(@Valid @RequestBody Showroom showroom) {

		Showroom showroomData = showroomService.updateShowroom(showroom);
		logger.info("*** Controller : Showroom updated successfully. ***");
		return new ResponseEntity<Showroom>(showroomData, HttpStatus.ACCEPTED);
	}

	// Method to get showroom by id
	@GetMapping("/getShowroom/{id}")
	public ResponseEntity<Showroom> getShowroomById(@PathVariable int id) {

		Showroom showroomData = showroomService.getShowroomById(id);

		if (showroomData == null) {
			logger.error("Controller: Showroom Not Found For given id : " + id);
			throw new ShowroomException("No Showroom present with the given id " + id);
		}

		logger.info("*** Controller : Displaying Showroom ***");
		return new ResponseEntity<Showroom>(showroomData, HttpStatus.OK);
	}

	// Method to delete showroom by id
	@DeleteMapping("/deleteShowroom/{id}")
	public ResponseEntity<Showroom> deleteShowroom(@PathVariable int id) {

		Showroom showroomData = showroomService.removeShowroomById(id);

		if (showroomData == null) {
			logger.error("Controller: Showroom Not Found For given id : " + id);
			throw new ShowroomException("Delete failed... Invalid showroom id " + id);
		}

		logger.info("*** Controller : Showroom removed. ***");
		return new ResponseEntity<Showroom>(showroomData, HttpStatus.OK);
	}

	// Method to get all showroom
	@GetMapping("/getAllShowroom")
	public ResponseEntity<List<Showroom>> getAllShowroom() {
		List<Showroom> showroomList = showroomService.getShowroomList();

		if (showroomList.size() == 0) {
			logger.error("Controller: Showroom not found.");
			throw new ShowroomException("No Showrooms in the database ");
		}

		logger.info("*** Controller : Displaying showroom list. ***");
		return new ResponseEntity<List<Showroom>>(showroomList, HttpStatus.OK);
	}

	// Method to get all showroom by location
	@GetMapping("/getAllShowroomByLocation/{location}")
	public ResponseEntity<List<Showroom>> getAllShowroomByLocation(@PathVariable("location") String location) {
		List<Showroom> showroomList = showroomService.getAllShowroomByLocation(location);

		if (showroomList.size() == 0) {
			logger.error("Controller: No showroom found at location " + location);
		    throw new ShowroomException("No showroom found at location " + location);
		}

		logger.info("*** Controller : Displaying showroom list. ***");
		return new ResponseEntity<List<Showroom>>(showroomList, HttpStatus.OK);
	}

	// Method to get all showroom by name
	@GetMapping("/getAllShowroomByName/{name}")
	public ResponseEntity<List<Showroom>> getAllShowroomByName(@PathVariable("name") String showroomName) {
		List<Showroom> showroomList = showroomService.getAllShowroomByName(showroomName);

		if (showroomList.size() == 0) {
			logger.error("Controller: No showroom found with name " + showroomName);
			throw new ShowroomException("No showroom found with name " + showroomName);
		}

		logger.info("*** Controller : Displaying showroom list. ***");
		return new ResponseEntity<List<Showroom>>(showroomList, HttpStatus.OK);
	}

	// Method to validate showroom
	@GetMapping("/validateShowroom/{email}/{password}")
	public ResponseEntity<Showroom> validateShowroom(@PathVariable("email") String email,@PathVariable("password") String password) {
		Showroom showroomData = showroomService.validateShowroom(email,password);
		if (showroomData == null) {
			logger.error("Controller: Login failed...");
			throw new ShowroomException("Login failed...Invalid Credentials");
		}
		logger.error("*** Controller: Showroom Login ***");
		return new ResponseEntity<Showroom>(showroomData, HttpStatus.OK);
	}
}
