package zhonglin.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestIncreased extends AbstractTestIncreased {
	
	private IMethodBody objMethodBody;
	
	/**
	 * 本方法使用了thread.join()，阻塞了当前线程的运行等待子线程完成 但是子线程仍然是没有同时启动运行的
	 *
	 */
	public void testIncreaseValue() {
		try {
			this.setUp();
			objMethodBody = new MethodBodyNormal();
			objMethodBody.init();
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
					objMethodBody.increaseValue();
				}
			});
			list.add(thr);
			thr.start();
		}

		try {
			for (Thread thread : list) {
				thread.join();
			}
			this.assertEquals(objMethodBody.getExpectValue(THREAD_TOTAL_COUNT), objMethodBody.getResultValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list.clear();
	}

	/**
	 * 本方法使用了CountDown，阻塞了当前线程的运行等待子线程完成 但是子线程仍然是没有同时启动运行的
	 *
	 */
	public void testIncreaseValue1() {
		try {
			this.setUp();
			objMethodBody = new MethodBodyNormal();
			objMethodBody.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CountDownLatch countDownLatch = new CountDownLatch(THREAD_TOTAL_COUNT);
		for (int i = 0; i < THREAD_TOTAL_COUNT; i++) {
			TestIncreaseValueThreadNotStartTheSameTime thr = new TestIncreaseValueThreadNotStartTheSameTime(
					countDownLatch, objMethodBody);
			thr.start();
		}

		try {
			// 阻塞当前线程，直到倒数计数器倒数到0
			countDownLatch.await();
			this.assertEquals(objMethodBody.getExpectValue(THREAD_TOTAL_COUNT), objMethodBody.getResultValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 本例使用了两个countdown保证，所有的线程在同一个时刻触发
	 */
	public void testIncreaseValue2() {
		try {
			this.setUp();
			objMethodBody = new MethodBodyNormal();
			objMethodBody.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CountDownLatch countDownLatch = new CountDownLatch(THREAD_TOTAL_COUNT);
		for (int i = 0; i < THREAD_TOTAL_COUNT; i++) {
			TestIncreaseValueThreadStartTheSameTime thr = new TestIncreaseValueThreadStartTheSameTime(
					countDownLatch, objMethodBody);
			thr.start();
		}
		try {
			// 等待所有的线程都运行完成
			countDownLatch.await();
			this.assertEquals(objMethodBody.getExpectValue(THREAD_TOTAL_COUNT), objMethodBody.getResultValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 在内部使用循环 更容易测试出并发错误
	 */
	public void testIncreaseValue3() {
		try {
			this.setUp();
			objMethodBody = new MethodBodyIncreaseCount();
			objMethodBody.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CountDownLatch countDownLatch = new CountDownLatch(THREAD_TOTAL_COUNT);
		for (int i = 0; i < THREAD_TOTAL_COUNT; i++) {
			TestIncreaseValueThreadStartTheSameTime thr = new TestIncreaseValueThreadStartTheSameTime(
					countDownLatch, objMethodBody);
			thr.start();
		}
		try {
			// 等待所有的线程都运行完成
			countDownLatch.await();
			this.assertEquals(objMethodBody.getExpectValue(THREAD_TOTAL_COUNT), objMethodBody.getResultValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testIncreaseValue4() {
		try {
			this.setUp();
			objMethodBody = new MethodBodyVolatile();
			objMethodBody.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CountDownLatch countDownLatch = new CountDownLatch(THREAD_TOTAL_COUNT);
		for (int i = 0; i < THREAD_TOTAL_COUNT; i++) {
			TestIncreaseValueThreadStartTheSameTime thr = new TestIncreaseValueThreadStartTheSameTime(
					countDownLatch, objMethodBody);
			thr.start();
		}
		try {
			// 等待所有的线程都运行完成
			countDownLatch.await();
			this.assertEquals(objMethodBody.getExpectValue(THREAD_TOTAL_COUNT), objMethodBody.getResultValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testIncreaseValue5() {
		try {
			this.setUp();
			objMethodBody = new MethodSynchronize();
			objMethodBody.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CountDownLatch countDownLatch = new CountDownLatch(THREAD_TOTAL_COUNT);
		for (int i = 0; i < THREAD_TOTAL_COUNT; i++) {
			TestIncreaseValueThreadStartTheSameTime thr = new TestIncreaseValueThreadStartTheSameTime(
					countDownLatch, objMethodBody);
			thr.start();
		}
		try {
			// 等待所有的线程都运行完成
			countDownLatch.await();
			this.assertEquals(objMethodBody.getExpectValue(THREAD_TOTAL_COUNT), objMethodBody.getResultValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static class TestIncreaseValueThreadNotStartTheSameTime extends
			Thread {

		private CountDownLatch countDownLatch;
		private IMethodBody objMethodBody;

		public TestIncreaseValueThreadNotStartTheSameTime(
				CountDownLatch countDownLatch, IMethodBody objMethodBody) {
			// TODO Auto-generated constructor stub
			this.countDownLatch = countDownLatch;
			this.objMethodBody = objMethodBody;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			objMethodBody.increaseValue();
			countDownLatch.countDown();
		}

	}

	public static class TestIncreaseValueThreadStartTheSameTime
			extends Thread {

		private CountDownLatch countDownLatch;
		private IMethodBody objMethodBody;

		public TestIncreaseValueThreadStartTheSameTime(
				CountDownLatch countDownLatch, IMethodBody objMethodBody) {
			// TODO Auto-generated constructor stub
			this.countDownLatch = countDownLatch;
			this.objMethodBody = objMethodBody;
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
				objMethodBody.increaseValue();
				this.countDownLatch.countDown();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
