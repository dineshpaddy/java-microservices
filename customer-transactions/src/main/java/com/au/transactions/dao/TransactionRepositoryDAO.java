package com.au.transactions.dao;

import com.au.transactions.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepositoryDAO extends JpaRepository<Transaction,Integer> {

    List<Transaction> findByCustomerId(Integer productCode);

    List<Transaction> findByProductId(Integer productCode);

    List<Transaction> findByCustomerIdAndLocationAndStatus(Integer customerId, String location, String status);

}
