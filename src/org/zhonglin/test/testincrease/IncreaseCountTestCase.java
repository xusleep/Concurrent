package zhonglin.test.testincrease;

import junit.framework.TestCase;
import zhonglin.test.framework.concurrence.MainConcurrentStarter;

public class IncreaseCountTestCase extends TestCase{
	
	private MainConcurrentStarter mct;
	private final int THREAD_COUNT = 5000;
	private final int INCREASE_COUNT = 500;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		mct = new MainConcurrentStarter(THREAD_COUNT);
	}
	/**
	 * 最为平常的测试，内部没有循环，这个测试比较难于测出问题
	 */
	public void testIncreaseValue()
	{
		IncreaseCountJobNormal objTestIncreaseCountJob = new IncreaseCountJobNormal();
		mct.setJob(objTestIncreaseCountJob);
		mct.startConcurrentThreads();
		this.assertEquals(THREAD_COUNT, objTestIncreaseCountJob.getResultValue());
	}
	
	/**
	 * 内部的job有循环在内，比较容易测试出问题
	 */
	public void testIncreaseValueLoopInside()
	{
		IncreaseCountJobLoopInside objIncreaseCountJobLoopInside = new IncreaseCountJobLoopInside();
		objIncreaseCountJobLoopInside.setIncreateCount(INCREASE_COUNT);
		mct.setJob(objIncreaseCountJobLoopInside);
		mct.startConcurrentThreads();
		this.assertEquals(THREAD_COUNT * objIncreaseCountJobLoopInside.getIncreateCount(), objIncreaseCountJobLoopInside.getResultValue());
	}
	
	/**
	 * 内部的job有循环在内，比较容易测试出问题, 内部使用了同步快
	 */
	public void testIncreaseValueSynchronized()
	{
		IncreaseCountJobSynchronized objIncreaseCountJob = new IncreaseCountJobSynchronized();
		objIncreaseCountJob.setIncreateCount(INCREASE_COUNT);
		mct.setJob(objIncreaseCountJob);
		mct.startConcurrentThreads();
		this.assertEquals(THREAD_COUNT * objIncreaseCountJob.getIncreateCount(), objIncreaseCountJob.getResultValue());
	}
	
	/**
	 * 内部的job有循环在内，比较容易测试出问题, 内部使用原子操作
	 */
	public void testIncreaseValueAtomic()
	{
		IncreaseCountJobAtomic objIncreaseCountJob = new IncreaseCountJobAtomic();
		objIncreaseCountJob.setIncreateCount(INCREASE_COUNT);
		mct.setJob(objIncreaseCountJob);
		mct.startConcurrentThreads();
		this.assertEquals(THREAD_COUNT * objIncreaseCountJob.getIncreateCount(), objIncreaseCountJob.getResultValue());
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		mct = null; 
	}
	
	
}
