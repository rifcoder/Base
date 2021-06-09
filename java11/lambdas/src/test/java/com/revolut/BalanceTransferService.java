package com.revolut;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BalanceTransferService {
    private final Lock lock = new ReentrantLock();

    void transfer(Account from, Account to, BigDecimal amount) {
        // 1. Validate input data
        // 2. Check if account from have sufficient money on it
        // 3. Withdraw money from account "from"
        // 4. Place money to the account "to"
//        Objects.requireNonNull(from, "Invalid 'from' account, it must be not [null]");
        if (from == null) throw new IllegalArgumentException("Invalid 'from' account, it must be not [null]");
        if (to == null) throw new IllegalArgumentException("Invalid 'to' account, it must be not [null]");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount must be positive");

        lock.lock();
        try {
            boolean successful = from.withdraw(amount);
            if (!successful) {
                throw new IllegalStateException("There is not enough fund to withdraw from");
            }
        } finally {
            lock.unlock();
        }

        to.credit(amount);
    }

}