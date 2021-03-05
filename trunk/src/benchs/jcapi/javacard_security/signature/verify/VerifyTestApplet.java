package benchs.jcapi.javacard_security.signature.verify;

import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.Util;
import javacard.security.KeyBuilder;
import javacard.security.Signature;
import benchs.lib.templates.KeyInfo;
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
 * <tt>Signature.verify</tt> method.
 */
public class VerifyTestApplet extends CryptoTemplateApplet {
  /** The data to be signed. */
  private byte[] inputBuffer;
	
  /** The buffer where is stored the signature. */
  private byte[] outputBuffer; 
  
  /** The signature length. */
  private short sigLength;
  
  private VerifyTestApplet() {
	inputBuffer = new byte[256];
    outputBuffer = new byte[320];
  }
  
  /**
   * Change the key to be used to perform the signature.
   * 
   * @param apduBuffer the APDU buffer where are read incoming data.
   */
  private void changeKey() {
    switch (keyInfo.getKeyType()) {
      case KeyBuilder.TYPE_DSA_PRIVATE:
    	keyInfo.setKeyType(KeyBuilder.TYPE_DSA_PUBLIC);
    	buildKey();
        break;
      case KeyBuilder.TYPE_DSA_PUBLIC:
    	keyInfo.setKeyType(KeyBuilder.TYPE_DSA_PRIVATE);
    	buildKey();
    	break;
      case KeyBuilder.TYPE_RSA_CRT_PRIVATE:
      case KeyBuilder.TYPE_RSA_PRIVATE:
    	keyInfo.setKeyType(KeyBuilder.TYPE_RSA_PUBLIC);
      	buildKey();
    	break;
      case KeyBuilder.TYPE_RSA_PUBLIC:
    	keyInfo.setKeyType(KeyBuilder.TYPE_RSA_PRIVATE);
        buildKey();
    	break;
      case KeyInfo.TYPE_RSA_CRT_PUBLIC:
    	keyInfo.setKeyType(KeyBuilder.TYPE_RSA_CRT_PRIVATE);
        buildKey();
    	break;
      default:
        break;
    }
  }
  
  private void init(byte[] apduBuffer, byte[] inputBuffer, byte[] outputBuffer, byte[] IV) {
	init(apduBuffer);  
	
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
	Util.arrayFillNonAtomic(inputBuffer,(short)0,(short)inputBuffer.length,(byte)0xA5);
	  
	if(cryptoInfo.getMode() == Signature.MODE_VERIFY) {
	  switch(cryptoInfo.getAlgorithm()){
		case Signature.ALG_DSA_SHA:
		case Signature.ALG_RSA_MD5_PKCS1:
		case Signature.ALG_RSA_MD5_RFC2409:
		case Signature.ALG_RSA_RIPEMD160_ISO9796:
		case Signature.ALG_RSA_RIPEMD160_PKCS1:
		case Signature.ALG_RSA_SHA_ISO9796:
		case Signature.ALG_RSA_SHA_PKCS1:
		case Signature.ALG_RSA_SHA_RFC2409:
		  signature.init(key,Signature.MODE_SIGN);
		  signature.sign(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		    outputBuffer,cryptoInfo.getOutOffset());
		  changeKey();
		  break;
		case Signature.ALG_DES_MAC4_ISO9797_M1:
		case Signature.ALG_DES_MAC4_ISO9797_M2:
		case Signature.ALG_DES_MAC4_NOPAD:
		case Signature.ALG_DES_MAC4_PKCS5:
		case Signature.ALG_DES_MAC8_ISO9797_M1:
		case Signature.ALG_DES_MAC8_ISO9797_M2:
		case Signature.ALG_DES_MAC8_NOPAD:
		case Signature.ALG_DES_MAC8_PKCS5:
		  if (IV == null)
            signature.init(key,Signature.MODE_SIGN);
		  else
		    signature.init(key,Signature.MODE_SIGN,IV,(short)0,(short)IV.length);
		  signature.sign(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		    outputBuffer,cryptoInfo.getOutOffset());
		  break;
		default:
		  break;
	  }
	}
	
	if (IV == null)
	  signature.init(key,Signature.MODE_VERIFY);
	else
      signature.init(key,Signature.MODE_VERIFY,IV,(short)0,(short)IV.length);
  }
  
  /** 
   * @see javacard.framework.Applet#install(byte[], short, byte) 
   */
  public static void install(byte[] bArray, short bOffset, byte bLength) {
    new VerifyTestApplet().register();
  } 
  
  /**
   * @see TestCase#getTestCases()
   */
  public TestCase[] getTestCases() {
    return new TestCase[] {
      // Dummy case used to compute the overhead 	
      new TestCase() {
        public void run(byte[] apduBuffer) {
		  dummy.dummyMethodShort(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		    outputBuffer,cryptoInfo.getOutOffset(),sigLength);
		}
	  },	
	  
	  // Verify the signature produced by x bytes of data 
      new TestCase() {
        public void setUp(byte[] apduBuffer) {
          init(apduBuffer,inputBuffer,outputBuffer,null);
        }		
		public void run(byte[] apduBuffer) {
	      signature.verify(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
	          outputBuffer,cryptoInfo.getOutOffset(),sigLength);
        }
	  },
	  
	  // Verify the signature produced by x bytes of data when IV is set and is null
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer,inputBuffer,outputBuffer,IV_NULL);
		}		
		public void run(byte[] apduBuffer) {
		  signature.verify(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		      outputBuffer,cryptoInfo.getOutOffset(),sigLength);
		}
      },
      
      // Verify the signature produced by x bytes of data when IV is set and is not null
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
		  init(apduBuffer,inputBuffer,outputBuffer,IV);
		}		
		public void run(byte[] apduBuffer) {
		  signature.verify(inputBuffer,cryptoInfo.getInOffset(),cryptoInfo.getInLength(),
		      outputBuffer,cryptoInfo.getOutOffset(),sigLength);
		}
      },
     
    };
  }
}