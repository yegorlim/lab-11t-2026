package model;

import exceptions.WithdrawalLimitExceededException;

public class CheckingAccount extends BankAccount {

    private double dailyWithdrawalLimit = 300;

    public CheckingAccount(String customerName, String userId, String password) {
        super(customerName, userId, password);
    }

    public double getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }

    public void setDailyWithdrawalLimit(double dailyWithdrawalLimit) {
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > dailyWithdrawalLimit) {
            throw new WithdrawalLimitExceededException(
                    "Amount exceeds daily withdrawal limit of " + dailyWithdrawalLimit);
        }
        super.withdraw(amount);
    }
}
