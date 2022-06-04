package com.au.transactions.service;

import com.au.transactions.model.dto.TransactionRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    ResponseEntity record(TransactionRequestDTO transactionRequestDTO);

    ResponseEntity<?> getCostByCustomer(Integer customerId);

    ResponseEntity<?> getCostByProduct(String productId);

    ResponseEntity<?> getByCustomerAndLocation(Integer customerId, String location);
}
