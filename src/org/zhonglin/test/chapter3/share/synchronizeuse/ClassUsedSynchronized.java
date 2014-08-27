package zhonglin.test.chapter3.share.synchronizeuse;

import zhonglin.test.chapter3.share.ClassUsedInterface;

public class ClassUsedSynchronized implements ClassUsedInterface {
	
	private int value = 0;
	private int resultValue = 0;
	
	public void method1()
	{
		//ʹ��synchronized �����Ŀ飬��֤������ʵı�����������ǻᱻ�����̸߳ı��
		// �����������̷߳���method2ʱ�������˸�ֵ��������ͬ�������ǻᷢ�ֲ�֪����
		// �䲻����ֹ���̣߳��ı乲�������ֵ
		synchronized(this)
		{
			value = 0;
			int sleepCount = 5;
			while(true)
			{
				if(value == -2000)
				{
					resultValue = value;
					break;
				}
				try {
					Thread.sleep(1000);
					sleepCount--;
					if(sleepCount < 0)
						break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(resultValue != -2000)
			{
				resultValue = 1000000;
			}
		}
	}
	
	
	public void method2()
	{
		// �����ø�ֵ�ķ����ڲ�û��ʹ�� ͬ���ķ��� ����ǲ���ȫ�ģ����޸ĵ�ֵ���ᷴӦ�� �����̵߳�ͬ�����ڲ� ���� method1
		value = -2000;
	}
	
	public int getExpectValue()
	{
		return 1000000;
	}
	
	public int getResultValue()
	{
		return resultValue;
	}
}
