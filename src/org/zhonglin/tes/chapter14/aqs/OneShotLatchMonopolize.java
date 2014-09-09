package zhonglin.tes.chapter14.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
/**
 * 使用AQS实现一个 闭锁， 开始的时候锁是闭着的，后面开始打开, 
 * 这里面使用的是独占的访问方式，因此每一次只能唤醒一个线程
 * 因此在这种需要共享操作的情况下，应该是用共享方式，释放时唤醒所有的线程
 * 因此此例子使用了很多次释放操作，才释放了所有的的线程
 * 并且每次释放中需有延迟，要不然释放可能会失败
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
