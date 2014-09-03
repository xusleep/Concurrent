package zhonglin.test.chapter9;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public abstract class WorkingTask implements Callable {

	// 这里编写取消当前的任务的方法
	public abstract void cancel();

	public FutureTask newTask() {
		// TODO Auto-generated method stub
		return new FutureTask(this){
			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				// TODO Auto-generated method stub
				//在这里调用我们自己编写的一些取消任务的方法，这里面不包含中断，因为中断会在接下来的方法中被触发
				WorkingTask.this.cancel();
				//若传入的参数mayInterruptIfRunning为true的话，会调用Thread.currentThread().interrupt()中断运行的线程
				//因此如果我们的任务中，有处理中断的话，就可以直接取消任务了
				return super.cancel(mayInterruptIfRunning);
			}

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
			}
		};
	}

}
