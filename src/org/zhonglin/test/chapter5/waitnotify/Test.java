package zhonglin.test.chapter5.waitnotify;
/**
 * 此例子中，wait方法和notify方法都需要放在锁定的块中，否则将出现问题，
 * 如果当前线程不是锁的持有者，
 * 该方法抛出一个IllegalMonitorStateException异常
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
