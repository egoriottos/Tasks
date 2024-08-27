import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {
    private Map<Integer, BankAccount> accountMap = new HashMap<>();
    private int accountNumber = 1;

    public Lock getLock() {
        return lock;
    }

    public synchronized BankAccount createAccount(int amount) {
        accountNumber++;
        BankAccount account = new BankAccount(amount, accountNumber);
        accountMap.put(accountNumber, account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, int amount) {

        BankAccount account1 = from;
        BankAccount account2 = to;
        Lock firstLock = account1.getLock();
        Lock secondLock = account2.getLock();
        firstLock.lock();
        try {
            secondLock.lock();
            try {
                if (account1.getBalance() > account2.getBalance()) {
                    account1.withDraw(amount);
                    account2.deposit(amount);
                } else if(account1.getBalance()<=account2.getBalance()){
                    account1.deposit(amount);
                    account2.withDraw(amount);
                }
                else {
                    throw new IllegalArgumentException();
                }
            }
            finally {
                secondLock.unlock();
            }
        }
        finally {
            firstLock.unlock();
        }
    }

    public synchronized int getTotalBalance() {
        int sum = 0;
        for (BankAccount num : accountMap.values()) {
            sum = sum + num.getBalance();
        }
        return sum;
    }
}
