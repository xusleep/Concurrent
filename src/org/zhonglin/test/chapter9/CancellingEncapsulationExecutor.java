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
 *  在这个类中，我们重写了newTaskFor方法，这个方法会在submit任务的时候被调用
 *  用以封装任务，并由submit方法将其返回
 *  这里我们重写该方法，并且返回在task里面封装并返回的FutureTask
 *  因此在我们的程序中就可以调用返回的FutureTask的cancel方法来取消任务
 *  在WorkingTask类中，我们改写了FutureTask的cancel方法，
 *  用以增加我们所需要的取消任务的操作
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
