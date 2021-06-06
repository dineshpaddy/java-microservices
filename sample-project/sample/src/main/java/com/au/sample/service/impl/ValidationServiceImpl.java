package com.au.sample.service.impl;

import com.au.sample.exceptions.InvalidPageSizeException;
import com.au.sample.exceptions.InvalidTransactionIdException;
import com.au.sample.service.ValidationService;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.au.sample.common.Constants.DEFAULT_PAGE_SIZE;
import static com.au.sample.common.Constants.INVALID_PAGE_SIZE_EXCEPTION_MESSAGE;
import static com.au.sample.common.Constants.INVALID_TRANSACTIONID_EXCEPTION_MESSAGE;
import static com.au.sample.common.Constants.TRANSACTIONID_NOT_FOUND_EXCEPTION_MESSAGE;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.regex.Pattern.compile;

@Service
//@Builder
public class ValidationServiceImpl implements ValidationService {


    private static final Pattern TRANSACTION_ID_PATTERN =
            compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
    @Override
    public void validateHeaderAndPageSize(final Integer size, final String transactionID)
            throws InvalidPageSizeException, InvalidTransactionIdException {

        if (!isNull(size) && size.compareTo(DEFAULT_PAGE_SIZE) > 0) {
            throw new InvalidPageSizeException(format(INVALID_PAGE_SIZE_EXCEPTION_MESSAGE,"size",DEFAULT_PAGE_SIZE));
        }

        if (null != transactionID) {
            final Matcher matcher = TRANSACTION_ID_PATTERN.matcher(transactionID);
            if (!matcher.find()) {
                throw new InvalidTransactionIdException(format(INVALID_TRANSACTIONID_EXCEPTION_MESSAGE,transactionID));
            }
        } else {
            throw new InvalidTransactionIdException(TRANSACTIONID_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

}
