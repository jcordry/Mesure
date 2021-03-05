package lib.cad;

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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/BadResponseStatusException.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */


/**
 * Exception thrown to indicate that the returned status of an APDU response is 
 * not as expected.
 */
public class BadResponseStatusException extends CADException {

  /** The APDU command. */
  private CommandAPDU command;
  
  /** The returned APDU response for <tt>command</tt> APDU command. */
  private ResponseAPDU response;
  
  /** The expected status for the <tt>response</tt> APDU response. */
  private int expectedStatus;

  /** 
   * Constructs a <tt>BadResponseStatusException</tt> exception with the 
   * default expected status (<tt>0x9000</tt>).
   * @param command the APDU command
   * @param response the returned APDU response
   */
  public BadResponseStatusException(CommandAPDU command, ResponseAPDU response) {
    expectedStatus = 0x9000;
    this.command = command;
    this.response = response;
  }

  /** 
   * Constructs a <tt>BadResponseStatusException</tt> exception with the
   * specified expected status.
   * @param command the APDU command
   * @param response the returned APDU response
   * @param expectedStatus the expected status
   */
  public BadResponseStatusException(CommandAPDU command, ResponseAPDU response, int expectedStatus) {
    this.expectedStatus = expectedStatus;
    this.command = command;
    this.response = response;
  }

  /**
   * Returns the detail message string of this exception.
   *
   * @inheritDoc
   * @return the detail message string of this <tt>BadResponseStatusException</tt> 
   *         instance.
   */
  public String getMessage() {
    StringBuffer s = new StringBuffer();
    s.append("The " + command.getName() + " command failed with response status ");
    s.append("0x" + Integer.toHexString(response.getSW()) + ", but 0x" +
    		   Integer.toHexString(expectedStatus) + " was expected.");
    return s.toString();
  }

  /**
   * Returns the expected status.
   * 
   * @return the expected status.
   */
  public int getExpectedStatus() {
    return expectedStatus;
  }

  /**
   * Returns the APDU response.
   * 
   * @returns the APDU response.
   */
  public ResponseAPDU getResponse() {
    return response;
  }

  /**
   * Returns the APDU command.
   * 
   * @return the APDU command.
   */
  public CommandAPDU getCommand() {
    return command;
  }
}
