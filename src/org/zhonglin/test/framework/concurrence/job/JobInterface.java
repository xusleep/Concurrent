package zhonglin.test.framework.concurrence.job;

public interface JobInterface {
	/**
	 * �˷���Ϊ���в�������ǰ��׼��
	 */
	void doBeforeJob();
	/**
	 * �˷������ڲ����߳������У����ɶ���߳�ͬʱ����
	 */
	void doConcurrentJob();
	/**
	 * �˷���Ϊ�����겢��������Ĵ��������ڲ��Ե�ʱ������ڴ˷������������ԱȽ�
	 */
	void doAfterJob();
}
