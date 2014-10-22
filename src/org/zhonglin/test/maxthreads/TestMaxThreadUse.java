package zhonglin.test.maxthreads;

import java.util.concurrent.CountDownLatch;
/**
 * ����jvm�ɴ����������ڴ���
 * (MaxProcessMemory - JVMMemory - ReservedOsMemory) / (ThreadStackSize) = Number of threads
 * MaxProcessMemory ��32λ�� windows���� 2G
 *	JVMMemory   eclipseĬ�������ĳ����ڴ���64M
 *	ReservedOsMemory  һ����130M����
 *	ThreadStackSize 32λ JDK 1.6Ĭ�ϵ�stacksize 325K����
 *	��ʽ���£�
 *	(2*1024*1024-64*1024-130*1024)/325 = 5841
 *	��ʽ��������5841����ʵ��5602����һ�£���ƫ������ΪReservedOsMemory���ܾܺ�ȷ��
 * @author zhonxu
 *
 */
public class TestMaxThreadUse {

	public static void main(String[] args) {

		System.out.println("Runtime.getRuntime().maxMemory() = " + Runtime.getRuntime().maxMemory() / (1024 * 1024) + "M");
		System.out.println("Runtime.getRuntime().freeMemory() = " + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "M");
		System.out.println("Runtime.getRuntime().totalMemory() = " + Runtime.getRuntime().totalMemory() / (1024 * 1024) + "M");
		System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors() );
		for (int i = 0;; i++) {
			System.out.println("i = " + i);
			new Thread(new HoldThread()).start();
			
		}
	}

}

class HoldThread extends Thread {
	CountDownLatch cdl = new CountDownLatch(1);

	public HoldThread() {
		this.setDaemon(true);
	}

	public void run() {
		try {
			cdl.await();
		} catch (InterruptedException e) {
		}
	}
}
