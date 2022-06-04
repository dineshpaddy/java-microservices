package com.au.transactions.exceptions;

public class MandatoryFieldException extends Exception{
    public MandatoryFieldException(String errorMessage) {
        super(errorMessage);
    }
}
