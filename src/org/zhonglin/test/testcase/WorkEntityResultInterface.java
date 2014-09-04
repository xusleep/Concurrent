package zhonglin.test.testcase;

import java.util.concurrent.ConcurrentHashMap;

public interface WorkEntityResultInterface {
	boolean isSucessful();
	Exception getException();
	String getDescription();
	ConcurrentHashMap hashmap = new ConcurrentHashMap();
}
