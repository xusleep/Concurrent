package zhonglin.test.chapter3.share.synchronizeuse;

import zhonglin.test.chapter3.share.ClassUsedInterface;

public class ClassUsedSynchronized3 implements ClassUsedInterface {
	
	private int value = 0;
	private int resultValue = 0;
	private final Object lockObj = new Object();
	
	public void method1()
	{
		//使用synchronized 保护的块，保证了其访问的变量在其块内是会被其他线程改变的
		// 例如在其他线程访问method2时，设置了该值，但是在同步快内是会发现并知道的
		// 其不能阻止其线程，改变共享变量其值
		synchronized(this)
		{
			value = 0;
			int sleepCount = 5;
			while(true)
			{
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
		}
	}
	
	
	public void method2()
	{
		// 在设置该值的方法内部使用同步的方法 因此是安全的，其修改的值，不会反应到 其他线程的同步快内部 例如 method1
		// 注意它锁定了 和method1 不同的对象 因此还是会有同步的问题
		synchronized(this)
		{
			value = -2000;
		}
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
