package zhonglin.test.muticpu.chapter1;

//有了节点我们开始构造链条   主要思想是让节点的前指针域指向前一个节点，后指针域指向后一个节点，构建循环链表只需要在首尾节点稍加调整即可
public class NodeList {
	/**
	 * 这是一个节点类 作为链表的组成单元 这里将节点定义为一个值域 和两个引用域主要是为了创建不同链表的不同需求 当创建单向链表时 只需要使用
	 * 两个应用域中的一个引用即可满足需要 当然出于内存优化分配的考虑可以重新创建一个只含有一个值域和一个引用域的节点类
	 * 当创建双向链表时本类亦可以满足需求
	 * **/

	private Node head;// 用于记录首节点
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
	 * 循环链表的创建 循环链表的创建只需要将非循环链表的最后一个节点的引用域指向首节点即可 下面的实现 会简单说明
	 * 创建单向链表的时候我们至少需要记住该链表的首节点 即得有一个引用指向该链表的首节点
	 * 
	 * 创建原理 ：根据传进来的值设计一个for循环创建所需要的节点 创建头节点的时候将头结点的地址赋给 head 在循环过程中定义一个临时Node的引用
	 * 用于记住前一个节点的引用 当创建下一个节点创建成功的时候 将该节点的引用赋给前一个节点的引用域 同事还需要将当前节点的引用赋给这个临时节点
	 * 以便添加下一节点之需 即 previous.next=current previous=current 需要特别指出的是当创建第一个节点时
	 * 由于头结点没有前一个节点 我们只需要将 当前节点的地址赋给 临时节点即可 即 previous=current 当前节点为最后一个节点时
	 * 将其next域指向首节点 以便实现循环 即 current.next=head;
	 * 
	 * */
	public Node createDoubleList(int n) {
		Node previous = null;// 记录前一个节点
		Node current = null;// 记录当前节点
		for (int i = 0; i < n; i++) {
			current = new Node(i + 1);
			if (head == null)
				head = current;
			if (previous != null)// 说明此时创建非首节点
			{
				previous.setNext(current);
				current.setPrevious(previous);
				previous = current.getPrevious();
			}
			previous = current;
			// 为了实现循环的需要需要加以判定
			if (i == n - 1) {// 说明此事创建的是最后一个节点
				current.setNext(head);// 至此 链表首尾相连了 O(∩_∩)O哈哈~
				head.setPrevious(current);
			}
		}
		length = n;
		return head;
	}

	/**
	 * 本方法用于链表的遍历 由于是循环链表不能一直迭代下去 要加以判定以便跳出 判断的标志是当前指针再次与首节点的指针相等时 跳出
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

	// 删除双向循环链表的节点
	public boolean deleteNode(Node node) {
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		length--;
		return true;

	}

	// 获取长度
	public int lenth() {
		return length;
	}
	
	
}
