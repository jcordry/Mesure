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
 * $HeadURL: svn+ssh://meunier@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 8 decembre 2006
 * Author: POPS 
 * $LastChangedRevision: 35 $
 * $LastChangedDate: 2006-09-29 17:10:44 +0200 (ven., 29 sept. 2006) $
 * $LastChangedBy: meunier $
 */

package tools.filter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Writer;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import lib.log.Log;
import lib.xml.test_result.*;

/**
 * This tool helps synthetizing raw results coming from the Manager tool.
 */
public class Main extends lib.main.Main {
	
  /** The XML root container. */	
  private static Bench bench;
  private static boolean filter;
	
  /**
   *  Reference Cases and Test Cases default filtered case number (in percentage).
   */
  private static Double referenceCasesFilterPercent = new Double(10.0);
  private static Double testCasesFilterPercent = new Double(10.0);

	
	/**
	 * Parses the -rcp option specifying a filtering percentage for reference cases.
	 * 
	 * @param args the buffer containing the value for this option.
	 */
	public static void processRefCasePercent(String[] args) throws FilterException {
		assert (args!=null && args.length==1) : 
			"option -rcp (or -refCasePerc) requires a percentage value. default:"+referenceCasesFilterPercent;
		Log.log[Resources.getVerboseFilter()].println(4,"Set filtering percentage for reference cases to: " + args[0]);
		referenceCasesFilterPercent = new Double(args[0]);
	}

	/**
	 * Parses the -tcp option specifying a filtering percentage for test cases.
	 * 
	 * @param args the buffer containing the value for this option.
	 */
	public static void processTestCasePercent(String[] args) throws FilterException {
		assert (args!=null && args.length==1) : 
			"option -tcp (or -testCasePerc) requires a percentage value. default:"+testCasesFilterPercent;
		Log.log[Resources.getVerboseFilter()].println(4,"Set filtering percentage for test cases to: " + args[0]);
		testCasesFilterPercent = new Double(args[0]);
	}

	/**
	 * Parses the -i option specifying an XML raw results file to be used.
	 * 
	 * @param args the buffer containing the value for this option.
	 */
	public static void processInput(String[] args) throws FilterException {
		assert (args!=null && args.length==1) : 
			"option -i (or -input) requires a path for the XML input file.";
		Log.log[Resources.getVerboseFilter()].println(4,"Loading XML file : " + args[0]);
		try {
		  Resources.setInputFile(args[0]);
		} catch (Exception e) {
		  throw new FilterException(e);
		}
	}
	
	/**
	 * Parses the -o option specifying an XML target file to be used.
	 * 
	 * @param args the buffer containing the value for this option.
	 */
	public static void processOutput(String[] args) throws FilterException {
		assert (args!=null && args.length==1) : 
			"option -o (or -input) requires a path for the XML output file.";
		Log.log[Resources.getVerboseFilter()].println("Saving to XML file : " + args[0]);
		Resources.setOutputFile(args[0]);
	}

	/**
	 * Parses the -f option specifying the filter activation action.
	 * 
	 * @param args the buffer containing the value for this option.
	 * @throws FilterException
	 */
	public static void processFilter(String[] args) throws FilterException {
		assert (args!=null && args.length==0) : 
			"option -f (or -filter) requires nothing.";
    	filter = true;
	}
	
	/**
	 * @param arg
	 * @throws FilterException
	 */
	private static void saveFilterResultFile() throws FilterException {
		try {
			Writer xmlWriter = new FileWriter(Resources.getOutputFile());
			bench.marshal(xmlWriter);
			xmlWriter.close();
		} catch (Exception e) {
			throw new FilterException(e);
		}		

	}
	
	/**
	 * Parses the -p option printing knowledge database.
	 * 
	 * @param <b>args</b>
	 */
	public static void processPrint(String[] args) {
		assert (args!=null && args.length==0) : 
			"option -p (or -print).";
	
	}
	
    /**
     * Parses the XML file resulting from the Manager tool.
     * @param args
     * @throws FilterException
     */
    private static void loadTestResultFile() throws FilterException {
    	FileInputStream fis = null;
    	InputStreamReader reader = null;
    	try {
    		fis = new FileInputStream(Resources.getInputFile());
    	} catch (FileNotFoundException e) {
    		throw new FilterException(e);
    	}
		reader = new InputStreamReader(fis);
    	try {
        	bench = Bench.unmarshal(reader);
    	} catch (MarshalException e) {
    		throw new FilterException (e);
    	} catch (ValidationException e) {
    		throw new FilterException (e);
    	}

    }
    
    /**
     * @param vec
     * @return
     */
    private static double computeAverage(double[] vec) {
		// compute avg of ref cases
		double avgAdd = 0;
		for (double l : vec) {
			avgAdd += l;
		}
		return avgAdd / vec.length;
    }
    
    /**
     * @param data
     * @return
     */
    private static double computeStdError(double[] data) {
    	double avg = computeAverage(data);
    	
    	double errAdd = 0;
    	for (int i=0; i<data.length; i++) {
    		double l = data[i];
    		double tsqu = (l-avg)*(l-avg);
			errAdd += tsqu;
		}
    	return Math.sqrt(errAdd/data.length);
    }

    /**
     * Finds the index of the farest sample (from average), also fills errs arrays with distance
     * to average.
     * @param data
     * @param avg
     * @param errs
     * @return
     */
    private static int findFarestSample(double[] data, double avg, double[] errs) {
    	int worstIndex = 0;
    	double worst = Math.abs(data[0] - avg);
    	errs[0] = worst;
    	
    	// fills errors array
    	for (int i=1; i<data.length; i++) {
    		double t = Math.abs(data[i] - avg);
    		errs[i] = t;
    		if (t > worst) {
    			worst = t;
    			worstIndex = i;
    		}
    	}
    	return worstIndex;
    }
    
    /**
     * @param tr
     * @return
     */
    private static double[] buildTimeArray(TestResult tr) {
		double[] timeArray = new double[tr.getTimeMeasureCount()];
		for (int i=0; i<tr.getTimeMeasureCount(); i++) {
			timeArray[i] = tr.getTimeMeasure(i).getMeasure();
		}
		return timeArray;
    }
    
    /**
     * @param tr
     * @param filteredNum
     */
    private static void removeWorstErr(TestResult tr, int filteredNum) {
		double[] timeArray = buildTimeArray(tr);
		Log.log[Resources.getVerboseFilter()].println(4,"err: " + computeStdError(timeArray) + " over " + timeArray.length + " samples");

		for (int i = 0; i < filteredNum; i++) {

			// removing 10% farest from average(stderror-wise) samples
			double[] errs = new double[timeArray.length];
			int maxIndex = findFarestSample(timeArray, tr.getAvg(), errs);
			
//			Arrays.sort(errs);
//			for (double d : errs) System.out.println(d);
			
			double t = timeArray[maxIndex] - tr.getAvg();
			tr.removeTimeMeasure(tr.getTimeMeasure(maxIndex));

			timeArray = buildTimeArray(tr);
			Log.log[Resources.getVerboseFilter()].println(4,"err: " + computeStdError(timeArray) + " after removing " + t*t + " at index " + maxIndex);
		}

    }
    /**
     * @param bench
     * @param refCasePerc
     * @param testCasePerc
     */
    private static void filterTestResult(Bench bench, double refCasePerc, double testCasePerc) {
    	for (int testIndex=0; testIndex<bench.getTestCount(); testIndex++) {
    		Test test = bench.getTest(testIndex);
    		
    		for (int index=0; index < test.getTestCaseCount(); index++) {
    			TestCase ct = test.getTestCase(index);
    			Log.log[Resources.getVerboseFilter()].println(4,"Filtering out " + testCasePerc + "% of name=\"" + ct.getName() + "\", reference=" + ct.getReferenceName());
    			TestResult tr = ct.getTestResult();
    			removeWorstErr(tr, (int)(tr.getTimeMeasureCount()*testCasePerc/100.0));
    		}
		}
    }
    
    /**
	 * @param args
	 */
	public static void main(String[] args) {
	  tool = "filter";
	  options = new String[][]{
	    {"i", "input", "1", "Populate the database with the XML result file given as argument. "},
		{"f", "filter", "0", "Filter input cases."},
		{"rcp", "refCasePercent", "1", "Reference Case Percentage"},
		{"tcp", "testCasePercent", "1", "Test Case Percentage"},
		{"p", "print", "0", "Prints knowledge base out."},
		{"o", "output", "1", "Output filtered results to TestResults'format XML file given as argument."}
	  };
	  lib.main.Main.main(args);
	  try {
	    loadTestResultFile();
	    if (filter)
	      filterTestResult(bench,referenceCasesFilterPercent,testCasesFilterPercent);
	    saveFilterResultFile();
	  } catch (FilterException e) {
	    e.printStackTrace();
		Log.fatal(e.getMessage());
	  }
	}

}
