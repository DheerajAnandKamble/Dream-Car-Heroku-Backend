package com.cg.car.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="showroom")
public class Showroom {

	@Id // It will determine most appropriate primary key generation
	@GeneratedValue(strategy = GenerationType.AUTO) // default generation type
//	@Column(name = "showroom_id") // custom column name
	private int showroomId;
	
	@NotEmpty(message = "Showroom Name is required ")
	private String showroomName;
	
	@NotEmpty(message = "Manager Name is required ")
	private String managerName;
	
	@NotEmpty(message = "Email is required ")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+[@][a-zA-Z]+[.][a-zA-Z]+", message = "Email id is invalid")
	private String email;
	
	@Size(min = 6, message = "The password must be at least 6 characters long.")
	private String password;
	
	@NotNull(message = "Contact number cannot be empty")
	private String contactNo;
	
	@NotNull(message = "Location  is required")
	private String location;

	public Showroom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Showroom(String showroomName, String managerName, String email, String password, String contactNo,
			String location) {
		super();
		this.showroomName = showroomName;
		this.managerName = managerName;
		this.email = email;
		this.password = password;
		this.contactNo = contactNo;
		this.location = location;
	}

	public Showroom(int showroomId, String showroomName, String managerName, String email, String password,
			String contactNo, String location) {
		super();
		this.showroomId = showroomId;
		this.showroomName = showroomName;
		this.managerName = managerName;
		this.email = email;
		this.password = password;
		this.contactNo = contactNo;
		this.location = location;
	}

	public int getShowroomId() {
		return showroomId;
	}

	public void setShowroomId(int showroomId) {
		this.showroomId = showroomId;
	}

	public String getShowroomName() {
		return showroomName;
	}

	public void setShowroomName(String showroomName) {
		this.showroomName = showroomName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
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

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Showroom [showroomId=" + showroomId + ", showroomName=" + showroomName + ", managerName=" + managerName
				+ ", email=" + email + ", password=" + password + ", contactNo=" + contactNo + ", location=" + location
				+ "]";
	}

}
