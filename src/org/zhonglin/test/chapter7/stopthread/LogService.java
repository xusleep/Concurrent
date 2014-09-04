package zhonglin.test.chapter7.stopthread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LogService {
	
	private final BlockingQueue<String> queue;
	private final Thread consumeThread;
	
	private boolean isShutdown;
	private int reservations;
	
	public LogService()
	{
		queue = new ArrayBlockingQueue<String>(10);
		consumeThread = new ConsumeLogThread();
	}
	
	public void log(String s){
		
	}
	
	private class ConsumeLogThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
				String msg;
				try {
					while(true)
					{
						msg = queue.take();
						System.out.println("log " + msg);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
		}
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
