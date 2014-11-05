package zhonglin.test.io.bio;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket objServerSocket = null;
		try {
			objServerSocket = new ServerSocket(8881);
			while (true) {
				final Socket objSocket = objServerSocket.accept();
				// �����߳�
				new Thread(new Runnable() {
					@Override
					public void run() {
						DataInputStream dis = null;
						try {
							while (true) {
								dis = new DataInputStream(
										objSocket.getInputStream());
								String buf = dis.readUTF();
								System.out.println("server side ���� " +  buf);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						finally{
							try {
								dis.close();
								if(objSocket != null)
									objSocket.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}).start();
				
				// д�����߳�
				new Thread(new Runnable() {
					@Override
					public void run() {
						DataOutputStream dos = null;  
				        BufferedReader br = null;  
						try {
							while (true) {
				                //��ͻ��˻ظ���Ϣ    
				                dos = new DataOutputStream(objSocket.getOutputStream());    
				                System.out.print("Server side ������:\t");    
				                // ����¼��    
				                br = new BufferedReader(new InputStreamReader(System.in));  
				                String send = br.readLine();    
				                //��������  
				                dos.writeUTF(send);    
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						finally{
				            try {  
				                if(dos != null){  
				                    dos.close();  
				                }  
				                if(br != null){  
				                    br.close();  
				                }  
				            } catch (IOException e) {  
				                e.printStackTrace();  
				            }  
						}
					}
				}).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
            try {  
                if(objServerSocket != null){  
                	objServerSocket.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
		}
	}

}
