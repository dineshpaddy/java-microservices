package com.au.transactions.service;

import com.au.transactions.exceptions.InvalidRequestException;
import com.au.transactions.exceptions.MandatoryFieldException;
import com.au.transactions.model.dto.TransactionRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface ValidationService {

    public void validateRequestBody(final TransactionRequestDTO transactionRequestDTO) throws MandatoryFieldException, InvalidRequestException;
}
