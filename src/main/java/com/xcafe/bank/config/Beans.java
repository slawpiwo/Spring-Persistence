package com.xcafe.bank.config;

import com.xcafe.bank.service.AccountNumberGenerator;
import com.xcafe.bank.service.Bank;
import com.xcafe.bank.service.SimpleAccountNumberGenerator;
import com.xcafe.bank.service.SimpleBank;
import com.xcafe.bank.service.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import(Persistence.class)
public class Beans {

    @Bean
    public AccountNumberGenerator accountNumberGenerator(DataSource dataSource) {
        return new SimpleAccountNumberGenerator(dataSource);
    }

    @Bean
    public Bank bank(AccountNumberGenerator accountNumberGenerator, AccountRepository accountRepository) {
        return new SimpleBank(accountNumberGenerator, accountRepository);
    }
}
