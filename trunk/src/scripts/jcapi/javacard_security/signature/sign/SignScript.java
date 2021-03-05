package scripts.jcapi.javacard_security.signature.sign;

import javacard.security.KeyBuilder;
import javacard.security.Signature;
import lib.cad.CADException;
import scripts.jcapi.javacard_security.signature.SignatureTestCase;
import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

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
public class SignScript extends TemplateScript{
  private final static byte[] DES_ALGOS = {
      Signature.ALG_DES_MAC8_ISO9797_M1, Signature.ALG_DES_MAC8_ISO9797_M2, Signature.ALG_DES_MAC8_NOPAD};	
		  
  private final static byte[] RSA_ALGOS = {
      Signature.ALG_RSA_SHA_ISO9796, Signature.ALG_RSA_SHA_PKCS1};	
			
  private final static byte[] DES_KEY_TYPES = {
	  KeyBuilder.TYPE_DES, KeyBuilder.TYPE_DES_TRANSIENT_DESELECT};	
				
  private final static short[] DES_KEY_LENGTHS = {
	  KeyBuilder.LENGTH_DES, KeyBuilder.LENGTH_DES3_2KEY, KeyBuilder.LENGTH_DES3_3KEY};
			   
  private final static byte[] RSA_KEY_TYPES = {
	  KeyBuilder.TYPE_RSA_PRIVATE, KeyBuilder.TYPE_RSA_CRT_PRIVATE};
			   
  private final static short[] RSA_KEY_LENGTHS = {
	  KeyBuilder.LENGTH_RSA_512, KeyBuilder.LENGTH_RSA_768, KeyBuilder.LENGTH_RSA_1024, KeyBuilder.LENGTH_RSA_2048};	
  
  private boolean isCalibrate = false;
  private String calibration = "";
  
  /**
   * Builds the test script used to measure performances of the
   * <tt>Signature.sign</tt> method.
 * @throws CADException 
   */
  public SignScript() throws CADException {  
	build(RSA_ALGOS,RSA_KEY_TYPES,RSA_KEY_LENGTHS);
    build(DES_ALGOS,DES_KEY_TYPES,DES_KEY_LENGTHS);
  }
  
  /**
   * Builds the test script used to measure performances of the 
   * <tt>Signature.sign</tt> method.
   * 
   * @param algorithms the signature algorithms.
   * @param keyTypes the types of the keys to be tested.
   * @param keyLengths the lengths of the keys to be tested.
 * @throws CADException 
   */
  private void build(byte[] algorithms, byte[] keyTypes, short[] keyLengths) throws CADException{
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
						  x = 2;
						  break;
					  case KeyBuilder.LENGTH_RSA_1024:
						  x = 3;
						  break;
					  case KeyBuilder.LENGTH_RSA_768:
						  x = 4;
						  break;
					  case KeyBuilder.LENGTH_RSA_512:
						  x = 6;
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
	    break;
	  default:
	    break;
	}
	  
    int i = 1;
    	
	// The empty test case used to compute overhead
	addTestCase(new SignatureTestCase("sign_ref_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
	      algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	      (short)0,blockSize,(short)0));

    // Sign blockSize bytes of data without overlap
	if ( !isCalibrate )
	{
    	addTestCase(new SignatureTestCase("sign_inOff=0_inLen=block_sigOff=0_" + algorithm + "_" + keyType + "_" + keyLength,++i,x,TestCase.Y,
	      algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	      (short)0,blockSize,(short)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("sign_ref_" + algorithm + "_" + keyType + "_" + keyLength)
	      .forCalibration());
    	
    	isCalibrate = true;
		calibration = "sign_inOff=0_inLen=block_sigOff=0_" + algorithm + "_" + keyType + "_" + keyLength;
	}
	else addTestCase(new SignatureTestCase("sign_inOff=0_inLen=block_sigOff=0_" + algorithm + "_" + keyType + "_" + keyLength,++i,x,TestCase.Y,
		      algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
		      (short)0,blockSize,(short)0)
		      .setBenched("x+RETURN-POP")
		      .setReference("sign_ref_" + algorithm + "_" + keyType + "_" + keyLength)
		      .setCalibration(calibration));
	
	getTestCase("sign_ref_" + algorithm + "_" + keyType + "_" + keyLength).setCalibration(calibration);
	
	addTestCase(new SignatureTestCase("sign_inOff=64_inLen=block_sigOff=0_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
	      algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	      (short)64,blockSize,(short)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("sign_ref_" + algorithm + "_" + keyType + "_" + keyLength)
	      .setCalibration(calibration));
	
	addTestCase(new SignatureTestCase("sign_inOff=0_inLen=block_sigOff=64_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
		      algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
		      (short)0,blockSize,(short)64)
		      .setBenched("x+RETURN-POP")
		      .setReference("sign_ref_" + algorithm + "_" + keyType + "_" + keyLength)
		      .setCalibration(calibration));
	
	// Sign 2*blockSize bytes of data without overlap
	addTestCase(new SignatureTestCase("sign_inOff=0_inLen=2*block_sigOff=0_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
          algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
          (short)0,(short)(blockSize*2),(short)0)
          .setBenched("x+RETURN-POP")
          .setReference("sign_ref_" + algorithm + "_" + keyType + "_" + keyLength)
		  .setCalibration(calibration));
	
    // Sign 3*blockSize bytes of data without overlap
	addTestCase(new SignatureTestCase("sign_inOff=0_inLen=3*block_sigOff=0_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
	    algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	    (short)0,(short)(blockSize*3),(short)0)
	    .setBenched("x+RETURN-POP")
	    .setReference("sign_ref_" + algorithm + "_" + keyType + "_" + keyLength)
		.setCalibration(calibration));

    // Sign blockSize bytes of data without overlap when IV is set and null
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
		addTestCase(new SignatureTestCase("sign_iv_null_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
		     algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
		     (short)0,blockSize,(short)0)
		     .setBenched("x+RETURN-POP")
		     .setReference("sign_ref_" + algorithm + "_" + keyType + "_" + keyLength)
			 .setCalibration(calibration));
	    break;
	  default:
	    break;
	}
	
    // Sign blockSize bytes of data without overlap when IV is set and not null
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
		addTestCase(new SignatureTestCase("sign_iv_notnull_" + algorithm + "_" + keyType + "_" + keyLength,i,x,TestCase.Y,
		     algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
		     (short)0,blockSize,(short)0)
		     .setBenched("x+RETURN-POP")
		     .setReference("sign_ref_" + algorithm + "_" + keyType + "_" + keyLength)
			 .setCalibration(calibration));
	    break;
	  default:
	    break;
	}
	
    // The empty test case used to compute overhead
	addTestCase(new SignatureTestCase("sign_overlap_ref_" + algorithm + "_" + keyType + "_" + keyLength,++i,x,TestCase.Y,
	      algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	      (short)0,blockSize,(short)0)
	      .setCalibration(calibration));
	
	 // Sign blockSize bytes of data with total overlap
	addTestCase(new SignatureTestCase("sign_overlap_" + algorithm + "_" + keyType + "_" + keyLength,++i,x,TestCase.Y,
		  algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
		  (short)0,blockSize,(short)0)
		  .setBenched("x+RETURN-POP")
		  .setReference("sign_overlap_ref_" + algorithm + "_" + keyType + "_" + keyLength)
		  .setCalibration(calibration));
	
	// The empty test case used to compute overhead
	// when the APDU buffer is used as input buffer
	addTestCase(new SignatureTestCase("sign_inBuff=apduBuffer_ref_" + algorithm + "_" + keyType + "_" + keyLength,++i,x,TestCase.Y,
	  algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	  (short)0,blockSize,(short)0)
	  .setCalibration(calibration));
	
    // Sign blockSize bytes of data
    // when the APDU buffer is used as input buffer
	addTestCase(new SignatureTestCase("sign_inBuff=apduBuffer_" + algorithm + "_" + keyType + "_" + keyLength,++i,x,TestCase.Y,
	    algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	    (short)0,blockSize,(short)0)
	    .setBenched("x+RETURN-POP")
	    .setReference("sign_inBuff=apduBuffer_ref_" + algorithm + "_" + keyType + "_" + keyLength)
	    .setCalibration(calibration));
	
    // The empty test case used to compute overhead
	// when the APDU buffer is used as output buffer
	addTestCase(new SignatureTestCase("sign_sigBuff=apduBuffer_ref_" + algorithm + "_" + keyType + "_" + keyLength,++i,x,TestCase.Y,
	  algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	  (short)0,blockSize,(short)0)
	  .setCalibration(calibration));
	
    // Sign blockSize bytes of data
    // when the APDU buffer is used as output buffer
	addTestCase(new SignatureTestCase("sign_sigBuff=apduBuffer_" + algorithm + "_" + keyType + "_" + keyLength,++i,x,TestCase.Y,
	    algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	    (short)0,blockSize,(short)0)
	    .setBenched("x+RETURN-POP")
	    .setReference("sign_sigBuff=apduBuffer_ref_" + algorithm + "_" + keyType + "_" + keyLength)
	    .setCalibration(calibration));
  }
}
