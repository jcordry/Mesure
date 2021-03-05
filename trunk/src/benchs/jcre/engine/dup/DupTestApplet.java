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

package benchs.jcre.engine.dup;

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

/**
 * This applets aims at evaluating dup2 and dup_x bytecodes.
 *
 */
public class DupTestApplet extends TemplateApplet {
	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new DupTestApplet().register();
	}	    

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				/**
				 * Reference test
				 */
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 361;
					}
				},
				
				/**
				 * DUP2 test suite
				 */
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 362;
					}
				},
				
				/**
				 * DUP_X test suite
				 */
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 363;
					}
				},
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 364;
					}
				},
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 365;
					}
				},
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 366;
					}
				},
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 367;
					}
				},
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 368;
					}
				},
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 369;
					}
				},
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 370;
					}
				},
				
				/**
				 * DUP test suite
				 */
				new DupTestCase() {
					public void run(byte[] apduBuffer) {
						short s = 371;
					}
				},
		};
	}

}

	abstract class DupTestCase extends TestCase {
		public DupTestCase() {
			super();
			setUseInnerCounter(false);
		}
}
