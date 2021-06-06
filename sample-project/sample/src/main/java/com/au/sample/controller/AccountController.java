package com.au.sample.controller;

import com.au.sample.error.AccountErrorResponse;
import com.au.sample.exceptions.InvalidPageSizeException;
import com.au.sample.exceptions.InvalidTransactionIdException;
import com.au.sample.model.dto.AccountDTO;
import com.au.sample.model.dto.TransactionDTO;
import com.au.sample.service.AccountService;
import com.au.sample.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

import static com.au.sample.common.Constants.RECORD_NOT_FOUND_EXCEPTION_CODE;
import static com.au.sample.common.Constants.RECORD_NOT_FOUND_EXCEPTION_TYPE;
import static com.au.sample.common.Constants.RECORD_NOT_FOUND_EXCEPTION_MESSAGE;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/v1/account/")
@Slf4j
public class AccountController {

    public AccountController(final AccountService accountService,
                                   final ValidationService validationService) {
        this.accountService = accountService;
        this.validationService = validationService;
    }

    private final AccountService accountService;

    private final ValidationService validationService;

    @GetMapping(path = "user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<?> getAccounts(@PathVariable(name = "userId") final Integer userId,
                                                @RequestParam(name = "page",required = false)
                                                final Integer page,
                                                @RequestParam(name = "size",required = false)
                                                final Integer size,
                                                @RequestHeader(name = "transactionID", required = false)
                                                final String transactionId,
                                                @RequestHeader final HttpHeaders httpHeaders)
            throws InvalidPageSizeException, InvalidTransactionIdException {
        log.info("HttpHeaders : {}", httpHeaders);
        validationService.validateHeaderAndPageSize(size, transactionId);
        List<AccountDTO> accountDTOList  = accountService.getAccountsByUser(userId,page,size);
        return accountDTOList.size() == 0 ? recordNotFoundException(userId) :
                new ResponseEntity(accountDTOList, OK);
    }

    @GetMapping(path = "/{accountId}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<?> getTransactions(@PathVariable(name = "accountId") final Integer accountId,
                                                    @RequestParam(name = "page",required = false) final Integer page,
                                                    @RequestParam(name = "size",required = false) final Integer size,
                                                    @RequestHeader(name = "transactionID", required = false)
                                                    final String transactionId,
                                                    @RequestHeader final HttpHeaders httpHeaders)
            throws InvalidPageSizeException, InvalidTransactionIdException {
        log.info("HttpHeaders : {}", httpHeaders);
        validationService.validateHeaderAndPageSize(size, transactionId);
        TransactionDTO transactionDTO  = accountService.getTransactionByAccount(accountId,page,size);
        return transactionDTO == null ? recordNotFoundException(accountId) :
                new ResponseEntity(transactionDTO, OK);
    }

    private ResponseEntity recordNotFoundException(final Integer id) {
        AccountErrorResponse error = AccountErrorResponse.builder()
                .code(RECORD_NOT_FOUND_EXCEPTION_CODE)
                .type(RECORD_NOT_FOUND_EXCEPTION_TYPE)
                .message(format(RECORD_NOT_FOUND_EXCEPTION_MESSAGE,id))
                .build();
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }


}
