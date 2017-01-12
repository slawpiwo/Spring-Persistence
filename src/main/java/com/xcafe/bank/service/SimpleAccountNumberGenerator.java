package com.xcafe.bank.service;


import java.util.concurrent.atomic.AtomicLong;

public class SimpleAccountNumberGenerator implements AccountNumberGenerator {

    public static final AtomicLong counter = new AtomicLong(0L);

    @Override
    public String getNext() {
        return String.format("%026d", counter.incrementAndGet());
    }
}
