package scripts.jcre.memsetup;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;


public class MemSetupScript extends TemplateScript{

	/**
	 * Creates a new test script and builds the test cases.
	 */
	public MemSetupScript() {
		short x1 = (short)TestCase.X; // defaults:10
		short y1 = 1; //TestCase.Y;

		// set to dummy quick values
		x1 = 10; //50 180 // 0;//^2 iteration on board
		y1 = 1; // off board

		// needs at least 1 testcase
		addTestCase(new TestCase("dummy memory tc name 1",0,x1,y1));
		addTestCase(new TestCase("dummy memory tc name 2",0,x1,y1));
	}
}
