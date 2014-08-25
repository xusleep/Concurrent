package zhonglin.test;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;
import junit.framework.TestFailure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestIncreased extends AbstractTestIncreased {

	public static int increaseValue() {
		value++;
		return value;
	}

	/**
	 * 本方法使用了thread.join()，阻塞了当前线程的运行等待子线程完成 但是子线程仍然是没有同时启动运行的
	 *
	 */
	public void testIncreaseValue() {
		try {
			this.setUp();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < THREAD_TOTAL_COUNT; i++) {
			Thread thr = new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					TestIncreased.increaseValue();
				}
			});
			list.add(thr);
			thr.start();
		}

		try {
			for (Thread thread : list) {
				thread.join();
			}
			this.assertEquals(THREAD_TOTAL_COUNT, value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list.clear();
		System.out.println(value);
	}

	/**
	 * 本方法使用了CountDown，阻塞了当前线程的运行等待子线程完成 但是子线程仍然是没有同时启动运行的
	 *
	 */
	public void testIncreaseValue1() {
		try {
			this.setUp();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CountDownLatch countDownLatch = new CountDownLatch(THREAD_TOTAL_COUNT);
		for (int i = 0; i < THREAD_TOTAL_COUNT; i++) {
			TestIncreaseValueThreadNotStartTheSameTime thr = new TestIncreaseValueThreadNotStartTheSameTime(
					countDownLatch);
			thr.start();
		}

		try {
			// 阻塞当前线程，直到倒数计数器倒数到0
			countDownLatch.await();
			this.assertEquals(THREAD_TOTAL_COUNT, value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(value);
	}

	/**
	 * 本例使用了两个countdown保证，所有的线程在同一个时刻触发
	 */
	public void testIncreaseValue2() {
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
			this.assertEquals(THREAD_TOTAL_COUNT, value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public static class TestIncreaseValueThreadNotStartTheSameTime extends
			Thread {

		private CountDownLatch countDownLatch;

		public TestIncreaseValueThreadNotStartTheSameTime(
				CountDownLatch countDownLatch) {
			// TODO Auto-generated constructor stub
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			TestIncreased.increaseValue();
			countDownLatch.countDown();
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
				TestIncreased.increaseValue();
				this.countDownLatch.countDown();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
