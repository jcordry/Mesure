package scripts.jcre.engine.objects;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

public class ObjectScript extends TemplateScript {

	public ObjectScript() {
		super();
		short x = (short)TestCase.X; // defaults:10
		short y = TestCase.Y;

		x = 50; //50 180 // 0;//^2 iteration on board
		y = 10; // off board

		int[] indices = new int[11];
		for (int i=0; i<indices.length; i++) indices[i]=i;

		// GETSTATIC_REF
		addTestCase(new TestCase("GETSTATIC_REF", indices[1], 
				x, y));
		// GETSTATIC_A
		addTestCase(new TestCase("GETSTATICA", "x", indices[2], 
				x, y).setReference("GETSTATIC_REF"));
		// ALOAD
		addTestCase(new TestCase("ALOAD", "x", indices[3], 
				x, y).setReference("GETSTATIC_REF"));
		// ASTORE
		addTestCase(new TestCase("ASTORE", "x+GETSTATICA", indices[4], 
				x, y).setReference("GETSTATIC_REF"));
		// ASTORE_0
		addTestCase(new TestCase("ASTORE0", "x+GETSTATICA", indices[5], 
				x, y).setReference("GETSTATIC_REF"));
		// ASTORE_1
		addTestCase(new TestCase("ASTORE1", "x+GETSTATICA", indices[6], 
				x, y).setReference("GETSTATIC_REF"));
		// ASTORE_2
		addTestCase(new TestCase("ASTORE2", "x+GETSTATICA", indices[7], 
				x, y).setReference("GETSTATIC_REF"));
		// ASTORE_3
		addTestCase(new TestCase("ASTORE3", "x+GETSTATICA", indices[8], 
				x, y).setReference("GETSTATIC_REF"));
		// AALOAD_REF
		addTestCase(new TestCase("AALOAD_REF", indices[9], 
				x, y));
		// AALOAD
		addTestCase(new TestCase("AALOAD", "x", indices[10], 
				x, y).setReference("AALOAD_REF"));
		
	}

}
