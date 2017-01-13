package com.xcafe.bank.config;

import com.xcafe.bank.service.repository.AccountRepository;
import com.xcafe.bank.service.repository.MySqlAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "jdbc.properties")
@EnableTransactionManagement
public class Persistence {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        // Simple implementation of the standard JDBC DataSource interface, configuring the plain old JDBC DriverManager
        // via bean properties, and returning a new Connection from every getConnection call.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.login"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClass"));
        return dataSource;
    }

    @Bean
    public AccountRepository accountRepository(DataSource dataSource) {
        return new MySqlAccountRepository(dataSource);
    }
}
