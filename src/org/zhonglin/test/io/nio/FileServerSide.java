package zhonglin.test.io.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class FileServerSide implements Runnable {
	private static final AtomicInteger count = new AtomicInteger(0);
	private ServerSocketChannel serverSocketChannel = null;  
	  
    private Selector            selector            = null;  
  
    public FileServerSide() {  
        try {  
            selector = Selector.open();  
            serverSocketChannel = ServerSocketChannel.open();  
            serverSocketChannel.configureBlocking(false);  
            serverSocketChannel.socket().bind(new InetSocketAddress(5001));  
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
                    	System.out.println("reading...");
                    	FileOutputStream fos = new FileOutputStream("E:\\Storage\\1.msi");
                    	FileChannel fileChannel = fos.getChannel();
                    	final SocketChannel sc = (SocketChannel)key.channel();
                    	long readCount = fileChannel.transferFrom(sc, 0, 1024 * 4);
                    	long totalCount = readCount;
                    	while(readCount >= 0){
                    		readCount = fileChannel.transferFrom(sc, totalCount, 1024 * 4);
                    		totalCount = totalCount + readCount;
                    		if(255557632 == totalCount)
                    			break;
                    	}
                    	fileChannel.close();
                        fos.close();
                        key.cancel();
                    }
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	} 
	
    public void write(SocketChannel sc) throws IOException {  
    	try {
			Thread.sleep(50);
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
        new Thread(new FileServerSide()).start();  
    }  
}
