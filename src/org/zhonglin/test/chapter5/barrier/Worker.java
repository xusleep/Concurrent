package zhonglin.test.chapter5.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {
	private CyclicBarrier barrier;
	
	public Worker(CyclicBarrier barrier){
		this.barrier = barrier;
	}
	
	private void doSomethingWhenArrived()
	{
		System.out.println("我已经到达了栅栏，所以等待其他线程完成 thread id is " + Thread.currentThread().getId());
		try {
			this.barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("其他线程已经完成，继续运行 thread id is " + Thread.currentThread().getId());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		doSomethingWhenArrived();
	}
}
