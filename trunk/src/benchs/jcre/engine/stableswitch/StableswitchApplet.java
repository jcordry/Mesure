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
package benchs.jcre.engine.stableswitch;

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

public class StableswitchApplet extends TemplateApplet {


	protected static short iterCount = 0;

	/**
	 *
	 */
	public StableswitchApplet() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new StableswitchApplet().register();
	}

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {

				new StableswitchTestCase() {
					public void run(byte[] buffer) {
						tc_ref_stableswitch_1();
					}
				},
				new StableswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_stableswitch_1();
					}
				},
//				new StableswitchTestCase() {
//					public void run(byte[] buffer) {
//						tc_ref_stableswitch_2();
//					}
//				},
				new StableswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_stableswitch_2();
					}
				},
//				new StableswitchTestCase() {
//					public void run(byte[] buffer) {
//						tc_ref_stableswitch_3();
//					}
//				},
				new StableswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_stableswitch_3();
					}
				},
//				new StableswitchTestCase() {
//					public void run(byte[] buffer) {
//						tc_ref_stableswitch_4();
//					}
//				},
				new StableswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_stableswitch_4();
					}
				},
//				new StableswitchTestCase() {
//					public void run(byte[] buffer) {
//						tc_ref_stableswitch_0();
//					}
//				},
				new StableswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_stableswitch_0();
					}
				},
				new StableswitchTestCase() {
					public void run(byte[] buffer) {
						tc_ref_stableswitch_10();
					}
				},
				new StableswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_stableswitch_10();
					}
				}};
	}

	/**
	 * STABLESWITCH
	 * CASE 1 is executed
	 * ref case, followed by test case
	 */
	private static void ref_stableswitch(short l0, short l1) {
		// 2nd argument is dummy to generate an sload bytecode
		// return
	}
	private static void tc_ref_stableswitch_1() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			// sconst_1, sload_0, invoke
			ref_stableswitch((short)1, l0);
		}
	}
	private static void unit_stableswitch(short l0) {
		switch(l0){
		case 1: 
			return;
		case 2: 
			return;
		case 3: 
			return;
		case 4:
			return;
		default:
			return;
		}
	}
	private static void tc_unit_stableswitch_1() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			// sconst_1, sload_0(inner), invoke, stableswitch
			unit_stableswitch((short)1);
		}
	}

	/**
	 * STABLESWITCH
	 * CASE 2 is executed
	 */
	private static void tc_ref_stableswitch_2() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			ref_stableswitch((short)2, l0);
		}
	}

	private static void tc_unit_stableswitch_2() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			unit_stableswitch((short)2);
		}
	}

	/**
	 * STABLESWITCH
	 * CASE 3 is executed
	 */
	private static void tc_ref_stableswitch_3() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			ref_stableswitch((short)3, l0);
		}
	}

	private static void tc_unit_stableswitch_3() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			unit_stableswitch((short)3);
		}
	}

	/**
	 * STABLESWITCH
	 * CASE 4 is executed
	 */
	private static void tc_ref_stableswitch_4() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			ref_stableswitch((short)4, l0);
		}
	}

	private static void tc_unit_stableswitch_4() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			unit_stableswitch((short)4);
		}
	}

	/**
	 * STABLESWITCH
	 * switch(0) uncatched
	 */
	private static void tc_ref_stableswitch_0() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			ref_stableswitch((short)0, l0);
		}
	}
	private static void tc_unit_stableswitch_0() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			unit_stableswitch((short)0);
		}
	}

	/**
	 * STABLESWITCH
	 * switch(10) uncatched
	 * ref case, followed by test case
	 */
	private static void tc_ref_stableswitch_10() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			ref_stableswitch((short)10, l0);
		}
	}
	private static void tc_unit_stableswitch_10() {
		short l0=0;
		short l1=iterCount;
		for (; l0<l1; l0++) {
			unit_stableswitch((short)10);
		}
	}
}

abstract class StableswitchTestCase extends TestCase {
	public StableswitchTestCase() {
		super();
		setUseInnerCounter(true);
	}
	public void setUp(byte[] buffer, short iterCount) {
		StableswitchApplet.iterCount = iterCount;
	}
}
