package zhonglin.test.muticpu.chapter1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 哲学家用餐问题解答
 * @author zhonxu
 *
 */
public class PhilosopherDinner {
	
	private NodeList list = new NodeList();
	
	public PhilosopherDinner()
	{
		list.createDoubleList(12);
		Node pp = list.getHead();
		for(int i = 1; i <= 6; i++)
		{
			Chopstick c = new Chopstick("" + i);
			pp.setE(c);
			Philosopher p = new Philosopher("" + i);
			pp = pp.getNext();
			pp.setE(p);
			pp = pp.getNext();
		}
		list.iteratorList();
	}
	
	public void fetchAndEat(String philosopherNumber )
	{
		Philosopher  p = getPhilosopher(philosopherNumber);
		if(p != null)
		{
			p.eat();
		}
	}

	public Philosopher getPhilosopher(String philosopherNumber){
		Node current = null;
		current = list.getHead();
		while (current != null) {
			if(current.getE().isPhilosopher())
			{
				Philosopher p = (Philosopher)current.getE();
				if(p.getPersonNumber().equals(philosopherNumber))
				{
					return p;
				}
			}
			current = current.getNext();
			if (current == list.getHead()) {
				break;
			}
		}
		return null;
	}
	
	public String getRandomPhilosopher(){
		Random r = new Random();
		int value = r.nextInt(6);
		while(value == 0)
		{
			value = r.nextInt(6);
		}
		return "" + value;
	}
}
