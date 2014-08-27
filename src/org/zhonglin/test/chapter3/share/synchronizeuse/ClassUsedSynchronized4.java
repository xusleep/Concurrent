package zhonglin.test.chapter3.share.synchronizeuse;

import zhonglin.test.chapter3.share.ClassUsedInterface;

public class ClassUsedSynchronized4 implements ClassUsedInterface {

	private int value = 0;
	private int resultValue = 0;
	private final Object lockObj = new Object();

	public synchronized void method1() {
		// ʹ��synchronized �����Ŀ飬��֤������ʵı�����������ǻᱻ�����̸߳ı��
		// �����������̷߳���method2ʱ�������˸�ֵ��������ͬ�������ǻᷢ�ֲ�֪����
		// �䲻����ֹ���̣߳��ı乲�������ֵ
		value = 0;
		int sleepCount = 5;
		while (true) {
			if (value == -2000) {
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
		if (resultValue != -2000) {
			resultValue = 1000000;
		}
	}

	public synchronized void method2() {
		// �����ø�ֵ�ķ����ڲ�ʹ��ͬ���ķ��� ����ǰ�ȫ�ģ����޸ĵ�ֵ�����ᷴӦ�� �����̵߳�ͬ�����ڲ� ���� method1
		// ע���������� ��method1 ��ͬ�Ķ��� ��˻��ǻ���ͬ��������
		value = -2000;
	}

	public int getExpectValue() {
		return 1000000;
	}

	public int getResultValue() {
		return resultValue;
	}
}
