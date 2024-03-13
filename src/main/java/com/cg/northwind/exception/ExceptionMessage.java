package com.cg.northwind.exception;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExceptionMessage {
	private String message;
	private LocalDate timeStamp;
	private int httpStatusCode;
	private String httpStatusMessage;

	public ExceptionMessage(LocalDate timeStamp, String message, int httpStatusCode, String httpStatusMessage) {
		super();
		this.message = message;
		this.timeStamp = timeStamp;
		this.httpStatusCode = httpStatusCode;
		this.httpStatusMessage = httpStatusMessage;
	}

}
