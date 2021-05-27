package com.cg.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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

import com.cg.car.entity.Customer;
import com.cg.car.repository.ICustomerRepository;
import com.cg.car.service.ICustomerService;

@SpringBootTest
public class CustomerTest {

	@Autowired
	private ICustomerService customerService;

	@MockBean
	private ICustomerRepository customerRepository;

	// test case for adding Customer

	@Test
	public void testAddCustomer() {
		// TODO Auto-generated method stub

		Customer customer = getCustomer();

		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		Customer result = customerService.addCustomer(customer);
		assertEquals(customer.getCity(), result.getCity());
		assertEquals(customer.getContactNo(), result.getContactNo());
		assertEquals(customer.getEmail(), result.getEmail());
		assertEquals(customer.getFirstName(), customer.getFirstName());
		assertEquals(customer.getLastName(), customer.getLastName());
		assertEquals(customer.getPassword(), customer.getPassword());

	}

	// test cases for updating Customer

	@Test
	public void testUpdateCustomer() {
		// TODO Auto-generated method stub

		Customer customer = getCustomer();
		when(customerRepository.existsById(customer.getCustomerId())).thenReturn(true);
		when(customerRepository.getOne(customer.getCustomerId())).thenReturn(customer);
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer result = customerService.updateCustomer(customer.getCustomerId(), customer);
		assertEquals(customer.getCity(), result.getCity());
		assertEquals(customer.getContactNo(), result.getContactNo());
		assertEquals(customer.getEmail(), result.getEmail());
		assertEquals(customer.getFirstName(), customer.getFirstName());
		assertEquals(customer.getLastName(), customer.getLastName());
		assertEquals(customer.getPassword(), customer.getPassword());

	}

	@Test
	public void testVerifyUpdateCustomer() {
		// TODO Auto-generated method stub

		Customer customer = getCustomer();
		customerService.updateCustomer(customer.getCustomerId(), customer);
		verify(customerRepository, times(1)).existsById(customer.getCustomerId());
	}

	// test cases for getting Customer by id
	@Test
	public void testGetCustomer() {
		Customer customer = getCustomer();
		Mockito.when(customerRepository.existsById(customer.getCustomerId())).thenReturn(true);
		customerService.getCustomer(customer.getCustomerId());
		verify(customerRepository, times(1)).findById(customer.getCustomerId());
	

	}

	// test case for removing Customer by id
	@Test
	public void testRemoveCustomer() {
		Customer customer = getCustomer();
		Mockito.when(customerRepository.existsById(customer.getCustomerId())).thenReturn(true);
		customerService.removeCustomer(customer.getCustomerId());
		verify(customerRepository, times(1)).existsById(customer.getCustomerId());
	}

	// test case for fetching all Customers
	@Test
	public void testGetAllCustomers() {
		when(customerRepository.findAll()).thenReturn(Stream.of(getCustomer()).collect(Collectors.toList()));
		assertEquals(1, customerService.getCustomerList().size());
	}

	// test case for fetching all Customers by city
	@Test
	public void testGetAllCustomersByCity() {
		Customer customer = getCustomer();
		when(customerRepository.findByCity(customer.getCity()))
				.thenReturn(Stream.of(customer).collect(Collectors.toList()));
		assertEquals(1, customerService.getCustomerList(customer.getCity()).size());
	}

	// test case for validating customer
	@Test
	public void testValidateCustomer() {
		Customer customer = getCustomer();
		customerService.validateCustomer(customer.getEmail(), customer.getPassword());
		verify(customerRepository, times(1)).findCustomerByEmail(customer.getEmail());

	}

	private Customer getCustomer() {

		Customer customer = new Customer();
		customer.setCustomerId(2);
		customer.setFirstName("Dhanraj");
		customer.setLastName("Singh");
		customer.setPassword("pass123");
		customer.setEmail("dhanraj@gmail.com");
		customer.setCity("Nagpur");
		customer.setContactNo(988118885);

		return customer;
	}

}
