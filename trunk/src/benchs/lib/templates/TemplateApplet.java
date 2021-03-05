package benchs.lib.templates;

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
 * $HeadURL: svn+ssh://corentinboe@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 46 $
 * $LastChangedDate: 2006-10-20 14:58:46 +0200 (ven., 20 oct. 2006) $
 * $LastChangedBy: meunier $
 */

import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;

/**
 * This class should be extended by all test applets.
 */
public abstract class TemplateApplet extends Applet implements Constants {

    /**
     * The constant used to mask logical channel information contained in the
     * CLA byte of commands.
     */
    protected static final byte CHANNEL_FILTER = (byte) 0xFC;
    
    /** 
     * The constant used to mask phase information (set-up, run, or 
     * clean-up) contained in the INS byte of commands.
     */
    protected static final byte PHASE_FILTER = (byte) 0xF0;
    
    /**
     * The constant used to mask case information contained in the INS byte of
     * commands.
     */
    protected static final byte CASE_FILTER = (byte) 0x0F;
    
    /** The test cases. */
    protected TestCase[] testCases;
    
    /** The exception information. */
    protected ExceptionInfo info;
    
    /** The writer used to write the exception information. */
    protected ByteArrayWriter writer;
    
    /**
     * Creates a new test applet and builds its test cases.
     */
    protected TemplateApplet() {
      info = new ExceptionInfo();
      writer = new ByteArrayWriter(ExceptionInfo.getSize());
      TestCase[] tmp = getTestCases();
      testCases = new TestCase[(short)(tmp.length + 1)];
      testCases[0] = new EmptyTestCase();
      for (short s = 0; s < tmp.length; s++)
        testCases[(short)(s+1)] = tmp[s];
    }

    /**
     * @see javacard.framework.APDU#process(APDU)
     */
    public void process(APDU apdu) { 
      if (selectingApplet()) 
        return;

      byte[] apduBuffer = apdu.getBuffer();
      if ((apduBuffer[ISO7816.OFFSET_CLA] & CHANNEL_FILTER) != MESURE_CLA)
        ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
      byte ins = apduBuffer[ISO7816.OFFSET_INS];
      if (((ins & PHASE_FILTER) != SETUP_INS) && 
    	  ((ins & PHASE_FILTER) != RUN_INS) &&
    	  ((ins & PHASE_FILTER) != CLEANUP_INS))
    	ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
      short p1 = (short)(apduBuffer[ISO7816.OFFSET_P1] & 0xFF);
      if (p1 >= testCases.length)
        ISOException.throwIt(ISO7816.SW_WRONG_P1P2);
      short p2 = (short)(apduBuffer[ISO7816.OFFSET_P2] & 0xFF);
      p2 = Util.loopSize(p2);
      
      try {
        switch ((byte)(ins & CASE_FILTER)) {
          case CASE_3:
    	    apdu.setIncomingAndReceive(); 
          case CASE_1:
            process(apduBuffer,ins,p1,p2);
            break;
          case CASE_4:
            apdu.setIncomingAndReceive();
            process(apduBuffer,ins,p1,p2);
            short offset_le = (short)(ISO7816.OFFSET_LC + 1);
            offset_le = (short)(offset_le + (short)(apduBuffer[ISO7816.OFFSET_LC] & 0xFF));
            apdu.setOutgoingAndSend((short)0,apduBuffer[offset_le]);
            break;
          case CASE_2:
            process(apduBuffer,ins,p1,p2);
    	    apdu.setOutgoingAndSend((short)0,(short)(apduBuffer[ISO7816.OFFSET_LC] & 0xFF));
    	    break;
          default:
            ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
        }
      } catch (ISOException e) {
        throw e;
      } catch (Throwable t) {
        switch ((byte)(ins & CASE_FILTER)) {
          case CASE_2:
          case CASE_4:
    	    info.setException(t);
            writer.clear();
            info.write(writer);
            writer.toByteArray(apduBuffer,ISO7816.OFFSET_CDATA);
            apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA,ExceptionInfo.getSize());
            break;
          default:
            break;
        }
        ISOException.throwIt(ISO7816.SW_UNKNOWN);
      }
    }
    
    /**
     * Processes the specified test case.
     * 
     * @param apdu the <tt>APDU</tt> object.
     * @param ins the execution phase (set-up, run or clean-up).
     * @param p1 the identifier of the test case to execute.
     * @param p2 the number of executions to perform for the identified test 
     *           case.
     */
    private void process(byte[] apduBuffer, short ins, short p1, short p2) {
      TestCase c = testCases[p1];
      switch ((short)(ins & PHASE_FILTER)) {
        case SETUP_INS:
        	if (c.useInnerCounter)
        		c.setUp(apduBuffer, p2);
        	else
        		c.setUp(apduBuffer);
          break;
        case RUN_INS:
        	if (c.useInnerCounter)
                c.run(apduBuffer);
        	else
              for (short s = 0; s < p2; s++) {
                c.run(apduBuffer);
              }
          break;
        case CLEANUP_INS:
          c.cleanUp(apduBuffer);
          break;
        default:
          ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
      }
    }
    
    /**
     * Returns the test cases that will be run by this test applet.
     * 
     * @return an array containing the <tt>TestCase</tt> objects.
     */
    public TestCase[] getTestCases() {
    	return new TestCase[0]; 
    }
}
