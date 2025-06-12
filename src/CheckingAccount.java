public class CheckingAccount extends BankAccount{


    public CheckingAccount(String customerName, double balance, int accountNumber) {
        super(customerName, balance, accountNumber);

    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount < -100){
            System.out.println("Overdraft limit reached");
        } else {
            super.withdraw(amount);
        }
    }
}
