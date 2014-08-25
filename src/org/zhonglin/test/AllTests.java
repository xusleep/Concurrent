package zhonglin.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.JUnit4TestAdapter;

//This section declares all of the test classes in your program.
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestIncreased.class,  // Add test classes here.
	TestIncreased3.class,
	TestIncreased4.class,
	TestIncreased5.class
})

public class AllTests {

    // The suite() method is helpful when using JUnit 3 Test Runners or Ant.
    public static junit.framework.Test suite() 
    {
       return new JUnit4TestAdapter(AllTests.class);
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 10; i++)
		junit.textui.TestRunner.run (suite());
	}

}
