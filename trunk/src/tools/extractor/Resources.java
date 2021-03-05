package tools.extractor;

import java.io.File;

import lib.main.ResourceException;

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
  /** The XML input file. */
  private static File inputFile;
  
  /**
   * Returns the extractor input file.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getInputFile() {
    return inputFile;
  }
  
  /**
   * Builds the extractor input file.
   * 
   * @param path the path of the extractor input file.
   * @throws <tt>FileNotFoundException</tt> exception if the specified file
   *         does not exist.
   */
  static void setInputFile(String path) throws ResourceException {
	inputFile = exists(path);
  }

  // Output Files
  /** The XML output file. */
  private static File outputFile;
  
  /**
   * Returns the extractor output file.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getOutputFile() {
    return outputFile;
  }  
  
  /**
   * Builds the extractor output file.
   * 
   * @param path the path of the XML file to be built.
   */
  static void setOutputFile(String path) {
	outputFile = new File(tmp, path);
  }
    
  //============================================================================
  // Internal Resources
  //============================================================================
  // None
  
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
	if (inputFile == null)
	  inputFile = new File(root + TMP + "filtered.xml");

	// Output Files
	if (outputFile == null)
	  outputFile = new File("extracted.xml");      
    //=========================================================================
	// Internal Resources
	//=========================================================================
	// None
  }
}
