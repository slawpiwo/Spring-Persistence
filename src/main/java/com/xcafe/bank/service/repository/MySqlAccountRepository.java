package com.xcafe.bank.service.repository;

import com.xcafe.bank.entity.Account;
import com.xcafe.bank.exception.AccountNotFoundException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;

/*
 * see https://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html#jdbc-core
 */
public class MySqlAccountRepository implements AccountRepository {

    private static final String INSERT = "insert into account values (null, :number, :balance)";
    private static final String UPDATE_BALANCE = "update account set balance = :balance where id = :id";
    private static final String SELECT_BY_NUMBER = "select * from account where number = :number";
    private NamedParameterJdbcTemplate jdbcTemplate;


    public MySqlAccountRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Account save(Account account) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT, new BeanPropertySqlParameterSource(account), keyHolder);
        account.setId(keyHolder.getKey().longValue());
        return account;
    }

    @Override
    public void update(Account account) {
        if (0 == jdbcTemplate.update(UPDATE_BALANCE, new BeanPropertySqlParameterSource(account))) {
            throw new AccountNotFoundException(String.format("Account not found: %s", account.getNumber()));
        }
    }

    @Override
    public Account getByNumber(String accountNumber) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("number", accountNumber);
        Account account = jdbcTemplate.query(SELECT_BY_NUMBER, parameterSource, new AccountExtractor());
        if (account == null) {
            throw new AccountNotFoundException(String.format("Account not found: %s", accountNumber));
        }
        return account;
    }
}
