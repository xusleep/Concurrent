package zhonglin.test.chapter10.deadlock.job;

import zhonglin.test.chapter10.lock.deadlock.ShareingData;
import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public class AccountJob1 extends AbstractJob {
	
	private ShareingData objClassUsedSynchronized;
	
	public AccountJob1(ShareingData objClassUsedSynchronized)
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
		try {
			objClassUsedSynchronized.method1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub

	}
}
