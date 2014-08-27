package zhonglin.test.testincrease;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import zhonglin.test.framework.concurrence.condition.MainConcurrentThread;
import zhonglin.test.framework.concurrence.condition.job.JobInterface;

public class IncreaseCountTestCase extends TestCase{
	private final int THREAD_COUNT = 1000;
	private final int INCREASE_COUNT = 200;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	
	private void startJob(AbstractIncreaseCountJob job)
	{
		List<JobInterface> jobList = new LinkedList<JobInterface>();
		job.setThreadCount(THREAD_COUNT);
		jobList.add(job);		
		MainConcurrentThread mct = new MainConcurrentThread(jobList);
		mct.start();
		try {
			mct.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 最为平常的测试，内部没有循环，这个测试比较难于测出问题
	 */
	public void testIncreaseValue()
	{
		IncreaseCountJobNormal job = new IncreaseCountJobNormal();
		startJob(job);
		this.assertEquals(THREAD_COUNT, job.getResultValue());
	}
	
	/**
	 * 内部的job有循环在内，比较容易测试出问题
	 */
	public void testIncreaseValueLoopInside()
	{
		IncreaseCountJobLoopInside job = new IncreaseCountJobLoopInside();
		job.setIncreateCount(INCREASE_COUNT);
		startJob(job);
		this.assertEquals(THREAD_COUNT * job.getIncreateCount(), job.getResultValue());
	}
	
	/**
	 * 内部的job有循环在内，内部使用了同步快,问题不会重现
	 */
	public void testIncreaseValueSynchronized()
	{
		IncreaseCountJobSynchronized job = new IncreaseCountJobSynchronized();
		job.setIncreateCount(INCREASE_COUNT);
		startJob(job);
		this.assertEquals(THREAD_COUNT * job.getIncreateCount(), job.getResultValue());
	}
	
	/**
	 * 内部的job有循环在, 内部使用原子操作，问题无法重现
	 */
	public void testIncreaseValueAtomic()
	{
		IncreaseCountJobAtomic job = new IncreaseCountJobAtomic();
		job.setIncreateCount(INCREASE_COUNT);
		startJob(job);
		this.assertEquals(THREAD_COUNT * job.getIncreateCount(), job.getResultValue());
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
}
