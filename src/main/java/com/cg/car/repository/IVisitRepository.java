package com.cg.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.car.entity.Visit;

public interface IVisitRepository extends JpaRepository<Visit,Integer>{
	
	@Query(value="select v.visit_id from visit v INNER JOIN car c on v.car_id=c.car_id INNER JOIN showroom s on c.showroom_id=s.showroom_id where s.showroom_id=:id",nativeQuery=true)
	public List<Integer> getVisitByShowroomId(@Param("id") int showroomId);
	
	@Query(value="select v.visit_id from visit v where v.customer_id=:id",nativeQuery=true)
	public List<Integer> getVisitByCustomerId(@Param("id") int customerId);
	
	
	
}
