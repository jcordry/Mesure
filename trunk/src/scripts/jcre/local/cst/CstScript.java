package scripts.jcre.local.cst;

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

import lib.cad.CADException;
import lib.cad.TimeCAD;
import lib.cad.ResponseAPDU;
import lib.cad.CommandAPDU;
import scripts.templates.TemplateScript;
import lib.chrono.ChronoException;
import lib.chrono.TimeMeasure;
import lib.chrono.Chronometer;
import lib.log.Log;

public class CstScript extends TemplateScript {
	private final static byte[] AID = new byte[]{
		(byte) 0x4D, (byte) 0x45, (byte) 0x53, (byte) 0x55,
		(byte) 0x52, (byte) 0x45, (byte) 0x5F, (byte) 0x63,
		(byte) 0x73, (byte) 0x74, (byte) 0x01
	};

	/**
	 * @see TemplateScript#getAID()
	 */
	protected byte[] getAID() {
		return AID;
	}
	
	/**
	 * This method is used to launch a given APDU.
	 * 
	 * @param apdu the apdu to send
	 * @param c the chronometer used to perform the measure
	 * @param cad the CAD to use to send commands.
	 * @param loopname the name of the test.
	 * @throws <tt>CADException</tt> if a problem occurs when sending the script commands.
	 * @return the time measured
	 */
	protected TimeMeasure launchTest (byte [] apdu, TimeCAD cad, String loopName) throws CADException {
		TimeMeasure tm;
		Chronometer 	c = cad.getChronometer();
		Log.out.println("> " + loopName);
		CommandAPDU capdu = new CommandAPDU(apdu);
		Log.out.println(capdu.toString());
		ResponseAPDU response;
		c.start();
		response = cad.sendAPDU(capdu);
		try {
	        tm = c.stop();
	      } catch (ChronoException e) {
	        throw new CADException(e);
	      }
		Log.out.println("Measure : " + tm.toString() + "\n" + response.toString() + "\n\n");
		return tm;
	}

	/**
	 * @see TemplateScript#process(CAD)
	 */
	protected void process(TimeCAD cad) throws CADException {
		TimeMeasure [] tm = new TimeMeasure[9];
		
		/* loopsize is the size of the loop and should be equal to
		 * (2^(P2 & 0x0F) - 1) 
		 */
		int loopsize = 511;
		byte [] capdu = {
				(byte) 0x80, (byte) 0x20, (byte) 0x00, (byte) 0x19
		};
		
		tm[0] = launchTest(capdu, cad, "Empty loop");
		
		capdu[2] = (byte) 0x10;
		tm[1] = launchTest(capdu, cad, "sconst");
		Log.out.println("Average measure for sconst : " + ((tm[1].getMeasure() - tm[0].getMeasure()) / (16*loopsize)) + " ns\n\n");
		
		capdu[2] = (byte) 0x20;
		tm[2] = launchTest(capdu, cad, "aconst_null");
		Log.out.println("Average measure for aconst_null: " + ((tm[2].getMeasure() - tm[0].getMeasure()) / (16*loopsize)) + " ns\n\n");
		
		capdu[2] = (byte) 0x30;
		tm[3] = launchTest(capdu, cad, "bspush");
		Log.out.println("Average measure for bspush: " + ((tm[3].getMeasure() - tm[0].getMeasure()) / (16*loopsize)) + " ns\n\n");
		
		capdu[2] = (byte) 0x40;
		tm[4] = launchTest(capdu, cad, "sspush");
		Log.out.println("Average measure for sspush: " + ((tm[4].getMeasure() - tm[0].getMeasure()) / (16*loopsize)) + " ns\n\n");
		
	}

}
