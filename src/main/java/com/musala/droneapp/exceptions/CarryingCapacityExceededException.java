package com.musala.droneapp.exceptions;

public class CarryingCapacityExceededException extends RuntimeException {

    public CarryingCapacityExceededException(String message) {
        super(message);
    }
}
