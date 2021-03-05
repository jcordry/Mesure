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
 * $HeadURL: $
 * Created: 1 septembre 2006
 * Author: POPS
 * $LastChangedRevision: 23 $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

import java.io.File;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.NumberFormatException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import lib.log.Log;

/**
 * Main is an easy way to deal with the String Array from the 
 * public static void Main (String [] args) of a tool.
 */
public class Main {
	private final static String[][] initialConfig = { 
	  {"h"   ,"help"         ,"0","print this help."},
	  {"l" ,"log"       ,"1","The log file. " +
		"If not specified, the \"tmp\\log.txt\" file is used."},
	  {"w"   ,"warning"      ,"1","with <level>, set a filter level for the warning log. <level> must be an int or \"no\" (no warning filter) or \"all\" (filter anything)."},
	  {"v"   ,"verbose"      ,"0","display tools logs on the default output."},
	  {"vl"  ,"verboseLevel" ,"1","with <level>, set a filter level for the logs output. level must be an int or \"no\" (no filter) or \"all\" (filter anything)."},
	  {"vf"  ,"verboseFilter","1","<logName:filterLevel>, set a filter level for the logs output. filterLevel must be an int or \"no\" (no filter) or \"all\" (filter anything)."},
	  {"c"   ,"config"       ,"1","with <cfgName> selected the predefine <cfgName> config from the \"./config/<tools>.xml\" file."},
	  {"dc"  ,"displayConfig","0","display the existing configs from the \"./config/<tools>.xml\" file."},
	  {"cd"  ,"configDefault","0","short way to write \"-c default\" ."},
	};
	
	/**
	 * The tool name.
	 */
	protected static String tool;
	
	/** The tool options. */
	protected static String[][] options;
	
	/**
	 * a Map between shortForm option name and complet option name.
	 */
	private static java.util.Map<String, String> theCommandMap = new java.util.HashMap<String, String>();
	/**
	 * a Map between shortForm option name and complet option name.
	 */
	private static java.util.Map<String, String> theParamMap = new java.util.HashMap<String, String>();
	/**
	 * a Map between shortForm option name and the description map.
	 */
	private static java.util.Map<String, String> theDescriptionMap = new java.util.HashMap<String, String>();
	
	/**
	 * Description of the final parameters managed by the tool. 
	 */
	private final static String finalParamsDsc = "The installation path. By default, this path is set to \"..\".";
	
	/**
	 * size (in char) of the longest "shortForm" of the option strings
	 */
	private static int maxShortCmdLength;
	/**
	 * size (in char) of the longest "complet Form" of the option strings
	 */
	private static int maxLongCmdLength;
	
	/**
	 * addConfigEntry add a command line in the MainArgsProcessor.
	 * 
	 * @param configEntry
	 */
	private static void addConfigEntry(String[] configEntry){
		assert configEntry.length==4:"a Config Entry must be an String Array of 4 elements.";
		assert !theCommandMap.containsKey(configEntry[0]) : "The option \"-"+configEntry[0]+"\" (a.k.a. \""+configEntry[1]+"\" cannot be added because the option \"-"+configEntry[0]+"\" (a.k.a \"-"+theCommandMap.get(configEntry[0])+") is already declared.";
		assert !theCommandMap.containsValue(configEntry[1]) : "The option \"-"+configEntry[0]+"\" (a.k.a. \""+configEntry[1]+"\" cannot be added because the option \"-"+configEntry[0]+"\" (a.k.a \"-"+theCommandMap.get(configEntry[0])+") is already declared.";
		theCommandMap.put(configEntry[0],configEntry[1]);
		theParamMap.put(configEntry[1],configEntry[2]);
		theDescriptionMap.put(configEntry[1],configEntry[3]);
		if(!configEntry[0].equals(configEntry[1]))
			maxShortCmdLength = (configEntry[0].length()>maxShortCmdLength)?configEntry[0].length():maxShortCmdLength;
		maxLongCmdLength = (configEntry[1].length()>maxLongCmdLength)?configEntry[1].length():maxLongCmdLength;
	}

	/**
	 * The processArgs method parse the args string array.
	 * According to the specified configuration (form the MainArgsProcessor 
	 * constructor), the processArgs method call specific methods on "this". 
	 * method are named public void process<OptionName>(String[] param).
	 * 
	 * @param args the string array to process
	 */
	private static void processArgs(String[] args) {
		Log.out.println("Processing arguments...");
		String[] aStringArray = new String[0];
		boolean noOptions = true;
		for (int i = 0; i < args.length; i++) {
			if (args[i].length() >= 1) {
				// is it an option
				if (args[i].charAt(0) == '-') {
					String optionName;
					String optionFullName;
					String paramString;
					int nbParam;
					String[] optionArgs;
					optionName = args[i].substring(1, args[i].length());
					optionFullName = theCommandMap.get(optionName);
					if (optionFullName == null)
						optionFullName = optionName;
					paramString = theParamMap.get(optionFullName);
					if (paramString == null)
						Log.fatal("unknow option \"-" + optionName
								+ "\". try -help option.");
					nbParam = Integer.parseInt(paramString);
					optionArgs = new String[nbParam];
					for (int j = 0; j < nbParam; j++) {
						i++;
						if (i >= args.length)
							Log.fatal("option \""+args[i-1]+ "\" need more arguments. try -help option.");
						optionArgs[j] = args[i];
					}
					noOptions=false;
					callBackSetOption(optionFullName, optionArgs);
				} else {
					// process all nexted strings as the main arguments
					aStringArray = new String[args.length-i];
					for(int j=0;i<args.length;i++,j++) 
						aStringArray[j]=args[i];
				}
			}
		}
		if (noOptions)
		  processNoOption();
		processFinalArgs(aStringArray);
	}
	
	/**
	 * This method is called when no option are processed.
	 * the default comportement load is to process a '-cd'
	 */
	private static void processNoOption() {
	    Log.warning(tool + ": no option specified, I fall back to default configuration (-cd).");
	    processConfigDefault(new String[0]);
	}
	
	/**
	 * Call callBackMethod named "set<optionName>" with String[] args as
	 * parameter.
	 * 
	 * @param optionFullName
	 * @param args
	 */
	private static void callBackSetOption(String optionFullName, String[] args) {
		java.lang.reflect.Method aMethod = null;
		Class[] descriptor = {String[].class};
		String convertedName;
		Class aClass = Resources.getMainClass();
		convertedName = Character.toUpperCase(optionFullName.charAt(0))+optionFullName.substring(1,optionFullName.length());
		// a good old lookup for the last overloaded method
		while(aClass!=null) {
			try {
				aMethod = aClass.getDeclaredMethod("process"+convertedName,descriptor);
				if(aMethod != null) break;
			} catch (SecurityException e) {
				Log.fatal("method \""+aClass.getName()+".process"+convertedName+"(String[] args)\" must be public.");
				return; //dirty ack for the java compiler
			} catch (NoSuchMethodException e) {
				aClass = aClass.getSuperclass();
				if(aClass==null)
					Log.fatal("undefined method \""+aClass.getName()+".process"+convertedName+"(String[] args)\".");
			}
		}
		try {
			Object [] argsArray = new Object[1];
			argsArray[0]=args;
			aMethod.invoke(null,argsArray);
		} catch (IllegalArgumentException e) {
			//unexpected Error
			Log.fatal(e);
		} catch (IllegalAccessException e) {
			Log.fatal("method \""+aClass.getName()+".process"+optionFullName+"(String[] args)\" must be public.");
		} catch (InvocationTargetException e) {
			Throwable aThrowable;
			// propagate the exception thrown by the process<Option> method
			aThrowable = e.getCause();
			if(aThrowable instanceof Error)
				throw (Error)aThrowable;
			else if(aThrowable instanceof RuntimeException)
					throw (RuntimeException)aThrowable;
			else if(aThrowable!=null)
				Log.fatal(aThrowable);
			else
				Log.fatal(e);	
		}
	}

	/**
	 * Default process for the Main args (without options entry)
	 * You must overload this method in your tool.
	 * 
	 * @param args the array of String (without "-<option> [param]").
	 */
	private static void processFinalArgs(String[] args){
	  if(args.length > 1) {
	    String errorMsg = "Arguments ";
		for(int i = 1; i < args.length; i++)
		  errorMsg += "\"" + args[i] + "\"" + ((i+1 < args.length)?",":"");
		  errorMsg+=" are not supported. Try -h to get more information.";
		  Log.fatal(errorMsg);
	  }
	  String installPath;
	  if (args.length == 0)
	    installPath = "..";
	  else 
		installPath = args[0];  
	  try {
	    Resources.loadResources(installPath,tool);
	  } catch (IOException e) {
	    Log.fatal(e.getMessage());
	  }		
	}
	
	/**
	 * Parses the -l option specifying the log file to be used.
	 * 
	 * @param args the buffer containing the value for this option.
	 */
	public static void processLog(String[] args) throws IOException {
	  assert (args!=null && args.length==1) : 
	      "option -l (or -log) requires a path for the log file.";
	  Resources.setLog(args[0]);
	}

	/**
	 * Call back method while the processArgs() parse a '-w' or a '-warning'.
	 * 
	 * @param args nothing
	 */
	public static void processWarning(String []args) {
		assert (args!=null && args.length==1) : "MainArgsProcessor failed to process the -o option !";
		if(args[0].equals("no")) {
			Log.dumpWarnings = true;
			Log.setWarningFilterLevel(Integer.MAX_VALUE);
		} else if(args[0].equals("all")) {
			Log.dumpWarnings = false;
			Log.setWarningFilterLevel(-1);
		} else {
			Log.dumpWarnings = true;
			try {
				Log.setWarningFilterLevel(Integer.parseInt(args[0]));
			} catch (NumberFormatException e) {
				Log.fatal("option -w (or -warning) requiered an integer value as arguments.\""+args[0]+"\" is not an Integer.");
			}
		}
	}
	/**
	 * Call back method while the processArgs() parse a '-h' or a '-help'.
	 * 
	 * @param args supposed to be a zero-length array...
	 */
	public static void processHelp(String []args) {
		Object[] aStringArray;
		assert (args!=null && args.length==0) : "MainArgsProcessor failed to process the -h option !";
		Log.out.println("\""+tool+"\" is a part of Mesure Project.");
		Log.out.println("  take a look on http://mesure.gforge.inria.fr");
		Log.out.println();
		// display usage of the tools
		if(finalParamsDsc==null)
			Log.out.println("Usage: "+tool+" {option ...}");
		else
			Log.out.println("Usage: "+tool+" {option ...} finalParams ...");
		Log.out.println("Supported options :");
		aStringArray = (theCommandMap.keySet().toArray());
		Arrays.sort(aStringArray);
		for(int i=0; i<aStringArray.length; i++) {
			String optionName = aStringArray[i].toString();
			String optionFullName = theCommandMap.get(optionName);
			String description = theDescriptionMap.get(optionFullName);
			if(description != null){
				String aWord;
				int headerSize;
				int currentPos;
				int j;
				headerSize = 0;
				currentPos = 0;
				Log.out.print(" -");
				headerSize+=2;
				Log.out.print(optionName);
				for(j=optionName.length();j<maxShortCmdLength;j++)
					Log.out.print(" ");
				headerSize+=maxShortCmdLength;
				j-=maxShortCmdLength+optionName.length();
				if(!optionName.equals(optionFullName)) {
					Log.out.print(" or -");
					headerSize+=5;
					Log.out.print(optionFullName);
					j=optionFullName.length();
				} else {
				}
				for(;j<maxLongCmdLength;j++)
					Log.out.print(" ");
				headerSize+=maxLongCmdLength;
				Log.out.print(":");
				headerSize++;
				currentPos=headerSize;
				aWord="";
				for(j=0;j<description.length();j++){
					char aChar;
					aChar=description.charAt(j);
					if(aChar == Character.LINE_SEPARATOR || currentPos==79) {
						Log.out.println();
						for(int k=0;k<headerSize;k++)
							Log.out.print(" ");
						if(aChar!=Character.LINE_SEPARATOR)
							aWord=aWord+aChar;
						currentPos=headerSize+aWord.length();
					} else { 
						if(aChar==' '|| aChar == Character.LINE_SEPARATOR) {
							Log.out.print(aWord);
							Log.out.print(" ");
							aWord="";
						} else
							aWord=aWord+aChar;
						currentPos++;
					}
				}
				Log.out.println(aWord);
			} 
		}
		Log.out.println();
		// display the final args description
		if(finalParamsDsc!=null) {
			displayString("finalParams ...: "+finalParamsDsc, 2);
			Log.out.println();
		}
		java.lang.System.exit(0);
	}

	/**
	 * print a clean output of the string aString 
	 * @param string
	 */
	private static void displayString(String aString, int headerSize) {
		String aWord;
		int currentPos;
		currentPos=0;
		aWord="";
		for(int i=0;i<aString.length();i++) {
			char aChar;
			aChar=aString.charAt(i);
			if(aChar == Character.LINE_SEPARATOR || currentPos==79) {
				Log.out.println();
				for(int k=0;k<headerSize;k++)
					Log.out.print(" ");
				if(aChar!=Character.LINE_SEPARATOR)
					aWord=aWord+aChar;
				currentPos=headerSize+aWord.length();
			} else { 
				if(aChar==' '|| aChar == Character.LINE_SEPARATOR) {
					Log.out.print(aWord);
					Log.out.print(" ");
					aWord="";
				} else
					aWord=aWord+aChar;
				currentPos++;
			}
		}
		Log.out.println(aWord);
		Log.out.println();
	}

	/**
	 * Call back method while the processArgs() parse a '-pc' or a '-printConfig'.
	 * this method read the "./misc/<toolsName>.xml" file and display all suitable
	 * entry suitable for the '-c' ('-config') command.
	 * 
	 * @param args supposed to be empty...
	 */
	public static void processDisplayConfig(String[] args) {
		ArrayList<String> configNameList;
		Map<String,String> name2DescriptionMap;
		File aFile;
		assert (args!=null && args.length==0) : "MainArgsProcessor failed to process the -dc option !";
		Log.out.println("\""+tool+"\" is a MESURE Tools.");
		Log.out.println("  take a look on http://mesure.gforge.inria.fr");
		Log.out.println(" ");
		Log.out.println("Preset configurations (for -c option) are :");
		try {
			aFile = Resources.getToolConfig();
			if(!aFile.exists()) {
				Log.fatal("unable to open the config File:\""+aFile+"\" ");
			}
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (aFile);		
			// normalize text representation
			doc.getDocumentElement ().normalize ();
			if(!doc.getDocumentElement().getNodeName().equals(tool))
				Log.fatal("The file \""+aFile+"\"doesnt specify a \""+tool+"\" record.");
			NodeList listOfConfigs = doc.getElementsByTagName("config");
			name2DescriptionMap = new HashMap<String,String>();
			configNameList = new ArrayList<String>();
			//read all avaluable entries
			for (int i = 0; i < listOfConfigs.getLength(); i++) {
				Node firstConfigNode = listOfConfigs.item(i);
				if (firstConfigNode.getNodeType() == Node.ELEMENT_NODE) {
					Element firstConfigElement = (Element) firstConfigNode;
					if (firstConfigElement.hasAttribute("name")) {
						String name = firstConfigElement.getAttribute("name").trim();
						String description = firstConfigElement.getAttribute("description").trim();
						configNameList.add(name);
						name2DescriptionMap.put(name,description);
					}
				}
			}
		} catch (SAXParseException err) {
			Log.err.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
			Log.err.println(" " + err.getMessage ());
			Log.fatal("xml processing failed.");
			return ;
		}catch (SAXException e) {
			Exception x = e.getException ();
			Log.fatal((x == null) ? e : x);
			return ;
		}catch (Throwable t) {
			//unexpceted exception
			Log.fatal(t);
			return ;
		}
		makeAPrettyPrint(configNameList.toArray(),name2DescriptionMap);
		java.lang.System.exit(0);
	}
	
	/**
	 * Call back method while the processArgs() parse a '-c' or a '-config'.
	 * this method read the "./misc/<toolsName>.xml" file and process all
	 * tag of the associated config as an option set by the main.
	 * 
	 * @param args an array containing a String : the selected config
	 */
	public static void processConfig(String[] args) {
		String configName;
		assert (args != null && args.length == 1) : "MainArgsProcessor failed to process the -c option !";
		configName = args[0];
		try {
			boolean configFind=false;
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(Resources.getToolConfig());
			// normalize text representation
			doc.getDocumentElement().normalize();
			// System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());

			NodeList listOfConfigs = doc.getElementsByTagName("config");
			for (int s = 0; s < listOfConfigs.getLength(); s++) {
				Node aConfigNode = listOfConfigs.item(s);
				if (aConfigNode.getNodeType() == Node.ELEMENT_NODE) {
					Element firstConfigElement = (Element) aConfigNode;
					if (firstConfigElement.hasAttribute("name")) {
						String name = firstConfigElement.getAttribute("name").trim();
						if (name.equals(configName)) {
							processOptionConfigEntry(firstConfigElement);
							configFind=true;
							break;
						}
					}
				}
			}//end of for loop with s var
			if(!configFind) {
				Log.fatal("The config \""+configName+"\" is undefine in the file \""+Resources.getToolConfig()+"\". try -dc (a.k.a. -displayConfig) to print the list of available configs...");
			}
		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();
			Log.fatal("Fail to read the config file...");
		} catch (IOException ioe) {
			Log.err.println(ioe);
			Log.fatal("Unable to open the config file ?");
		} catch (ParserConfigurationException e) {
			//unexpected exception ? 
			Log.err.println(e);
			Log.fatal("Unexpected exception...");
		}
	}

	/**
	 * Call back method while the processArgs() parse a '-dc' or a '-defaultConfig'.
	 * this method call the processConfig with "default" as parameter.
	 * 
	 * @param args a zero-length array of String
	 */
	public static void processConfigDefault(String [] args) {
		assert (args != null && args.length == 0) : "MainArgsProcessor failed to process the -c option !";
		args = new String[1];
		args[0]="default";
		processConfig(args);
	}
	
	/**
	 * make a pretty print for each entry of an Array associated with a 
	 * description define in a Map. 
	 * 
	 * @param aStringArray the element 
	 * @param aMap the linked description
	 */
	private static void makeAPrettyPrint(Object[] aStringArray, Map<String,String> aMap) {
		int maxStringSize=0;
		//Compute the size of the biggest string in the stringArray
		for(int i=0;i<aStringArray.length;i++){
			int stringLength = aStringArray[i].toString().length();
			maxStringSize = (maxStringSize<stringLength)?stringLength:maxStringSize;
		}
		//sort by name :
		java.util.Arrays.sort(aStringArray);
		//pretty print...
		for(int i=0;i<aStringArray.length;i++){
			String configName;
			String description;
			String aWord;
			int headerSize = maxStringSize + 2;
			int currentPos = 0;
			configName = aStringArray[i].toString();
			Log.out.print("  "+configName);
			for(int j=configName.length();j<maxStringSize;j++)
				Log.out.print(" ");
			Log.out.print(":");
			currentPos=headerSize;
			description=aMap.get(configName);
			aWord="";
			for(int j=0;j<description.length();j++){
				char aChar;
				aChar=description.charAt(j);
				if(aChar == Character.LINE_SEPARATOR || currentPos==79) {
					Log.out.println();
					for(int k=0;k<headerSize;k++)
						Log.out.print(" ");
					currentPos=headerSize+aWord.length();
				} 
				if(aChar==' ' || aChar == Character.LINE_SEPARATOR){
					Log.out.print(aWord);
					Log.out.print(" ");
					aWord="";
				} else {
					aWord = aWord+aChar;
				}
				currentPos++;
			}
			Log.out.println(aWord);
		}
	}
	
	/**
	 * process the elements of a xml Node.
	 * 
	 * @param aConfigNode
	 */
	private static void processOptionConfigEntry(Node aConfigNode) {
		Node currentNode;
		currentNode = aConfigNode.getFirstChild();
		while(currentNode!=null) {
			// process option ? 
			if(currentNode.getNodeType()==Node.ELEMENT_NODE){
				String[] args;
				args = new String[0];
				Node aChildNode = currentNode.getFirstChild();
				// process parameter option ? 
				while(aChildNode!=null) {
					if(aChildNode.getNodeType()==Node.TEXT_NODE) {
						String [] newArgs;
						newArgs = new String[args.length+1];
						System.arraycopy(args,0,newArgs,0,args.length);
						String s = aChildNode.getNodeValue();
						newArgs[args.length]=aChildNode.getNodeValue();
						// ... replaced obsolete getTextContent();
						args=newArgs;
					}
					aChildNode = aChildNode.getNextSibling();
				}
				callBackSetOption(currentNode.getNodeName(),args);
			}
			currentNode = currentNode.getNextSibling();
		}
	}
	
	/**
	 * Call back method while the processArgs() parse a '-v' or a '-verbose'.
	 * this method read the "./misc/<toolsName>.xml" file and process all
	 * tag of the associated config as an option set by the main.
	 * 
	 * @param args an array containing a String : the selected config
	 */
	public static void processVerbose(String[] args){
		assert (args!=null && args.length==0) : "MainArgsProcessor failed to process the -vl option !";
		Log.setAGeneralFilterLevel(0);
	}

	/**
	 * Call back method while the processArgs() parse a '-vl' or a '-verboseLevel'.
	 * 
	 * @param args an array containing a String : the selected config
	 */
	public static void processVerboseLevel(String[] args){
		assert (args!=null && args.length==1) : "MainArgsProcessor failed to process the -vl option !";
		int level;
		if(args[0].equals("no")) {
			level = Integer.MAX_VALUE;
		} else if(args[0].equals("all")) {
			level = -1;
		} else {
			try {
				level = Integer.parseInt(args[0]);	
			} catch (NumberFormatException e) {
				Log.fatal("option -vl (or -verboselevel) requiered an integer value as arguments.\""+args[0]+"\" is not an Integer.");
				return;
			}
		}
		Log.setAGeneralFilterLevel(level);
	}
	
	/**
	 * Call back method while the processArgs() parse a '-vf' or a '-verboseFilter'.
	 * 
	 * @param args an array containing a String : the verbose filter
	 */
	public static void processVerboseFilter(String[] args) {
		assert (args!=null && args.length==1) : "MainArgsProcessor failed to process the -vl option !";
		String arg;
		String filterName;
		int filterLevel;
		int anInt;
		arg = args[0];
		// get the filterName
		anInt = arg.indexOf(":") ;
		if(anInt==-1)
			Log.fatal("Wrong format for the parameter of -vf (-verboseFilter) option a ':' character must be used to separate the name and the number. Try -h...");
		filterName = arg.substring(0,anInt);
		// get FilterLevel
		arg=arg.substring(anInt+1,arg.length());
		if(arg.equals("no")) {
			filterLevel = Integer.MAX_VALUE;
		} else if (arg.equals("all")) {
			filterLevel = -1;
		} else {
			try {
				filterLevel = Integer.parseInt(arg);	
			} catch (NumberFormatException e) {
				Log.fatal("option -vf (or -verboseFilter) requiered an integer value as second arguments.\""+args[0]+"\" is not an Integer.");
				return;
			}
		}
		Log.setFilterLevel(filterName,filterLevel);
	}
	
	/**
	 * Builds a new instance of the tool and configures it.
	 *  
	 * @param args the options to be passed to the tool.
	 */
	public static void main(String[] args) {
	  try {
	    Resources.loadResources("..",tool);
	  } catch (IOException e) {
	    e.printStackTrace();
		Log.fatal(e.getMessage());
	  }
	  if (options == null)
	    options = new String[0][0];
	  for(int i=0;i<initialConfig.length;i++)
	    addConfigEntry(initialConfig[i]);
	  for(int i=0;i<options.length;i++)
	    addConfigEntry(options[i]);	
	  processArgs(args);
	}
}
