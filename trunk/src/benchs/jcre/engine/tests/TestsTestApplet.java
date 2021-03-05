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
package benchs.jcre.engine.tests;

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

public class TestsTestApplet extends TemplateApplet {

	static short iterCount = 0;
	
	static byte nonNullArray[] = {1, 2};
	
	/**
	 * 
	 */
	TestsTestApplet() {
		super();
	}

	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new TestsTestApplet().register();
	}	    

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				/**
				 * ifeq REFERENCE test suite
				 */
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifeq_ref();
					}
				},
				// IFEQ_OK
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifeq_ok();
					}
				},
				// IFEQ_KO
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifeq_ko();
					}
				},
				// IFNEQ_OK
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifneq_ok();
					}
				},
				// IFNEQ_KO
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifneq_ko();
					}
				},
				// IFGT_OK
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifgt_ok();
					}
				},
				// IFGT_KO
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifgt_ko();
					}
				},
				// IFNONNULL_OK
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifnonnull_ok();
					}
				},
				// IFGT_KO
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifnonnull_ko();
					}
				},
				// IFEQ_W_REF
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifeq_w_ref();
					}
				},
				// IFEQ_W_OK
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifeq_w_ok();
					}
				},
				// IFEQ_W_KO
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_ifeq_w_ko();
					}
				},
				// IFCMPGT_W_OK
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_if_cmpgt_w_ok();
					}
				},
				// IFCMPGT_W_KO
				new TestTestCase() {
					public void run(byte[] apduBuffer) {
						loop_if_cmpgt_w_ko();
					}
				},
		};
	}

	static void loop_ifeq_ref() {
		short l0=0, l1=iterCount;
		boolean l2=true;
		short l3=0;

		for (; l0<l1; l0++)
			l3++;
	}
	static void loop_ifeq_ok() {
		short l0=0, l1=iterCount;
		boolean l2=false;
		short l3=0;

		for (; l0<l1; l0++) {
			if (l2 == true) { // ifeq 0 + jump
				l3++; l3++; l3++;
			} else {
				l3++; //executed code 
			}
		}
	}
	static void loop_ifeq_ko() {
		short l0=0, l1=iterCount;
		boolean l2=true;
		short l3=0;

		for (; l0<l1; l0++) {
			if (l2 == true) { // ifeq 0 + no jump
				l3++;
			}
		}
	}

	static void loop_ifneq_ko() {
		short l0=0, l1=iterCount;
		boolean l2=false;
		short l3=0;

		for (; l0<l1; l0++) {
			if (l2 != true) {
				l3++; // executed code
			}
		}
	}
	static void loop_ifneq_ok() {
		short l0=0, l1=iterCount;
		boolean l2=true;
		short l3=0;

		for (; l0<l1; l0++) {
			if (l2 != true) {
				l3++; l3++; l3++; l3++; l3++;
			} else {
				l3++; // executed code
			}
		}
	}

	static void loop_ifgt_ok() {
		short l0=0, l1=iterCount;
		short l2=1;
		short l3=0;
		
		for (; l0<l1; l0++) {
			if (l2 <= 0) { //ifgt
				l3++; l3++; l3++;
			} else {
				l3++; // executed code ?
			}
		}
	}
	static void loop_ifgt_ko() {
		short l0=0, l1=iterCount;
		short l2=-1;
		short l3=0;

		for (; l0<l1; l0++) {
			if (l2 <= 0) { //ifgt
				l3++; // executed code
			}
		}
	}

	static void loop_ifnonnull_ok() {
		short l0=0, l1=iterCount;
		byte[] l2 = nonNullArray;
		short l3=0;
		
		for (; l0<l1; l0++) {
			if (l2 == null) {
				l3++; l3++; l3++;
			} else  
				l3++; //executed code
			
		}
	}
	static void loop_ifnonnull_ko() {
		short l0=0, l1=iterCount;
		byte[] l2 = null;
		short l3=0;
		
		for (; l0<l1; l0++) {
			if (l2 == null)
				l3++; // executed code
		}
	}

	/**
	 * IFEQ_W_REF : big enough to generate a if_scmplt_w in the inner loop.
	 */
	static void loop_ifeq_w_ref() {
		short l0=0, l1=iterCount;
		boolean l2=false;
		short l3=0;
		boolean l4=false;
		
		for (; l0<l1; l0++) {
			if (l4 /* false */ ) { // skipped code generating an if_scmplt_w
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			}
			l3++; //executed code 
		}
	}

	static void loop_ifeq_w_ok() {
		short l0=0, l1=iterCount;
		boolean l2=false;
		short l3=0;
		boolean l4=false;

		for (; l0<l1; l0++) {
			if (l4 /* false */ ) { // skipped code generating an if_scmplt_w
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			}

			if (l2 == true) { // sload_2 (which is equal to 0)+  ifeq 0 jumped!
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			} else {
				l3++; //executed code - same as reference.
			}
		}
	}
	
	static void loop_ifeq_w_ko() {
		short l0=0, l1=iterCount;
		boolean l2=true;
		short l3=0;
		boolean l4=false;
		
		for (; l0<l1; l0++) {
	
			if (l4 /* false */ ) { // skipped code generating an if_scmplt_w
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			}

			if (l2 == true) { // sload_2 (equal to 1) ifeq 0 + no jump
				l3++; // reference code + goto_w
			} else { // 
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			}
		}
	}
	
				
	static void loop_if_cmpgt_w_ok() {
		short l0=0, l1=iterCount;
		boolean l2=false;
		short l3=0;
		short l4=0;
		
		for (; l0<l1; l0++) {
			if (l2 /* false */ ) { // skipped code generating an if_scmplt_w
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			}

			if (l1 <= l4) { // sload_1, sload_3 +  if_cmpgt_w, jumped!
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			} else {
				l3++; //executed code - same as reference.
			}
		}
	}

	static void loop_if_cmpgt_w_ko() {
		short l0=0, l1=iterCount;
		boolean l2=false;
		short l3=0;
		short l4=0;
		
		for (; l0<l1; l0++) {
			if (l2 /* false */ ) { // skipped code generating an if_scmplt_w
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			}

			if (l1 > l4) { // sload_1, sload_3 +  if_cmpgt_w, jumped!
				l3++; //executed code - same as reference.
			} else { // GOTO_W
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			}
		}
	}

	static void loop_if_cmplt_w_ref() {
		short l0=0, l1=iterCount;
		boolean l2=false;
		short l3=0;
		short l4=0;
		
		for (; l0<l1; l0++) {
			if (l2 /* false */ ) { // skipped code generating an if_scmplt_w
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			}
			l3++;
		}
	}

	static void loop_if_cmplt_w() {  // if_cmplt_w_ref - IFCMP_LT + IFCMPLT_W
		short l0=0, l1=iterCount;
		boolean l2=false;
		short l3=0;
		short l4=0;
		
		for (; l0<l1; l0++) {
			if (l2 /* false */ ) { // skipped code generating an if_scmplt_w
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
				l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;l3++;
			}
			l3++;
		}
	}

}

abstract class TestTestCase extends TestCase {
	public TestTestCase() {
		super();
		setUseInnerCounter(true);
	}
	public void setUp(byte[] buffer, short iterCount) {
		TestsTestApplet.iterCount = iterCount;
	}
}

