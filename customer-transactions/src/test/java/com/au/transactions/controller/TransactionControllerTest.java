package com.au.transactions.controller;

import com.au.transactions.dao.TransactionRepositoryDAO;
import com.au.transactions.exceptions.InvalidRequestException;
import com.au.transactions.exceptions.MandatoryFieldException;
import com.au.transactions.model.dto.EntityCostResponseDTO;
import com.au.transactions.model.dto.TotalTransactionsResponseDTO;
import com.au.transactions.model.dto.TransactionRequestDTO;
import com.au.transactions.model.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class TransactionControllerTest {

    @Autowired
    private TransactionController transactionController;
    @Autowired
    private TransactionRepositoryDAO transactionRepositoryDAO;

    @Test
    public void recordTransactionSuccess() throws MandatoryFieldException, InvalidRequestException {

        ResponseEntity responseEntity = transactionController.record(getTransactionRequestBody(), new HttpHeaders());
        List<Transaction> transactionList = transactionRepositoryDAO.findAll();
        Transaction transaction = transactionList.get(0);
        assertEquals(1, transactionList.size());
        assertEquals(Double.valueOf(100), transaction.getTotalCost());
        assertEquals(Integer.valueOf(3), transaction.getCustomer().getId());
        assertThat(responseEntity.getStatusCode(), Is.is(HttpStatus.OK));
        transactionRepositoryDAO.deleteAll();
    }

    @Test(expected = InvalidRequestException.class)
    public void recordInactiveProduct() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setProductCode("PRODUCT_002");
        transactionController.record(transactionRequestDTO, new HttpHeaders());
    }

    @Test(expected = InvalidRequestException.class)
    public void recordPastDate() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setTransactionTime("2020-06-05 19:56");
        transactionController.record(transactionRequestDTO, new HttpHeaders());
    }

    @Test(expected = InvalidRequestException.class)
    public void recordHigherTotalAmount() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setQuantity(101);
        transactionController.record(transactionRequestDTO, new HttpHeaders());
    }

    @Test(expected = MandatoryFieldException.class)
    public void validateMissingQuantity() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setQuantity(0);
        transactionController.record(transactionRequestDTO, new HttpHeaders());
    }

    @Test(expected = MandatoryFieldException.class)
    public void validateMissingProductCode() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setProductCode(null);
        transactionController.record(transactionRequestDTO, new HttpHeaders());
    }

    @Test(expected = MandatoryFieldException.class)
    public void validateMissingTransactionDate() throws MandatoryFieldException, InvalidRequestException {

        TransactionRequestDTO transactionRequestDTO = getTransactionRequestBody();
        transactionRequestDTO.setTransactionTime(null);
        transactionController.record(transactionRequestDTO, new HttpHeaders());
    }

    @Test
    public void testCostByCustomerSuccess() throws MandatoryFieldException, InvalidRequestException {

        transactionController.record(getTransactionRequestBody(), new HttpHeaders());
        ResponseEntity responseEntity = transactionController.getCostByCustomer(3, new HttpHeaders());
        EntityCostResponseDTO entityCostResponseDTO = (EntityCostResponseDTO) responseEntity.getBody();
        assertEquals(Double.valueOf(100),entityCostResponseDTO.getValue());
        assertThat(responseEntity.getStatusCode(), Is.is(HttpStatus.OK));
        transactionRepositoryDAO.deleteAll();
    }

    @Test(expected = MandatoryFieldException.class)
    public void testCostByCustomerInvalidRequest() throws MandatoryFieldException {
        transactionController.getCostByCustomer(0, new HttpHeaders());
    }

    @Test
    public void testCostByProductSuccess() throws MandatoryFieldException, InvalidRequestException {

        transactionController.record(getTransactionRequestBody(), new HttpHeaders());
        ResponseEntity responseEntity = transactionController.getCostByProduct("PRODUCT_001", new HttpHeaders());
        EntityCostResponseDTO entityCostResponseDTO = (EntityCostResponseDTO) responseEntity.getBody();
        assertEquals(Double.valueOf(100),entityCostResponseDTO.getValue());
        assertThat(responseEntity.getStatusCode(), Is.is(HttpStatus.OK));
        transactionRepositoryDAO.deleteAll();
    }

    @Test(expected = MandatoryFieldException.class)
    public void testCostByProductInvalidRequest() throws MandatoryFieldException {
        transactionController.getCostByProduct("", new HttpHeaders());
    }

    @Test
    public void testCostByCustomerAndLocationRequest() throws MandatoryFieldException, InvalidRequestException {
        transactionController.record(getTransactionRequestBody(), new HttpHeaders());
        ResponseEntity responseEntity = transactionController.getByCustomerAndLocation(3,"Australia", new HttpHeaders());
        TotalTransactionsResponseDTO totalTransactionsResponseDTO = (TotalTransactionsResponseDTO) responseEntity.getBody();
        assertEquals(Integer.valueOf(1),totalTransactionsResponseDTO.getValue());
        assertThat(responseEntity.getStatusCode(), Is.is(HttpStatus.OK));
        transactionRepositoryDAO.deleteAll();
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
