package zhonglin.test.testincrease;

public class IncreaseCountJobLoopInside extends AbstractIncreaseCountJob
{
	public int increateCount;
	public int value;
	
	public IncreaseCountJobLoopInside()
	{
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

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
