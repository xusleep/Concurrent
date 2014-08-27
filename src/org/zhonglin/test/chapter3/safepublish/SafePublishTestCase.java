package zhonglin.test.chapter3.safepublish;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import zhonglin.test.chapter3.safepublish.job.SavePublishAssertJob;
import zhonglin.test.chapter3.safepublish.job.SavePublishInitJob;
import zhonglin.test.framework.concurrence.condition.MainConcurrentThread;
import zhonglin.test.framework.concurrence.condition.job.JobInterface;

public class SafePublishTestCase extends TestCase {

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	public void testPublishClass() {

		PublishClass obj = new PublishClass();
		SavePublishAssertJob job1 = new SavePublishAssertJob(obj);
		job1.setThreadCount(1000);
		
		SavePublishInitJob job2 = new SavePublishInitJob(obj);
		job2.setThreadCount(1);
		
		List<JobInterface> jobList = new LinkedList<JobInterface>();
		jobList.add(job1);
		jobList.add(job2);
		
		MainConcurrentThread mct1 = new MainConcurrentThread(jobList);
		mct1.start();

		try {
			mct1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj = null;
		job2 = null;
		job1 = null;
		mct1 = null;
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

}
