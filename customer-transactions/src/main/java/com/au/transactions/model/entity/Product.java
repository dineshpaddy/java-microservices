package com.au.transactions.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Product")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "productCode")
    private String productCode;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "status")
    private String status;
}
