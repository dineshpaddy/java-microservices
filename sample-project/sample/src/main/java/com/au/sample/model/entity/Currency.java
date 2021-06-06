package com.au.sample.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="Currency")
public class Currency {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String country;

    private String description;
}
