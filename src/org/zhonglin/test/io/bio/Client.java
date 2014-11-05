package zhonglin.test.io.bio;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		final Socket client;
		try {
			client = new Socket("localhost", 8881);
			//�ͻ���д���߳�
			new Thread(
				new Runnable(){
					@Override
					public void run() {
						DataOutputStream dos = null;
						BufferedReader br = null;
						try
						{
							// TODO Auto-generated method stub
							while (true) {
								// ȡ�������
								dos = new DataOutputStream(
										client.getOutputStream());
								System.out.print("client side������: \t");
								// ����¼��
								br = new BufferedReader(new InputStreamReader(
										System.in));
								String send = br.readLine();
								// ��������
								dos.writeUTF(send);
							}
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							try {
								if (dos != null) {
									dos.close();
								}
								if (br != null) {
									br.close();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			).start();
			
			//�ͻ��� �����߳�
			new Thread(
				new Runnable(){
					@Override
					public void run() {
				        DataInputStream dis = null;  
						try
						{
							// TODO Auto-generated method stub
				            while(true){  
				                //��ȡ������������    
				                dis = new DataInputStream(client.getInputStream());  
				                String receive = dis.readUTF();     
				                System.out.println("client side ����: " + receive);    
				            }  
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
				            try {  
				                if(dis != null){  
				                    dis.close();  
				                }  
				            } catch (IOException e) {  
				                e.printStackTrace();  
				            }  
						}
					}
				}
			).start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		
	}
}
