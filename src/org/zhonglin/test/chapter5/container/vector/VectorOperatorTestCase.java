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
	 * 启动运行两个job，一个job在一个线程中运行，另外一个job在10个线程中运行，然后同时启动所有的线程
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
	 *  容器测试，在方法数据时，修改数据
	 */
	public void testVectorOperatorMethod1()
	{
		VectorOperatorNotIterator objVectorOperatorNotIterator = new VectorOperatorNotIterator();
		startTest(objVectorOperatorNotIterator);
		this.assertEquals(objVectorOperatorNotIterator.checkResult().getDescription(), true, objVectorOperatorNotIterator.checkResult().isSucessful());
	}
	
//	/**
//	 *  容器测试，在迭代访问数据时，修改数据
//	 */
//	public void testVectorOperatorMethod2()
//	{
//		startTest(new VectorOperatorExplicitIterator());
//	}
	
//	/**
//	 *  容器测试，使用了隐式迭代器
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
