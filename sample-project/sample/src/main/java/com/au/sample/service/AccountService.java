package com.au.sample.service;

import com.au.sample.model.dto.AccountDTO;
import com.au.sample.model.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for all the accounts and its transactions
 */
@Service
public interface AccountService {

    /**
     * Get all the accounts held by a given user
     * @param userId
     * @param page
     * @param size
     * @return
     */
    List<AccountDTO> getAccountsByUser(Integer userId, Integer page, Integer size);

    /**
     * Get all transactions for a given account number
     * @param accountId
     * @param page
     * @param size
     * @return
     */
    TransactionDTO getTransactionByAccount(Integer accountId, Integer page, Integer size);
}
