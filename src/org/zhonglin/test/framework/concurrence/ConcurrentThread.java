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
			//������һ��֤���Ѿ���һ���߳�׼�����ˣ�Ȼ���ٵȴ������̵߳�׼�����
			this.getMainThread().countDownConcurrentThreadLatch();
			this.getMainThread().awaitCountDownConcurrentThreadLatch();
			if(this.getMainThread().getJob() != null)
			{
				this.getMainThread().getJob().doConcurrentJob();
			}
			//������һ��֤���Ѿ���һ���߳������job
			this.getMainThread().countDownMainConcurrentLatch();
		}
	}
	
	
}
