package zhonglin.test.framework.concurrence.job;

import zhonglin.test.framework.concurrence.utils.ConcurrentUtils;

public class TestJob implements JobInterface {

	@Override
	public void doBeforeJob() {
		// TODO Auto-generated method stub
		ConcurrentUtils.printString("I am starting my job.");
	}

	@Override

	public void doConcurrentJob() {
		// TODO Auto-generated method stub
		ConcurrentUtils.printString("I am doing some thing.");
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub
		ConcurrentUtils.printString("I finished my job.");
	}

}
