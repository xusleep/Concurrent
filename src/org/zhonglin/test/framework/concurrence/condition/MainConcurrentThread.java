package zhonglin.test.framework.concurrence.condition;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import zhonglin.test.framework.concurrence.condition.job.JobInterface;
import zhonglin.test.framework.concurrence.condition.job.TestJob;

public class MainConcurrentThread extends Thread {
	private CountDownLatch mainConcurrentStarterCountDownLatch;
	private CountDownLatch concurrentThreadCountDownLatch;
	private List<JobInterface> jobList;
	
	public MainConcurrentThread(List<JobInterface> jobList)
	{ 
		this.jobList = jobList;
		init();
	}
	
	/**
	 * 返回需要创建的所有的线程数目
	 * @return
	 */
	private int getAllJobThreadCount()
	{
		int countJobs = jobList.size();
		int countAllJobThreads = 0;
		for(int i = 0; i < countJobs; i++)
		{
			countAllJobThreads = countAllJobThreads + jobList.get(i).getThreadCount();
		}
		return countAllJobThreads;
	}
	
	/**
	 * 初始化
	 * 
	 */
	private void init()
	{
		int allJobThreadCount = getAllJobThreadCount();
		this.mainConcurrentStarterCountDownLatch = new CountDownLatch(allJobThreadCount);
		this.concurrentThreadCountDownLatch = new CountDownLatch(allJobThreadCount);
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		int countJobs = jobList.size();
		for(int i = 0; i < countJobs; i++)
		{
			JobInterface job = jobList.get(i);
			//运行job前，先运行一些准备任务
			job.doBeforeJob();
		}
		for(int i = 0; i < countJobs; i++)
		{
			JobInterface job = jobList.get(i);
			int jobThreadCount = job.getThreadCount();
			//启动所有的线程
			for(int j = 0; j < jobThreadCount; j++)
			{
				Thread thr = new ConcurrentThread(this, job);
				thr.start();
			}
		}
		//等待所有子线程完成任务
		this.awaitCountDownMainConcurrentLatch();
		for(int i = 0; i < countJobs; i++)
		{
			JobInterface job = jobList.get(i);
			//运行完job后，先运行一些后续任务
			job.doAfterJob();
		}
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
	public static void main(String[] args) {
		TestJob job1 = new TestJob(1);
		job1.setThreadCount(1);
		TestJob job2 = new TestJob(2);
		job2.setThreadCount(10);
		List<JobInterface> jobList = new LinkedList<JobInterface>();
		jobList.add(job1);
		jobList.add(job2);
		MainConcurrentThread ct = new MainConcurrentThread(jobList);
		ct.start();
	}
}
