package zhonglin.test;

public interface IMethodBody {
	void init();
	void increaseValue();
	int getResultValue();
	int getExpectValue(int threadCount);
}
