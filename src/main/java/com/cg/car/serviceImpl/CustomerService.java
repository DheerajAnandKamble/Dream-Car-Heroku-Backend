package com.cg.car.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.car.entity.Customer;
import com.cg.car.repository.ICustomerRepository;
import com.cg.car.service.ICustomerService;

@Service
@Transactional
public class CustomerService implements ICustomerService {
	// Service implementation for Customer

	@Autowired
	ICustomerRepository customerRepository;

	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	// Method to add customer
	// INPUT : customer
	// OUTPUT : added customer details

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub

		Customer customerObj = customerRepository.save(customer);
		logger.info("*** Service :  Customer added successfully. ***");
		return customerObj;
	}

	// Method to update customer
	// INPUT : customer
	// OUTPUT : updated customer details

	@Override
	public Customer updateCustomer(int customerId, Customer customer) {
		// TODO Auto-generated method stub

		if (customerRepository.existsById(customerId)) {
			Customer customerObj = customerRepository.getOne(customerId);
			customerObj.setFirstName(customer.getFirstName());
			customerObj.setLastName(customer.getLastName());
			customerObj.setEmail(customer.getEmail());
			customerObj.setPassword(customer.getPassword());
			customerObj.setCity(customer.getCity());
			customerObj.setContactNo(customer.getContactNo());
			logger.info("*** Service :  Customer updated successfully. ***");
			return customerRepository.save(customerObj);
		} else {
			return null;
		}
	}

	// Method to get customer by customer Id
	// INPUT : customer Id
	// OUTPUT : customer by customer Id

	@Override
	public Customer getCustomer(int customerId) {
		if (customerRepository.existsById(customerId)) {

			logger.info("*** Service : Displaying customer with id ***" + customerId);
			Customer customer=customerRepository.findById(customerId).orElse(null);
			logger.info("*** Service : Displaying customer  ***" + customer);
			return customer;
		} else {
			logger.error("*** Service : Customer is not present with the given id  ***");
			return null;
		}

	}

	// Method to remove customer by customerId
	// INPUT : customerId
	// OUTPUT : removed customer by customerId

	@Override
	public Customer removeCustomer(int customerId) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findById(customerId).orElse(null);

		logger.warn("*** Removing Customer ***");

		if (customerRepository.existsById(customerId)) {

			customerRepository.deleteById(customerId);
			logger.info("*** Service : Customer removed ***");
		}

		return customer;
	}

	// Method to get all customers
	// OUTPUT : List of all customers

	@Override
	public List<Customer> getCustomerList() {
		// TODO Auto-generated method stub
		logger.info("*** Service : Displaying customer information ***");
		return customerRepository.findAll();
	}

	// Method to get all customers
	// INPUT : City
	// OUTPUT : List of all customers by City
	@Override
	public List<Customer> getCustomerList(String city) { // TODO Auto-generated method stub
		logger.info("*** Service : Displaying Customers by City ***");
		return customerRepository.findByCity(city);

	}

	public Customer validateCustomer(String email, String password) {

		Customer customer = customerRepository.findCustomerByEmail(email);

		if (customer == null) {
			logger.info("***Service : Customer login failed ***");
			return null;
		} else {
			if (customer.getPassword().equals(password)) {
				logger.info("***Service : Displaying customer Login ***");

				logger.info("***Service : Customer login Successful ***");
				return customer;
			} else {
				logger.info("***Service : Customer login failed ***");
				return null;
			}
		}

	}

}
