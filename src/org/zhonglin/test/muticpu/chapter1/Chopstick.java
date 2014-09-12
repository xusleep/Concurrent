package zhonglin.test.muticpu.chapter1;


public class Chopstick extends Element{
	private String chopstickNumber;
	private Philosopher usedBy;

	public Chopstick(String number){
		this.chopstickNumber = number;
		usedBy = null;
	}
	
	public Philosopher getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(Philosopher usedBy) {
		this.usedBy = usedBy;
	}
	
	public String getChopstickNumber() {
		return chopstickNumber;
	}

	public void setChopstickNumber(String chopstickNumber) {
		this.chopstickNumber = chopstickNumber;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.chopstickNumber.equals(((Chopstick)obj).getChopstickNumber());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "chopstickNumber " + chopstickNumber;
	}

	@Override
	public boolean isPhilosopher() {
		// TODO Auto-generated method stub
		return false;
	}
}
