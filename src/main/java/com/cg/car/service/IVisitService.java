package com.cg.car.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.car.entity.Visit;

public interface IVisitService {

	public Visit addVisit(Visit visit);

	public Visit updateVisit( Visit visit);
	
	public Visit removeVisit(int visitId);

	public Visit getVisit(int visitId);

	public List<Visit> getVisitList();
	
	public List<Visit> getVisitByShowroomId(int showroomId);
	
	public List<Visit> getVisitByCustomerId(int customerId);
}
