package scripts.jcapi.javacard_framework.ownerpin;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;
import benchs.jcapi.javacard_framework.ownerpin.PINInfo;
import benchs.lib.templates.ByteArrayReader;
import benchs.lib.templates.ByteArrayWriter;
import benchs.lib.templates.Util;

/*
 * Copyright (c) 2006 Mesure Project
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
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/benchs/jcre/overhead/script/ComTestScript.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class is dedicated to measurements of the <tt>OwnerPIN</tt> methods.
 * We can here expect a high variability of the performances, since the 
 * <tt>OwnerPIN</tt> class is highly sensitive, which means that its operations 
 * will be heavily protected in the most secure platforms.
 */
public class OwnerPINScript extends TemplateScript {
	
  /** The default PIN try limit. */
  final static byte PTL = 1;
  
  /** The default reference PIN in BCD format. */
  private final static byte[] PIN_BCD = new byte[] {
      (byte)0x24,(byte)0x12,(byte)0x34,(byte)0xFF,
      (byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF
  };
  
  /** The default reference PIN in HEX format. */
  private final static byte[] PIN_HEX = new byte[] {
      (byte)0x01,(byte)0x02,(byte)0x03,(byte)0x04
  };
  
  /** 
   * A bad presented PIN in HEX format. 
   * The first index byte is not correct.
   */
  private final static byte[] BAD_PIN_HEX_0 = new byte[] {
      (byte)0x00,(byte)0x02,(byte)0x03,(byte)0x04
  };
  
  /** 
   * A bad presented PIN in HEX format. 
   * The last index byte is not correct.
   */
  private final static byte[] BAD_PIN_HEX_3 = new byte[] {
      (byte)0x01,(byte)0x02,(byte)0x03,(byte)0x00
  };
  
  /**
   * Creates a new test script and builds the test cases.
   */
  public OwnerPINScript() {
	int i = 1;
	short x1 = 9;
	short x2 = 44;
	short x4 = 16;
	byte tryLimit = (byte)(Util.loopSize(x1) + 1);
	
	// The empty test cases used to computes overhead
	addTestCase(new PINTestCase("check_ref",i,x1,TestCase.Y,
			PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0).
			setCalibration("check_ko(PTC=0)"));
	
	// The empty test cases used to computes overhead
	addTestCase(new PINTestCase("check_trylimit_ref",i,x1,TestCase.Y,
			PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0).
			setCalibration("check_ko_first(length=4)"));
	
    // The empty test cases used to computes overhead
	addTestCase(new PINTestCase("update_ref",i,x4,TestCase.Y,
			PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0).
			setCalibration("update(length=4)"));

	// Succeeds when verifying a 4-byte PIN whose size is the maximum size
	addTestCase(new PINTestCase("check_ok(length=4)",++i,x1,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("check_ref")
	      .setCalibration("check_ko(PTC=0)"));

	// Succeeds when verifying a 4-byte PIN whose size is lower than the 
	// maximum size
	addTestCase(new PINTestCase("check_ok(length=4,maxPINSize=8)",i,x1,TestCase.Y,
	      PTL,(byte)PIN_BCD.length,PIN_HEX,PIN_HEX,(byte)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("check_ref")
	      .setCalibration("check_ko(PTC=0)"));
	
	// Succeeds when verifying a 8-byte PIN whose size is the maximum size
	addTestCase(new PINTestCase("check_ok(length=8)",i,x1,TestCase.Y,
	      PTL,(byte)PIN_BCD.length,PIN_BCD,PIN_BCD,(byte)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("check_ref")
	      .setCalibration("check_ko(PTC=0)"));
	
	// Fails when verifying a 4-byte PIN 
	// First index byte is not correct
	addTestCase(new PINTestCase("check_ko_first(length=4)",i,x1,TestCase.Y,
	      tryLimit,(byte)PIN_HEX.length,PIN_HEX,BAD_PIN_HEX_0,(byte)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("check_trylimit_ref")
	      .forCalibration());

    // Fails when verifying a 4-byte PIN 
	// Last index byte is not correct
	addTestCase(new PINTestCase("check_ko_last(length=4)",i,x1,TestCase.Y,
		  tryLimit,(byte)PIN_HEX.length,PIN_HEX,BAD_PIN_HEX_3,(byte)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("check_trylimit_ref")
	      .setCalibration("check_ko_first(length=4)"));

	// Fails when verifying a blocked 4-byte PIN 
	addTestCase(new PINTestCase("check_ko(PTC=0)",i,x1,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,BAD_PIN_HEX_0,PTL)
	      .setBenched("x+RETURN-POP")
	      .setReference("check_ref")
	      .forCalibration());
	
    // Updates a 4-byte PIN whose size is the maximum size
	addTestCase(new PINTestCase("update(length=4)",++i,x4,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0)
	      .setBenched("x+RETURN")
	      .setReference("update_ref")
	      .forCalibration());  
	      
	// Updates a 4-byte PIN whose size id not the maximum size
	addTestCase(new PINTestCase("update(length=4,maxPINSize=8)",i,x4,TestCase.Y,
	      PTL,(byte)PIN_BCD.length,PIN_HEX,PIN_HEX,(byte)0)
	      .setBenched("x+RETURN")
	      .setReference("update_ref")
	      .setCalibration("update(length=4)"));
	      
	// Updates a 8-byte PIN whose size is the maximum size
	addTestCase(new PINTestCase("update(length=8)",i,x4,TestCase.Y,
	      PTL,(byte)PIN_BCD.length,PIN_BCD,PIN_BCD,(byte)0)
	      .setBenched("x+RETURN")
	      .setReference("update_ref")
	      .setCalibration("update(length=4)"));
	
	// The empty test cases used to computes overhead
	addTestCase(new PINTestCase("check_apdubuffer_ref",++i,x1,TestCase.Y,
		  PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0).
		  setCalibration("check_ko(PTC=0)"));
	
    // Succeeds when verifying a 4-byte PIN whose size is the maximum size
	addTestCase(new PINTestCase("check_apdubuffer_ok(length=4)",++i,x1,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("check_apdubuffer_ref")
	      .setCalibration("check_ko(PTC=0)"));
	
    // Succeeds when verifying a 8-byte PIN whose size is the maximum size
	addTestCase(new PINTestCase("check_apdubuffer_ok(length=8)",i,x1,TestCase.Y,
	      PTL,(byte)PIN_BCD.length,PIN_BCD,PIN_BCD,(byte)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("check_apdubuffer_ref")
	      .setCalibration("check_ko(PTC=0)"));

    // The empty test cases used to computes overhead
	addTestCase(new PINTestCase("unblock_ref",++i,x1,TestCase.Y,
		  // The tryLimit shall not be set to PTL in the reference test case 
		  // because otherwise the comparison is only performed once
		  tryLimit,(byte)PIN_HEX.length,PIN_HEX,BAD_PIN_HEX_0,(byte)0).
		  forCalibration());
	
	// Unblocks a 4-byte PIN
    addTestCase(new PINTestCase("resetAndUnblock_unblock",++i,x1,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,BAD_PIN_HEX_0,(byte)0)
	      .setBenched("x+RETURN")
	      .setReference("unblock_ref")
	      .setCalibration("unblock_ref"));

	addTestCase(new PINTestCase("no_param_ref",++i,x2,TestCase.Y,
		  PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0).
		  setCalibration("getTriesRemaining"));	
		      
	// Gets the remaining tries
	addTestCase(new PINTestCase("getTriesRemaining",++i,x2,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("no_param_ref")
	      .forCalibration());
	    
	// Gets the validated flag before PIN presentation
	addTestCase(new PINTestCase("isValidated(PVS=0)",++i,x2,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0)
	      .setBenched("x+RETURN-POP")
	      .setReference("no_param_ref")
	      .setCalibration("getTriesRemaining"));
	      
	// Gets the validated flag after PIN presentation
	addTestCase(new PINTestCase("isValidated(PVS=1)",i,x2,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)1)
	      .setBenched("x+RETURN-POP")
	      .setReference("no_param_ref")
	      .setCalibration("getTriesRemaining")); 
	      
	// Resets the PIN before PIN presentation
	addTestCase(new PINTestCase("reset(PVS=0)",++i,x2,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0)
	      .setBenched("x+RETURN")
	      .setReference("no_param_ref")
	      .setCalibration("getTriesRemaining"));
	      
	// Resets the PIN after PIN presentation
	addTestCase(new PINTestCase("reset(PVS=1)",i,x2,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)1)
	      .setBenched("x+RETURN")
	      .setReference("no_param_ref")
	      .setCalibration("getTriesRemaining")); 
	      
	// Unblocks the PIN before PIN presentation
	addTestCase(new PINTestCase("resetAndUnblock(PVS=0)",++i,x2,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)0)
	      .setBenched("x+RETURN")
	      .setReference("no_param_ref")
	      .setCalibration("getTriesRemaining"));
	  
	// Unblocks the PIN after PIN presentation
	addTestCase(new PINTestCase("resetAndUnblock(PVS=1)",i,x2,TestCase.Y,
	      PTL,(byte)PIN_HEX.length,PIN_HEX,PIN_HEX,(byte)1)
	      .setBenched("x+RETURN")
	      .setReference("no_param_ref")
	      .setCalibration("getTriesRemaining"));    
  }
}

/**
 * A PIN-related test case.
 */
class PINTestCase extends TestCase {
  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a CASE 3 command.
   * 
   * @param name the test case name.
   * @param id the on-card test case identifier.
   * @param x the number of executions to be performed on-card for this 
   *          identified test case.
   * @param y the number of executions to be performed off-card for this
   *          identified test case.
   * @param tryLimit the PIN try limit.
   * @param maxSize the maximum PIN size.
   * @param reference the reference PIN.
   * @param presented the presented PIN.
   * @param remainingTries the PIN remaining tries.
   */
  public PINTestCase(String name, int id, int x, int y,  
		  byte tryLimit, byte maxSize, 
		  byte[] reference, byte[] presented, byte remainingTries) {
	  super(name,id,x,y,0);
	  // The maximum number of executions to be performed on-card depends on the
	  // maximum try limit for a PIN.
	  // More precisely, tryLimit <= 127, i.e. xmax <= 11.
	  if (tryLimit != OwnerPINScript.PTL)
	    setXMax(11);
	  PINInfo info = new PINInfo(tryLimit,maxSize,reference,presented,remainingTries);
	  ByteArrayWriter writer = new ByteArrayWriter((short)21);
	  info.write(writer);
	  setData(writer.toByteArray());
  }
  
  /**
   * Sets the number of executions to be performed on-card for this test 
   * case.
   * 
   * @param x the new on-card execution number.
   */
  public void setX(int x) {
    super.setX(x);
    ByteArrayReader reader = new ByteArrayReader(
        lib.util.Util.hexStringToByteArray(getData()),(short)0);
    PINInfo info = new PINInfo();
    info.read(reader);
    if (info.getTryLimit() != OwnerPINScript.PTL)
      info.setTryLimit((byte)(Util.loopSize((short)x) + 1));
    ByteArrayWriter writer = new ByteArrayWriter((short)21);
	info.write(writer);
	setData(writer.toByteArray());
  }
}
