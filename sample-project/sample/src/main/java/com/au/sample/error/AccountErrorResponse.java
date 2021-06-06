package com.au.sample.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountErrorResponse {

    private String message;
    private String type;
    private String code;
}
