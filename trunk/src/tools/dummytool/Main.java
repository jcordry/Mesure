package tools.dummytool;

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
 * $HeadURL: svn+ssh://meunier@scm.gforge.inria.fr/svn/mesureprv/src/lib/cad/JSR268CAD.java $
 * Created: 1 septembre 2006
 * Author: POPS 
 * $LastChangedRevision: 23 $
 * $LastChangedDate: 2006-09-08 13:44:52 +0200 (Fri, 08 Sep 2006) $
 * $LastChangedBy: meunier $
 */

import lib.log.Log;

/**
 * This class is just a dummy tool that demonstrates how to use MainArgsProcessor.
 * You may use it as a skeleton for your own tools. 
 *
 */
public class Main extends lib.main.Main {
	/**
	 * Log usage example.
	 *  this LOG is a tool wide log.
	 *  foobarLOG is limited to the processFoobar method.
	 */
    static final private int LOG = lib.log.Log.getASpecificLogEntry("Log of DummyTool");
    static final private int foobarLOG = lib.log.Log.getASpecificLogEntry("Log of foobarHandler");
	
	/**
	 * Whether foobar debugging is enabled. False by default.
	 */
	static boolean foobar = false;
	
	/**
	 * How many cats to use.
	 */
	static int numberOfCats = 0;

	/**
	 * What to do when the "foobar" option is met.
	 * @param args Arguments of the foobar option - that is, none.
	 */
	public static void processFoobar(String[] args)
	{
		foobar = true;
		
		// Illustrates usage of another log.
		Log.log[foobarLOG].println("Foobar debugging is switched on !");
	}

	/**
	 * What to do when the numberofcats option is met.
	 * @param args Arguments of the numberofcat options. There is only one: the number of cats in question.
	 */
	public static void processNumberofcats(String[] args)
	{
		numberOfCats = Integer.parseInt(args[0]);
	}

	/**
	 * Process the final arguments. These are the divinities that are going to be invoked.
	 */
	public static void processFinalArgs(String[] args)
	{
		for (String arg : args)
		{
			Log.log[LOG].println("Invoking " + arg + "...");
		}
	}
	
	/**
	 * Execute our tool, according to its current settings.
	 */
	private static void exec()
	{
		// illustrate basic multi-Log usage
		Log.log[LOG].println("Start of DummyTool...");
		if (foobar) {
			// enabled if foobar flag is on
			Log.log[LOG].println("Foobar debugging is active");
		}
		Log.log[LOG].println("Number of cats to use: " + numberOfCats);
		
		// illustrates debuglevel.
		// when invoked with -vl 8 at least, print nice additional debugging
		// infos.
		Log.log[LOG].print(8,">>");
		Log.log[LOG].printCurrentMethod(8);
		Log.log[LOG].println(8,"");
		Log.log[LOG].enter();			    
		
		if (numberOfCats >= 666)
		{
			Log.log[LOG].println("UNDEADS! UNDEADS!");
			if (foobar) {
				Log.log[LOG].println("Foobar debugging enabled - undeads shot down.");
			}
			else {
				Log.log[LOG].println("No debugging enabled! To Hell, the whole of you!");
			}
		}
		Log.log[LOG].println("End of DummyTool...");
	}

	/**
	 * Main method. Instanciates this class, processes the command-line arguments, 
	 * and executes the tool.
	 */
	public static void main(String[] args) {
	  tool = "dummyTool";
	  options = new String[][]{
			{ "f", "foobar", "0", "Use foobar debugging" },
			{ "nbc", "numberofcats", "1", "<number>, specify number of cats to use" },
			{ "i", "input", null, null},
			{ "o", "output", null, null}
	  };
	  lib.main.Main.main(args);
	  exec();
	}
}
