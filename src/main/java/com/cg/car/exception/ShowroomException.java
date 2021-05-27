package com.cg.car.exception;

public class ShowroomException extends RuntimeException  {

	String message;

	public ShowroomException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;

	}
}
