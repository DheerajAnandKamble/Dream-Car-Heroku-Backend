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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.car.entity.Customer;
import com.cg.car.exception.CustomerException;
import com.cg.car.service.ICustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/DreamCarApp/Customer")
public class CustomerController {

	@Autowired
	ICustomerService CustomerService;

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	// Method to add Customer
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {

		Customer customerData = CustomerService.addCustomer(customer);
		if (customerData == null) {
			logger.error("Controller: Failed to add customer");
			throw new CustomerException("Customer not added");
		}
		logger.info("*** Controller : Customer added successfully. ***");
		return new ResponseEntity<Customer>(customerData, HttpStatus.OK);

	}

	// Method to update Customer
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable int id) {
		Customer customerData = null;
		Customer customerById = CustomerService.getCustomer(id);

		if (customerById == null) {
			logger.error("Controller: Failed to update customer");
			throw new CustomerException("Customer is not present with id " + id);

		} else {
			customerData = CustomerService.updateCustomer(id, customer);

		}

		logger.info("*** Controller : Customer updated successfully. ***");
		return new ResponseEntity<Customer>(customerData, HttpStatus.OK);
	}

	// Method to get Customer by id
	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {

		Customer customerData = CustomerService.getCustomer(id);

		if (customerData == null) {
			logger.error("Controller: Customer Not Found For given id : " + id);
			throw new CustomerException("No Customer present with the given id " + id);
		}

		logger.info("*** Controller : Displaying Customer ***"+customerData);
		
		return new ResponseEntity<Customer>(customerData, HttpStatus.OK);
	}

	// Method to delete Customer by id
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {

		Customer customerData = CustomerService.removeCustomer(id);

		if (customerData == null) {
			logger.error("Controller: Customer Not Found For given id : " + id);
			throw new CustomerException("No Customer present with the given id " + id);
		}

		logger.info("*** Controller : Customer removed. ***");
		return new ResponseEntity<Customer>(customerData, HttpStatus.OK);
	}

	// Method to get all Customers
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customersList = CustomerService.getCustomerList();

		if (customersList.size() == 0) {
			logger.error("Controller: Customers not found.");
			throw new CustomerException("No Customers in the database ");
		}

		logger.info("*** Controller : Displaying customers list. ***");
		return new ResponseEntity<List<Customer>>(customersList, HttpStatus.OK);
	}

	// Method to get Customers by City
	@GetMapping("/CustomersByCity")
	public ResponseEntity<List<Customer>> getCustomerByCity(@RequestParam("city") String city) {
		List<Customer> customersList = CustomerService.getCustomerList(city);
		if (customersList.size() == 0) {
			logger.error("Controller: Customers not found.");
			throw new CustomerException("No Customers in the database with city " + city);
		}
		logger.info("*** Controller : Displaying Customers list by city ***");
		return new ResponseEntity<List<Customer>>(customersList, HttpStatus.OK);
	}

	// Method to Validate Customer
	@GetMapping("/validateCustomer/{email}/{password}")
	public ResponseEntity<Customer> validateCustomer(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		Customer customerData = CustomerService.validateCustomer(email, password);
		if (customerData == null) {
			logger.error("**Controller : Invalid Credentials**");
			throw new CustomerException("Login Failure..Please check your credentials ");
		} else {
			logger.info("**Controller : Successfull Login in Customer **");
		}

		return new ResponseEntity<Customer>(customerData, HttpStatus.OK);
	}

}
