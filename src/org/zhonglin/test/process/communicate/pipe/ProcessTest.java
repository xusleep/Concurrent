package zhonglin.test.process.communicate.pipe;

import java.io.IOException;

public class ProcessTest {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		Process p = Runtime.getRuntime().exec(
				"java -classpath C:\\Users\\zhonxu\\git\\Distribution\\Distribution\\bin\\ zhonglin.process.communicate.pipe.MyTest");
		byte[] bs = new byte[500];
		while(p.getErrorStream().read(bs) > 0)
		{
			System.out.println(new String(bs));
		}
		

		StringBuilder sbuilder = new StringBuilder();
		for (int k = 0; k < 1; k++) {
			sbuilder.append("hello");
		}

		int outSize = 1;
		TestOut out[] = new TestOut[outSize];
		for (int i = 0; i < outSize; i++) {
			out[i] = new TestOut(p, sbuilder.toString().getBytes());
			new Thread(out[i]).start();
		}

		int inSize = 1;
		TestIn in[] = new TestIn[inSize];
		for (int j = 0; j < inSize; j++) {
			in[j] = new TestIn(p);
			new Thread(in[j]).start();
		}
	}
}
