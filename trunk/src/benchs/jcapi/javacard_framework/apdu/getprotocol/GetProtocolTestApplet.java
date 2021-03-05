package benchs.jcapi.javacard_framework.apdu.getprotocol;

import javacard.framework.APDU;
import benchs.lib.templates.DummyClass;
import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

/*
 * Copyright (c) 2007 Mesure Project
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
 * $HeadURL: $
 * Created: 9 mai 2007
 * Author: TL 
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

/**
 * This class is dedicated to measurements of the <tt>Util.getProtocol</tt> method.
 */
public class GetProtocolTestApplet extends TemplateApplet {

	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new GetProtocolTestApplet().register();
	}

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
      return new TestCase[] {
		// Dummy case used to compute the overhead with no transaction
		new TestCase() {
		  public void run(byte[] apduBuffer) {
		    DummyClass.dummyMethodStaticShort();
		  }
		},

		// Get the protocol
		new TestCase() {
		  public void run(byte[] apduBuffer) {
		    APDU.getProtocol();
		  }
		}
      };
	}
}