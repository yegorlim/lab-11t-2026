package model;

import exceptions.InvalidAmountException;
import exceptions.WithdrawalLimitExceededException;

import java.math.BigDecimal;

public class CheckingAccount extends BankAccount {

    // Ліміт діє на одну операцію зняття, як сказано в README
    private BigDecimal dailyWithdrawalLimit = new BigDecimal("300");

    public CheckingAccount(String customerName, String userId, String password) {
        super(customerName, userId, password);
    }

    public BigDecimal getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }

    public void setDailyWithdrawalLimit(BigDecimal dailyWithdrawalLimit) {
        if (dailyWithdrawalLimit == null || dailyWithdrawalLimit.signum() < 0) {
            throw new InvalidAmountException("Daily withdrawal limit must be a non-negative amount");
        }
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (amount != null && amount.compareTo(dailyWithdrawalLimit) > 0) {
            throw new WithdrawalLimitExceededException(
                    "Amount exceeds daily withdrawal limit of " + dailyWithdrawalLimit.toPlainString());
        }
        super.withdraw(amount);
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
}
