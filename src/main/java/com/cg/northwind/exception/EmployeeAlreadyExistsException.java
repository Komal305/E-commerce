package com.cg.northwind.exception;



public class EmployeeAlreadyExistsException extends Exception {

	public EmployeeAlreadyExistsException() {
	}

	public EmployeeAlreadyExistsException(String message) {
		super(message);
	}

	public EmployeeAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public EmployeeAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	
}
