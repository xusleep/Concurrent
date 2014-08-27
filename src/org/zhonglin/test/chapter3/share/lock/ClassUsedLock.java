package zhonglin.test.chapter3.share.lock;

import java.util.concurrent.locks.ReentrantLock;

import zhonglin.test.chapter3.share.ClassUsedInterface;

public class ClassUsedLock implements ClassUsedInterface {
	private int value = 0;
	private int resultValue = 0;
	private final ReentrantLock lock = new ReentrantLock();
	
	@Override
	public void method1() {
		lock.lock();
		System.out.println("Method 1 start time is " + System.currentTimeMillis());
		// TODO Auto-generated method stub
		value = 0;
		int sleepCount = 5;
		while(true)
		{
			System.out.println("Method 1 runing time is " + System.currentTimeMillis());
			if(value == -2000)
			{
				resultValue = value;
				break;
			}
			try {
				Thread.sleep(1000);
				sleepCount--;
				if(sleepCount < 0)
					break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(resultValue != -2000)
		{
			resultValue = 1000000;
		}
		System.out.println("Method 1 end time is " + System.currentTimeMillis());
		lock.unlock();
	}

	public void method2()
	{
		lock.lock();
		System.out.println("Method 2 start time is " + System.currentTimeMillis());
		value = -2000;
		lock.unlock();
		System.out.println("Method 2 end time is " + System.currentTimeMillis());
	}
	
	public int getExpectValue()
	{
		return 1000000;
	}
	
	public int getResultValue()
	{
		return resultValue;
	}

}
