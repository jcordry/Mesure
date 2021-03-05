package lib.cad;

import javax.smartcardio.ATR;

import lib.log.Log;

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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/CAD.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 337 $
 * $LastChangedDate: 2007-08-10 18:02:45 +0200 (ven., 10 août 2007) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class should be extended by all Card Acceptance Device (CAD)
 * related class.
 * It provides useful methods to manage a CAD.
 */
public abstract class CAD
{
	/**
	 * Log specific to this class.
	 */
	public static final int LOG = lib.log.Log.getASpecificLogEntry("CAD");
	
	/**
	 * The CAD name;
	 */
	protected String name;
	
	/**
	 * Builds a CAD with the specified name.
	 * 
	 * @param name the CAP name.
	 */
	public CAD(String name) {
	  setName(name);
	}
	
	/**
	 * Establishes a connection to the card. 
	 * 
	 * @throws <tt>CardException</tt> if the connection could not be 
	 *         established.
	 * @return the <tt>ATR</tt> object encapsulating the answer-to-reset bytes.
	 */
    public abstract ATR connect() throws CADException;
	
    /**
     * Disconnects the connection with this card. 
     * 
     * @throws <tt>CADException</tt> if the operation failed.
     */
    public abstract void disconnect() throws CADException;
 
    /**
     * Sends an ADPU command and returns the response. 
     * APDUs are traced.
     * 
     * @param capdu the <tt>CommandAPDU</tt> to send.
     * @return the response to the sent APDU command.
     * @throws <tt>CADException</tt> if a communication problem occured.
     */
    public ResponseAPDU sendAPDU(CommandAPDU capdu) throws CADException {
      Log.log[LOG].println(4, " (capdu) "+ capdu.toString());
      ResponseAPDU rapdu = send(capdu);
      Log.log[LOG].println(4, "(rapdu) " + rapdu.toString());
	  Log.log[LOG].println(4, "");
	  return rapdu;
    }
    
    /**
     * Sends an ADPU command and returns the response. 
     * 
     * @param capdu the <tt>CommandAPDU</tt> to send.
     * @return the response to the sent APDU command.
     * @throws <tt>CADException</tt> if a communication problem occured.
     */
    protected abstract ResponseAPDU send(CommandAPDU capdu) throws CADException;
    
    /**
     * Sends an APDU command, gets the response and compares its status to the 
     * expected status passed as parameter. 
     * @param capdu the <tt>CommandAPDU</tt> to send.
     * @param sw the expected status for the APDU response.
     * @return the response to the sent APDU command.
     * @throws <tt>CADException</tt> if a communication problem occured.
     * @throws <tt>BadResponseStatusException</tt> if the response status is 
     *         not equal to the expected one.
     */
    public ResponseAPDU sendAPDUAndVerify(CommandAPDU capdu, int sw) 
        throws CADException, BadResponseStatusException {
      ResponseAPDU rapdu = sendAPDU(capdu);
      if (rapdu.getSW() != sw)
        throw new BadResponseStatusException(capdu,rapdu,sw);
      return rapdu;
    }
    
    /**
     * Sends an APDU command, gets the response and compares its status to the 
     * <tt>0x9000</tt> status. 
     * @param capdu the <tt>CommandAPDU</tt> to send.
     * @return the response to the sent APDU command.
     * @throws <tt>CADException</tt> if a communication problem occured.
     * @throws <tt>BadResponseStatusException</tt> if the response status is 
     *         not equal to the expected one.
     */
    public ResponseAPDU sendAPDUAndVerify(CommandAPDU capdu) 
        throws CADException, BadResponseStatusException {
      return sendAPDUAndVerify(capdu,0x9000);
    }

	/**
	 * Returns the name of this CAD.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/** Set the name of this CAD.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
