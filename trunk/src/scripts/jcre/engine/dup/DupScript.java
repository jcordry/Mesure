/**
 * 
 */
package scripts.jcre.engine.dup;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

/**
 * @author cordry
 *
 */
public class DupScript extends TemplateScript {
	
	//static final long serialVersionUID = 0;

	/**
	 * Creates a new test script and builds the test cases.
	 */
	public DupScript() {
		short x = (short)TestCase.X; // defaults:10
		short y = 1; //TestCase.Y;

		
		x = 50; //50 180 // 0;//^2 iteration on board
		y = 10; // off board

		int[] indices = new int[16];
		for (int i=0; i<indices.length; i++) indices[i]=i;

		
		/** 
		 * Flag activating ALL tests.
		 * */
		//final boolean testALL = true;
		
		/** 
		 * Flags activating parts of the following test suite.
		 **/
		final boolean testDUP2 = true;
		final boolean testDUPX = true;
		final boolean testDUP = true;

		
		/** 
		 * DUP2 test suite 
		 */
		if (testDUP2 || testDUPX || testDUP) {
			// DUP REFERENCE
			addTestCase(new TestCase("DUPN_REF", indices[1], 
					x, y));
		}
		
		if (testDUP2) {
			// DUP2
			addTestCase(new TestCase("DUP2", "x", indices[2], 
					x, y).setReference("DUPN_REF"));
		}
		
		/**
		 * DUPX test suite 
		 */
		if (testDUPX) {
			// DUP_X 10
			addTestCase(new TestCase("DUPX_10", "x", indices[3], 
					x, y).setReference("DUPN_REF"));
			// DUP_X 11
			addTestCase(new TestCase("DUPX_11", "x", indices[4], 
					x, y).setReference("DUPN_REF"));
			// DUP_X 12
			addTestCase(new TestCase("DUPX_12", "x", indices[5], 
					x, y).setReference("DUPN_REF"));
			// DUP_X 13
			addTestCase(new TestCase("DUPX_13", "x", indices[6], 
					x, y).setReference("DUPN_REF"));
			// DUP_X 20
			addTestCase(new TestCase("DUPX_20", "x", indices[7], 
					x, y).setReference("DUPN_REF"));
			// DUP_X 22
			addTestCase(new TestCase("DUPX_22", "x", indices[8], 
					x, y).setReference("DUPN_REF"));
			// DUP_X 23
			addTestCase(new TestCase("DUPX_23", "x", indices[9], 
					x, y).setReference("DUPN_REF"));
			// DUP_X 24
			addTestCase(new TestCase("DUPX_24", "x", indices[10], 
					x, y).setReference("DUPN_REF"));
		}
		
		/**
		 * DUP test suite
		 */
		if (testDUP) {
		  // DUP
		  addTestCase(new TestCase("DUP", "x", indices[11], 
		            x, y).setReference("DUPN_REF"));
		}

	}
	

}
