package com.sber.incrementor.exception.model;

/**
 * Represents custom exception for errors with maximum value
 */
public class InvalidMaxValueException extends Exception {

    public InvalidMaxValueException(String message) {
        super(message);
    }
}
