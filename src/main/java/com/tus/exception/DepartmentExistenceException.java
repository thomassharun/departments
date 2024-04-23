package com.tus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class DepartmentExistenceException extends RuntimeException{
	
	public DepartmentExistenceException(String message) {
		super(message);
	}

}
