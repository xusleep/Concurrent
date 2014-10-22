package zhonglin.test.maxthreads;

import java.util.concurrent.CountDownLatch;
/**
 * 测试jvm可创建的最大的内存数
 * (MaxProcessMemory - JVMMemory - ReservedOsMemory) / (ThreadStackSize) = Number of threads
 * MaxProcessMemory 在32位的 windows下是 2G
 *	JVMMemory   eclipse默认启动的程序内存是64M
 *	ReservedOsMemory  一般是130M左右
 *	ThreadStackSize 32位 JDK 1.6默认的stacksize 325K左右
 *	公式如下：
 *	(2*1024*1024-64*1024-130*1024)/325 = 5841
 *	公式计算所得5841，和实践5602基本一致（有偏差是因为ReservedOsMemory不能很精确）
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
