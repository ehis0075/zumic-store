package com.store.zumic.service.exception;


public class ServiceProviderNotFoundException extends RuntimeException {

    public ServiceProviderNotFoundException(String message) {
        super(message);
    }

    public ServiceProviderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}



