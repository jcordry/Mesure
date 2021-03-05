package scripts.jcapi.javacard_security.randomdata.generatedata;

import javacard.security.RandomData;
import scripts.jcapi.javacard_security.randomdata.RandomTestCase;
import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

/*
 * Copyright (c) 2006-2007 Mesure Project
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

/**
 * The purpose of this test is to measure the performances of the 
 * <tt>RandomData.generateData</tt> method.
 */
public class GenerateDataScript extends TemplateScript{
	
  /**
   * Builds the test script used to measure performances of the 
   * <tt>RandomData.generateData</tt> method.
   */
  public GenerateDataScript() {
    int i = 1;
    short x = 28;
		
	// The empty test cases used to compute overhead
    addTestCase(new RandomTestCase("generatedata_ref",i,x,TestCase.Y,
	    RandomData.ALG_SECURE_RANDOM,(short)0,(short)8).setCalibration("generatedata_0_8"));

	// Generate 8 bytes of random data at offset 0
    addTestCase(new RandomTestCase("generatedata_0_8",++i,x,TestCase.Y,
        RandomData.ALG_SECURE_RANDOM,(short)0,(short)8)
        	.setBenched("x+RETURN")
        	.setReference("generatedata_ref")
        	.forCalibration());
	   
    // Generate 16 bytes of random data at offset 0
    addTestCase(new RandomTestCase("generatedata_0_16",i,x,TestCase.Y,
        RandomData.ALG_SECURE_RANDOM,(short)0,(short)16)
    	.setBenched("x+RETURN")
    	.setReference("generatedata_ref")
    	.setCalibration("generatedata_0_8"));
   
    // Generate 24 bytes of random data at offset 0
    addTestCase(new RandomTestCase("generatedata_0_24",i,x,TestCase.Y,
        RandomData.ALG_SECURE_RANDOM,(short)0,(short)24)
    	.setBenched("x+RETURN")
    	.setReference("generatedata_ref")
    	.setCalibration("generatedata_0_8"));
   
    // Generate 8 bytes of random data at offset 64
    addTestCase(new RandomTestCase("generatedata_64_8",i,x,TestCase.Y,
      RandomData.ALG_SECURE_RANDOM,(short)64,(short)8)
  		.setBenched("x+RETURN")
  		.setReference("generatedata_ref")
  		.setCalibration("generatedata_0_8"));

    // Generate 8 bytes of random data at offset 128
    addTestCase(new RandomTestCase("generatedata_128_8",i,x,TestCase.Y,
      RandomData.ALG_SECURE_RANDOM,(short)128,(short)8)
  		.setBenched("x+RETURN")
  		.setReference("generatedata_ref")
  		.setCalibration("generatedata_0_8"));

    // Generate 8 bytes of random data at offset 172
    addTestCase(new RandomTestCase("generatedata_172_8",i,x,TestCase.Y,
      RandomData.ALG_SECURE_RANDOM,(short)172,(short)8)
  		.setBenched("x+RETURN")
  		.setReference("generatedata_ref")
  		.setCalibration("generatedata_0_8"));

    // The empty test cases used to compute overhead
    // when random data are generated in the APDU buffer
    addTestCase(new RandomTestCase("generatedata_apdubuffer_ref",++i,x,TestCase.Y,
	    RandomData.ALG_SECURE_RANDOM,(short)0,
	    (short)8).setCalibration("generatedata_apdubuffer_0_8"));

    // Generate 8 bytes of random data at offset 0 in the APDU buffer
    addTestCase(new RandomTestCase("generatedata_apdubuffer_0_8",++i,x,TestCase.Y,
        RandomData.ALG_SECURE_RANDOM,(short)0,(short)8)
        	.setBenched("x+RETURN")
        	.setReference("generatedata_apdubuffer_ref")
        	.forCalibration());
    
    // Generate 8 bytes of random data at offset 64 in the APDU buffer
    addTestCase(new RandomTestCase("generatedata_apdubuffer_64_8",i,x,TestCase.Y,
        RandomData.ALG_SECURE_RANDOM,(short)64,(short)8)
    		.setBenched("x+RETURN")
    		.setReference("generatedata_apdubuffer_ref")
    		.setCalibration("generatedata_apdubuffer_0_8"));

    // Generate 8 bytes of random data at offset 128 in the APDU buffer
    addTestCase(new RandomTestCase("generatedata_apdubuffer_128_8",i,x,TestCase.Y,
        RandomData.ALG_SECURE_RANDOM,(short)128,(short)8)
    		.setBenched("x+RETURN")
    		.setReference("generatedata_apdubuffer_ref")
    		.setCalibration("generatedata_apdubuffer_0_8"));

    // Generate 8 bytes of random data at offset 172 in the APDU buffer
    addTestCase(new RandomTestCase("generatedata_apdubuffer_172_8",i,x,TestCase.Y,
        RandomData.ALG_SECURE_RANDOM,(short)172,(short)8)
    		.setBenched("x+RETURN")
    		.setReference("generatedata_apdubuffer_ref")
    		.setCalibration("generatedata_apdubuffer_0_8"));
  }
}
