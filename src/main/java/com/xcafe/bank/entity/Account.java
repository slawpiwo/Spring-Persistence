package com.xcafe.bank.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Account {

    private Long id;
    private String number;
    private long balance;


    public Account(String number) {
        this.number = number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public long getBalance() {
        return balance;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("number", number)
                .append("balance", balance)
                .toString();
    }
}
