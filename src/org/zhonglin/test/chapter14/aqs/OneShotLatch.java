package zhonglin.test.chapter14.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
/**
 * 使用AQS实现一个 闭锁， 开始的时候锁是闭着的，后面开始打开
 * @author zhonxu
 *
 */
public class OneShotLatch {
	
	private Sync sync = new Sync();

	public void await(){
		sync.acquireShared(0);
	}
	
	public void signal(){
		sync.releaseShared(1);
	}
	
	private class Sync extends AbstractQueuedSynchronizer{
		
		public Sync(){
			this.setState(0);
		}

		@Override
		protected int tryAcquireShared(int arg0) {
			return this.getState() == 1 ? 1 : -1;
		}

		@Override
		protected boolean tryReleaseShared(int arg0) {
			this.setState(1);
			return true;
		}
		
	}
	
	public static void main(String[] agrs){
		final OneShotLatch objOneShotLatch = new OneShotLatch();
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
	}

}
