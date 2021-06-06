package com.au.sample.dao;

import com.au.sample.model.entity.Account;
import com.au.sample.model.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepositoryDAO extends JpaRepository<Transaction,Integer> {

    List<Transaction> findByAccount(Account account, Pageable pageable);

}
