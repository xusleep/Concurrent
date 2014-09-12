package zhonglin.test.muticpu.chapter1;

import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public class DinningJob extends AbstractJob {
	
	private PhilosopherDinner objPhilosopherDinner;
	
	public DinningJob(PhilosopherDinner objPhilosopherDinner){
		this.objPhilosopherDinner = objPhilosopherDinner;
	}

	@Override
	public void doBeforeJob() {
	}

	@Override
	public void doConcurrentJob() {
		// TODO Auto-generated method stub
		objPhilosopherDinner.fetchAndEat("1");
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub
		objPhilosopherDinner = null;
	}

}
