import java.util.concurrent.locks.Lock;

public class BankAccount {
    private int balance;
    private int accountNumber;
    private Lock lock;

    public Lock getLock() {
        return lock;
    }

    public BankAccount(int balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public synchronized void deposit(int sum){
        balance = balance+sum;

    }
    public synchronized void withDraw(int sum){
        if(sum>balance){
            throw new IllegalArgumentException();
        }
        balance= balance-sum;

    }
    public synchronized int getBalance(){
        return balance;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
}
