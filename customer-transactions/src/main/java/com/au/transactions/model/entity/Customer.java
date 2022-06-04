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
@Table(name="Customer")
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "customerId")
    private Integer customerId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "location")
    private String location;
}
