package zhonglin.test.chapter5.waitnotify;
/**
 * �������У�wait������notify��������Ҫ���������Ŀ��У����򽫳������⣬
 * �����ǰ�̲߳������ĳ����ߣ�
 * �÷����׳�һ��IllegalMonitorStateException�쳣
 * @author zhonxu
 *
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Object o = new Object();
		
		for(int i = 0; i < 10; i++){
			new Thread(
				new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							System.out.println("waiting " + Thread.currentThread().getId());
							synchronized(o)
							{
								o.wait();
							}
							System.out.println("running " + Thread.currentThread().getId());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			).start();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("notify all ");
		synchronized(o)
		{
			o.notifyAll();
		}
	}

}
