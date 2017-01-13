package com.xcafe.bank.service;


import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleAccountNumberGenerator implements AccountNumberGenerator {

    private static final String SELECT_MAX_ACCOUNT_NUMBER = "select max(number) from account";
    private JdbcTemplate jdbcTemplate;


    public SimpleAccountNumberGenerator(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String getNext() {
        AtomicLong sequencer = new AtomicLong(getLastAccountNumber());
        return String.format("%026d", sequencer.incrementAndGet());
    }

    private Long getLastAccountNumber() {
        Long initialAccountNumber = 0L;

        String lastAccountNumber = jdbcTemplate.queryForObject(SELECT_MAX_ACCOUNT_NUMBER, String.class);
        if (lastAccountNumber != null) {
            initialAccountNumber = Long.valueOf(lastAccountNumber);
        }

        return initialAccountNumber;
    }
}
