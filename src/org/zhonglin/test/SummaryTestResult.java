package zhonglin.test;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import junit.framework.TestFailure;
import junit.framework.TestResult;

public class SummaryTestResult {
	
	private HashMap hmFailures;
	private HashMap hmErrors;
	private TestResult tr;
	
	public SummaryTestResult(TestResult tr)
	{
		this.tr = tr;
		hmFailures = new HashMap(tr.failureCount());
		hmErrors = new HashMap(tr.errorCount());
	}
	
	public void printFailures()
	{
		printDefects(tr.failures());
	}
	
	public void printSummary()
	{
		Enumeration fail = tr.failures();
		for (int i = 1; fail.hasMoreElements(); i++)
		{
			TestFailure tf = (TestFailure) fail.nextElement();
			Object objCount = hmFailures.get(tf.failedTest().toString());
			if(objCount != null)
			{
				long lcount = (long)objCount;
				lcount = lcount + 1;
				hmFailures.put(tf.failedTest().toString(), lcount);
			}
			else
			{
				hmFailures.put(tf.failedTest().toString(), 1L);
			}
		}
		
		fail = tr.errors();
		for (int i = 1; fail.hasMoreElements(); i++)
		{
			TestFailure tf = (TestFailure) fail.nextElement();
			Object objCount = hmErrors.get(tf.failedTest().toString());
			if(objCount != null)
			{
				long lcount = (long)objCount;
				lcount = lcount + 1;
				hmErrors.put(tf.failedTest().toString(), lcount);
			}
			else
			{
				hmErrors.put(tf.failedTest().toString(), 1L);
			}
		}
		System.out.println("--------------------- Failure summary ---------------------");
		Iterator keysIterator = hmFailures.keySet().iterator();
		
		while(keysIterator.hasNext())
		{
			Object key = keysIterator.next();
			long value = (long)hmFailures.get(key);
			
			System.out.println(key + " : " + value);
		}
		System.out.println("-----------------------------------------------------------");
		
		System.out.println("--------------------- Error summary ---------------------");
		keysIterator = hmErrors.keySet().iterator();
		
		while(keysIterator.hasNext())
		{
			Object key = keysIterator.next();
			long value = (long)hmErrors.get(key);
			
			System.out.println(key + " : " + value);
		}
		System.out.println("-----------------------------------------------------------");
	}
	
	public void printErrosr()
	{
		printDefects(tr.errors());
	}
	
	public static void printDefects(Enumeration fail) {
		for (int i = 1; fail.hasMoreElements(); i++)
			printDefect((TestFailure) fail.nextElement(), i);
	}

	public static void printDefect(TestFailure fail, int count) {
		System.out.println(fail.failedTest().toString());
	}
	
	
	
}
