package zhonglin.test.process.communicate.pipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyTest {
	public static void main(String[] args) throws Exception {
		// 读取父进程输入流
		final BufferedReader bfr = new BufferedReader(
				new InputStreamReader(System.in));
		new Thread(
			new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						String strLine = null;
						try {
							strLine = bfr.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (strLine != null) {
							System.out.println("df" + strLine);// 这个地方的输出在子进程控制台是无法输出的，只可以在父进程获取子进程的输出
						} else {
							return;
						}
					}
				}
				
			}

		).start();
	}
}
