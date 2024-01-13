package com.jsp.ums.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionInterface {

	String getRootCause();

	String getMessage();
	
	HttpStatus getStatus();
		
}
