package com.cg.northwind.exception;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(IdAlreadyExistsException.class)
	public ResponseEntity<ExceptionMessage> idAlreadyExistsException(IdAlreadyExistsException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionMessage> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(IdDoesNotExistsException.class)
	public ResponseEntity<ExceptionMessage> idDoesNotExistsException(IdDoesNotExistsException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(CategoryDoesNotExistsException.class)
	public ResponseEntity<ExceptionMessage> categoryDoesNotExistsException(CategoryDoesNotExistsException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ExceptionMessage> noSuchElementException(NoSuchElementException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<ExceptionMessage> noContentException(NoContentException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(ShippersAlreadyExistsException.class)
	public ResponseEntity<ExceptionMessage> shippersAlreadyExistsException(ShippersAlreadyExistsException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(BodyNotFoundException.class)
	public ResponseEntity<ExceptionMessage> bodyNotFoundException(BodyNotFoundException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(ProductNotExistsException.class)
	public ResponseEntity<ExceptionMessage> productNotExistsException(ProductNotExistsException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(ProductIdAlreadyExistsException.class)
	public ResponseEntity<ExceptionMessage> productIdAlreadyExistsException(ProductIdAlreadyExistsException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.CONFLICT.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionMessage> httpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(ShipperIdNotFoundException.class)
	public ResponseEntity<ExceptionMessage> shipperIdNotFoundException(ShipperIdNotFoundException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(OrderAlreadyExistException.class)
	public ResponseEntity<ExceptionMessage> orderAlreadyExistException(OrderAlreadyExistException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(CustomerNotfoundException.class)
	public ResponseEntity<ExceptionMessage> customerNotfoundException(CustomerNotfoundException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);

	}

	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<ExceptionMessage> employeeAlreadyExistsException(EmployeeAlreadyExistsException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);

	}
	@ExceptionHandler(EmployeeDoesNotExist.class)
	public ResponseEntity<ExceptionMessage> employeeDoesNotExist(EmployeeDoesNotExist e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
		
	}
	@ExceptionHandler(OrderIdNotFoundException.class)
    public ResponseEntity<ExceptionMessage> orderIdNotFoundException(OrderIdNotFoundException e) {
        ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
                HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase());
        return ResponseEntity.badRequest().body(errorResponse);
    }
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionMessage> httpMessageNotReadableException(HttpMessageNotReadableException e) {
		ExceptionMessage errorResponse = new ExceptionMessage(LocalDate.now(), e.getMessage(),
				HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase());
		return ResponseEntity.badRequest().body(errorResponse);
	}

}
