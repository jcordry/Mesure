package benchs.jcapi.javacard_framework.util.setshort;

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


public class SetShortTestApplet extends TemplateApplet {
	/** The short to set. */
	private final static short SHORT = (byte)0x57E4;
	
	/** The first persistent array. */
	private byte[] persistentArray = null;
	
	/** The transient array. */
	private byte[] transientArray = null;
	
	/** The offset. **/
	private short offset = 0;
	
	/** The length of the several arrays. */
	private short arrayLength = (short)200;
	
	/**
	 * SetShortTestApplet default constructor
	 * Only this class's install method should create the applet object.
	 * 
	 */
	private SetShortTestApplet() {
		persistentArray = new byte[arrayLength];
		transientArray = JCSystem.makeTransientByteArray((short)arrayLength,JCSystem.CLEAR_ON_DESELECT);
		register();
	}
	
	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new SetShortTestApplet();
	}
	
	/**
	 * Gets parameters in APDU buffer and performs several 
	 * control on them.
	 * 
	 * @param buffer the APDU buffer
	 */
	private void init(byte[] buffer,boolean isApdu){		
		offset = Util.makeShort(buffer[ISO7816.OFFSET_CDATA],buffer[ISO7816.OFFSET_CDATA+1]);
		if (isApdu)
		{
			if (offset > (short)(buffer.length - 2))
				ISOException.throwIt(ISO7816.SW_DATA_INVALID);
		}
		else
			if (offset > (short)(arrayLength-2))
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
						  DummyClass.dummyMethodStaticShort(persistentArray,offset,SHORT);
					  }
				  },
				  // Dummy case used to compute the overhead with persistent in a transaction
				  new TestCase() {
					  public void setUp(byte[] apduBuffer) {
						  init(apduBuffer,false);
					  }
					  public void run(byte[] apduBuffer) {
						  JCSystem.beginTransaction();
						  DummyClass.dummyMethodStaticShort(persistentArray,offset,SHORT);
						  JCSystem.commitTransaction();
					  }
				  },
				  // Dummy case used to compute the overhead with transient	
				  new TestCase() {
					  public void setUp(byte[] apduBuffer) {
						  init(apduBuffer,false);
					  }
					  public void run(byte[] apduBuffer) {
						  DummyClass.dummyMethodStaticShort(transientArray,offset,SHORT);
					  }
				  },
				  // Dummy case used to compute the overhead with apdu	
				  new TestCase() {
					  public void setUp(byte[] apduBuffer) {
						  init(apduBuffer,true);
					  }
					  public void run(byte[] apduBuffer) {
						  DummyClass.dummyMethodStaticShort(apduBuffer,offset,SHORT);
					  }
				  },
				  
				
				// Set a short in a persistent array outside of a transaction.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false);
					}
					
					public void run(byte[] apduBuffer) {
						Util.setShort(persistentArray,offset,SHORT);
					}
				},
				
				// Set a short in a persistent array inside a transaction.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false);
					}
					
					public void run(byte[] apduBuffer) {
						JCSystem.beginTransaction();
						Util.setShort(persistentArray,offset,SHORT);
						JCSystem.commitTransaction();
					}
				},
				
				// Set a short in a transient array.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,false);
					}
					
					public void run(byte[] apduBuffer) {
						Util.setShort(transientArray,offset,SHORT);
					}
				},
				
				// Set a short in apdu.
				new TestCase() {
					public void setUp(byte[] apduBuffer) {
						init(apduBuffer,true);
					}
					
					public void run(byte[] apduBuffer) {
						Util.setShort(apduBuffer,offset,SHORT);
					}
				}
		};
		
	}
	
}
