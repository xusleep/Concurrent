package zhonglin.test.chapter5.exchanger;

import java.util.concurrent.Exchanger;

public class TestExchanger {
	
	private DataBuffer initEmptyBuffer;
	private DataBuffer initFullBuffer;
	private Exchanger  objExchanger;
	
	public TestExchanger()
	{
		initEmptyBuffer = new DataBuffer("1");
		initFullBuffer = new DataBuffer("2");
		objExchanger = new Exchanger();
	}
	
	public void start()
	{
		Thread t1 = new Thread(new Runnable(){
			private DataBuffer currentDataBuffer = initEmptyBuffer;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < 100; i++)
				{
					currentDataBuffer.addData("the data");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(currentDataBuffer.isFull())
						try {
							System.out.println("waiting to exchange");
							currentDataBuffer = (DataBuffer) objExchanger.exchange(currentDataBuffer);
							System.out.println("finish exchange");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			private DataBuffer currentDataBuffer = initFullBuffer;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < 100; i++)
				{
					currentDataBuffer.removeData();
					if(currentDataBuffer.isEmpty())
						try {
							currentDataBuffer = (DataBuffer) objExchanger.exchange(currentDataBuffer);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		});
		t1.start();
		t2.start();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestExchanger texc = new TestExchanger();
		texc.start();
	}

}
