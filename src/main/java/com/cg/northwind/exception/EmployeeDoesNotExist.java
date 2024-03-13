package com.cg.northwind.exception;

public class EmployeeDoesNotExist extends Exception {

	public EmployeeDoesNotExist() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeDoesNotExist(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmployeeDoesNotExist(Throwable cause) {
		super(cause);

	}

	public EmployeeDoesNotExist(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeDoesNotExist(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
