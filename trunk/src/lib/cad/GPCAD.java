package lib.cad;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.smartcardio.ATR;

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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/GPCAD.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 264 $
 * $LastChangedDate: 2007-05-11 11:34:39 +0200 (ven., 11 mai 2007) $
 * $LastChangedBy: cpascal $
 */

/**
 * The CAD to be used when sending GlobalPlatform APDU commands.
 */
public class GPCAD extends CAD
{
  //---------------------------------------------------------------------------
  // Data
  //---------------------------------------------------------------------------
	
  /** The underlying CAD. */
  private CAD cad;
	
  /** The Secure Channel Protocol. */
  private int scp;
  
  /** Derivation using CBC mode. */
  private boolean scp1cbc;
  
  /** SCP 1. */
  private final static int SCP_1 = 0x01;
	
  /** SCP 2. */
  private final static int SCP_2 = 0x02;
  
  /** The security level. */
  private int secLevel;
  
  /** The default key. */
  private final static byte[] KEY = 
	  new byte[] {(byte)0x40,(byte)0x41,(byte)0x42,(byte)0x43,(byte)0x44,
                  (byte)0x45,(byte)0x46,(byte)0x47,(byte)0x48,(byte)0x49,
	              (byte)0x4A,(byte)0x4B,(byte)0x4C,(byte)0x4D,(byte)0x4E,
	              (byte)0x4F};
  
  /** The encryption key. */
  private byte[] kenc;
  
  /** The mac key. */
  private byte[] kmac;
  
  /** The key version. */
  private int keyVersion;
  
  /** The key index. */
  private int keyIndex;
  
  /** The host challenge. */
  private byte[] hostChallenge = null;
	
  /** The session encryption key (triple). */
  private SecretKey sessionEncKey;
	  
  /** The session C-MAC key (triple). */
  private SecretKey sessionCMacKey;
	  
  /** The session C-MAC key (simple). */
  private SecretKey sessionCMacSimpleKey;
	  
  /** The session R-MAC key (triple). */
  private SecretKey sessionRMacKey;
  
  /** The session R-MAC key (simple). */
  private SecretKey sessionRMacSimpleKey;
	  
  /** The current security level. */
  private int currentSecLevel;
	  
  /** The current C-MAC. */
  private byte[] cmac;
	  
  /** The current R-MAC. */
  private byte[] rmac;
	  
  /** The sequence counter. */
  private int sequenceCounter;
	  
  /** Mutual authentication has occured (<tt>0x80</tt>). */
  public static final int AUTHENTICATED = 0x80;
  
  /** The MAC security level for response data (<tt>0x10</tt>). */
  public static final int R_MAC = 0x10;
	  
  /** A triple DES-based cipher in ECB mode without padding. */
  private Cipher cipher3ECB;
	  
  /** A triple DES-based cipher in CBC mode without padding. */
  private Cipher cipher3CBC;
	  
  /** A simple DES-based cipher in ECB mode without padding. */
  private Cipher cipher1ECB;
	  
  /** A simple DES-based cipher in CBC mode without padding. */
  private Cipher cipher1CBC;
	  
  /** Default Initial Vector. */
  private static IvParameterSpec IV = new IvParameterSpec(new byte[8]);
  
  // --------------------------------------------------------------------------
  // Constructor
  // --------------------------------------------------------------------------

  /**
   * Constructs CAD able to send Global Platform APDU commands.
   * 
   * @param cad a standard CAD.
   * @param scp the Secure Channel Protocol
   */
  public GPCAD(CAD cad) {
	super(cad.name);
	this.cad = cad;
	scp = SCP_1;
	secLevel = ExternalAuthenticateAPDU.P1;
	kenc = KEY;
	kmac = KEY;
	keyVersion = 0;
	keyIndex = 0;
	hostChallenge = null;
	try {
	  cipher3ECB = Cipher.getInstance("DESede/ECB/NoPadding");
	  cipher3CBC = Cipher.getInstance("DESede/CBC/NoPadding");
	  cipher1ECB = Cipher.getInstance("DES/ECB/NoPadding");
	  cipher1CBC = Cipher.getInstance("DES/CBC/NoPadding");
	} catch (NoSuchAlgorithmException e) {
	  // should not occur
	  throw new RuntimeException(e.getMessage());
	} catch (NoSuchPaddingException e) {
	  // should not occur
	  throw new RuntimeException(e.getMessage());
	}
  }
  
  // --------------------------------------------------------------------------
  // SetX methods
  // --------------------------------------------------------------------------
  
  /**
   * Sets the Secure Channel Protocol.
   * 
   * @param scp the Secure Channel Protocol to use.
   * @see GPCAD#SCP_1
   * @see GPCAD#SCP_2
   */
  private void setSCP(String scp) {
	if (scp.equals(""+SCP_1)) 
      this.scp = SCP_1;
	else if (scp.equals(""+SCP_2))
	  this.scp = SCP_2;
	else if (scp.equalsIgnoreCase(""+SCP_1+"CBC")) {
	  this.scp = SCP_1;
	  scp1cbc = true;
	} 
  }
  
  /**
   * Sets the security level.
   * 
   * @param secLevel the security level to use.
   * @see ExternalAuthenticateAPDU#P1
   * @see ExternalAuthenticateAPDU#P1_MAC
   * @see ExternalAuthenticateAPDU#P1_ENC
   */
  private void setSecurityLevel(int secLevel) {
    switch (secLevel) {
      case ExternalAuthenticateAPDU.P1:
      case ExternalAuthenticateAPDU.P1_MAC:
      case ExternalAuthenticateAPDU.P1_ENC:
	    this.secLevel = secLevel;
	    break;
	  default:
		break;
    }
  }
 
  /**
   * Sets the encryption key.
   * 
   * @param kenc the encryption key to use.
   */
  public void setEncKey(byte[] kenc) {
    if (kenc != null)
      this.kenc = kenc;
  }
  
  /**
   * Sets the MAC key.
   * 
   * @param kmac the MAC key to use.
   */
  public void setMacKey(byte[] kmac) {
	if (kmac != null)
      this.kmac = kmac;
  }
  
  /**
   * Sets the key version.
   * 
   * @param keyVersion the key version to use.
   */
  private void setKeyVersion(int keyVersion) {
    this.keyVersion = keyVersion;
  }
  
  /**
   * Sets the key index.
   * 
   * @param keyIndex the key index to use.
   */ 
  private void setKeyIndex(int keyIndex) {
    this.keyIndex = keyIndex;
  }
  
  /**
   * Sets the host challenge.
   * 
   * @param hostChallenge the host challenge to use.
   */
  private void setHostChallenge(byte[] hostChallenge) {
	if (hostChallenge != null)
      this.hostChallenge = hostChallenge;
  }
  
  /**
   * Initializes the CAD with all parameters required for mutual authentication.
   * If this method is not called, default parameters are used:
   * <ul>
   *   <li>The Secure Channel Protocol is set to {@link GPCAD#SCP_1}.
   *   <li>The security level is set to {@link ExternalAuthenticateAPDU#P1}.
   *   <li>The encryption key is set to {@link GPCAD#KEY}.
   *   <li>The MAC key is set to {@link GPCAD#KEY}.
   *   <li>The key version is set to 0.
   *   <li>The key index is set to 0.
   *   <li>The host challenge is randomly generated.
   * </ul>
   *
   * @param scp the Secure Channel Protocol.
   * @param secLevel the security level.
   * @param kenc the encryption key.
   * @param kmac the MAC key.
   * @param keyVersion the key version.
   * @param keyIndex the key index.
   * @param hostChallenge the host challenge.
   * @see GPCAD#SCP_1
   * @see GPCAD#SCP_2 
   * @see GPCAD#SCP_1_CBC
   * @see ExternalAuthenticateAPDU#P1
   * @see ExternalAuthenticateAPDU#P1_MAC
   * @see ExternalAuthenticateAPDU#P1_ENC
   */
  public void init(String scp, int secLevel, 
		           byte[] kenc, byte[] kmac, int keyVersion, int keyIndex, 
		           byte[] hostChallenge) {
    setSCP(scp);
    setSecurityLevel(secLevel);
    setEncKey(kenc);
    setMacKey(kmac);
    setKeyVersion(keyVersion);
    setKeyIndex(keyIndex);
    setHostChallenge(hostChallenge);
  }

  // --------------------------------------------------------------------------
  // Implementation of superclass abstract methods
  // --------------------------------------------------------------------------

  /**
   * @see CAD#connect()
   */
  public ATR connect() throws CADException {
    return cad.connect();
  }

  /**
   * @see CAD#disconnect()
   */
  public void disconnect() throws CADException {
	cad.disconnect();
  }

  /**
   * @see CAD#send(CommandAPDU)
   */
  protected ResponseAPDU send(CommandAPDU capdu) throws CADException {
	CommandAPDU scapdu = wrap(capdu);
	scapdu.setName(capdu.getName());
	ResponseAPDU rapdu = cad.send(scapdu);
	return unwrap(scapdu,rapdu);
  }

  /**
   * Wraps the passed APDU command, as explained in GlobalPlatform
   * specification.
   * 
   * @param capdu the APDU command to wrap.
   * @return the wrapped APDU command.
   * @throws GeneralSecurityException
   * @throws CADException
   */
  private CommandAPDU wrap(CommandAPDU capdu)
      throws CADException {
    if ((currentSecLevel & ExternalAuthenticateAPDU.P1_MAC) != 
    	ExternalAuthenticateAPDU.P1_MAC)
	  return capdu;

	// Copies Header + Lc + Data
	byte[] data = capdu.getData();
	byte[] tmp = new byte[5 + data.length];
	System.arraycopy(capdu.getHeader(), 0, tmp, 0, 4);
	tmp[4] = (byte) data.length;
	System.arraycopy(data, 0, tmp, 5, data.length);
	// Adds 8 to Lc
	tmp[4] = (byte) ((tmp[4] & 0xFF) + 8);
	// Masks CLA byte with secure messaging byte
	tmp[0] |= 0x04;
	// Pads Header + Lc + Data
	tmp = padding(tmp);

	// Computes the MAC
	byte[] enciphered;
	switch (scp) {
	  case 1:
		try {
		  cipher3CBC.init(Cipher.ENCRYPT_MODE, sessionCMacKey,
			  new IvParameterSpec(cmac));
		  enciphered = cipher3CBC.doFinal(tmp);
		  System.arraycopy(enciphered, enciphered.length - 8, cmac, 0, 8);
		} catch (GeneralSecurityException e) {
		  throw new CADException(e);
		}
		break;
	  case 2:
		try {
		  // Option 15
		  // Encryption of C-MAC, except for EXTERNAL AUTHENTICATE commands
		  if ((capdu.getCLA() != ExternalAuthenticateAPDU.CLA)
		   || (capdu.getINS() != ExternalAuthenticateAPDU.INS)) {
		    cipher1ECB.init(Cipher.ENCRYPT_MODE, sessionCMacSimpleKey);
		    cmac = cipher1ECB.doFinal(cmac, 0, cmac.length);
		  }
		  // Simple DES on first blocks
		  if (tmp.length > 8) {
		    cipher1CBC.init(Cipher.ENCRYPT_MODE, sessionCMacSimpleKey,
		        new IvParameterSpec(cmac));
		    enciphered = cipher1CBC.doFinal(tmp, 0, tmp.length - 8);
		    System.arraycopy(enciphered, enciphered.length - 8, cmac, 0, 8);
		  }
		  // Triple DES on last block
		  cipher3CBC.init(Cipher.ENCRYPT_MODE, sessionCMacKey,
		      new IvParameterSpec(cmac));
		  cmac = cipher3CBC.doFinal(tmp, tmp.length - 8, 8);
		} catch (GeneralSecurityException e) {
		  throw new CADException(e);
		}
	    break;
	  default:
	    throw new CADException("Invalid Secure Channel Protocol");
	}

	// Encrypts data
	if ((currentSecLevel & ExternalAuthenticateAPDU.P1_ENC) == 
		ExternalAuthenticateAPDU.P1_ENC) {
	  switch (scp) {
	    case 1:
		  tmp = new byte[data.length + 1];
		  tmp[0] = (byte) capdu.getLc();
		  System.arraycopy(data, 0, tmp, 1, data.length);
		  if ((tmp.length % 8) != 0)
		    tmp = padding(tmp);
		  break;
		case 2:
		  tmp = padding(data);
		  break;
		default:
		  throw new CADException("Invalid Secure Channel Protocol");
	  }
	  try {
	    cipher3CBC.init(Cipher.ENCRYPT_MODE, sessionEncKey, IV);
	    data = cipher3CBC.doFinal(tmp);
	  } catch (GeneralSecurityException e) {
	    throw new CADException(e);
	  }
	}

	// Builds the wrapped command
	tmp = new byte[data.length + 8];
	System.arraycopy(data, 0, tmp, 0, data.length);
	System.arraycopy(cmac, 0, tmp, data.length, 8);
	CommandAPDU scapdu = new CommandAPDU(capdu.getCLA() | 0x04, capdu.getINS(),
	  capdu.getP1(), capdu.getP2(), tmp);
	if (capdu.getLe() >= 0)
	  scapdu.setLe(capdu.getLe());
	scapdu.setName(capdu.getName());
	return scapdu;
  }
    
  /**
   * Unwraps the passed APDU command, as explained in GlobalPlatform
   * specification.
   * 
   * @param capdu the sent APDU command.
   * @param rapdu the returned wrapped APDU response.
   * @return the unwrapped APDU response.
   * @throws GeneralSecurityException
   * @throws CADException
   */
  private ResponseAPDU unwrap(CommandAPDU capdu, ResponseAPDU rapdu) 
	throws CADException {
    switch (scp) {
      case 1:
        return rapdu;
      case 2:
    	if ((currentSecLevel & R_MAC) != R_MAC) 
    	  return rapdu;
    	// Computes Lc without MAC
    	int lc = capdu.getLc();
    	if ((currentSecLevel & ExternalAuthenticateAPDU.P1_MAC) == ExternalAuthenticateAPDU.P1_MAC)
    	  lc -= 8;
    	// Computes Lr without MAC
    	int lr = rapdu.getLr() - 8;
    	// Prepares data
    	byte[] tmp = new byte[lc + lr + 8];
    	// Copies command header
    	System.arraycopy(capdu.getHeader(),0,tmp,0,4);
    	// Masks CLA byte with secure messaging byte
    	tmp[0] = (byte)(tmp[0] & 0xFB);
    	// Copies Lc without MAC
    	tmp[4] = (byte)lc;
    	// Copies command data without MAC
    	System.arraycopy(capdu.getData(),0,tmp,5,lc);
    	// Copies Lr without MAC
    	tmp[5 + lc] = (byte)lr;
    	// Copies response data without MAC
    	System.arraycopy(rapdu.getData(),0,tmp,6 + lc,lr);
    	// Copies response status
    	tmp[6 + lc + lr] = (byte)rapdu.getSW1();
    	tmp[7 + lc + lr] = (byte)rapdu.getSW2();
    	
    	// Builds the unwrapped response
    	ResponseAPDU unwrapped = new ResponseAPDU(tmp,6 + lc,lr+2);
    	
    	// Pads prepared data
    	tmp = padding(tmp);
    	
    	// Computes the R-MAC
    	try {
    	  // Simple DES cipher on first blocks
    	  if (tmp.length > 8) {
		    cipher1CBC.init(Cipher.ENCRYPT_MODE, sessionRMacSimpleKey,
		        new IvParameterSpec(rmac));
		    byte[] enciphered = cipher1CBC.doFinal(tmp,0,tmp.length - 8);
		    System.arraycopy(enciphered,enciphered.length - 8,rmac,0,8);
		  }
		  // Triple DES on last block
		  cipher3CBC.init(Cipher.ENCRYPT_MODE, sessionRMacKey,
		      new IvParameterSpec(rmac));
		  rmac = cipher3CBC.doFinal(tmp,tmp.length - 8,8);
    	} catch (GeneralSecurityException e) {
    	  throw new CADException(e);
    	}
		
		// Checks the R-MAC
		tmp = new byte[8];
		System.arraycopy(rapdu.getData(),lr,tmp,0,8);
		if (!Arrays.equals(rmac,tmp))
		  throw new CADException("The R-MAC verification failed.");
		return unwrapped;
      default:
	    throw new CADException("Invalid Secure Channel Protocol");  
    }
  }
    
  /**
   * Pads data, as explained in GlobalPlatform specification.
   * 
   * @param data the data to pad.
   * @return the padded data.
   */
  private static byte[] padding(byte[] data) {
    int length = data.length;
	if (length % 8 == 0)
	  length += 8;
	else
	  length += (8 - (length % 8));
	byte[] padded = new byte[length];
	System.arraycopy(data,0,padded,0,data.length);
	padded[data.length] = (byte)0x80;
	return padded;
  }
  
  //---------------------------------------------------------------------------
  // Mutual Authentication
  //---------------------------------------------------------------------------
  
  /**
   * Performs mutual authentication.
   * 
   * @throw <tt>CADException</tt> if one of the sent APDU commands fails.
   */
  public void mutualAuthenticate() 
      throws CADException {
    resetSecurityLevel();
    try {
      // Sends INITIALIZE UPDATE command
      InitializeUpdateAPDU initUpdate;
      if (hostChallenge == null) {
        initUpdate = new InitializeUpdateAPDU(keyVersion,keyIndex);
        hostChallenge = initUpdate.getData();
      }
      else
        initUpdate = new InitializeUpdateAPDU(keyVersion,keyIndex,hostChallenge);
      byte[] rapdu = cad.sendAPDUAndVerify(initUpdate).getData();
    
      // Reads the sequence counter
      // Useful for SCP 2 only
      sequenceCounter = (((rapdu[12] & 0xFF) << 8) | (rapdu[13] & 0xFF));
    
      // Reads the card challenge
      byte[] cardChallenge = new byte[8];
      System.arraycopy(rapdu,12,cardChallenge,0,8);
    
      // Reads the card cryptogram
      byte[] cardCryptogram = new byte[8];
      System.arraycopy(rapdu,20,cardCryptogram,0,8);
    
      // Derivates the session key
      switch (scp) {
        case 1:
  	      derivateSessionKey(cardChallenge,hostChallenge);
          break;
        case 2:
    	  derivateSessionKey(sequenceCounter);
  	      break;
        default:
          throw new CADException("Invalid Secure Channel Protocol");
      }

      // Computes and checks the card cryptogram
      if (!Arrays.equals(cardCryptogram,computeCryptogram(hostChallenge,cardChallenge)))
        throw new CADException("The card cryptogram verification failed.");
    
      // Computes the host cryptogram
      byte[] hostCryptogram = computeCryptogram(cardChallenge,hostChallenge);
    
      // Sends EXTERNAL AUTHENTICATE command
      currentSecLevel = ExternalAuthenticateAPDU.P1_MAC;
      cmac = new byte[8];  
      ExternalAuthenticateAPDU extAuth = new ExternalAuthenticateAPDU(
          secLevel,hostCryptogram);
      cad.sendAPDUAndVerify(wrap(extAuth));
      rmac = new byte[8];
      System.arraycopy(cmac,0,rmac,0,8);
    
      // Sets the security level
      currentSecLevel = secLevel | AUTHENTICATED;
    
      // Increments the sequence counter
      sequenceCounter++;
      
    } catch (GeneralSecurityException e) {
      resetSecurityLevel();
      throw new CADException(e);
    } 
  }  
  
  /**
   * Resets the security level.
   */
  public void resetSecurityLevel() {
    currentSecLevel = ExternalAuthenticateAPDU.P1;
    cmac = null;
    rmac = null;
    sessionEncKey = null;
    sessionCMacKey = null;
    sessionRMacKey = null;
    sessionCMacSimpleKey = null;
  }

  /**
	 * Computes a cryptogram, according to GlobalPlatform specification.
	 * 
	 * @param challenge1
	 *            a first challenge.
	 * @param challenge2
	 *            a second challenge.
	 * @return the comuted cryptogram.
	 */
  private byte[] computeCryptogram(byte[] challenge1, byte[] challenge2)
      throws GeneralSecurityException {
    byte[] data = new byte[24];
    System.arraycopy(challenge1, 0, data, 0, 8);
    System.arraycopy(challenge2, 0, data, 8, 8);
    data[16] = (byte) 0x80;
    
    cipher3CBC.init(Cipher.ENCRYPT_MODE,sessionEncKey,IV);
    byte[] encipheredData = cipher3CBC.doFinal(data);
    
    byte[] cryptogram = new byte[8];
    System.arraycopy(encipheredData,16,cryptogram,0,8);
    return cryptogram;
  }
    
  /**
   * Derivates the session keys, as specified in GlobalPlatform specification
   * for SCP 1 protocol.
   *   
   * @param cardChallenge the card challenge.
   * @param hostChallenge the host challenge.
   */
  private void derivateSessionKey(byte[] cardChallenge, byte[] hostChallenge)
      throws GeneralSecurityException, CADException {
    byte[] derivationData = new byte[16];
	System.arraycopy(cardChallenge,4,derivationData,0,4);
	System.arraycopy(hostChallenge,0,derivationData,4,4);
	System.arraycopy(cardChallenge,0,derivationData,8,4);
	System.arraycopy(hostChallenge,4,derivationData,12,4);
	sessionEncKey = buildTripleDESKey(
	    derivateKey(derivationData,buildTripleDESKey(kenc)));
	sessionCMacKey = buildTripleDESKey(
		derivateKey(derivationData,buildTripleDESKey(kmac)));
	sessionRMacKey = sessionCMacKey;
  }
  
  /**
   * Derivates the session keys, as specified in GlobalPlatform specification
   * for SCP 2 protocol.
   * 
   * @param sequenceCounter the sequence counter.
   */
  private void derivateSessionKey(int sequenceCounter) throws GeneralSecurityException, CADException {
    byte[] derivationData = new byte[16];
	derivationData[0] = (byte)1;
	derivationData[2] = (byte)((sequenceCounter >> 8) & 0xFF);
	derivationData[3] = (byte)(sequenceCounter & 0xFF);
	
	derivationData[1] = (byte)1;
	SecretKey key = buildTripleDESKey(kmac);
	byte[] keyData = derivateKey(derivationData,key);
	sessionCMacKey = buildTripleDESKey(keyData);
	sessionCMacSimpleKey = buildSimpleDESKey(keyData);

	derivationData[1] = (byte)0x82;
	sessionEncKey = buildTripleDESKey(
	    derivateKey(derivationData,buildTripleDESKey(kenc)));

	derivationData[1] = (byte)2;
    keyData = derivateKey(derivationData,key);
	sessionRMacKey = buildTripleDESKey(keyData);
	sessionRMacSimpleKey = buildSimpleDESKey(keyData);
  }
    
  /**
   * Derivates a key from the specified key and derivation data.
   * 
   * @param derivationData the derivation data.
   * @param key the key.
   * @return the array containing the session key data.
   * @throws <tt>GeneralSecurityException</tt> if a problem occured during
   *         cryptographic computations.
   */
  private byte[] derivateKey(byte[] derivationData, SecretKey key) 
      throws GeneralSecurityException, CADException {
	switch (scp) {
	  case 1: {
		if (scp1cbc)
		  return derivateKeyCBC(derivationData,key);
	    return derivateKeyECB(derivationData,key);
	  }
	  case 2:
		return derivateKeyCBC(derivationData,key);
      default:
    	throw new CADException("Invalid Secure Channel Protocol");
	}
  }
  
  /**
   * Derivates a key using a DES-based cipher in ECB mode from the specified
   * key and derivation data.
   * 
   * @param derivationData the derivation data.
   * @param key the key.
   * @return the array containing the session key data.
   * @throws <tt>GeneralSecurityException</tt> if a problem occured during
   *         cryptographic computations.
   */
  public byte[] derivateKeyECB(byte[] derivationData, SecretKey key) 
      throws GeneralSecurityException {
    cipher3ECB.init(Cipher.ENCRYPT_MODE,key);
    return cipher3ECB.doFinal(derivationData);
  }
  
  /**
   * Derivates a key using a DES-based cipher in CBC mode from the specified
   * key and derivation data.
   * 
   * @param derivationData the derivation data.
   * @param key the key.
   * @return the array containing the session key data.
   * @throws <tt>GeneralSecurityException</tt> if a problem occured during
   *         cryptographic computations.
   */
  private byte[] derivateKeyCBC(byte[] derivationData, SecretKey key) 
      throws GeneralSecurityException {
    cipher3CBC.init(Cipher.ENCRYPT_MODE,key,IV);
    return cipher3CBC.doFinal(derivationData);
  }
    
  /** 
   * Builds a triple DES key from a byte array containing the key data.
   * 
   * @param key the byte array containing the bytes of the key to build.
   * @return a <tt>SecretKey<tt> object encapsulating <tt>key</tt>.
   * @throws <tt>GeneralSecurityException</tt> if a problem occured during
   *         cryptographic computations.
   */
  public SecretKey buildTripleDESKey(byte[] key) throws GeneralSecurityException {
    if (key.length != 16)
      throw new InvalidKeyException("The key length is invalid.");
    SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
    byte[] key24 = new byte[24];
    System.arraycopy(key,0,key24,0,16);
    System.arraycopy(key,0,key24,16,8);
    KeySpec keySpec = new DESedeKeySpec(key24);
    return skf.generateSecret(keySpec);
  }
    
  /** 
   * Builds a simple DES key from a byte array containing the key data.
   * 
   * @param key the byte array containing the bytes of the key to build.
   * @return a <tt>SecretKey<tt> object encapsulating <tt>key</tt>.
   * @throws <tt>GeneralSecurityException</tt> if a problem occured during
   *         cryptographic computations.
   */
  public SecretKey buildSimpleDESKey(byte[] key) throws GeneralSecurityException {
    if (key.length != 16)
      throw new InvalidKeyException("The key length is invalid.");
    SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
    byte[] key24 = new byte[24];
    System.arraycopy(key,0,key24,0,16);
    System.arraycopy(key,0,key24,16,8);
    KeySpec keySpec = new DESKeySpec(key24);
    return skf.generateSecret(keySpec);
  }
  
  /**
   * Returns the underlying CAD.
   * 
   * @return the underlying <tt>CAD</tt> object.     
   */
  public CAD getCAD() {
    return cad;
  }
}