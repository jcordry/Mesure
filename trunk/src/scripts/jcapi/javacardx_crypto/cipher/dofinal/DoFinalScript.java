package scripts.jcapi.javacardx_crypto.cipher.dofinal;

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
 * <tt>Cipher.doFinal</tt> method.
 */
public class DoFinalScript extends TemplateScript{
  private boolean isCalibrate = false;
  private String calibration = "";
	    
  /**
   * Builds the test script used to measure performances of the 
   * <tt>Cipher.doFinal</tt> method.
   * 
   * @param x the number of executions to be performed on-card for this 
   *          identified test case.
   * @param algorithm the signature algorithm.
   * @param keyType the type of the key to be tested.
   * @param keyLength the length of the key to be tested.
   * @param mode the mode (<tt>ENCRYPT_MODE</tt> or <tt>DECRYPT_MODE</tt>).
 * @throws CADException 
   */
  protected DoFinalScript(int x, byte algorithm, byte keyType, short keyLength, byte mode) throws CADException {
    build(x,algorithm,keyType,keyLength,mode);
  }	  
  
  /**
   * Builds the test script used to measure performances of the 
   * <tt>Cipher.doFinal</tt> method.
   * 
   * @param algorithms the signature algorithms.
   * @param keyTypes the types of the keys to be tested.
   * @param keyLengths the lengths of the keys to be tested.
   * @param modes the modes.
 * @throws CADException 
   */
  protected DoFinalScript(byte[] algorithms, byte[] keyTypes, short[] keyLengths, byte[] modes) throws CADException{
	  for (int k = 0; k < keyLengths.length; k++)
	  {
		  isCalibrate = false;
		  for (int i = 0; i < algorithms.length; i++)
			  for (int j = 0; j < keyTypes.length; j++)
				  for (int l = 0; l < modes.length; l++) {
					  int x = 0;
					  switch (algorithms[i]) {
					  case Cipher.ALG_DES_CBC_ISO9797_M1:
					  case Cipher.ALG_DES_CBC_ISO9797_M2:
					  case Cipher.ALG_DES_CBC_NOPAD:
					  case Cipher.ALG_DES_ECB_ISO9797_M1:
					  case Cipher.ALG_DES_ECB_ISO9797_M2:
					  case Cipher.ALG_DES_ECB_NOPAD:
						  x = 13;
						  break;
					  case Cipher.ALG_RSA_NOPAD:
					  case Cipher.ALG_RSA_PKCS1:
						  switch (keyLengths[k]) {
						  case KeyBuilder.LENGTH_RSA_2048:
							  x = 5;
							  break;
						  case KeyBuilder.LENGTH_RSA_1024:
							  x = 7;
							  break;
						  case KeyBuilder.LENGTH_RSA_768:
							  x = 8;
							  break;
						  case KeyBuilder.LENGTH_RSA_512:
							  x = 9;
							  break;
						  default:
							  break;
						  }
						  break;
					  default:
						  break;
					  }
					  build(x,algorithms[i],keyTypes[j],keyLengths[k],modes[l]);
				  }
	  }
  }
	 
  private void build(int x, byte algorithm, byte keyType, short keyLength, byte mode) throws CADException {
	  short length = 0;
	  switch (algorithm) {
		case Cipher.ALG_DES_CBC_NOPAD:
		case Cipher.ALG_DES_CBC_ISO9797_M1:
		case Cipher.ALG_DES_CBC_ISO9797_M2: 
		case Cipher.ALG_DES_ECB_NOPAD:
		case Cipher.ALG_DES_ECB_ISO9797_M1:
		case Cipher.ALG_DES_ECB_ISO9797_M2:
		  length = 8;
		  break;
		case Cipher.ALG_RSA_PKCS1:
		  length = (short)(keyLength / 8);
		  length = (short)(length - 11);
		  if (mode == Cipher.MODE_DECRYPT)
		    keyType = changeKey(keyType);
		  break;
		case Cipher.ALG_RSA_NOPAD:
		  length = (short)(keyLength / 8);
		  if (mode == Cipher.MODE_DECRYPT)
		    keyType = changeKey(keyType);
		  break;
		default:
			break;
	  }
	  
    int i = 1;
      
    // The empty test case used to compute overhead
  	addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref",i,x,TestCase.Y,
  	      algorithm,mode,keyType,keyLength,false,
  	      (short)0,length,(short)0));

    // Encipher/decipher blockSize bytes of data without overlap
  	if ( !isCalibrate )
	{
    	addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_inOff=0_inLen=block_outOff=0",++i,x,TestCase.Y,
  	      algorithm,mode,keyType,keyLength,false,
  	      (short)0,length,(short)0)
  	      .setBenched("x+RETURN-POP")
  	      .setReference("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref")
  	      .forCalibration());
    	
    	isCalibrate = true;
		calibration = "dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_inOff=0_inLen=block_outOff=0";
	}
	else addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_inOff=0_inLen=block_outOff=0",++i,x,TestCase.Y,
	  	      algorithm,mode,keyType,keyLength,false,
	  	      (short)0,length,(short)0)
	  	      .setBenched("x+RETURN-POP")
	  	      .setReference("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref")
	  	      .setCalibration(calibration));
  	
  	getTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref").setCalibration(calibration);
  	
  	addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_inOff=64_inLen=block_outOff=0",i,x,TestCase.Y,
  	      algorithm,mode,keyType,keyLength,false,
  	      (short)32,length,(short)0)
  	      .setBenched("x+RETURN-POP")
  	      .setReference("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref")
  	      .setCalibration(calibration));
  	
  	addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_inOff=0_inLen=block_outOff=64",i,x,TestCase.Y,
  		      algorithm,mode,keyType,keyLength,false,
  		      (short)0,length,(short)32)
  		      .setBenched("x+RETURN-POP")
  		      .setReference("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref")
  		      .setCalibration(calibration));
  	
  	switch (algorithm) {
	  case Cipher.ALG_DES_CBC_NOPAD:
	  case Cipher.ALG_DES_CBC_ISO9797_M1:
	  case Cipher.ALG_DES_CBC_ISO9797_M2: 
	  case Cipher.ALG_DES_ECB_NOPAD:
	  case Cipher.ALG_DES_ECB_ISO9797_M1:
	  case Cipher.ALG_DES_ECB_ISO9797_M2:
  	    // Encipher/decipher 2*blockSize bytes of data without overlap
  	    addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_inOff=0_inLen=2*block_outOff=0",i,x,TestCase.Y,
            algorithm,mode,keyType,keyLength,false,
            (short)0,(short)(length*2),(short)0)
            .setBenched("x+RETURN-POP")
            .setReference("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref")
  		  	.setCalibration(calibration));
  	
        // Encipher/decipher 3*blockSize bytes of data without overlap
  	    addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_inOff=0_inLen=3*block_outOff=0",i,x,TestCase.Y,
  	        algorithm,mode,keyType,keyLength,false,
  	        (short)0,(short)(length*3),(short)0)
  	        .setBenched("x+RETURN-POP")
  	        .setReference("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref")
  	        .setCalibration(calibration));
  	    break;
  	  default:
  		break;
  	}

    // Encipher/decipher blockSize bytes of data without overlap when IV is set and null
  	++i;
  	switch (algorithm) {
  	   case Cipher.ALG_DES_CBC_NOPAD:
	   case Cipher.ALG_DES_CBC_ISO9797_M1:
	   case Cipher.ALG_DES_CBC_ISO9797_M2: 
  		 addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_iv_null",i,x,TestCase.Y,
  		     algorithm,mode,keyType,keyLength,false,
  		     (short)0,length,(short)0)
  		     .setBenched("x+RETURN-POP")
  		     .setReference("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref")
  		     .setCalibration(calibration));
  	    break;
  	  default:
  	    break;
  	}
  	
    // Encipher/decipher blockSize bytes of data without overlap when IV is set and not null
  	++i;
  	switch (algorithm) {
  	   case Cipher.ALG_DES_CBC_NOPAD:
	   case Cipher.ALG_DES_CBC_ISO9797_M1:
	   case Cipher.ALG_DES_CBC_ISO9797_M2: 
  		addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_iv_notnull",i,x,TestCase.Y,
  		     algorithm,mode,keyType,keyLength,false,
  		     (short)0,length,(short)0)
  		     .setBenched("x+RETURN-POP")
  		     .setReference("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref")
  		     .setCalibration(calibration));
  	    break;
  	  default:
  	    break;
  	}
  	
    // The empty test case used to compute overhead
  	addTestCase(new CipherTestCase("dofinal_overlap_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref",++i,x,TestCase.Y,
  	      algorithm,mode,keyType,keyLength,false,
  	      (short)0,length,(short)0)
  	    .setCalibration(calibration));
  	
  	// Encipher/decipher blockSize bytes of data with total overlap
  	addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_overlap",++i,x,TestCase.Y,
  		  algorithm,mode,keyType,keyLength,false,
  		  (short)0,length,(short)0)
  		  .setBenched("x+RETURN-POP")
  		  .setReference("dofinal_overlap_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_ref")
  		  .setCalibration(calibration));
  	
  	// The empty test case used to compute overhead
  	// when the APDU buffer is used as input buffer
  	addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_inBuff=apduBuffer_ref",++i,x,TestCase.Y,
  	  algorithm,mode,keyType,keyLength,false,
  	  (short)0,length,(short)0).setCalibration(calibration));
  	
    // Encipher/decipher blockSize bytes of data
    // when the APDU buffer is used as input buffer
  	addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_inBuff=apduBuffer",++i,x,TestCase.Y,
  	  algorithm,mode,keyType,keyLength,false,
  	  (short)0,length,(short)0)
  	  .setBenched("x+RETURN-POP")
  	  .setReference("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_inBuff=apduBuffer_ref")
  	  .setCalibration(calibration));
  	
    // The empty test case used to compute overhead
  	// when the APDU buffer is used as output buffer
  	addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_outBuff=apduBuffer_ref",++i,x,TestCase.Y,
  	  algorithm,mode,keyType,keyLength,false,
  	  (short)0,length,(short)0).setCalibration(calibration));
  	
    // Encipher/decipher blockSize bytes of data
    // when the APDU buffer is used as output buffer
  	addTestCase(new CipherTestCase("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_outBuff=apduBuffer",++i,x,TestCase.Y,
  	  algorithm,mode,keyType,keyLength,false,
  	  (short)0,length,(short)0)
  	  .setBenched("x+RETURN-POP")
  	  .setReference("dofinal_"+algorithm+"_"+keyType+"_"+keyLength+"_"+mode+"_outBuff=apduBuffer_ref")
  	  .setCalibration(calibration)); 
  }
  
  /**
   * Get the type of the key to be used to sign the message to be verified.
   * 
   * @return the key type.
   */
  private byte changeKey(byte keyType) {
	byte type = 0;
    switch (keyType) {
      case KeyBuilder.TYPE_DSA_PRIVATE:
    	type = KeyBuilder.TYPE_DSA_PUBLIC;
        break;
      case KeyBuilder.TYPE_DSA_PUBLIC:
    	type = KeyBuilder.TYPE_DSA_PRIVATE;
    	break;
      case KeyBuilder.TYPE_RSA_CRT_PRIVATE:
    	type = KeyInfo.TYPE_RSA_CRT_PUBLIC;
    	break;
      case KeyBuilder.TYPE_RSA_PRIVATE:
    	type = KeyBuilder.TYPE_RSA_PUBLIC;
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
