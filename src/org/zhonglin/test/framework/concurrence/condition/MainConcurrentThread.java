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
	 * ������Ҫ���������е��߳���Ŀ
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
	 * ��ʼ��
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
			//����jobǰ��������һЩ׼������
			job.doBeforeJob();
		}
		for(int i = 0; i < countJobs; i++)
		{
			JobInterface job = jobList.get(i);
			int jobThreadCount = job.getThreadCount();
			//�������е��߳�
			for(int j = 0; j < jobThreadCount; j++)
			{
				Thread thr = new ConcurrentThread(this, job);
				thr.start();
			}
		}
		//�ȴ��������߳��������
		this.awaitCountDownMainConcurrentLatch();
		for(int i = 0; i < countJobs; i++)
		{
			JobInterface job = jobList.get(i);
			//������job��������һЩ��������
			job.doAfterJob();
		}
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
