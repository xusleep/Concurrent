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
			//客户端写入线程
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
								// 取得输出流
								dos = new DataOutputStream(
										client.getOutputStream());
								System.out.print("client side请输入: \t");
								// 键盘录入
								br = new BufferedReader(new InputStreamReader(
										System.in));
								String send = br.readLine();
								// 发送数据
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
			
			//客户端 读入线程
			new Thread(
				new Runnable(){
					@Override
					public void run() {
				        DataInputStream dis = null;  
						try
						{
							// TODO Auto-generated method stub
				            while(true){  
				                //读取服务器端数据    
				                dis = new DataInputStream(client.getInputStream());  
				                String receive = dis.readUTF();     
				                System.out.println("client side 读入: " + receive);    
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
