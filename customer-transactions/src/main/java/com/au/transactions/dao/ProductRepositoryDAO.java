package com.au.transactions.dao;

import com.au.transactions.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryDAO extends JpaRepository<Product,Integer> {

    Product findByProductCode(String productCode);

}
