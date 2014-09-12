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
		DinningJob3 job3 = new DinningJob3(obj);
		DinningJob4 job4 = new DinningJob4(obj);
		DinningJob5 job5 = new DinningJob5(obj);
		DinningJob6 job6 = new DinningJob6(obj);
		job1.setThreadCount(10);
		job2.setThreadCount(10);
		job3.setThreadCount(10);
		job4.setThreadCount(10);
		job5.setThreadCount(10);
		job6.setThreadCount(10);
		
		List<JobInterface> jobList = new LinkedList<JobInterface>();
		jobList.add(job2);
		jobList.add(job1);
		jobList.add(job3);
		jobList.add(job4);
		jobList.add(job5);
		jobList.add(job6);
		
		MainConcurrentThread ct = new MainConcurrentThread(jobList, true);
		ct.start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PhilosopherDinner.printString();
			}
			
		}).start();;
	}

}
