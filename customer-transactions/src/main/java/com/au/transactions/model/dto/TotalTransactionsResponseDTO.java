package com.au.transactions.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TotalTransactionsResponseDTO {

    private String message;
    private String code;
    private Integer value;
}
