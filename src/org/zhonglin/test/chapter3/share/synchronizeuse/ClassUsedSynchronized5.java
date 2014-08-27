package zhonglin.test.chapter3.share.synchronizeuse;

import zhonglin.test.chapter3.share.ClassUsedInterface;

public class ClassUsedSynchronized5 implements ClassUsedInterface {

	private int value = 0;
	private int resultValue = 0;
	private final Object lockObj = new Object();

	public synchronized void method1() {
		System.out.println("Method 1 start time is " + System.currentTimeMillis());
		// 使用synchronized 保护的块，保证了其访问的变量在其块内是会被其他线程改变的
		// 例如在其他线程访问method2时，设置了该值，但是在同步快内是会发现并知道的
		// 其不能阻止其线程，改变共享变量其值
		value = 0;
		resultValue = 0;
		int sleepCount = 5;
		while (true) {
			if (value != 0) {
				resultValue = value;
				break;
			}
			try {
				Thread.sleep(1000);
				sleepCount--;
				if (sleepCount < 0)
					break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultValue == 0) {
			resultValue = value;
		}
		System.out.println("Method 1 end time is " + System.currentTimeMillis());
	}
	
	// 在方法上面添加一个synchronized锁定后，若一个线程已经进入了同步方法，那么其他线程将无法进入该方法，甚至其他同步的方法也不行
	// 例如method1。
	public synchronized void method2() {
		System.out.println("Method 2 start time is " + System.currentTimeMillis());
		if(value == 0)
			value = 1;
		value ++;
		
		System.out.println("Method 2 end time is " + System.currentTimeMillis());
	}

	public int getExpectValue() {
		return 0;
	}

	public int getResultValue() {
		return resultValue;
	}
}
