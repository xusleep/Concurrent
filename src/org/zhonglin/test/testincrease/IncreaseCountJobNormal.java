package zhonglin.test.testincrease;

public class IncreaseCountJobNormal extends AbstractIncreaseCountJob {

	private int value;
	private int expectedValue;
	
	public IncreaseCountJobNormal()
	{
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		value = 0;
		expectedValue = 0;
	}
	


	@Override
	public void doBeforeJob() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doConcurrentJob() {
		// TODO Auto-generated method stub
		value++;
	}

	@Override
	public void doAfterJob() {
	}
	
	@Override
	public int getResultValue() {
		// TODO Auto-generated method stub
		return value;
	}

}
