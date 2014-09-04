package zhonglin.test.chapter9;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhonxu
 *
 */
public class LogService {
	
	private final CancellingEncapsulationExecutor logExecutor;
	private final BlockingQueue<String> msgQueue;
	private boolean isShutdown;
	private AtomicInteger reservations;
	private FutureTask consumer;
	private FutureTask producer;
	
	public LogService(){
		logExecutor = new CancellingEncapsulationExecutor(2);
		msgQueue = new LinkedBlockingQueue<String>(1);
		isShutdown = false;
		reservations = new AtomicInteger(0);
	}
	
	public void start()
	{
		consumer = consumeLog();
		producer = produceLog();
	}
	
	public void stop(){
		producer.cancel(true);
		logExecutor.shutdown();
	}
	
	public synchronized void setShutdown(boolean flag){
		this.isShutdown = flag;
	}
	
	public FutureTask produceLog()
	{
		return (FutureTask) logExecutor.submit(new WorkingTask(){

			@Override
			public Object call() throws Exception {
				try
				{
					int j = 0;
					for(int i = 0; i < 100000; i++)
					{

						if(!isShutdown)
						{
							msgQueue.put("ddfsd " + i);
							reservations.incrementAndGet();
						}
						else
						{
							break;
						}
					}
				}
				catch(InterruptedException ignored)
				{
					//�����˳��̣߳������κ�����
					//�˳��̣߳������ñ�ʶλ��ʾֹͣ������
					setShutdown(true);
					System.out.println("test 1");
				}
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void cancel() {
				// TODO Auto-generated method stub
				System.out.println("I am doing something to cancel the producer job");
				//ֹͣ������
				consumer.cancel(true);
			}
			
		});
	}
	
	public FutureTask consumeLog()
	{
		return (FutureTask) logExecutor.submit(new WorkingTask(){

			@Override
			public Object call() throws Exception {
					while(true)
					{
						try
						{
							if(isShutdown && (reservations.get() == 0)){
								break;
							}
							String msg = msgQueue.take();
							reservations.decrementAndGet();
							Thread.sleep(100);
							System.out.println("log " + msg);
						}
						catch(InterruptedException ignored)
						{
							//������Ҫ���ԣ���֤��������˳�
						}
					}
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void cancel() {
				// TODO Auto-generated method stub
				System.out.println("I am doing something to cancel the consume job");
				//�˳��̣߳������ñ�ʶλ��ʾֹͣ������
				setShutdown(true);
			}
			
		});
	}
	
	public static void main(String[] args){
		try {
			LogService objLogService = new LogService();
			objLogService.start();
			Random r = new Random();
			Thread.sleep(r.nextInt(5) * 1000);
			objLogService.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
