import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String accountType;
    private double balance;

    public Account(String accountType, double initialBalance) {
        this.accountType = accountType;
        this.balance = initialBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class Onlinebanking {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Map<String, List<String>> transactionHistory = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Online Banking System");
            System.out.println("1. Create Account");
            System.out.println("2. View Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Funds Transfer");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    viewBalance();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    fundsTransfer();
                    break;
                case 6:
                    viewTransactionHistory();
                    break;
                case 7:
                    System.out.println("Thank you for using the Online Banking System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter initial balance: $");
        double initialBalance = scanner.nextDouble();
        System.out.print("Enter account type (e.g., Checking, Savings): ");
        String accountType = scanner.next();

        Account account = new Account(accountType, initialBalance);
        accounts.put(name, account);
        transactionHistory.put(name, new ArrayList<>());
        System.out.println("Account created successfully.");
    }

    private static void viewBalance() {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        Account account = accounts.get(name);
        if (account != null) {
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deposit() {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        Account account = accounts.get(name);
        if (account != null) {
            System.out.print("Enter the amount to deposit: $");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            recordTransaction(name, "Deposit: $" + amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        Account account = accounts.get(name);
        if (account != null) {
            System.out.print("Enter the amount to withdraw: $");
            double amount = scanner.nextDouble();
            if (account.withdraw(amount)) {
                recordTransaction(name, "Withdraw: $" + amount);
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void fundsTransfer() {
        System.out.print("Enter your name: ");
        String senderName = scanner.next();
        Account senderAccount = accounts.get(senderName);

        System.out.print("Enter recipient's name: ");
        String recipientName = scanner.next();
        Account recipientAccount = accounts.get(recipientName);

        if (senderAccount != null && recipientAccount != null) {
            System.out.print("Enter the amount to transfer: $");
            double amount = scanner.nextDouble();
            if (senderAccount.withdraw(amount)) {
                recipientAccount.deposit(amount);
                recordTransaction(senderName, "Transfer to " + recipientName + ": $" + amount);
                recordTransaction(recipientName, "Transfer from " + senderName + ": $" + amount);
                System.out.println("Funds transfer successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    private static void viewTransactionHistory() {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        List<String> history = transactionHistory.get(name);
        if (history != null) {
            System.out.println("Transaction History for " + name);
            for (String transaction : history) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void recordTransaction(String name, String transaction) {
        List<String> history = transactionHistory.get(name);
        if (history != null) {
            history.add(transaction);
        }
    }
}
