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

package scripts.jcre.engine.ual;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

public class UALScript extends TemplateScript {
	/**
	 * Creates a new test script and builds the test cases.
	 */
	public UALScript() {
		short x = TestCase.X; // defaults:10
		short y = TestCase.Y;

		x = 180; //180; // 180 // 0;//^2 iteration on board
		y = 10; // off board

		int[] indices = new int[64];
		for (int i=0; i<indices.length; i++) indices[i]=i;
		
		/** 
		 * Flag activating ALL tests.
		 */
		final boolean testALL = true;
		
		/** 
		 * Flags activating part of test suite.
		 */
		final boolean testBinArith = true | testALL;
		final boolean testShift1Arith = false | testALL;
		final boolean testUnArith = false | testALL;

		/**
		 * BINARY ARITH OPS suite 
		 */
		if (testBinArith) {
			addTestCase(new TestCase("BINOPS_REF", indices[1], 
					x, y));
			addTestCase(new TestCase("SADD_2S", "x", indices[2], 
					x, y).setReference("BINOPS_REF"));
			addTestCase(new TestCase("SSUB_2S", "x", indices[3], 
					x, y).setReference("BINOPS_REF"));
			addTestCase(new TestCase("SMUL_2S", "x", indices[4], 
					x, y).setReference("BINOPS_REF"));
			addTestCase(new TestCase("SDIV_2S", "x", indices[5], 
					x, y).setReference("BINOPS_REF"));
			addTestCase(new TestCase("SREM_2S", "x", indices[6], 
					x, y).setReference("BINOPS_REF"));
		}

		/**
		 * BINARY SHIFT 1bit OPS suite 
		 */
		if (testShift1Arith) {
			addTestCase(new TestCase("SHIFT_1_REF", indices[7], 
					x, y));
			addTestCase(new TestCase("SSHL_1_2S", "x", indices[8], 
					x, y).setReference("SHIFT_1_REF"));
			addTestCase(new TestCase("SSHR_1_2S", "x", indices[9], 
					x, y).setReference("SHIFT_1_REF"));
		}
		
		/**
		 * UNARY OPS suite 
		 */
		if (testUnArith) {
			addTestCase(new TestCase("UNARYOPS_REF", indices[7], 
					x, y));
			addTestCase(new TestCase("SNEG_1S", "x", indices[8], 
					x, y).setReference("UNARYOPS_REF"));
		}
		
	}
}
