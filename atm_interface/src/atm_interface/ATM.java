package atm_interface;
import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String id;
    private String pin;

    public User(String id, String pin) {
        this.id = id;
        this.pin = pin;
    }

    public String getId() {
        return id;
    }

    public String getPin() {
        return pin;
    }
}

class BankAccount {
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount() {
        balance = 0.0;
        transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited $" + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawn $" + amount);
            return true;
        }
        return false;
    }

    public void transfer(double amount, BankAccount recipientAccount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            recipientAccount.deposit(amount);
            transactionHistory.add("Transferred $" + amount + " to " + recipientAccount.hashCode());
        }
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}

public class ATM {
    private User currentUser;
    private BankAccount userAccount;

    public ATM() {
        userAccount = new BankAccount();
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Transaction History");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
    }

    public boolean authenticateUser(String id, String pin) {
        User user1 = new User("user1", "123");
        User user2 = new User("user2", "456");

        if (user1.getId().equals(id) && user1.getPin().equals(pin)) {
            currentUser = user1;
            return true;
        } else if (user2.getId().equals(id) && user2.getPin().equals(pin)) {
            currentUser = user2;
            return true;
        }

        return false;
    }

    public void withdraw(double amount) {
        if (userAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful. Current balance: $" + userAccount.getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }

    public void deposit(double amount) {
        userAccount.deposit(amount);
        System.out.println("Deposit successful. Current balance: $" + userAccount.getBalance());
    }

    public void transactionHistory() {
        ArrayList<String> history = userAccount.getTransactionHistory();
        System.out.println("Transaction History:");
        
        for (String transaction : history) {
            if (transaction.startsWith("Withdrawn")) {
                // Replace "Withdrawn" with "Withdrawn amount" for withdrawals.
                transaction = transaction.replace("Withdrawn", "Withdrawn amount");
            } else if (transaction.startsWith("Transferred")) {
                // Replace "Transferred" with "Transferred amount" for transfers.
                transaction = transaction.replace("Transferred", "Transferred amount");
            }
            System.out.println(transaction);
        }
    }

    public void transfer() {
        System.out.print("Enter the recipient's account ID: ");
        Scanner scanner = new Scanner(System.in);
        String recipientId = scanner.next();

        boolean recipientExists = recipientId.equals("user1") || recipientId.equals("user2");

        if (recipientExists) {
            System.out.print("Enter the transfer amount: $");
            double transferAmount = scanner.nextDouble();

            BankAccount recipientAccount = new BankAccount();

            if (userAccount.withdraw(transferAmount)) {
                recipientAccount.deposit(transferAmount);
                System.out.println("Transfer successful. Current balance: $" + userAccount.getBalance());
            } else {
                System.out.println("Transfer failed. Insufficient funds.");
            }
        } else {
            System.out.println("Recipient not found in the system.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        boolean isAuthenticated = false;
        do {
            System.out.print("Enter your 5-letter ID: ");
            String id = scanner.next();
            System.out.print("Enter your 3-number PIN: ");
            String pin = scanner.next();
            isAuthenticated = atm.authenticateUser(id, pin);
            if (!isAuthenticated) {
                System.out.println("Authentication failed. Please try again.");
            }
        } while (!isAuthenticated);

        System.out.println("Authentication successful. Welcome, " + atm.currentUser.getId() + "!");

        while (true) {
            atm.displayMenu();
            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    atm.transactionHistory();
                    break;
                case 4:
                    atm.transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-5).");
            }
        }
    }
}



