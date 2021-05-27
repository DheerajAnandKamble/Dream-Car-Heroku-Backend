package com.cg.car.exception;

public class VisitException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	String message;

	public VisitException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;

	}
}
