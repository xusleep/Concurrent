package zhonglin.test.noframework.concurrence;

public class MethodBodyVolatile implements IMethodBody{
	public static volatile int value_volatile = 0;
	public static final int INCREASE_COUNT = 100;
	
	@Override
	public void increaseValue() {
		for (int i = 0; i < INCREASE_COUNT; i++)
			value_volatile++;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		value_volatile = 0;
	}

	@Override
	public int getResultValue() {
		// TODO Auto-generated method stub
		return value_volatile;
	}

	@Override
	public int getExpectValue(int threadCount) {
		// TODO Auto-generated method stub
		return threadCount * INCREASE_COUNT;
	}

}
