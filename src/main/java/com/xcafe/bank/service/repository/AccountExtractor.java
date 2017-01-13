package com.xcafe.bank.service.repository;

import com.xcafe.bank.entity.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountExtractor implements ResultSetExtractor<Account> {

    @Override
    public Account extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Account account = null;

        if (resultSet.next()) {
            account = new Account(resultSet.getString("number"));
            account.setId(resultSet.getLong("id"));
            account.setBalance(resultSet.getLong("balance"));
        }

        return account;
    }
}
