package benchs.jcapi.javacard_security.signature.sign;

import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.Util;
import javacard.security.Signature;
import benchs.lib.templates.CryptoTemplateApplet;
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
 * $HeadURL: svn+ssh://corentinboe@gforge.inria.fr/svn/mesureprv/src/benchs/jcapi/javacard_framework/util/applet/ArrayCopyTestApplet.java $
 * Created: 19 février 2007
 * Author: TL 
 * $LastChangedRevision: 1 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cboe $
 */

/**
 * The purpose of this test is to measure the performances of the 
 * <tt>Signature.sign</tt> method.
 */
public class SignTestApplet extends CryptoTemplateApplet {
  /** The data to be signed. */
  private byte[] inputBuffer;
	
  /** The buffer where is stored the signature. */
  private byte[] outputBuffer; 
  
  private SignTestApplet() {
	inputBuffer = JCSystem.makeTransientByteArray((short)256,JCSystem.CLEAR_ON_DESELECT);
    outputBuffer = JCSystem.makeTransientByteArray((short)320,JCSystem.CLEAR_ON_DESELECT);
  }
	
  /** 
   * @see javacard.framework.Applet#install(byte[], short, byte) 
   */
  public static void install(byte[] bArray, short bOffset, byte bLength) {
    new SignTestApplet().register();
  } 
  
  private void init(byte[] apduBuffer, byte[] inputBuffer, byte[] outputBuffer, byte[] IV) {
    init(apduBuffer);  
    
    short sigLength = 0;
    switch (cryptoInfo.getAlgorithm()) {
	  case Signature.ALG_DES_MAC4_ISO9797_M1:
	  case Signature.ALG_DES_MAC4_ISO9797_M2:
	  case Signature.ALG_DES_MAC4_NOPAD:
	  case Signature.ALG_DES_MAC4_PKCS5: 
	    sigLength = 4;
	    break;
	  case Signature.ALG_DES_MAC8_ISO9797_M1:
	  case Signature.ALG_DES_MAC8_ISO9797_M2:
	  case Signature.ALG_DES_MAC8_NOPAD:
	  case Signature.ALG_DES_MAC8_PKCS5:
		sigLength = 8;
	    break;
	  case Signature.ALG_DSA_SHA: 
	  case Signature.ALG_RSA_MD5_PKCS1:
	  case Signature.ALG_RSA_MD5_RFC2409:
	  case Signature.ALG_RSA_RIPEMD160_ISO9796:
	  case Signature.ALG_RSA_RIPEMD160_PKCS1:
	  case Signature.ALG_RSA_SHA_ISO9796:
	  case Signature.ALG_RSA_SHA_PKCS1:
	  case Signature.ALG_RSA_SHA_RFC2409:
		sigLength = (short)(keyInfo.getKeyLength()/8);
	    break;
	  default:
	    break;
	}
		  
    if ((short)(cryptoInfo.getInOffset() + cryptoInfo.getInLength()) > (short)inputBuffer.length)
	  ISOException.throwIt(ISO7816.SW_DATA_INVALID);
	if ((short)(cryptoInfo.getOutOffset() + sigLength) > (short)outputBuffer.length)
	  ISOException.throwIt(ISO7816.SW_DATA_INVALID);
		  
	if (IV == null)
	  signature.init(key,Signature.MODE_SIGN);
	else
	  signature.init(key,Signature.MODE_SIGN,IV,(short)0,(short)IV.length);
  }
  
  /**
   * @see TestCase#getTestCases()
   */
  public TestCase[] getTestCases() {
    return new TestCase[] {
      // Dummy case used to compute the overhead for sign method
      new TestCase() {
        public void run(byte[] apduBuffer) {
		  dummy.dummyMethodShort(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		      outputBuffer,cryptoInfo.getOutOffset());
		}
	  },		
	  
	  // Sign x bytes of data without overlap
      new TestCase() {
        public void setUp(byte[] apduBuffer) {
		  init(apduBuffer,inputBuffer,outputBuffer,null);
		  Util.arrayFillNonAtomic(inputBuffer,(short)0,(short)inputBuffer.length,(byte)0xA5);
        }		
		public void run(byte[] apduBuffer) {
	      signature.sign(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
	          outputBuffer,cryptoInfo.getOutOffset());
        }
	  },
	  
	  // Sign x bytes of data without overlap when IV is set and is null
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer,inputBuffer,outputBuffer,IV_NULL);
		  Util.arrayFillNonAtomic(inputBuffer,(short)0,(short)inputBuffer.length,(byte)0xA5);
		}		
		public void run(byte[] apduBuffer) {
	      signature.sign(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
	          outputBuffer,cryptoInfo.getOutOffset());
		}
      },
      
	  // Sign x bytes of data without overlap when IV is set and is not null
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer,inputBuffer,outputBuffer,IV);
		  Util.arrayFillNonAtomic(inputBuffer,(short)0,(short)inputBuffer.length,(byte)0xA5);
		}		
		public void run(byte[] apduBuffer) {
	      signature.sign(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
	          outputBuffer,cryptoInfo.getOutOffset());
		}
      },
      
      // Dummy case used to compute the overhead for sign method
  	  // with overlap
      new TestCase() {
        public void run(byte[] apduBuffer) {
          Util.arrayFillNonAtomic(inputBuffer,(short)0,(short)inputBuffer.length,(byte)0xA5);
		  dummy.dummyMethodShort(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		    inputBuffer,cryptoInfo.getInOffset());
		}
	  },	
      
      // Sign x bytes of data with overlap 
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer,inputBuffer,inputBuffer,null);
	    }		
		public void run(byte[] apduBuffer) {
		  Util.arrayFillNonAtomic(inputBuffer,(short)0,(short)inputBuffer.length,(byte)0xA5);
		  signature.sign(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		    inputBuffer,cryptoInfo.getInOffset());
	    }
	  },
  	  
      // Dummy case used to compute the overhead for sign method
  	  // when the APDU buffer is used as input buffer
      new TestCase() {
        public void run(byte[] apduBuffer) {
          Util.arrayFillNonAtomic(apduBuffer,(short)0,(short)apduBuffer.length,(byte)0xA5);
		  dummy.dummyMethodShort(apduBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		      outputBuffer,cryptoInfo.getOutOffset());
		}
	  },	
	  
	  // Sign x bytes of data 
	  // when the APDU buffer is used as input buffer
	  new TestCase() {
		public void setUp(byte[] apduBuffer) {
		  init(apduBuffer,apduBuffer,outputBuffer,null);
	    }	  
	    public void run(byte[] apduBuffer) {
	      Util.arrayFillNonAtomic(apduBuffer,(short)0,(short)apduBuffer.length,(byte)0xA5);
	      signature.sign(apduBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		          outputBuffer,cryptoInfo.getOutOffset());
	    }
	  },	
	  
      // Dummy case used to compute the overhead for sign method
  	  // when the APDU buffer is used as output buffer
      new TestCase() {
        public void run(byte[] apduBuffer) {
		  dummy.dummyMethodShort(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		      apduBuffer,cryptoInfo.getOutOffset());
		}
	  },
	  
	  // Sign x bytes of data 
	  // when the APDU buffer is used as output buffer
	  new TestCase() {
		public void setUp(byte[] apduBuffer) {
		  init(apduBuffer,inputBuffer,apduBuffer,null);
		  Util.arrayFillNonAtomic(inputBuffer,(short)0,(short)inputBuffer.length,(byte)0xA5);
	    }	  
	    public void run(byte[] apduBuffer) {
	      signature.sign(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		          apduBuffer,cryptoInfo.getOutOffset());
	    }
	  }
    };
  }
}