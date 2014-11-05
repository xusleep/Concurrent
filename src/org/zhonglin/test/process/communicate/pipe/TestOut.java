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
			// 注意这个地方如果关闭，则父进程只可以给子进程发送一次信息，如果这个地方开启close()则父进程给子进程不管发送大小多大的数据，子进程都可以返回
			// 如果这个地方close()不开启，则父进程给子进程发送数据累加到8192子进程才返回。
			// ops.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
