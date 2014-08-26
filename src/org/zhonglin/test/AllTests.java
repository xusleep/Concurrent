package zhonglin.test;

import java.util.Enumeration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.extensions.RepeatedTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestFailure;
import junit.framework.TestResult;

//This section declares all of the test classes in your program.
@RunWith(Suite.class)
@Suite.SuiteClasses({
// TestIncreased.class, // Add test classes here.
// TestIncreased3.class,
// TestIncreased4.class,
TestIncreased.class })
public class AllTests {

	// The suite() method is helpful when using JUnit 3 Test Runners or Ant.
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			TestResult tr = junit.textui.TestRunner.run(new RepeatedTest(suite(), 100));
			
			SummaryTestResult str = new SummaryTestResult(tr);
			str.printSummary();
			System.out.println("failure count " + tr.failureCount());
			System.out.println("error count " + tr.errorCount());
	}


}
