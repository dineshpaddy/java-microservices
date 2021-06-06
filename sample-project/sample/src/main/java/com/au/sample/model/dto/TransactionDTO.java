package com.au.sample.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private String accountNumber;

    private String accountName;

    private List<TransactionDetailsDTO> transactionDetails;
}
