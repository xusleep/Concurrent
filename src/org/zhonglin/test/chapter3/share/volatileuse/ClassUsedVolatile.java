package zhonglin.test.chapter3.share.volatileuse;

import zhonglin.test.chapter3.share.ClassUsedInterface;

public class ClassUsedVolatile implements ClassUsedInterface {
	private volatile long value = -1000000000000000l;
	private final long swap_value1 = -1000000000000000l;
	private final long swap_value2 = 9999999999999999l;

	@Override
	// 读线程，通过多个线程读取value
	public synchronized void method1() {
		// TODO Auto-generated method stub
		int sleepCount = 655536;
		while (true) {
			if (value != swap_value1 && value != swap_value2) {
				System.out
						.println("error is out the value is not swap 1 or swap 2, the value is  "
								+ value);
				break;
			}
			sleepCount--;
			if (sleepCount < 0)
				break;
		}
	}

	public void method2() {
		System.out.println("swap start. ");
		long sleepCount = 9999999999999999l;
		while (true) {
			if (value == swap_value1) {
				value = swap_value2;
			}

			if (value == swap_value2) {
				value = swap_value1;
			}
			sleepCount--;
			if (sleepCount < 0)
				break;
		}
		System.out.println("swap end.");
	}

	public int getExpectValue() {
		return 0;
	}

	public int getResultValue() {
		return 0;
	}

}
