package com.zensar.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidStockIDException extends RuntimeException {

	private String message;

	public InvalidStockIDException() {
		super();
	}

	public InvalidStockIDException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Stock ID " + message ;
	}
	
}
