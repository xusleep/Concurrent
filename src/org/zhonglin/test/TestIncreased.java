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
	 * ������ʹ����thread.join()�������˵�ǰ�̵߳����еȴ����߳���� �������߳���Ȼ��û��ͬʱ�������е�
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
	 * ������ʹ����CountDown�������˵�ǰ�̵߳����еȴ����߳���� �������߳���Ȼ��û��ͬʱ�������е�
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
			// ������ǰ�̣߳�ֱ������������������0
			countDownLatch.await();
			this.assertEquals(THREAD_TOTAL_COUNT, value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(value);
	}

	/**
	 * ����ʹ��������countdown��֤�����е��߳���ͬһ��ʱ�̴���
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
			// �ȴ����е��̶߳��������
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
				// ʹ��ȫ���ź������ȴ����е��̶߳�׼����
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
