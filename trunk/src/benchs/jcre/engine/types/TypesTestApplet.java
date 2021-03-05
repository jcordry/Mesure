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
package benchs.jcre.engine.types;

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

public class TypesTestApplet extends TemplateApplet {

	static short iterCount = 0;

	interface I1 {
	}

	class C1 implements I1 {
	}

	interface I2 {
	}

	class C2 extends C1 implements I2 {
	}

	interface I31 {
	}
	interface I32 {
	}
	interface I33 {
	}

	class C3 extends C2 implements I31, I32, I33 {
	}
	
	/**
	 * 
	 */
	TypesTestApplet() {
		super();
	}

	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new TypesTestApplet().register();
	}	    

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				// S2B reference
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_s2b_ref();
					}
				},
				// S2B benchmark
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_s2b();
					}
				},
				
				// CHECKCAST_REF_1
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_checkcast_ref();
					}
				},
				// CHECKCAST PARENT CLASS
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_checkcast_c2();
					}
				},
				// CHECKCAST GRAND-PARENT CLASS
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_checkcast_c1();
					}
				},
				// CHECKCAST INTERFACE
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_checkcast_i31();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_checkcast_i32();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_checkcast_i33();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_checkcast_i2();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_checkcast_i1();
					}
				},

		};
	}

	/**
	 * S2B REF
	 *
	 */
	static void loop_s2b_ref() {
		short l0=0, l1=iterCount;
		byte l2 = 34;
		byte l3=0;

		for (; l0<l1; l0++)
			l3 = l2;
	}
	
	/**
	 * S2B
	 *
	 */
	static void loop_s2b() {
		short l0=0, l1=iterCount;
		short l2 = 1234;
		byte l3=0;
		for (; l0<l1; l0++) {
			l3 = (byte)l2;
		}
	}

	/**
	 * CHECKCAST REF
	 *
	 */
	void loop_checkcast_ref() {
		short l0=0, l1=iterCount;
		C3 son=null;
		C3 parent=null;
		
		for (; l0<l1; l0++) {
			parent = son;
		}
	}
	/**
	 * CHECKCAST PARENT CLASS
	 *
	 */
	void loop_checkcast_c2() {
		short l0=0, l1=iterCount;
		Object son=new C3();
		C2 parent=null;
		
		for (; l0<l1; l0++) {
			parent = (C2) son;
		}
	}
	
	/**
	 * CHECKCAST GRAND-PARENT CLASS
	 *
	 */
	void loop_checkcast_c1() {
		short l0=0, l1=iterCount;
		Object son = new C3();
		C1 grandParent = null;
		
		for (; l0<l1; l0++) {
			grandParent = (C1) son;
		}
	}

	/**
	 * CHECKCAST I31 INTERFACE
	 *
	 */
	void loop_checkcast_i31() { //was static
		short l0=0, l1=iterCount;
		Object instance = new C3();
		I31 interf = null;
		
		for (; l0<l1; l0++) {
			interf = (I31) instance;
		}
	}

	/**
	 * CHECKCAST I32 INTERFACE
	 *
	 */
	void loop_checkcast_i32() { //was static
		short l0=0, l1=iterCount;
		Object instance = new C3();
		I32 interf = null;
		
		for (; l0<l1; l0++) {
			interf = (I32) instance;
		}
	}


	/**
	 * CHECKCAST I33 INTERFACE
	 *
	 */
	void loop_checkcast_i33() { //was static
		short l0=0, l1=iterCount;
		Object instance = new C3();
		I33 interf = null;
		
		for (; l0<l1; l0++) {
			interf = (I33) instance;
		}
	}

	/**
	 * CHECKCAST I2 PARENT INTERFACE
	 *
	 */
	void loop_checkcast_i2() { //was static
		short l0=0, l1=iterCount;
		Object instance = new C3();
		I2 interf = null;
		
		for (; l0<l1; l0++) {
			interf = (I2) instance;
		}
	}

	/**
	 * CHECKCAST I1 GRAND-PARENT INTERFACE
	 *
	 */
	void loop_checkcast_i1() { //was static
		short l0=0, l1=iterCount;
		Object instance = new C3();
		I1 interf = null;
		
		for (; l0<l1; l0++) {
			interf = (I1) instance;
		}
	}

	
	// JSR test
	void loop_checkcast_4() {
		short l0=0, l1=iterCount;
//		DummyImplem2 dummy1=dummyImplem2;
//		DummyInterface interf=null;
		
//		for (; l0<l1; l0++) {
//			interf = dummy1;
//			short i=0;
//			try {
//				i++;
//				
//			} catch (javacard.framework.PINException e1) {
//				return;
//			} catch (javacard.framework.ISOException e2) {
//				i--;
//			}
//			finally {
//				i++;
//				i++;
//				i++;
//				i++;
//				i++;
//				i++;
//			}
//
//		}
	}
}

abstract class TypesTestCase extends TestCase {
	public TypesTestCase() {
		super();
		setUseInnerCounter(true);
	}
	public void setUp(byte[] buffer, short iterCount) {
		TypesTestApplet.iterCount = iterCount;
	}
}


