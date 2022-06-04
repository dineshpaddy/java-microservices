package com.au.transactions.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionErrorResponse {

    private String message;
    private String type;
    private String code;
}
