import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank extends BankingInterface {
    private String name;
    private List<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addBranch(String branchName) {
        branches.add(new Branch(branchName));
    }

    public Branch findBranch(String branchName) {
        for (Branch branch : branches) {
            if (branch.getName().equalsIgnoreCase(branchName)) {
                return branch;
            }
        }
        return null;
    }

    @Override
    public void createAccount(String name, String branchName, double initialDeposit) {
        Branch branch = findBranch(branchName);
        if (branch == null) {
            System.out.println("Branch not found!");
            return;
        }
        String accountNumber = "AC" + new Random().nextInt(10000);
        Customer customer = new Customer(name, "C" + new Random().nextInt(1000));
        Account account = new Account(accountNumber, customer, initialDeposit);
        branch.addAccount(account);
        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + accountNumber);
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        for (Branch branch : branches) {
            Account account = branch.findAccount(accountNumber);
            if (account != null) {
                account.deposit(amount);
                System.out.println("Deposit successful! New Balance: " + account.getBalance());
                return;
            }
        }
        System.out.println("Account not found!");
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        for (Branch branch : branches) {
            Account account = branch.findAccount(accountNumber);
            if (account != null) {
                if (account.withdraw(amount)) {
                    System.out.println("Withdrawal successful! New Balance: " + account.getBalance());
                } else {
                    System.out.println("Insufficient balance!");
                }
                return;
            }
        }
        System.out.println("Account not found!");
    }

    @Override
    public void displayAccountDetails(String accountNumber) {
        for (Branch branch : branches) {
            Account account = branch.findAccount(accountNumber);
            if (account != null) {
                System.out.println("Account Number: " + account.getAccountNumber());
                System.out.println("Customer Name: " + account.getCustomer().getName());
                System.out.println("Balance: " + account.getBalance());
                return;
            }
        }
        System.out.println("Account not found!");
    }
}
