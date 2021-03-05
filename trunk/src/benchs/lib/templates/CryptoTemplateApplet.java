package benchs.lib.templates;

import javacard.security.MessageDigest;
import javacard.security.RandomData;
import javacard.security.Signature;
import javacardx.crypto.Cipher;

/**
 * This class should be extended by all test applets whose purpose is to measure
 * performances of cryptographic algorithms.
 */
public abstract class CryptoTemplateApplet extends KeyTemplateApplet{
	//===========================================================================
	// Default values for algorithms
	//===========================================================================
	
	/** 
	 * A non null initial vector.
	 **/
	protected final byte[] IV = { 
			(byte)0xc9,(byte)0x36,(byte)0x78,(byte)0x99,
			(byte)0x52,(byte)0x3e,(byte)0xea,(byte)0xf2 
	};
	
	/** 
	 * The null initial vector.
	 **/
	protected final byte[] IV_NULL = { 
			(byte)0,(byte)0,(byte)0,(byte)0,
			(byte)0,(byte)0,(byte)0,(byte)0 
	};
	
	/** The dummy class. */
	protected DummyClass dummy; //overriddes the dummy field in superclass.
	
	/** The cryptographic objects. */
	private Object[] engines;
	
	/** The cipher. */
	protected Cipher cipher;
	
	/** The signature. */
	protected Signature signature;
	
	/** The digest. */
	protected MessageDigest digest;
	
	/** The random generator. */
	protected RandomData random;
	
	/** The information. */
	protected CryptoInfo cryptoInfo;
	
	//===========================================================================
	// Constructor
	//===========================================================================
	
	protected CryptoTemplateApplet() {		
	  cryptoInfo = new CryptoInfo();
	  dummy = new DummyClass();
	  engines = new Object[15]; // 6(algorithm) for DES ciphers
	                            // + 2(algorithm) for RSA ciphers
	                            // + 3(algorithm) for DES signatures
	                            // + 2(algorithm) for RSA signatures
	                            // + 1(algorithm) for digests
	                            // + 1(algorithm) for random data
	}
	
	//===========================================================================
	// Useful methods for initialization of cryptographic algorithms
	//===========================================================================
	
	/**
	 * Reads information among incoming data, builds a new 
	 * cryptographic object with the read parameters.
	 * 
	 * @param apduBuffer the APDU buffer where are read incoming data.
	 */
	protected void init(byte[] apduBuffer) {
	  super.init(apduBuffer);
	  cryptoInfo.setKeyInfo(keyInfo);
	  byte type = cryptoInfo.getType();
	  byte algorithm = cryptoInfo.getAlgorithm();
	  cryptoInfo.setCryptoInfo(reader);
	  if ((cryptoInfo.getType() != type) ||
		  (cryptoInfo.getAlgorithm() != algorithm))
	    buildEngine();
	}
	
    private void buildEngine() {
      for (short s = 0; s < (short)engines.length; s++) {
        if (engines[s] == null) {    
          try {
            switch (cryptoInfo.getType()) {
              case CryptoInfo.TYPE_SIGNATURE:
  	            signature = Signature.getInstance(
  		            cryptoInfo.getAlgorithm(),false);	
  	            engines[s] = signature;
  	            cipher = null; digest = null; random = null;
  	            break;
              case CryptoInfo.TYPE_CIPHER:
                cipher = Cipher.getInstance(
                    cryptoInfo.getAlgorithm(),false);
                engines[s] = cipher;
                signature = null; digest = null; random = null;
                break;
              case CryptoInfo.TYPE_DIGEST:
                digest = MessageDigest.getInstance(
                	MessageDigest.ALG_SHA,false);		
                engines[s] = digest;
                signature = null; cipher = null; random = null;
                break;
              case CryptoInfo.TYPE_RANDOM:
            	random = RandomData.getInstance(RandomData.ALG_SECURE_RANDOM);
            	engines[s] = random;
            	signature = null; cipher = null; digest = null;
            	break;
              default:
            	signature = null; cipher = null; digest = null; random = null;
            	break;
            }
            return;
  		  } catch (RuntimeException e){
  		    cryptoInfo.setType((byte)0);
  	        cryptoInfo.setAlgorithm((byte)0);
  		    throw e;
  	      }
        }
        else {
          switch (cryptoInfo.getType()) {
            case CryptoInfo.TYPE_SIGNATURE:
              if (engines[s] instanceof Signature) {
                Signature c = (Signature)engines[s];
                if (c.getAlgorithm() == cryptoInfo.getAlgorithm()) {
                  signature = c;
                  cipher = null; digest = null; random = null;
                  return;
                }
              }
              break;
            case CryptoInfo.TYPE_CIPHER:
              if (engines[s] instanceof Cipher) {
                Cipher c = (Cipher)engines[s]; 
                if (c.getAlgorithm() == cryptoInfo.getAlgorithm()) {
                  cipher = c;
                  signature = null; digest = null; random = null;
                  return;
                }
              }
              break;
            case CryptoInfo.TYPE_DIGEST:
              if (engines[s] instanceof MessageDigest) {
                digest = (MessageDigest)engines[s];  
                signature = null; cipher = null; random = null;
                return;
              }
              break;
            case CryptoInfo.TYPE_RANDOM:
              if (engines[s] instanceof RandomData) {
                random = (RandomData)engines[s];
                signature = null;cipher = null; digest = null;
                return;
              }
              break;
            default:
              break;
          }
        }
      }
    }
  }         