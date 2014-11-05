package zhonglin.test.process.communicate.pipe;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class TestInOut implements Runnable{
	Process p = null;
	public TestInOut() throws Exception {
		p = Runtime.getRuntime().exec("java -classpath C:\\Users\\zhonxu\\git\\Distribution\\Distribution\\bin\\ zhonglin.process.communicate.pipe.MyTest");  //�����ӽ��̣�������򲻴��ڻ�������⣡
		new Thread(this).start();		      	
	}
	public void send() throws Exception {
		int i=0;
		try{
			OutputStream ops = p.getOutputStream();  //���ͣ�����Ҫ����һ�����������		
			while(true) {
				i++;
				System.out.println("" + i);
				if(i > 1000) {
					break;		
				}
				ops.write(("help\r\n"+ (new Integer(i)).toString()).getBytes());  //д���ַ���
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
			//��������ʱ���ӽ��̾������ˣ������߳������ˡ�	ts.send();}
	}
}
