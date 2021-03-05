package scripts.jcapi.javacard_framework.aid;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;
import benchs.jcapi.javacard_framework.aid.AIDInfo;
import benchs.lib.templates.ByteArrayWriter;

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
 * Created: 11 mai 2007
 * Author: TL 
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: cpascal $
 */

/**
 * This class is dedicated to measurements of the <tt>AID</tt> methods.
 */
public class AIDScript extends TemplateScript {
  
  /** The default AID. */
  private final static byte[] AID = new byte[] {
	  (byte)0x4D,(byte)0x45,(byte)0x53,(byte)0x55,
	  (byte)0x52,(byte)0x45,(byte)0x5F,(byte)0x61,
	  (byte)0x69,(byte)0x64,(byte)0x01
  };

  /**
   * Creates a new test script and builds the test cases.
   */
  public AIDScript() {
	int i = 1;
	short x = 26;
	
	// The empty test case used to computes overhead for 'partialEquals'
	addTestCase(new AIDTestCase("partialequals_ref",i,x,TestCase.Y,
			AID,(short)5,(byte)8).setCalibration("partialequals_epo_apdubuffer"));
	
	// Compares the AID 
	// with 'partialEquals'
	// on Java Card RE-owned instances
	// when the APDU buffer is passed as parameter
	addTestCase(new AIDTestCase("partialequals_epo_apdubuffer",++i,x,TestCase.Y,
			AID,(short)5,(byte)8)
			.setBenched("x+RETURN-POP")
			.setReference("partialequals_ref")
			.forCalibration());

    // Compares the AID 
	// with 'partialEquals'
	// on proprietary instances
	// when the APDU buffer is passed as parameter
	addTestCase(new AIDTestCase("partialequals_prop_apdubuffer",++i,x,TestCase.Y,
			AID,(short)5,(byte)8)
			.setBenched("x+RETURN-POP")
			.setReference("partialequals_ref")
			.setCalibration("partialequals_epo_apdubuffer"));
	
	x = 34;
	
	// The empty test case used to computes overhead for 'getbytes'
	addTestCase(new AIDTestCase("getbytes_ref",++i,x,TestCase.Y,
			AID,(short)4,(byte)0).setCalibration("getbytes_epo_apdubuffer"));
	
	// Gets the AID 
	// with 'getBytes'
	// on Java Card RE-owned instances
	// when the APDU buffer is passed as parameter
	addTestCase(new AIDTestCase("getbytes_epo_apdubuffer",++i,x,TestCase.Y,
			AID,(short)4,(byte)0)
			.setBenched("x+RETURN-POP")
			.setReference("getbytes_ref")
			.forCalibration());

    // Gets the AID 
	// with 'getBytes'
	// on proprietary instances
	// when the APDU buffer is passed as parameter
	addTestCase(new AIDTestCase("getbytes_prop_apdubuffer",++i,x,TestCase.Y,
			AID,(short)4,(byte)0)
			.setBenched("x+RETURN-POP")
			.setReference("getbytes_ref")
			.setCalibration("getbytes_epo_apdubuffer"));
  }
}

/**
 * An AID-related test case.
 */
class AIDTestCase extends TestCase {
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
   * @param aid the AID.
   * @param offset an offset.
   * @param length a length.
   */
  public AIDTestCase(String name, int id, int x, int y,  
		  byte[] aid, short offset, byte length) {
	  super(name,id,x,y,0);
	  AIDInfo info = new AIDInfo(aid,offset,length);
	  ByteArrayWriter writer = new ByteArrayWriter(info.getSize());
	  info.write(writer);
	  setData(writer.toByteArray());
  }
}
