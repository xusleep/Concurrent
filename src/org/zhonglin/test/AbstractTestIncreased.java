package zhonglin.test;

import java.util.concurrent.CountDownLatch;

import junit.framework.TestCase;

public class AbstractTestIncreased extends TestCase {
	public static int THREAD_TOTAL_COUNT = 10000;
	public static int value = 0;
	public static volatile int value_volatile = 0;
	public static int INCREACE_COUNT = 100;
	private static CountDownLatch countDownLatch;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		value = 0;
		value_volatile = 0;
		countDownLatch = new CountDownLatch(THREAD_TOTAL_COUNT);
	}

	/**
	 * ʹ��ͬ���İ취��֤�������ǰ�ȫ��
	 */
	public static synchronized void countTheCountdown() {
		AbstractTestIncreased
				.printString("countDownLatch.getCount() before is "
						+ countDownLatch.getCount());
		countDownLatch.countDown();
		AbstractTestIncreased.printString("countDownLatch.getCount() after is "
				+ countDownLatch.getCount());
	}

	/**
	 * ���ȫ�ּ�����
	 * 
	 * @return
	 */
	public static CountDownLatch getTheCountdown() {
		return countDownLatch;
	}

	public static synchronized void printString(String str) {
		// System.out.println(str);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		countDownLatch = null;
	}
}
