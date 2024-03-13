package com.cg.northwind.exception;

public class CategoryAlreadyExistsException extends Exception {

	public CategoryAlreadyExistsException() {
	}

	public CategoryAlreadyExistsException(String message) {
		super(message);
	}

	public CategoryAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public CategoryAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoryAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
