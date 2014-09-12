package zhonglin.test.muticpu.chapter1;

public abstract class Element {
	public Node storeNode;

	public Node getStoreNode() {
		return storeNode;
	}

	public void setStoreNode(Node storeNode) {
		this.storeNode = storeNode;
	}
	
	public abstract boolean isPhilosopher();
}
