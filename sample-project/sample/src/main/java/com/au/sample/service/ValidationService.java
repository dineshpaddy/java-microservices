package com.au.sample.service;

import com.au.sample.exceptions.InvalidPageSizeException;
import com.au.sample.exceptions.InvalidTransactionIdException;
import org.springframework.stereotype.Service;

/**
 * Service layer to validate the request attributes
 */
@Service
public interface ValidationService {

    public void validateHeaderAndPageSize(final Integer size, final String transactionId) throws InvalidPageSizeException, InvalidTransactionIdException;

}
