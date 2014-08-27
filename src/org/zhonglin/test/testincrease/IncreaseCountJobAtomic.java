package zhonglin.test.testincrease;

import java.util.concurrent.atomic.AtomicInteger;

public class IncreaseCountJobAtomic extends AbstractIncreaseCountJob {
	public int increateCount;
	public AtomicInteger value;
	
	public IncreaseCountJobAtomic()
	{
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		value = new AtomicInteger(0);
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
		return value.get();
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
			value.incrementAndGet();
		}
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub
		
	}
}
