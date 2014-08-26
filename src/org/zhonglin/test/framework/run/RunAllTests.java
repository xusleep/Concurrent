package zhonglin.test.framework.run;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import junit.extensions.RepeatedTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestResult;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import zhonglin.test.framework.printer.HTMLResultPrinter;
import zhonglin.test.framework.summary.SummaryTestResult;
import zhonglin.test.testincrease.IncreaseCountTestCase;

//This section declares all of the test classes in your program.
@RunWith(Suite.class)
@Suite.SuiteClasses({
// TestIncreased.class, // Add test classes here.
// TestIncreased3.class,
// TestIncreased4.class,
	IncreaseCountTestCase.class })
public class RunAllTests {

	private static final int REPEAT_TIMES = 10000;
	// The suite() method is helpful when using JUnit 3 Test Runners or Ant.
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RunAllTests.class);
	}
	
	public static junit.framework.Test repeatSuite() {
		return new RepeatedTest(suite(), REPEAT_TIMES);
	}

	public static void main(String[] args) {

		HTMLResultPrinter htmlreport;
		try {
			htmlreport = new HTMLResultPrinter(new PrintStream(
					new FileOutputStream("E:\\1.html")), REPEAT_TIMES);
			MyTestRunner runner = new MyTestRunner(htmlreport);
			TestResult tr = runner.doRun(repeatSuite());
			SummaryTestResult str = new SummaryTestResult(tr);
			str.executeSummary();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
