package zhonglin.test.chapter10.lock.deadlock;


public class ShareingData implements ShareingDataInterface{
	private Account fromAcct = new Account(2000);
	private Account toAcct = new Account(2000);
	
	public void method1() throws Exception
	{
		synchronized(fromAcct)
		{
			synchronized(toAcct)
			{
				System.out.println("tranforming 1");
				TransferOperation.transfer(fromAcct, toAcct, 100);
			}
		}
	}
	
	public void method2() throws Exception
	{
		synchronized(toAcct)
		{
			synchronized(fromAcct)
			{
				System.out.println("tranforming 2");
				TransferOperation.transfer(toAcct, fromAcct, 100);
			}
		}
	}
}
