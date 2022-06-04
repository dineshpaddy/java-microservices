package com.au.transactions.common;

public final class Constants {

    public static final String SUCCESS_CODE = "00000";
    public static final String SUCCESS_MESSAGE = "Request processed successfully.";
    public static final String SUCCESS_COST_BY_CUSTOMER = "Total cost of transactions per customer";
    public static final String SUCCESS_COST_BY_PRODUCT = "Total cost of transactions per product";
    public static final String SUCCESS_COST_BY_CUSTOMERANDLOCATION = "Number of transactions sold to customer from Australia";
    public static final String TECHNICAL_EXCEPTION_MESSAGE = "A technical error has occurred during the processing of the request.";
    public static final String TECHNICAL_EXCEPTION_CODE = "00001";
    public static final String TECHNICAL_EXCEPTION_TYPE = "TechnicalException";
    public static final String MANDATORY_FIELD_EXCEPTION_MESSAGE = "The value for %s is empty";
    public static final String MANDATORY_FIELD_EXCEPTION_CODE = "00002";
    public static final String MANDATORY_FIELD_EXCEPTION_TYPE = "ValidationException";
    public static final String RECORD_NOT_FOUND_EXCEPTION_CODE = "00004";
    public static final String RECORD_NOT_FOUND_EXCEPTION_TYPE = "NotFoundException";
    public static final String RECORD_NOT_FOUND_EXCEPTION_MESSAGE = "Record not found for the provided id %s";
    public static final String RECORD_NOT_FOUND_ENTITY_EXCEPTION_MESSAGE = "Record not found for entity %s for the provided id %s";
    public static final String INVALID_REQUEST_EXCEPTION_CODE = "00003";
    public static final String INVALID_REQUEST_EXCEPTION_TYPE = "ValidationException";
    public static final String PAST_TRANSACTION_DATE_ERROR_MESSAGE = "TransactionDate must not be in the past";
    public static final String TRANSACTION_TOTAL_COST_ERROR_MESSAGE = "Total cost of transaction must not exceed 5000";
    public static final String INACTIVE_PRODUCT_ERROR_MESSAGE = "Product must be active";
}
