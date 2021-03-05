package scripts.jcapi.javacard_security.rsaprivatekey.setx;

import javacard.security.KeyBuilder;
import lib.cad.CADException;
import scripts.jcapi.javacard_security.KeyTestCase;
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
 * $HeadURL: $
 * Created: 15 mai 2007
 * Author: TL 
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

/**
 * The purpose of this test is to measure the performances of the 
 * <tt>RSAPrivateKey.setX</tt> methods.
 */
public class SetXScript extends TemplateScript {
	
  private final static byte[] KEY_TYPES = {
      KeyBuilder.TYPE_RSA_PRIVATE};	
				
  protected final static short[] KEY_LENGTHS = {
	  KeyBuilder.LENGTH_RSA_512, KeyBuilder.LENGTH_RSA_768, KeyBuilder.LENGTH_RSA_1024, KeyBuilder.LENGTH_RSA_2048};
  
  private final static int X = 9; 
  
  /** Indicates if the calibration reference test has been set. */
	private boolean isCalibrate = false;
	/** The name of the calibration reference test. */
	private String calibration = "";
  
  /**
   * Builds the test script used to measure performances of the
   * <tt>RSAPrivateCrtKey.setX</tt> methods.
 * @throws CADException 
   */
  public SetXScript() throws CADException {
    this(KEY_TYPES,KEY_LENGTHS);
  }
		
  /**
   * Builds the test script used to measure performances of the
   * <tt>RSAPrivateCrtKey.setX</tt> methods.
   * 
   * @param keyTypes the types of the RSA keys to be tested.
   * @param keyLengths the lengths of the RSA keys to be tested.
 * @throws CADException 
   */
  protected SetXScript(byte[] keyTypes, short[] keyLengths) throws CADException {
    for (int j = 0; j < keyLengths.length; j++) {
      isCalibrate = false;
      // The empty test cases used to computes overhead
      String ref = "setx_" + keyLengths[j] + "_ref";
      addTestCase(new KeyTestCase(ref,1,X,TestCase.Y,
  	        (byte)0,(short)0,false));
	  for (int i = 0; i < keyTypes.length; i++)
	    build(keyTypes[i],keyLengths[j],ref);
	  getTestCase(ref).setCalibration(calibration);
    }
  }
   
  private void build(byte keyType, short keyLength, String ref) {
	int i = 2;

	// Set the exponent
	// from a persistent array
	if ( ! isCalibrate)
	{
		addTestCase(new KeyTestCase("setexponent_P_pers_" + keyLength,i,X,TestCase.Y,
				keyType,keyLength,false)
				.setBenched("x+RETURN")
				.setReference(ref)
				.forCalibration());
		isCalibrate = true;
		calibration = "setexponent_P_pers_" + keyLength;
	}
	else addTestCase(new KeyTestCase("setexponent_P_pers_" + keyLength,i,X,TestCase.Y,
			keyType,keyLength,false)
			.setBenched("x+RETURN")
			.setReference(ref)
			.setCalibration(calibration));
	
    // Set the exponent
	// from a transient array
	addTestCase(new KeyTestCase("setexponent_P_cod_" + keyLength,++i,X,TestCase.Y,
		keyType,keyLength,false)
		.setBenched("x+RETURN")
		.setReference(ref)
		.setCalibration(calibration));
	
    // Set the modulus
	// from a persistent array
	addTestCase(new KeyTestCase("setmodulus_P_pers_" + keyLength,++i,X,TestCase.Y,
		keyType,keyLength,false)
		.setBenched("x+RETURN")
		.setReference(ref)
		.setCalibration(calibration));
	
    // Set the modulus
	// from a transient array
	addTestCase(new KeyTestCase("setmodulus_P_cod_" + keyLength,++i,X,TestCase.Y,
		keyType,keyLength,false)
		.setBenched("x+RETURN")
		.setReference(ref)
		.setCalibration(calibration));
  }
}