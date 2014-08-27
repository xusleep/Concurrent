package zhonglin.test.chapter3.share.synchronizeuse;

import zhonglin.test.chapter3.share.ClassUsedInterface;

public class ClassUsedSynchronized5 implements ClassUsedInterface {

	private int value = 0;
	private int resultValue = 0;
	private final Object lockObj = new Object();

	public synchronized void method1() {
		System.out.println("Method 1 start time is " + System.currentTimeMillis());
		// ʹ��synchronized �����Ŀ飬��֤������ʵı�����������ǻᱻ�����̸߳ı��
		// �����������̷߳���method2ʱ�������˸�ֵ��������ͬ�������ǻᷢ�ֲ�֪����
		// �䲻����ֹ���̣߳��ı乲�������ֵ
		value = 0;
		resultValue = 0;
		int sleepCount = 5;
		while (true) {
			if (value != 0) {
				resultValue = value;
				break;
			}
			try {
				Thread.sleep(1000);
				sleepCount--;
				if (sleepCount < 0)
					break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultValue == 0) {
			resultValue = value;
		}
		System.out.println("Method 1 end time is " + System.currentTimeMillis());
	}
	
	// �ڷ����������һ��synchronized��������һ���߳��Ѿ�������ͬ����������ô�����߳̽��޷�����÷�������������ͬ���ķ���Ҳ����
	// ����method1��
	public synchronized void method2() {
		System.out.println("Method 2 start time is " + System.currentTimeMillis());
		if(value == 0)
			value = 1;
		value ++;
		
		System.out.println("Method 2 end time is " + System.currentTimeMillis());
	}

	public int getExpectValue() {
		return 0;
	}

	public int getResultValue() {
		return resultValue;
	}
}
