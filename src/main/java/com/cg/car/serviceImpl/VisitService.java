package com.cg.car.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.car.entity.Visit;
import com.cg.car.repository.IVisitRepository;
import com.cg.car.service.IVisitService;

@Service
@Transactional
public class VisitService implements IVisitService {

	@Autowired
	IVisitRepository visitRepository;

	Logger logger = LoggerFactory.getLogger(VisitService.class);

	@Override
	public Visit addVisit(Visit visit) {
		// TODO Auto-generated method stub
		Visit visitObj = visitRepository.save(visit);
		logger.info("*** Service :  Visit added successfully. ***");
		return visitObj;
	}

	@Override
	public Visit updateVisit(Visit visit) {
		// TODO Auto-generated method stub
		Visit visitObj = visitRepository.save(visit);
		logger.info("*** Service :  Visit updated successfully. ***");
		return visitObj;
	}

	@Override
	public Visit removeVisit(int visitId) {
		// TODO Auto-generated method stub
		Visit visit = visitRepository.findById(visitId).orElse(null);

		logger.warn("*** Removing Visit ***");

		if (visitRepository.existsById(visitId)) {

			visitRepository.deleteById(visitId);
			logger.info("*** Service : Visit removed ***");
		} else {
			logger.error("*** Service : Visit is not present with the given id  ***");
		}

		return visit;
	}

	@Override
	public Visit getVisit(int visitId) {
		// TODO Auto-generated method stub
		if (visitRepository.existsById(visitId)) {
			logger.info("*** Service : Displaying visit with id ***" + visitId);

		} else {
			logger.error("*** Service : Visit is not present with the given id  ***");
		}

		return visitRepository.findById(visitId).orElse(null);

	}

	@Override
	public List<Visit> getVisitList() {
		// TODO Auto-generated method stub
		logger.info("*** Service : Displaying all visit information ***");
		return visitRepository.findAll();
	}

	@Override
	public List<Visit> getVisitByShowroomId(int showroomId) {
		List<Integer> list = visitRepository.getVisitByShowroomId(showroomId);
			List<Visit> list1 = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Visit visit = getVisit(list.get(i));
				list1.add(visit);
			}
			return list1;
	}

	@Override
	public List<Visit> getVisitByCustomerId(int customerId) {
		List<Integer> list = visitRepository.getVisitByCustomerId(customerId);
			List<Visit> list1 = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Visit visit = getVisit(list.get(i));
				list1.add(visit);

			}
			return list1;
		
	}

}
