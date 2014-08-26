package zhonglin.test.framework.concurrence;

import java.util.concurrent.CountDownLatch;

import zhonglin.test.framework.concurrence.job.JobInterface;
import zhonglin.test.framework.concurrence.job.TestJob;

public class MainConcurrentStarter {
	private CountDownLatch mainConcurrentStarterCountDownLatch;
	private CountDownLatch concurrentThreadCountDownLatch;
	private int threadCount;
	private JobInterface job;
	
	public MainConcurrentStarter(int threadCount)
	{
		this(threadCount, null);
	}
	
	public MainConcurrentStarter(int threadCount, JobInterface job)
	{ 
		this.threadCount = threadCount;
		this.job = job;
		init();
	}
	
	private void init()
	{
		this.mainConcurrentStarterCountDownLatch = new CountDownLatch(threadCount);
		this.concurrentThreadCountDownLatch = new CountDownLatch(threadCount);
	}
	
	
	public void startConcurrentThreads() {
		init();
		if(this.getJob() != null)
		{
			//����jobǰ��������һЩ׼������
			this.getJob().doBeforeJob();
			//�������е��߳�
			for(int i = 0; i < this.threadCount; i++)
			{
				Thread thr = new ConcurrentThread(this);
				thr.start();
			}
			//�ȴ��������߳��������
			this.awaitCountDownMainConcurrentLatch();
			this.getJob().doAfterJob();
		}
	}

	public void setJob(JobInterface job) {
		this.job = job;
	}

	/**
	 * ��������һ���������ʹ��ͬ������ ���̵߳�count down ��Ҫ�������߳� �ȴ����е��������
	 */
	public synchronized void countDownMainConcurrentLatch()
	{
		if(this.mainConcurrentStarterCountDownLatch != null)
		{
			this.mainConcurrentStarterCountDownLatch.countDown();
		}
	}
	/**
	 * �����̣߳��������ʹ��ͬ������ ���̵߳�count down ��Ҫ�������߳� �ȴ����е��������
	 */
	public void awaitCountDownMainConcurrentLatch()
	{
		if(this.mainConcurrentStarterCountDownLatch != null)
		{
			try {

				this.mainConcurrentStarterCountDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * ��������һ���������ʹ��ͬ������ ���̵߳�count down ��Ҫ���ڲ����߳� �ȴ����е��������
	 */
	public synchronized void countDownConcurrentThreadLatch()
	{
		if(this.concurrentThreadCountDownLatch != null)
		{
			this.concurrentThreadCountDownLatch.countDown();
		}
	}
	/**
	 * �����̣߳��������ʹ��ͬ������ �����̵߳�count down ��Ҫ���ڲ����߳� �ȴ����е��������
	 */
	public void awaitCountDownConcurrentThreadLatch()
	{
		if(this.concurrentThreadCountDownLatch != null)
		{
			try {
				this.concurrentThreadCountDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ���е�����
	 * @return
	 */
	public JobInterface getJob() {
		return job;
	}
	
	public static void main(String[] args) {
		MainConcurrentStarter mct = new MainConcurrentStarter(1000, new TestJob());
		mct.startConcurrentThreads();
	}
}
