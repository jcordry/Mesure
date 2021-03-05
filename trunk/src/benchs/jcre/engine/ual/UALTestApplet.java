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

package benchs.jcre.engine.ual;

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

/**
 * This applets aims at evaluating Basic UAL bytecodes.
 *
 */
public class UALTestApplet extends TemplateApplet {

	public static short iterCount = 0;

	/**
	 * 
	 */
	UALTestApplet() {
		super();
	}

	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new UALTestApplet().register();
	}	    

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				/**
				 * ARITH16 - 2-op operations 
				 */
				// BINOPS_REF
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_binops_ref();
					}
				},
				// SADD
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sadd_1s2s();
					}
				},
				// SSUB
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_ssub_1s2s();
					}
				},
				// SMUL
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_smul_1s2s();
					}
				},
				// SDIV
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sdiv_1s2s();
					}
				},
				// SREM
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_srem_1s2s();
					}
				},

				// SHIFT_REF
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_shift_1_ref();
					}
				},
				// SSHL
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sshl_1_1s2s();
					}
				},
				// SSHR
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sshr_1_1s2s();
					}
				},

				
				// UNARY REF
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_unaryops_ref();
					}
				},
				// SNEG
				new UALTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sneg_1s1s();
					}
				},

		};
	}
		
	/**
	 * SADD_REF
	 * using 3 parameters to build 1 more sload to match the number of sload of bin ops.
	 */
	static private void test_unit_binops_ref() {
		short l0=0;
		short l1=11;

		for (; l0<iterCount; l0++)
			// 3*SLOAD
			// INVOKESTATIC
			unit_1s3s(l0, l1, l0);
	}

	/**
	 * SADD_2S
	 */
	static private void test_unit_sadd_1s2s() {
		short l0=0;
		short l1=11;

		for (; l0<iterCount; l0++)
			// 2*SLOAD
			// INVOKESTATIC
			unit_sadd_1s2s(l0, l1);
	}

	/**
	 * SSUB_2S
	 */
	static private void test_unit_ssub_1s2s() {
		short l0=0;
		short l1=11;

		for (; l0<iterCount; l0++)
			unit_ssub_1s2s(l0, l1);
	}

	/**
	 * SMUL_2S
	 */
	static private void test_unit_smul_1s2s() {
		short l0=0;
		short l1=11;

		for (; l0<iterCount; l0++)
			unit_smul_1s2s(l0, l1);
	}

	/**
	 * SDIV_2S
	 */
	static private void test_unit_sdiv_1s2s() {
		short l0=0;
		short l1=11;

		for (; l0<iterCount; l0++)
			unit_sdiv_1s2s(l0, l1);
	}

	/**
	 * SREM_2S
	 */
	static private void test_unit_srem_1s2s() {
		short l0=0;
		short l1=11;

		for (; l0<iterCount; l0++)
			unit_srem_1s2s(l0, l1);
	}

	/**
	 * SHIFTS 
	 */
	
	/**
	 * SHIFT_1_REF
	 */
	static private void test_unit_shift_1_ref() {
		short l0=0;
		short l1=l0;

		for (; l0<iterCount; l0++)
			unit_1s3s(l0, (short)1, l0);
	}

	/**
	 * SSHL_1_2S
	 */
	static private void test_unit_sshl_1_1s2s() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_sshl_1s2s(l0, (short)1);
	}

	/**
	 * SSHR_1_2S
	 */
	static private void test_unit_sshr_1_1s2s() {
		short l0=0;

		for (; l0<iterCount; l0++)
			unit_sshr_1s2s(l0, (short)1);
	}

	/**
	 * UNARY OPS 
	 */
	
	/**
	 * UNARY_OPS_REF
	 * using 3 parameters to build 1 more sload to match the number of sload of bin ops.
	 */
	static private void test_unit_unaryops_ref() {
		short l0=0;

		for (; l0<iterCount; l0++)
			// SLOAD
			// INVOKESTATIC
			unit_1s1s(l0);
	}

	/**
	 * SNEG_1S
	 */
	static private void test_unit_sneg_1s1s() {
		short l0=0;

		for (; l0<iterCount; l0++)
			// SLOAD
			// INVOKESTATIC
			unit_sneg_1s1s(l0);
	}


	/**
	 * UNITS of binary ops
	 */
	static private short unit_1s3s(short l0, short l1, short l2) {
		// SLOAD
		// SRETURN
		return (l0);
	}

	static private short unit_sadd_1s2s(short l0, short l1) {
		// SLOAD
		// SLOAD
		// SADD
		// SRETURN
		return (short)(l0+l1);
	}

	static private short unit_ssub_1s2s(short l0, short l1) {
		return (short)(l0-l1);
	}
	static private short unit_smul_1s2s(short l0, short l1) {
		return (short)(l0*l1);
	}
	static private short unit_sdiv_1s2s(short l0, short l1) {
		return (short)(l0/l1);
	}
	static private short unit_srem_1s2s(short l0, short l1) {
		return (short)(l0%l1);
	}

	/**
	 * UNITS of SHIFTS
	 */
	static private short unit_1s2s(short l0, short l1) {
		// SLOAD
		// SRETURN
		return (l0);
	}

	static private short unit_sshl_1s2s(short l0, short l1) {
		// SLOAD
		// SLOAD
		// SSHL
		// SRETURN
		return (short)(l0<<l1);
	}

	static private short unit_sshr_1s2s(short l0, short l1) {
		return (short)(l0>>l1);
	}

	/**
	 * UNITS of unary ops
	 */
	static private short unit_1s1s(short l0) {
		// SLOAD
		// SRETURN
		return (l0);
	}

	static private short unit_sneg_1s1s(short l0) {
		// SLOAD
		// SNEG
		// SRETURN
		return (short)(-l0);
	}

}

abstract class UALTestCase extends TestCase {
	public UALTestCase() {
		super();
		setUseInnerCounter(true);
	}
	public void setUp(byte[] buffer, short iterCount) {
		UALTestApplet.iterCount = iterCount;
	}
}
