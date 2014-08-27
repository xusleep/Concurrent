package zhonglin.test.testincrease;

import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public abstract class AbstractIncreaseCountJob extends  AbstractJob {
	public abstract void init();
	public abstract int getResultValue();
}
