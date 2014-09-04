package zhonglin.test.chapter5.block;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class TaskRunnable implements Runnable {
	
	BlockingQueue<Task> queue;
	
	public TaskRunnable(BlockingQueue<Task> queue){
		this.queue = queue;	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
			for(int i = 0; i < 5; i++)
			{
				try {
					queue.put(new Task());
					System.out.println("put " + i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Thread.currentThread().interrupt();
				}                                            
				
			}
	}

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Task> queue = new LinkedBlockingQueue<Task>(1);
		// TODO Auto-generated method stub
		Thread t = new Thread(new TaskRunnable(queue));
		t.start();
		t.interrupt();
		t.sleep(1000);
		t.interrupt();
		t.sleep(1000);
		t.interrupt();
		//t.resume();
		//Semaphore sem = new Semaphore(10);
	}

}
