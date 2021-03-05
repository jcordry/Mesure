package benchs.jcre.engine.stests;

/*
 * Copyright (c) 2006-2007 Mesure Project
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

import javacard.framework.ISOException;
import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

public class STestsTestApplet extends TemplateApplet {
	
	static public Object dummy;
	
	/**
	 * Method installing the applet.
	 * @param bArray the array constaining installation parameters
	 * @param bOffset the starting offset in bArray
	 * @param bLength the length in bytes of the data parameter in bArray
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) throws ISOException{
		/* applet  instance creation */
		new STestsTestApplet().register();
	}
	
	public TestCase[] getTestCases() {
		return new TestCase[] {
				/* if_scmp_ok_ref */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						s1++;
						short s = 360;
					}
				},
				/* if_scmp_ko_ref */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						s1++;
						short s = 361;
					}
				},
				/* if_null_ok_ref */
				new TestCase () {
					public void setUp(byte[] apduBuffer) {
					  dummy = new Object();
					}
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (dummy != null) {
							s1++;
						} else {
							s2++;
						}
						short s = 362;
					}
					public void cleanUp(byte[] apduBuffer) {
					  dummy = null;
					}
				},
				/* if_null_ko_ref */
				new TestCase () {
					public void setUp(byte[] apduBuffer) {
					  dummy = new Object();
					}
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (dummy != null) {
							s1++;
						} else {
							s2++;
						}
						short s = 363;
					}
					public void cleanUp(byte[] apduBuffer) {
					  dummy = null;
					}
				},
				/* ifge_ok */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 364;
					}
				},
				/* ifge_ko */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 365;
					}
				},
				/* ifgt_ok */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 366;
					}
				},
				/* ifgt_ko */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 367;
					}
				},
				/* ifle_ok */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 368;
					}
				},
				/* ifle_ko */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 369;
					}
				},
				/* iflt_ok */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 370;
					}
				},
				/* iflt_ko */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 371;
					}
				},
				/* ifnull_ok */
				new TestCase () {
					public void setUp(byte[] apduBuffer) {
					  dummy = new Object();
					}
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (dummy != null) {
							s1++;
						} else {
							s2++;
						}
						short s = 372;
					}
					public void cleanUp(byte[] apduBuffer) {
					  dummy = null;
					}
				},
				/* ifnull_ko */
				new TestCase () {
					public void setUp(byte[] apduBuffer) {
					  dummy = new Object();
					}
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (dummy != null) {
							s1++;
						} else {
							s2++;
						}
						short s = 373;
					}
					public void cleanUp(byte[] apduBuffer) {
					  dummy = null;
					}
				},
				/* if_scmpge_ok */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 374;
					}
				},
				/* if_scmpge_ko */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 375;
					}
				},
				/* if_scmpgt_ok */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 376;
					}
				},
				/* if_scmpgt_ko */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 377;
					}
				},
				/* if_scmple_ok */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 378;
					}
				},
				/* if_scmple_ko */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 379;
					}
				},
				/* if_scmplt_ok */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 380;
					}
				},
				/* if_scmplt_ko */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 381;
					}
				},
				/* if_scmpne_ok */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 382;
					}
				},
				/* if_scmpne_ko */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 383;
					}
				},
				/* if_scmpeq_ok */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 384;
					}
				},
				/* if_scmpeq_ko */
				new TestCase () {
					public void run (byte[] apduBuffer) {
						short s1 = 0,  s2 = 1;
						if (s1!=s2) {
							s1++;
						} else {
							s2++;
						}
						short s = 385;
					}
				}
		};
	}
	
}
