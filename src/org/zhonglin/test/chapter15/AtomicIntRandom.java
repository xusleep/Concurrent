package zhonglin.test.chapter15;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntRandom {
	
	private final AtomicInteger seed ;
	
	public AtomicIntRandom(int s){
		seed = new AtomicInteger(s);
	}
	
	public int nextInt(int n) {
		while(true)
		{
			int i = seed.get();
			int next = caculateNextInt(i);
			if(seed.compareAndSet(i, next)){
				int remainder = i % n;
				return remainder > 0 ? remainder : remainder + n;
			}
		}
	}
	
	private int caculateNextInt(int i){
		Random r = new Random(i);
		return r.nextInt();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final AtomicIntRandom air = new AtomicIntRandom(100);
		for(int i = 10; i < 20; i++)
		{
			final int value = i;
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(air.nextInt(value));
				}
				
			}).start();
		}
		
	}

}
