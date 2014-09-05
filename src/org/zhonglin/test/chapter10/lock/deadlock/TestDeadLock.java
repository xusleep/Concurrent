package zhonglin.test.chapter10.lock.deadlock;

import java.util.LinkedList;
import java.util.List;

import zhonglin.test.chapter10.deadlock.job.AccountJob1;
import zhonglin.test.chapter10.deadlock.job.AccountJob2;
import zhonglin.test.framework.concurrence.condition.MainConcurrentThread;
import zhonglin.test.framework.concurrence.condition.job.JobInterface;

public class TestDeadLock {
	
	public static void main(String[] agrs){
		ShareingData obj = new ShareingData();
		AccountJob2 job2 = new AccountJob2(obj);
		AccountJob1 job1 = new AccountJob1(obj);
		job1.setThreadCount(100);
		job2.setThreadCount(100);
		List<JobInterface> jobList = new LinkedList<JobInterface>();
		jobList.add(job1);
		jobList.add(job2);
		MainConcurrentThread mct1 = new MainConcurrentThread(jobList);
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
}
