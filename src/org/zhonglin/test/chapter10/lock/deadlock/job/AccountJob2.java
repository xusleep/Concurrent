package zhonglin.test.chapter10.lock.deadlock.job;

import zhonglin.test.chapter10.lock.deadlock.ShareingDataInterface;
import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public class AccountJob2 extends AbstractJob {
	
	private ShareingDataInterface objClassUsedSynchronized;
	
	public AccountJob2(ShareingDataInterface objClassUsedSynchronized)
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
			objClassUsedSynchronized.method2();
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
