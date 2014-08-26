package zhonglin.test.noframework.concurrence;

public interface IMethodBody {
	void init();
	void increaseValue();
	int getResultValue();
	int getExpectValue(int threadCount);
}
