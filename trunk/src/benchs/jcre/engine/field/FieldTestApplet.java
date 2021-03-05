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
package benchs.jcre.engine.field;

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

public class FieldTestApplet extends TemplateApplet {

	static short iterCount = 0;

	public static short dummyStaticShortField = 0;
	public static byte dummyStaticByteField = 0;
	public static FieldTestApplet dummyStaticRefField = null;
	
	public short dummyShortField = 0;
	public byte dummyByteField = 0;
	public FieldTestApplet dummyRefField = null;
	
	/**
	 * 
	 */
	FieldTestApplet() {
		super();
	}

	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new FieldTestApplet().register();
	}	    

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				/**
				 * COMMON REFERENCE
				 */
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_field_ref();
					}
				},
				
				/**
				 * GETSTATIC and GETFIELD
				 */
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_getStaticS();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_getStaticB();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_getFieldSThis();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_getFieldS();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_getFieldBThis();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_getFieldB();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_getFieldAThis();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_getFieldA();
					}
				},
				
				/**
				 * PUTSTATIC and PUTFIELD
				 */
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_putStaticA();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_putFieldS();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_putFieldB();
					}
				},
				new TypesTestCase() {
					public void run(byte[] apduBuffer) {
						loop_putFieldA();
					}
				},
				

		};
	}

	/**
	 * REFERENCE for getstatic, getfield, putfield bytecodes benchmarks
	 *
	 */
	static void loop_field_ref() {
		short l0=0, l1=iterCount;
		short l2 = 0;
		FieldTestApplet l3;
		
		for (; l0<l1; l0++)
			;
	}
	
	/**
	 * GETSTATIC and GETFIELD
	 *
	 */
	static void loop_getStaticS() {
		short l0=0, l1=iterCount;
		short l2 = 0;
		FieldTestApplet l3;
		
		for (; l0<l1; l0++) {
			// getstatic_s, sstore_2
			l2 = dummyStaticShortField;
		}
	}

	static void loop_getStaticB() {
		short l0=0, l1=iterCount;
		byte l2 = 0;
		FieldTestApplet l3;
		
		for (; l0<l1; l0++) {
			// getstatic_b, sstore_2
			l2 = dummyStaticByteField;
		}
	}

	void loop_getFieldSThis() {
		short l0=0, l1=iterCount;
		short l2 = 0;
		FieldTestApplet l3 = null;
		
		for (; l0<l1; l0++) {
			// getfield_s_this, sstore_3
			l2 = this.dummyShortField;
		}
	}

	void loop_getFieldS() {
		short l0=0, l1=iterCount;
		short l2 = 0;
		FieldTestApplet l3 = this;
		
		for (; l0<l1; l0++) {
			// aload_4, getfield_s, sstore_3
			l2 = l3.dummyShortField;
		}
	}

	void loop_getFieldBThis() {
		short l0=0, l1=iterCount;
		byte l2 = 0;
		FieldTestApplet l3;
		
		for (; l0<l1; l0++) {
			// getfield_b_this, sstore_3
			l2 = this.dummyByteField;
		}
	}

	void loop_getFieldB() {
		short l0=0, l1=iterCount;
		byte l2 = 0;
		FieldTestApplet l3=this;
		
		for (; l0<l1; l0++) {
			// aload_4, getfield_b, sstore_3
			l2 = l3.dummyByteField;
		}
	}

	void loop_getFieldAThis() {
		short l0=0, l1=iterCount;
		FieldTestApplet l2=null;
		FieldTestApplet l3=null;
		
		for (; l0<l1; l0++) {
			// getfield_a_this, sstore
			l3 = this.dummyRefField;
		}
	}

	void loop_getFieldA() {
		short l0=0, l1=iterCount;
		FieldTestApplet l2=this;
		FieldTestApplet l3=null;
		
		for (; l0<l1; l0++) {
			// aload_3, getfield_a, sstore
			l3 = l2.dummyRefField;
		}
	}

	
	/**
	 * PUTSTATIC and PUTFIELD
	 *
	 */
	static void loop_putStaticA() {
		short l0=0, l1=iterCount;
		short l2 = 0;
		FieldTestApplet l3;
		
		for (; l0<l1; l0++) {
			// aconst_null, putstatic_a
			dummyStaticRefField = null;
		}
	}

	void loop_putFieldS() {
		short l0=0, l1=iterCount;
		short l2 = 0;
		FieldTestApplet l3=this;
		
		for (; l0<l1; l0++) {
			//aload_4, sconst_0, putfield_s
			l3.dummyShortField = 0;
		}
	}

	void loop_putFieldB() {
		short l0=0, l1=iterCount;
		short l2 = 0;
		FieldTestApplet l3=this;
		
		for (; l0<l1; l0++) {
			// aload_4, sconst_0, putfield_b
			l3.dummyByteField = 0;
		}
	}

	void loop_putFieldA() {
		short l0=0, l1=iterCount;
		short l2 = 0;
		FieldTestApplet l3=this;
		
		for (; l0<l1; l0++) {
			// aload_4, aconst_null, putfield_a
			l3.dummyRefField = null;
		}
	}


}

abstract class TypesTestCase extends TestCase {
	public short dummyField=0;
	
	public TypesTestCase() {
		super();
		setUseInnerCounter(true);
	}
	public void setUp(byte[] buffer, short iterCount) {
		FieldTestApplet.iterCount = iterCount;
	}
}


