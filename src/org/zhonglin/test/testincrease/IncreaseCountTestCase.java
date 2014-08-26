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
	 * ��Ϊƽ���Ĳ��ԣ��ڲ�û��ѭ����������ԱȽ����ڲ������
	 */
	public void testIncreaseValue()
	{
		IncreaseCountJobNormal objTestIncreaseCountJob = new IncreaseCountJobNormal();
		mct.setJob(objTestIncreaseCountJob);
		mct.startConcurrentThreads();
		this.assertEquals(THREAD_COUNT, objTestIncreaseCountJob.getResultValue());
	}
	
	/**
	 * �ڲ���job��ѭ�����ڣ��Ƚ����ײ��Գ�����
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
	 * �ڲ���job��ѭ�����ڣ��Ƚ����ײ��Գ�����, �ڲ�ʹ����ͬ����
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
	 * �ڲ���job��ѭ�����ڣ��Ƚ����ײ��Գ�����, �ڲ�ʹ��ԭ�Ӳ���
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
