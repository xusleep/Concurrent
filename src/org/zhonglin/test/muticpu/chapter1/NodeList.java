package zhonglin.test.muticpu.chapter1;

//���˽ڵ����ǿ�ʼ��������   ��Ҫ˼�����ýڵ��ǰָ����ָ��ǰһ���ڵ㣬��ָ����ָ���һ���ڵ㣬����ѭ������ֻ��Ҫ����β�ڵ��Լӵ�������
public class NodeList {
	/**
	 * ����һ���ڵ��� ��Ϊ�������ɵ�Ԫ ���ｫ�ڵ㶨��Ϊһ��ֵ�� ��������������Ҫ��Ϊ�˴�����ͬ����Ĳ�ͬ���� ��������������ʱ ֻ��Ҫʹ��
	 * ����Ӧ�����е�һ�����ü���������Ҫ ��Ȼ�����ڴ��Ż�����Ŀ��ǿ������´���һ��ֻ����һ��ֵ���һ��������Ľڵ���
	 * ������˫������ʱ�����������������
	 * **/

	private Node head;// ���ڼ�¼�׽ڵ�
	private int length;

	public NodeList() {

	}
	
	

	public Node getHead() {
		return head;
	}



	public void setHead(Node head) {
		this.head = head;
	}



	/**
	 * ѭ������Ĵ��� ѭ������Ĵ���ֻ��Ҫ����ѭ����������һ���ڵ��������ָ���׽ڵ㼴�� �����ʵ�� ���˵��
	 * �������������ʱ������������Ҫ��ס��������׽ڵ� ������һ������ָ���������׽ڵ�
	 * 
	 * ����ԭ�� �����ݴ�������ֵ���һ��forѭ����������Ҫ�Ľڵ� ����ͷ�ڵ��ʱ��ͷ���ĵ�ַ���� head ��ѭ�������ж���һ����ʱNode������
	 * ���ڼ�סǰһ���ڵ������ ��������һ���ڵ㴴���ɹ���ʱ�� ���ýڵ�����ø���ǰһ���ڵ�������� ͬ�»���Ҫ����ǰ�ڵ�����ø��������ʱ�ڵ�
	 * �Ա������һ�ڵ�֮�� �� previous.next=current previous=current ��Ҫ�ر�ָ�����ǵ�������һ���ڵ�ʱ
	 * ����ͷ���û��ǰһ���ڵ� ����ֻ��Ҫ�� ��ǰ�ڵ�ĵ�ַ���� ��ʱ�ڵ㼴�� �� previous=current ��ǰ�ڵ�Ϊ���һ���ڵ�ʱ
	 * ����next��ָ���׽ڵ� �Ա�ʵ��ѭ�� �� current.next=head;
	 * 
	 * */
	public Node createDoubleList(int n) {
		Node previous = null;// ��¼ǰһ���ڵ�
		Node current = null;// ��¼��ǰ�ڵ�
		for (int i = 0; i < n; i++) {
			current = new Node(i + 1);
			if (head == null)
				head = current;
			if (previous != null)// ˵����ʱ�������׽ڵ�
			{
				previous.setNext(current);
				current.setPrevious(previous);
				previous = current.getPrevious();
			}
			previous = current;
			// Ϊ��ʵ��ѭ������Ҫ��Ҫ�����ж�
			if (i == n - 1) {// ˵�����´����������һ���ڵ�
				current.setNext(head);// ���� ������β������ O(��_��)O����~
				head.setPrevious(current);
			}
		}
		length = n;
		return head;
	}

	/**
	 * ��������������ı��� ������ѭ��������һֱ������ȥ Ҫ�����ж��Ա����� �жϵı�־�ǵ�ǰָ���ٴ����׽ڵ��ָ�����ʱ ����
	 * 
	 * */
	public void iteratorList() {
		Node current = null;
		current = head;
		while (current != null) {
			System.out.print(current.getE() + " --> ");
			current = current.getNext();
			if (current == head) {
				break;
			}
		}
		System.out.println();
	}

	// ɾ��˫��ѭ������Ľڵ�
	public boolean deleteNode(Node node) {
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		length--;
		return true;

	}

	// ��ȡ����
	public int lenth() {
		return length;
	}
	
	
}
