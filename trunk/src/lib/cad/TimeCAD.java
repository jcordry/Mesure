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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/TimeCAD.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 252 $
 * $LastChangedDate: 2007-05-02 16:34:56 +0200 (mer., 02 mai 2007) $
 * $LastChangedBy: meunier $
 */

import javax.smartcardio.ATR;

import lib.chrono.ChronoException;
import lib.chrono.Chronometer;
import lib.chrono.SystemTimeNanosChronometer;
import lib.chrono.TimeMeasure;
import lib.log.Log;

/**
 * The CAD to be used when performing time measures on APDU commands.
 */
public class TimeCAD extends CAD
{
	/**
	 * Log specific to this class.
	 */
	public static final int LOG = lib.log.Log.getASpecificLogEntry("TIMECAD");

	/** The underlying CAD. */
	private CAD cad;
	
	/** The chronometer used to perform time measures. */
	private Chronometer chrono;
	
	/** The last measure performed. */
	private TimeMeasure measure;
	
	/** 
	 * Constructs a CAD able to perform time measures from the specified
	 * standard CAD and using the specified chrono.
	 * 
	 * @param cad a standard CAD.
	 * @param chrono the chronometer.
	 */
	public TimeCAD(CAD cad, Chronometer chrono) {
		super(cad.name);
		this.cad = cad;
		this.chrono = chrono; 
	}
	
	/**
	 * Constructs a CAD able to perform time measures from the specified
	 * standard CAD and using the default chronometer
	 * (<tt>SystemTimeMillis</tt>).
	 * 
	 * @param CAD a standard CAD.
	 */
	public TimeCAD(CAD cad) {
	  this(cad,new SystemTimeNanosChronometer());
    }
	
	/**
	 * Returns the chronometers.
	 * 
	 * @return the <tt>Chronometer</tt> object used to perform time measures.
	 */
	public Chronometer getChronometer() {
	  return chrono;
	}
	
	/**
	 * @see CAD#connect()
	 */
    public ATR connect() throws CADException {
      return cad.connect();
    }
	
    /**
     * @see CAD#disconnect()
     */
    public void disconnect() throws CADException {
      cad.disconnect();
    }
 
    /**
     * @see CAD#send(CommandAPDU)
     */
    protected ResponseAPDU send(CommandAPDU capdu) throws CADException {
      measure = null;
      chrono.start();
      ResponseAPDU rapdu = cad.send(capdu);
      try {
        measure = chrono.stop();
      } catch (ChronoException e) {
        throw new CADException(e);
      }
      Log.log[LOG].println(4, "(" + measure.toString() + " - " + 
        		                    chrono.toString() + ")");
      return rapdu;
    }
    
    /**
     * Returns the underlying CAD.
     * 
     * @return the underlying <tt>CAD</tt> object.     
     */
    public CAD getCAD() {
      return cad;
    }
    
    /**
     * Returns the time measure for the last command sent.
     * 
     * @return the <tt>TimeMeasure</tt> object encapsulating the last measure
     *         performed. This measure is converted in nanoseconds before being
     *         returned.
     * @see TimeMeasure#NANOSECONDS
     */
    public TimeMeasure getMeasure() {
      return measure;
    }
}
