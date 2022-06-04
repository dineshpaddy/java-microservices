package com.au.transactions.exceptions;

import com.au.transactions.error.TransactionErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.au.transactions.common.Constants.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class WebRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity technicalException(Exception e) {
        log.error(e.getMessage(),e);
        TransactionErrorResponse error = TransactionErrorResponse.builder()
                .code(TECHNICAL_EXCEPTION_CODE)
                .type(TECHNICAL_EXCEPTION_TYPE)
                .message(TECHNICAL_EXCEPTION_MESSAGE)
                .build();
        return new ResponseEntity(error, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MandatoryFieldException.class)
    public ResponseEntity mandatoryFieldException(MandatoryFieldException e) {
        log.error(e.getMessage(),e);
        TransactionErrorResponse error = TransactionErrorResponse.builder()
                .code(MANDATORY_FIELD_EXCEPTION_CODE)
                .type(MANDATORY_FIELD_EXCEPTION_TYPE)
                .message(e.getMessage())
                .build();
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity invalidRequestException(InvalidRequestException e) {
        log.error(e.getMessage(),e);
        TransactionErrorResponse error = TransactionErrorResponse.builder()
                .code(INVALID_REQUEST_EXCEPTION_CODE)
                .type(INVALID_REQUEST_EXCEPTION_TYPE)
                .message(e.getMessage())
                .build();
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
