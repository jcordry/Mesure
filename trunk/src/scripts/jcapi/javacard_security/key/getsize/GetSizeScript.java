package scripts.jcapi.javacard_security.key.getsize;

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
 * $HeadURL: svn+ssh://corentinboe@gforge.inria.fr/svn/mesureprv/src/benchs/jcapi/javacard_framework/util/applet/ArrayCopyTestApplet.java $
 * Created: 19 f?vrier 2007
 * Author: TL 
 * $LastChangedRevision: 1 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cboe $
 */

/**
 * The purpose of this test is to measure the performances of the 
 * <tt>Key.getSize</tt> method.
 */
public class GetSizeScript extends TemplateScript {
   private final static byte[] DES_KEY_TYPES = {
       KeyBuilder.TYPE_DES, KeyBuilder.TYPE_DES_TRANSIENT_DESELECT};	
	
   private final static short[] DES_KEY_LENGTHS = {
       KeyBuilder.LENGTH_DES, KeyBuilder.LENGTH_DES3_2KEY, KeyBuilder.LENGTH_DES3_3KEY};
   
   private final static byte[] RSA_KEY_TYPES = {
	   KeyBuilder.TYPE_RSA_PRIVATE, KeyBuilder.TYPE_RSA_CRT_PRIVATE, KeyBuilder.TYPE_RSA_PUBLIC};
   
   private final static short[] RSA_KEY_LENGTHS = {
       KeyBuilder.LENGTH_RSA_512, KeyBuilder.LENGTH_RSA_768, KeyBuilder.LENGTH_RSA_1024, KeyBuilder.LENGTH_RSA_2048};
	
	/** Indicates if the calibration reference test has been set. */
	private boolean isCalibrate = false;
	/** The name of the calibration reference test. */
	private String calibration = "";
	
	/**
	 * Builds the test script used to measure performances of the
	 * <tt>Key.getSize</tt> method.
	 * 
	 * @param keyTypes the types of the keys to be tested.
	 * @param keyLengths the lengths of the keys to be tested.
	 * @throws CADException 
	 */
	public GetSizeScript() throws CADException {
		build(DES_KEY_TYPES,DES_KEY_LENGTHS);
		build(RSA_KEY_TYPES,RSA_KEY_LENGTHS);
	}

   private void build(byte[] keyTypes, short[] keyLengths) throws CADException {
	 for (int j = 0; j < keyLengths.length; j++) {
	   isCalibrate = false;
       for (int i = 0; i < keyTypes.length; i++)
         build(keyTypes[i],keyLengths[j]);
	 }
   }
	   
   private void build(byte keyType, short keyLength) throws CADException {
	 int i = 1;
	 // The empty test cases used to compute overhead
	 addTestCase(new KeyTestCase("getsize_" + keyType + "_" + keyLength + "_ref",i,(short)40,TestCase.Y,
        keyType,keyLength,false));

	// Get the key size
	 if ( !isCalibrate)
	 {
		 addTestCase(new KeyTestCase("getsize_" + keyType + "_" + keyLength,++i,(short)40,TestCase.Y,
				 keyType,keyLength,false)
				 .setBenched("x+RETURN-POP")
				 .setReference("getsize_" + keyType + "_" + keyLength + "_ref")
				 .forCalibration());
		 isCalibrate = true;
		 calibration = "getsize_" + keyType + "_" + keyLength;
	 }
	 else addTestCase(new KeyTestCase("getsize_" + keyType + "_" + keyLength,++i,(short)40,TestCase.Y,
			 keyType,keyLength,false)
			 .setBenched("x+RETURN-POP")
			 .setReference("getsize_" + keyType + "_" + keyLength + "_ref")
			 .setCalibration(calibration));
	 
	 getTestCase("getsize_" + keyType + "_" + keyLength + "_ref").setCalibration(calibration);
  }
}