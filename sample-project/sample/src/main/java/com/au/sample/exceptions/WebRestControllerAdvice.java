package com.au.sample.exceptions;

import com.au.sample.error.AccountErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.au.sample.common.Constants.TECHNICAL_EXCEPTION_CODE;
import static com.au.sample.common.Constants.TECHNICAL_EXCEPTION_TYPE;
import static com.au.sample.common.Constants.TECHNICAL_EXCEPTION_MESSAGE;
import static com.au.sample.common.Constants.INVALID_PAGE_SIZE_EXCEPTION_CODE;
import static com.au.sample.common.Constants.INVALID_PAGE_SIZE_EXCEPTION_TYPE;
import static com.au.sample.common.Constants.INVALID_TRANSACTIONID_EXCEPTION_CODE;
import static com.au.sample.common.Constants.INVALID_TRANSACTIONID_EXCEPTION_TYPE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class WebRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity technicalException(Exception e) {
        log.error(e.getMessage(),e);
        AccountErrorResponse error = AccountErrorResponse.builder()
                .code(TECHNICAL_EXCEPTION_CODE)
                .type(TECHNICAL_EXCEPTION_TYPE)
                .message(TECHNICAL_EXCEPTION_MESSAGE)
                .build();
        return new ResponseEntity(error, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidPageSizeException.class)
    public ResponseEntity invalidPageSizeException(InvalidPageSizeException e) {
        log.error(e.getMessage(),e);
        AccountErrorResponse error = AccountErrorResponse.builder()
                .code(INVALID_PAGE_SIZE_EXCEPTION_CODE)
                .type(INVALID_PAGE_SIZE_EXCEPTION_TYPE)
                .message(e.getMessage())
                .build();
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTransactionIdException.class)
    public ResponseEntity invalidTransactionException(InvalidTransactionIdException e) {
        log.error(e.getMessage(),e);
        AccountErrorResponse error = AccountErrorResponse.builder()
                .code(INVALID_TRANSACTIONID_EXCEPTION_CODE)
                .type(INVALID_TRANSACTIONID_EXCEPTION_TYPE)
                .message(e.getMessage())
                .build();
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
