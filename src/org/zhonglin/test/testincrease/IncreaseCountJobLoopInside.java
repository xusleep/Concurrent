package zhonglin.test.testincrease;

import zhonglin.test.framework.concurrence.job.JobInterface;

public class IncreaseCountJobLoopInside implements JobInterface, IncreaseCountJobInterface{
	public int increateCount;
	public int value;
	
	public IncreaseCountJobLoopInside()
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
		for (int i = 0; i < this.getIncreateCount(); i++)
		{
			value++;
		}
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub
		
	}

}
