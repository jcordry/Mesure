package benchs.jcapi.javacard_framework.ownerpin;

import javacard.framework.ISO7816;
import javacard.framework.OwnerPIN;
import javacard.framework.Util;

import benchs.lib.templates.ByteArrayReader;
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
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/benchs/jcre/overhead/applet/ComTestApplet.java $
 * Created: 6 octobre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class is dedicated to measurements of the <tt>OwnerPIN</tt> methods.
 * We can here expect a high variability of the performances, since the 
 * <tt>OwnerPIN</tt> class is highly sensitive, which means that its operations 
 * will be heavily protected in the most secure platforms.
 */
public class OwnerPINTestApplet extends TemplateApplet {
	
  /** The dummy class. */
  private DummyClass dummy;
	
  /** The PIN. */
  private OwnerPIN pin;
  
  /** The PIN information. */
  private PINInfo info;
  
  /** The PIN try limit. */
  private byte tryLimit;
  
  /** The maximum PIN size. */
  private byte maxPINSize;
  
  /** The reader used to read PIN information. */
  private ByteArrayReader reader;
  
  /** The array where is stored PIN information. */
  private byte[] value;
  
  /** The starting offset in the <tt>value</tt> array for the PIN information. */
  private short offset;
  
  /** The length of the PIN information in the <tt>value</tt> array. */
  private byte length;

  private OwnerPINTestApplet() {
    info = new PINInfo();
    dummy = new DummyClass();
  }
	
  /**
   * @see javacard.framework.Applet#install(byte[], short, byte)
   */
  public static void install(byte[] bArray, short bOffset, byte bLength) {
    new OwnerPINTestApplet().register();
  }
  
  /**
   * Reads PIN information among incoming data, builds a new 
   * <tt>OwnerPIN</tt> object with the read PIN try limit and maximum PIN size
   * as parameters, updates it with the read reference value, and checks it
   * with the read presented value if required.
   * 
   * @param apduBuffer the APDU buffer where are read incoming data.
   */
  private void init(byte[] apduBuffer) {
	if (reader == null)
	  reader = new ByteArrayReader(apduBuffer,ISO7816.OFFSET_CDATA);
	else
	  reader.reset(apduBuffer,ISO7816.OFFSET_CDATA);
	byte tryLimit = info.getTryLimit();
	byte maxSize = info.getMaxSize();
	info.read(reader);
	if ((info.getTryLimit() != tryLimit) ||
	    (info.getMaxSize() != maxSize))
	  pin = new OwnerPIN(info.getTryLimit(),info.getMaxSize());
	byte[] reference = info.getReferencePIN();
	pin.update(reference,(short)0,(byte)reference.length);
    byte[] presented = info.getPresentedPIN();
    for (short s = 0; s < info.getTriesRemaining(); s++)
      pin.check(presented,(short)0,(byte)presented.length);
  }
 
  /**
   * @see Test#getTestCases()
   */
  public TestCase[] getTestCases() {
	  return new TestCase[]{
			  
	  // Dummy case used to compute the overhead 
	  // for 'check' and 'update'
	  new TestCase() {
		public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  value = info.getPresentedPIN();
		  offset = (short)0;
		  length = (byte)value.length;
	    }
	    public void run(byte[] apduBuffer) {
		  dummy.dummyMethodShort(value,offset,length);
		}
	  },	
	  
	  // Check the PIN
	  new TestCase() {
		public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  value = info.getPresentedPIN();
		  offset = (short)0;
		  length = (byte)value.length;
		}
		public void run(byte[] apduBuffer) {
	      pin.check(value,offset,length);
	    }
	  },
	  
	  // Updates a PIN
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
	      init(apduBuffer);
	      value = info.getReferencePIN();
	      offset = (short)0;
	      length = (byte)value.length;
	    }
	    public void run(byte[] apduBuffer) {
		  pin.update(value,offset,length);
	    }
	  },
	  
      // Dummy case used to compute the overhead 
	  // for 'check' and 'update'
	  // when the APDU buffer is passed as parameter
	  new TestCase() {
		public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  value = info.getPresentedPIN();
		  offset = (short)0;
		  length = (byte)value.length;
	    }
	    public void run(byte[] apduBuffer) {
	      Util.arrayCopyNonAtomic(value,offset,apduBuffer,ISO7816.OFFSET_CDATA,length);
		  dummy.dummyMethodShort(apduBuffer,ISO7816.OFFSET_CDATA,length);
		}
	  },	
	  
	  // Check the PIN
	  // when the APDU buffer is passed as parameter
	  new TestCase() {
		public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  value = info.getPresentedPIN();
		  offset = (short)0;
		  length = (byte)value.length;
		}
		public void run(byte[] apduBuffer) {
		  Util.arrayCopyNonAtomic(value,offset,apduBuffer,ISO7816.OFFSET_CDATA,length);
	      pin.check(apduBuffer,ISO7816.OFFSET_CDATA,length);
	    }
	  },
	  
	  // Dummy case used to compute the overhead 
	  // for 'check' + 'resetAndUnblock'
	  new TestCase() {
		public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  value = info.getPresentedPIN();
		  offset = (short)0;
		  length = (byte)value.length;
	    }
	    public void run(byte[] apduBuffer) {
		  pin.check(value,offset,length);
		  dummy.dummyMethodVoid();
		}
	  },	

      // Check and unblock the PIN
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
	      init(apduBuffer);
		  value = info.getPresentedPIN();
		  offset = (short)0;
		  length = (byte)value.length;
		}
		public void run(byte[] apduBuffer) {
		  pin.check(value,offset,length);
		  pin.resetAndUnblock();
		}
	  },
	  
	  // Dummy case used to compute the overhead 
	  // for 'getTriesRemaining', 'isValidated', 'reset', 'resetAndUnblock'
	  new TestCase() {
	    public void run(byte[] apduBuffer) {
		  dummy.dummyMethodShort();
		}
	  },	
	
	  // Get remaining tries 
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
	    }
		public void run(byte[] apduBuffer) {
	      pin.getTriesRemaining();
	    }
	  },	  
	  
	  // Get validated flag
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		}
	    public void run(byte[] apduBuffer) {
		  pin.isValidated();
		}
	  },	  
	  
	  // Reset the PIN
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		}
	    public void run(byte[] apduBuffer) {
		  pin.reset();
		}
	  },	  

	  // Unblock a PIN
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
	      init(apduBuffer);
	    }
	    public void run(byte[] apduBuffer) {
	      pin.resetAndUnblock();
	    }
	  },
	  
      // Dummy case used to compute the overhead
	  // for '<init>'
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
	      init(apduBuffer);
	      tryLimit = info.getTryLimit();
		  maxPINSize = info.getMaxSize();
	    }
	    public void run(byte[] apduBuffer) {
		  DummyClass.dummyMethodStaticVoid(tryLimit,maxPINSize);
	    }
	  },
	  
	  // Builds a PIN
	  new TestCase() {
		public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  tryLimit = info.getTryLimit();
		  maxPINSize = info.getMaxSize();
		}
	    public void run(byte[] apduBuffer) {
	      new OwnerPIN(tryLimit,maxPINSize);
	    }
	  }
	};
  }
}