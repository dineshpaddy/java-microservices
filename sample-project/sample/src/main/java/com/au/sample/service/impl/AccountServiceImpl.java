package com.au.sample.service.impl;

import com.au.sample.dao.AccountRepositoryDAO;
import com.au.sample.dao.TransactionRepositoryDAO;
import com.au.sample.model.dto.AccountDTO;
import com.au.sample.model.dto.TransactionDTO;
import com.au.sample.model.dto.TransactionDetailsDTO;
import com.au.sample.model.entity.Account;
import com.au.sample.model.entity.Transaction;
import com.au.sample.model.entity.User;
import com.au.sample.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static com.au.sample.common.Constants.DEFAULT_PAGE_NUMBER;
import static com.au.sample.common.Constants.DEFAULT_PAGE_SIZE;
import static com.au.sample.common.SampleUtil.convertDate;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepositoryDAO accountRepositoryDAO;

    @Autowired
    private TransactionRepositoryDAO transactionRepositoryDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AccountDTO> getAccountsByUser(Integer userId, Integer page, Integer size) {

        User user= User.builder().build();
        user.setId(userId);
        Integer pageNumber = (page == null || page ==0) ? DEFAULT_PAGE_NUMBER: page;
        Integer pageSize = (size == null || size ==0) ? DEFAULT_PAGE_SIZE: size;
        Pageable pageWithElements = PageRequest.of(pageNumber-1, pageSize, Sort.Direction.ASC,"id");
        List<Account> accountList = accountRepositoryDAO.findByUser(user,pageWithElements);
        List<AccountDTO> accountDTOList = accountList.parallelStream()
                .map(this::convertAccountToDto)
                .collect(Collectors.toList());
        accountList.parallelStream().forEach(account -> log.info("Account information for user {} {} is : {}",
                account.getUser().getFirstName(), account.getUser().getLastName(), account));
        return accountDTOList;
    }

    @Override
    public TransactionDTO getTransactionByAccount(Integer accountId, Integer page, Integer size) {
        Account account = Account.builder().build();
        account.setId(accountId);
        Integer pageNumber = (page == null || page ==0) ? DEFAULT_PAGE_NUMBER: page;
        Integer pageSize = (size == null || size ==0) ? DEFAULT_PAGE_SIZE: size;
        Pageable pageWithElements = PageRequest.of(pageNumber-1, pageSize, Sort.Direction.ASC,"id");
        List<Transaction> transactionList = transactionRepositoryDAO.findByAccount(account,pageWithElements);
        TransactionDTO transactionDTO = null;
        if (transactionList.size() != 0) {
            transactionDTO = TransactionDTO.builder().build();
            transactionDTO.setAccountNumber(transactionList.stream().findFirst().get().getAccount().getNumber());
            transactionDTO.setAccountName(transactionList.stream().findFirst().get().getAccount().getName());
            List<TransactionDetailsDTO> transactionDetailsDTOList = transactionList.stream()
                    .map(this::convertTransactionDetailsToDto)
                    .collect(Collectors.toList());
            transactionDTO.setTransactionDetails(transactionDetailsDTOList);
            transactionList.stream().forEach(transaction -> log.info("Transaction details for the account {} is : {}",
                    transaction.getAccount().getNumber(), transaction));
        }
        return transactionDTO;
    }

    /**
     * Map the Account entity to DTO
     * @param account
     * @return
     */
    private AccountDTO convertAccountToDto(Account account)  {
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
        accountDTO.setCurrency(account.getCurrency().getName());
        try {
            accountDTO.setBalanceDate(convertDate(account.getBalanceDate()));
        } catch (ParseException e) {
            log.error(e.getMessage(),e);
        }
        return accountDTO;
    }

    /**
     * Convert the transaction entity to the transactionDetailsDTO object
     * @param transaction
     * @return
     */
    private TransactionDetailsDTO convertTransactionDetailsToDto(Transaction transaction)  {
        TransactionDetailsDTO transactionDetailsDTO = TransactionDetailsDTO.builder().build();
        transactionDetailsDTO.setAmount(transaction.getAmount());
        transactionDetailsDTO.setCurrency(transaction.getAccount().getCurrency().getName());
        transactionDetailsDTO.setType(transaction.getType());
        transactionDetailsDTO.setNarrative(transaction.getNarrative());
        try {
            transactionDetailsDTO.setValueDate(convertDate(transaction.getValueDate()));
        } catch (ParseException e) {
            log.error(e.getMessage(),e);
        }
        return transactionDetailsDTO;
    }
}
