package com.au.sample.dao;

import com.au.sample.model.entity.Account;
import com.au.sample.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositoryDAO extends JpaRepository<Account,Integer> {

    List<Account> findByUser(User user, Pageable pageable);

}
