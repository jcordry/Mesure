package tools.profiler;

import java.io.File;

import lib.main.ResourceException;

public class Resources extends lib.main.Resources {
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
  
  /** The XML file containing the raw results. */
  private static File rawResults;
  
  /**
   * Returns the XML file containing the raw results.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getRawResults() {
    return rawResults;
  }
  
  /**
   * Builds the XML file containing the raw results.
   * 
   * @param path the path of the XML file containing the raw results.
   * @throws <tt>FileNotFoundException</tt> exception if the specified file
   *         does not exist.
   */
  public static void setRawResults(String path) throws ResourceException {
    rawResults = exists(path);
  }
  
  /** The XML file containing the extracted results. */
  private static File extractedResults;
  
  /**
   * Returns the XML file containing the extracted results.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getExtractedResults() {
    return extractedResults;
  }
  
  /**
   * Builds the XML file containing the extracted results.
   * @throws <tt>FileNotFoundException</tt> exception if the specified file
   *         does not exist.
   */
  public static void setExtractedResults(String path) throws ResourceException {
    extractedResults = exists(path);
  }
  
  /** The XML file containing the coefficients. */
  private static File xmlCoef;
  
  /**
   * Returns the XML file containing the coefficients.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getXMLCoef() {
    return xmlCoef;
  }
  
  /**
   * Builds the XML file containing the coefficients.
   * @throws <tt>FileNotFoundException</tt> exception if the specified file
   *         does not exist.
   */
  public static void setXMLCoef(String path) throws ResourceException {
    xmlCoef = exists(path);
  }
  
  /** The stylesheet for HTML rendering. */
  private static File stylesheet;
  
  /**
   * Returns the XSL file used for HTML rendering.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getStyleSheet() {
    return stylesheet;
  }
  
  /**
   * Builds the XSL file used for HTML rendering.
   * @throws <tt>FileNotFoundException</tt> exception if the specified file
   *         does not exist.
   */
  public static void setStyleSheet(String path) throws ResourceException {
    stylesheet = exists(path);
  }

  // Output Files
  /** The HTML file containing the results. */
  private static File htmlOutput;
	  
  /**
   * Returns the HTML file containing the results.
   * 
   * @return the <tt>File</tt> object associated to this file.
   */
  public static File getHTMLOutput() {
    return htmlOutput;
  }  
	  
  /**
   * Builds the HTML file containing the results.
   * 
   * @param path the path of the XML file to be built.
   */
  static void setHTMLOutput(String path) {
    htmlOutput = new File(tmp, path);
  }
  
  /** The database. */
  private static File database;
  
  /**
   * Returns the database.
   * 
   * @return the <tt>File</tt> object associated to this database. 
   */
  public static File getDatabase() {
    return database;
  }
  
  /**
   * Builds the database.
   * 
   * @param path the path of the database.
   */
  static void setDatabase(String path) {
    database = new File(tmp, path);
  }
 
  //============================================================================
  // Internal Resources
  //============================================================================
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
	// Libraries
	String libPath = root + LIB;
    exists(libPath + "jcommon-1.0.9.jar");
	exists(libPath + "jfreechart-1.0.5.jar");
	  
	// Input Files
	if (managerConfig == null)
	  managerConfig = new File(root + CONFIG + "manager/" + "ManagerConfig.xml");
	if (rawResults == null)
	  rawResults = new File(root + TMP + "results.xml");
	if (extractedResults == null)
	  extractedResults = new File(root + TMP + "extracted.xml");
	if (xmlCoef == null)
	  xmlCoef = new File(root + CONFIG + "profiler/" + "coefs.xml");
	if (stylesheet == null)
	  stylesheet = new File(root + CONFIG + "profiler/" + "profiler.xsl");
	
	// Output Files
	if (htmlOutput == null)
	  htmlOutput = new File(tmp,"results.html"); 
	if (database == null)
	  database = new File(tmp,"fusion_db.xml");
    //=========================================================================
	// Internal Resources
	//=========================================================================
    // None
  }
	
  //============================================================================
  // Default HTML output options
  //============================================================================	
  /**
   * The language of the output file.
   */
  private static String language = "en";
    
  /** 
   * The kind of chart used for the mark.
   */
  private static String markStyle = "Spider";
    
  /** 
   * The kind of chart used for the application domains.
   */
  private static String domainStyle = "Bar";
    
  //============================================================================
  // Setters / Getters
  //============================================================================	
       
  /**
   * Sets the language used in the output file.
   * @param language The new language.
   */
  public static void setLang(String lang) {
    language = lang;
  }
	
  /**
   * Sets the kind of chart used for the marks.
   * @param style The new style.
   */
  public static void setMarkStyle(String style) {
    markStyle = style;
  }
	
  /**
   * Sets the kind of chart used for the domains.
   * @param style The new style.
   */
  public static void setDomainStyle(String style) {
    domainStyle = style;
  }
	
  /**
   * @return The langage used in the output file.
   */
  public static String getLanguage() {
    return language;
  }
	
  /**
   * @return The kind of chart used for the marks.
   */
  public static String getMarkStyle() {
    return markStyle;
  }
	
  /**
   * @return The kind of chart used for the domains.
   */
  public static String getDomainStyle() {
    return domainStyle;
  }
}
