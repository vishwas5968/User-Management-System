package com.jsp.ums.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException implements ExceptionInterface{
	/**
	 * 
	 */
	private String message;
	private HttpStatus status;
	private String rootCause;
}
