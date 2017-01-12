package com.xcafe.bank.service.repository;

import com.xcafe.bank.entity.Account;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;

/*
 * see https://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html#jdbc-core
 */
public class MySqlAccountRepository implements AccountRepository {

    private static final String INSERT = "insert into account values (null, :number, :balance)";
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
}
