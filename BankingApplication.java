import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create banks
        List<Bank> banks = new ArrayList<>();
        System.out.print("Enter the number of banks: ");
        int numberOfBanks = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfBanks; i++) {
            System.out.print("Enter the name of Bank " + (i + 1) + ": ");
            String bankName = scanner.nextLine();
            Bank bank = new Bank(bankName);

            System.out.print("Enter the number of branches for " + bankName + ": ");
            int numberOfBranches = scanner.nextInt();
            scanner.nextLine();

            for (int j = 0; j < numberOfBranches; j++) {
                System.out.print("Enter the name of Branch " + (j + 1) + " for " + bankName + ": ");
                String branchName = scanner.nextLine();
                bank.addBranch(branchName);
            }
            banks.add(bank);
        }

        // User interaction
        while (true) {
            System.out.println("\n1. Create Account\n2. Deposit\n3. Withdraw\n4. Display Account\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 5) break;

            System.out.print("Enter Bank Name: ");
            String bankName = scanner.nextLine();
            Bank selectedBank = null;

            for (Bank bank : banks) {
                if (bank.getName().equalsIgnoreCase(bankName)) {
                    selectedBank = bank;
                    break;
                }
            }

            if (selectedBank == null) {
                System.out.println("Bank not found!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Branch Name: ");
                    String branchName = scanner.nextLine();
                    System.out.print("Enter Initial Deposit: ");
                    double deposit = scanner.nextDouble();
                    selectedBank.createAccount(name, branchName, deposit);
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter Amount to Deposit: ");
                    double amount = scanner.nextDouble();
                    selectedBank.deposit(accountNumber, amount);
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter Amount to Withdraw: ");
                    amount = scanner.nextDouble();
                    selectedBank.withdraw(accountNumber, amount);
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    selectedBank.displayAccountDetails(accountNumber);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }
}
