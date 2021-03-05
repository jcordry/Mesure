package scripts.jcapi.javacard_security.rsapublickey.setx;

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
 * <tt>RSAPublicKey.setX</tt> methods.
 */
public class SetXScript extends TemplateScript {
	
  private final static byte[] KEY_TYPES = {
      KeyBuilder.TYPE_RSA_PUBLIC};	
				
  protected final static short[] KEY_LENGTHS = {
	  KeyBuilder.LENGTH_RSA_512, KeyBuilder.LENGTH_RSA_768, KeyBuilder.LENGTH_RSA_1024, KeyBuilder.LENGTH_RSA_2048};
  
  private final static int X1 = 10; 
  private final static int X2 = 14; 
 
  /** Indicates if the calibration reference test has been set. */
	private boolean isExponentCalibrate = false;
	private boolean isModulusCalibrate = false;
	/** The name of the calibration reference test. */
	private String exponentCalibration = "";
	private String modulusCalibration = "";
  
  /**
   * Builds the test script used to measure performances of the
   * <tt>RSAPublicKey.setX</tt> methods.
 * @throws CADException 
   */
  public SetXScript() throws CADException {
    this(KEY_TYPES,KEY_LENGTHS);
  }
		
  /**
   * Builds the test script used to measure performances of the
   * <tt>RSAPublicKey.setX</tt> methods.
   * 
   * @param keyTypes the types of the RSA keys to be tested.
   * @param keyLengths the lengths of the RSA keys to be tested.
 * @throws CADException 
   */
  protected SetXScript(byte[] keyTypes, short[] keyLengths) throws CADException {
    for (int j = 0; j < keyLengths.length; j++) {
      isExponentCalibrate = false;
      isModulusCalibrate = false;
      // The empty test cases used to computes overhead
      String exponentRef = "setexponent_" + keyLengths[j] + "_ref";
      String modulusRef = "setmodulus_" + keyLengths[j] + "_ref";
      addTestCase(new KeyTestCase(exponentRef,1,X2,TestCase.Y,
  	        (byte)0,(short)0,false));
      addTestCase(new KeyTestCase(modulusRef,1,X1,TestCase.Y,
  	        (byte)0,(short)0,false));

	  for (int i = 0; i < keyTypes.length; i++)
	    build(keyTypes[i],keyLengths[j], exponentRef, modulusRef);
	  getTestCase(exponentRef).setCalibration(exponentCalibration);
	  getTestCase(modulusRef).setCalibration(modulusCalibration);
    }
  }
   
  private void build(byte keyType, short keyLength, String exponentRef, String modulusRef) {
	int i = 2;

	// Set the exponent
	// from a persistent array
	if ( ! isExponentCalibrate)
	{
		addTestCase(new KeyTestCase("setexponent_p_pers_" + keyLength,i,X2,TestCase.Y,
		keyType,keyLength,false)
		.setBenched("x+RETURN")
		.setReference(exponentRef)
		.forCalibration());
		isExponentCalibrate = true;
		exponentCalibration = "setexponent_p_pers_" + keyLength;
	}
	else addTestCase(new KeyTestCase("setexponent_p_pers_" + keyLength,i,X2,TestCase.Y,
			keyType,keyLength,false)
			.setBenched("x+RETURN")
			.setReference(exponentRef)
			.setCalibration(exponentCalibration));
	
    // Set the exponent
	// from a transient array
	addTestCase(new KeyTestCase("setexponent_p_cod_" + keyLength,++i,X2,TestCase.Y,
		keyType,keyLength,false)
		.setBenched("x+RETURN")
		.setReference(exponentRef)
		.setCalibration(exponentCalibration));
	
    // Set the modulus
	// from a persistent array
	if ( ! isModulusCalibrate)
	{
		addTestCase(new KeyTestCase("setmodulus_p_pers_" + keyLength,++i,X1,TestCase.Y,
		keyType,keyLength,false)
		.setReference(modulusRef)
		.forCalibration());
		isModulusCalibrate = true;
		modulusCalibration = "setmodulus_p_pers_" + keyLength;
	}
	else addTestCase(new KeyTestCase("setmodulus_p_pers_" + keyLength,++i,X1,TestCase.Y,
			keyType,keyLength,false)
			.setBenched("x+RETURN")
			.setReference(modulusRef)
			.setCalibration(modulusCalibration));
	
    // Set the modulus
	// from a transient array
	addTestCase(new KeyTestCase("setmodulus_p_cod_" + keyLength,++i,X1,TestCase.Y,
		keyType,keyLength,false)
		.setBenched("x+RETURN")
		.setReference(modulusRef)
		.setCalibration(modulusCalibration));
  }
}