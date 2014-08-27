package zhonglin.test.chapter3.share;

import java.util.LinkedList;
import java.util.List;

import zhonglin.test.chapter3.share.lock.ClassUsedLock;
import zhonglin.test.chapter3.share.synchronizeuse.ClassUsedSynchronized;
import zhonglin.test.chapter3.share.synchronizeuse.ClassUsedSynchronized1;
import zhonglin.test.chapter3.share.synchronizeuse.ClassUsedSynchronized2;
import zhonglin.test.chapter3.share.synchronizeuse.ClassUsedSynchronized3;
import zhonglin.test.chapter3.share.synchronizeuse.ClassUsedSynchronized4;
import zhonglin.test.chapter3.share.synchronizeuse.ClassUsedSynchronized5;
import zhonglin.test.chapter3.share.volatileuse.ClassUsedVolatile;
import zhonglin.test.chapter3.synchronizeuse.job.SynchronizedUserJob1;
import zhonglin.test.chapter3.synchronizeuse.job.SynchronizedUserJob2;
import zhonglin.test.framework.concurrence.condition.MainConcurrentThread;
import zhonglin.test.framework.concurrence.condition.job.JobInterface;
import junit.framework.TestCase;

public class SychornizedTestCase extends TestCase {
	private final int THREAD_COUNT = 1;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	/**
	 * ������������job��һ��job��һ���߳������У�����һ��job��10���߳������У�Ȼ��ͬʱ�������е��߳�
	 * @param obj
	 */
	private void startTest(ClassUsedInterface obj)
	{
		SynchronizedUserJob2 job2 = new SynchronizedUserJob2(obj);
		SynchronizedUserJob1 job1 = new SynchronizedUserJob1(obj);
		job1.setThreadCount(1);
		job2.setThreadCount(10);
		List<JobInterface> jobList = new LinkedList<JobInterface>();
		jobList.add(job1);
		jobList.add(job2);
		MainConcurrentThread mct1 = new MainConcurrentThread(jobList);
		mct1.start();
		try {
			mct1.join();
			this.assertEquals(obj.getExpectValue(), obj.getResultValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		obj = null;
		job2 = null;
		job1 = null;
		mct1 = null;
	}
	
//	/**
//	 * �˲������ڲ��� ��synchronized����ʹ�õı����������ط��ı�ʱ���������ĵİ�ȫ�����⣬����������׾�������
//	 * ������������job��һ��job���޸ı�������������һ���߳��ж�ȡ ���ݷ����Ѿ��ı���
//	 */
//	public void testSychornizedMethod()
//	{
//		startTest(new ClassUsedSynchronized());
//	}
//
//	/**
//	 * �˲������ڲ��� ��synchronized����ʹ�õı����������ط��ı�ʱ���������ĵİ�ȫ�����⣬����������׾�������
//	 * ������������job��һ��job���޸ı�������������һ���߳��ж�ȡ ���ݷ����Ѿ��ı���
//	 */
//	public void testSychornizedMethod1()
//	{
//		startTest(new ClassUsedSynchronized1());
//	}
	
	
//	/**
//	 * �˲������ڲ��� ��synchronized����ʹ�õı����������ط��ı�ʱ��ʹ����ͬ�����Խ������
//	 */
//	public void testSychornizedMethod2()
//	{
//		startTest(new ClassUsedSynchronized2());
//	}
	
//	/**
//	 * �˲������ڲ��� ��synchronized����ʹ�õı����������ط��ı�ʱ��ʹ����ͬ�����Խ�����ϵ�����
//	 */
//	public void testSychornizedMethod3()
//	{
//		startTest(new ClassUsedSynchronized3());
//	}
	
//	/**
//	 * �˲������ڲ��� ��synchronized����ʹ�õı����������ط��ı�ʱ��ʹ����ͬ�����Խ�����ϵ�����
//	 */
//	public void testSychornizedMethod4()
//	{
//		startTest(new ClassUsedSynchronized4());
//	}
	
	
//	/**
//	 *  �˲��Ա�����һ�������ڷ����м���synchronized�ؼ���ʱ�����һ���߳̽��뵽synchronized������
//	 *  �����߳��ǲ��ܽ��뵽��synchronized��������������synchronized�����ڵ�
//	 *  ˵���ڷ����м���synchronized�����е����ǵ�ǰ�����
//	 */
//	public void testSychornizedMethod5()
//	{
//		startTest(new ClassUsedSynchronized5());
//	}
	
//	/**
//	 *  �˲��Ա�����һ�������ڷ����м����������ؼ���ʱ�����һ���߳̽��뵽��������
//	 *  �����߳��ǲ��ܽ��뵽������������
//	 */
//	public void testSychornizedMethod6()
//	{
//		startTest(new ClassUsedLock());
//	}

	/**
	 *  �˲��Ա�����һ�������ڷ����м����������ؼ���ʱ�����һ���߳̽��뵽��������
	 *  �����߳��ǲ��ܽ��뵽������������
	 */
	public void testSychornizedMethod7()
	{
		startTest(new ClassUsedVolatile());
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
}
