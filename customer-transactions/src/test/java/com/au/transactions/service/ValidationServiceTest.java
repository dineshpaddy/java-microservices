package com.au.transactions.service;

import com.au.transactions.exceptions.InvalidRequestException;
import com.au.transactions.exceptions.MandatoryFieldException;
import com.au.transactions.model.dto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class ValidationServiceTest {

    @Autowired
    private ValidationService validationService;

    @Test(expected = InvalidRequestException.class)
    public void recordInactiveProduct() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setProductCode("PRODUCT_002");
        validationService.validateRequestBody(transactionRequestDTO);
    }

    @Test(expected = InvalidRequestException.class)
    public void recordPastDate() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setTransactionTime("2020-06-05 19:56");
        validationService.validateRequestBody(transactionRequestDTO);
    }

    @Test(expected = InvalidRequestException.class)
    public void recordHigherTotalAmount() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setQuantity(101);
        validationService.validateRequestBody(transactionRequestDTO);
    }

    @Test(expected = MandatoryFieldException.class)
    public void validateMissingQuantity() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setQuantity(0);
        validationService.validateRequestBody(transactionRequestDTO);
    }

    @Test(expected = MandatoryFieldException.class)
    public void validateMissingProductCode() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setProductCode(null);
        validationService.validateRequestBody(transactionRequestDTO);
    }

    @Test(expected = MandatoryFieldException.class)
    public void validateMissingTransactionDate() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setTransactionTime(null);
        validationService.validateRequestBody(transactionRequestDTO);
    }

    private TransactionRequestDTO getTransactionRequestBody() {
        TransactionRequestDTO transactionRequestDTO = TransactionRequestDTO.builder().build();
        transactionRequestDTO.setTransactionTime("2029-06-05 19:56");
        transactionRequestDTO.setQuantity(2);
        transactionRequestDTO.setCustomerId(10003);
        transactionRequestDTO.setProductCode("PRODUCT_001");
        return transactionRequestDTO;
    }
}
