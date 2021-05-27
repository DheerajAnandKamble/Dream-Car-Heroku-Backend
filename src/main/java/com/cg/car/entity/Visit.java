package com.cg.car.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class Visit {
	
	@Id // It will determine most appropriate primary key generation
	@GeneratedValue(strategy = GenerationType.AUTO) // default generation type
	//@Column(name = "visit_id") // custom column name
	private int visitId;

	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name="car_id")
	private Car car;
	private LocalDate visitDate;
	@JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING, timezone="GMT")
	private LocalTime visitTime;

	public Visit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Visit(Customer customer, Car car, LocalDate visitDate, LocalTime visitTime) {
		super();
		this.customer = customer;
		this.car = car;
		this.visitDate = visitDate;
		this.visitTime = visitTime;
	}

	public Visit(int visitId, Customer customer, Car car, LocalDate visitDate, LocalTime visitTime) {
		super();
		this.visitId = visitId;
		this.customer = customer;
		this.car = car;
		this.visitDate = visitDate;
		this.visitTime = visitTime;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public LocalDate getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}

	public LocalTime getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(LocalTime visitTime) {
		this.visitTime = visitTime;
	}

	@Override
	public String toString() {
		return "Visit [visitId=" + visitId + ", customer=" + customer + ", car=" + car + ", visitDate=" + visitDate
				+ ", visitTime=" + visitTime + "]";
	}

	
	

}