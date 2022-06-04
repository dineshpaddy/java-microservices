package com.au.transactions.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntityCostResponseDTO {

    private String message;
    private String code;
    private Double value;
}
