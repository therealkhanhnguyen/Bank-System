import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList <BankAccount> accounts = new ArrayList<>();
        boolean running = true;



        while(running) {
            System.out.println("\n====== BANK SYSTEM MENU ======");
            System.out.println("1. Create Account");
            System.out.println("2. Select Account");
            System.out.println("3. Exit");


            String input = scanner.nextLine();
            int choice = -1;

            try{
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please enter a number");
                continue;
            }

            switch(choice) {
                case 1:
                    //create checking or saving account
                    System.out.println("Do you want to create checking or saving account ?");
                    String type = scanner.nextLine().toLowerCase();
                    System.out.println("Customer name: ");
                    String name = scanner.nextLine().toLowerCase();
                    //name have to be a string, not number.

                    if(name.matches(".*\\d.*")){
                        System.out.println("Name cannot contain numbers");
                        break;
                    }

                    //balance can't be a string and bigger than 0
                    double balance = 0;

                    while(true) {
                        System.out.println("Starting balance: ");
                        try {
                            balance = Double.parseDouble(scanner.nextLine());
                            if (balance <= 0) {
                                System.out.println("Invalid balance. Please enter a positive number");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid option. Please enter a number");
                        }
                    }

                    int accountNumber = (int)(Math.random() * 90000 + 10000); // 5-digit number

                    BankAccount account;
                    if (type.equals("checking")) {
                        account = new CheckingAccount(name,balance,accountNumber);
                    } else if (type.equals("saving")) {
                        account = new SavingAccount(name,balance,accountNumber);
                    } else {
                        System.out.println("Invalid account type");
                        break;
                    }

                    //add to list:
                    accounts.add(account);
                    System.out.println("Account created");
                    account.printDetails();

                    break;
                case 2:
                    //list all accounts and choose 1 account
                    System.out.println("Available accounts ?");
                    for (BankAccount acc: accounts) {
                        System.out.println("Account number: " + acc.getAccountNumber() + " - " + acc.getCustomerName());
                    }

                    System.out.println("Enter account number: ");
                    int selectedNumber = -1;

                    try {
                        selectedNumber = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid account type");
                        break;
                    }

                    BankAccount selectedAccount = null;

                    for (BankAccount acc: accounts) {
                        if (acc.getAccountNumber() == selectedNumber) {
                            selectedAccount = acc;
                            break;
                        }
                    }

                    if (selectedAccount != null) {
                       boolean accountMenu = true;

                       while (accountMenu) {
                           System.out.println("1. Deposit");
                           System.out.println("2. Withdraw");
                           System.out.println("3. View Account");

                           //if account is saving. We'll have interest option:

                           if(selectedAccount instanceof SavingAccount) {
                               System.out.println("4. Apply Interest: ");
                               System.out.println("5. Exit");
                           } else {
                               System.out.println("4. Exit");
                           }
                            // handle input mismatch
                           int subChoice = -1;

                           try{
                               subChoice = Integer.parseInt(scanner.nextLine());
                           } catch (NumberFormatException e) {
                               System.out.println("Invalid option. Please enter a number");
                               continue;
                           }

                           switch (subChoice) {
                               case 1:
                                   System.out.println("Enter deposit amount: ");
                                   double depositAmount = Double.parseDouble(scanner.nextLine());
                                   selectedAccount.deposit(depositAmount);
                                   System.out.println("Deposited successfully");
                                   break;
                               case 2:
                                   System.out.println("Enter withdrawal amount: ");
                                   double withdrawalAmount = Double.parseDouble(scanner.nextLine());
                                   selectedAccount.withdraw(withdrawalAmount);
                                   System.out.println("Withdrawn successfully");
                                   break;
                               case 3:
                                   selectedAccount.printDetails();
                                   break;
                               case 4:
                                   if (selectedAccount instanceof SavingAccount) {
                                       ((SavingAccount) selectedAccount).applyInterest();
                                   } else {
                                       accountMenu = false;
                                   }
                                   break;
                               case 5:
                                   if (selectedAccount instanceof SavingAccount) {
                                       accountMenu = false;
                                   }
                                   break;
                                   default:
                                       System.out.println("Invalid option.");
                           }
                       }

                    } else {
                        System.out.println("Account not found");
                    }

                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        scanner.close();
    }
}