package com.musala.droneapp.exceptions;

public class BatteryCriticallyLowException extends RuntimeException {

    public BatteryCriticallyLowException(String message) {
        super(message);
    }
}
