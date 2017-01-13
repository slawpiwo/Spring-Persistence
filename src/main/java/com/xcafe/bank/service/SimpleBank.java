package com.xcafe.bank.service;

import com.xcafe.bank.entity.Account;
import com.xcafe.bank.service.repository.AccountRepository;

public class SimpleBank implements Bank {

    private AccountNumberGenerator accountNumberGenerator;
    private AccountRepository accountRepository;

    public SimpleBank(AccountNumberGenerator accountNumberGenerator, AccountRepository accountsRepository) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.accountRepository = accountsRepository;
    }

    @Override
    public Account createAccount() {
        Account account = new Account(accountNumberGenerator.getNext());
        return accountRepository.save(account);
    }

    @Override
    public void makeDeposit(long amount, String accountNumber) {
        Account account = accountRepository.getByNumber(accountNumber);
        account.setBalance(account.getBalance() + amount);
        accountRepository.update(account);
    }

    @Override
    public Account getAccountDetails(String accountNumber) {
        return accountRepository.getByNumber(accountNumber);
    }


}
