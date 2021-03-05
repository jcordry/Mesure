package benchs.jcapi.javacard_framework.util.arraycopy;

import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.SystemException;
import javacard.framework.Util;
import benchs.lib.templates.DummyClass;
import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

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
 * $HeadURL: svn+ssh://corentinboe@gforge.inria.fr/svn/mesureprv/src/benchs/jcapi/javacard_framework/util/applet/ArrayCopyTestApplet.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 1 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cboe $
 */


public class ArrayCopyTestApplet extends TemplateApplet {

	/** The first persistent array. */
	private byte[] persistent1 = null;
	
	/** The second persistent array. */
	private byte[] persistent2 = null;
	
	/** The first transient array. */
	private byte[] transient1 = null;
	
	/** the second transient array. */
	private byte[] transient2 = null;
	
	/** The current copy length. */
	private short lengthCopy = 0;
	
	/** The current source offset. */
	private short srcOff = 0;
	
	/** The current destination offset. */
	private short destOff = 0;
	
	/** The length of the several arrays. */
	private short arrayLength = (short)128;
	
	/** The length of the APDU buffer. */
	private short apduLength = 0;
	
	
	/**
	 * ArrayCopyTestApplet default constructor
	 * Only this class's install method should create the applet object.
	 * 
	 */
	private ArrayCopyTestApplet() {
		try{
			persistent1 = new byte[arrayLength];
			persistent2 = new byte[arrayLength];
			transient1 = JCSystem.makeTransientByteArray(arrayLength,JCSystem.CLEAR_ON_DESELECT);
			transient2 = JCSystem.makeTransientByteArray(arrayLength,JCSystem.CLEAR_ON_DESELECT);
		}
		catch(SystemException e){
			if (e.getReason()==SystemException.NO_TRANSIENT_SPACE)
				ISOException.throwIt((short)0x9B41);
			if (e.getReason()==SystemException.NO_RESOURCE)
				ISOException.throwIt((short)0x9B42);
			ISOException.throwIt((short)0x9B40);
		}
		register();
	}
	
	
	/**
	 * Gets parameters in APDU buffer and performs several 
	 * control on them.
	 * 
	 * @param buffer the APDU buffer
	 * @param array1 indicates if the first array is the APDU buffer
	 * @param array2 indicates if the second array is the APDU buffer
	 */
	private void init(byte[] buffer,boolean array1, boolean array2){
		if (apduLength == 0)
			apduLength = (short)buffer.length;
		
		short array1Length;
		short array2Length;
		if (array1)
			array1Length = apduLength;
		else array1Length = arrayLength;
		if (array2)
			array2Length = apduLength;
		else array2Length = arrayLength;
		
		lengthCopy = Util.makeShort(buffer[ISO7816.OFFSET_CDATA],buffer[ISO7816.OFFSET_CDATA+1]);
		srcOff = Util.makeShort(buffer[ISO7816.OFFSET_CDATA+2],buffer[ISO7816.OFFSET_CDATA+3]);
		destOff = Util.makeShort(buffer[ISO7816.OFFSET_CDATA+4],buffer[ISO7816.OFFSET_CDATA+5]);
		if (lengthCopy > 256 || srcOff > 172 || destOff > 172 || 
				(short)(lengthCopy + srcOff) > array1Length || (short)(lengthCopy + destOff) > array2Length)
			ISOException.throwIt(ISO7816.SW_DATA_INVALID);
	}
	
	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new ArrayCopyTestApplet();
	}
	
	
	/**
	 * @see TestCase#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				// Dummy case used to compute the overhead with persistent
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,false);
					}
					
					public void run(byte[] apduBuffer) {
						DummyClass.dummyMethodStaticShort(persistent1,srcOff,persistent2,destOff,lengthCopy);
					}
				},
				// Dummy case used to compute the overhead with persistent into transaction
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,false);
					}
					
					public void run(byte[] apduBuffer) {
						JCSystem.beginTransaction();
						DummyClass.dummyMethodStaticShort(persistent1,srcOff,persistent2,destOff,lengthCopy);
						JCSystem.commitTransaction();
					}
				},
				// Dummy case used to compute the overhead from APDU to persitent into transaction
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,true,false);
					}
					
					public void run(byte[] apduBuffer) {
						JCSystem.beginTransaction();
						DummyClass.dummyMethodStaticShort(apduBuffer,srcOff,persistent1,destOff,lengthCopy);
						JCSystem.commitTransaction();
					}
				},
				// Dummy case used to compute the overhead from transient to persistent into transaction
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,false);
					}
					
					public void run(byte[] apduBuffer) {
						JCSystem.beginTransaction();
						DummyClass.dummyMethodStaticShort(transient1,srcOff,persistent1,destOff,lengthCopy);
						JCSystem.commitTransaction();
					}
				},
				// Dummy case used to compute the overhead with APDU
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,true,true);
					}
					
					public void run(byte[] apduBuffer) {
						DummyClass.dummyMethodStaticShort(apduBuffer,srcOff,apduBuffer,destOff,lengthCopy);
					}
				},
				// Dummy case used to compute the overhead from APDU to transient
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,true,false);
					}
					
					public void run(byte[] apduBuffer) {
						DummyClass.dummyMethodStaticShort(apduBuffer,srcOff,transient1,destOff,lengthCopy);
					}
				},
				// Dummy case used to compute the overhead from transient to APDU
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,true);
					}
					
					public void run(byte[] apduBuffer) {
						DummyClass.dummyMethodStaticShort(transient1,srcOff,apduBuffer,destOff,lengthCopy);
					}
				},

				// Dummy case used to compute the overhead with transient
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,false);
					}
					
					public void run(byte[] apduBuffer) {
						DummyClass.dummyMethodStaticShort(transient1,srcOff,transient2,destOff,lengthCopy);
					}
				},
				// Dummy case used to compute the overhead from persistent to APDU
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,true);
					}
					
					public void run(byte[] apduBuffer) {
						DummyClass.dummyMethodStaticShort(persistent1,srcOff,apduBuffer,destOff,lengthCopy);
					}
				},
				// Dummy case used to compute the overhead from persistent to transient
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,false);
					}
					
					public void run(byte[] apduBuffer) {
						DummyClass.dummyMethodStaticShort(persistent1,srcOff,transient1,destOff,lengthCopy);
					}
				},
				
				
				
				
				// Copy from a persitent to a persitent outside of a transaction.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,false);
					}
					
					public void run(byte[] apduBuffer) {
						Util.arrayCopy(persistent1,srcOff,persistent2,destOff,lengthCopy);
					}
				},
				// Copy from a persitent to a persitent inside a transaction.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,false);
					}
					
					public void run(byte[] apduBuffer) {
						JCSystem.beginTransaction();
						Util.arrayCopy(persistent1,srcOff,persistent2,destOff,lengthCopy);
						JCSystem.commitTransaction();
					}
				},
				// Copy from a APDU to a persitent inside a transaction.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,true,false);
					}
					
					public void run(byte[] apduBuffer) {
						JCSystem.beginTransaction();
						Util.arrayCopy(apduBuffer,srcOff,persistent1,destOff,lengthCopy);
						JCSystem.commitTransaction();
					}
				},
				// Copy from a transient to a persitent inside a transaction.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,false);
					}
					
					public void run(byte[] apduBuffer) {
						JCSystem.beginTransaction();
						Util.arrayCopy(transient1,srcOff,persistent1,destOff,lengthCopy);
						JCSystem.commitTransaction();
					}
				},
				

				// Copy from APDU to APDU.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,true,true);
					}
					
					public void run(byte[] apduBuffer) {
						Util.arrayCopy(apduBuffer,srcOff,apduBuffer,destOff,lengthCopy);
					}
				},
				// Copy from APDU to transient.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,true,false);
					}
					
					public void run(byte[] apduBuffer) {
						Util.arrayCopy(apduBuffer,srcOff,transient1,destOff,lengthCopy);
					}
				},
				// Copy from transient to APDU.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,true);
					}
					
					public void run(byte[] apduBuffer) {
						Util.arrayCopy(transient1,srcOff,apduBuffer,destOff,lengthCopy);
					}
				},
				// Copy from transient to transient.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,false);
					}
					
					public void run(byte[] apduBuffer) {
						Util.arrayCopy(transient1,srcOff,transient2,destOff,lengthCopy);
					}
				},
				// Copy from persitent to APDU.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,true);
					}
					
					public void run(byte[] apduBuffer) {
						Util.arrayCopy(persistent1,srcOff,apduBuffer,destOff,lengthCopy);
					}
				},
				// Copy from a persitent to a transient.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false,false);
					}
					
					public void run(byte[] apduBuffer) {
						Util.arrayCopy(persistent1,srcOff,transient1,destOff,lengthCopy);
					}
				}
		};
		
	}
	
}
