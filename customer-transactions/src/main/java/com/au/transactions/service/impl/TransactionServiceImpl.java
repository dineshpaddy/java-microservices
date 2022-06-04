package com.au.transactions.service.impl;

import com.au.transactions.dao.CustomerRepositoryDAO;
import com.au.transactions.dao.ProductRepositoryDAO;
import com.au.transactions.dao.TransactionRepositoryDAO;
import com.au.transactions.error.TransactionErrorResponse;
import com.au.transactions.model.dto.EntityCostResponseDTO;
import com.au.transactions.model.dto.TotalTransactionsResponseDTO;
import com.au.transactions.model.dto.TransactionReponseDTO;
import com.au.transactions.model.dto.TransactionRequestDTO;
import com.au.transactions.model.entity.Customer;
import com.au.transactions.model.entity.Product;
import com.au.transactions.model.entity.Transaction;
import com.au.transactions.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.au.transactions.common.Constants.*;
import static java.lang.String.format;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    CustomerRepositoryDAO customerRepositoryDAO;
    ProductRepositoryDAO productRepositoryDAO;
    TransactionRepositoryDAO transactionRepositoryDAO;

    @Override
    public ResponseEntity record(TransactionRequestDTO transactionRequestDTO) {

        Customer customer = customerRepositoryDAO.findByCustomerId(transactionRequestDTO.getCustomerId());
        if(null == customer) {
            return recordNotFoundException(transactionRequestDTO.getCustomerId(), "customer");
        }
        Product product = productRepositoryDAO.findByProductCode(transactionRequestDTO.getProductCode());
        if(null == product) {
            return recordNotFoundException(transactionRequestDTO.getProductCode(), "product");
        }
        // Saving the transaction
        saveTransaction(transactionRequestDTO, customer, product);

        TransactionReponseDTO transactionReponseDTO = TransactionReponseDTO.builder().build();
        transactionReponseDTO.setCode(SUCCESS_CODE);
        transactionReponseDTO.setMessage(SUCCESS_MESSAGE);
        return new ResponseEntity(transactionReponseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCostByCustomer(Integer customerId) {

        List<Transaction> transactionList = transactionRepositoryDAO.findByCustomerId(customerId);
        if(null != transactionList && !transactionList.isEmpty()) {
            Double totalCost = transactionList.stream().map(transaction -> transaction.getTotalCost())
                    .collect(Collectors.summingDouble(Double::doubleValue));
            EntityCostResponseDTO entityCostResponseDTO = EntityCostResponseDTO.builder().build();
            entityCostResponseDTO.setCode(SUCCESS_CODE);
            entityCostResponseDTO.setMessage(SUCCESS_COST_BY_CUSTOMER);
            entityCostResponseDTO.setValue(totalCost);
            return new ResponseEntity(entityCostResponseDTO, HttpStatus.OK);
        } else {
            return recordNotFoundException(customerId, "customer");
        }
    }

    @Override
    public ResponseEntity<?> getCostByProduct(String productCode) {
        Product product = productRepositoryDAO.findByProductCode(productCode);
        if(null != product && product.getId() > 0) {
            List<Transaction> transactionList = transactionRepositoryDAO.findByProductId(product.getId());
            if(null != transactionList && !transactionList.isEmpty()) {
                Double totalCost = transactionList.stream().map(transaction -> transaction.getTotalCost())
                        .collect(Collectors.summingDouble(Double::doubleValue));
                EntityCostResponseDTO entityCostResponseDTO = EntityCostResponseDTO.builder().build();
                entityCostResponseDTO.setCode(SUCCESS_CODE);
                entityCostResponseDTO.setMessage(SUCCESS_COST_BY_PRODUCT);
                entityCostResponseDTO.setValue(totalCost);
                return new ResponseEntity(entityCostResponseDTO, HttpStatus.OK);
            } else {
                return recordNotFoundException(product.getId(), "customer");
            }
        }
        return recordNotFoundException(productCode, "product");
    }

    @Override
    public ResponseEntity<?> getByCustomerAndLocation(Integer customerId, String location) {
        List<Transaction> transactionList = transactionRepositoryDAO.findByCustomerIdAndLocationAndStatus(customerId, location, "Active");
        if(null != transactionList && !transactionList.isEmpty()) {
            TotalTransactionsResponseDTO totalTransactionsResponseDTO = TotalTransactionsResponseDTO.builder().build();
            totalTransactionsResponseDTO.setCode(SUCCESS_CODE);
            totalTransactionsResponseDTO.setMessage(SUCCESS_COST_BY_CUSTOMERANDLOCATION);
            totalTransactionsResponseDTO.setValue(transactionList.size());
            return new ResponseEntity(totalTransactionsResponseDTO, HttpStatus.OK);
        } else {
                return recordNotFoundException(customerId, "customer");
        }
    }

    private void saveTransaction(TransactionRequestDTO transactionRequestDTO, Customer customer, Product product) {
        Transaction transaction = Transaction.builder().build();
        transaction.setCustomer(customer);
        transaction.setCost(product.getCost());
        transaction.setProduct(product);
        transaction.setQuantity(transactionRequestDTO.getQuantity());
        transaction.setLocation(customer.getLocation());
        transaction.setStatus(product.getStatus());
        transaction.setTotalCost(product.getCost() * transactionRequestDTO.getQuantity());
        transactionRepositoryDAO.save(transaction);
    }

    private ResponseEntity recordNotFoundException(final Object request, final String entity) {
        TransactionErrorResponse error = TransactionErrorResponse.builder()
                .code(RECORD_NOT_FOUND_EXCEPTION_CODE)
                .type(RECORD_NOT_FOUND_EXCEPTION_TYPE)
                .message(format(RECORD_NOT_FOUND_ENTITY_EXCEPTION_MESSAGE,"["+entity+"]", request))
                .build();
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
