package com.au.sample.exceptions;

public class InvalidPageSizeException extends Exception{
    public InvalidPageSizeException(String errorMessage) {
        super(errorMessage);
    }
}
