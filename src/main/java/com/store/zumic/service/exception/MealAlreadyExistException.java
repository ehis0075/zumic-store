package com.store.zumic.service.exception;



public class MealAlreadyExistException extends RuntimeException {
    public MealAlreadyExistException(String message) {
        super(message);
    }

    public MealAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}

