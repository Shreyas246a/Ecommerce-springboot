package com.ecommerce.backend.exceptions;

public class NotAllowedException extends RuntimeException {

    public NotAllowedException(String message) {
        super(message);
    }

    public NotAllowedException() {
        super();
    }
}
