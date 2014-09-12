package zhonglin.test.muticpu.chapter1;

import java.util.LinkedList;
import java.util.List;

/**
 * 哲学家用餐问题解答
 * @author zhonxu
 *
 */
public class PhilosopherDinner {
	
	private static class Chopstick
	{
		public static final Philosopher NOT_USED = new Philosopher("0", "0", "0");
		private String chopstickNumber;
		private Philosopher usedPerson;
		
		public Chopstick(String number){
			usedPerson = NOT_USED;
			this.chopstickNumber = number;
		}
		
		public Philosopher getUsedPerson() {
			return usedPerson;
		}

		public void setUsedPerson(Philosopher p){
			usedPerson = p;
		}

		public String getChopstickNumber() {
			return chopstickNumber;
		}

		public void setChopstickNumber(String chopstickNumber) {
			this.chopstickNumber = chopstickNumber;
		}
		
		public boolean isUsed(){
			return !this.getUsedPerson().equals(NOT_USED);
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
		
		
	}
	
	private static class Philosopher
	{
		private String personNumber;
		private final String leftChostickNumber;
		private final String rightChostickNumber;
		private Chopstick leftChostick;
		private Chopstick rightChostick;
		private final Chopstick NOT_USE_C = new Chopstick("0");
		
		private List<Chopstick> chopsticks = new LinkedList<Chopstick>();
		

		public String getLeftChostickNumber() {
			return leftChostickNumber;
		}

		public String getRightChostickNumber() {
			return rightChostickNumber;
		}


		public Philosopher(String number, String leftChostickNumber, String rightChostickNumber){
			this.personNumber = number;
			this.leftChostickNumber = leftChostickNumber;
			this.rightChostickNumber = rightChostickNumber;
			this.leftChostick = NOT_USE_C;
			this.rightChostick = NOT_USE_C;
		}
		
		public String getPersonNumber() {
			return personNumber;
		}

		public void setPersonNumber(String personNumber) {
			this.personNumber = personNumber;
		}
		
		public void eating(List<Chopstick> chopsticks)
		{
			if(fetchStcik(chopsticks))
			{
				leftChostick.setUsedPerson(this);
				rightChostick.setUsedPerson(this);
				System.out.println(this + " I am eating using " + leftChostick + " " + rightChostick);
			}
			else
			{
				System.out.println(this + " Can not get the chopsticks, try to eat later.");
			}
		}
		
		public void notEating()
		{
			leftChostick.setUsedPerson(Chopstick.NOT_USED);
			rightChostick.setUsedPerson(Chopstick.NOT_USED);
			System.out.println(this + " I am not eating. any more");
		}
		
		public boolean fetchStcik(List<Chopstick> chopsticks){
			boolean hasLeft = false;
			boolean hasRight = false;
			for(int i = 0; i < chopsticks.size(); i++){
				Chopstick c = chopsticks.get(i);
				if(c.isUsed())
					continue;
				if(c.chopstickNumber.equals(this.leftChostickNumber))
				{
					hasLeft = true;
					leftChostick = c;
				}
				if(c.chopstickNumber.equals(this.rightChostickNumber))
				{
					hasRight = true;
					rightChostick = c;
				}
			}
			return hasLeft && hasRight;
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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final List<Chopstick> chopsticks = new LinkedList<Chopstick>();
		List<Philosopher> persons = new LinkedList<Philosopher>();
		Chopstick c = new Chopstick("1");
		c.setUsedPerson(Chopstick.NOT_USED);
		chopsticks.add(c);
		c = new Chopstick("2");
		c.setUsedPerson(Chopstick.NOT_USED);
		chopsticks.add(c);
		c = new Chopstick("3");
		c.setUsedPerson(Chopstick.NOT_USED);
		chopsticks.add(c);
		c = new Chopstick("4");
		c.setUsedPerson(Chopstick.NOT_USED);
		chopsticks.add(c);
		c = new Chopstick("5");
		c.setUsedPerson(Chopstick.NOT_USED);
		chopsticks.add(c);
		c = new Chopstick("6");
		c.setUsedPerson(Chopstick.NOT_USED);
		chopsticks.add(c);
		
		final Philosopher p1 = new Philosopher("1", "1", "2");
		persons.add(p1);
		
		final Philosopher p2 = new Philosopher("2", "2", "3");
		persons.add(p2);
		
		final Philosopher p3 = new Philosopher("3", "3", "4");
		persons.add(p3);
		
		Philosopher p4 = new Philosopher("4", "4", "5");
		persons.add(p4);
		
		Philosopher p5 = new Philosopher("5", "5", "6");
		persons.add(p5);
		
		Philosopher p6 = new Philosopher("6", "6", "1");
		persons.add(p6);
		
		for(int i = 0; i < 1; i++)
		{
			new Thread(new Runnable(){
	
				@Override
				public void run() {
					while(true)
					{
					// TODO Auto-generated method stub
						p1.eating(chopsticks);
						p1.notEating();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}).start();;
	
			new Thread(new Runnable(){
	
				@Override
				public void run() {
					while(true)
					{
						// TODO Auto-generated method stub
						p2.eating(chopsticks);
						p2.notEating();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}).start();;
			
			new Thread(new Runnable(){
				
				@Override
				public void run() {
					while(true)
					{
						// TODO Auto-generated method stub
						p3.eating(chopsticks);
						p3.notEating();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}).start();;
		}
	}

}
