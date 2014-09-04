package zhonglin.test.chapter5.container.vector;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import zhonglin.test.chapter5.container.ContainerOperator;

public class VectorOperatorHiddenIterator implements ContainerOperator {
	
	private Vector vector = new Vector();
	
	public VectorOperatorHiddenIterator()
	{
		for(int i = 0; i < 1000; i++)
			vector.add(i);
	}

	@Override
	public void interatorTheVector() {
		// TODO Auto-generated method stub
		String s = "" + vector;
		System.out.println(s);
	}

	@Override
	public void modifyTheVector() {
		// TODO Auto-generated method stub
		Random random = new Random();
		int i = random.nextInt(vector.size());
		System.out.println("remove index " + i);
		vector.remove(i);
	}

}
