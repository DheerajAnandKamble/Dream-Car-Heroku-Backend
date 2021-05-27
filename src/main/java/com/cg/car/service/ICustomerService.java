package com.cg.car.service;

import java.util.List;

import com.cg.car.entity.Customer;

public interface ICustomerService {

	public Customer addCustomer(Customer customer);

	public Customer updateCustomer(int customerId, Customer customer);

	public Customer getCustomer(int customerId);

	public Customer removeCustomer(int customerId);

	public List<Customer> getCustomerList();
	
	public List<Customer> getCustomerList(String city);
	public Customer validateCustomer(String email, String password);
}
