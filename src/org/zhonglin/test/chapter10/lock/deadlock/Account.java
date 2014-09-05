package zhonglin.test.chapter10.lock.deadlock;

public class Account {
	
	private int balance;
	
	public Account(int balance){
		this.balance = balance;
	}
	
	public void debit(int amount){
		balance = balance - amount;
	}
	
	public void credit(int amount){
		balance = balance + amount;
	}

	public int getBalance() {
		return balance;
	}
	
	
}
