package com.au.sample.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Account")
public class Account {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "accountNumber")
    private String number;

    private String name;

    private String type;

    @Column(name = "balanceDate")
    private String balanceDate;

    @ManyToOne
    @JoinColumn(name="currencyId", nullable=false)
    private Currency currency;

    @Column(name = "openingAvailableBalance")
    private Double openingAvailableBalance;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private User user;
}
