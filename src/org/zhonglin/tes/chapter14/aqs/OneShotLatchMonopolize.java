package zhonglin.tes.chapter14.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
/**
 * ʹ��AQSʵ��һ�� ������ ��ʼ��ʱ�����Ǳ��ŵģ����濪ʼ��, 
 * ������ʹ�õ��Ƕ�ռ�ķ��ʷ�ʽ�����ÿһ��ֻ�ܻ���һ���߳�
 * �����������Ҫ�������������£�Ӧ�����ù���ʽ���ͷ�ʱ�������е��߳�
 * ��˴�����ʹ���˺ܶ���ͷŲ��������ͷ������еĵ��߳�
 * ����ÿ���ͷ��������ӳ٣�Ҫ��Ȼ�ͷſ��ܻ�ʧ��
 * @author zhonxu
 *
 */
public class OneShotLatchMonopolize {
	
	private Sync sync = new Sync();

	public void await(){
		sync.acquire(0);
	}
	
	public void signal(){
		sync.release(1);
	}
	
	private class Sync extends AbstractQueuedSynchronizer{
		
		public Sync(){
			this.setState(0);
		}

		@Override
		protected boolean tryAcquire(int arg0) {
			return this.getState() == 1 ? true : false;
		}

		@Override
		protected boolean tryRelease(int arg0) {
			this.setState(1);
			return true;
		}
		
	}
	
	public static void main(String[] agrs){
		final OneShotLatchMonopolize objOneShotLatch = new OneShotLatchMonopolize();
		for(int i = 0; i < 10; i++)
		{
			new Thread(new Runnable(){
	
				@Override
				public void run() {
					System.out.println("waiting to start");
					objOneShotLatch.await();
					System.out.println("doing something");
				}
				
			}).start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objOneShotLatch.signal();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objOneShotLatch.signal();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objOneShotLatch.signal();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objOneShotLatch.signal();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objOneShotLatch.signal();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objOneShotLatch.signal();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objOneShotLatch.signal();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objOneShotLatch.signal();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objOneShotLatch.signal();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objOneShotLatch.signal();
	}

}
