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
 * NIO�ͻ���
 * 
 * @author С·
 */
public class FileNIOClient {
	public static AtomicInteger aint = new AtomicInteger(0);
	// ͨ��������
	private Selector selector;
	private boolean exitFlag = false;

	/**
	 * ���һ��Socketͨ�������Ը�ͨ����һЩ��ʼ���Ĺ���
	 * 
	 * @param ip
	 *            ���ӵķ�������ip
	 * @param port
	 *            ���ӵķ������Ķ˿ں�
	 * @throws IOException
	 */
	public void initClient(String ip, int port) throws IOException {
		// ���һ��Socketͨ��
		SocketChannel channel = SocketChannel.open();
		// ����ͨ��Ϊ������
		channel.configureBlocking(false);
		// ���һ��ͨ��������
		this.selector = Selector.open();

		// �ͻ������ӷ�����,��ʵ����ִ�в�û��ʵ�����ӣ���Ҫ��listen���������е�
		// ��channel.finishConnect();�����������
		channel.connect(new InetSocketAddress(ip, port));
		// ��ͨ���������͸�ͨ���󶨣���Ϊ��ͨ��ע��SelectionKey.OP_CONNECT�¼���
		channel.register(selector, SelectionKey.OP_CONNECT);
	}

	/**
	 * ������ѯ�ķ�ʽ����selector���Ƿ�����Ҫ������¼�������У�����д���
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void listen() throws IOException {
		// ��ѯ����selector
		while (true) {
			if (exitFlag)
				break;
			selector.select();
			// ���selector��ѡ�е���ĵ�����
			Iterator ite = this.selector.selectedKeys().iterator();
			while (ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				// ɾ����ѡ��key,�Է��ظ�����
				ite.remove();
				// �����¼�����
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					// ����������ӣ����������
					if (channel.isConnectionPending()) {
						channel.finishConnect();

					}
					// ���óɷ�����
					channel.configureBlocking(false);
					File file = new File(
							"E:\\OSGI\\mysql-installer-community-5.6.19.0.msi");
					FileInputStream fis = new FileInputStream(file);
					long offset = 0;
					long totalBytes = file.length();
					System.out.println("�ܹ���Ҫ���� " + totalBytes + " �ֽ�");
					FileChannel fileChannel = fis.getChannel();
					while (offset < totalBytes) {
						long buffSize = 1024 * 4; // ÿ����ഫ4K
						if (totalBytes - offset < buffSize) {
							buffSize = totalBytes - offset;
						}
						long transferred = fileChannel.transferTo(offset,
								buffSize, channel);
						if (transferred > 0) {
							offset += transferred;
							System.out.println("�Ѿ����� " + offset + " �ֽ�");
						}
					}
					fileChannel.close();
					fis.close();
					// �ںͷ�������ӳɹ�֮��Ϊ�˿��Խ��յ�����˵���Ϣ����Ҫ��ͨ�����ö���Ȩ�ޡ�
					channel.register(this.selector, SelectionKey.OP_READ);
					channel.shutdownOutput();
					channel.close();
					// ����˿ɶ����¼�
				} else if (key.isReadable()) {
					read(key);
					key.cancel();
					exitFlag = true;
				}

			}

		}
	}

	/**
	 * �����ȡ����˷�������Ϣ ���¼�
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		// �ͷ���˵�read����һ��
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int count = channel.read(buffer);
		if (count == -1) {
			channel.close();
			return;
		}
		System.out.println("���յ��������ˣ�"
				+ channel.socket().getInetAddress().getHostAddress() + "������Ϣ��"
				+ new String(buffer.array()));
	}

	/**
	 * �����ͻ��˲���
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileNIOClient client = new FileNIOClient();
		client.initClient("localhost", 5001);
		client.listen();
	}

}