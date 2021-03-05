package scripts.jcapi.javacard_framework.util.makeshort;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

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



/**
 * This class is dedicated to measurements of the <tt>Util.setShort</tt> method.
 */
public class MakeShortScript extends TemplateScript{
	
	/** The default number of executions to be performed on-card for a test case. */
	private final static int ON_CARD = 44;
	/** The default number of executions to be performed off-card for a test case. */
	private final static int OFF_CARD = TestCase.Y;

	/**
	 * Creates a new test script and builds the test cases.
	 */
	public MakeShortScript() {
		//Overhead test
		addTestCase(new TestCase("makeShort_ref",1,ON_CARD,OFF_CARD,0)
		    .setCalibration("makeShort"));
		
		//card test 
		addTestCase(new TestCase("makeShort",2,ON_CARD,OFF_CARD,0)
		    .setReference("makeShort_ref")
			.forCalibration().setBenched("x-POP"));
	}
	
}
