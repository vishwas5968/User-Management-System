package com.jsp.ums.util;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ums.exception.ExceptionInterface;
import com.jsp.ums.exception.UserNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	private Object exceptionStructure(int status, String msg, String rootCause) {
		return Map.of("Status", status, "Message", msg, "Root Cause", rootCause);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(ExceptionInterface ex) {
		return new ResponseEntity<Object>(exceptionStructure(ex.getStatus().value(),ex.getMessage(),ex.getRootCause()),ex.getStatus());
	}
}
