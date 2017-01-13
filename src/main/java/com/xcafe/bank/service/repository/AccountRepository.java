package com.xcafe.bank.service.repository;

import com.xcafe.bank.entity.Account;

public interface AccountRepository {

    Account save(Account account);

    void update(Account account);

    Account getByNumber(String accountNumber);

}
