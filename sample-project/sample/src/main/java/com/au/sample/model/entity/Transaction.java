package com.au.sample.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Data
@Table(name="Transaction")
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="accountId", nullable=false)
    private Account account;

    @Column(name = "valueDate")
    private String valueDate;

    @Column(name = "amount")
    private Double amount;

    private String type;

    private String narrative;

}
