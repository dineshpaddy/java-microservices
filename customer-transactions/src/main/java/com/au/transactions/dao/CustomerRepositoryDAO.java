package com.au.transactions.dao;

import com.au.transactions.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoryDAO extends JpaRepository<Customer,Integer> {

    Customer findByCustomerId(Integer customerId);

}
