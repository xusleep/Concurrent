package zhonglin.test.chapter3.safepublish.job;

import zhonglin.test.chapter3.safepublish.PublishClass;
import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public class SavePublishInitJob extends AbstractJob {
	private PublishClass plc;
	
	public SavePublishInitJob(PublishClass plc)
	{
		this.plc = plc;
	}
	
	public void doBeforeJob() {
		// TODO Auto-generated method stub
		
	}

	public void doConcurrentJob() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		plc.initialize();
	}

	public void doAfterJob() {
		// TODO Auto-generated method stub

	}

}
