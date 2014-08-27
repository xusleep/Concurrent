package zhonglin.test.chapter3.safepublish.job;

import zhonglin.test.chapter3.safepublish.PublishClass;
import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public class SavePublishAssertJob extends AbstractJob {
	private PublishClass plc;
	
	public SavePublishAssertJob(PublishClass plc)
	{
		this.plc = plc;
	}
	
	@Override
	public void doBeforeJob() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doConcurrentJob() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(plc.holder != null)
		{
			plc.holder.assertSanity();
		}
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub

	}

}
