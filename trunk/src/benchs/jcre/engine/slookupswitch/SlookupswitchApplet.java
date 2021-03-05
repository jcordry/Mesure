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
package benchs.jcre.engine.slookupswitch;

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

/**
 * 
 * @author original source code : étudiants DESS TIIR
 *
 */
public class SlookupswitchApplet extends TemplateApplet {

	protected static short iterCount = 0;

	/**
	 *
	 */
	public SlookupswitchApplet() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new SlookupswitchApplet().register();
	}

	
	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {

				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_ref_slookupswitch_1();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_slookupswitch_1();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_ref_slookupswitch_2();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_slookupswitch_2();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_ref_slookupswitch_5();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_slookupswitch_5();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_ref_slookupswitch_100();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_slookupswitch_100();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_ref_slookupswitch_0();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_slookupswitch_0();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_ref_slookupswitch_60();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_slookupswitch_60();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_ref_slookupswitch_200();
					}
				},
				new SlookupswitchTestCase() {
					public void run(byte[] buffer) {
						tc_unit_slookupswitch_200();
					}
				}
		};
	}

	/**
	 * SLOOKUPSWITCH
	 * CASE 1 is executed
	 */
	private static void ref_slookupswitch(short l0, short l1) {
	}
	private static void tc_ref_slookupswitch_1() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			ref_slookupswitch((short)1, l0);
		}
	}

	private static void unit_slookupswitch(short l0) {
		switch(l0){
		case 1: 
			return;
		case 2: 
			return;
		case 5: 
			return;
		case 100: 
			return;
		}
	}
	private static void tc_unit_slookupswitch_1() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			unit_slookupswitch((short)1);
		}
	}

	/**
	 * SLOOKUPSWITCH
	 * CASE 2 is executed
	 */
	private static void tc_ref_slookupswitch_2() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			ref_slookupswitch((short)2, l0);
		}
	}
	private static void tc_unit_slookupswitch_2() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			unit_slookupswitch((short)2);
		}
	}

	/**
	 * SLOOKUPSWITCH
	 * CASE 5 is executed
	 */
	private static void tc_ref_slookupswitch_5() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			ref_slookupswitch((short)5, l0);
		}
	}
	private static void tc_unit_slookupswitch_5() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			unit_slookupswitch((short)5);
		}
	}
	
	/**
	 * SLOOKUPSWITCH
	 * CASE 100 is executed
	 */
	private static void tc_ref_slookupswitch_100() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			ref_slookupswitch((short)100, l0);
		}
	}
	private static void tc_unit_slookupswitch_100() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			unit_slookupswitch((short)100);
		}
	}

	/**
	 * SLOOKUPSWITCH
	 * CASE 0 is executed
	 */
	private static void tc_ref_slookupswitch_0() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			ref_slookupswitch((short)0, l0);
		}
	}
	private static void tc_unit_slookupswitch_0() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			unit_slookupswitch((short)0);
		}
	}

	/**
	 * SLOOKUPSWITCH
	 * CASE 60 is executed
	 */
	private static void tc_ref_slookupswitch_60() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			ref_slookupswitch((short)60, l0);
		}
	}
	private static void tc_unit_slookupswitch_60() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			unit_slookupswitch((short)60);
		}
	}
	
	/**
	 * SLOOKUPSWITCH
	 * CASE 200 is executed
	 */
	private static void tc_ref_slookupswitch_200() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			ref_slookupswitch((short)200, l0);
		}
	}
	private static void tc_unit_slookupswitch_200() {
		short l0=0;
		short l1=iterCount;
		for (;l0<l1; l0++) {
			unit_slookupswitch((short)200);
		}
	}

}

abstract class SlookupswitchTestCase extends TestCase {
	public SlookupswitchTestCase() {
		super();
		setUseInnerCounter(true);
	}
	public void setUp(byte[] buffer, short iterCount) {
		SlookupswitchApplet.iterCount = iterCount;
	}
}
