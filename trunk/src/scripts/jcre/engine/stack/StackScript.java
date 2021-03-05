/*
 * Copyright (c) 2006 Mesure Project
 * 
 * This software is a computer program whose purpose is to measure 
 * the performances of Java Card platforms.
 *
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 * 
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 * 
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

/*
 * $HeadURL: svn+ssh://meunier@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 8 decembre 2006
 * Author: POPS 
 * $LastChangedRevision: 35 $
 * $LastChangedDate: 2006-09-29 17:10:44 +0200 (ven., 29 sept. 2006) $
 * $LastChangedBy: meunier $
 */

package scripts.jcre.engine.stack;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

/**
 * 
 * Script benchmarking stack related bytecodes
 */
public class StackScript extends TemplateScript {

	/**
	 * Creates a new test script and builds the test cases.
	 */
	public StackScript() {
		short x = TestCase.X; // defaults:10
		short y = TestCase.Y;

		
		x = 180; //50 180 // 0;//^2 iteration on board
		//y = 10; // off board

		int[] indices = new int[64];
		for (int i=0; i<indices.length; i++) indices[i]=i;
		
		/** 
		 * Flag activating ALL tests.
		 * */
		final boolean testALL = true;
		
		/** 
		 * Flags activating parts of the following test suite.
		 **/
		final boolean testSLOAD = false | testALL;
		final boolean testALOAD = false| testALL;
		final boolean testSCONST = false | testALL;
		final boolean testBSPUSH = true | testALL;
		final boolean testSSPUSH = false | testALL;
		final boolean testACONST = false | testALL;
		final boolean testSSTORE = false | testALL;
		final boolean testDUP = false | testALL;
		final boolean testPOP = false | testALL;
		
		
		/** 
		 * SLOAD test suite 
		 */
		if (testSLOAD) {
			// SLOAD REFERENCE
			addTestCase(new TestCase("SLOAD_REF", indices[1], 
					x, y));
			// SLOAD_0
			addTestCase(new TestCase("SLOAD0_0", "x", indices[2], 
					x, y).setReference("SLOAD_REF"));
			// SLOAD_0 * 2
			addTestCase(new TestCase("SLOAD0_00", "x*2", indices[3], 
					x, y).setReference("SLOAD_REF"));
			// SLOAD_0 * 3
			addTestCase(new TestCase("SLOAD0_000", "x*3", indices[4], 
					x, y).setReference("SLOAD_REF"));
			// SLOAD_1
			addTestCase(new TestCase("SLOAD1", "x", indices[5], 
					x, y).setReference("SLOAD_REF"));
			// SLOAD_2
			addTestCase(new TestCase("SLOAD2", "x", indices[6], 
					x, y).setReference("SLOAD_REF"));
			// SLOAD_3
			addTestCase(new TestCase("SLOAD3", "x", indices[7], 
					x, y).setReference("SLOAD_REF"));
			// SLOAD 4
			addTestCase(new TestCase("SLOAD_4", "x", indices[8], 
					x, y).setReference("SLOAD_REF"));
			// SLOAD 5
			addTestCase(new TestCase("SLOAD_5", "x", indices[9], 
					x, y).setReference("SLOAD_REF"));
		}
		
		/**
		 * ALOAD test suite 
		 */
		if (testALOAD) {
			// ALOAD REFERENCE
			addTestCase(new TestCase("ALOAD_REF", indices[10], 
					x, y));
			// ALOAD_0
			addTestCase(new TestCase("ALOAD0_0", "x", indices[11], 
					x, y).setReference("ALOAD_REF"));
			// ALOAD_0 * 2
			addTestCase(new TestCase("ALOAD0_00", "x*2", indices[12], 
					x, y).setReference("ALOAD_REF"));
			// ALOAD_0 * 3
			addTestCase(new TestCase("ALOAD0_000", "x*3", indices[13], 
					x, y).setReference("ALOAD_REF"));
			// ALOAD_1
			addTestCase(new TestCase("ALOAD1", "x", indices[14], 
					x, y).setReference("ALOAD_REF"));
			// ALOAD_2
			addTestCase(new TestCase("ALOAD2", "x", indices[15], 
					x, y).setReference("ALOAD_REF"));
			// ALOAD_3
			addTestCase(new TestCase("ALOAD3", "x", indices[16], 
					x, y).setReference("ALOAD_REF"));
			// ALOAD 4
			addTestCase(new TestCase("ALOAD_4", "x", indices[17], 
					x, y).setReference("ALOAD_REF"));
			// ALOAD 5
			addTestCase(new TestCase("ALOAD_5", "x", indices[18], 
					x, y).setReference("ALOAD_REF"));
		}

		/**
		 * SCONST test suite 
		 */
		if (testSCONST) {
			// SCONST REFERENCE
			addTestCase(new TestCase("SCONST_REF", indices[19], 
					x, y));
			// SCONST_0
			addTestCase(new TestCase("SCONST0_0", "x", indices[20], 
					x, y).setReference("SCONST_REF"));
			// SCONST_0 * 2
			addTestCase(new TestCase("SCONST0_00", "x*2", indices[21], 
					x, y).setReference("SCONST_REF"));
			// SCONST_0 * 3
			addTestCase(new TestCase("SCONST0_000", "x*3", indices[22], 
					x, y).setReference("SCONST_REF"));
			// SCONST_1
			addTestCase(new TestCase("SCONST1", "x", indices[23], 
					x, y).setReference("SCONST_REF"));
			// SCONST_2
			addTestCase(new TestCase("SCONST2", "x", indices[24], 
					x, y).setReference("SCONST_REF"));
			// SCONST_3
			addTestCase(new TestCase("SCONST3", "x", indices[25], 
					x, y).setReference("SCONST_REF"));
			// SCONST_4
			addTestCase(new TestCase("SCONST4", "x", indices[26], 
					x, y).setReference("SCONST_REF"));
			// SCONST_5
			addTestCase(new TestCase("SCONST5", "x", indices[27], 
					x, y).setReference("SCONST_REF"));
		}

		/** 
		 * BSPUSH test suite
		 */
		if (testBSPUSH) {
			// BSPUSH REFERENCE
			addTestCase(new TestCase("BSPUSH_REF", indices[28], 
					x, y));
			// BSPUSH 0
			addTestCase(new TestCase("BSPUSH_0", "x", indices[29], 
					x, y).setReference("BSPUSH_REF"));
			// BSPUSH 0 * 2
			addTestCase(new TestCase("BSPUSH_00", "x*2", indices[30], 
					x, y).setReference("BSPUSH_REF"));
			// BSPUSH 0 * 3
			addTestCase(new TestCase("BSPUSH_000", "x*3", indices[31], 
					x, y).setReference("BSPUSH_REF"));
			// BSPUSH -128
			addTestCase(new TestCase("BSPUSH_-128", "x", indices[32], 
					x, y).setReference("BSPUSH_REF"));
			// BSPUSH 127
			addTestCase(new TestCase("BSPUSH_127", "x", indices[33], 
					x, y).setReference("BSPUSH_REF"));
		}
		
		/** 
		 * SSPUSH test suite 
		 */
		if (testSSPUSH) {
			// SSPUSH REFERENCE
			addTestCase(new TestCase("SSPUSH_REF", indices[34], 
					x, y));
			// SSPUSH
			addTestCase(new TestCase("SSPUSH_0", "x", indices[35], 
					x, y).setReference("SSPUSH_REF"));
			// SSPUSH * 2
			addTestCase(new TestCase("SSPUSH_00", "x*2", indices[36], 
					x, y).setReference("SSPUSH_REF"));
			// SSPUSH * 3
			addTestCase(new TestCase("SSPUSH_000", "x*3", indices[37], 
					x, y).setReference("SSPUSH_REF"));
		}
		
		/**
		 * ACONST suite
		 */
		if (testACONST) {
			// ACONST REFERENCE
			addTestCase(new TestCase("ACONSTNULL_REF", indices[38], 
					x, y));
			// ACONST_NULL
			addTestCase(new TestCase("ACONSTNULL_0", "x", indices[39], 
					x, y).setReference("ACONSTNULL_REF"));
			// ACONST_NULL  * 2
			addTestCase(new TestCase("ACONSTNULL_00", "x*2", indices[40], 
					x, y).setReference("ACONSTNULL_REF"));
			// ACONST_NULL * 3
			addTestCase(new TestCase("ACONSTNULL_000", "x*3", indices[41], 
					x, y).setReference("ACONSTNULL_REF"));
		}
		
		/**
		 * SSTORE suite 
		 */
		if (testSSTORE) {
			// SSTORE REFERENCE
			addTestCase(new TestCase("SSTORE_REF", indices[42], 
					x, y));
			// SSTORE_0
			addTestCase(new TestCase("SSTORE0", "x+SLOAD1", indices[43], 
					x, y).setReference("SSTORE_REF"));
			// SSTORE_1
			addTestCase(new TestCase("SSTORE1", "x+SLOAD0", indices[44], 
					x, y).setReference("SSTORE_REF"));
			// SSTORE_2
			addTestCase(new TestCase("SSTORE2", "x+SLOAD0", indices[45], 
					x, y).setReference("SSTORE_REF"));
			// SSTORE_3
			addTestCase(new TestCase("SSTORE3", "x+SLOAD0", indices[46], 
					x, y).setReference("SSTORE_REF"));
			// SSTORE 4
			addTestCase(new TestCase("SSTORE_4", "x+SLOAD0", indices[47], 
					x, y).setReference("SSTORE_REF"));
			// SSTORE 5
			addTestCase(new TestCase("SSTORE_5", "x+SLOAD0", indices[48], 
					x, y).setReference("SSTORE_REF"));
		}
 		
		/**
		 * DUP suite 
		 */
		if (testDUP) {
			// DUP REFERENCE
			addTestCase(new TestCase("DUP_REF", indices[49], 
					x, y));
			// DUP
			addTestCase(new TestCase("DUP", "x+SSTORE0", indices[50], 
					x, y).setReference("DUP_REF"));
		}

		/**
		 * POP suite
		 */
		if (testPOP) {
			// POP REFERENCE
			addTestCase(new TestCase("POP_REF", "x", indices[51], 
					x, y));
			// POP
			addTestCase(new TestCase("POP", "x+2*SLOAD0", indices[53], 
					x, y).setReference("POP_REF"));
		}
	}
}
