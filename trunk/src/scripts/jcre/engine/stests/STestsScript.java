package scripts.jcre.engine.stests;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

public class STestsScript extends TemplateScript {
	
	public STestsScript() {
		super();
		short x = (short)TestCase.X; // defaults:10
		short y = TestCase.Y;
		
		x = 50; //50 180 // 0;//^2 iteration on board
		y = 10; // off board
		
		int[] indices = new int[27];
		for (int i=0; i<indices.length; i++) indices[i]=i;
		
		int i = 0;
		// IF_SCMP_OK_REF
		addTestCase(new TestCase("IFSCMP_OK_REF", indices[++i], 
				x, y));
		// IF_SCMP_KO_REF
		addTestCase(new TestCase("IFSCMP_KO_REF", indices[++i], 
				x, y));
		// IF_NULL_OK_REF
		addTestCase(new TestCase("IFNULL_OK_REF", indices[++i], 
				x, y));
		// IF_NULL_KO_REF
		addTestCase(new TestCase("IFNULL_KO_REF", indices[++i], 
				x, y));
		// IFGE_OK
		addTestCase(new TestCase("IFGE_OK", "x", indices[++i], 
				x, y).setReference("IFSCMP_OK_REF"));
		// IFGE_KO
		addTestCase(new TestCase("IFGE_KO", "x", indices[++i], 
				x, y).setReference("IFSCMP_KO_REF"));
		// IFGT_OK
		addTestCase(new TestCase("IFGT_OK", "x", indices[++i], 
				x, y).setReference("IFSCMP_OK_REF"));
		// IFGT_KO
		addTestCase(new TestCase("IFGT_KO", "x", indices[++i], 
				x, y).setReference("IFSCMP_KO_REF"));
		// IFLE_OK
		addTestCase(new TestCase("IFLE_OK", "x", indices[++i], 
				x, y).setReference("IFSCMP_OK_REF"));
		// IFLE_KO
		addTestCase(new TestCase("IFLE_KO", "x", indices[++i], 
				x, y).setReference("IFSCMP_KO_REF"));
		// IFLT_OK
		addTestCase(new TestCase("IFLT_OK", "x", indices[++i], 
				x, y).setReference("IFSCMP_OK_REF"));
		// IFLT_KO
		addTestCase(new TestCase("IFLT_KO", "x", indices[++i], 
				x, y).setReference("IFSCMP_KO_REF"));
		// IFNULL_OK
		addTestCase(new TestCase("IFNULL_OK", "x", indices[++i], 
				x, y).setReference("IFNULL_OK_REF"));
		// IFNULL_KO
		addTestCase(new TestCase("IFNULL_KO", "x", indices[++i], 
				x, y).setReference("IFNULL_KO_REF"));
		// IF_SCMPGE_OK
		addTestCase(new TestCase("IFSCMPGE_OK", "x", indices[++i], 
				x, y).setReference("IFSCMP_OK_REF"));
		// IF_SCMPGE_KO
		addTestCase(new TestCase("IFSCMPGE_KO", "x", indices[++i], 
				x, y).setReference("IFSCMP_KO_REF"));
		// IF_SCMPGT_OK
		addTestCase(new TestCase("IFSCMPGT_OK", "x", indices[++i], 
				x, y).setReference("IFSCMP_OK_REF"));
		// IF_SCMPGT_KO
		addTestCase(new TestCase("IFSCMPGT_KO", "x", indices[++i], 
				x, y).setReference("IFSCMP_KO_REF"));
		// IF_SCMPLE_OK
		addTestCase(new TestCase("IFSCMPLE_OK", "x", indices[++i], 
				x, y).setReference("IFSCMP_OK_REF"));
		// IF_SCMPLE_KO
		addTestCase(new TestCase("IFSCMPLE_KO", "x", indices[++i], 
				x, y).setReference("IFSCMP_KO_REF"));
		// IF_SCMPLT_OK
		addTestCase(new TestCase("IFSCMPLT_OK", "x", indices[++i], 
				x, y).setReference("IFSCMP_OK_REF"));
		// IF_SCMPLT_KO
		addTestCase(new TestCase("IFSCMPLT_KO", "x", indices[++i], 
				x, y).setReference("IFSCMP_KO_REF"));
		// IF_SCMPNE_OK
		addTestCase(new TestCase("IFSCMPNE_OK", "x", indices[++i], 
				x, y).setReference("IFSCMP_OK_REF"));
		// IF_SCMPNE_KO
		addTestCase(new TestCase("IFSCMPNE_KO", "x", indices[++i], 
				x, y).setReference("IFSCMP_KO_REF"));
		// IF_SCMPEQ_OK
		addTestCase(new TestCase("IFSCMPEQ_OK", "x", indices[++i], 
				x, y).setReference("IFSCMP_OK_REF"));
		// IF_SCMPEQ_KO
		addTestCase(new TestCase("IFSCMPEQ_KO", "x", indices[++i], 
				x, y).setReference("IFSCMP_KO_REF"));
		
	}
	
}
