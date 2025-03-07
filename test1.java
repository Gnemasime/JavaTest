import java.util.Scanner;
import java.util.HashMap;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            return true;
        } else {
            System.out.println("Insufficient balance.");
            return false;
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }
}

class ATM {
    private HashMap<String, Account> accounts;
    private Account currentAccount;

    public ATM() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public boolean login(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            currentAccount = accounts.get(accountNumber);
            return true;
        } else {
            System.out.println("Account not found.");
            return false;
        }
    }

    public void deposit(double amount) {
        if (currentAccount != null) {
            currentAccount.deposit(amount);
        }
    }

    public void withdraw(double amount) {
        if (currentAccount != null) {
            currentAccount.withdraw(amount);
        }
    }

    public void checkBalance() {
        if (currentAccount != null) {
            currentAccount.checkBalance();
        }
    }

    public boolean isLoggedIn() {
        return currentAccount != null;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        // Create accounts and add them to the ATM system
        Account account1 = new Account("12345", "John Doe", 1000);
        Account account2 = new Account("67890", "Jane Doe", 2000);
        atm.addAccount(account1);
        atm.addAccount(account2);

        System.out.println("Welcome to the ATM System");
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Login");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    if (atm.login(accountNumber)) {
                        System.out.println("Login successful.");
                    }
                    break;
                case 2:
                    if (atm.isLoggedIn()) {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 3:
                    if (atm.isLoggedIn()) {
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 4:
                    if (atm.isLoggedIn()) {
                        atm.checkBalance();
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
