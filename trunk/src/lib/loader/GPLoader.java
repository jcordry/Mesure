package lib.loader;

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
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/lib/loader/GPLoader.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

import javax.crypto.SecretKey;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import lib.cad.CADException;
import lib.cad.DeleteAPDU;
import lib.cad.GPCAD;
import lib.cad.GetDataAPDU;
import lib.cad.InstallForInstallAPDU;
import lib.cad.InstallForLoadAPDU;
import lib.cad.LoadAPDU;
import lib.cad.ResponseAPDU;
import lib.cad.SelectAPDU;
import lib.capfile.CAPFile;
import lib.loader.config.CardConfig;
import lib.util.Util;

/**
 * This class implements a very simple loader for GlobalPlatform platforms.
 */
public class GPLoader {

  /** The CAD to be used for card content management. */
  private GPCAD cad;	
  
  /** The Card Manager AID. */
  private byte[] cardManagerAID;
  
  /** The diversification method. */
  private String div;
  
  /** To indicate that diversification is not necessary. */
  private final static String DIV_NONE = "none";
  
  /** 
   * To indicate that diversification is necessary and that the VISA 1 
   * derivation method should be applied.
   */
  private final static String DIV_VISA_1 = "visa1";
  
  /**
   * To indicate that diversification is necessary and that the VISA 2
   * derivation method should be applied.
   */
  private final static String DIV_VISA_2 = "visa2";
  
  /** The master key. */
  private byte[] kmc;
  
  /** The maximum Lc for LOAD commands. */
  private int lc;
  
  /** 
   * Determines if the Non Volatile Code Space Limit shall be set or not for
   * INSTALL[for load] commands.
   */
  private boolean installForLoadNonVolatileCodeSpaceLimit;

  /**
   * Constructs a <tt>GPLoader</tt> object.
   * 
   * @param configFileName the file containing the loader configuration.
   * @param cad the CAD to be used for card content management operations.
   */
  public GPLoader(String configFileName, GPCAD cad) throws CADException {
    
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(new File(configFileName));
    } catch (FileNotFoundException e) {
      throw new CADException (e);
    }
    InputStreamReader reader = new InputStreamReader(fis);
    CardConfig config = null;
    try {
      config = CardConfig.unmarshal(reader);
    } catch (MarshalException e) {
      throw new CADException (e);
    } catch (ValidationException e) {
      throw new CADException (e);
    }
    cardManagerAID = Util.hexStringToByteArray(config.getCardManagerAid());
    lc = config.getLoad().getLc();
    installForLoadNonVolatileCodeSpaceLimit = config.getLoad().getNonVolatileCode();
    div = config.getMutualAuthenticate().getDiv();
    String scp = config.getMutualAuthenticate().getScp();
    int secLevel = config.getMutualAuthenticate().getSecLevel();
    int keyVersion = config.getMutualAuthenticate().getKeyVersion();
    int keyIndex = config.getMutualAuthenticate().getKeyIndex();
    byte[] kenc = Util.hexStringToByteArray(config.getMutualAuthenticate().getKenc());
    byte[] kmac = Util.hexStringToByteArray(config.getMutualAuthenticate().getKmac());
    kmc = Util.hexStringToByteArray(config.getMutualAuthenticate().getKmc());
    byte[] hostChallenge = Util.hexStringToByteArray(
        config.getMutualAuthenticate().getHostChallenge());
    
    this.cad = cad;
    this.cad.init(scp,secLevel,kenc,kmac,keyVersion,keyIndex,hostChallenge);
  }
  
  /**
   * Performs mutual authentication.
   * 
   * @throw <tt>CADException</tt> if one of the sent APDU commands fails.
   */
  public void selectAndAuthenticate() 
      throws CADException {
    cad.resetSecurityLevel();
	SelectAPDU select = new SelectAPDU(cardManagerAID);
    cad.sendAPDUAndVerify(select);
    
    if (!div.equalsIgnoreCase(DIV_NONE)) {
      ResponseAPDU rapdu = cad.sendAPDUAndVerify(new GetDataAPDU(
          GetDataAPDU.TAG_CARD_PRODUCTION_LIFE_CYCLE));
      if (div.equalsIgnoreCase(DIV_VISA_1))
        divVisa1(rapdu.getData());
      else if (div.equalsIgnoreCase(DIV_VISA_2))
    	divVisa2(rapdu.getData());
    }
    
    cad.mutualAuthenticate();
  }
  
  /**
   * Performs diversification from master key using VISA 1 method.
   * 
   * @param data the Card Production Life Cycle data.
   */
  private void divVisa1(byte[] data) throws CADException {
	try {
      byte[] derivationData = new byte[16];
      // Copies the Card Serial Number
      System.arraycopy(data,13,derivationData,2,8);
      // Builds the master key
      SecretKey key = cad.buildTripleDESKey(kmc);
    
      // Computes the encryption key
      derivationData[0] = (byte)0xFF;
      derivationData[1] = (byte)0xFF;
      derivationData[10] = 0x01;
      cad.setEncKey(cad.derivateKeyECB(derivationData,key));
    
      // Computes the MAC key
      derivationData[0] = 0;
      derivationData[1] = 0;
      derivationData[10] = 0x02;
      cad.setMacKey(cad.derivateKeyECB(derivationData,key));
	} catch (GeneralSecurityException e) {
	  throw new CADException(e);
	}
  }
  
  /**
   * Performs diversification from master key using VISA 2 method.
   * 
   * @param data the Card Production Life Cycle data.
   */
  private void divVisa2(byte[] data) throws CADException {
    try {
      byte[] derivationData = new byte[16]; 
      
      // Copies the 2 last bytes of the Card Manager AID
      System.arraycopy(cardManagerAID,cardManagerAID.length-2,derivationData,0,2);
      System.arraycopy(cardManagerAID,cardManagerAID.length-2,derivationData,8,2);
      // Copies the Card Serial Number
      System.arraycopy(data,15,derivationData,2,4);
      System.arraycopy(data,15,derivationData,10,4);
      // Builds the master key
      SecretKey key = cad.buildTripleDESKey(kmc);
    
      // Computes the encryption key
      derivationData[6] = (byte)0xF0;
      derivationData[7] = (byte)0x01;
      derivationData[14] = (byte)0x0F;
      derivationData[15] = (byte)0x01;
      cad.setEncKey(cad.derivateKeyECB(derivationData,key));
      
      // Computes the MAC key
      derivationData[6] = (byte)0xF0;
      derivationData[7] = (byte)0x02;
      derivationData[14] = (byte)0x0F;
      derivationData[15] = (byte)0x02;
      cad.setMacKey(cad.derivateKeyECB(derivationData,key));
    } catch (GeneralSecurityException e) {
	  throw new CADException(e);
	}
  }
  
  /**
   * Loads the CAP file.
   * 
   * @param capfile the CAP file to load.
   * @throw <tt>CADException</tt> if one of the sent APDU commands fails.
   */
  public CAPFile load(CAPFile capfile) throws CADException {
	// Builds INSTALL[for load] command
	byte[] params = null;
    if (installForLoadNonVolatileCodeSpaceLimit) {
	  params = new byte[6];
	  params[0] = (byte)0xEF;
	  params[1] = (byte)0x04;
	  params[2] = (byte)0xC6;
	  params[3] = (byte)0x02;
	  int size = capfile.getSize() + capfile.getStaticImageSize();
	  params[4] = (byte)((size >> 8) & 0xFF);
	  params[5] = (byte)(size & 0xFF);
    }
	InstallForLoadAPDU installForLoad = new InstallForLoadAPDU(
        capfile.getPackageAID(),cardManagerAID,params);

	// Builds LOAD commands
	LoadAPDU[] load = LoadAPDU.getLoadCommandAPDU(capfile,lc);
    
	// Loads CAP file
	cad.sendAPDUAndVerify(installForLoad);
	for (int i = 0; i < load.length; i++)
	  cad.sendAPDUAndVerify(load[i]);
	
	return capfile;
  }

  /**
   * Installs the applet instances contained in the CAP file.
   * 
   * @throw <tt>CADException</tt> if one of the sent APDU commands fails.
   */
  public void install(CAPFile capfile) throws CADException {
	byte[] pkgAID = capfile.getPackageAID();
    byte[][] AIDs = capfile.getAppletAIDs();
	InstallForInstallAPDU[] commands = new InstallForInstallAPDU[AIDs.length];
    for (int i = 0; i < AIDs.length; i++)                         
	  commands[i] = new InstallForInstallAPDU(pkgAID,AIDs[i],AIDs[i],0,null);    
    for (int i = 0; i < commands.length; i++)
      cad.sendAPDUAndVerify(commands[i]);
  }
  
  /**
   * Installs the specified applet with the specified AID, privileges and 
   * parameters.
   * 
   * @param packageAID the byte array containing the package AID identifying
   *                  the package whose the applet to be instanciated belongs.
   * @param appletAID the byte array containing the applet AID identifying 
   *                  applet to be instantiated.
   * @param instanceAID the byte array containing the instance AID identifying 
   *                    the instance to be installed.
   * @param privileges the privileges granted to this instance .
   * @param params the byte array containing the installation 
   *               parameters to be passed to this instance.
   * @throw <tt>CADException</tt> if one of the sent APDU commands fails.
   */
  public void install(byte[] packageAID, 
      byte[] appletAID, byte[] instanceAID, int privileges, byte[] params)
      throws CADException {
	if (instanceAID == null)
	  instanceAID = appletAID;
    cad.sendAPDUAndVerify(new InstallForInstallAPDU(
        packageAID,appletAID,instanceAID,privileges,params));
  }

  /**
   * Deletes the package and contained instances.
   * 
   * @param capfile the capfile to delete.
   * @throw <tt>CADException</tt> if one of the sent APDU commands fails.
   */
  public void delete(CAPFile capfile) throws CADException{
	byte[][] AIDs = capfile.getAppletAIDs();
	DeleteAPDU[] commands = new DeleteAPDU[AIDs.length];
	for (int i = 0; i < AIDs.length; i++)                         
      commands[i] = new DeleteAPDU(AIDs[i]);    
	for (int i = 0; i < commands.length; i++)
	  cad.sendAPDUAndVerify(commands[i]);
	cad.sendAPDUAndVerify(new DeleteAPDU(capfile.getPackageAID())); 
  }
  
  /**
   * Delete the package or instance with the specified AID.
   * 
   * @param AID the AID of the package or instance to delete.
   * @throw <tt>CADException</tt> if one of the sent APDU commands fails.
   */
  public void delete(byte[] AID) throws CADException {
    cad.sendAPDUAndVerify(new DeleteAPDU(AID));
  }
}
