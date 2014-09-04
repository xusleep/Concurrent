package zhonglin.test.chapter9;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这个程序将发生无法结束的问题，
 * 由于producer结束的太快，线程都还没有走到call方法里面去执行任务就已经被取消了
 * 所以comsume，无法结束，因为在produce程序中是要等到中断到达的时候才会中断程序
 * 因此将无法中断comsume，而consumer一直处于阻塞当中，因此程序将一直执行下去。
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
					//允许退出线程，不做任何事情
					//退出线程，并设置标识位表示停止生产了
					setShutdown(true);
					System.out.println("test 1");
					//停止消费者
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
							//这里需要重试，保证消费完才退出
						}
					}
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void cancel() {
				// TODO Auto-generated method stub
				System.out.println("I am doing something to cancel the consume job");
				//退出线程，并设置标识位表示停止生产了
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
