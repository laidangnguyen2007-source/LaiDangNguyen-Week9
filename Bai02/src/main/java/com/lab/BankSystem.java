package com.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankSystem {
    private static final Logger logger = LoggerFactory.getLogger(BankSystem.class);
    private double balance;

    public BankSystem(double initialBalance) {
        this.balance = initialBalance;
        logger.info("Account created with balance: {}", initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            logger.error("Deposit amount must be positive. Provided: {}", amount);
            return;
        }
        balance += amount;
        logger.info("Deposited: {}. New balance: {}", amount, balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            logger.error("Withdrawal amount must be positive. Provided: {}", amount);
            return;
        }
        if (balance < amount) {
            logger.error("Insufficient funds. Balance: {}, Requested: {}", balance, amount);
            return;
        }
        balance -= amount;
        logger.info("Withdrew: {}. New balance: {}", amount, balance);
    }

    public double getBalance() {
        return balance;
    }
}
