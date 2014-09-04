package zhonglin.test.chapter5.exchanger;

import java.util.LinkedList;
import java.util.Queue;

public class DataBuffer {
	private Queue<String> data = new LinkedList<String>();
	private String id;
	
	public DataBuffer(String id){
		this.id = id;
	}
	
	public boolean isFull(){
		return data.size() == 10;
	}
	
	public boolean isEmpty(){
		return data.size() == 0;
	}
	
	public void addData(String s){
		if(isFull())
			return;
		System.out.println("adding data to buffer " + id);
		data.offer(s);
	}
	
	public void removeData(){
		if(isEmpty())
			return;
		System.out.println("removing data from buffer " + id);
		data.poll();
	}
}
