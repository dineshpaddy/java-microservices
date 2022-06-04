package com.au.transactions.exceptions;

public class InvalidRequestException extends Exception{
    public InvalidRequestException(String errorMessage) {
        super(errorMessage);
    }
}
