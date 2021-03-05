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

/**
 *
 * LogStream is a kind of PrintStream and can be used as a PrintStream.
 * Nevertheless, a LogStream override method from PrintStream with an int as
 * first agrument. This int is used as a level indicator. Default print method
 * are associated to level 0. A LogStream can filter the output message using the
 * setFilter method. In this cas each print command with a detail level >= to the
 * level define by the setFilter are ignore.  
 * 
 */
public class LogStream extends PrintStream {

	/**
	 * lodId: of the last instancied LogStream
	 */
	static private int lastCreatedLogId=0;
	
	/**
	 * currentLogId: id of the last logStream
	 */
	static private int lastLogId=-1;
	
	/**
	 * lastPrintStream: printStream used for the last print...
	 */
	static private PrintStream lastPrintStream; 
	
	/**
	 * lastPrintIncludeLn: is true if the last Print operation include a new line...
	 */
	static private boolean lastPrintIncludeLn = true; 
	
	/**
	 * Define the logName associated to the log trace.
	 */
	String logName;
	/**
	 * logId: int identifier for this logStream
	 */
	private int logId = lastCreatedLogId++;
	/**
	 * out: the internal PrintStream used to output the messages
	 */
	private PrintStream out;
	
	/**
	 * detailslevel: define the level of details 
	 */
	private int detailsLevel = Log.defaultFilterLevel;
	
	/**
	 * deep: define the tabulation deepness before a new print line;
	 */
	private int deep = 0;
	
	/**
	 * deepString: the string used for tabulations;
	 */
	private String deepString = "";

	/**
	 * header: the string used as header of each line;
	 */
	private String header = "";
	
	/**
	 * build an instance of LogStream. In this case, 
	 * The default output stream is the java.lang.System.out.
	 */
	public LogStream() {
		super(java.lang.System.out);
		this.out = java.lang.System.out;
	}
	
	/**
	 * build an instance of LogStream with a specific detail level. 
	 * The default output stream is the java.lang.System.out.
	 * @param level the level of details expected.
	 */
	public LogStream(int level) {
		super(java.lang.System.out);
		this.out = java.lang.System.out;
		this.detailsLevel = level;
	}

	/**
	 * build an instance of LogStream with a specific line header. 
	 * The default output stream is the java.lang.System.out.
	 * @param header the string used as line header.
	 */
	public LogStream(String logName) {
		super(java.lang.System.out);
		this.out = java.lang.System.out;
		if(logName!=null){
			this.logName = logName;
			this.header = "["+logName+"] ";
		} else {
			this.logName = "";
			this.header = "";
		}
	}

	/**
	 * build an instance of LogStream with a specific detail leve and 
	 * a specific line header. 
	 * The default output stream is the java.lang.System.out.
	 * @param level the level of details expected.
	 * @param header the string used as line header.
	 */
	public LogStream(int level, String logName) {
		super(java.lang.System.out);
		this.out = java.lang.System.out;
		this.detailsLevel = level;
		if(logName!=null){
			this.logName = logName;
			this.header = "["+logName+"] ";
		} else {
			this.logName = "";
			this.header = "";
		}
	}

	/**
	 * build an instance of LogStream from an PrintStream
	 * @param out the PrintStream used.
	 */
	public LogStream(PrintStream out){
		super(out);
		this.out = out;
	}

	/**
	 * build an instance of LogStream from an PrintStream, with a specific details 
	 * level.
	 * @param out the PrintStream used.
	 * @param level the level of details expected.
	 */
	public LogStream(PrintStream out, int level){
		super(out);
		this.out = out;
		this.detailsLevel = level;
	}

	/**
	 * build an instance of LogStream from an PrintStream an with a specif header
	 * @param out the PrintStream used.
	 * @param header the string used as line header.
	 */
	public LogStream(PrintStream out, String logName){
		super(out);
		this.out = out;
		if(logName!=null){
			this.logName = logName;
			this.header = "["+logName+"] ";
		} else {
			this.logName = "";
			this.header = "";
		}
	}

	/**
	 * build an instance of LogStream from an PrintStream, with a specific details
	 * level and a specific header.
	 * @param out the PrintStream used.
	 * @param level the level of details expected.
	 */
	public LogStream(PrintStream out, int level, String logName){
		super(out);
		this.out = out;
		this.detailsLevel = level;
		if(logName!=null){
			this.logName = logName;
			this.header = "["+logName+"] ";
		} else {
			this.logName = "";
			this.header = "";
		}
	}

	/**
	 * define a new filter level. the filter can be set to -1 to remove any
	 * log printing...
	 * @param level the assigned level
	 */
	public void setFilter(int level) {
		assert level>=-1 : "the level must be in range [-1...Integer.MAX-VALUE]";
		this.detailsLevel = level;
	}

	/**
	 * define a new header for the ouput log. 
	 * @param header the assigned header
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	
	/**
	 * enter add a new tabulation before any new printed line;
	 */
	public void enter() {
		this.enter(1);
	}
	
	/**
	 * enter(deepness) add "deepness" tabulations before any new printed line;
	 * @param deepness the number of tabulations added
	 */
	public void enter(int deepness) {
		assert deepness>=1 : "deepness must be a strict positive number";
		this.deep = this.deep + deepness;
		for(;deepness>0;deepness--)
			this.deepString = this.deepString + " ";
	}

	/**
	 * exit sub an existing tabulation before any new printed line;
	 */
	public void exit() {
		this.exit(1);
	}
	/**
	 * enter(deepness) add "deepness" tabulations before any new printed line;
	 * @param deepness the number of tabulations added
	 */
	public void exit(int deepness) {
		assert deepness<=1 : "deepness must be a strict positive number";
		assert this.deep >= deepness : "";
		this.deep = this.deep - deepness;
		for(;deepness>0;deepness--)
			this.deepString = this.deepString.substring(1);
	}

	/**
	 * print aString with the highest outpout level (i.e. 0) 
	 * @param aString the string supposed to be printed
	 */
	public void print(java.lang.String aString) {
		this.print(0,aString);
	}
	
	/**
	 * print aString if the associated level is lower of equal to the
	 * filter level.
	 * @param level an positive int defining the output level
	 * @param aString the string supposed to be printed
	 */
	public void print(int level, java.lang.String aString) {
		assert level>=0 : "log output with a negative level";
		if(aString==null)
			aString="null";
		if(willPrintLevel(level)){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			aString.replace(
					""+Character.LINE_SEPARATOR,
					""+Character.LINE_SEPARATOR+this.header+this.deepString);
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.print(aString);
			LogStream.lastPrintIncludeLn = false;
		} 
	}
	
	/**
	 * print aBoolean value with the highest outpout level (i.e. 0) 
	 * @param aBoolean the boolean value supposed to be printed
	 */
	public void print(boolean aBoolean) {
		this.print(0,aBoolean);
	}

	/**
	 * print anBoolean value if the associated level is lower of equal to the
	 * filter level.
	 * @param level an positive int defining the output level
	 * @param anBoolean the boolean value supposed to be printed
	 */
	public void print(int level, boolean aBoolean) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.print(aBoolean);
			LogStream.lastPrintIncludeLn = false;
		} 
	}
	
	/**
	 * print aByte value with the highest outpout level (i.e. 0) 
	 * @param aByte the byte value supposed to be printed
	 */
	public void print(byte aByte) {
		this.print(0,aByte);
	}
	
	/**
	 * print aByte value if the associated level is lower of equal to the
	 * filter level.
	 * @param level an positive int defining the output level
	 * @param aByte the byte value supposed to be printed
	 */
	public void print(int level, byte aByte) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.print(aByte);
			LogStream.lastPrintIncludeLn = false;
		} 
	}
	
	/**
	 * print aShort value with the highest outpout level (i.e. 0) 
	 * @param aShort the short value supposed to be printed
	 */
	public void print(short aShort) {
		this.print(0,aShort);
	}

	/**
	 * print aShort value if the associated level is lower of equal to the
	 * filter level.
	 * @param level an positive int defining the output level
	 * @param anInt the int value supposed to be printed
	 */
	public void print(int level, short aShort) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.print(aShort);
			LogStream.lastPrintIncludeLn = false;
		} 
	}

	/**
	 * print aChar value with the highest outpout level (i.e. 0) 
	 * @param aChar the Char supposed to be printed
	 */
	public void print(char aChar) {
		this.print(0,aChar);
	}

	/**
	 * print aChar value if the associated level is lower of equal to the
	 * filter level.
	 * @param level an positive int defining the output level
	 * @param aChar the char supposed to be printed
	 */
	public void print(int level, char aChar) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.print(aChar);
			LogStream.lastPrintIncludeLn = false;
		} 
	}

	/**
	 * print anInt value with the highest outpout level (i.e. 0) 
	 * @param anInt the int value supposed to be printed
	 */
	public void print(int anInt) {
		this.print(0,anInt);
	}

	/**
	 * print anInt value if the associated level is lower of equal to the
	 * filter level.
	 * @param level an positive int defining the output level
	 * @param anInt the int value supposed to be printed
	 */
	public void print(int level, int anInt) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.print(anInt);
			LogStream.lastPrintIncludeLn = false;
		} 
	}
	
	/**
	 * print aFloat value with the highest outpout level (i.e. 0) 
	 * @param aFloat the float value supposed to be printed
	 */
	public void print(float aFloat) {
		this.print(0,aFloat);
	}

	/**
	 * print aFloat value if the associated level is lower of equal to the
	 * filter level.
	 * @param level an positive int defining the output level
	 * @param aFloat the float value supposed to be printed
	 */
	public void print(int level, float aFloat) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.print(aFloat);
			LogStream.lastPrintIncludeLn = false;
		} 
	}
	
	/**
	 * print aLong value with the highest outpout level (i.e. 0) 
	 * @param aLong the long value supposed to be printed
	 */
	public void print(long aLong) {
		this.print(0,aLong);
	}

	/**
	 * print aLong value if the associated level is lower of equal to the
	 * filter level.
	 * @param level an positive int defining the output level
	 * @param aLong the long value supposed to be printed
	 */
	public void print(int level, long aLong) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.print(aLong);
			LogStream.lastPrintIncludeLn = false;
		} 
	}

	/**
	 * print aDouble value with the highest outpout level (i.e. 0) 
	 * @param aDouble the double value supposed to be printed
	 */
	public void print(double aDouble) {
		this.print(0,aDouble);
	}

	/**
	 * print aDouble value if the associated level is lower of equal to the
	 * filter level.
	 * @param level an positive int defining the output level
	 * @param aDouble the double value supposed to be printed
	 */
	public void print(int level, double aDouble) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.print(aDouble);
			LogStream.lastPrintIncludeLn = false;
		} 
	}
	
	/**
	 * print aString and a new line with the highest outpout level (i.e. 0) 
	 * @param aString the string supposed to be printed
	 */
	public void println(java.lang.String aString) {
		this.println(0, aString);
	}
	
	/**
	 * print aString and a new line if the associated level is lower or equal 
	 * to the filter level.
	 * @param level an positive int defining the output level
	 * @param aString the string supposed to be printed
	 */
	public void println(int level, java.lang.String aString) {
		assert level>=0 : "log output with a negative level";
		if(aString==null)
			aString="null";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if (aString == "") {
				this.out.println(aString);
			} else {
			aString.replace(
					""+Character.LINE_SEPARATOR,
					""+Character.LINE_SEPARATOR+this.deepString);
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header + this.deepString);
			this.out.println(aString);
			}
			LogStream.lastPrintIncludeLn = true;
		} 
	}
	
	/**
	 * print aBoolean value and a new line with the highest output level (i.e. 0) 
	 * @param aBoolean the boolean value supposed to be printed
	 */
	public void println(boolean aBoolean) {
		this.println(0,aBoolean);
	}

	/**
	 * print anBoolean value and a new line if the associated level is lower or 
	 * equal to the filter level.
	 * @param level an positive int defining the output level
	 * @param anBoolean the boolean value supposed to be printed
	 */
	public void println(int level, boolean aBoolean) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId))){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.println(aBoolean);
			LogStream.lastPrintIncludeLn = true;
		} 
	}
	
	/**
	 * print aByte value and a new line with the highest outpout level (i.e. 0) 
	 * @param aByte the byte value supposed to be printed
	 */
	public void println(byte aByte) {
		this.println(0,aByte);
	}
	
	/**
	 * print aByte value and a new line if the associated level is lower or equal
	 * to the filter level.
	 * @param level an positive int defining the output level
	 * @param aByte the byte value supposed to be printed
	 */
	public void println(int level, byte aByte) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId))){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.println(aByte);
			LogStream.lastPrintIncludeLn = true;
		} 
	}
	
	/**
	 * print aShort value and a new line with the highest outpout level (i.e. 0) 
	 * @param aShort the short value supposed to be printed
	 */
	public void println(short aShort) {
		this.println(0,aShort);
	}

	/**
	 * print aShort value and a new line if the associated level is lower or equal
	 * to the filter level.
	 * @param level an positive int defining the output level
	 * @param anInt the int value supposed to be printed
	 */
	public void println(int level, short aShort) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId))){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.println(aShort);
			LogStream.lastPrintIncludeLn = true;
		} 
	}

	/**
	 * print aChar value and a new line with the highest outpout level (i.e. 0) 
	 * @param aChar the Char supposed to be printed
	 */
	public void println(char aChar) {
		this.println(0,aChar);
	}

	/**
	 * print aChar value and a new line if the associated level is lower or equal
	 * to the filter level.
	 * @param level an positive int defining the output level
	 * @param aChar the char supposed to be printed
	 */
	public void println(int level, char aChar) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId))){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.println(aChar);
			LogStream.lastPrintIncludeLn = true;
		} 
	}

	/**
	 * print anInt value and a new line with the highest outpout level (i.e. 0) 
	 * @param anInt the int value supposed to be printed
	 */
	public void println(int anInt) {
		this.println(0,anInt);
	}

	/**
	 * print anInt value with a new line if the associated level is lower or equal
	 * to the filter level.
	 * @param level an positive int defining the output level
	 * @param anInt the int value supposed to be printed
	 */
	public void println(int level, int anInt) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId))){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.println(anInt);
			LogStream.lastPrintIncludeLn = true;
		} 
	}
	
	/**
	 * print aFloat value and a new line with the highest outpout level (i.e. 0) 
	 * @param aFloat the float value supposed to be printed
	 */
	public void println(float aFloat) {
		this.print(0,aFloat);
	}

	/**
	 * print aFloat value and a new line if the associated level is lower or equal
	 * to the filter level.
	 * @param level an positive int defining the output level
	 * @param aFloat the float value supposed to be printed
	 */
	public void println(int level, float aFloat) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId))){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.println(aFloat);
			LogStream.lastPrintIncludeLn = true;
		} 
	}
	
	/**
	 * print aLong value and a new line with the highest outpout level (i.e. 0) 
	 * @param aLong the long value supposed to be printed
	 */
	public void println(long aLong) {
		this.println(0,aLong);
	}

	/**
	 * print aLong value and a new line if the associated level is lower or equal
	 * to the filter level.
	 * @param level an positive int defining the output level
	 * @param aLong the long value supposed to be printed
	 */
	public void println(int level, long aLong) {
		assert level>=0 : "log output with a negative level";
		if(level<=this.detailsLevel){
			if((LogStream.lastLogId!=-1)&&((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId))){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.println(aLong);
			LogStream.lastPrintIncludeLn = true;
		} 
	}

	/**
	 * print aDouble value and a new line with the highest outpout level (i.e. 0) 
	 * @param aDouble the double value supposed to be printed
	 */
	public void println(double aDouble) {
		this.println(0,aDouble);
	}

	/**
	 * print aDouble value and a new line if the associated level is lower or
	 * equal to the filter level.
	 * @param level an positive int defining the output level
	 * @param aDouble the double value supposed to be printed
	 */
	public void println(int level, double aDouble) {
		assert level>=0 : "log output with a negative level";
		if(willPrintLevel(level)){
			if((LogStream.lastLogId!=-1)&&(this.logId!=LogStream.lastLogId)){
				LogStream.lastPrintStream.println();
				LogStream.lastLogId=this.logId;
				LogStream.lastPrintStream=this.out;
			}
			if(LogStream.lastPrintIncludeLn)
				this.out.print(this.header+this.deepString);
			this.out.println(aDouble);
			LogStream.lastPrintIncludeLn = true;
		} 
	}
	
	/**
	 * print a new line with the highest output level (i.e. 0) 
	 */
	public void println() {
		this.println(0,"");
		LogStream.lastPrintIncludeLn = true;
	}

	/**
	 * Print the class, name and line of the caller method.
	 */
	public void printCurrentMethod() {
		this.printCurrentMethod(0);
	}

	/**
	 * Print the class, name and line of the caller method.
	 * @param level an positive int defining the output level
	 */
	public void printCurrentMethod(int level) {
		StackTraceElement aFrame[]=Thread.currentThread().getStackTrace();
		this.print(level,aFrame[3].getClassName()+"."+aFrame[3].getMethodName()+" in file \""+aFrame[3].getFileName()+"\" line "+aFrame[3].getLineNumber());
	}

	/**
	 * Print (with a new line) the class, name and line of the caller method.
	 */
	public void printCurrentMethodln() {
		this.printCurrentMethodln(0);
	}

	/**
	 * Print (with a new line) the class, name and line of the caller method.
	 * @param level an positive int defining the output level
	 */
	public void printCurrentMethodln(int level) {
		StackTraceElement aFrame[]=Thread.currentThread().getStackTrace();
		if(LogStream.lastPrintIncludeLn)
			this.out.print(this.header+this.deepString);
		this.println(level,aFrame[3].getClassName()+"."+aFrame[3].getMethodName()+" in file \""+aFrame[3].getFileName()+"\" line "+aFrame[3].getLineNumber());
		LogStream.lastPrintIncludeLn = false;
	}

	/**
	 * retrun true if a print with level lvl will be printed by the logStream
	 * @param lvl
	 * @return 
	 */
	public boolean willPrintLevel(int lvl) {
		return lvl<=this.detailsLevel;
	}

	/**
	 * returns current output stream.
	 * @return
	 */
	public PrintStream getOut() {
		return out;
	}

	/**
	 * sets output stream
	 * @param out
	 */
	public void setOut(PrintStream out) {
		this.out = out;
	}

}
