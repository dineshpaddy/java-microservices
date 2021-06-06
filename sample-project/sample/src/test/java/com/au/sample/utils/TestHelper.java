package com.au.sample.utils;

import com.au.sample.model.dto.AccountDTO;
import com.au.sample.model.dto.TransactionDTO;
import com.au.sample.model.entity.Account;
import com.au.sample.model.entity.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.io.FileUtils.readFileToString;

@Slf4j
public class TestHelper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T loadFromJson(final String filename, final Class<T> objectClass) {
        try {
            String json = readFile(filename);
            return mapper.readValue(json, objectClass);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static String readFile(final String filePath) throws IOException {
        final File sampleMessage = new ClassPathResource(filePath).getFile();
        return readFileToString(sampleMessage, Charset.defaultCharset());
    }

    public static List<AccountDTO> getAccountDTOListResponse() {
        return Arrays.asList(loadFromJson("json/getAccountsByUserDTOResponse.json",
                AccountDTO[].class));
    }

    public static TransactionDTO getTransactionDTOListResponse() {
        return loadFromJson("json/getTransactionDTOListResponse.json",
                TransactionDTO.class);
    }

    public static List<Account> getAccountListResponse() {
        return Arrays.asList(loadFromJson("json/getAccountsByUserEntityResponse.json",
                Account[].class));
    }

    public static List<Transaction> getTransactionListResponse() {
        return Arrays.asList(loadFromJson("json/getTransactionsByAccountEntityResponse.json",
                Transaction[].class));
    }

    public static TransactionDTO getTransactionResponse() {
        return loadFromJson("json/getTransactionsByAccountResponse.json",
                TransactionDTO.class);
    }

    public static AccountDTO getAccountDTOResponse() {
        return loadFromJson("json/getAccountDTOResponse.json",
                AccountDTO.class);
    }

    public static List<AccountDTO> getAccountDTOSingleListResponse() {
        return Arrays.asList(loadFromJson("json/getAccountDTOListResponse.json",
                AccountDTO[].class));
    }

    public static List<Account> getEmptyAccountListResponse() {
        return Arrays.asList(loadFromJson("json/getEmptyListResponse.json",
                Account[].class));
    }

    public static List<Transaction> getEmptyTransactionListResponse() {
        return Arrays.asList(loadFromJson("json/getEmptyListResponse.json",
                Transaction[].class));
    }

    public static List<AccountDTO> getEmptyAccountDTOListResponse() {
        return Arrays.asList(loadFromJson("json/getEmptyListResponse.json",
                AccountDTO[].class));
    }
}
