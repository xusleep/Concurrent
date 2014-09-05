package zhonglin.test.chapter10.lock.deadlock;


public class TransferOperation {
	
	public static void transfer(final Account fromAcct, final Account toAcct, final int amount) throws Exception{
	
		class Helper{
			public void transfer() throws Exception
			{
				if(fromAcct.getBalance() < amount)
				{
					throw new Exception("Óà¶î²»×ã");
				}
				else
				{
					fromAcct.debit(amount);
					toAcct.credit(amount);
				}
			}
		}
	}
}
