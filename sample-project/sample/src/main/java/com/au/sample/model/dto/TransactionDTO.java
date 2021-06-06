package com.au.sample.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransactionDTO {

    private String accountNumber;

    private String accountName;

    private List<TransactionDetailsDTO> transactionDetails;
}
