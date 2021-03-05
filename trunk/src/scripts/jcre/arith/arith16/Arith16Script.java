package scripts.jcre.arith.arith16;

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

import scripts.templates.TestCase;
import scripts.templates.TemplateScript;

/**
 * This class should be used to measure the 16 bits arithmetics
 */
public class Arith16Script extends TemplateScript {
	
	/**
	* Creates a new test script and builds the test cases.
	*/
	public Arith16Script() {
		int i = 0;
		short x1 = 50;//(short)TestCase.X;
		
		addTestCase(new TestCase("ARITH16_REF", 1, x1, TestCase.Y));
		addTestCase(new TestCase("SADD", "x*16", 2, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SSUB", "x*16", 3, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SMUL", "x*16", 4, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SDIV", "x*16", 5, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SDIV_0", "x*16", 6, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SREM", "x*16", 7, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SREM_0", "x*16", 8, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SNEG", "x*16", 9, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SSHL", "x*16", 10, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SSHR", "x*16", 11, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SOR", "x*16", 12, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SAND", "x*16", 13, x1, TestCase.Y).setReference("ARITH16_REF"));
		addTestCase(new TestCase("SXOR", "x*16", 14, x1, TestCase.Y).setReference("ARITH16_REF"));
		
	}
	
	
}