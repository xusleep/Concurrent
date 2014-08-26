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
			//运行job前，先运行一些准备任务
			this.getJob().doBeforeJob();
			//启动所有的线程
			for(int i = 0; i < this.threadCount; i++)
			{
				Thread thr = new ConcurrentThread(this);
				thr.start();
			}
			//等待所有子线程完成任务
			this.awaitCountDownMainConcurrentLatch();
			this.getJob().doAfterJob();
		}
	}

	public void setJob(JobInterface job) {
		this.job = job;
	}

	/**
	 * 计数器减一，这里必须使用同步方法 主线程的count down 主要用于主线程 等待所有的任务完成
	 */
	public synchronized void countDownMainConcurrentLatch()
	{
		if(this.mainConcurrentStarterCountDownLatch != null)
		{
			this.mainConcurrentStarterCountDownLatch.countDown();
		}
	}
	/**
	 * 阻塞线程，这里必须使用同步方法 主线程的count down 主要用于主线程 等待所有的任务完成
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
	 * 计数器减一，这里必须使用同步方法 主线程的count down 主要用于并发线程 等待所有的任务完成
	 */
	public synchronized void countDownConcurrentThreadLatch()
	{
		if(this.concurrentThreadCountDownLatch != null)
		{
			this.concurrentThreadCountDownLatch.countDown();
		}
	}
	/**
	 * 阻塞线程，这里必须使用同步方法 并发线程的count down 主要用于并发线程 等待所有的任务完成
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
	 * 运行的任务
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
