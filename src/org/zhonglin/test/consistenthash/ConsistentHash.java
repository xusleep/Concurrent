package zhonglin.test.consistenthash;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/***
 * 一致性hash算法 虚拟节点的方式
 * @author zhonxu
 *
 * @param <T>
 */
public class ConsistentHash<T> {

 private final HashFunction hashFunction;
 private final int numberOfReplicas;
 private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

 public ConsistentHash(HashFunction hashFunction, int numberOfReplicas,
     Collection<T> nodes) {
   this.hashFunction = hashFunction;
   this.numberOfReplicas = numberOfReplicas;

   for (T node : nodes) {
     add(node);
   }
 }

 public void add(T node) {
   for (int i = 0; i < numberOfReplicas; i++) {
     circle.put(hashFunction.hash(node.toString() + i), node);
   }
 }

 public void remove(T node) {
   for (int i = 0; i < numberOfReplicas; i++) {
     circle.remove(hashFunction.hash(node.toString() + i));
   }
 }

 public T get(Object key) {
   if (circle.isEmpty()) {
     return null;
   }
   int hash = hashFunction.hash(key);
   if (!circle.containsKey(hash)) {
     SortedMap<Integer, T> tailMap = circle.tailMap(hash);
     hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
   }
   return circle.get(hash);
 }

}