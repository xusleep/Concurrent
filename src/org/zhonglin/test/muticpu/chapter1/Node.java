package zhonglin.test.muticpu.chapter1;

public class Node {
	//为了方便理解这里写成int类型 实际需求中将其改为Object类型即可  
	private int number;  
	private Node previous;  
	private Node next;  
	private Element e;
	  
	public Element getE() {
		return e;
	}

	public void setE(Element e) {
		this.e = e;
		this.e.setStoreNode(this);
	}

	public Node(int n){  
	this.number=n;  
	}  
	  
	public Node getNext() {  
	return next;  
	}  
	public void setNext(Node current) {  
	this.next = current;  
	}  
	public Node getPrevious() {  
	return previous;  
	}  
	public void setPrevious(Node previous) {  
	this.previous = previous;  
	}  
	public int getNumber() {  
	return number;  
	}  
	public void setNumber(int number) {  
	this.number = number;  
	} 
}
