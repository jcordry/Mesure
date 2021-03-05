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

package benchs.jcre.engine.stack;

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

/**
 * This applets aims at evaluating stack based bytecodes.
 *
 */
public class StackTestApplet extends TemplateApplet {
	
	public static short iterCount = 0;
	
	/**
	 * 
	 */
	StackTestApplet() {
		super();
	}

	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new StackTestApplet().register();
	}	    

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {

				/**
				 * SLOAD test suite
				 */
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_vv_sload();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1s_l0();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1s_l0l0();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1s_l0l0l0();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1s_l1();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1s_l2();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1s_l3();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1s_l4();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1s_l5();
					}
				},

				/**
				 * ILOAD test suite
				 */
//				new StackTestCase() {
//					public void run() {
//						test_unit_vv_iload();
//					}
//				},
//				new StackTestCase() {
//					public void run() {
//						test_unit_v1i_l0();
//					}
//				},
//				new StackTestCase() {
//					public void run() {
//						test_unit_v1i_l0l0();
//					}
//				},
//				new StackTestCase() {
//					public void run() {
//						test_unit_v1i_l0l0l0();
//					}
//				},
//				new StackTestCase() {
//					public void run() {
//						test_unit_v1i_l1();
//					}
//				},
//				new TestCase() {
//					public void run() {
//						test_unit_v1i_l2();
//					}
//				},
//				new StackTestCase() {
//					public void run() {
//						test_unit_v1i_l3();
//					}
//				},
//				new StackTestCase() {
//					public void run() {
//						test_unit_v1i_l4();
//					}
//				},
//				new StackTestCase() {
//					public void run() {
//						test_unit_v1i_l5();
//					}
//				},


				/**
				 * ALOAD test suite
				 */
				 new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_vv_aload();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1a_l0();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v2a_l0l0();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v3a_l0l0l0();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1a_l1();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1a_l2();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1a_l3();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1a_l4();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1a_l5();
					}
				},

				/**
				 * SCONST test suite
				 */
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_vv_sconst();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_0();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v2cst_00();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v3cst_000();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_1();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_2();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_3();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_4();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_5();
					}
				},

				/**
				 * BSPUSH test suite
				 */
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_vv_bspush();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_BS();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_BS_BS();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_BS_BS_BS();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_n128();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_p127();
					}
				},

				/**
				 * SSPUSH test suite
				 */
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_vv_sspush();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_SS();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_SS_SS();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_SS_SS_SS();
					}
				},

				/**
				 * ACONST test suite
				 */
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_vv_sconst();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v1cst_N();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v2cst_NN();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_v3cst_NNN();
					}
				},
				
				/**
				 * SSTORE
				 */
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_vv_sstore();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_1sv_l0();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_1sv_l1();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_1sv_l2();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_1sv_l3();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_1sv_l4();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_1sv_l5();
					}
				},
				
				/**
				 * DUP REF
				 */
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_dup_ref();
					}
				},
				/**
				 * DUP+SSTORE_1
				 */
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_dup_sstore1();
					}
				},

				/**
				 * POP_REF
				 */
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_pop_ref();
					}
				},
				/**
				 * POP
				 */
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_pop1();
					}
				},
				new StackTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_pop2();
					}
				},
		};
	}

	/**
	 * REF SLOAD
	 */
	static private void test_unit_vv_sload() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_vv();
	}

	/**
	 * SLOAD_L0
	 */
	static private void test_unit_v1s_l0() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		// l1 = iterCount ........ l0<l1
		for (; l0<iterCount; l0++)
			unit_v1s(l0);
	}

	/**
	 * SLOAD_L0L0
	 */
	static private void test_unit_v1s_l0l0() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v2s(l0, l0);
	}

	/**
	 * SLOAD_L0L0L0
	 */
	static private void test_unit_v1s_l0l0l0() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v3s(l0, l0, l0);
	}

	/**
	 * SLOAD_L1
	 */
	static private void test_unit_v1s_l1() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v1s(l1);
	}

	/**
	 * SLOAD_L2
	 */
	static private void test_unit_v1s_l2() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v1s(l2);
	}

	/**
	 * SLOAD_L3
	 */
	static private void test_unit_v1s_l3() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v1s(l3);
	}

	/**
	 * SLOAD_L4
	 */
	static private void test_unit_v1s_l4() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v1s(l4);
	}

	/**
	 * SLOAD_L5
	 */
	static private void test_unit_v1s_l5() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v1s(l5);
	}


	/**
	 * REF
	 */
	static public void unit_vv() {
		return;
	}

	/** 
	 * SLOAD_1, SLOAD_11, SLOAD_111 
	 */
	static public void unit_v1s(short p1) {
		return;
	}

	static public void unit_v2s(short p1, short p2) {
		return;
	}

	static public void unit_v3s(short p1, short p2, short p3) {
		return;
	}

	
	/* ------------------------ ALOAD ----------------------------*/
	/**
	 * REF ALOAD
	 */
	static private void test_unit_vv_aload() {
		short l0=0;
		TestCase l1=null;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_vv();
	}

	/**
	 * ALOAD_L0
	 */
	static private void test_unit_v1a_l0() {
		TestCase l0=null;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l1<iterCount; l1++)
			unit_v1a(l0);
	}

	/**
	 * ALOAD_L0L0
	 */
	static private void test_unit_v2a_l0l0() {
		TestCase l0=null;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l1<iterCount; l1++)
			unit_v2a(l0, l0);
	}

	/**
	 * ALOAD_L0L0L0
	 */
	static private void test_unit_v3a_l0l0l0() {
		TestCase l0=null;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l1<iterCount; l1++)
			unit_v3a(l0, l0, l0);
	}

	/**
	 * ALOAD_L1
	 */
	static private void test_unit_v1a_l1() {
		short l0=0;
		TestCase l1=null;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v1a(l1);
	}

	/**
	 * ALOAD_L2
	 */
	static private void test_unit_v1a_l2() {
		short l0=0;
		short l1=0;
		TestCase l2=null;
		short l3=0;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v1a(l2);
	}

	/**
	 * SLOAD_L3
	 */
	static private void test_unit_v1a_l3() {
		short l0=0;
		short l1=0;
		short l2=0;
		TestCase l3=null;
		short l4=0;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v1a(l3);
	}

	/**
	 * SLOAD_L4
	 */
	static private void test_unit_v1a_l4() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		TestCase l4=null;
		short l5=0;

		for (; l0<iterCount; l0++)
			unit_v1a(l4);
	}

	/**
	 * SLOAD_L5
	 */
	static private void test_unit_v1a_l5() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		TestCase l5=null;

		for (; l0<iterCount; l0++)
			unit_v1a(l5);
	}

	/** 
	 * ALOAD_1, ALOAD_11, ALOAD_111
	 */

	static public void unit_v1a(TestCase p1) {
		return;
	}

	static public void unit_v2a(TestCase p1, TestCase p2) {
		return;
	}

	static public void unit_v3a(TestCase p1, TestCase p2, TestCase p3) {
		return;
	}

	/* ------------------ SCONST ------------------- */

	/**
	 * REF SCONST
	 */
	static private void test_unit_vv_sconst() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_vv();
	}

	/**
	 * SCONST_0
	 */
	static private void test_unit_v1cst_0() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1s((short)0);
	}

	/**
	 * SCONST_00
	 */
	static private void test_unit_v2cst_00() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v2s((short)0, (short)0);
	}

	/**
	 * SCONST_000
	 */
	static private void test_unit_v3cst_000() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v3s((short)0, (short)0, (short)0);
	}

	/**
	 * SCONST_1
	 */
	static private void test_unit_v1cst_1() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1s((short)1);
	}

	/**
	 * SCONST_2
	 */
	static private void test_unit_v1cst_2() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1s((short)2);
	}

	/**
	 * SCONST_3
	 */
	static private void test_unit_v1cst_3() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1s((short)3);
	}

	/**
	 * SCONST_4
	 */
	static private void test_unit_v1cst_4() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1s((short)4);
	}

	/**
	 * SCONST_5
	 */
	static private void test_unit_v1cst_5() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1s((short)5);
	}

	/* --------------- BSPUSH -------------- */
	
	/**
	 * REF BSPUSH
	 */
	static private void test_unit_vv_bspush() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_vv();
	}

	/**
	 * BSPUSH_BS
	 */
	static private void test_unit_v1cst_BS() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1s((short)6);
	}

	/**
	 * BSPUSH_n128
	 */
	static private void test_unit_v1cst_n128() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1s((short)-128);
	}

	/**
	 * BSPUSH_p127
	 */
	static private void test_unit_v1cst_p127() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1s((short)+127);
	}

	/**
	 * BSPUSH_BS_BS
	 */
	static private void test_unit_v1cst_BS_BS() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v2s((short)6, (short) 7);
	}

	/**
	 * BSPUSH_BS_BS_BS
	 */
	static private void test_unit_v1cst_BS_BS_BS() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v3s((short)6, (short) 7, (short)8);
	}

	/* -------------- SSPUSH -------------- */
	/**
	 * REF SSPUSH
	 */
	static private void test_unit_vv_sspush() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_vv();
	}

	/**
	 * SSPUSH_SS
	 */
	static private void test_unit_v1cst_SS() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1s((short)128);
	}

	/**
	 * SSPUSH_SS_SS
	 */
	static private void test_unit_v1cst_SS_SS() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v2s((short)12345, (short)12346);
	}

	/**
	 * SSPUSH_SS_SS_SS
	 */
	static private void test_unit_v1cst_SS_SS_SS() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v3s((short)12345, (short)12346, (short)12347);
	}


	/* -------------- ACONST -------------- */
	/**
	 * ACONST_N
	 */
	static private void test_unit_v1cst_N() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v1a(null);
	}

	/**
	 * ACONST_NN
	 */
	static private void test_unit_v2cst_NN() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v2a(null, null);
	}

	/**
	 * ACONST_NNN
	 */
	static private void test_unit_v3cst_NNN() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_v3a(null, null, null);
	}
	
	
	/* ------------------- SSTORE ---------------- */

	/**
	 * REF SSTORE
	 */
	static private void test_unit_vv_sstore() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;

			
		for (; l0<iterCount; l0++)
			;
	}

	/**
	 * SSTORE_l0
	 */
	static private void test_unit_1sv_l0() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;
		
		for (; l1<iterCount; l1++) {
			l0 = l1;
		}
	}

	/**
	 * SSTORE_l1
	 */
	static private void test_unit_1sv_l1() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;
			
		for (; l0<iterCount; l0++)
			l1 = l0;
	}

	/**
	 * SSTORE_l2
	 */
	static private void test_unit_1sv_l2() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;
			
		for (; l0<iterCount; l0++)
			l2 = l0;
	}

	/**
	 * SSTORE_l3
	 */
	static private void test_unit_1sv_l3() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;
			
		for (; l0<iterCount; l0++)
			l3 = l0;
	}

	/**
	 * SSTORE_l4
	 */
	static private void test_unit_1sv_l4() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;
			
		for (; l0<iterCount; l0++)
			l4 = l0;
	}

	/**
	 * SSTORE_l5
	 */
	static private void test_unit_1sv_l5() {
		short l0=0;
		short l1=0;
		short l2=0;
		short l3=0;
		short l4=0;
		short l5=0;
			
		for (; l0<iterCount; l0++)
			l5 = l0;
	}

	/** 
	 * 
	 */
	static public short unit_1sv() {
		return (short)0;
	}

	static public short unit_1s1s(short l0) {
		return (short)l0;
	}
	
	/**
	 * DUP_REF 
	 */
	static private void test_unit_dup_ref() {
		short l0=0;

		for (; l0<iterCount; l0++) {
			// sload + invok
			unit_dup_ref(l0);
		}
	}
	static private short unit_dup_ref(short l0) {
		// sload, sconst_1, sadd, sreturn
		return (short)(l0 + 1); 
	}
	

	/**
	 * DUP + SSTORE_0 (bug ?)
	 * @return
	 */
	static private void test_unit_dup_sstore1() {
		short l0=0;

		for (; l0<iterCount; l0++) {
			// sload + invok
			unit_dup(l0);
		}
	}


	/**
	 * DUP_REF + DUP + SSTORE_0
	 * @param l0
	 * @return
	 */
	static private short unit_dup(short l0) {
		// sload, dup, sconst, sadd, store, sreturn
		return l0++;
	}

	/**
	 * DUP2_REF
	 * 
	 */
	static void loop_dup2_ref() {
		short l0=0, l1=0;
		short array0[] = {0};

		
		for (; l0<iterCount; l0++) {
			l1 = array0[0];
		}
	}
	 
	/**
	 *  DUP2
	 * == DUP2_REF + DUP2 + SCONST_1 + SADD
	 */
	static void loop_dup2() {
		short l0=0, l1=0;
		short array0[] = {0};
		
		for (; l0<iterCount; l0++) {
			array0[0]++; 
		}
	}
	
	/**
	 * POP_REF
	 * @return
	 */
	static private short test_unit_pop_ref() {
		short l0=0;
		short l1;
		
		for (; l0<iterCount; l0++) {
			unit_vv();
		}
		return l0;
	}
	
	/**
	 * POP_REF + STUFF + SSTORE_1
	 * @return
	 */
	static private short test_unit_pop1() {
		short l0=0;
		short l1;
		
		for (; l0<iterCount; l0++) {
			// sload_0, sreturn, sstore_1
			 l1 = unit_1s1s(l0);
		}
		return l0;
	}

	/**
	 * POP_REF + STUFF + POP
	 * @return
	 */
	static private short test_unit_pop2() {
		short l0=0;
		short l1;
		
		for (; l0<iterCount; l0++) {
			// sload_0, sreturn, pop
			unit_1s1s(l0);
		}
		return l0;
	}
}

abstract class StackTestCase extends TestCase {
	public StackTestCase() {
		super();
		setUseInnerCounter(true);
	}
	public void setUp(byte[] buffer, short iterCount) {
		StackTestApplet.iterCount = iterCount;
	}
}
