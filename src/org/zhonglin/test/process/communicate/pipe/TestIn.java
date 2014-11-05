package zhonglin.test.process.communicate.pipe;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TestIn implements Runnable {

	private Process p = null;

	public TestIn(Process process) {
		p = process;
	}

	@Override
	public void run() {
		try {
			InputStream in = p.getInputStream();
			BufferedReader bfr = new BufferedReader(new InputStreamReader(in));
			String rd = bfr.readLine();
			if (rd != null) {
				System.out.println(rd);// ����ӽ��̷�����Ϣ(���ӽ����е�System.out.println()����)
			} else {
				return;
			}
			// ע������ط�������ر������ӽ��̵ķ�����Ϣ�޷���ȡ��������ر�ֻ�е��ӽ��̷����ֽ�Ϊ8192ʱ�ŷ��أ�Ϊʲô��8192����˵��.
			// bfr.close();
			// in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
