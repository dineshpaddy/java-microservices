package com.au.sample.exceptions;

public class InvalidTransactionIdException extends Exception{
    public InvalidTransactionIdException(String errorMessage) {
        super(errorMessage);
    }
}
