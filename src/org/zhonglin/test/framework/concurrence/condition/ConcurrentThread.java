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
			//������һ��֤���Ѿ���һ���߳�׼�����ˣ�Ȼ���ٵȴ������̵߳�׼�����
			this.getMainThread().countDownConcurrentThreadLatch();
			this.getMainThread().awaitCountDownConcurrentThreadLatch();
			if(this.job != null)
			{
				this.job.doConcurrentJob();
			}
			//������һ��֤���Ѿ���һ���߳������job
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
