package zhonglin.test.chapter5.barrier;

import java.util.concurrent.CyclicBarrier;

public class MainWorker {
	
	public void doSomethingWhenArrival()
	{
		System.out.println("�����Ѿ�������դ��������ִ��");
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
		//��һ��դ����ʼ����������
		for(int i = 0; i < count; i++){
			workers[i] = new Worker(barrier);
		}
		
		for(int i = 0; i < count; i++){
			new Thread(workers[i]).start();
		}

		try {
			//����ʹ����ʱ������������������
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�ڶ���դ���Ŀ�ʼ����������barrier
		
		for(int i = 0; i < count; i++){
			new Thread(workers[i]).start();
		}
	}

}
