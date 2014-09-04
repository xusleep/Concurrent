package zhonglin.test.chapter9;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ������򽫷����޷����������⣬
 * ����producer������̫�죬�̶߳���û���ߵ�call��������ȥִ��������Ѿ���ȡ����
 * ����comsume���޷���������Ϊ��produce��������Ҫ�ȵ��жϵ����ʱ��Ż��жϳ���
 * ��˽��޷��ж�comsume����consumerһֱ�����������У���˳���һֱִ����ȥ��
 * @author zhonxu
 *
 */
public class LogServiceError {
	
	private final CancellingEncapsulationExecutor logExecutor;
	private final BlockingQueue<String> msgQueue;
	private boolean isShutdown;
	private AtomicInteger reservations;
	private FutureTask consumer;
	private FutureTask producer;
	
	public LogServiceError(){
		logExecutor = new CancellingEncapsulationExecutor(1);
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
		//consumer.cancel(true);
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
				System.out.println("produceLog start ");
				try
				{
					System.out.println("for");
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
					//ֹͣ������
					consumer.cancel(true);
				}
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void cancel() {
				// TODO Auto-generated method stub
				System.out.println("I am doing something to cancel the producer job");
			}
			
		});
	}
	
	public FutureTask consumeLog()
	{
		return (FutureTask) logExecutor.submit(new WorkingTask(){

			@Override
			public Object call() throws Exception {
					System.out.println("consumeLog start ");
					while(true)
					{
						try
						{
							if(isShutdown && (reservations.get() == 0)){
								break;
							}
							System.out.println("consumeLog start 1");
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
//		try {
			LogServiceError objLogService = new LogServiceError();
			objLogService.start();
//			Random r = new Random();
//			Thread.sleep(r.nextInt(1) * 1000);
			objLogService.stop();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
