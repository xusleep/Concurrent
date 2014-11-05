package zhonglin.test.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import zhonglin.test.io.nio.Reactor.Acceptor;

public class ServerSide implements Runnable {
	private static final AtomicInteger count = new AtomicInteger(0);
	private ServerSocketChannel serverSocketChannel = null;  
	  
    private Selector            selector            = null;  
  
    public ServerSide() {  
        try {  
            selector = Selector.open();  
            serverSocketChannel = ServerSocketChannel.open();  
            serverSocketChannel.configureBlocking(false);  
            serverSocketChannel.socket().bind(new InetSocketAddress(8881));  
            SelectionKey selectionKey = serverSocketChannel.register(selector,  
                SelectionKey.OP_ACCEPT);  
            System.out.println("服务器启动正常!");  
        } catch (IOException e) {  
            System.out.println("启动服务器时出现异常!");  
            e.printStackTrace();  
        }  
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
        while (true) {  
            try {  
                selector.select();  
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();  
                while (iter.hasNext()) {  
                    SelectionKey key = iter.next();    
                    iter.remove(); 
                    if(key.isAcceptable()){
                    	ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                		SocketChannel sc = serverSocketChannel.accept();
        				sc.configureBlocking(false);
        				sc.register(this.selector, SelectionKey.OP_READ);
                    }
                    else if(key.isReadable()){
                    	String content = "Hello World! count = " + count.incrementAndGet(); 
                    	final SocketChannel sc = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);  
                        sc.read(buffer);  
                        System.out.println("接收到来自客户端（" + sc.socket().getInetAddress().getHostAddress()  
                                           + "）的消息：" + new String(buffer.array()));
                        key.cancel();
                        new Thread(new Runnable(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {
									write(sc);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
                        	
                        }).start();
                    }
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	} 
	
    public void write(SocketChannel sc) throws IOException {  
    	try {
			Thread.sleep(50000);
			System.out.println("正在写入...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String content = "Hello World! count = " + count.incrementAndGet(); 
        ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());  
        sc.write(buffer);  
        sc.close();
    }  
    public static void main(String[] args) {  
        new Thread(new ServerSide()).start();  
    }  
}
