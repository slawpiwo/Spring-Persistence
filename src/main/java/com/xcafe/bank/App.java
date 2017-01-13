package com.xcafe.bank;

import com.xcafe.bank.config.Beans;
import com.xcafe.bank.entity.Account;
import com.xcafe.bank.service.Bank;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext appCtx = new AnnotationConfigApplicationContext(Beans.class);
        Bank bank = appCtx.getBean(Bank.class);
        Account account1 = bank.createAccount();
        Account account2 = bank.createAccount();

        System.out.println("account 1: " + account1);
        System.out.println("account 2: " + account2);

        bank.makeDeposit(100L, account1.getNumber());
        bank.makeDeposit(250L, account2.getNumber());

        System.out.println("account 1: " + bank.getAccountDetails(account1.getNumber()));
        System.out.println("account 2: " + bank.getAccountDetails(account2.getNumber()));
    }
}
