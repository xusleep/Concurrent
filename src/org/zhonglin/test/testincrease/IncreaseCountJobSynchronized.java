package zhonglin.test.testincrease;

import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public class IncreaseCountJobSynchronized extends AbstractIncreaseCountJob {
	public int increateCount;
	public int value;
	
	public IncreaseCountJobSynchronized()
	{
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		value = 0;
		increateCount = 0;
	}

	public int getIncreateCount() {
		return increateCount;
	}

	public void setIncreateCount(int increateCount) {
		this.increateCount = increateCount;
	}

	@Override
	public int getResultValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void doBeforeJob() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doConcurrentJob() {
		// TODO Auto-generated method stub
		synchronized(this)
		{
			for (int i = 0; i < this.getIncreateCount(); i++)
			{
				value++;
			}
		}
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub
		
	}

}
