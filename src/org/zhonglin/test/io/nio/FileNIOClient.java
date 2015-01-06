package zhonglin.test.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NIO客户端
 * 
 * @author 小路
 */
public class FileNIOClient {
	public static AtomicInteger aint = new AtomicInteger(0);
	// 通道管理器
	private Selector selector;
	private boolean exitFlag = false;

	/**
	 * 获得一个Socket通道，并对该通道做一些初始化的工作
	 * 
	 * @param ip
	 *            连接的服务器的ip
	 * @param port
	 *            连接的服务器的端口号
	 * @throws IOException
	 */
	public void initClient(String ip, int port) throws IOException {
		// 获得一个Socket通道
		SocketChannel channel = SocketChannel.open();
		// 设置通道为非阻塞
		channel.configureBlocking(false);
		// 获得一个通道管理器
		this.selector = Selector.open();

		// 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
		// 用channel.finishConnect();才能完成连接
		channel.connect(new InetSocketAddress(ip, port));
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
		channel.register(selector, SelectionKey.OP_CONNECT);
	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void listen() throws IOException {
		// 轮询访问selector
		while (true) {
			if (exitFlag)
				break;
			selector.select();
			// 获得selector中选中的项的迭代器
			Iterator ite = this.selector.selectedKeys().iterator();
			while (ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				// 删除已选的key,以防重复处理
				ite.remove();
				// 连接事件发生
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					// 如果正在连接，则完成连接
					if (channel.isConnectionPending()) {
						channel.finishConnect();

					}
					// 设置成非阻塞
					channel.configureBlocking(false);
					File file = new File(
							"E:\\OSGI\\mysql-installer-community-5.6.19.0.msi");
					FileInputStream fis = new FileInputStream(file);
					long offset = 0;
					long totalBytes = file.length();
					System.out.println("总共需要传送 " + totalBytes + " 字节");
					FileChannel fileChannel = fis.getChannel();
					while (offset < totalBytes) {
						long buffSize = 1024 * 4; // 每次最多传4K
						if (totalBytes - offset < buffSize) {
							buffSize = totalBytes - offset;
						}
						long transferred = fileChannel.transferTo(offset,
								buffSize, channel);
						if (transferred > 0) {
							offset += transferred;
							System.out.println("已经传送 " + offset + " 字节");
						}
					}
					fileChannel.close();
					fis.close();
					// 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
					channel.register(this.selector, SelectionKey.OP_READ);
					channel.shutdownOutput();
					channel.close();
					// 获得了可读的事件
				} else if (key.isReadable()) {
					read(key);
					key.cancel();
					exitFlag = true;
				}

			}

		}
	}

	/**
	 * 处理读取服务端发来的信息 的事件
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		// 和服务端的read方法一样
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int count = channel.read(buffer);
		if (count == -1) {
			channel.close();
			return;
		}
		System.out.println("接收到服务器端（"
				+ channel.socket().getInetAddress().getHostAddress() + "）的消息："
				+ new String(buffer.array()));
	}

	/**
	 * 启动客户端测试
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileNIOClient client = new FileNIOClient();
		client.initClient("localhost", 5001);
		client.listen();
	}

}