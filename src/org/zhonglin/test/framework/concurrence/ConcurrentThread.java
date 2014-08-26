package zhonglin.test.framework.concurrence;

public class ConcurrentThread extends Thread{
	private MainConcurrentStarter mainThread;
	
	public MainConcurrentStarter getMainThread() {
		return mainThread;
	}

	public void setMainThread(MainConcurrentStarter mainThread) {
		this.mainThread = mainThread;
	}

	public ConcurrentThread(MainConcurrentStarter mainThread)
	{
		this.mainThread = mainThread;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		if(this.getMainThread() != null)
		{
			//计数减一，证明已经有一个线程准备好了，然后再等待其他线程的准备完成
			this.getMainThread().countDownConcurrentThreadLatch();
			this.getMainThread().awaitCountDownConcurrentThreadLatch();
			if(this.getMainThread().getJob() != null)
			{
				this.getMainThread().getJob().doConcurrentJob();
			}
			//计数减一，证明已经有一个线程完成了job
			this.getMainThread().countDownMainConcurrentLatch();
		}
	}
	
	
}
