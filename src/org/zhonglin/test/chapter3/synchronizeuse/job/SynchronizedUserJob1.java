package zhonglin.test.chapter3.synchronizeuse.job;

import zhonglin.test.chapter3.share.ClassUsedInterface;
import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public class SynchronizedUserJob1 extends AbstractJob {
	
	private ClassUsedInterface objClassUsedSynchronized;
	
	public SynchronizedUserJob1(ClassUsedInterface objClassUsedSynchronized)
	{
		this.objClassUsedSynchronized = objClassUsedSynchronized;
	}

	@Override
	public void doBeforeJob() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doConcurrentJob() {
		// TODO Auto-generated method stub
		objClassUsedSynchronized.method1();
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub

	}
}
