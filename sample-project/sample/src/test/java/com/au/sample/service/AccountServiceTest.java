package com.au.sample.service;

import com.au.sample.dao.AccountRepositoryDAO;
import com.au.sample.dao.TransactionRepositoryDAO;
import com.au.sample.model.dto.AccountDTO;
import com.au.sample.model.dto.TransactionDTO;
import com.au.sample.model.entity.Account;
import com.au.sample.model.entity.Transaction;
import com.au.sample.service.impl.AccountServiceImpl;
import com.au.sample.utils.TestHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class AccountServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private AccountRepositoryDAO accountRepositoryDAO;

    @Mock
    private TransactionRepositoryDAO transactionRepositoryDAO;

    @Mock
    private ModelMapper modelMapper;
    @Before
    public void setUp() throws Exception {
        accountService = new AccountServiceImpl(accountRepositoryDAO, transactionRepositoryDAO, modelMapper);
    }

    @Test
    public void getAccountsShouldReturnSuccessResponse() {

        List<Account> findByUserResponse = TestHelper.getAccountListResponse();
        AccountDTO mappedAccountDTOResponse = TestHelper.getAccountDTOResponse();
        when(accountRepositoryDAO.findByUser(any(),any()))
                .thenReturn(findByUserResponse);
        when(modelMapper.map(any(),any()))
                .thenReturn(mappedAccountDTOResponse);
        List<AccountDTO> accountDTOList = accountService.getAccountsByUser(1,1,3);
        List<AccountDTO> expectedResponse = TestHelper.getAccountDTOSingleListResponse();
        assertEquals(expectedResponse, accountDTOList);
    }

    @Test
    public void getAccountsShouldReturnEmptyResponse() {

        List<Account> findByUserResponse = TestHelper.getEmptyAccountListResponse();
        when(accountRepositoryDAO.findByUser(any(),any()))
                .thenReturn(findByUserResponse);
        List<AccountDTO> accountDTOList = accountService.getAccountsByUser(1,1,3);
        List<AccountDTO> expectedResponse = TestHelper.getEmptyAccountDTOListResponse();
        assertEquals(expectedResponse, accountDTOList);
    }

    @Test
    public void getTransactionsShouldReturnSuccessResponse() {

        List<Transaction> findByAccountResponse = TestHelper.getTransactionListResponse();
        when(transactionRepositoryDAO.findByAccount(any(),any()))
                .thenReturn(findByAccountResponse);
        TransactionDTO transactionDTO = accountService.getTransactionByAccount(1,1,3);
        TransactionDTO expectedResponse = TestHelper.getTransactionDTOListResponse();
        assertEquals(expectedResponse, transactionDTO);
    }

    @Test
    public void getTransactionsShouldReturnNullResponse() {

        List<Transaction> findByAccountResponse = TestHelper.getEmptyTransactionListResponse();
        when(transactionRepositoryDAO.findByAccount(any(),any()))
                .thenReturn(findByAccountResponse);
        TransactionDTO transactionDTO = accountService.getTransactionByAccount(1,1,3);
        assertEquals(null, transactionDTO);
    }
}
