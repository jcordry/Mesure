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

package benchs.jcre.engine.locvar;

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

public class LocalVariableTestApplet extends TemplateApplet  {


	public static short iterCount = 0;

	/**
	 * 
	 */
	LocalVariableTestApplet() {
		super();
	}

	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new LocalVariableTestApplet().register();
	}	    

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				/**
				 * SINC test suite
				 */
				new LocalVariableTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sinc_ref();
					}
				},
				new LocalVariableTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sinc();
					}
				},
		};
	}


	/**
	 * SINC_REF
	 * @return
	 */
	short test_unit_sinc_ref() {
		short l0=0;
		short l1=0;

		for (; l0<iterCount; l0++) {
		}
		return l1;
	}

	/**
	 * SINC_REF + SINC
	 * @return
	 */
	short test_unit_sinc() {
		short l0=0;
		short l1=0;
		
		for (; l0<iterCount; l0++) {
			l1++;
		}
		return l1;
	}

	// TODO: (if possible) SINC_W 
	/**
	 * SINC_REF + SINCW - NOT WORKING
	 * @return
	 */
//	short test_unit_sincw() {
//		short l0=0;
//		short l1=1;
//		
//		for (; l0<iterCount; l0++) {
//			l1+=16384;
//		}
//		return l1;
//	}
	
	static void dup2() {
		
	}
	
}

abstract class LocalVariableTestCase extends TestCase {
	public LocalVariableTestCase() {
		super();
		setUseInnerCounter(true);
	}
	public void setUp(byte[] buffer, short iterCount) {
		LocalVariableTestApplet.iterCount = iterCount;
	}
}

