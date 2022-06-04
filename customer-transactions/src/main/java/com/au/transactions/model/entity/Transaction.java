package com.au.transactions.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="customerId", nullable=false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="productId", nullable=false)
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status;

    @Column(name = "totalCost")
    private Double totalCost;

    @CreationTimestamp
    private LocalDateTime createDateTime;

}
