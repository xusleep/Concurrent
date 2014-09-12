package zhonglin.test.muticpu.chapter1;

import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public class DinningJob3 extends AbstractJob {
	
	private PhilosopherDinner objPhilosopherDinner;
	
	public DinningJob3(PhilosopherDinner objPhilosopherDinner){
		this.objPhilosopherDinner = objPhilosopherDinner;
	}
	@Override
	public void doBeforeJob() {
	}

	@Override
	public void doConcurrentJob() {
		// TODO Auto-generated method stub
		objPhilosopherDinner.fetchAndEat("3");
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub
		objPhilosopherDinner = null;
	}

}
