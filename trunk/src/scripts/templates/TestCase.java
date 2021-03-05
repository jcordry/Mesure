package scripts.templates;

//import java.io.ByteArrayOutputStream;
//import java.util.Vector;

import javacard.framework.APDUException;
import javacard.framework.SystemException;
import javacard.framework.TransactionException;
import javacard.security.CryptoException;
import lib.cad.BadResponseStatusException;
import lib.cad.CAD;
import lib.cad.CADException;
import lib.cad.CommandAPDU;
import lib.cad.ResponseAPDU;
import lib.cad.TimeCAD;
import lib.chrono.TimeMeasure;
import lib.log.Log;
import tools.calibrate.CalibrateUtil;
import benchs.lib.templates.Constants;
import benchs.lib.templates.ExceptionInfo;
import benchs.lib.templates.Util;

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
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 6 octobre 2006
 * Author: TL 
 * $LastChangedRevision: 35 $
 * $LastChangedDate: 2006-09-29 17:10:44 +0200 (ven., 29 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * Each test case should extend this class.
 */
public class TestCase extends lib.xml.test_result.TestCase {
	
  /**
  * Log specific to this class.
  */
  public static final int LOG = lib.log.Log.getASpecificLogEntry("TESTCASE");

  /** The default number of executions to be performed on-card for a test case. */
  public final static int X = 100;
		  
  /** The default number of executions to be performed off-card for a test case. */
  public final static int Y = 5;

  /** The underlying APDU command. */
  protected CommandAPDU capdu;
  
  /** The set-up response. */
  protected ResponseAPDU setupResponse;
  
  /** The clean-up response. */
  protected ResponseAPDU cleanupResponse;
  
  /** The command case for this test case. */
  protected int c;
  
  /** Determines if the underlying APDU command shall be sent "as is" or not'. */
  boolean sendAsIs;
  
  /**
   * The maximum number of executions to be performed on-card for a test case. 
   * This value is useful for calibration only.
   */
  private int xmax = CalibrateUtil.X_MAX;
  
  /**
   * The minimum number of executions to be performed on-card for a test case. 
   * This value is useful for calibration only.
   */
  private int xmin = 2;

  /**
   * Sets the incoming data.
   * 
   * @param data the incoming data to be passed to the card.
   * @return this <tt>TestCase</tt> object.
   */
  protected TestCase setData(byte[] data) {
    capdu.setData(data);
    switch(capdu.getCase()) {
      case 3:
        capdu.setINS(Constants.CASE_3);
        break;
      case 4:
    	capdu.setINS(Constants.CASE_4);
    	break;
      case 1:
      case 2:
      default:
    	// should not happen
        break;
    }
    setData(lib.util.Util.byteArrayToHexString(data,":"));
    return this;
  }  
  
  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a CASE 4 command.
   * 
   * @param name the test case name.
   * @param id the on-card test case identifier.
   * @param x the number of executions to be performed on-card for this 
   *          identified test case.
   * @param y the number of executions to be performed off-card for this
   *          identified test case.
   * @param data the incoming data to be passed to the card.
   * @param le the expected length of the outgoing data returned by the card.
   * @throws <tt>NullPointerException</tt> if <tt>data</tt> is null.
   * @throws <tt>IllegalArgumentException</tt> if <tt>le</tt> is negative or
   *         greater than 65536.
   * @throws <tt>IllegalArgumentException</tt> if <tt>data.length</tt> is 
   *         greater than 65535.
   */
  public TestCase(String name, int id, int x, int y, byte[] data, int le) {
	setReference(null);
	setY(y);
	super.setX(x);
	setData(lib.util.Util.byteArrayToHexString(data,":"));
	capdu = new CommandAPDU(Constants.MESURE_CLA,0,id,x,data,le); 
	capdu.setINS(Constants.CASE_4);
	capdu.setName(name);
	c = Constants.CASE_1;
	setTestResult(new TestResult(TestResult.NOT_EXECUTED,null,new TimeMeasure[0]));
	setBenchedUnit("x");
	sendAsIs = false;
  }

  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a CASE 4 command.
   * The <tt>X</tt> and <tt>Y</tt> default values are used as number of 
   * executions to be respectively performed on-card and off-card.
   * 
   * @param name the test case name.
   * @param id the on-card test case identifier.
   * @param data the incoming data to be passed to the card.
   * @param le the expected length of the outgoing data returned by the card.
   * @throws <tt>NullPointerException</tt> if <tt>data</tt> is null.
   * @throws <tt>IllegalArgumentException</tt> if <tt>le</tt> is negative or
   *         greater than 65536.
   * @throws <tt>IllegalArgumentException</tt> if <tt>data.length</tt> is 
   *         greater than 65535.
   */
  public TestCase(String name, int id, byte[] data, int le) {
    this(name,id,X,Y,data,le);
  }
  
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
   * @param data the incoming data to be passed to the card.
   * @throws <tt>NullPointerException</tt> if <tt>data</tt> is null.
   * @throws <tt>IllegalArgumentException</tt> if <tt>data.length</tt> is 
   *         greater than 65535.
   */
  public TestCase(String name, int id, int x, int y, byte[] data) {
	setReference(null);
	setY(y);
	super.setX(x);
	setData(lib.util.Util.byteArrayToHexString(data,":"));
    capdu = new CommandAPDU(Constants.MESURE_CLA,0,id,x,data);
    capdu.setINS(Constants.CASE_3);
    capdu.setName(name);
    c = Constants.CASE_1;
    setTestResult(new TestResult(TestResult.NOT_EXECUTED,null,new TimeMeasure[0]));
    setBenchedUnit("x");
    sendAsIs = false;
  }

  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a CASE 3 command.
   * The <tt>X</tt> and <tt>Y</tt> default values are used as number of 
   * executions to be respectively performed on-card and off-card.
   * 
   * @param name the test case name.
   * @param id the on-card test case identifier.
   * @param data the incoming data to be passed to the card.
   * @throws <tt>NullPointerException</tt> if <tt>data</tt> is null.
   * @throws <tt>IllegalArgumentException</tt> if <tt>data.length</tt> is 
   *         greater than 65535.
   */
  public TestCase(String name, int id, byte[] data) {
    this(name,id,X,Y,data);
  }
  
  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a CASE 2 command.
   * 
   * @param name the test case name.
   * @param id the on-card test case identifier.
   * @param x the number of executions to be performed on-card for this 
   *          identified test case.
   * @param y the number of executions to be performed off-card for this
   *          identified test case.
   * @param le the expected length of the outgoing data returned by the card.
   * @throws <tt>IllegalArgumentException</tt> if <tt>le</tt> is negative or
   *         greater than 65536.
   */
  public TestCase(String name, int id, int x, int y, int le) {
	setReference(null);
	setY(y);
	super.setX(x);
	capdu = new CommandAPDU(Constants.MESURE_CLA,0,id,x,le);
	capdu.setINS(Constants.CASE_2);
	capdu.setName(name);
	c = Constants.CASE_1;
	setTestResult(new TestResult(TestResult.NOT_EXECUTED,null,new TimeMeasure[0]));
	setBenchedUnit("x");
	sendAsIs = false;
  }

  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a CASE 2 command.
   * The <tt>X</tt> and <tt>Y</tt> default values are used as number of 
   * executions to be respectively performed on-card and off-card.
   * 
   * @param name the test case name.
   * @param id the on-card test case identifier.
   * @param le the expected length of the outgoing data returned by the card.
   * @throws <tt>IllegalArgumentException</tt> if <tt>le</tt> is negative or
   *         greater than 65536.
   */
  public TestCase(String name, int id, int le) {
    this(name,id,X,Y,le);
  }
  
  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a CASE 1 command.
   * 
   * @param name the test case name.
   * @param id the on-card test case identifier.
   * @param x the number of executions to be performed on-card for this 
   *          identified test case.
   * @param y the number of executions to be performed off-card for this
   *          identified test case.
   */
  public TestCase(String name, int id, int x, int y) {
	setReference(null);
	setY(y);
	super.setX(x);
	capdu = new CommandAPDU(Constants.MESURE_CLA,0,id,x);
	capdu.setINS(Constants.CASE_1);
	capdu.setName(name);
	c = Constants.CASE_1;
	setTestResult(new TestResult(TestResult.NOT_EXECUTED,null,new TimeMeasure[0]));
	setBenchedUnit("x");
	sendAsIs = false;
  }

  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a CASE 1 command.
   * 
   * @param name the test case name.
   * @param reference name of the reference test case.
   * @param id the on-card test case identifier.
   * @param x the number of executions to be performed on-card for this 
   *          identified test case.
   * @param y the number of executions to be performed off-card for this
   *          identified test case.
   */
  public TestCase(String name, String benchedUnit, int id, int x, int y) {
    this(name,id,x,y, 0);
    
    // set equation defining benched units.
	setBenchedUnit(benchedUnit);
  }

  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a CASE 1 command.
   * The <tt>X</tt> and <tt>Y</tt> default values are used as number of 
   * executions to be respectively performed on-card and off-card.
   * 
   * @param name the test case name.
   * @param id the on-card test case identifier.
   */
  public TestCase(String name, int id) {
	this(name,id,X,Y);
  }
  
  private String parseAPDUException(short error) {
	  String ret = " with ";
	  switch (error){
	  case APDUException.BAD_LENGTH:
		  ret+="BAD_LENGTH";
		  break;
	  case APDUException.BUFFER_BOUNDS: 
		  ret+="BAD_LENGTH";
		  break;
	  case APDUException.ILLEGAL_USE:
		  ret+="BUFFER_BOUNDS";
		  break;
	  case APDUException.IO_ERROR:
		  ret+="IO_ERROR";
		  break;
	  case APDUException.NO_T0_GETRESPONSE:
		  ret+="NO_T0_GETRESPONSE";
		  break;
	  case APDUException.T1_IFD_ABORT:
		  ret+="T1_IFD_ABORT";
		  break;
	  default:
		  ret+="0x"+error;
		  break;
	  }
	  return ret+" reason";
  }
  
  private String parseSystemException(short error) {
	  String ret = " with ";
	  switch (error){
	  case SystemException.ILLEGAL_AID:
		  ret+="ILLEGAL_AID";
		  break;
	  case SystemException.ILLEGAL_TRANSIENT: 
		  ret+="ILLEGAL_TRANSIENT";
		  break;
	  case SystemException.ILLEGAL_VALUE:
		  ret+="ILLEGAL_VALUE";
		  break;
	  case SystemException.NO_RESOURCE:
		  ret+="NO_RESOURCE";
		  break;
	  case SystemException.NO_TRANSIENT_SPACE:
		  ret+="NO_TRANSIENT_SPACE";
		  break;
	  default:
		  ret+="0x"+error;
		  break;
	  }
	  return ret+" reason";
  }
  
  private String parseTransactionException(short error) {
	  String ret = " with ";
	  switch (error){
	  case TransactionException.BUFFER_FULL:
		  ret+="BUFFER_FULL";
		  break;
	  case TransactionException.IN_PROGRESS: 
		  ret+="IN_PROGRESS";
		  break;
	  case TransactionException.INTERNAL_FAILURE:
		  ret+="INTERNAL_FAILURE";
		  break;
	  case TransactionException.NOT_IN_PROGRESS:
		  ret+="NOT_IN_PROGRESS";
		  break;
	  default:
		  ret+="0x"+error;
		  break;
	  }
	  return ret+" reason";
  }
  
  private String parseCryptoException(short error) {
	  String ret = " with ";
	  switch (error){
	  case CryptoException.ILLEGAL_USE:
		  ret+="ILLEGAL_USE";
		  break;
	  case CryptoException.ILLEGAL_VALUE: 
		  ret+="ILLEGAL_VALUE";
		  break;
	  case CryptoException.INVALID_INIT:
		  ret+="INVALID_INIT";
		  break;
	  case CryptoException.NO_SUCH_ALGORITHM:
		  ret+="NO_SUCH_ALGORITHM";
		  break;
	  case CryptoException.UNINITIALIZED_KEY:
		  ret+="UNINITIALIZED_KEY";
		  break;
	  default:
		  ret+="0x"+error;
		  break;
	  }
	  return ret+" reason";
  }
  
  private String parseSetupResponse(BadResponseStatusException e) {
	  byte[] response = e.getResponse().getData();
	  if (response.length!=3)
		  return "Unknown error";
	  String ret = "";
	  switch (response[0]){
	  case ExceptionInfo.JAVA_IO_IOEXCEPTION:
		  ret+="JAVA_IO_IOEXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_ARITHMETICEXCEPTION:
		  ret+="JAVA_LANG_ARITHMETICEXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_ARRAYINDEXOUTOFBOUNDSEXCEPTION:
		  ret+="JAVA_LANG_ARRAYINDEXOUTOFBOUNDSEXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_ARRAYSTOREEXCEPTION:
		  ret+="JAVA_LANG_ARRAYSTOREEXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_CLASSCASTEXCEPTION:
		  ret+="JAVA_LANG_CLASSCASTEXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_EXCEPTION:
		  ret+="JAVA_LANG_EXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_INDEXOUTOFBOUNDSEXCEPTION:
		  ret+="JAVA_LANG_INDEXOUTOFBOUNDSEXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_NEGATIVEARRAYSIZEEXCEPTION:
		  ret+="JAVA_LANG_NEGATIVEARRAYSIZEEXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_NULLPOINTEREXCEPTION:
		  ret+="JAVA_LANG_NULLPOINTEREXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_RUNTIMEEXCEPTION:
		  ret+="JAVA_LANG_RUNTIMEEXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_SECURITYEXCEPTION:
		  ret+="JAVA_LANG_SECURITYEXCEPTION";
		  break;
	  case ExceptionInfo.JAVA_LANG_THROWABLE:
		  ret+="JAVA_LANG_THROWABLE";
		  break;
	  case ExceptionInfo.JAVA_RMI_REMOTEEXCEPTION:
		  ret+="JAVA_RMI_REMOTEEXCEPTION";
		  break;
	  case ExceptionInfo.JAVACARD_FRAMEWORK_APDUEXCEPTION:
		  ret+="JAVACARD_FRAMEWORK_APDUEXCEPTION";
		  ret+=parseAPDUException(javacard.framework.Util.makeShort(response[1],response[2]));
		  break;
	  case ExceptionInfo.JAVACARD_FRAMEWORK_CARDEXCEPTION:
		  ret+="JAVACARD_FRAMEWORK_CARDEXCEPTION";
		  break;
	  case ExceptionInfo.JAVACARD_FRAMEWORK_CARDRUNTIMEEXCEPTION:
		  ret+="JAVACARD_FRAMEWORK_CARDRUNTIMEEXCEPTION";
		  break;
	  case ExceptionInfo.JAVACARD_FRAMEWORK_ISOEXCEPTION:
		  ret+="JAVACARD_FRAMEWORK_ISOEXCEPTION";
		  break;
	  case ExceptionInfo.JAVACARD_FRAMEWORK_PINEXCEPTION:
		  ret+="JAVACARD_FRAMEWORK_PINEXCEPTION";
		  break;
	  case ExceptionInfo.JAVACARD_FRAMEWORK_SERVICE_SERVICEEXCEPTION:
		  ret+="JAVACARD_FRAMEWORK_SERVICE_SERVICEEXCEPTION";
		  break;
	  case ExceptionInfo.JAVACARD_FRAMEWORK_SYSTEMEXCEPTION:
		  ret+="JAVACARD_FRAMEWORK_SYSTEMEXCEPTION";
		  ret+=parseSystemException(javacard.framework.Util.makeShort(response[1],response[2]));
		  break;
	  case ExceptionInfo.JAVACARD_FRAMEWORK_TRANSACTIONEXCEPTION:
		  ret+="JAVACARD_FRAMEWORK_TRANSACTIONEXCEPTION";
		  ret+=parseTransactionException(javacard.framework.Util.makeShort(response[1],response[2]));
		  break;
	  case ExceptionInfo.JAVACARD_FRAMEWORK_USEREXCEPTION:
		  ret+="JAVACARD_FRAMEWORK_USEREXCEPTION";
		  break;
	  case ExceptionInfo.JAVACARD_SECURITY_CRYPTOEXCEPTION:
		  ret+="JAVACARD_SECURITY_CRYPTOEXCEPTION";
		  ret+=parseCryptoException(javacard.framework.Util.makeShort(response[1],response[2]));
		  break;
	  default:
		  return "Unknown error";
	  }
	  return ret;
  }
  
  /**
   * Runs the test case several times (according to the specified number of
   * executions performed off-card for this test case) and returns the 
   * average execution time.
   * 
   * @param cad the CAD to use to run the test case.
   */
  public void run(TimeCAD cad) throws NoResourceException {
		CAD basecad = cad.getCAD(); // in order to not bench setup and cleanup
		setupResponse = null;
		cleanupResponse = null;
		CommandAPDU capdu = new CommandAPDU(this.capdu.getBytes());
		TimeMeasure[] measures = new TimeMeasure[getY()];
		java.util.Arrays.fill(measures, null);
		if (getTestResult().getStatus() != TestResult.NO_RESOURCES)
		  getTestResult().setStatus(TestResult.NOT_EXECUTED);
		Log.log[LOG].println(2, "Running " + getY() + " iterations of " + this.capdu.getName() + " ");
		short m = Util.loopSize((short)getX());
		
		for (int i = 0; i < getY(); i++) {
			try {
				if (!sendAsIs) {
				  // Set-up command
				  // Don't measure execution time
				  capdu.setINS(Constants.SETUP_INS | this.capdu.getINS());
				  capdu.setName("SET-UP " + i + " [" + this.capdu.getName() + "]");
				  setupResponse = basecad.sendAPDUAndVerify(capdu);
				}
			} catch (BadResponseStatusException e) {
				Log.log[LOG].println(2, "");
				Log.log[LOG].println(2, capdu.getName() + " FAILED: " + e.getMessage());
				String exception = parseSetupResponse(e);
				if (exception.equals("JAVACARD_FRAMEWORK_SYSTEMEXCEPTION with NO_RESOURCE reason") ||
					exception.equals("JAVACARD_FRAMEWORK_SYSTEMEXCEPTION with NO_TRANSIENT_SPACE reason")) {
				  if (getTestResult().getStatus() != TestResult.NO_RESOURCES) {
				    getTestResult().setMessage(e.getMessage() + " (" + exception + ")");
				    getTestResult().setTimeMeasure(measures);
				    getTestResult().setStatus(TestResult.NO_RESOURCES);
				    throw new NoResourceException(this);
				  }
				}
				getTestResult().setMessage(e.getMessage() + " (" + exception + ")");
				getTestResult().setTimeMeasure(measures);
				getTestResult().setStatus(TestResult.NOT_EXECUTED);
				return;
			} catch (CADException e) {
				Log.log[LOG].println(2, "");
				Log.log[LOG].println(2, capdu.getName() + " FAILED: " + e.getMessage());
				getTestResult().setMessage(e.getMessage());
				getTestResult().setTimeMeasure(measures);
				getTestResult().setStatus(TestResult.NOT_EXECUTED);
				return;
			}
			
			try{
				// Run command
				// Measure execution time for x executions on-card
				if (!sendAsIs) {
				  capdu.setINS(Constants.RUN_INS | c);
				  capdu.setName("RUN " + i + " [" + this.capdu.getName() + "]");
				  cad.sendAPDUAndVerify(capdu);
				  setApdu(capdu.toHexString());
				}
				else {
				  capdu.setINS(this.capdu.getINS());
				  capdu.setName("RUN " + i + " [" + this.capdu.getName() + "]");
				  cad.sendAPDUAndVerify(capdu);
				  setApdu(capdu.toHexString());
				}
				
				/**
				 * This check could be damageable for the calibration.
				 * The execution time of a test is garantied by the calibration to
				 * be over one second.
				 */
				// Compute execution time for 1 execution on-card
				measures[i] = cad.getMeasure();
				Log.log[LOG].println(8, "> Total time for " + m + " executions on-card [" + this.capdu.getName() + "]: " + measures[i]);
				Log.log[LOG].println(8, "> Total time for " + 1 + " execution on-card [" + this.capdu.getName() + "]: " + measures[i].divide(m));
				Log.log[LOG].println(8, "");
			} catch (BadResponseStatusException e) {
				Log.log[LOG].println(2, "");
				Log.log[LOG].println(2, capdu.getName() + " FAILED: " + e.getMessage());
				getTestResult().setMessage(e.getMessage());
				getTestResult().setTimeMeasure(measures);
				getTestResult().setStatus(TestResult.FAILED);
				return;
			} catch (CADException e) {
				Log.log[LOG].println(2, "");
				Log.log[LOG].println(2, capdu.getName() + " FAILED: " + e.getMessage());
				getTestResult().setMessage(e.getMessage());
				getTestResult().setTimeMeasure(measures);
				getTestResult().setStatus(TestResult.FAILED);
				return;
			}
			
			try {
				// Clean-up command
				// Don't measure execution time
				if (!sendAsIs) {
				  capdu.setINS(Constants.CLEANUP_INS | this.capdu.getINS());
				  capdu.setName("CLEAN-UP " + i + " [" + this.capdu.getName() + "]");
				  cleanupResponse = basecad.sendAPDUAndVerify(capdu);
				}
			} catch (BadResponseStatusException e) {
				Log.log[LOG].println(2, "");
				Log.log[LOG].println(2, capdu.getName() + " FAILED: " + e.getMessage());
				getTestResult().setMessage(e.getMessage());
				getTestResult().setTimeMeasure(measures);
				getTestResult().setStatus(TestResult.FAILED);
				return;
			} catch (CADException e) {
				Log.log[LOG].println(2, "");
				Log.log[LOG].println(2, capdu.getName() + " FAILED: " + e.getMessage());
				getTestResult().setMessage(e.getMessage());
				getTestResult().setTimeMeasure(measures);
				getTestResult().setStatus(TestResult.FAILED);
				return;
			}
		}
		getTestResult().setMessage(null);
		getTestResult().setStatus(TestResult.PASSED);
		getTestResult().setTimeMeasure(measures);
		TimeMeasure avg = TimeMeasure.average(getTestResult().getTimeMeasure());
		getTestResult().setAvg(avg.getMeasure());
		getTestResult().setStdError(TimeMeasure.stdError(getTestResult().getTimeMeasure()).getMeasure());
		Log.log[LOG].println("> Average measure for " + m + " executions on-card [" + this.capdu.getName() + "]: "
	        + avg);
		Log.log[LOG].println("> Average measure for " + 1 + " execution on-card [" + this.capdu.getName() + "]: "
		    + avg.divide(m));
		Log.log[LOG].println("");
  }
  
//  /**
//   * Returns an array of commands.
//   * This array contains all the commands needed to set up this test case. 
//   * 
//   * @param max the maximum value for the <tt>Lc</tt> byte in each command.
//   */
//  private CommandAPDU[] getSetupCommandAPDU(int max) {
//	ByteArrayOutputStream stream = new ByteArrayOutputStream(max);
//	Vector commands = new Vector();
//	byte[] data = this.capdu.getData();
//	for (int j = 0; j < data.length; ) {
//	  int size = data.length - j;
//	  if (size > max)
//	    size = max;
//	  stream.write(data,j,size);
//	  CommandAPDU capdu = new CommandAPDU(
//          this.capdu.getCLA(),Constants.SETUP_INS | this.capdu.getINS(),
//		  this.capdu.getP1(),this.capdu.getP2(),
//		  stream.toByteArray(),
//		  this.capdu.getLe());
//	  capdu.setName("SET-UP [" + this.capdu.getName() + "]");
//	  commands.add(capdu);
//      stream.reset();
//	  j += size; 
//	}
//	return (CommandAPDU[]) commands.toArray(new CommandAPDU[0]);
//  }
  
  /**
   * Returns the set-up response.
   * 
   * @return the associated <tt>ResponseAPDU</tt> object.
   */
  public ResponseAPDU getSetupResponse() {
    return setupResponse;
  }
  
  /**
   * Returns the clean-up response.
   * 
   * @return the associated <tt>ResponseAPDU</tt> object.
   */
  public ResponseAPDU getCleanupResponse() {
    return cleanupResponse;
  }
  
  /**
   * Returns the test case name.
   * 
   * @return the test case name.
   */
  public String getName() {
    return capdu.getName();
  }
  
  /**
   * Determines of this test case is a reference test case, or not.
   * 
   * @return <tt>true</tt> if and only if this test case is a reference test 
   * case.
   */
  public boolean isReference() {
    return getReferenceName() == null;
  }
  
  /**
   * Determines of this test case is a test case used for calibration, or not.
   * 
   * @return <tt>true</tt> if and only if this test case is used for calibration.
   */
  public boolean isForCalibration() {
    return getCalibrationName() == null;
  }
  
  /**
   * Returns the command case for this test case.
   * 
   * @see Constants#CASE_1
   * @see Constants#CASE_2
   * @see Constants#CASE_3
   * @see Constants#CASE_4
   */
  public int getCase() {
    return c;
  }
  
  /**
   * Gets the maximum number of executions to be performed on-card for this test case.
   * This value is useful for test cases chosen for calibration only.
   */
  public int getXMax() {
    return xmax;
  }
  
  /**
   * Gets the minimum number of executions to be performed on-card for this test case.
   * This value is useful for test cases chosen for calibration only.
   */
  public int getXMin() {
    return xmin;
  }
	
  /**
   * Resets the test case result.
   * 
   * @see TestResult#NOT_EXECUTED
   */
  public void reset() {
	getTestResult().setStatus(TestResult.NOT_EXECUTED);
	getTestResult().setMessage(null);
    getTestResult().setTimeMeasure(new TimeMeasure[0]);
  }
  
  /**
   * Sets the name of the associated reference test case. It also
   * sets the calibrate reference test case. The tests that need to
   * have a different calibration reference need to do so explicitely
   * through setCalibrateReference.
   * 
   * @param reference the name of the associated reference test case. 
   * @return this <tt>TestCase</tt> object.
   */
  public TestCase setReference(String reference) {
    setReferenceName(reference);
    setCalibrationName(reference);
    return this;
  }
  
  /**
   * Sets the name of the associated calibrate reference test case.
   * 
   * @param reference the name of the associated calibrate reference test case. 
   * @return this <tt>TestCase</tt> object.
   */
  public TestCase setCalibration(String reference) {
    setCalibrationName(reference);
	return this;
  }
  
  /**
   * Sets the benched unit string.
   * 
   * @param reference the benched unit string. 
   * @return this <tt>TestCase</tt> object.
   */
  public TestCase setBenched(String reference) {
	  setBenchedUnit(reference);
	  return this; 
  }
  
  /**
   * Tags this test case as being a test case used for calibration.
   */
  public TestCase forCalibration() {
    setCalibration(null);
    return this;
  }
 
  /**
   * Sets the number of executions to be performed on-card for this test 
   * case.
   * 
   * @param x the new on-card execution number.
   */
  public void setX(int x) {
	super.setX(x);
    capdu.setP2(x);
  }
  
  /**
   * Sets the command case for this test case.
   * 
   * @param c the new command case.
   * @return this <tt>TestCase</tt> object.
   * @throws <tt>CADException</tt> if the specified case is invalid or 
   * incompatible with the command format for this test case.
   * 
   * @see Constants#CASE_1
   * @see Constants#CASE_2
   * @see Constants#CASE_3
   * @see Constants#CASE_4
   */
  public TestCase setCase(int c) throws IllegalArgumentException {
	switch (c) {
	  case Constants.CASE_1:
	    break;
	  case Constants.CASE_2:
		if ((capdu.getCase() != 2) && (capdu.getCase() != 4)) 
		  throw new IllegalArgumentException("The specified case is incompatible with the command format (" 
		  + capdu.getCase() + ")");
        break;
	  case Constants.CASE_3:
		if ((capdu.getCase() != 3) && (capdu.getCase() != 4)) 
		  throw new IllegalArgumentException("The specified case is incompatible with the command format (" 
		      + capdu.getCase() + ")");
		break;
	  case Constants.CASE_4:
		if (capdu.getCase() != 4)
		  throw new IllegalArgumentException("The specified case is incompatible with the command format (" 
	         + capdu.getCase() + ")");
		break;
	  default:
	    throw new IllegalArgumentException("The specified case is invalid");
	}
	this.c = c;
    return this;	  
  }
  
  /**
   * Sets the maximum number of executions to be performed on-card for this test case.
   * This value is useful for test cases choosen for calibration only.
   * 
   * @param xmax the new value.
   */
  public void setXMax(int xmax) {
    this.xmax = xmax;
  }
  
  /**
   * Sets the minimum number of executions to be performed on-card for this test case.
   * This value is useful for test cases choosen for calibration only.
   * 
   * @param xmin the new value.
   */
  public void setXMin(int xmin) {
    this.xmin = xmin;
  }
  
  /** 
   * Specifies that the underlying APDU command shall be sent 
   * "as is". 
   */
  public void sendAsIs() {
    sendAsIs = true;
  }
  
  /**
   * Determines if the underlying APDU command shall be sent "as is" or not.
   * 
   * @return <tt>true</tt> if and only if the underlying APDU command is sent 
   * "as is".
   */
  public boolean sentAsIs() {
    return sendAsIs;
  }
}