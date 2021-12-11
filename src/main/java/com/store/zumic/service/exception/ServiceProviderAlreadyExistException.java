package com.store.zumic.service.exception;


public class ServiceProviderAlreadyExistException extends RuntimeException {
    public ServiceProviderAlreadyExistException(String message) {
        super(message);
    }

    public ServiceProviderAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
