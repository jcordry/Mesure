package scripts.jcapi.javacard_security.signature.initiv;

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
 * Created: 16 mai 2007
 * Author: TL 
 * $LastChangedRevision: 1 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cboe $
 */

/**
 * The purpose of this test is to measure the performances of the 
 * <tt>Signature.init(Key,byte,byte[],short,short)</tt> method.
 */
public class InitIVScript extends TemplateScript {  
  private final static byte[] DES_ALGOS = {
    Signature.ALG_DES_MAC8_ISO9797_M1, Signature.ALG_DES_MAC8_ISO9797_M2, Signature.ALG_DES_MAC8_NOPAD};	
		  
  private final static byte[] DES_KEY_TYPES = {
    KeyBuilder.TYPE_DES, KeyBuilder.TYPE_DES_TRANSIENT_DESELECT};	
				
  private final static short[] DES_KEY_LENGTHS = {
	KeyBuilder.LENGTH_DES, KeyBuilder.LENGTH_DES3_2KEY, KeyBuilder.LENGTH_DES3_3KEY};
			   
  private final static int X = 22;
  
  private boolean isCalibrate = false;
  private String calibration = "";
  
  /**
   * Builds the test script used to measure performances of the
   * <tt>Signature.init(Key,byte,byte[],short,short)</tt> method.
 * @throws CADException 
   */
  public InitIVScript() throws CADException {
    build(DES_ALGOS,DES_KEY_TYPES,DES_KEY_LENGTHS,"des");
  }

  /**
   * Builds the test script used to measure performances of the 
   * <tt>Signature.init</tt> method.
   * 
   * @param algorithms the signature algorithms.
   * @param keyTypes the types of the keys to be tested.
   * @param keyLengths the lengths of the keys to be tested.
 * @throws CADException 
   */
  private void build(byte[] algorithms, byte[] keyTypes, short[] keyLengths, String type) throws CADException{
	for (int k = 0; k < keyLengths.length; k++) {
	  isCalibrate = false;
      // The empty test cases used to compute overhead
	  String ref = "initivsign_" + type + "_" + keyLengths[k] + "_ref";
	  addTestCase(new SignatureTestCase(ref,1,X,TestCase.Y,
	      (byte)0,Signature.MODE_SIGN,(byte)0,(short)0,false,
		  (short)0,(short)0,(short)0));
	  for (int i = 0; i < algorithms.length; i++)
	    for (int j = 0; j < keyTypes.length; j++)
	      build(algorithms[i],keyTypes[j],keyLengths[k],ref);
	  getTestCase(ref).setCalibration(calibration);
	}
  }
  
  private void build(byte algorithm, byte keyType, short keyLength, String ref) { 
    int i = 2;

	// Initializes the signature in SIGN mode
	// IV is a persistent array
    if ( !isCalibrate )
	{
    	addTestCase(new SignatureTestCase("initiv_pers_sign_" + algorithm + "_" + keyType + "_" + keyLength,i,X,TestCase.Y,
	      algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	      (short)0,(short)0,(short)0)
	      .setBenched("x+RETURN")
	      .setReference(ref)
	      .forCalibration());
    	isCalibrate = true;
		calibration = "initiv_pers_sign_" + algorithm + "_" + keyType + "_" + keyLength;
	}
	else addTestCase(new SignatureTestCase("initiv_pers_sign_" + algorithm + "_" + keyType + "_" + keyLength,i,X,TestCase.Y,
		      algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
		      (short)0,(short)0,(short)0)
		      .setBenched("x+RETURN")
		      .setReference(ref)
		      .setCalibration(calibration));
	
    // Initializes the signature in SIGN mode
	// IV is a transient array
	addTestCase(new SignatureTestCase("initiv_cod_sign_" + algorithm + "_" + keyType + "_" + keyLength,++i,X,TestCase.Y,
	      algorithm,Signature.MODE_SIGN,keyType,keyLength,false,
	      (short)0,(short)0,(short)0)
	      .setBenched("x+RETURN")
	      .setReference(ref)
	      .setCalibration(calibration));
	
    // Initializes the signature in VERIFY mode
	// IV is a persistent array
	addTestCase(new SignatureTestCase("initiv_pers_verify_" + algorithm + "_" + keyType + "_" + keyLength,--i,X,TestCase.Y,
	      algorithm,Signature.MODE_VERIFY,keyType,keyLength,false,
	      (short)0,(short)0,(short)0)
	      .setBenched("x+RETURN")
	      .setReference(ref)
	      .setCalibration(calibration));
	
    // Initializes the signature in VERIFY mode
	// IV is a transient array
	addTestCase(new SignatureTestCase("initiv_cod_verify_" + algorithm + "_" + keyType + "_" + keyLength,++i,X,TestCase.Y,
	      algorithm,Signature.MODE_VERIFY,keyType,keyLength,false,
	      (short)0,(short)0,(short)0)
	      .setBenched("x+RETURN")
	      .setReference(ref)
	      .setCalibration(calibration));
  }
}
