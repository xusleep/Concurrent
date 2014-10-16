package zhonglin.test.flowcontrol;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ʵ��һ�����س��򡣿��ƿͻ���ÿ�����ĳ��Զ�̷��񲻳���N�Σ��ͻ����ǻ���̲߳������ã���Ҫһ����������ʵ�֣���ҿ��������һ��ʵ�֣�
 * Ȼ������Լ�дһ��ʵ�֡�
 * 
 * @author zhonxu
 *
 */
public class FlowControl {

	final static int MAX_QPS = 10;

	final static Semaphore semaphore = new Semaphore(MAX_QPS);

	public static void main(String... args) throws Exception {

		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
//				/**
//				 * ����������⣬�����ÿ�������û�г����趨���Ļ�����ô�ź�������������ۻ�
//				 */
//				semaphore.release(MAX_QPS / 2);
				int available = semaphore.availablePermits();
				 //ֻ�ͷ��õ������֤����
				 semaphore.release(MAX_QPS-available);
			}

		}, 1000, 1000, TimeUnit.MILLISECONDS);

		// lots of concurrent calls:100 * 1000
		ExecutorService pool = Executors.newFixedThreadPool(100);

		for (int i = 100; i > 0; i--) {

			final int x = i;

			pool.submit(new Runnable() {
				@Override
				public void run() {

					for (int j = 1; j > 0; j--) {
						semaphore.acquireUninterruptibly(1);
						remoteCall(x, j);

					}

				}

			});

		}

		pool.shutdown();

		pool.awaitTermination(1, TimeUnit.HOURS);

		System.out.println("DONE");
	}

	private static void remoteCall(int i, int j) {
		System.out.println(String.format("%s - %s: %d %d", new Date(),
				Thread.currentThread(), i, j));
	}

}
