public class SavingAccount extends BankAccount{

    public SavingAccount(String customerName, double balance, int accountNumber) {
        super(customerName, balance, accountNumber);
    }

    public void applyInterest() {
        double interest = this.getBalance() * 0.03;
        this.deposit(interest);
        System.out.println("Interest applied : $" + interest);
    }
}
