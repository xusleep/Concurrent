package zhonglin.test.chapter9;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public abstract class WorkingTask implements Callable {

	// �����дȡ����ǰ������ķ���
	public abstract void cancel();

	public FutureTask newTask() {
		// TODO Auto-generated method stub
		return new FutureTask(this){
			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				// TODO Auto-generated method stub
				//��������������Լ���д��һЩȡ������ķ����������治�����жϣ���Ϊ�жϻ��ڽ������ķ����б�����
				WorkingTask.this.cancel();
				//������Ĳ���mayInterruptIfRunningΪtrue�Ļ��������Thread.currentThread().interrupt()�ж����е��߳�
				//���������ǵ������У��д����жϵĻ����Ϳ���ֱ��ȡ��������
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
