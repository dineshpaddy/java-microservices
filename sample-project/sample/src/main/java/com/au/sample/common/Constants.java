package com.au.sample.common;

public final class Constants {

    public static final String TECHNICAL_EXCEPTION_MESSAGE = "A technical error has occurred during the processing of the request.";
    public static final String TECHNICAL_EXCEPTION_CODE = "00001";
    public static final String TECHNICAL_EXCEPTION_TYPE = "TechnicalException";
    public static final String INVALID_PAGE_SIZE_EXCEPTION_MESSAGE = "The value for %s should not exceed %s";
    public static final String INVALID_PAGE_SIZE_EXCEPTION_CODE = "00002";
    public static final String INVALID_PAGE_SIZE_EXCEPTION_TYPE = "ValidationException";
    public static final String RECORD_NOT_FOUND_EXCEPTION_CODE = "00004";
    public static final String RECORD_NOT_FOUND_EXCEPTION_TYPE = "NotFoundException";
    public static final String RECORD_NOT_FOUND_EXCEPTION_MESSAGE = "Record not found for the provided id %s";
    public static final String INVALID_TRANSACTIONID_EXCEPTION_MESSAGE = "Invalid transactionID : %s provided in the request";
    public static final String TRANSACTIONID_NOT_FOUND_EXCEPTION_MESSAGE = "Parameter transactionID not provided in the request";
    public static final String INVALID_TRANSACTIONID_EXCEPTION_CODE = "00003";
    public static final String INVALID_TRANSACTIONID_EXCEPTION_TYPE = "ValidationException";
    public static final Integer DEFAULT_PAGE_SIZE = 5;
    public static final Integer DEFAULT_PAGE_NUMBER = 1;
}
