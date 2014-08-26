package zhonglin.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestIncreased extends AbstractTestIncreased {
	
	private IMethodBody objMethodBody;
	
	/**
	 * ������ʹ����thread.join()�������˵�ǰ�̵߳����еȴ����߳���� �������߳���Ȼ��û��ͬʱ�������е�
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
	 * ������ʹ����CountDown�������˵�ǰ�̵߳����еȴ����߳���� �������߳���Ȼ��û��ͬʱ�������е�
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
			// ������ǰ�̣߳�ֱ������������������0
			countDownLatch.await();
			this.assertEquals(objMethodBody.getExpectValue(THREAD_TOTAL_COUNT), objMethodBody.getResultValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ʹ��������countdown��֤�����е��߳���ͬһ��ʱ�̴���
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
			// �ȴ����е��̶߳��������
			countDownLatch.await();
			this.assertEquals(objMethodBody.getExpectValue(THREAD_TOTAL_COUNT), objMethodBody.getResultValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ���ڲ�ʹ��ѭ�� �����ײ��Գ���������
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
			// �ȴ����е��̶߳��������
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
			// �ȴ����е��̶߳��������
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
			// �ȴ����е��̶߳��������
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
				// ʹ��ȫ���ź������ȴ����е��̶߳�׼����
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
