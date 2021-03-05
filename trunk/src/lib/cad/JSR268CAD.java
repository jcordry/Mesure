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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/JSR268CAD.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 569 $
 * $LastChangedDate: 2008-01-16 16:17:10 +0100 (mer., 16 janv. 2008) $
 * $LastChangedBy: cpascal $
 */

import java.util.Iterator;
import java.util.List;

import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

public class JSR268CAD extends CAD {
	
	/** The Java Card platform. */
	private Card card;

	/** The logical channel. */
	private CardChannel channel;
	
	/** 
	 * Builds a <tt>JSR268CAD</tt> object with the specified name.
	 * @param name the card reader name associated to the newly created CAD.
	 */
	public JSR268CAD(String name) {
	  super(name);
	}

	/**
	 * @see CAD#connect()
	 */
	public ATR connect() throws CADException {
		TerminalFactory factory = TerminalFactory.getDefault();
		List terminals = null;
		try {
			terminals = factory.terminals().list();
		} catch (CardException e) {
			e.printStackTrace();
			System.exit(1);
		}
		if (terminals.isEmpty())
		  throw new CADException("No card reader detected.");
		CardTerminal terminal = null;
		for (Iterator i = terminals.iterator(); i.hasNext ();) {
	      CardTerminal t = (CardTerminal) i.next();
	      if (t.getName().equalsIgnoreCase(name)) {
	    	terminal = t;
	        break;
	      }
		}
		if (terminal == null)
		  throw new CADException("Card reader " + name + " not found.");
		boolean inserted = false;
		try {
		  inserted = terminal.isCardPresent();
		} catch (CardException e) {
	      throw new CADException(e);
		}
		if (!inserted)
		  throw new CADException("Card is not inserted into reader.");
		try {
		  // We should have instead
		  // card = terminal.connect("*");
		  // but the JSR 268 does not handle correctly the T=1 protocol.
		  card = terminal.connect("T=0");
		} catch (CardException e) {
		  throw new CADException(e);
		}
		channel = card.getBasicChannel();
		return card.getATR();
	}

	/**
	 * @see CAD#disconnect()
	 */
	public void disconnect() throws CADException {
	  try {
		card.disconnect(false);
	  } catch (CardException e) {
	    throw new CADException(e);
	  }
	}

	/**
	 * @see CAD#send(CommandAPDU)
	 */
	protected ResponseAPDU send(CommandAPDU capdu) throws CADException {
		javax.smartcardio.ResponseAPDU rapdu = null;
		try {
		  rapdu = channel.transmit(new javax.smartcardio.CommandAPDU(capdu.getBytes()));
		} catch (CardException e) {
		  throw new CADException(e);	
		}
		return new ResponseAPDU(rapdu.getBytes());
	}
}
