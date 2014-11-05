package zhonglin.test.process.communicate.pipe;

import java.io.IOException;
import java.io.OutputStream;

public class TestOut implements Runnable {
	private Process p = null;
	private byte[] b = null;

	public TestOut(Process process, byte byt[]) {
		p = process;
		b = byt;
	}

	@Override
	public void run() {
		try {
			OutputStream ops = p.getOutputStream();
			// System.out.println("out--"+b.length);
			ops.write(b);
			// ע������ط�����رգ��򸸽���ֻ���Ը��ӽ��̷���һ����Ϣ���������ط�����close()�򸸽��̸��ӽ��̲��ܷ��ʹ�С�������ݣ��ӽ��̶����Է���
			// �������ط�close()���������򸸽��̸��ӽ��̷��������ۼӵ�8192�ӽ��̲ŷ��ء�
			// ops.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
