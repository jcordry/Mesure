package benchs.jcapi.javacard_security.randomdata.generatedata;

import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import benchs.lib.templates.CryptoTemplateApplet;
import benchs.lib.templates.TestCase;

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

/*
 * $HeadURL: svn+ssh://corentinboe@gforge.inria.fr/svn/mesureprv/src/benchs/jcapi/javacard_framework/util/applet/ArrayCopyTestApplet.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 1 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cboe $
 */

public class GenerateDataTestApplet extends CryptoTemplateApplet {
  /** The buffer where to store the random data. */
  private byte[] data;
	
  private GenerateDataTestApplet() {
    data = JCSystem.makeTransientByteArray((short)200,JCSystem.CLEAR_ON_DESELECT);
  }
			
  /** 
   * @see javacard.framework.Applet#install(byte[], short, byte) 
   */
  public static void install(byte[] bArray, short bOffset, byte bLength) {
    new GenerateDataTestApplet().register();
  } 

  /**
   * @see TestCase#getTestCases()
   */
  public TestCase[] getTestCases() {
    return new TestCase[] {
      // Dummy case used to compute the overhead for generateData method
      new TestCase() {  
        public void run(byte[] apduBuffer) {
		  dummy.dummyMethodVoid(data,cryptoInfo.getOutOffset(),cryptoInfo.getOutLength());
		}
	  },		
	  
	  // Generate random data
      new TestCase() {
        public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  if ((short)(cryptoInfo.getOutOffset() + cryptoInfo.getOutLength()) > (short)data.length)
	        ISOException.throwIt(ISO7816.SW_DATA_INVALID);
        }		
		public void run(byte[] apduBuffer) {
	      random.generateData(data,cryptoInfo.getOutOffset(),cryptoInfo.getOutLength());
        }
	  },
	  
	  // Dummy case used to compute the overhead for generateData method
	  // when the APDU buffer is passed as parameter
	  new TestCase() {
	    public void run(byte[] apduBuffer) {
	      dummy.dummyMethodVoid(apduBuffer,cryptoInfo.getOutOffset(),cryptoInfo.getOutLength());
		}
	  },		
		  
	  // Generate random data in the APDU buffer
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
	      init(apduBuffer);
	      if ((short)(cryptoInfo.getOutOffset() + cryptoInfo.getOutLength()) > (short)apduBuffer.length)
	        ISOException.throwIt(ISO7816.SW_DATA_INVALID);
	    }		
	    public void run(byte[] apduBuffer) {
		  random.generateData(apduBuffer,cryptoInfo.getOutOffset(),cryptoInfo.getOutLength());
	    }
	  }
    };
  }
}
