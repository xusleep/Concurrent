package zhonglin.test;

import java.util.concurrent.CountDownLatch;

/**
 * ʹ��volatile�ؼ��� ��Ȼ����ȥ�� ������⣬ ���в����������
 * 
 * @author zhonxu
 *
 */
public class TestIncreased4 extends AbstractTestIncreased {
	/**
	 * ���ڲ�ʹ��ѭ�� �����ײ��Գ���������
	 * 
	 * @return
	 */
	public static int increaseValue() {
		for (int i = 0; i < INCREACE_COUNT; i++)
			value_volatile++;
		return value_volatile;
	}

	public void testIncreaseValue4() {
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
				// ʹ��ȫ���ź������ȴ����е��̶߳�׼����
				AbstractTestIncreased.countTheCountdown();
				AbstractTestIncreased.getTheCountdown().await();
				AbstractTestIncreased.printString("starting to increase");
				TestIncreased4.increaseValue();
				this.countDownLatch.countDown();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
