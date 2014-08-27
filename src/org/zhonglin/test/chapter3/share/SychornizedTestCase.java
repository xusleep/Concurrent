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
	 * 启动运行两个job，一个job在一个线程中运行，另外一个job在10个线程中运行，然后同时启动所有的线程
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
//	 * 此测试用于测试 在synchronized块中使用的变量在其他地方改变时，所引发的的安全性问题，此问题很容易就重现了
//	 * 启动运行两个job，一个job在修改变量，而在另外一个线程中读取 数据发现已经改变了
//	 */
//	public void testSychornizedMethod()
//	{
//		startTest(new ClassUsedSynchronized());
//	}
//
//	/**
//	 * 此测试用于测试 在synchronized块中使用的变量在其他地方改变时，所引发的的安全性问题，此问题很容易就重现了
//	 * 启动运行两个job，一个job在修改变量，而在另外一个线程中读取 数据发现已经改变了
//	 */
//	public void testSychornizedMethod1()
//	{
//		startTest(new ClassUsedSynchronized1());
//	}
	
	
//	/**
//	 * 此测试用于测试 在synchronized块中使用的变量在其他地方改变时，使用锁同步可以解决问题
//	 */
//	public void testSychornizedMethod2()
//	{
//		startTest(new ClassUsedSynchronized2());
//	}
	
//	/**
//	 * 此测试用于测试 在synchronized块中使用的变量在其他地方改变时，使用锁同步可以解决以上的问题
//	 */
//	public void testSychornizedMethod3()
//	{
//		startTest(new ClassUsedSynchronized3());
//	}
	
//	/**
//	 * 此测试用于测试 在synchronized块中使用的变量在其他地方改变时，使用锁同步可以解决以上的问题
//	 */
//	public void testSychornizedMethod4()
//	{
//		startTest(new ClassUsedSynchronized4());
//	}
	
	
//	/**
//	 *  此测试表明，一个类中在方法中加入synchronized关键字时候，如果一个线程进入到synchronized方法后
//	 *  其他线程是不能进入到本synchronized方法，或者其他synchronized方法内的
//	 *  说明在方法中加入synchronized，持有的锁是当前类对象
//	 */
//	public void testSychornizedMethod5()
//	{
//		startTest(new ClassUsedSynchronized5());
//	}
	
//	/**
//	 *  此测试表明，一个类中在方法中加入重入锁关键字时候，如果一个线程进入到重入锁后
//	 *  其他线程是不能进入到本重入锁方法
//	 */
//	public void testSychornizedMethod6()
//	{
//		startTest(new ClassUsedLock());
//	}

	/**
	 *  此测试表明，一个类中在方法中加入重入锁关键字时候，如果一个线程进入到重入锁后
	 *  其他线程是不能进入到本重入锁方法
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
