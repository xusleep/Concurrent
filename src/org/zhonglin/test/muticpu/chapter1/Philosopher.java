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
		//使用这种方法，会产生死锁，因为所有的哲学家可能都拿到了左边的筷子，然后企图拿右边的筷子的时候，这样变形成了一个环
		synchronized(left)
		{
			left.setUsedBy(this);
			PhilosopherDinner.msg.put(Thread.currentThread().getId() + "left", "left : " + left + " is used by " + left.getUsedBy());
			synchronized(right)
			{
				right.setUsedBy(this);
				PhilosopherDinner.msg.put(Thread.currentThread().getId()+ "person", this + " is eating!");
				PhilosopherDinner.msg.put(Thread.currentThread().getId()+ "right", "right: " + right + " is used by " + right.getUsedBy());
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				left.setUsedBy(null);
				right.setUsedBy(null);
			}
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
