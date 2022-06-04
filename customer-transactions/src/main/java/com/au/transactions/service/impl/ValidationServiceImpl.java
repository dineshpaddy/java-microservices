package com.au.transactions.service.impl;

import com.au.transactions.dao.ProductRepositoryDAO;
import com.au.transactions.exceptions.InvalidRequestException;
import com.au.transactions.exceptions.MandatoryFieldException;
import com.au.transactions.model.dto.TransactionRequestDTO;
import com.au.transactions.model.entity.Product;
import com.au.transactions.service.ValidationService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.au.transactions.common.Constants.*;
import static java.lang.String.format;

@Service
@Builder
@Slf4j
public class ValidationServiceImpl implements ValidationService {

    ProductRepositoryDAO productRepositoryDAO;
    @Override
    public void validateRequestBody(TransactionRequestDTO transactionRequestDTO) throws MandatoryFieldException, InvalidRequestException {
        validateMandatoryFields(transactionRequestDTO);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime transactionDateTime = LocalDateTime.parse(transactionRequestDTO.getTransactionTime(), formatter);

        if(transactionDateTime.isBefore(currentTime)) {
            throw new InvalidRequestException(PAST_TRANSACTION_DATE_ERROR_MESSAGE);
        }
        Product product = productRepositoryDAO.findByProductCode(transactionRequestDTO.getProductCode());
        if(null != product) {
            Double totalTransactionCost = product.getCost() * transactionRequestDTO.getQuantity();
            if(totalTransactionCost > 5000) {
                throw new InvalidRequestException(TRANSACTION_TOTAL_COST_ERROR_MESSAGE);
            }
            if(!product.getStatus().equalsIgnoreCase("ACTIVE")) {
                throw new InvalidRequestException(INACTIVE_PRODUCT_ERROR_MESSAGE);
            }
        }
    }

    private void validateMandatoryFields(TransactionRequestDTO transactionRequestDTO) throws MandatoryFieldException {
        if(null == transactionRequestDTO.getTransactionTime() || transactionRequestDTO.getTransactionTime().isEmpty()) {
            throw new MandatoryFieldException(format(MANDATORY_FIELD_EXCEPTION_MESSAGE,"transactionTime"));
        }
        if(null == transactionRequestDTO.getProductCode() || transactionRequestDTO.getProductCode().isEmpty()) {
            throw new MandatoryFieldException(format(MANDATORY_FIELD_EXCEPTION_MESSAGE,"productCode"));
        }
        if(null == transactionRequestDTO.getCustomerId() && transactionRequestDTO.getCustomerId() <= 0) {
            throw new MandatoryFieldException(format(MANDATORY_FIELD_EXCEPTION_MESSAGE,"customerId"));
        }
        if(null != transactionRequestDTO.getQuantity() && transactionRequestDTO.getQuantity() <= 0) {
            throw new MandatoryFieldException(format(MANDATORY_FIELD_EXCEPTION_MESSAGE,"quantity"));
        }
    }
}
