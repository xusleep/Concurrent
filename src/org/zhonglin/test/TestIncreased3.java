package zhonglin.test;

import java.util.concurrent.CountDownLatch;

import zhonglin.test.TestIncreased.TestIncreaseValueThreadWithAllTheThreadStartTheSameTime;
import junit.framework.TestCase;

public class TestIncreased3 extends AbstractTestIncreased {
	
	/**
	 * 在内部使用循环 更容易测试出并发错误
	 * 
	 * @return
	 */
	public static int increaseValue() {
		for (int i = 0; i < INCREACE_COUNT; i++)
			value++;
		return value;
	}
	
	public void testIncreaseValue3() {
		try {
			this.setUp();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CountDownLatch countDownLatch = new CountDownLatch(THREAD_TOTAL_COUNT);
		for (int i = 0; i < THREAD_TOTAL_COUNT; i++) {
			TestIncreaseValueThreadWithAllTheThreadStartTheSameTime thr = new TestIncreaseValueThreadWithAllTheThreadStartTheSameTime(
					countDownLatch);
			thr.start();
		}
		try {
			// 等待所有的线程都运行完成
			countDownLatch.await();
			this.assertEquals(THREAD_TOTAL_COUNT * INCREACE_COUNT, value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static class TestIncreaseValueThreadWithAllTheThreadStartTheSameTime
			extends Thread {

		private CountDownLatch countDownLatch;

		public TestIncreaseValueThreadWithAllTheThreadStartTheSameTime(
				CountDownLatch countDownLatch) {
			// TODO Auto-generated constructor stub
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {

				AbstractTestIncreased
						.printString("waiting for the thread to start...");
				// 使用全局信号量，等待所有的线程都准备好
				AbstractTestIncreased.countTheCountdown();
				AbstractTestIncreased.getTheCountdown().await();
				AbstractTestIncreased.printString("starting to increase");
				TestIncreased3.increaseValue();
				this.countDownLatch.countDown();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
