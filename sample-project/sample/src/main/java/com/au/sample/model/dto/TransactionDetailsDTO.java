package com.au.sample.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailsDTO {

    private String valueDate;

    private String currency;

    private Double amount;

    private String type;

    private String narrative;
}
