package lib.main;

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
 * A single test unit for the mainArgsProcessor system.
 */
public class Check extends Main {

	/**
	 * The config string [][] give to the super constructor 
	 * (Class MainArgsProcessor) to degine some specific features 
	 * for our check class.
	 */
	static final private String[][] config = {
			{"t1","test1","0","test number 1"},
			{"t2","test2","0","test number 2"},
			{"t3","test3","1","test number 3"},
			{"t4","test4","2","test number 4"}
	};
	
	/**
	 * an array of string array to be processed for the tests.
	 */
	static final private String[][] tests = { {"-t1"},
											{"-test2"},
											{"-t3","a"},
											{"-test4","b","c"},
											{"-test1","-t2","-test3", "a", "-t4", "b", "c", "d", "e"}
	};
	
	/**
	 * current state of the test1..4 options (OK/KO, default : KO)
	 */
	static boolean test1OK = false;
	static boolean test2OK = false;
	static boolean test3OK = false;
	static boolean test4OK = false;
	static boolean mainArgsOK = false;
	
	/**
	 * The main method used to launch the the check
	 * @param args
	 */
	public static void main(String[] args) {
		tool = "Check";
		options = config;
		lib.main.Main.main(tests[0]);
		if(!test1OK) 
		  Log.fatal("test1 failed");
		lib.main.Main.main(tests[1]);
		if(!test2OK) 
	      Log.fatal("test2 failed");
		lib.main.Main.main(tests[2]);
		if(!test3OK) 
		  Log.fatal("test3 failed");
		lib.main.Main.main(tests[3]);	
		if(!test4OK) 
		  Log.fatal("test4 failed");
		lib.main.Main.main(tests[4]);
		if(! (test1OK &&
				test2OK &&
				test3OK &&
				test4OK &&
				mainArgsOK) ) 
			Log.fatal("test5 failed");
		Log.out.println("tests successed.");
	}

	/**
	 * the test1 option ("-t1" or "-test1" option of the command line)
	 * @param args
	 */
	public static void processTest1(String[] args) {
		assert args!=null && args.length == 0 : "wrong args length in test1";
		test1OK=true;
	}
	
	/**
	 * the test1 option ("-t2" or "-test2" option of the command line)
	 * @param args
	 */
	public static void processTest2(String[] args) {
		assert args!=null && args.length == 0 : "wrong args length in test1";
		test2OK=true;
	}

	/**
	 * the test3 option ("-t3" or "-test3" option of the command line)
	 * @param args
	 */
	public static void processTest3(String[] args) {
		assert args!=null && args.length == 1 : "wrong args length in test1";
		test3OK = args[0].equals("a");
	}
	
	/**
	 * the test4 option ("-t4" or "-test4" option of the command line)
	 * @param args
	 */
	public static void processTest4(String[] args) {
		assert args!=null && args.length == 1 : "wrong args length in test1";
		test4OK = args[0].equals("b") && args[1].equals("c");
	}

	/**
	 * the test4 option ("-t4" or "-test4" option of the command line)
	 * @param args
	 */
	public static void processFinalArgs(String[] args) {
		assert args!=null && args.length == 2 : "wrong args length in test1";
		mainArgsOK = args[0].equals("d") && args[1].equals("e");
	}

}
