package com.au.transactions.service;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepositoryDAO transactionRepositoryDAO;

    @Test
    public void recordTransactionSuccess() throws MandatoryFieldException, InvalidRequestException {

        ResponseEntity responseEntity = transactionService.record(getTransactionRequestBody());
        List<Transaction> transactionList = transactionRepositoryDAO.findAll();
        Transaction transaction = transactionList.get(0);
        assertEquals(1, transactionList.size());
        assertEquals(Double.valueOf(100), transaction.getTotalCost());
        assertEquals(Integer.valueOf(3), transaction.getCustomer().getId());
        assertThat(responseEntity.getStatusCode(), Is.is(HttpStatus.OK));
        transactionRepositoryDAO.deleteAll();
    }

    @Test
    public void testCostByCustomerSuccess() {

        transactionService.record(getTransactionRequestBody());
        ResponseEntity responseEntity = transactionService.getCostByCustomer(3);
        EntityCostResponseDTO entityCostResponseDTO = (EntityCostResponseDTO) responseEntity.getBody();
        assertEquals(Double.valueOf(100),entityCostResponseDTO.getValue());
        assertThat(responseEntity.getStatusCode(), Is.is(HttpStatus.OK));
        transactionRepositoryDAO.deleteAll();
    }

    @Test
    public void testCostByProductSuccess() {

        transactionService.record(getTransactionRequestBody());
        ResponseEntity responseEntity = transactionService.getCostByProduct("PRODUCT_001");
        EntityCostResponseDTO entityCostResponseDTO = (EntityCostResponseDTO) responseEntity.getBody();
        assertEquals(Double.valueOf(100),entityCostResponseDTO.getValue());
        assertThat(responseEntity.getStatusCode(), Is.is(HttpStatus.OK));
        transactionRepositoryDAO.deleteAll();
    }

    @Test
    public void testCostByCustomerAndLocationRequest() throws MandatoryFieldException, InvalidRequestException {
        transactionService.record(getTransactionRequestBody());
        ResponseEntity responseEntity = transactionService.getByCustomerAndLocation(3,"Australia");
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
