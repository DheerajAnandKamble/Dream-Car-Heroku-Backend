package com.cg.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.car.entity.Car;
import com.cg.car.entity.Customer;
import com.cg.car.entity.Showroom;
import com.cg.car.entity.Visit;
import com.cg.car.repository.IVisitRepository;
import com.cg.car.service.IVisitService;

@SpringBootTest
public class VisitTest {

	@Autowired
	IVisitService service;

	@MockBean
	IVisitRepository repository;

	@Test
	public void testAddVisit() { // TODO Auto-generated method stub

		Visit visit = getVisit();
		Mockito.when(repository.save(visit)).thenReturn(visit);
		Visit result = service.addVisit(visit);
		assertEquals(visit, result); // to check whether the objects are equal

	}
	
	@Test
	public void testUpdateVisit() { // TODO Auto-generated method stub

		Visit visit = getVisit();
		Mockito.when(repository.save(visit)).thenReturn(visit);
		Visit result = service.updateVisit(visit);
		assertEquals(visit.getVisitDate(), result.getVisitDate()); // to check whether the objects are equal

	} 
	@Test
	public void testGetVisit() {
		Visit visit = getVisit();
		service.getVisit(visit.getVisitId());
		verify(repository, times(1)).findById(visit.getVisitId());
	}
	
	@Test
	public void testGetVisitList() {
		when(repository.findAll()).thenReturn(Stream.of(getVisit()).collect(Collectors.toList()));
		assertEquals(1, service.getVisitList().size());
	}
	
	@Test
	public void testRemoveVisit() {
		Visit visit = getVisit();
		service.removeVisit(visit.getVisitId());
		verify(repository, times(1)).existsById(visit.getVisitId());
	}
	
	@Test
	public void testGetVisitByShowroomId() {
		Visit visit = getVisit();
		Mockito.when(repository.existsById(visit.getCar().getShowroom().getShowroomId())).thenReturn(true);
		service.getVisitByShowroomId(visit.getCar().getShowroom().getShowroomId());
		verify(repository, times(1)).existsById(visit.getCar().getShowroom().getShowroomId());	
	}
	
	@Test
	public void testGetVisitByCustomerId() {
		Visit visit = getVisit();
		Mockito.when(repository.existsById(visit.getCustomer().getCustomerId())).thenReturn(true);
		service.getVisitByCustomerId(visit.getCustomer().getCustomerId());
		verify(repository, times(1)).existsById(visit.getCustomer().getCustomerId());	
	}

	public Visit getVisit() {
		Visit visit = new Visit();
		visit.setVisitId(10);
		visit.setCustomer(getCustomer());
		visit.setCar(getCar());
		visit.setVisitDate(LocalDate.parse("2017-02-03"));
		visit.setVisitTime(LocalTime.of(10, 43, 12));

		return visit;
	}

	public Customer getCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("Dhanraj");
		customer.setLastName("Singh");
		customer.setPassword("pass123");
		customer.setEmail("dhanraj@gmail.com");
		customer.setCity("Nagpur");
		customer.setContactNo(988118885);

		return customer;
	}

	public Showroom getShowroom() {
		Showroom showroom = new Showroom();
		showroom.setShowroomId(2);
		showroom.setShowroomName("Pride");
		showroom.setManagerName("Raj");
		showroom.setContactNo("9875463215");
		showroom.setEmail("raj@gmail.com");
		showroom.setPassword("Raj456");
		showroom.setLocation("Pune");
		return showroom;
	}

	public Car getCar() {
		Car car = new Car();
		car.setCarId(15);
		car.setBrand("Toyota");
		car.setCarName("Fortuner");
		car.setColour("Black");
		car.setFuelType("Diesel");
		car.setModelNo("first");
		car.setPrice(4000000);
		car.setShowroom(getShowroom());

		return car;
	}

}