package zhonglin.test;

public class MethodSynchronize implements IMethodBody {

	public static int value = 0;
	
	public synchronized void increaseValue() {
		value++;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		value = 0;
	}

	@Override
	public int getResultValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public int getExpectValue(int threadCount) {
		// TODO Auto-generated method stub
		return threadCount;
	}
}
