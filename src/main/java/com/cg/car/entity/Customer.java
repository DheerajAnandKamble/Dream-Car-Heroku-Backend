package com.cg.car.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Entity // class is an entity and is mapped to database table
@Table(name = "customer") // table name for customer table
public class Customer {
	@Id // It will determine most appropriate primary key generation
	@GeneratedValue(strategy = GenerationType.AUTO) // default generation type
	//@Column(name = "customer_id") // custom column name
	private int customerId;

	@Column(name = "first_name")
	@NotEmpty(message = "First Name is required ")
	private String FirstName;

	@Column(name = "last_name")
	@NotEmpty(message = "First Name is required ")
	private String LastName;

	@Column(name = "contact_no")
	@NotNull(message = "Mobile number cannot be empty")
	@Positive(message = "Mobile number must be positive value")
	private long contactNo;

	@NotEmpty(message = "City name is required ")
	private String city;

	@NotEmpty(message = "Email is required")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+[@][a-zA-Z]+[.][a-zA-Z]+", message = "Email id is invalid")
	private String email;

	@Size(min = 6, message = "The password must be at least 6 characters long.")
	private String password;

	public Customer() {
		super();
	}

	public Customer(int customerId, String firstName, String lastName, long contactNo, String city, String email,
			String password) {
		super();
		this.customerId = customerId;
		FirstName = firstName;
		LastName = lastName;
		this.contactNo = contactNo;
		this.city = city;
		this.email = email;
		this.password = password;
	}

	public Customer(String firstName, String lastName, long contactNo, String city, String email, String password) {
		super();
		FirstName = firstName;
		LastName = lastName;
		this.contactNo = contactNo;
		this.city = city;
		this.email = email;
		this.password = password;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", FirstName=" + FirstName + ", LastName=" + LastName
				+ ", contactNo=" + contactNo + ", city=" + city + ", email=" + email + ", password=" + password + "]";
	}

}