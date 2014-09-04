package zhonglin.test.chapter5.container.vector;

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
import zhonglin.test.chapter5.container.ContainerOperator;
import zhonglin.test.chapter5.container.vector.job.InteratorVectorJob;
import zhonglin.test.chapter5.container.vector.job.ModifyVectorJob;
import zhonglin.test.framework.concurrence.condition.MainConcurrentThread;
import zhonglin.test.framework.concurrence.condition.job.JobInterface;
import junit.framework.TestCase;

public class VectorOperatorTestCase extends TestCase {
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
	private void startTest(ContainerOperator obj)
	{
		InteratorVectorJob job1 = new InteratorVectorJob(obj);
		ModifyVectorJob job2 = new ModifyVectorJob(obj);
		job1.setThreadCount(1);
		job2.setThreadCount(10);
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

	/**
	 *  �������ԣ��ڷ�������ʱ���޸�����
	 */
	public void testVectorOperatorMethod1()
	{
		VectorOperatorNotIterator objVectorOperatorNotIterator = new VectorOperatorNotIterator();
		startTest(objVectorOperatorNotIterator);
		this.assertEquals(objVectorOperatorNotIterator.checkResult().getDescription(), true, objVectorOperatorNotIterator.checkResult().isSucessful());
	}
	
//	/**
//	 *  �������ԣ��ڵ�����������ʱ���޸�����
//	 */
//	public void testVectorOperatorMethod2()
//	{
//		startTest(new VectorOperatorExplicitIterator());
//	}
	
//	/**
//	 *  �������ԣ�ʹ������ʽ������
//	 */
//	public void testVectorOperatorMethod3()
//	{
//		startTest(new VectorOperatorExplicitIterator());
//	}

	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
}
