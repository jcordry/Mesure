package lib.log;

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
 * $HeadURL: svn+ssh://meunier@scm.gforge.inria.fr/svn/mesureprv/src/lib/cad/JSR268CAD.java $
 * Created: 1 septembre 2006
 * Author: POPS 
 * $LastChangedRevision: 23 $
 * $LastChangedDate: 2006-09-08 13:44:52 +0200 (Fri, 08 Sep 2006) $
 * $LastChangedBy: meunier $
 */

import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Log {
	
	/**
	 * defines the defaultFilter level for LogStream outputs <p>
	 * take also a look on setAGeneralFilterLevel(int level).
	 */
	public static int defaultFilterLevel = -1; 

	/**
	 * out is the default (common) LogStream. 
	 */
	public final static LogStream out = new LogStream(1);
	
	public static PrintStream defaultOutStream = System.out;
	/**
	 * err is the error report LogStream.
	 */
	public final static LogStream err = new LogStream(java.lang.System.err,1);

	/**
	 * log[] is an array of log used for specific log output.
	 * You must get a specific entry using logIndex.
	 */
	public static LogStream log[] = new LogStream[0];
	
	/**
	 * prompt warnings (default true) 
	 */
	public static boolean dumpWarnings = true;
	/**
	 * the LogStream used to report the warnings...
	 */
	private static int WarningLOG = getASpecificLogEntry(1,"WARNING");

	/**
	 * a set of string containing the already displayed warning...
	 */
	private static Set<String> warnings = new HashSet<String>();

	/**
	 * a map of string->Integer to get the specific filterLevel of a filterName
	 */
	private static Map<String,Integer> filterMap = new HashMap<String,Integer>();
	
	/**
	 * getASpecificLogEntry return an new entry in the Log.log[]
	 * with a default output (java.lang.System.out),
	 * the default filter (Log.defaultFilterLevel),
	 * and the default string ("").
	 * @return the dedicated Log Entry in the log array
	 */
	public static int getASpecificLogEntry() {
		return getASpecificLogEntry(Log.defaultOutStream,Log.defaultFilterLevel,"");
	}

	/**
	 * getASpecificLogEntry return an new entry in the Log.log[]
	 * with a default output (java.lang.System.out),
	 * and the default string ("").
	 * @param level the filter level expected.
	 * @return the dedicated Log Entry in the log array
	 */
	public static int getASpecificLogEntry(int level) {
		return getASpecificLogEntry(Log.defaultOutStream,level,"");
	}

	/**
	 * getASpecificLogEntry return an new entry in the Log.log[] with a specific header.
	 * @param header the string used as line header.
	 * @return the dedicated Log Entry in the log array
	 */
	public static int getASpecificLogEntry(String header) {
		return getASpecificLogEntry(Log.defaultOutStream,Log.defaultFilterLevel,header);
	}

	/**
	 * getASpecificLogEntry return an new entry in the Log.log[]
	 * @param level the filter level expected.
	 * @param header the string used as line header.
	 * @return the dedicated Log Entry in the log array
	 */
	public static int getASpecificLogEntry(int level, String header) {
		return getASpecificLogEntry(Log.defaultOutStream,level,header);
	}
	
	/**
	 * getASpecificLogEntry return an new entry in the Log.log[]
	 * @param aPrintStream the PrintStream used as output.
	 * @param level the filter level expected.
	 * @param header the string used as line header.
	 * @return the dedicated Log Entry in the log array
	 */
	public static int getASpecificLogEntry(PrintStream aPrintStream, int level, String header) {
		int logSize = log.length;
		LogStream newLog[] = new LogStream[logSize+1];
		java.lang.System.arraycopy(log,0,newLog,0,logSize);
		newLog[logSize]=new LogStream(aPrintStream, level,header);
		if(Log.filterMap!=null && Log.filterMap.get(header)!=null) {
			newLog[logSize].setFilter(Log.filterMap.get(header));
		}
		log=newLog;
		return logSize;
	}
	
	/**
	 * notify a Fatal error exception on the log.err and exit()
	 * @param anException the fatal exception 
	 */
	public static void fatal(Throwable anException) {
		StackTraceElement aFrame[]=Thread.currentThread().getStackTrace();
		Log.err.print("a Fatal error is throw in ");
		Log.err.println(aFrame[3].getClassName()+"."+aFrame[3].getMethodName()+"(...) in file \""+aFrame[3].getFileName()+"\" line "+aFrame[3].getLineNumber());
		anException.printStackTrace(Log.err);
		java.lang.System.exit(1);
	}

	/**
	 * notify a Fatal error on the log.err and exit()
	 * @param message the error notification printed on log.err 
	 */
	public static void fatal(String message) {
		Log.err.println(message);
		java.lang.System.exit(1);
	}
	
	/**
	 * set a General Filter Level for the Log.log LogStream. <p>
	 * N.B. defaultLevel is not applied to Log.out and Log.err
	 * 
	 * @param defaultLevel the expected level
	 */
	public static void setAGeneralFilterLevel(int defaultLevel){
		Log.defaultFilterLevel = defaultLevel;
		for(int i=0;i<Log.log.length;i++){
			if(i!=Log.WarningLOG)
				Log.log[i].setFilter(defaultLevel);
		}
	}
	
	/**
	 * print a warning on the Log.err.
	 * Each warning is printed only once.
	 * 
	 * @param msg the warning...
	 */
	public static void warning(String msg) {
		if(dumpWarnings) {
			if(!warnings.contains(msg)) {
				Log.log[WarningLOG].println(msg);
				warnings.add(msg);
			}
		}
	}
	/**
	 * print a warning on the Log.err with a filter level.
	 * Each (printed) warning is printed only once.
	 * 
	 * @param lvl the warning level...
	 * @param msg the warning...
	 */
	public static void warning(int level, String msg) {
		if(dumpWarnings) {
			if(Log.log[WarningLOG].willPrintLevel(level)) {
				if(!warnings.contains(msg)) {
					Log.log[WarningLOG].println(level,msg);
					warnings.add(msg);
				}
			}
		}
	}
	
	/**
	 * set FilterLevel allow to specify a specific filterLevel for a
	 * filter specified by it name as shown in '['+name+']'
	 * @param filterName the filterName targeted
	 * @param filterLevel the filterLevel expected
	 */
	public static void setFilterLevel(String filterName, int filterLevel) {
		for(int i=0;i<Log.log.length;i++) {
			if(Log.log[i].logName==filterName) {
				Log.log[i].setFilter(filterLevel);
				return ;
			}
		}
		Log.filterMap.put(filterName,filterLevel);
	}

	/**
	 * set FilterLevel allow to specify a specific filterLevel for the
	 * warning log.
	 * @param filterLevel the filterLevel expected
	 */
	public static void setWarningFilterLevel(int filterLevel) {
		Log.log[WarningLOG].setFilter(filterLevel);
	}
	
	/**
	 * set log[]-wide output stream.
	 * @param out
	 */
	public static void setOut(PrintStream out) {
		Log.defaultOutStream = out;
		for (int i=0; i<log.length; i++) {
			log[i].setOut(Log.defaultOutStream);
		}
	}
}
