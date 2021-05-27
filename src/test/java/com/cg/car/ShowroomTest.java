package com.cg.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.car.entity.Showroom;
import com.cg.car.repository.IShowroomRepository;
import com.cg.car.service.IShowroomService;

@SpringBootTest
public class ShowroomTest {

	@Autowired
	IShowroomService service;

	@MockBean
	IShowroomRepository repository;

	// test case for add showroom
	@Test
	public void testAddShowroom() {

		Showroom showroom = getShowroom();

		Mockito.when(repository.save(showroom)).thenReturn(showroom);
		Showroom result = service.addShowroom(showroom);
		assertEquals(showroom, result); // to check whether the objects are equal

	}
	
	// test cases for updating showroom
	@Test
	public void testUpdateShowroom() {

		Showroom showroom = getShowroom();
		showroom.setManagerName("Amit");
		when(repository.save(showroom)).thenReturn(showroom);
		Showroom showroom1=service.updateShowroom(showroom);
		repository.save(showroom1);
		assertEquals("Amit",showroom1.getManagerName());
		
	}

	// test case for getting showroom by id
	@Test
	public void testGetShowroom() {
		Showroom showroom = getShowroom();
		service.getShowroomById(showroom.getShowroomId());
		verify(repository, times(1)).findById(showroom.getShowroomId()); // Verify that the correct methods of
																			// our
		// mock objects were invoked
	}

	// test case for removing showroom by id
	@Test
	public void testRemoveShowroom() {
		Showroom showroom = getShowroom();
		when(repository.save(showroom)).thenReturn(showroom);
		Showroom showroom1 = service.removeShowroomById(77);
		assertNull(showroom1);
	}

	// test case for fetching all showroom
	@Test
	public void testGetAllShowroom() {
		when(repository.findAll()).thenReturn(Stream.of(getShowroom()).collect(Collectors.toList()));
		// assertEquals(1, service.getShowroomList().size());
		assertTrue(service.getShowroomList().size() >= 1);
	}

	// test case for fetching all showroom by location
	@Test
	public void testGetAllShowroomsByLocation() {
		Showroom showroom = getShowroom();
		when(repository.findShowroomByLocation(showroom.getLocation()))
				.thenReturn(Stream.of(showroom).collect(Collectors.toList()));
		assertEquals(1, service.getAllShowroomByLocation(showroom.getLocation()).size());
	}

	// test case for fetching all showroom by name
	@Test
	public void testGetAllShowroomByName() {
		Showroom showroom = getShowroom();
		when(repository.findShowroomByShowroomName(showroom.getShowroomName()))
				.thenReturn(Stream.of(showroom).collect(Collectors.toList()));
		assertEquals(0, service.getAllShowroomByName("DSK").size());
	}

	// test case for validating showroom
	@Test
	public void testValidateShowroom() {
		Showroom showroom = getShowroom();
		service.validateShowroom(showroom.getEmail(), showroom.getPassword());
		verify(repository, times(1)).findShowroomByEmail(showroom.getEmail());
	}

	public Showroom getShowroom() {
		Showroom showroom = new Showroom();
		showroom.setShowroomId(10);
		showroom.setShowroomName("Pride");
		showroom.setManagerName("Raj");
		showroom.setContactNo("9875463215");
		showroom.setEmail("raj@gmail.com");
		showroom.setPassword("Raj456");
		showroom.setLocation("Pune");
		return showroom;
	}

}