package benchs.jcre.local.cst;

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

import benchs.lib.templates.TemplateApplet;
import javacard.framework.*;

public class CstApplet extends TemplateApplet {

	/**
	 * This field is used to set the loop range
	 */
	private short [] iterationRange;
	
	/** The parameter used to test empty loops */
	private static final short P1_emptyLoop = (short) 0x0000;

	/** The parameter used to test sconst */
	private static final short P1_sconst = (short) 0x0010;
	
	/** The parameter used to test aconst_null */
	private static final short P1_aconst= (short) 0x0020;
	
	/** The parameter used to test bspush */
	private static final short P1_bspush = (short) 0x0030;
	
	/** The parameter used to test sspush */
	private static final short P1_sspush = (short) 0x0040;
	
	/** 
	 * The constant used to mask case information contained in the
     * P1 byte of commands.
     */
	private static final short P1_FILTER = (short) 0x00FC;
	
	/**
	 * Cst default constructor
	 * Only this class's install method should create the applet object.
	 */
	protected CstApplet (byte[] buffer, short offset, byte length) {
		iterationRange = new short [16];
		iterationRange[0] = 0;
		iterationRange[1] = 1;
		iterationRange[2] = 3;
		iterationRange[3] = 7;
		iterationRange[4] = 15;
		iterationRange[5] = 31;
		iterationRange[6] = 63;
		iterationRange[7] = 127;
		iterationRange[8] = 255;
		iterationRange[9] = 511;
		iterationRange[10] = 1023;
		iterationRange[11] = 2047;
		iterationRange[12] = 4095;
		iterationRange[13] = 8191;
		iterationRange[14] = 16383;
		iterationRange[15] = 32767;
		register();
	}

	/** The method used to test empty loops */
	private void emptyLoop () {}

	/** The method used to test sconst */
	private void callsconst () {}

	/** The method used to test aconst */
	private void callaconst () {}

	/** The method used to test bspush */
	private void callbspush () {}

	/** The method used to test sspush */
	private void callsspush () {}
	
	
	/**
	 * Method installing the applet.
	 * @param bArray the array constaining installation parameters
	 * @param bOffset the starting offset in bArray
	 * @param bLength the length in bytes of the data parameter in bArray
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) throws ISOException{
		/* applet  instance creation */
		new CstApplet (bArray, bOffset, (byte)bLength );
	}

	/**
	 * Processes the specified test case.
	 * 
	 * @param apdu the <tt>APDU</tt> object.
	 * @param p1 the number of the test case to execute.
	 * @return the result of the executed test case.
	 */
	protected byte process(byte[] apduBuffer, short ins, short p1, short p2) {
		short loop1, loop2;
		short i, j; 
		loop1 = (short) (apduBuffer[ISO7816.OFFSET_P2] & 0xF0);
		loop2 = iterationRange[apduBuffer[ISO7816.OFFSET_P2] & 0x0F];
		switch (p1 & P1_FILTER) {
		case P1_emptyLoop :
			for(i = 0; i < loop1; i++) {
				for(j = 0; j < loop2; j++) {
					emptyLoop();
				}
			}
			break;
		case P1_sconst :
			for(i = 0; i < loop1; i++) {
				for(j = 0; j < loop2; j++) {
					callsconst();
				}
			}
			break;
		case P1_aconst :
			for(i = 0; i < loop1; i++) {
				for(j = 0; j < loop2; j++) {
					callaconst();
				}
			}
			break;
		case P1_bspush :
			for(i = 0; i < loop1; i++) {
				for(j = 0; j < loop2; j++) {
					callbspush();
				}
			}
			break;
		case P1_sspush :
			for(i = 0; i < loop1; i++) {
				for(j = 0; j < loop2; j++) {
					callsspush();
				}
			}
			break;
		default:
			ISOException.throwIt(ISO7816.SW_INCORRECT_P1P2);
			break;
		}
		return 0;
	}
}