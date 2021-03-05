package scripts.jcre.engine.jump;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

public class JumpScript extends TemplateScript {

	public JumpScript() {
		super();
		short x = (short)TestCase.X; // defaults:10
		short y = 1; //TestCase.Y;

		
		x = 50; //50 180 // 0;//^2 iteration on board
		y = 10; // off board

		int[] indices = new int[4];
		for (int i=0; i<indices.length; i++) indices[i]=i;

		
		/** 
		 * Flag activating ALL tests.
		 * */
		final boolean testALL = true;
		
		/** 
		 * GOTO TEST 
		 */
		// GOTO_REF
		addTestCase(new TestCase("GOTO_REF", indices[1], 
				x, y));
		// GOTO
		addTestCase(new TestCase("GOTO", "x", indices[2], 
				x, y).setReference("GOTO_REF"));
		// GOTO_W
		addTestCase(new TestCase("GOTOW", "x", indices[3], 
				x, y).setReference("GOTO_REF"));
	}
}
