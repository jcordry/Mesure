package benchs.jcapi.javacard_framework.util.getshort;

import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
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


public class GetShortTestApplet extends TemplateApplet {
	
	/** The first persistent array. */
	private byte[] persistentArray = null;
	
	/** The transient array. */
	private byte[] transientArray = null;
	
	/** The offset. */
	private short offset = 0;
	
	/** The length of the several arrays. */
	private short arrayLength = (short)200;
	
	/** The length of the APDU buffer. */
	private short apduLength = 0;
	
	/**
	 * GetShortTestApplet default constructor
	 * Only this class's install method should create the applet object.
	 * 
	 */
	private GetShortTestApplet() {
		persistentArray = new byte[arrayLength];
		transientArray = JCSystem.makeTransientByteArray((short)arrayLength,JCSystem.CLEAR_ON_DESELECT);
		Util.arrayFillNonAtomic(persistentArray,(short)0,(short)arrayLength,(byte)1);
		Util.arrayFillNonAtomic(transientArray,(short)0,(short)arrayLength,(byte)1);
		register();
	}
	
	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new GetShortTestApplet();
	}
	
	
	/**
	 * Gets parameters in APDU buffer and performs several 
	 * control on them.
	 * 
	 * @param buffer the APDU buffer
	 * @param array indicates if the first array is the APDU buffer
	 */
	private void init(byte[] buffer,boolean array){
		if (apduLength == 0)
			apduLength = (short)buffer.length;
		
		short array1Length;
		if (array)
			array1Length = apduLength;
		else array1Length = arrayLength;
		
		offset = Util.makeShort(buffer[ISO7816.OFFSET_CDATA],buffer[ISO7816.OFFSET_CDATA+1]);
		if (offset > (short)(array1Length-1))
			ISOException.throwIt(ISO7816.SW_DATA_INVALID);
	}
	
	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				  // Dummy case used to compute the overhead with persistent
				  new TestCase() {
					  public void setUp(byte[] apduBuffer) {
						  init(apduBuffer,false);
					  }
					  public void run(byte[] apduBuffer) {
						  DummyClass.dummyMethodStaticShort(persistentArray,offset);
					  }
				  },
				  // Dummy case used to compute the overhead with transient	
				  new TestCase() {
					  public void setUp(byte[] apduBuffer) {
						  init(apduBuffer,false);
					  }
					  public void run(byte[] apduBuffer) {
						  DummyClass.dummyMethodStaticShort(transientArray,offset);
					  }
				  },
				  // Dummy case used to compute the overhead with APDU	
				  new TestCase() {
					  public void setUp(byte[] apduBuffer) {
						  init(apduBuffer,true);
					  }
					  public void run(byte[] apduBuffer) {
						  DummyClass.dummyMethodStaticShort(apduBuffer,offset);
					  }
				  },
				  // Dummy case used to compute the overhead with persistent into a transaction
				  new TestCase() {
					  public void setUp(byte[] apduBuffer) {
						  init(apduBuffer,false);
					  }
					  public void run(byte[] apduBuffer) {
						  JCSystem.beginTransaction();
						  DummyClass.dummyMethodStaticShort(persistentArray,offset);
						  JCSystem.commitTransaction();
					  }
				  },
				  
				
				// Get a short in a persistent array.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false);
					}
					
					public void run(byte[] apduBuffer) {
						Util.getShort(persistentArray,offset);
					}
				},
				
//				 Get a short in a transient array.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false);
					}
					
					public void run(byte[] apduBuffer) {
						Util.getShort(transientArray,offset);
					}
				},
				
//				 Get a short in a transient array.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,true);
					}
					
					public void run(byte[] apduBuffer) {
						Util.getShort(apduBuffer,offset);
					}
				},
				
				//Get a short in a persistent array into a transaction.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false);
					}
					
					public void run(byte[] apduBuffer) {
						JCSystem.beginTransaction();
						Util.getShort(persistentArray,offset);
						JCSystem.commitTransaction();
					}
				}
		};
		
	}
	
}
