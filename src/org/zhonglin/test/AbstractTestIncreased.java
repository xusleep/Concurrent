package zhonglin.test;

import java.util.concurrent.CountDownLatch;

import junit.framework.TestCase;

public class AbstractTestIncreased extends TestCase {
	public static int THREAD_TOTAL_COUNT = 10000;


	private static CountDownLatch countDownLatch;

	

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		countDownLatch = new CountDownLatch(THREAD_TOTAL_COUNT);
	}

	/**
	 * 使用同步的办法保证，计数是安全的
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
	 * 获得全局计数器
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
