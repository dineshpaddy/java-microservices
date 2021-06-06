package com.au.sample.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
public class AccountDTO {

    @JsonIgnore
    private Integer id;

    private String number;

    private String name;

    private String type;

    private String balanceDate;

    private String currency;

    private Double openingAvailableBalance;

}
