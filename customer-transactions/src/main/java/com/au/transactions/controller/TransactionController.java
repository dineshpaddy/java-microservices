package com.au.transactions.controller;

import com.au.transactions.exceptions.InvalidRequestException;
import com.au.transactions.exceptions.MandatoryFieldException;
import com.au.transactions.model.dto.TransactionRequestDTO;
import com.au.transactions.service.TransactionService;
import com.au.transactions.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.au.transactions.common.Constants.*;
import static java.lang.String.format;

@RestController
@RequestMapping("/v1/transactions/")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;
    private final ValidationService validationService;

    public TransactionController(TransactionService transactionService, ValidationService validationService) {
        this.transactionService = transactionService;
        this.validationService = validationService;
    }

    @PostMapping("record")
    public ResponseEntity record(@RequestBody TransactionRequestDTO transactionRequestDTO,
                                 @RequestHeader final HttpHeaders httpHeaders) throws MandatoryFieldException, InvalidRequestException {

        log.info("HttpHeaders : {}", httpHeaders);
        validationService.validateRequestBody(transactionRequestDTO);
        return transactionService.record(transactionRequestDTO);

    }

    @GetMapping(path = "customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<?> getCostByCustomer(@PathVariable(name = "customerId") final Integer customerId,
                                               @RequestHeader final HttpHeaders httpHeaders) throws MandatoryFieldException {
        log.info("HttpHeaders : {}", httpHeaders);
        if(customerId <= 0) {
            throw new MandatoryFieldException(format(MANDATORY_FIELD_EXCEPTION_MESSAGE,"customerId"));
        }
        return transactionService.getCostByCustomer(customerId);
    }


    @GetMapping(path = "product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<?> getCostByProduct(@PathVariable(name = "productId") final String productId,
                                                 @RequestHeader final HttpHeaders httpHeaders) throws MandatoryFieldException {
        log.info("HttpHeaders : {}", httpHeaders);
        if(null == productId  || productId.isEmpty()) {
            throw new MandatoryFieldException(format(MANDATORY_FIELD_EXCEPTION_MESSAGE,"productId"));
        }
        return transactionService.getCostByProduct(productId);
    }

    @GetMapping(path = "customer/{customerId}/location/{location}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<?> getByCustomerAndLocation(@PathVariable(name = "customerId") final Integer customerId,
                                                 @PathVariable(name = "location") final String location,
                                                 @RequestHeader final HttpHeaders httpHeaders) throws MandatoryFieldException {
        if(customerId <= 0) {
            throw new MandatoryFieldException(format(MANDATORY_FIELD_EXCEPTION_MESSAGE,"customerId"));
        } else if(null == location  || location.isEmpty()) {
            throw new MandatoryFieldException(format(MANDATORY_FIELD_EXCEPTION_MESSAGE,"location"));
        }
        log.info("HttpHeaders : {}", httpHeaders);
        return transactionService.getByCustomerAndLocation(customerId, location);
    }



}
