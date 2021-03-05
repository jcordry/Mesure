package tools.manager;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import lib.main.ResourceException;
import lib.util.Util;

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
 * $LastChangedRevision: 43 $
 * $LastChangedDate: $
 * $LastChangedBy: cpascal $
 */

/**
 * The test manager resources. 
 */
public class Resources extends lib.main.Resources
{
  //============================================================================
  // External Resources
  //============================================================================	
  // Input Files
  /** The manager configuration file. */
  private static File managerConfig;
  
  /**
   * Returns the manager configuration file.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getManagerConfig() {
    return managerConfig;
  }
  
  /**
   * Builds the manager configuration file.
   * 
   * @param path the path of the manager configuration file.
   * @throws <tt>FileNotFoundException</tt> exception if the specified file
   *         does not exist.
   */
  static void setManagerConfig(String path) throws ResourceException {
	managerConfig = exists(path);
  }

  // Output Files
  /** The XML file containing the results. */
  private static File xmlOutput;
  
  /**
   * Returns the XML file containing the results.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getXMLOutput() {
    return xmlOutput;
  }  
  
  /**
   * Builds the XML file containing the results.
   * 
   * @param path the path of the XML file to be built.
   */
  static void setXMLOutput(String path) {
	xmlOutput = new File(tmp, path);
  }
  
  /** The CSV file containing the results. */
  private static File csvOutput;
  
  /**
   * Returns the CSV file containing the results.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getCSVOutput() {
    return csvOutput;
  }  
  
  /**
   * Builds the XML file containing the results.
   * 
   * @param path the path of the XML file to be built.
   */
  static void setCSVOutput(String path) {
	csvOutput = new File(tmp, path);
  }
  
  //Temporary Files
  /** The relative path of the folder containing the temporary files. */
  static final String BENCHS = "benchs";
    
  //============================================================================
  // Internal Resources
  //============================================================================
  // CAP Files
  /** The relative path of the template CAP file. */
  private static final String TEMPLATE = 
      "benchs/lib/templates/javacard/templates.cap";
  
  /** The complete path of the template CAP file. */
  private static String template;

  /**
   * Returns the complete path of the template CAP file.
   * 
   * @returns the <tt>String</tt> object representing this path.
   */
  public static String getTemplate() {
    return template;
  }
  
  /**
   * Returns the complete path of a test configuration file.
   * 
   * @param configPath the relative path of a test configuration file.
   * @return the <tt>String</tt> object representing its complete path.
   * @throws <tt>IOException</tt> exception if a problem occurs when building
   *         the complete path.
   */
  public static String getTestConfig(String configPath) throws IOException {
	  File f = new File(tmp,configPath.substring(0,configPath.lastIndexOf("/")));
	  if (!f.exists())
	    f.mkdirs();  
	  return Util.Url2Path(Resources.class.getResource(
        "/" + configPath),false,f);
  }
  
  /**
   * Returns the complete path of a test CAP file.
   * 
   * @param CAPFilePath the relative path of a test CAP file.
   * @return the <tt>String</tt> object representing its complete path.
   * @throws <tt>IOException</tt> exception if a problem occurs when building
   *         the complete path.
   */
  public static String getCAPFile(String CAPFilePath) throws IOException {
	  URL url = Resources.class.getResource("/" + CAPFilePath);
	  // splitted to allow debugging.. url can be null.
	  if (url == null)
		  throw new IOException("Can't find file : " + "/" + CAPFilePath);
      // Temporary File
	  File f = new File(tmp,CAPFilePath.substring(0,CAPFilePath.lastIndexOf("/")));
	  if (!f.exists())
        f.mkdirs();
	  String s = Util.Url2Path(url, true, f);
	  return s;
  }
  
  /**
   * Loads useful resources.
   * 
   * @param installPath the installation path.
   * @param tool the tool name.
   * @throw <tt>IOException</tt> exception if some required resources
   *        are missing.
   */
  public static void loadResources(String installPath, String tool) throws ResourceException
  {
	//=========================================================================
    // External Resources
	//=========================================================================  
	// Input Files
	if (managerConfig == null)
	  setManagerConfig(root + CONFIG + tool + "ManagerConfig.xml");
	
	// Output Files
	if (xmlOutput == null)
	  xmlOutput = new File(tmp,"results.xml");
	if (csvOutput == null)
	  csvOutput = new File(tmp,"results.csv");
	      
    //=========================================================================
	// Internal Resources
	//=========================================================================
	try {
	  // CAP Files
	  File f = new File(tmp,TEMPLATE.substring(0,TEMPLATE.lastIndexOf("/")));
	  if (!f.exists())
	    f.mkdirs();  
	  template = Util.Url2Path(Resources.class.getResource("/" + TEMPLATE),true,f);
	} catch (Exception e) {
	  throw new ResourceException(e);
	}
  }
}
