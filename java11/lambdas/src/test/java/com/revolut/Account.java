package com.revolut;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean withdraw(BigDecimal amount) {
        BigDecimal newBalance = balance.subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        balance = newBalance;
        return true;
    }

    public boolean credit(BigDecimal amount) {
        balance = balance.add(amount);
        return true;
    }
    // empty skeleton, fill in
}
