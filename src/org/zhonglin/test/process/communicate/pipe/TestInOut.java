package zhonglin.test.process.communicate.pipe;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class TestInOut implements Runnable{
	Process p = null;
	public TestInOut() throws Exception {
		p = Runtime.getRuntime().exec("java -classpath C:\\Users\\zhonxu\\git\\Distribution\\Distribution\\bin\\ zhonglin.process.communicate.pipe.MyTest");  //启动子进程，这个程序不存在会出现问题！
		new Thread(this).start();		      	
	}
	public void send() throws Exception {
		int i=0;
		try{
			OutputStream ops = p.getOutputStream();  //发送，首先要连接一个输出流对象		
			while(true) {
				i++;
				System.out.println("" + i);
				if(i > 1000) {
					break;		
				}
				ops.write(("help\r\n"+ (new Integer(i)).toString()).getBytes());  //写入字符串
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			}
	}
	public void run() {	
		try{
			InputStream in = p.getInputStream();
			BufferedReader bfr = new BufferedReader(new InputStreamReader(in));
			while(true) {
				String strLine = bfr.readLine();
				System.out.println(strLine);
				}	   
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		public static void main(String args[]) throws Exception {
			TestInOut ts = new TestInOut();		
			//创建对象时，子进程就启动了，接收线程启动了。	ts.send();}
	}
}
