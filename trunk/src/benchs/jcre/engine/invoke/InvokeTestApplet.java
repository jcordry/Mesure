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

package benchs.jcre.engine.invoke;

import javacard.framework.CardRuntimeException;
import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

/**
 * This applets aims at evaluating invoke based bytecodes.
 *
 */
public class InvokeTestApplet extends TemplateApplet {

	public static short iterCount = 0;

	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new InvokeTestApplet().register();
	}	    

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				//INVOKERETTHROW_REF
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_0();
					}
				},
				
				//INVOKESTATIC+RETURN
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_1();
					}
				},
				
				//INVOKESTATIC+RETURN+noCATCH
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_11();
					}
				},
				
				//INVOKESTATIC+THROW+CATCH
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_2();
					}
				},
				
				//2*INVOKESTATIC+THROW+CATCH+RETURN
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_3();
					}
				},
				
				//INVOKESTATIC+THROW+CATCH+RETURN
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_33();
					}
				},
				
				//THROW+CATCH
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_4();
					}
				},
				
				//INVOKE_REF
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_5();
					}
				},
				
				//INVOKESTATIC+RETURN
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_6();
					}
				},
				
				//INVOKESPECIAL+RETURN
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_7();
					}
				},
				
				//INVOKESVIRTUAL+RETURN
				new InvokeTestCase() {
					public void run(byte[] apduBuffer) {
						loop_8();
					}
				},

		};
	}

	/**
	 * INVOKE SUITE
	 *
	 */
	
	// LOOP empty
	void loop_0() {
		short l0=0;
		for (; l0<iterCount; l0++) {
			// EMPTY
		}
	}
	
	// LOOP  INVOKESTATIC + (RETURN)
	void loop_1() {
		short l0=0;
		for (; l0<iterCount; l0++) {
			// INVOKESTATIC
			// RETURN
			m1();
		}
	}

	// LOOP INVOKESTATIC + (RETURN) + noCATCH
	void loop_11() {
		short l0=0;
		for (; l0<iterCount; l0++) {
			// INVOKESTATIC
			// RETURN
			// EMPTYCATCH (GOTO)
			try {
				m1();
			} catch (CardRuntimeException e) {
			}
		}
	}

	// LOOP INVOKESTATIC + (THROW) + CATCH
	void loop_2() {
		short l0=0;
		for (; l0<iterCount; l0++) {
			try {
				m2();
			} catch (CardRuntimeException e) {
			}
		}
	}

	// LOOP INVOKESTATIC + (INVOKESTATIC+THROW+CATCH+RETURN)
	void loop_3() {
		short l0=0;
		for (; l0<iterCount; l0++) {
			m3();
		}
	}

	// LOOP INVOKESTATIC + (THROW+CATCH+RETURN)
	void loop_33() {
		short l0=0;
		for (; l0<iterCount; l0++) {
			m33();
		}
	}

	// LOOP THROW+CATCH
	void loop_4() {
		short l0=0;
		for (; l0<iterCount; l0++) {
			try {
				CardRuntimeException.throwIt((short)0);
			} catch (CardRuntimeException e) {
			}
		}
	}

	// RETURN
	static void m1() {
		return;
	}

	// THROW
	static void m2() {
		CardRuntimeException.throwIt((short)0);
	}

	// INVOKESTATIC+ (THROW) +CATCH+RETURN
	static void m3() {
		try {
			m2();
		} catch (CardRuntimeException e) {
			return;
		}
	}

	// THROW+CATCH+RETURN
	static void m33() {
		try {
			CardRuntimeException.throwIt((short)0);
		} catch (CardRuntimeException e) {
 			return;
		}
	}

	

	/**
	 * invoke reference
	 */
	void loop_5() {
		short l0=0;
		
		for (; l0<iterCount; l0++) {
		}
	}

	/**
	 * invokestatic + return
	 */
	void loop_6() {
		short l0=0;
		
		for (; l0<iterCount; l0++) {
			m_static();
		}
	}

	/**
	 * invokespecial + return
	 */
	void loop_7() {
		short l0=0;
		
		for (; l0<iterCount; l0++) {
			m_private();
		}
	}

	/**
	 * invokevirtual + return
	 */
	void loop_8() {
		short l0=0;
		
		for (; l0<iterCount; l0++) {
			m_public();
		}
	}
	

	/**
	 * UNITS
	 *
	 */
	static public void m_static() { // generates invokestatic
		return;
	}

	private void m_private() { // generate invokespecial
		return;
	}

	public void m_public() { // generate invokevirtual
		return;
	}
}

abstract class InvokeTestCase extends TestCase {
	
	public InvokeTestCase() {
		super();
		setUseInnerCounter(true);
	}
	public void setUp(byte[] buffer, short iterCount) {
		InvokeTestApplet.iterCount = iterCount;
	}
}



