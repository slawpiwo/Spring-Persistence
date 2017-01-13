package com.xcafe.bank.service;

import com.xcafe.bank.entity.Account;

public interface Bank {

    Account createAccount();

    void makeDeposit(long amount, String accountNumber);

    Account getAccountDetails(String accountNumber);
}
