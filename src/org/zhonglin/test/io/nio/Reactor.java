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
  
public class Reactor implements Runnable {  
    private ServerSocketChannel serverSocketChannel = null;  
  
    private Selector            selector            = null;  
  
    public Reactor() {  
        try {  
            selector = Selector.open();  
            serverSocketChannel = ServerSocketChannel.open();  
            serverSocketChannel.configureBlocking(false);  
            serverSocketChannel.socket().bind(new InetSocketAddress(8881));  
            SelectionKey selectionKey = serverSocketChannel.register(selector,  
                SelectionKey.OP_ACCEPT);  
            selectionKey.attach(new Acceptor());  
            System.out.println("��������������!");  
        } catch (IOException e) {  
            System.out.println("����������ʱ�����쳣!");  
            e.printStackTrace();  
        }  
    }  
  
    public void run() {  
        while (true) {  
            try {  
                selector.select();  
  
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();  
                while (iter.hasNext()) {  
                    SelectionKey selectionKey = iter.next();  
                    dispatch((Runnable) selectionKey.attachment());  
                    iter.remove();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    public void dispatch(Runnable runnable) {  
        if (runnable != null) {  
            runnable.run();  
        }  
    }  
  
    public static void main(String[] args) {  
        new Thread(new Reactor()).start();  
    }  
  
    class Acceptor implements Runnable {  
        public void run() {  
            try {  
                SocketChannel socketChannel = serverSocketChannel.accept();  
                if (socketChannel != null) {  
                    System.out.println("���յ����Կͻ��ˣ�"  
                                       + socketChannel.socket().getInetAddress().getHostAddress()  
                                       + "��������");  
                    new Handler(selector, socketChannel);  
                }  
  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  
  
class Handler implements Runnable {  
  
    private static final int READ_STATUS  = 1;  
  
    private static final int WRITE_STATUS = 2; 
    private static final AtomicInteger count = new AtomicInteger(0);
  
    private SocketChannel    socketChannel;  
  
    private SelectionKey     selectionKey;  
  
    private int              status       = READ_STATUS;  
  
    public Handler(Selector selector, SocketChannel socketChannel) {  
        this.socketChannel = socketChannel;  
        try {  
            socketChannel.configureBlocking(false);  
            selectionKey = socketChannel.register(selector, 0);  
            selectionKey.interestOps(SelectionKey.OP_READ);  
            selectionKey.attach(this);  
            selector.wakeup();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void run() {  
        try {  
            if (status == READ_STATUS) {  
                read();  
                selectionKey.interestOps(SelectionKey.OP_WRITE);  
                status = WRITE_STATUS;  
            } else if (status == WRITE_STATUS) {  
                process();  
                selectionKey.cancel(); 
                
                System.out.println("������������Ϣ�ɹ�!");  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void read() throws IOException {  
        ByteBuffer buffer = ByteBuffer.allocate(1024);  
        socketChannel.read(buffer);  
        System.out.println("���յ����Կͻ��ˣ�" + socketChannel.socket().getInetAddress().getHostAddress()  
                           + "������Ϣ��" + new String(buffer.array()));  
    }  
  
    public void process() throws IOException {  
        String content = "Hello World! count = " + count.incrementAndGet();  
        ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());  
        socketChannel.write(buffer);  
        //socketChannel.close();
    }  
}  
