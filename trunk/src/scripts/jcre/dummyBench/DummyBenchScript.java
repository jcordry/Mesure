package scripts.jcre.dummyBench;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

/**
 * 
 * @author goyan
 * DummyBench script
 */
public class DummyBenchScript extends TemplateScript {

	/**
	* Creates a new test script and builds the test cases.
	*/
	public DummyBenchScript() {
	  int i = 1;
	  short x1 = (short)TestCase.X; // 10
	  short y1 = 1;
		
	  // The empty test cases used to computes overhead
	  addTestCase(new TestCase("dummy_ref",i,x1,y1));
	  addTestCase(new TestCase("dummy",++i,x1,y1).setReference("dummy_ref"));
	}
}
