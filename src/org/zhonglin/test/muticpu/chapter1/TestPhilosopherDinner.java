package zhonglin.test.muticpu.chapter1;

import java.util.LinkedList;
import java.util.List;
import zhonglin.test.framework.concurrence.condition.MainConcurrentThread;
import zhonglin.test.framework.concurrence.condition.job.JobInterface;

public class TestPhilosopherDinner {
	
	public static void main(String[] args) {
		PhilosopherDinner obj = new PhilosopherDinner();
		DinningJob job2 = new DinningJob(obj);
		DinningJob2 job1 = new DinningJob2(obj);
		job1.setThreadCount(1);
		job2.setThreadCount(1);
		List<JobInterface> jobList = new LinkedList<JobInterface>();
		jobList.add(job2);
		jobList.add(job1);
		MainConcurrentThread ct = new MainConcurrentThread(jobList);
		ct.start();
	}

}
