package zhonglin.test.chapter10.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import zhonglin.test.chapter10.lock.deadlock.ShareingData;
import zhonglin.test.chapter10.lock.deadlock.ShareingData1;
import zhonglin.test.chapter10.lock.deadlock.ShareingDataInterface;
import zhonglin.test.chapter10.lock.deadlock.job.AccountJob1;
import zhonglin.test.chapter10.lock.deadlock.job.AccountJob2;
import zhonglin.test.framework.concurrence.condition.MainConcurrentThread;
import zhonglin.test.framework.concurrence.condition.job.JobInterface;

public class TestDeadLock {
	
	public static void startTest(ShareingDataInterface obj)
	{
		ConcurrentHashMap chm = new ConcurrentHashMap();
		AccountJob2 job2 = new AccountJob2(obj);
		AccountJob1 job1 = new AccountJob1(obj);
		job1.setThreadCount(10);
		job2.setThreadCount(10);
		List<JobInterface> jobList = new LinkedList<JobInterface>();
		jobList.add(job1);
		jobList.add(job2);
		MainConcurrentThread mct1 = new MainConcurrentThread(jobList, true);
		mct1.start();
		try {
			mct1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		obj = null;
		job2 = null;
		job1 = null;
		mct1 = null;
	}
	
	public static void test1(){
		ShareingData obj = new ShareingData();
		startTest(obj);
	}
	
	public static void test2(){
		ShareingData1 obj = new ShareingData1();
		startTest(obj);
	}
	
	public static void main(String[] agrs){
		TestDeadLock.test1();
		StringBuffer sb = new StringBuffer ();
	}
}
