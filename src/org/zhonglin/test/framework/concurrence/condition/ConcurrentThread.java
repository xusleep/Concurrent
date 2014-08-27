package zhonglin.test.framework.concurrence.condition;

import zhonglin.test.framework.concurrence.condition.job.JobInterface;

public class ConcurrentThread extends Thread{
	private MainConcurrentThread mainThread;
	private JobInterface job;
	
	public ConcurrentThread(MainConcurrentThread mainThread, JobInterface job)
	{
		this.mainThread = mainThread;
		this.job = job;
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
			if(this.job != null)
			{
				this.job.doConcurrentJob();
			}
			//计数减一，证明已经有一个线程完成了job
			this.getMainThread().countDownMainConcurrentLatch();
		}
	}
	
	public MainConcurrentThread getMainThread() {
		return mainThread;
	}

	public void setMainThread(MainConcurrentThread mainThread) {
		this.mainThread = mainThread;
	}
}
