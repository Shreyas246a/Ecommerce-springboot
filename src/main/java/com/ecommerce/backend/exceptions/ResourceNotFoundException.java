package com.ecommerce.backend.exceptions;

import lombok.experimental.StandardException;

@StandardException
public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException() {
		super();
	}
}
