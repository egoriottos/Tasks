import java.util.HashMap;
import java.util.Map;

public class ConcurrentBank {
    private Map<Integer,BankAccount> accountMap = new HashMap<>();
    private int accountNumber = 1;
    public synchronized BankAccount createAccount(int amount){
        accountNumber++;
        BankAccount account = new BankAccount(amount,accountNumber);
        accountMap.put(accountNumber,account);
        return account;
    }
    public synchronized void transfer(BankAccount from, BankAccount to, int amount){
        BankAccount account1 = from;
        BankAccount account2 = to;
        synchronized (account1){
            synchronized (account2){
                if(account1.getBalance()> account2.getBalance()){
                    account1.withDraw(amount);
                    account2.deposit(amount);
                }
                else{
                    account1.deposit(amount);
                    account2.withDraw(amount);
                }
            }
        }
    }
    public synchronized int getTotalBalance(){
        int sum = 0;
        for(BankAccount num : accountMap.values()){
            sum = sum + num.getBalance();
        }
        return sum;
    }
}
