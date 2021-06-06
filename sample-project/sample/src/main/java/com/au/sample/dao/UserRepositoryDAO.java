package com.au.sample.dao;

import com.au.sample.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryDAO extends JpaRepository<User,Integer> {

}
