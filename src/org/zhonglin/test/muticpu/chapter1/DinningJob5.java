package zhonglin.test.muticpu.chapter1;

import zhonglin.test.framework.concurrence.condition.job.AbstractJob;

public class DinningJob5 extends AbstractJob {
	
	private PhilosopherDinner objPhilosopherDinner;
	
	public DinningJob5(PhilosopherDinner objPhilosopherDinner){
		this.objPhilosopherDinner = objPhilosopherDinner;
	}
	@Override
	public void doBeforeJob() {
	}

	@Override
	public void doConcurrentJob() {
		// TODO Auto-generated method stub
		objPhilosopherDinner.fetchAndEat("5");
	}

	@Override
	public void doAfterJob() {
		// TODO Auto-generated method stub
		objPhilosopherDinner = null;
	}

}
