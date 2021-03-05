package scripts.jcapi.javacard_security.messagedigest.dofinal;

import javacard.security.MessageDigest;
import scripts.jcapi.javacard_security.messagedigest.DigestTestCase;
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
 * <tt>MessageDigest.doFinal</tt> method.
 */
public class DoFinalScript extends TemplateScript{
  private final static short X = (short)12;
  private final static short BLOCKSIZE = (short)64;
	
  /**
   * Builds the test script used to measure performances of the 
   * <tt>MessageDigest.doFinal</tt> method.
   */
   public DoFinalScript() {
     int i = 1;
	
     // The empty test cases used to compute overhead
	 addTestCase(new DigestTestCase("dofinal_ref",i,X,TestCase.Y,
	  MessageDigest.ALG_SHA,
	  (short)0,BLOCKSIZE,(short)0).setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));

	 // Hash blockSize bytes of data without overlap
	 addTestCase(new DigestTestCase("dofinal_inOff=0_inLen=block_outOff=0",++i,X,TestCase.Y,
	   MessageDigest.ALG_SHA,(short)0,BLOCKSIZE,(short)0)
	   	.setBenched("x+RETURN-POP")
	   	.setReference("dofinal_ref")
	   	.forCalibration());
	 
	 addTestCase(new DigestTestCase("dofinal_inOff=64_inLen=block_outOff=0",i,X,TestCase.Y,
			   MessageDigest.ALG_SHA,(short)64,BLOCKSIZE,(short)0)
			   	.setBenched("x+RETURN-POP")
			   	.setReference("dofinal_ref")
			   	.setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));
	 
	 addTestCase(new DigestTestCase("dofinal_inOff=0_inLen=block_outOff=64",i,X,TestCase.Y,
			   MessageDigest.ALG_SHA,(short)0,BLOCKSIZE,(short)64)
			   	.setBenched("x+RETURN-POP")
			   	.setReference("dofinal_ref")
			   	.setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));
	 
     //	Hash 2*blockSize bytes of data without overlap
	 addTestCase(new DigestTestCase("dofinal_inOff=0_inLen=2*block_outOff=0",i,X,TestCase.Y,
	   MessageDigest.ALG_SHA,(short)0,(short)(BLOCKSIZE*2),(short)0)
	   	.setBenched("x+RETURN-POP")
	   	.setReference("dofinal_ref")
	   	.setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));

     // Hash 3*blockSize bytes of data without overlap
	 addTestCase(new DigestTestCase("dofinal_inOff=0_inLen=3*block_outOff=0",i,X,TestCase.Y,
	   MessageDigest.ALG_SHA,(short)0,(short)(BLOCKSIZE*3),(short)0)
	   	.setBenched("x+RETURN-POP")
	   	.setReference("dofinal_ref")
	   	.setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));

     //	The empty test cases used to compute overhead
	 // for total overlap
	 addTestCase(new DigestTestCase("dofinal_overlap_ref",++i,X,TestCase.Y,
	  MessageDigest.ALG_SHA,(short)0,BLOCKSIZE,
	  (short)0).setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));
   
     // Hash blockSize bytes of data with total overlap
     addTestCase(new DigestTestCase("dofinal_overlap",++i,X,TestCase.Y,
	   MessageDigest.ALG_SHA,(short)0,BLOCKSIZE,(short)0)
	   	.setBenched("x+RETURN-POP")
	   	.setReference("dofinal_overlap_ref")
	   	.setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));
     
    // The empty test case used to compute overhead
 	// when the APDU buffer is used as input buffer
 	addTestCase(new DigestTestCase("dofinal_inBuff=apduBuffer_ref",++i,X,TestCase.Y,
 	  MessageDigest.ALG_SHA,(short)0,BLOCKSIZE,
 	  (short)0).setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));
 	
     // Hash blockSize bytes of data
     // when the APDU buffer is used as input buffer
 	addTestCase(new DigestTestCase("dofinal_inBuff=apduBuffer",++i,X,TestCase.Y,
 	  MessageDigest.ALG_SHA,(short)0,BLOCKSIZE,(short)0)
 	  	.setBenched("x+RETURN-POP")
 	  	.setReference("dofinal_inBuff=apduBuffer_ref")
 	  	.setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));
 	
    // The empty test case used to compute overhead
 	// when the APDU buffer is used as output buffer
 	addTestCase(new DigestTestCase("dofinal_outBuff=apduBuffer_ref",++i,X,TestCase.Y,
 	  MessageDigest.ALG_SHA,(short)0,BLOCKSIZE,
 	  (short)0).setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));
 	
     // Hash blockSize bytes of data
     // when the APDU buffer is used as output buffer
 	addTestCase(new DigestTestCase("dofinal_outBuff=apduBuffer",++i,X,TestCase.Y,
 	  MessageDigest.ALG_SHA,(short)0,BLOCKSIZE,(short)0)
 	  	.setBenched("x+RETURN-POP")
 	  	.setReference("dofinal_outBuff=apduBuffer_ref")
 	  	.setCalibration("dofinal_inOff=0_inLen=block_outOff=0"));
  }
}
