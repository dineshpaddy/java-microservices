package com.au.transactions.model.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TransactionRequestDTO {

    private String transactionTime;
    private Integer customerId;
    private Integer quantity;
    private String productCode;
}
