package com.xcafe.bank.service;

import com.xcafe.bank.entity.Account;
import com.xcafe.bank.service.repository.AccountRepository;

public class SimpleBank implements Bank {

    private AccountNumberGenerator accountNumberGenerator;
    private AccountRepository accountsRepository;

    public SimpleBank(AccountNumberGenerator accountNumberGenerator, AccountRepository accountsRepository) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.accountsRepository = accountsRepository;
    }

    @Override
    public Account createAccount() {
        Account account = new Account(accountNumberGenerator.getNext());
        return accountsRepository.save(account);
    }
}
