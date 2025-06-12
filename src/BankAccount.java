public class BankAccount {
    private String customerName;
    private int accountNumber;
    protected double balance;

    public BankAccount(String customerName, double balance, int accountNumber) {
        this.customerName = customerName;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0){
            this.balance += amount;
            System.out.println("Deposited $" + amount + " to " + customerName + " account");
            System.out.println("Account Balance: $" + this.balance);
            System.out.println("-".repeat(40));
        } else {
            System.out.println("Invalid amount");
        }
    }

    public void withdraw(double amount) {
        if (amount < 0){
            System.out.println("Insufficient balance");
        } else {
            this.balance -= amount;
            System.out.println("Withdrawn $" + amount + " to " + customerName + " account");
            double remainingBalance = this.balance;
            System.out.println("Remaining balance: $" + remainingBalance);
            System.out.println("-".repeat(40));
        }
    }

    public void printDetails() {
        System.out.println("Customer Name: " + customerName);
        System.out.println("Balance: $" + balance);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("-".repeat(40));
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getCustomerName() {
        return customerName;
    }
}
