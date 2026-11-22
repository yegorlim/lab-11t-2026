package model;

import exceptions.AuthenticationException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import exceptions.InvalidPasswordException;

import java.math.BigDecimal;

public class BankAccount {

    private final String customerName;
    private final String userId;
    private final String password;
    private BigDecimal balance = BigDecimal.ZERO;

    public BankAccount(String customerName, String userId, String password) {
        validatePassword(password);
        this.customerName = customerName;
        this.userId = userId;
        this.password = password;
    }

    public static void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters long");
        }
        if (password.chars().noneMatch(Character::isDigit)) {
            throw new InvalidPasswordException("Password must contain at least one digit");
        }
    }

    public void checkPassword(String password) {
        if (!this.password.equals(password)) {
            throw new AuthenticationException("Wrong password for user id " + userId);
        }
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than 0");
        }
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than 0");
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientFundsException("Insufficient funds: balance is " + balance.toPlainString());
        }
        balance = balance.subtract(amount);
    }

    public String getAccountType() {
        return "Basic";
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
