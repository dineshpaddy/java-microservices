package com.au.sample.service;

import com.au.sample.exceptions.InvalidPageSizeException;
import com.au.sample.exceptions.InvalidTransactionIdException;
import com.au.sample.service.impl.ValidationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class ValidationServiceTest {

    @Mock
    private ValidationService validationService;

    @Before
    public void setUp() throws Exception {
        validationService = ValidationServiceImpl.builder().build();
    }

    @Test(expected = InvalidPageSizeException.class)
    public void shouldThrowInvalidPageException() throws InvalidTransactionIdException, InvalidPageSizeException {

        validationService.validateHeaderAndPageSize(7, "85f46f54-e05b-430f-8caa-9034a0a641dd");
    }

    @Test(expected = InvalidTransactionIdException.class)
    public void shouldThrowInvalidTransactionId() throws InvalidTransactionIdException, InvalidPageSizeException {

        validationService.validateHeaderAndPageSize(3, "85f46f54-e05b-9034a0a641dd");
    }

    @Test
    public void shouldNotThrowAnyException() throws InvalidTransactionIdException, InvalidPageSizeException {

        validationService.validateHeaderAndPageSize(3, "85f46f54-e05b-430f-8caa-9034a0a641dd");
    }
}
