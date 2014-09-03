package zhonglin.test.chapter9;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 *  ��������У�������д��newTaskFor�����������������submit�����ʱ�򱻵���
 *  ���Է�װ���񣬲���submit�������䷵��
 *  ����������д�÷��������ҷ�����task�����װ�����ص�FutureTask
 *  ��������ǵĳ����оͿ��Ե��÷��ص�FutureTask��cancel������ȡ������
 *  ��WorkingTask���У����Ǹ�д��FutureTask��cancel������
 *  ����������������Ҫ��ȡ������Ĳ���
 * @author zhonxu
 *
 */
public class CancellingEncapsulationExecutor extends ThreadPoolExecutor {
	
	public CancellingEncapsulationExecutor(int nThreads)
	{
		super(nThreads, nThreads,
				0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
	}

	@Override
	protected <T> RunnableFuture<T> newTaskFor(Callable<T> task) {
		if(task instanceof WorkingTask)
		{
			return ((WorkingTask)task).newTask();
		}
		else
		{
			// TODO Auto-generated method stub
			return super.newTaskFor(task);
		}
	}
	

}
