package zhonglin.test.chapter10.lock.deadlock;

import java.lang.management.LockInfo;  
import java.lang.management.ManagementFactory;  
import java.lang.management.ThreadInfo;  
import java.lang.management.ThreadMXBean;  
import java.util.HashMap;  
import java.util.Map;  
import java.util.concurrent.Executors;  
import java.util.concurrent.TimeUnit;  
  
public class ThreadDemo {  
  
    private static Object lock1 = new Object();  
    private static Object lock2 = new Object();  
  
    /**  
     * for test deadlock  
     * @param agrs  
     */  
    public static void main(String[] agrs) {  
        new Thread(new FightSchedule1()).start();  
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new FightSchedule(), 0, 10, TimeUnit.SECONDS);  
        new Thread(new FightSchedule2()).start();  
    }  
  
    static class FightSchedule2 implements Runnable {  
  
        public void run() {  
            try {  
                synchronized (lock2) {  
                    Thread.sleep(1000);  
                    synchronized (lock1) {  
                        Thread.sleep(1000);  
                    }  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    static class FightSchedule1 implements Runnable {  
  
        public void run() {  
            try {  
                synchronized (lock1) {  
                    Thread.sleep(1000);  
                    synchronized (lock2) {  
                        Thread.sleep(1000);  
                    }  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    static class FightSchedule implements Runnable {  
  
        public void run() {  
            StringBuilder sb = new StringBuilder();  
            try {  
                ThreadGroup group;  
                for (group = Thread.currentThread().getThreadGroup(); group.getParent() != null; group = group.getParent());  
                sb.append("totalThread:" + group.activeCount()).append('\n');  
                Thread[] threads = new Thread[group.activeCount()];  
                group.enumerate(threads);  
                Map<Long, Thread> threadMap = new HashMap<Long, Thread>();  
                for (Thread thread : threads) {  
                    if (thread != null) {  
                        threadMap.put(thread.getId(), thread);  
                    }  
                }  
                ThreadMXBean mxb = ManagementFactory.getThreadMXBean();  
                //此方法设计用于故障排除，不用于异常控制。它是一个开销很大的操作。   
                long[] moniterThreads = mxb.findMonitorDeadlockedThreads();  
                if (moniterThreads != null) {  
                    sb.append("moniterThreads:").append(moniterThreads.length).append('\n');  
                    ThreadInfo[] moniterThreadInfos = mxb.getThreadInfo(moniterThreads);  
                    for (ThreadInfo ti : moniterThreadInfos) {  
                        LockInfo li = ti.getLockInfo();  
                        Thread thread = threadMap.get(ti.getThreadId());  
                        if (thread != null) {  
                            ThreadGroup tg = thread.getThreadGroup();  
                            StackTraceElement[] stes = Thread.getAllStackTraces().get(thread);  
                            sb.append("threadGroup=").append((tg != null ? tg.getName() : ""));  
                            if (stes != null) {  
                                for (StackTraceElement ste : stes) {  
                                    sb.append(",").append(ste.toString());  
                                }  
                            }  
                        } else {  
                            sb.append("threadGroup=").append("thread is null?");  
                        }  
                        sb.append(",name=").append(thread.getName()).append(",lockClassName=").append(((li != null) ? li.getClassName() : "")).append(",state=").append(ti.getThreadState()).append('\n');  
                    }  
                }  
                long[] lockedThreads = mxb.findDeadlockedThreads();  
                if (lockedThreads != null) {  
                    sb.append("lockedThreads:").append(lockedThreads.length).append('\n');  
                    ThreadInfo[] lockThreadInfos = mxb.getThreadInfo(lockedThreads);  
                    for (ThreadInfo ti : lockThreadInfos) {  
                        LockInfo li = ti.getLockInfo();  
                        Thread thread = threadMap.get(ti.getThreadId());  
                        if (thread != null) {  
                            ThreadGroup tg = thread.getThreadGroup();  
                            StackTraceElement[] stes = Thread.getAllStackTraces().get(thread);  
                            sb.append("threadGroup=").append((tg != null ? tg.getName() : ""));  
                            if (stes != null) {  
                                for (StackTraceElement ste : stes) {  
                                    sb.append(",").append(ste.toString());  
                                }  
                            }  
                        } else {  
                            sb.append("threadGroup=").append("thread is null?");  
                        }  
                        sb.append(",name=").append(thread.getName()).append(",lockClassName=").append(((li != null) ? li.getClassName() : "")).append(",state=").append(ti.getThreadState()).append('\n');  
                    }  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            if (sb.length() > 0) {  
                System.out.println(sb.deleteCharAt(sb.length() - 1).toString());  
            }  
        }  
    }  
}  
