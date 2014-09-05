package zhonglin.test.chapter10.lock.deadlock;


public class ShareingData1 implements ShareingDataInterface{
	private Account fromAcct = new Account(2000);
	private Account toAcct = new Account(2000);
	
	/**
	 * 使用hashcode保持两个锁的顺序一致，以消除死锁
	 */
	public void method1() throws Exception
	{
		int fromHash = System.identityHashCode(fromAcct);
		int toHash = System.identityHashCode(toAcct);
		if(fromHash < toHash)
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
		else
		{
			synchronized(toAcct)
			{
				synchronized(fromAcct)
				{
					System.out.println("tranforming 1");
					TransferOperation.transfer(fromAcct, toAcct, 100);
				}
			}
		}
	}
	
	public void method2() throws Exception
	{
		int fromHash = System.identityHashCode(fromAcct);
		int toHash = System.identityHashCode(toAcct);
		if(fromHash < toHash)
		{
			synchronized(fromAcct)
			{
				synchronized(toAcct)
				{
					System.out.println("tranforming 2");
					TransferOperation.transfer(fromAcct, toAcct, 100);
				}
			}
		}
		else
		{
			synchronized(toAcct)
			{
				synchronized(fromAcct)
				{
					System.out.println("tranforming 2");
					TransferOperation.transfer(fromAcct, toAcct, 100);
				}
			}
		}
	}
}
