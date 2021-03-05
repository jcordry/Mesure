package benchs.jcre.arith.arith16;

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

/**
 * This class should be used to measure the operations in 16 arithemtics
 */
public class Arith16Applet extends TemplateApplet {
	/**
	 * Method installing the applet.
	 * @param bArray the array constaining installation parameters
	 * @param bOffset the starting offset in bArray
	 * @param bLength the length in bytes of the data parameter in bArray
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) throws ISOException{
		new Arith16Applet().register();
	}
	
	/* (non-Javadoc)
	 * @see benchs.lib.templates.applets.TemplateApplet#getTestCases()
	 * @Override
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
			/* emptyLoop */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 361;
					}
			},
			/* sadd */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 362;
					}
			},
			/* ssub */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 363;
					}
			},
			/* smul */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 364;
					}
			},
			/* sdiv */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 365;
					}
			},
			/* sdiv0 */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 366;
					}
			},
			/* srem */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 367;
					}
			},
			/* srem 0 */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 368;
					}
			},
			/* sneg */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 369;
					}
			},
			/* sshl */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 370;
					}
			},
			/* sshr */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 371;
					}
			},
			/* sor */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 372;
					}
			},
			/* sand */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 373;
					}
			},
			/* sxor */
			new TestCase () {
				public void run (byte[] apduBuffer) {
					short s = 374;
					}
			}
		};
	}
	
	
}