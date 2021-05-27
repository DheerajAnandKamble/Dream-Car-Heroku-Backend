package com.cg.car.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	@Id // It will determine most appropriate primary key generation
	@GeneratedValue(strategy = GenerationType.AUTO) // default generation type
	// @Column(name = "car_id") // custom column name
	private int carId;
	private String carName;
	private String modelNo;
	private String fuelType;
	private String colour;
	private double price;
	private String brand;

	@ManyToOne
	@JoinColumn(name = "showroom_id", referencedColumnName = "showroomId")
	private Showroom showroom;

	public Car() {
		super();
	}

	public Car(String carName, String modelNo, String fuelType, String colour, double price, String brand,
			Showroom showroom) {
		super();
		this.carName = carName;
		this.modelNo = modelNo;
		this.fuelType = fuelType;
		this.colour = colour;
		this.price = price;
		this.brand = brand;
		this.showroom = showroom;
	}

	public Car(int carId, String carName, String modelNo, String fuelType, String colour, double price, String brand,
			Showroom showroom) {
		super();
		this.carId = carId;
		this.carName = carName;
		this.modelNo = modelNo;
		this.fuelType = fuelType;
		this.colour = colour;
		this.price = price;
		this.brand = brand;
		this.showroom = showroom;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Showroom getShowroom() {
		return showroom;
	}

	public void setShowroom(Showroom showroom) {
		this.showroom = showroom;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carName=" + carName + ", modelNo=" + modelNo + ", fuelType=" + fuelType
				+ ", colour=" + colour + ", price=" + price + ", brand=" + brand + ", showroom=" + showroom + "]";
	}

}