package scripts.jcapi.javacard_security.signature.verify;

import javacard.security.KeyBuilder;
import javacard.security.Signature;
import lib.cad.CADException;
import scripts.jcapi.javacard_security.signature.SignatureTestCase;
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
 * <tt>Signature.verify</tt> method.
 */
public class VerifyScript extends TemplateScript{
	
	  private boolean isCalibrate = false;
	  private String calibration = "";
	  
	  
  /**
   * Builds the test script used to measure performances of the 
   * <tt>Signature.verify</tt> method.
 * @throws CADException 
   */
  protected VerifyScript(int x, byte algorithm, byte keyType, short keyLength) throws CADException {
	build(x,algorithm,keyType,keyLength);
  }	  
  
  /**
   * Builds the test script used to measure performances of the 
   * <tt>Signature.verify</tt> method.
   * 
   * @param algorithms the signature algorithms.
   * @param keyTypes the types of the keys to be tested.
   * @param keyLengths the lengths of the keys to be tested.
 * @throws CADException 
   */
  protected VerifyScript(byte[] algorithms, byte[] keyTypes, short[] keyLengths) throws CADException{
	  for (int k = 0; k < keyLengths.length; k++)
	  {
		  isCalibrate = false;
		  for (int i = 0; i < algorithms.length; i++)
			  for (int j = 0; j < keyTypes.length; j++)
			  {
				  int x = 0;
				  switch (algorithms[i]) {
				  case Signature.ALG_DES_MAC4_ISO9797_M1:
				  case Signature.ALG_DES_MAC4_ISO9797_M2:
				  case Signature.ALG_DES_MAC4_NOPAD:
				  case Signature.ALG_DES_MAC4_PKCS5:  
				  case Signature.ALG_DES_MAC8_ISO9797_M1:
				  case Signature.ALG_DES_MAC8_ISO9797_M2:
				  case Signature.ALG_DES_MAC8_NOPAD:
				  case Signature.ALG_DES_MAC8_PKCS5:
					  x = 16;
					  break;
				  case Signature.ALG_DSA_SHA: 
				  case Signature.ALG_RSA_MD5_PKCS1:
				  case Signature.ALG_RSA_MD5_RFC2409:
				  case Signature.ALG_RSA_RIPEMD160_ISO9796:
				  case Signature.ALG_RSA_RIPEMD160_PKCS1:
				  case Signature.ALG_RSA_SHA_ISO9796:
				  case Signature.ALG_RSA_SHA_PKCS1:
				  case Signature.ALG_RSA_SHA_RFC2409:
					  switch (keyLengths[k]) {
					  case KeyBuilder.LENGTH_RSA_2048:
						  x = 5;
						  break;
					  case KeyBuilder.LENGTH_RSA_1024:
						  x = 6;
						  break;
					  case KeyBuilder.LENGTH_RSA_768:
						  x = 7;
						  break;
					  case KeyBuilder.LENGTH_RSA_512:
						  x = 8;
						  break;
					  default:
						  break;
					  }
					  break;
				  default:
					  break;
				  }
				  build(x,algorithms[i],keyTypes[j],keyLengths[k]);
			  }
	  }
  }
	  
  private void build(int x, byte algorithm, byte keyType, short keyLength) throws CADException {	  
    short blockSize = 0;
	switch (algorithm) {
	  case Signature.ALG_DES_MAC4_ISO9797_M1:
	  case Signature.ALG_DES_MAC4_ISO9797_M2:
	  case Signature.ALG_DES_MAC4_NOPAD:
	  case Signature.ALG_DES_MAC4_PKCS5: 
	  case Signature.ALG_DES_MAC8_ISO9797_M1:
	  case Signature.ALG_DES_MAC8_ISO9797_M2:
	  case Signature.ALG_DES_MAC8_NOPAD:
	  case Signature.ALG_DES_MAC8_PKCS5:
		blockSize = 8; 
		break;
	  case Signature.ALG_DSA_SHA: 
	  case Signature.ALG_RSA_MD5_PKCS1:
	  case Signature.ALG_RSA_MD5_RFC2409:
	  case Signature.ALG_RSA_RIPEMD160_ISO9796:
	  case Signature.ALG_RSA_RIPEMD160_PKCS1:
	  case Signature.ALG_RSA_SHA_ISO9796:
	  case Signature.ALG_RSA_SHA_PKCS1:
	  case Signature.ALG_RSA_SHA_RFC2409:
		blockSize = 64;
		keyType = changeKey(keyType);
		break;
	  default:
		break;
    }  
	  
    int i = 1;
    	
	// The empty test cases used to compute overhead
	addTestCase(new SignatureTestCase("verify_ref_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
	      algorithm,Signature.MODE_VERIFY,keyType,keyLength,false,
	      (short)0,blockSize,(short)0));

    // Verify the signature produced by blockSize bytes of data 
	if ( !isCalibrate )
	{
    	addTestCase(new SignatureTestCase("verify_inLen=block_" + algorithm + "_" + keyType + "_" + keyLength,++i,x,TestCase.Y,
	      algorithm,Signature.MODE_VERIFY,keyType,keyLength,false,
	      (short)0,blockSize,(short)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("verify_ref_" + algorithm + "_" + keyType + "_" + keyLength)
	      .forCalibration());

    	isCalibrate = true;
		calibration = "verify_inLen=block_" + algorithm + "_" + keyType + "_" + keyLength;
	}
	else addTestCase(new SignatureTestCase("verify_inLen=block_" + algorithm + "_" + keyType + "_" + keyLength,++i,x,TestCase.Y,
		      algorithm,Signature.MODE_VERIFY,keyType,keyLength,false,
		      (short)0,blockSize,(short)0)
		      .setBenched("x+RETURN-POP")
		      .setReference("verify_ref_" + algorithm + "_" + keyType + "_" + keyLength)
		      .setCalibration(calibration));
	
	getTestCase("verify_ref_" + algorithm + "_" + keyType + "_" + keyLength).setCalibration(calibration);
	
    // Verify the signature produced by 2*blockSize bytes of data 
	addTestCase(new SignatureTestCase("verify_inLen=2*block_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
	      algorithm,Signature.MODE_VERIFY,keyType,keyLength,false,
	      (short)0,(short)(blockSize*2),(short)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("verify_ref_" + algorithm + "_" + keyType + "_" + keyLength)
	      .setCalibration(calibration));
	
    // Verify the signature produced by 3*blockSize bytes of data 
	addTestCase(new SignatureTestCase("verify_inLen=3*block_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
	      algorithm,Signature.MODE_VERIFY,keyType,keyLength,false,
	      (short)0,(short)(blockSize*3),(short)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("verify_ref_" + algorithm + "_" + keyType + "_" + keyLength)
	      .setCalibration(calibration));
	
    // Verify the signature produced by blockSize byte of data when IV is set and is null
	++i;
	switch (algorithm) {
	  case Signature.ALG_DES_MAC4_ISO9797_M1:
	  case Signature.ALG_DES_MAC4_ISO9797_M2:
	  case Signature.ALG_DES_MAC4_NOPAD:
	  case Signature.ALG_DES_MAC4_PKCS5:
	  case Signature.ALG_DES_MAC8_ISO9797_M1:
	  case Signature.ALG_DES_MAC8_ISO9797_M2:
	  case Signature.ALG_DES_MAC8_NOPAD:
	  case Signature.ALG_DES_MAC8_PKCS5:
		addTestCase(new SignatureTestCase("verify_iv_null_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
		     algorithm,Signature.MODE_VERIFY,keyType,keyLength,false,
		     (short)0,blockSize,(short)0)
		     .setBenched("x+RETURN-POP")
		     .setReference("verify_ref_" + algorithm + "_" + keyType + "_" + keyLength)
			 .setCalibration(calibration));
	    break;
	  default:
	    break;
	}
	
    // Verify the signature produced by blockSize byte of data when IV is set and is not null
	++i;
	switch (algorithm) {
	  case Signature.ALG_DES_MAC4_ISO9797_M1:
	  case Signature.ALG_DES_MAC4_ISO9797_M2:
	  case Signature.ALG_DES_MAC4_NOPAD:
	  case Signature.ALG_DES_MAC4_PKCS5:
	  case Signature.ALG_DES_MAC8_ISO9797_M1:
	  case Signature.ALG_DES_MAC8_ISO9797_M2:
	  case Signature.ALG_DES_MAC8_NOPAD:
	  case Signature.ALG_DES_MAC8_PKCS5:
		addTestCase(new SignatureTestCase("verify_iv_notnull_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
		     algorithm,Signature.MODE_VERIFY,keyType,keyLength,false,
		     (short)0,blockSize,(short)0)
		     .setBenched("x+RETURN-POP")
		     .setReference("verify_ref_" + algorithm + "_" + keyType + "_" + keyLength)
			 .setCalibration(calibration));
	    break;
	  default:
	    break;
	}
  }
  
  /**
   * Get the type of the key to be used to sign the message to be verified.
   * 
   * @return the key type.
   */
  private byte changeKey(byte keyType) {
	byte type = 0;
    switch (keyType) {
      case KeyBuilder.TYPE_DSA_PUBLIC:
    	type = KeyBuilder.TYPE_DSA_PRIVATE;
    	break;
      case KeyBuilder.TYPE_RSA_PUBLIC:
    	type = KeyBuilder.TYPE_RSA_PRIVATE;
    	break;
      case KeyInfo.TYPE_RSA_CRT_PUBLIC:
    	type = KeyBuilder.TYPE_RSA_CRT_PRIVATE;
    	break;
      default:
        break;
    }
    return type;
  }
}
