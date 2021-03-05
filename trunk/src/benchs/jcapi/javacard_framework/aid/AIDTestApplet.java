package benchs.jcapi.javacard_framework.aid;

import javacard.framework.AID;
import javacard.framework.ISO7816;
import javacard.framework.JCSystem;
import javacard.framework.Util;

import benchs.lib.templates.ByteArrayReader;
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
 * Created: 10 mai 2007
 * Author: TL 
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

/**
 * This class is dedicated to measurements of the <tt>AID</tt> methods.
 */
public class AIDTestApplet extends TemplateApplet {
	
  /** The dummy class. */
  private DummyClass dummy;
  
  /** The AID. */
  private AID aid;
  
  /** The AID information. */
  private AIDInfo info;
  
  /** The array where is stored the AID information. */
  private byte[] value;
  
  /** The starting offset in the <tt>value</tt> array for the AID information. */
  private short offset;
  
  /** The length of the AID information in the <tt>value</tt> array. */
  private byte length;
  
  /** The reader used to read AID information. */
  private ByteArrayReader reader;

  private AIDTestApplet() {
	info = new AIDInfo();
    dummy = new DummyClass();
    value = new byte[(short)16];
  }
	
  /**
   * @see javacard.framework.Applet#install(byte[], short, byte)
   */
  public static void install(byte[] bArray, short bOffset, byte bLength) {
    new AIDTestApplet().register();
  }
  
  /**
   * Reads AID information among incoming data and builds a new 
   * <tt>AID</tt> object.
   * 
   * @param apduBuffer the APDU buffer where are read incoming data.
   */
  private void init(byte[] apduBuffer) {
	if (reader == null)
	  reader = new ByteArrayReader(apduBuffer,ISO7816.OFFSET_CDATA);
	else
	  reader.reset(apduBuffer,ISO7816.OFFSET_CDATA);
	info.read(reader);
	byte[] value = info.getAID();
	Util.arrayCopyNonAtomic(value,(short)0,this.value,(short)0,(short)value.length);
	if ((aid == null) || !aid.equals(value,(short)0,(byte)value.length))
	  aid = new AID(value,(short)0,(byte)value.length);
	offset = info.getOffset();
	length = info.getLength();
  }
 
  /**
   * @see Test#getTestCases()
   */
  public TestCase[] getTestCases() {
	  return new TestCase[]{
	  // Dummy case used to compute the overhead 
	  // for 'partialEquals'
	  // when the APDU buffer is passed as parameter
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		}
		public void run(byte[] apduBuffer) {
		  Util.arrayCopyNonAtomic(value,(short)0,apduBuffer,offset,length);
		  dummy.dummyMethodShort(apduBuffer,offset,length);
		}
	  },	
			  
	  // Compares the AID 
	  // with 'partialEquals'
	  // on Java Card RE-owned instances
	  // when the APDU buffer is passed as parameter
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  aid = JCSystem.getAID();
		  aid.getBytes(value,(short)0);
		}
		public void run(byte[] apduBuffer) {
		  Util.arrayCopyNonAtomic(value,(short)0,apduBuffer,offset,length);
		  aid.partialEquals(apduBuffer,offset,length);
		}
	  },	  
	  
	  // Compares the AID
      // with 'partialEquals'
	  // on proprietary instances
	  // when the APDU buffer is passed as parameter
	  new TestCase() {
		public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		}
		public void run(byte[] apduBuffer) {
		  Util.arrayCopyNonAtomic(value,(short)0,apduBuffer,offset,length);
	      aid.partialEquals(apduBuffer,offset,length);
	    }
	  },
	  
	  // Dummy case used to compute the overhead 
	  // for 'getBytes'
	  // when the APDU buffer is passed as parameter
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		}
		public void run(byte[] apduBuffer) {
		  dummy.dummyMethodShort(apduBuffer,offset);
		}
	  },	
			  
	  // Gets the AID 
	  // with 'getBytes'
	  // on Java Card RE-owned instances
	  // when the APDU buffer is passed as parameter
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  aid = JCSystem.getAID();
		}
		public void run(byte[] apduBuffer) {
		  aid.getBytes(apduBuffer,offset);
		}
	  },	  
	  
	  // Gets the AID
      // with 'getBytes'
	  // on proprietary instances
	  // when the APDU buffer is passed as parameter
	  new TestCase() {
		public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  value = info.getAID();
		  offset = info.getOffset();
		  aid = new AID(value,(short)0,(byte)value.length);
		}
		public void run(byte[] apduBuffer) {
	      aid.getBytes(apduBuffer,offset);
	    }
	  },
	};
  }
}