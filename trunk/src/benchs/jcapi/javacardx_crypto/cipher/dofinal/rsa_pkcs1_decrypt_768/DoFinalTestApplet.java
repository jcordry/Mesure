package benchs.jcapi.javacardx_crypto.cipher.dofinal.rsa_pkcs1_decrypt_768;
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
import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.Util;
import javacard.security.KeyBuilder;
import javacardx.crypto.Cipher;
import benchs.lib.templates.CryptoTemplateApplet;
import benchs.lib.templates.KeyInfo;
import benchs.lib.templates.TestCase;

public class DoFinalTestApplet extends CryptoTemplateApplet{
	/** The data to be enciphered/deciphered. */
	private byte[] inputBuffer;
	
	/** The buffer where is stored the enciphered/deciphered data. */
	private byte[] outputBuffer; 
	
	/** A temporary buffer. */
	private byte[] tmpBuffer;
	
	/** The length of the enciphered data. */
	private short length;
	
	private DoFinalTestApplet() {
	  tmpBuffer = new byte[(short)128];
      inputBuffer = JCSystem.makeTransientByteArray((short)128,JCSystem.CLEAR_ON_DESELECT);
	  outputBuffer = JCSystem.makeTransientByteArray((short)128,JCSystem.CLEAR_ON_DESELECT);
	}
	
	/**
	 * Change the key to be used to encipher / decipher.
	 * 
	 * @param apduBuffer the APDU buffer where are read incoming data.
	 */
	protected void changeKey() {
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
	
	protected void init(byte[] apduBuffer, byte[] inputBuffer, byte[] IV) {
	  init(apduBuffer);		
	  if ((short)(cryptoInfo.getInOffset() + cryptoInfo.getInLength()) > (short)inputBuffer.length)
	    ISOException.throwIt(ISO7816.SW_DATA_INVALID);
	  Util.arrayFillNonAtomic(tmpBuffer,(short)0,(short)tmpBuffer.length,(byte)0xA5);
	  length = cryptoInfo.getInLength();
	  if(cryptoInfo.getMode() == Cipher.MODE_DECRYPT) {
	    switch(cryptoInfo.getAlgorithm()){
		  case Cipher.ALG_RSA_PKCS1:
		  case Cipher.ALG_RSA_ISO14888:
		  case Cipher.ALG_RSA_ISO9796:
		  case Cipher.ALG_RSA_NOPAD:
			if (IV == null)
			  cipher.init(key,Cipher.MODE_ENCRYPT);
			else 
			  cipher.init(key,Cipher.MODE_ENCRYPT,IV,(short)0,(short)IV.length);
			length = cipher.doFinal(tmpBuffer,(short)0,cryptoInfo.getInLength(),
			    tmpBuffer,(short)0);
			changeKey();
			break;  
		  case Cipher.ALG_DES_ECB_ISO9797_M2:
		  case Cipher.ALG_DES_CBC_ISO9797_M2:
		  case Cipher.ALG_DES_ECB_ISO9797_M1:
		  case Cipher.ALG_DES_CBC_ISO9797_M1:
		  case Cipher.ALG_DES_CBC_NOPAD:
		  case Cipher.ALG_DES_ECB_NOPAD:
			  if (IV == null)
				  cipher.init(key,Cipher.MODE_ENCRYPT);
				else 
				  cipher.init(key,Cipher.MODE_ENCRYPT,IV,(short)0,(short)IV.length);
				length = cipher.doFinal(tmpBuffer,(short)0,cryptoInfo.getInLength(),
				    tmpBuffer,(short)0);
			  break;
		  default:
		    break;
	    }
	  }
	  if (IV == null)
	    cipher.init(key,cryptoInfo.getMode());
	  else
		cipher.init(key,cryptoInfo.getMode(),IV,(short)0,(short)IV.length);
	}
	
	/** 
	 * @see javacard.framework.Applet#install(byte[], short, byte) 
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new DoFinalTestApplet().register();
	} 

	public TestCase[] getTestCases() {
		return new TestCase[] {
		  // Dummy case used to compute the overhead for doFinal method
		  new TestCase() {
		    public void run(byte[] buffer) {
			  dummy.dummyMethodShort(inputBuffer,cryptoInfo.getInOffset(),length,
			      outputBuffer,cryptoInfo.getOutOffset());
			}
		  },		
				
		  // Encipher/decipher x bytes of data without overlap
		  new TestCase() {
		    public void setUp(byte[] apduBuffer) {
			  init(apduBuffer,inputBuffer,null);
			  Util.arrayCopyNonAtomic(tmpBuffer,(short)0,inputBuffer,cryptoInfo.getInOffset(),length);
			}		
			public void run(byte[] buffer) {
			  cipher.doFinal(inputBuffer,cryptoInfo.getInOffset(),length,
			      outputBuffer,cryptoInfo.getOutOffset());
			}
		  },
		  
		  // Encipher/decipher x bytes of data without overlap when IV is set and is null
		  new TestCase() {
		    public void setUp(byte[] apduBuffer) {
			  init(apduBuffer,inputBuffer,IV_NULL);
			  Util.arrayCopyNonAtomic(tmpBuffer,(short)0,inputBuffer,cryptoInfo.getInOffset(),length);
			}		
			public void run(byte[] apduBuffer) {
			  cipher.doFinal(inputBuffer,cryptoInfo.getInOffset(),length,
			      outputBuffer,cryptoInfo.getOutOffset());
			}
	      },
	      
	      // Encipher/decipher x bytes of data without overlap when IV is set and is not null
		  new TestCase() {
		    public void setUp(byte[] apduBuffer) {
			  init(apduBuffer,inputBuffer,IV);
			  Util.arrayCopyNonAtomic(tmpBuffer,(short)0,inputBuffer,cryptoInfo.getInOffset(),length);
			}		
			public void run(byte[] apduBuffer) {
			  cipher.doFinal(inputBuffer,cryptoInfo.getInOffset(),length,
			      outputBuffer,cryptoInfo.getOutOffset());
			}
	      },
	      
          // Dummy case used to compute the overhead for doFinal method
	  	  // with overlap
	      new TestCase() {
	        public void run(byte[] apduBuffer) {
	          Util.arrayCopyNonAtomic(tmpBuffer,(short)0,inputBuffer,cryptoInfo.getInOffset(),length);
			  dummy.dummyMethodShort(inputBuffer,cryptoInfo.getInOffset(),length,
			    inputBuffer,cryptoInfo.getInOffset());
			}
		  },	
	      
	      // Encipher/decipher x bytes of data with overlap 
		  new TestCase() {
		    public void setUp(byte[] apduBuffer) {
			  init(apduBuffer,inputBuffer,null);
		    }		
			public void run(byte[] apduBuffer) {
			  Util.arrayCopyNonAtomic(tmpBuffer,(short)0,inputBuffer,cryptoInfo.getInOffset(),length);
			  cipher.doFinal(inputBuffer,cryptoInfo.getInOffset(),length,
			    inputBuffer,cryptoInfo.getInOffset());
		    }
		  },
	  	  
	      // Dummy case used to compute the overhead for doFinal method
	  	  // when the APDU buffer is used as input buffer
	      new TestCase() {
	        public void run(byte[] apduBuffer) {
	          Util.arrayCopyNonAtomic(tmpBuffer,(short)0,apduBuffer,cryptoInfo.getInOffset(),length);
			  dummy.dummyMethodShort(apduBuffer,cryptoInfo.getInOffset(),length,
			      outputBuffer,cryptoInfo.getOutOffset());
			}
		  },	
		  
		  // Encipher/decipher x bytes of data 
		  // when the APDU buffer is used as input buffer
		  new TestCase() {
			public void setUp(byte[] apduBuffer) {
			  init(apduBuffer,apduBuffer,null);
		    }	  
		    public void run(byte[] apduBuffer) {
		      Util.arrayCopyNonAtomic(tmpBuffer,(short)0,apduBuffer,cryptoInfo.getInOffset(),length);
		      cipher.doFinal(apduBuffer,cryptoInfo.getInOffset(),length,
			      outputBuffer,cryptoInfo.getOutOffset());
		    }
		  },	
		  
	      // Dummy case used to compute the overhead for doFinal method
	  	  // when the APDU buffer is used as output buffer
	      new TestCase() {
	        public void run(byte[] apduBuffer) {
			  dummy.dummyMethodShort(inputBuffer,cryptoInfo.getInOffset(),length,
			      apduBuffer,cryptoInfo.getOutOffset());
			}
		  },
		  
		  // Encipher/decipher x bytes of data
		  // when the APDU buffer is used as output buffer
		  new TestCase() {
			public void setUp(byte[] apduBuffer) {
			  init(apduBuffer,inputBuffer,null);
			  Util.arrayCopyNonAtomic(tmpBuffer,(short)0,inputBuffer,cryptoInfo.getInOffset(),length);
		    }	  
		    public void run(byte[] apduBuffer) {
		      cipher.doFinal(inputBuffer,cryptoInfo.getInOffset(),length,
			    apduBuffer,cryptoInfo.getOutOffset());
		    }
		  }
	    };  
	}
}
