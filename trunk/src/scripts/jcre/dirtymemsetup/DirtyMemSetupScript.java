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
 * $HeadURL: svn+ssh://meunier@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 23 Avril 2007
 * Author: POPS 
 * $LastChangedRevision: 35 $
 * $LastChangedDate: 2007-04-23 17:10:44 +0200 (ven., 29 sept. 2006) $
 * $LastChangedBy: meunier $
 */
package scripts.jcre.dirtymemsetup;

import java.util.Vector;

import lib.cad.CAD;
import lib.cad.GPCAD;
import lib.cad.CADException;
import lib.cad.CommandAPDU;
import lib.cad.ResponseAPDU;
import lib.cad.SelectAPDU;
import lib.capfile.CAPFile;
import lib.loader.GPLoader;
import lib.log.Log;
import scripts.jcre.memsetup.MemSetupScript;
import tools.manager.Resources;


/**
 * Script for Memory testing benchs.
 * @author HM
 *
 */
public class DirtyMemSetupScript extends MemSetupScript {

	private static final int LOG = lib.log.Log.getASpecificLogEntry("DIRTYMEM");

	private Vector<byte[]> AIDs = new Vector<byte[]>();

	/**
	 * 
	 */
	public DirtyMemSetupScript() {
		// TODO Auto-generated constructor stub
	}



	/* (non-Javadoc)
	 * @see scripts.templates.TemplateScript#prepare(lib.cad.CAD, lib.loader.GPLoader)
	 * Is in charge of fragmentating the available memory.
	 * Basically, load 2 applets, run commands upon these applets, alternatively
	 * allocating and appending a dummy node to a list.
	 * This fragmentates the memory. The next step is removing the 1st applet, roughly
	 * freeing space used by the 1st applet. One more loading of the removed applet, and
	 * we end up with a vastly fragmentated memory. ..
	 * 
	 *  returns true if everything gone fine.
	 */
	public boolean prepare(GPCAD gpcad, GPLoader loader) throws Exception {
		String capFileName;
		CAPFile capfile;
		
		// underlying cad
		CAD cad = gpcad.getCAD();
		
		// MESURE_dirtymem
		// MESURE_memsetup
		byte[] pkg = {0x4D, 0x45, 0x53, 0x55, 0x52, 0x45, 0x5F, 0x64, 0x69, 0x72, 0x74, 0x79, 0x6D, 0x65};
		byte[] pkg2 = {0x4D, 0x45, 0x53, 0x55, 0x52, 0x45, 0x5F, 0x64, 0x69, 0x72, 0x74, 0x79, 0x6D, 0x66};
		byte[] applets = {0x4D, 0x45, 0x53, 0x55, 0x52, 0x45, 0x5F, 0x64, 0x69, 0x72, 0x74, 0x79, 0x6D, 0x65, 0x01};
		byte[] applets2 = {0x4D, 0x45, 0x53, 0x55, 0x52, 0x45, 0x5F, 0x64, 0x69, 0x72, 0x74, 0x79, 0x6D, 0x66, 0x01};

		final String dirtyAppletPath = "benchs/jcre/dirtymemsetup/javacard/dirtymemsetup.cap";
		final String dirtyApplet2Path = "benchs/jcre/dirtymemsetup2/javacard/dirtymemsetup2.cap";
		Log.log[LOG].println("prepare() : fragmenting memory");

		try  {
			Log.log[LOG].println("reading and loading dirtyMemSetup capfile");
			capFileName = Resources.getCAPFile(dirtyAppletPath);	
			capfile = new CAPFile(capFileName);
			capfile.read();
			loader.load(capfile);

			Log.log[LOG].println("reading and loading dirtyMemSetup2 capfile");
			capFileName = Resources.getCAPFile(dirtyApplet2Path);	
			capfile = new CAPFile(capFileName);
			capfile.read();
			loader.load(capfile);

			AIDs.add(pkg); AIDs.add(pkg2); AIDs.add(0, applets); AIDs.add(0, applets2);

			Log.log[LOG].println("Installs dirty applets 1 and 2");
			loader.install(pkg, applets, applets, 0, null);
			loader.install(pkg2, applets2, applets2, 0, null);

		} catch (CADException e) {
			e.printStackTrace();
			throw new Exception(e);
		}

		// Fills memory
		Log.log[LOG].println("calls command 1 of applet1 and 2 until memory is filled up");

		CommandAPDU capdu = null;
		int cnt = 0;
		while (true) {
			// selects dirty applet1
			capdu = new SelectAPDU(applets);
			cad.sendAPDUAndVerify(capdu);

			capdu = new CommandAPDU(0, 1, 0, 0);
			ResponseAPDU r = null;
			try {
				r = cad.sendAPDU(capdu);
				if (r.getSW() != 0x9000)
					break;
				cnt ++;
			} catch (CADException e) {
				Log.log[LOG].println("Dirty mem response : " + Integer.toHexString(r.getSW()));
				return false;
			}

			// selects dirty applet 2
			capdu = new SelectAPDU(applets2);
			cad.sendAPDUAndVerify(capdu);

			capdu = new CommandAPDU(0, 1, 0, 0);
			r = null;
			try {
				r = cad.sendAPDU(capdu);
				if (r.getSW() != 0x9000)
					break;
				cnt ++;
			} catch (CADException e) {
				Log.log[LOG].println("Dirty mem response : " + Integer.toHexString(r.getSW()));
				return false;
			}

		}
		Log.log[LOG].println("dirty mem has allocated " + cnt + " Nodes before failure.");

		// delete applet 1
		Log.log[LOG].println("Deleting dirty mem applet 1");
		cad.connect();
		loader.selectAndAuthenticate(); 
		loader.delete(applets);
		loader.delete(pkg);

		// reload applet 1
		Log.log[LOG].println("Reloading dirty mem applet 1");
		capFileName = Resources.getCAPFile(dirtyAppletPath);	
		capfile = new CAPFile(capFileName);
		capfile.read();
		loader.load(capfile);
		loader.install(pkg, applets, applets, 0, null);
		return true;
	}

	/* (non-Javadoc)
	 * @see scripts.templates.TemplateScript#terminate(lib.cad.CAD, lib.loader.GPLoader)
	 */
	public void terminate(GPCAD gpcad, GPLoader loader) throws Exception {
		Log.log[LOG].println("terminate() : ");

		// underlying cad
		CAD cad = gpcad.getCAD();
		try {
			// Deletes the CAP file
			cad.connect();
			loader.selectAndAuthenticate(); 

			System.out.println("AIDS : " + AIDs.size());
			for (int i = 0; i < AIDs.size(); i++) {
				Log.log[LOG].println("removing dirty applets.");
				loader.delete((byte[])AIDs.elementAt(i));
			}
		} catch (CADException e) {
			throw new Exception(e); 
		}
	}
}
