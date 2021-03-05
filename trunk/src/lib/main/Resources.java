package lib.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.lang.reflect.Method;

import lib.log.Log;

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
 * Author: TL 
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: cpascal $
 */

/**
 * The tool resources. 
 */
public class Resources
{
  //============================================================================
  // External Resources
  //============================================================================	
  /** The installation folder. */
  protected static String root;
    
  // Libraries
  /** The relative path of the folder containing the libraries. */
  protected static final String LIB = "/lib/";
  
  // Executable Files
  /** The relative path of the folder containing the executable files. */
  private static final String BIN = "/bin/";

  // Configuration Files
  /** The relative path of the folder containing the configuration files. */
  protected static final String CONFIG = "/config/";
  
  /** The tool configuration file. */
  private static File toolConfig;
  
  /**
   * Returns the tool configuration file.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getToolConfig() {
    return toolConfig;
  }
  
  /**
   * Builds the tool configuration file.
   * 
   * @param path the path of the tool configuration file.
   * @throws <tt>FileNotFoundException</tt> exception if the specified file
   *         does not exist.
   */
  private static void setToolConfig(String path) throws ResourceException {
	toolConfig = exists(path);
  }
  
  // Temporary Files
  /** The relative path of the folder containing the temporary files. */
  protected static final String TMP = "/tmp/";
  
  /** The folder containing the temporary files. */
  protected static File tmp;
  
  /**
   * Return the temporary folder.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getTmpFolder() {
    return tmp;
  }
  
  // Log File
  /** The generic output stream used to write to the log file. */  
  private static PrintStream out;

  /** The specific output stream used to write to the log file. */ 
  private static FileOutputStream fos;
  
  /** The log file. */
  private static File log;
  
  /**
   * Builds the log file.
   * 
   * @param path the path of the log file to be built.
   */
  static void setLog(String path) throws ResourceException {
    log = new File(tmp, path);
    try {
	  fos = new FileOutputStream(log);
    } catch (FileNotFoundException e) {
      throw new ResourceException(e);
    }
	out = new PrintStream(fos);
	lib.log.Log.setOut(out);
  }
    
  //============================================================================
  // Internal Resources
  //============================================================================
  // Main Class
  /** The main class. */
  private static Class main;
  
  /**
   * The verbose filter for this tool.
   */
  private static int verboseFilter;

  /**
   * Returns the verbose filter for this tool.
   * 
   * @return the verbose filter.
   */
  public static int getVerboseFilter() {
    return verboseFilter;
  }
  
  /**
   * Returns the main class. 
   * 
   * @return the <tt>Class</tt> object associated to the main class.
   */
  public static Class getMainClass() {
    return main;
  }
  
  /**
   * Loads useful resources.
   * 
   * @param installPath the installation path.
   * @param tool the tool name.
   * @throw <tt>IOException</tt> exception if some required resources
   *        are missing.
   */
  protected static void loadResources(String installPath, String tool) throws ResourceException
  {
	verboseFilter = Log.getASpecificLogEntry(new String(tool).toUpperCase());
	Log.log[verboseFilter].println(4,"Loading resources...");

	//=========================================================================
    // External Resources
	//=========================================================================  
	// Installation Path
	root = installPath;
	exists(root);
	
	// Libraries
	String libPath = root + LIB;
	exists(libPath);
	exists(libPath + "api21.jar");
	exists(libPath + "castor-1.0.3.jar");
	exists(libPath + "commons-logging-1.1.jar");
	exists(libPath + "xerces-J_1.4.0.jar");
	
	// Temporary Files
	String tmpPath = root + TMP;
	tmp = new File(tmpPath);
	if (!tmp.exists())
	  tmp.mkdir();
	
    // Log File
    if (log == null)
      /*setLog(tmpPath + "log.txt")*/;

	// Configuration Files
    if (!tool.endsWith("/"))
  	  tool = tool + "/";
	if (toolConfig == null)
	  setToolConfig(root + CONFIG + tool + "ToolConfig.xml");
	
    //=========================================================================
	// Internal Resources
	//=========================================================================
	try {
	  main = Class.forName("tools." + tool.replace('/','.').toLowerCase() + "Main");
	  Class resources = Class.forName("tools." + tool.replace('/','.').toLowerCase() + "Resources");
	  Method m = resources.getMethod("loadResources",
	      new java.lang.Class[]{String.class,String.class});
	  m.invoke(null,new Object[]{installPath,tool});
	} catch (Exception e) {
	  throw new ResourceException(e);
	}
  }
  
  /**
   * Checks that the specified file or directory exists.
   *
   * @param name the name of the file or directory.
   * @return the associated <tt>File</tt> object if the specified file or
   *         directory exists.
   * @throw <tt>FileNotFoundException</tt> exception otherwise.
   */
  protected static File exists(String name) throws ResourceException
  {
    File f = new File(name);
    if (!f.exists())
      throw new ResourceException(new FileNotFoundException(f.getAbsolutePath()));
    return f;
  }
}
