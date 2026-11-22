import exceptions.AccountException;
import exceptions.AccountNotFoundException;
import exceptions.DuplicateUserIdException;
import exceptions.InvalidAmountException;
import exceptions.NonZeroBalanceException;
import exceptions.PasswordMismatchException;
import model.BankAccount;
import model.CheckingAccount;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientInterface {

    private final List<BankAccount> accounts = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    private List<String> options = List.of(
            "1 - Create account",
            "2 - Delete account",
            "3 - Make account deposit",
            "4 - Make account withdrawal",
            "5 - Print account info",
            "6 - Exit"
    );

    public void start() throws IOException {

        String option;
        while (true) {
            printMainMenu();
            option = scanner.next();
            scanner.nextLine();

            try {
                switch (parseOption(option)) {
                    case 1:
                        addNewAccount();
                        break;
                    case 2:
                        deleteAccount();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        withdraw();
                        break;
                    case 5:
                        printInfo();
                        break;
                    case 6:
                        return;
                    default:
                        throw new AccountException("Invalid menu option: " + option);
                }
            } catch (AccountException e) {
                System.err.println("Error: " + e.getMessage() + "\n");
            }
        }

    }

    private void printMainMenu() throws IOException {
        System.out.println("\n****************");
        System.out.println("Banking system: ");
        System.out.println("****************");
        options.forEach(System.out::println);
        System.out.print("\n>>> Choose your option: ");
    }

    private int parseOption(String option) {
        try {
            return Integer.parseInt(option);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private BankAccount findAccount(String userId, String password) {
        BankAccount account = accounts.stream()
                .filter(a -> a.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Account with user id " + userId + " not found"));
        account.checkPassword(password);
        return account;
    }

    private BigDecimal parseAmount(String amount) {
        BigDecimal value;
        try {
            value = new BigDecimal(amount);
        } catch (NumberFormatException e) {
            throw new InvalidAmountException("Invalid amount: " + amount);
        }
        if (value.scale() > 2 || value.precision() - value.scale() > 12) {
            throw new InvalidAmountException("Invalid amount: " + amount);
        }
        return value;
    }

    private void addNewAccount() {

        System.out.println("\n****************");
        System.out.println("Creating new account: ");
        System.out.println("****************");

        System.out.print(">>> Enter customer name: ");
        var name = scanner.nextLine().trim();
        System.out.print(">>> Enter user id: ");
        var userId = scanner.next();
        System.out.print(">>> Enter user password: ");
        String password = scanner.next();
        System.out.print(">>> Confirm password: ");
        String confirmPassword = scanner.next();

        if (name.isBlank()) {
            throw new AccountException("Customer name must not be empty");
        }
        if (accounts.stream().anyMatch(a -> a.getUserId().equals(userId))) {
            throw new DuplicateUserIdException("User id " + userId + " is already taken");
        }
        BankAccount.validatePassword(password);
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException("Passwords do not match");
        }

        accounts.add(new CheckingAccount(name, userId, password));
        System.out.println("\nAccount created successfully.");
    }

    private void deleteAccount() {

        System.out.println("\n****************");
        System.out.println("Deleting account: ");
        System.out.println("****************");

        System.out.print(">>> Enter user id: ");
        var userId = scanner.next();
        System.out.print(">>> Enter user password: ");
        var password = scanner.next();

        BankAccount account = findAccount(userId, password);
        if (account.getBalance().signum() != 0) {
            throw new NonZeroBalanceException("Cannot delete account with non-zero balance");
        }
        accounts.remove(account);
        System.out.println("\nAccount deleted successfully.");
    }

    private void deposit() {

        System.out.println("\n****************");
        System.out.println("Making a deposit: ");
        System.out.println("****************");

        System.out.print(">>> Enter user id: ");
        var userId = scanner.next();
        System.out.print(">>> Enter user password: ");
        var password = scanner.next();
        System.out.print(">>> Enter amount: ");
        var amount = scanner.next();

        BankAccount account = findAccount(userId, password);
        account.deposit(parseAmount(amount));
        System.out.println("\nDeposit successful. New balance: " + account.getBalance().toPlainString());
    }

    private void withdraw() {

        System.out.println("\n****************");
        System.out.println("Making a withdrawal: ");
        System.out.println("****************");

        System.out.print(">>> Enter user id: ");
        var userId = scanner.next();
        System.out.print(">>> Enter user password: ");
        var password = scanner.next();
        System.out.print(">>> Enter amount: ");
        var amount = scanner.next();

        BankAccount account = findAccount(userId, password);
        account.withdraw(parseAmount(amount));
        System.out.println("\nWithdrawal successful. New balance: " + account.getBalance().toPlainString());
    }

    private void printInfo() {

        System.out.println("\n****************");
        System.out.println("Printing account info: ");
        System.out.println("****************");

        System.out.print(">>> Enter user id: ");
        var userId = scanner.next();
        System.out.print(">>> Enter user password: ");
        var password = scanner.next();

        BankAccount account = findAccount(userId, password);

        System.out.println("\nAccount info: ");

        System.out.println(" - customer name: " + account.getCustomerName());
        System.out.println(" - account balance: " + account.getBalance().toPlainString());
        System.out.println(" - account type: " + account.getAccountType());
        if (account instanceof CheckingAccount checking) {
            System.out.println(" - account daily withdrawal limit: "
                    + checking.getDailyWithdrawalLimit().toPlainString());
        }

        System.out.print("\nPress Enter to return to main menu...");

        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
