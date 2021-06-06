package com.au.sample.controller;

import com.au.sample.exceptions.InvalidPageSizeException;
import com.au.sample.exceptions.InvalidTransactionIdException;
import com.au.sample.service.AccountService;
import com.au.sample.service.ValidationService;
import com.au.sample.service.impl.ValidationServiceImpl;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Mock
    private AccountController accountController;
    @Mock
    private AccountService accountService;
    @Mock
    private ValidationService validationService;

    @Before
    public void setUp() throws Exception {
        accountController = new AccountController(accountService, validationService);

    }

    @Test
    public void getAccountsShouldThrowNotFoundException() throws InvalidTransactionIdException, InvalidPageSizeException {

        ResponseEntity<?> responseEntity =
                accountController.getAccounts(1,1,2,"85f46f54-e05b-430f-8caa-9034a0a641dd", new HttpHeaders());
        verify(validationService, times(1))
                .validateHeaderAndPageSize(any(),any());
        verify(accountService, times(1))
                .getAccountsByUser(any(), any(), any());
        assertThat(responseEntity.getStatusCode(), Is.is(HttpStatus.NOT_FOUND));
    }

   /* @Test
    public void getAccountsShouldGiveSuccessResponse() throws InvalidTransactionIdException, InvalidPageSizeException {

        ResponseEntity<?> responseEntity =
                accountController.getAccounts(1,1,2,"85f46f54-e05b-430f-8caa-9034a0a641dd", new HttpHeaders());
        verify(validationService, times(1))
                .validateHeaderAndPageSize(any(),any());
        verify(accountService, times(1))
                .getAccountsByUser(any(), any(), any());
        when(accountService.getAccountsByUser(any(), any(), any()))
                .thenReturn(response);
        assertThat(responseEntity.getStatusCode(), Is.is(HttpStatus.NOT_FOUND));
    }*/
}
