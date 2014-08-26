package zhonglin.test.noframework.concurrence;

public class MethodBodyIncreaseCount implements IMethodBody {
	public static final int INCREASE_COUNT = 100;
	public static int value = 0;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		value = 0;
	}

	@Override
	public void increaseValue() {
		// TODO Auto-generated method stub
		for (int i = 0; i < INCREASE_COUNT; i++)
			value++;
	}

	@Override
	public int getResultValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public int getExpectValue(int threadCount) {
		// TODO Auto-generated method stub
		return threadCount * INCREASE_COUNT;
	}

}
