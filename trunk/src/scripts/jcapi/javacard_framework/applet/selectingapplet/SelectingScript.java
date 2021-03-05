package scripts.jcapi.javacard_framework.applet.selectingapplet;

import lib.cad.SelectAPDU;
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
 * This class is dedicated to measurements of the <tt>Applet.selectingApplet</tt> 
 * method.
 */
public class SelectingScript extends TemplateScript{
	
	/** The applet AID. */
	final static byte[] AID = {
	      (byte)0x4D,(byte)0x45,(byte)0x53,(byte)0x55,
		  (byte)0x52,(byte)0x45,(byte)0x5F,(byte)0x73,
		  (byte)0x65,(byte)0x6C,(byte)0x65,(byte)0x63,
		  (byte)0x74,(byte)0x69,(byte)0x6E,(byte)0x01
	  };
	
	/** The default number of executions to be performed on-card for a test case. */
	private final static int X = 46;

	public SelectingScript() {
	  int i = 1;
		
      // Dummy case used to compute the overhead for MESURE commands
	  addTestCase(new TestCase("selectingapplet_mesure_ref",i,X,TestCase.Y,AID,
			  0).setCalibration("selectingapplet_mesure"));
	  
      // A MESURE command invoking 'selectingApplet'
	  addTestCase(new TestCase("selectingapplet_mesure",++i,X,TestCase.Y,AID,0)
			  .setBenched("x+SCONST_0+SRETURN")
			  .setReference("selectingapplet_mesure_ref")
			  .forCalibration());	
	  
	  // Dummy case used to compute the overhead for SELECT commands
	  addTestCase(new SelectingTestCase("selectingapplet_select_ref",--i,X,
			  TestCase.Y).setCalibration("selectingapplet_mesure"));

	  // A SELECT command invoking 'selectingApplet'
	  addTestCase(new SelectingTestCase("selectingapplet_select",++i,X,TestCase.Y)
			  .setBenched("x+SCONST_0+SRETURN")
			  .setReference("selectingapplet_select_ref")
			  .setCalibration("selectingapplet_mesure"));
	}
}

class SelectingTestCase extends TestCase {
  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a SELECT command.
   * 
   * @param name the test case name.
   * @param id the on-card test case identifier.
   * @param x the number of executions to be performed on-card for this 
   *          identified test case.
   * @param y the number of executions to be performed off-card for this
   *          identified test case.
   */
  public SelectingTestCase(String name, int id, int x, int y) {
    super(name,id,x,y);
    setX(SelectAPDU.P2);
	setData(SelectingScript.AID);
	capdu.setCLA(SelectAPDU.CLA);
	capdu.setINS(SelectAPDU.INS);
	capdu.setP1(SelectAPDU.P1);
	capdu.setLe(0);
	sendAsIs();
  }
  
  /**
   * Sets the number of executions to be performed on-card for this test 
   * case.
   * 
   * @param x the new on-card execution number.
   */
  public void setX(int x) {
    super.setX(SelectAPDU.P2);
  }
}