package com.cg.northwind.exception;

public class IdAlreadyExistsException extends Exception {

	public IdAlreadyExistsException() {
	}

	public IdAlreadyExistsException(String message) {
		super(message);
	}

	public IdAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public IdAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
