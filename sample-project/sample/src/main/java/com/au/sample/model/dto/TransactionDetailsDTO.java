package com.au.sample.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDetailsDTO {

    private String valueDate;

    private String currency;

    private Double amount;

    private String type;

    private String narrative;
}
