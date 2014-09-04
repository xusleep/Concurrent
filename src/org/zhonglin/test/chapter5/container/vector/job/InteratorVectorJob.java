package zhonglin.test.chapter5.container.vector.job;

import zhonglin.test.chapter5.container.ContainerOperator;
import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public class InteratorVectorJob extends AbstractJob {
	
	private ContainerOperator vop;
	
	public InteratorVectorJob(ContainerOperator vop)
	{
		this.vop = vop;
	}

	@Override
	public void doBeforeJob() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doConcurrentJob() {
		// TODO Auto-generated method stub
		this.vop.interatorTheVector();
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub

	}

}
