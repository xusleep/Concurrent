package zhonglin.test.muticpu.chapter1;

public class Philosopher extends Element
{
	private String personNumber;

	public Philosopher(String number){
		this.personNumber = number;
	}
	

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}
	
	public synchronized void eat(){
		Chopstick left = (Chopstick)this.getStoreNode().getPrevious().getE();
		Chopstick right = (Chopstick)this.getStoreNode().getNext().getE();
		if(left.getUsedBy() == null && right.getUsedBy() == null)
		{
			left.setUsedBy(this);
			right.setUsedBy(this);
			System.out.println(this + " is eating!");
			System.out.println("left : " + left + " is used by " + left.getUsedBy() + " right: " + right + " is used by " + right.getUsedBy());
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			left.setUsedBy(null);
			right.setUsedBy(null);
		}
		else
		{
			System.out.println(this + " can not get enough chostics to eat!");
			System.out.println("left : " + left + " is used by " + left.getUsedBy() + " right: " + right + " is used by " + right.getUsedBy());
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.personNumber.equals(((Philosopher)obj).getPersonNumber());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "personNumber " + personNumber;
	}

	@Override
	public boolean isPhilosopher() {
		// TODO Auto-generated method stub
		return true;
	}
}
