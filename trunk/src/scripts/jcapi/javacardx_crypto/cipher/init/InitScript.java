package scripts.jcapi.javacardx_crypto.cipher.init;

import javacard.security.KeyBuilder;
import javacardx.crypto.Cipher;
import lib.cad.CADException;
import scripts.jcapi.javacardx_crypto.cipher.CipherTestCase;
import scripts.templates.TemplateScript;
import scripts.templates.TestCase;
import benchs.lib.templates.KeyInfo;

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
 * <tt>Cipher.init</tt> method.
 */
public class InitScript extends TemplateScript{	
	private final static byte[] DES_ALGOS = {
        Cipher.ALG_DES_CBC_ISO9797_M1, Cipher.ALG_DES_CBC_ISO9797_M2, Cipher.ALG_DES_CBC_NOPAD,
        Cipher.ALG_DES_ECB_ISO9797_M1, Cipher.ALG_DES_ECB_ISO9797_M2, Cipher.ALG_DES_ECB_NOPAD};	
		  
	private final static byte[] RSA_ALGOS = {
		Cipher.ALG_RSA_NOPAD, Cipher.ALG_RSA_PKCS1};	
			
	private final static byte[] DES_KEY_TYPES = {
		KeyBuilder.TYPE_DES, KeyBuilder.TYPE_DES_TRANSIENT_DESELECT};	
				
	private final static short[] DES_KEY_LENGTHS = {
		KeyBuilder.LENGTH_DES, KeyBuilder.LENGTH_DES3_2KEY, KeyBuilder.LENGTH_DES3_3KEY};
			   
	private final static byte[] RSA_KEY_TYPES = {
	    KeyBuilder.TYPE_RSA_CRT_PRIVATE, KeyBuilder.TYPE_RSA_PRIVATE, KeyBuilder.TYPE_RSA_PUBLIC};
			   
	private final static short[] RSA_KEY_LENGTHS = {
		KeyBuilder.LENGTH_RSA_512, KeyBuilder.LENGTH_RSA_768, KeyBuilder.LENGTH_RSA_1024, KeyBuilder.LENGTH_RSA_2048};
	
	private final static int X = 10;
	
	private boolean isCalibrate = false;
	private String calibration = "";
	private boolean RSAPublicAdded = false;
	
	/**
	 * Builds the test script used to measure performances of the
	 * <tt>Cipher.init</tt> method.
	 * @param iv 
	 * @param desAlgos the list DES algorithms
	 * @param rsaAlgos the list of RSA algorithms
	 * @throws CADException 
	 */
	public InitScript(String iv, byte[] desAlgos, byte[] rsaAlgos) throws CADException {	  
	  build(desAlgos,DES_KEY_TYPES,DES_KEY_LENGTHS,"des",iv);
	  build(rsaAlgos,RSA_KEY_TYPES,RSA_KEY_LENGTHS,"rsa",iv);
	}
	
	/**
	 * Builds the test script used to measure performances of the
	 * <tt>Cipher.init</tt> method.
	 * 
	 * @throws CADException
	 */
	public InitScript() throws CADException {	
		this("", DES_ALGOS, RSA_ALGOS);
		}
	
	protected void build(byte[] algorithms, byte[] keyTypes, short[] keyLengths, String type, String iv) throws CADException {
	  for (int k = 0; k < keyLengths.length; k++) {
		isCalibrate = false;
		RSAPublicAdded = false;
        // The empty test cases used to compute overhead
		String ref = "initcipher" + iv + "_" + type + "_" + keyLengths[k] + "_ref";
		addTestCase(new CipherTestCase(ref,1,X,TestCase.Y,
		  (byte)0,Cipher.MODE_ENCRYPT,(byte)0,(short)0,false,
		  (short)0,(short)0,(short)0));
	    for (int i = 0; i < algorithms.length; i++)
	      for (int j = 0; j < keyTypes.length; j++)
	        build(algorithms[i],keyTypes[j],keyLengths[k],ref,iv);
	    getTestCase(ref).setCalibration(calibration);
	  }
	}
	
	private void build(byte algorithm, byte keyType, short keyLength,String ref, String iv) {
		int i = 2;
		
		// Initializes the cipher in ENCRYPT mode
		if ( !isCalibrate )
		{
			addTestCase(new CipherTestCase("init"+iv+"_encrypt_"+algorithm+"_"+keyType+"_"+ keyLength,
					i,X,TestCase.Y,algorithm,Cipher.MODE_ENCRYPT,keyType,keyLength,false,
					(short)0,(short)0,(short)0)
					.setBenched("x+RETURN")
					.setReference(ref)
					.forCalibration());
			isCalibrate = true;
			calibration = "init"+iv+"_encrypt_"+algorithm+"_"+keyType+"_"+ keyLength;
		}
		else
		addTestCase(new CipherTestCase("init"+iv+"_encrypt_"+algorithm+"_"+keyType+"_"+ keyLength,
				i,X,TestCase.Y,algorithm,Cipher.MODE_ENCRYPT,keyType,keyLength,false,
				(short)0,(short)0,(short)0)
				.setBenched("x+RETURN")
				.setReference(ref)
				.setCalibration(calibration));
		
		switch (keyType) {
		case KeyBuilder.TYPE_DSA_PRIVATE:
			keyType = KeyBuilder.TYPE_DSA_PUBLIC;
			break;
		case KeyBuilder.TYPE_RSA_CRT_PRIVATE:
		case KeyBuilder.TYPE_RSA_PRIVATE:
			keyType = KeyBuilder.TYPE_RSA_PUBLIC;
			break;
		case KeyBuilder.TYPE_DSA_PUBLIC:
			keyType = KeyBuilder.TYPE_DSA_PRIVATE;
			break;
		case KeyBuilder.TYPE_RSA_PUBLIC:
			keyType = KeyBuilder.TYPE_RSA_PRIVATE;
			break;
		case KeyInfo.TYPE_RSA_CRT_PUBLIC:
			keyType = KeyBuilder.TYPE_RSA_CRT_PRIVATE;
			break;
		default:
			break;
		}
		
		// Initializes the cipher in DECRYPT mode
		if (RSAPublicAdded)
		  return;
		addTestCase(new CipherTestCase("init"+iv+"_decrypt_"+algorithm+"_"+keyType+"_"+ keyLength,
				i,X,TestCase.Y,algorithm,Cipher.MODE_DECRYPT,keyType,keyLength,false,
				(short)0,(short)0,(short)0)
				.setBenched("x+RETURN")
				.setReference(ref)
				.setCalibration(calibration));
		if (keyType == KeyBuilder.TYPE_RSA_PUBLIC)
		  RSAPublicAdded = true;
	}
}
