package zhonglin.test.chapter5.barrier;

import java.util.concurrent.CyclicBarrier;

public class MainWorker {
	
	public void doSomethingWhenArrival()
	{
		System.out.println("所有已经到达了栅栏，所以执行");
	}
	
	public static void main(String[] args) {
		final MainWorker mainWorker = new MainWorker();
		final Worker[] workers;
		final int count = 5;
		CyclicBarrier barrier = new CyclicBarrier(count, new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mainWorker.doSomethingWhenArrival();
			}
		});
		
		workers = new Worker[count];
		//第一次栅栏开始，运行任务
		for(int i = 0; i < count; i++){
			workers[i] = new Worker(barrier);
		}
		
		for(int i = 0; i < count; i++){
			new Thread(workers[i]).start();
		}

		try {
			//这里使用延时，让上面的任务先完成
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//第二次栅栏的开始，不用重置barrier
		
		for(int i = 0; i < count; i++){
			new Thread(workers[i]).start();
		}
	}

}
