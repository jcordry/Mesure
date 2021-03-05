package tools.averagetool;

import java.io.File;
import java.util.Vector;

import lib.main.ResourceException;

public class Resources extends lib.main.Resources {
	
	/** The XML file containing the extracted results. */
	private static Vector<File> extractedResults = new Vector<File>();
	
	/**
	 * Returns the XML file containing the extracted results.
	 * 
	 * @return the <tt>File</tt> object associated to this file.
	 */
	public static Vector<File> getExtractedResults() {
		return extractedResults;
	}
	
	/**
	 * Builds the XML file containing the extracted results.
	 * @throws <tt>FileNotFoundException</tt> exception if the specified file
	 *         does not exist.
	 */
	public static void setExtractedResults(String path) throws ResourceException {
		extractedResults.add(exists(path));
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
	
	
	/** The XML file containing the coefficients. */
	private static File xmlOutput;
	
	/**
	 * Returns the XML file containing the coefficients.
	 * 
	 * @return the <tt>File</tt> object associated to this file.
	 */
	public static File getXMLOutput() {
		return xmlOutput;
	}
	
	/**
	 * Builds the XML file containing the coefficients.
	 * @throws <tt>FileNotFoundException</tt> exception if the specified file
	 *         does not exist.
	 */
	public static void setXMLOutput(String path) throws ResourceException {
		xmlOutput = new File(tmp,path);
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
		if (xmlCoef == null)
		  xmlCoef = new File(root + CONFIG + "profiler/" + "coefs.xml");
		
		// Output Files
		if (xmlOutput == null)
		  xmlOutput = new File(tmp,"coefs.xml");
		
		//=========================================================================
		// Internal Resources
		//=========================================================================
		// none
	}
}
