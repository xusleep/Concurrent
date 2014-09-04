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
		System.out.println("���Ѿ�������դ�������Եȴ������߳���� thread id is " + Thread.currentThread().getId());
		try {
			this.barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�����߳��Ѿ���ɣ��������� thread id is " + Thread.currentThread().getId());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		doSomethingWhenArrived();
	}
}
