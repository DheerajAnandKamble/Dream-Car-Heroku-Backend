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

import com.cg.car.entity.Visit;
import com.cg.car.exception.CustomerException;
import com.cg.car.exception.VisitException;
import com.cg.car.service.IVisitService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/DreamCarApp/Visit")
public class VisitController {
	
	@Autowired
	IVisitService visitService;
	
	Logger logger = LoggerFactory.getLogger(VisitController.class);
	
	@PostMapping("/addVisit")
	public ResponseEntity<Visit> addVisit( @RequestBody Visit visit) {

		Visit visitData = visitService.addVisit(visit);
		if (visitData == null) {
			logger.error("Controller: Failed to add Visit");
			throw new VisitException("Visit not added");
		}
		logger.info("*** Controller : Visit added successfully. ***");
		return new ResponseEntity<Visit>(visitData, HttpStatus.OK);

	}
	
	@PutMapping("/updateVisit")
	public ResponseEntity<Visit> updateVisit(@Valid @RequestBody Visit visit) {
		Visit visitData = null;
		
			visitData = visitService.updateVisit( visit);

		

		logger.info("*** Controller : Visit updated successfully. ***");
		return new ResponseEntity<Visit>(visitData, HttpStatus.OK);
	}

	@DeleteMapping("/deleteVisit/{id}")
	public ResponseEntity<Visit> deleteVisit(@PathVariable int id) {

		Visit visitData = visitService.removeVisit(id);

		if (visitData == null) {
			logger.error("Controller: Visit Not Found For given id : " + id);
			throw new CustomerException("No Visit present with the given id " + id);
		}

		logger.info("*** Controller : Visit removed. ***");
		return new ResponseEntity<Visit>(visitData, HttpStatus.OK);
	}

	@GetMapping("/getVisit/{id}")
	public ResponseEntity<Visit> getVisitById(@PathVariable int id) {

		Visit visitData = visitService.getVisit(id);

		if (visitData == null) {
			logger.error("Controller: Visit Not Found For given id : " + id);
			throw new CustomerException("No Visit present with the given id " + id);
		}

		logger.info("*** Controller : Displaying Customer ***");
		return new ResponseEntity<Visit>(visitData, HttpStatus.OK);
	}
	
	@GetMapping("/getAllVisits")
	public ResponseEntity<List<Visit>> getAllVisits() {
		List<Visit> visitsList = visitService.getVisitList();

		if (visitsList.size() == 0) {
			logger.error("Controller: Visits not found.");
			throw new CustomerException("No Visits in the database ");
		}

		logger.info("*** Controller : Displaying customers list. ***");
		return new ResponseEntity<List<Visit>>(visitsList, HttpStatus.OK);
	}
	
	@GetMapping("/getAllVisitsByShowroomId/{id}")
	public ResponseEntity<List<Visit>> getAllVisitsByShowroomId(@PathVariable int id) {
		List<Visit> visitsList = visitService.getVisitByShowroomId(id);

		if (visitsList.size() == 0) {
			logger.error("Controller: Visits not found.");
			throw new CustomerException("No Visits in the database ");
		}

		logger.info("*** Controller : Displaying customers list. ***");
		return new ResponseEntity<List<Visit>>(visitsList, HttpStatus.OK);
	}
	
	@GetMapping("/getAllVisitsByCustomerId/{id}")
	public ResponseEntity<List<Visit>> getAllVisitsByCustomerId(@PathVariable int id) {
		List<Visit> visitsList = visitService.getVisitByCustomerId(id);

		if (visitsList.size() == 0) {
			logger.error("Controller: Visits not found.");
			throw new CustomerException("No Visits in the database ");
		}

		logger.info("*** Controller : Displaying customers list. ***");
		return new ResponseEntity<List<Visit>>(visitsList, HttpStatus.OK);
	}
	
	
}
