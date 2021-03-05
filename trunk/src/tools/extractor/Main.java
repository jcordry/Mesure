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

package tools.extractor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import benchs.lib.templates.Util;

import tools.extractor.ast.ParseException;
import tools.extractor.ast.Solver;
import tools.extractor.ast.SolvingException;

import lib.chrono.TimeMeasure;
import lib.log.Log;
import lib.xml.extractor_result.ExtractorResult;
import lib.xml.extractor_result.Metric;
import lib.xml.extractor_result.Unit;
import lib.xml.test_result.Bench;
import lib.xml.test_result.Test;
import lib.xml.test_result.TestCase;


/**
 * This tool helps synthetizing raw results coming from the Manager tool.
 */
public class Main extends lib.main.Main {
	
  private static boolean resolve;

	class MeasureUnit {
		String name;
		// ExpTree tree;
		List<String> splitName;

		public MeasureUnit(String name, TestCase tc) {
			// TODO Auto-generated constructor stub
			this.name = name;
			this.splitName = splitName(name);
		}
	}
	Map<String, MeasureUnit> MUMap = new HashMap<String, MeasureUnit>();

	/**
	 * Returns a list of "/" separated words
	 * @return
	 */
	private List<String> splitName(String name) {
		String[] splitNameArray = name.split("[/+*]");
		return new ArrayList<String>(java.util.Arrays.asList(splitNameArray));
	}
	
	/**
	 * Maps storing reference case informations.
	 */
	private static Map<String, TestCase> inputTestBase = new HashMap<String, TestCase>();
	
	private static Map<String, TestCase> inputRefBase = new HashMap<String, TestCase>();
	
	private static Map<String, TimeMeasure> refAvgBase = new HashMap<String, TimeMeasure>();
	
	private static Map<String, String> missedTests = new HashMap<String, String>();

	/**
	 * Maps storings test case informations.
	 */
	private static Map<ArrayList<String>, Vector<TimeMeasure[]>> testBase = new HashMap<ArrayList<String>, Vector<TimeMeasure[]>>(); 
	// BJulien
	private static Map<ArrayList<String>, Vector<String>> benchedUnitBase = new HashMap<ArrayList<String>, Vector<String>>();  
	// EJulien
	
	/**
	 * Parses the -i option specifying an XML raw results file to be used.
	 * 
	 * @param args the buffer containing the value for this option.
	 */
	public static void processInput(String[] args) throws ExtractorException {
		assert (args!=null && args.length==1) : 
			"option -i (or -input) requires a path for the XML input file.";
		Log.log[Resources.getVerboseFilter()].println(4,"Populating data base using : " + args[0]);
		try {
		  Resources.setInputFile(args[0]);
		} catch (Exception e) {
		  throw new ExtractorException(e);
		}
	}
	
	
	/**
	 * Parses the -o option specifying an XML target file to be used.
	 * 
	 * @param args the buffer containing the value for this option.
	 */
	public static void processOutput(String[] args) throws ExtractorException {
		assert (args!=null && args.length==1) : 
			"option -o (or -input) requires a path for the XML output file.";
		Log.log[Resources.getVerboseFilter()].println(4,"Saving result data base using : " + args[0]);
		Resources.setOutputFile(args[0]);
	}
	
	
	/**
	 * Saves extractor synthetic results to XML file which name is given as argument.
	 * @throws ExtractorException
	 */
	private static void saveExtractorResultFile() throws ExtractorException {
		ExtractorResult xmlExtractorResult = new ExtractorResult();
		Metric extractorMetric = new Metric();
		xmlExtractorResult.addMetric(extractorMetric);
		
		Set<ArrayList<String>> keySet = testBase.keySet();
		
		Iterator<ArrayList<String>> itKeySet = keySet.iterator();
		while (itKeySet.hasNext()) {
			ArrayList<String> splitListName = itKeySet.next();
			Vector<TimeMeasure[]> tmVec = testBase.get(splitListName);
			String name = new String(splitListName.get(0));
			for (int i=1 ;i<splitListName.size(); i++)
				name += "/" + splitListName.get(i);
			
			
			System.out.println("Outputing : "  + name);
			TestCase tc = inputTestBase.get(name);
			System.out.println("reference is : " + tc.getReferenceName());

			TestCase refTc = null; 
			if (tc != null)
				  refTc = inputRefBase.get(tc.getReferenceName());
			//			  refTc = inputTestBase.get(tc.getReferenceName()); // is this a bug ?
			
			System.out.println("TC: " + tc);
			System.out.println("REF:" + refTc);
			
			Unit unit = new Unit();
			extractorMetric.addUnit(unit);
			
			// keeps reference in unit, for later link 
			unit.setReference(tc.getReferenceName());
			unit.setScript(refTc.getScript());
			if (unit.getScript().startsWith("scripts.jcapi")) {
				unit.setBenchedmode("api");
			} else
				unit.setBenchedmode("vm");
			
			unit.setName(name);
			if (refTc != null)
			  unit.setConfig("");
			
			if (tc != null)
			  unit.setResult((tc.getTestResult().getStatus()== scripts.templates.TestResult.PASSED) ? "SUCCESS" : "FAILED");
			System.out.println(name);
			
			lib.xml.extractor_result.Time t = new lib.xml.extractor_result.Time();
			TimeMeasure avgTm = computeTmVectorAverage(tmVec);
			t.setAvg(avgTm.getMeasure());
			double stdError = Math.sqrt(TimeMeasure.variance(tmVec.elementAt(0)).getMeasure()); 
			t.setStdError(stdError);
			t.setTrust(2.58 * stdError);
			t.setSampleSize(tmVec.elementAt(0).length);
			Log.log[Resources.getVerboseFilter()].println(4,tmVec.elementAt(0).length);
			unit.setTime(t);
		}
		
		Set<String> mkeySet = missedTests.keySet();
	
		Iterator<String> mitKeySet = mkeySet.iterator();
		while (mitKeySet.hasNext()) {
			String name = mitKeySet.next();
			System.out.println("Outputing : "  + name);
			
			TestCase tc = inputTestBase.get(name);
			TestCase refTc = null; 
			if (tc != null)
			  refTc = inputTestBase.get(tc.getReferenceName());
			
			System.out.println(refTc);
			System.out.println(tc);
			
			Unit unit = new Unit();
			extractorMetric.addUnit(unit);
			
			unit.setName(name);
			if (refTc != null)
			  unit.setConfig("");
			
			unit.setResult(missedTests.get(name));
			
			if (tc != null)
			  unit.setResult((tc.getTestResult().getStatus()== scripts.templates.TestResult.PASSED) ? "SUCCESS" : "FAILED");
			System.out.println(name);
			
			lib.xml.extractor_result.Time t = new lib.xml.extractor_result.Time();
			TimeMeasure avgTm = new TimeMeasure(0, TimeMeasure.NANOSECONDS);
			t.setAvg(avgTm.getMeasure());
			t.setStdError(0);
			t.setTrust(0);
			t.setSampleSize(0);
			Log.log[Resources.getVerboseFilter()].println(0);
			unit.setTime(t);
		}
		
		try {
			Writer xmlWriter = new FileWriter(Resources.getOutputFile());
			xmlExtractorResult.marshal(xmlWriter);
			xmlWriter.close();
		} catch (Exception e) {
			throw new ExtractorException(e);
		}		
		
		//
//		exMetric.setName(name);
		
	}
	
	
	private static void displayTmVec(int verboseLevel, Vector<TimeMeasure[]> tml) {
		Iterator<TimeMeasure[]> it = tml.iterator();
		
		while (it.hasNext()) {
			TimeMeasure[] tm = it.next();
			try {
				Log.log[Resources.getVerboseFilter()].print(verboseLevel, TimeMeasure.average(tm) + "/"+ TimeMeasure.stdError(tm) + " ");
			} catch (ArrayIndexOutOfBoundsException e) {}
//			Log.log[LOG].println(java.util.Arrays.toString(tm));
		}
		Log.log[Resources.getVerboseFilter()].println(verboseLevel, "");
	}
	/**
	 * Displays Base content in console.
	 */
	private static void printBases() {
		Log.log[Resources.getVerboseFilter()].println(-1, "");
		Log.log[Resources.getVerboseFilter()].println(-1, "---- Knowledge Base (avg/stderror)-----");
		// iterate on uncomplete KB and print things out.
		Set<ArrayList<String>> keySet = testBase.keySet();
		Iterator<ArrayList<String>> it = keySet.iterator();

		if (it.hasNext()) {
			while (it.hasNext()) {
				ArrayList<String> s = it.next();
				Vector<TimeMeasure[]> tmVec = testBase.get(s);
				Log.log[Resources.getVerboseFilter()].print(s + " ");
				displayTmVec(-1, tmVec);
			}
		} else
			Log.log[Resources.getVerboseFilter()].println(-1, "Empty");
	}
	
	/**
	 * Parses the -r option resolving the system of rules.
	 * 
	 * @param <b>args</b>
	 * @throws ExtractorException
	 */
	public static void processResolve(String[] args) throws ExtractorException {
		assert (args!=null && args.length==0) : 
			"option -r (or -resolve).";
		Log.log[Resources.getVerboseFilter()].println("Resolving...");
		resolve = true;
	}
	
	/**
	 * Parses the -p option printing knowledge database.
	 * 
	 * @param <b>args</b>
	 */
	public static void processPrint(String[] args) {
		assert (args!=null && args.length==0) : 
			"option -p (or -print).";
	
		printBases();
	}

	private static void resolve() throws ExtractorException {
		Solver solver;
		try {
			solver = new Solver(benchedUnitBase);
		} catch (ParseException e) {
			throw new ExtractorException(e);
		}
		try {
			Set<ArrayList <String>> keySet = testBase.keySet();
			Iterator<ArrayList <String>> itKeySet = keySet.iterator();
			Map <String, TimeMeasure> input = new HashMap<String, TimeMeasure>();
			while (itKeySet.hasNext()) {
				ArrayList<String> nameList = itKeySet.next();
				StringBuffer name = new StringBuffer();
				int size = nameList.size();
				for (int i = 0; i < size; i++) {
					name.append(nameList.get(i));
				}
				Vector <TimeMeasure []> tmVec = testBase.get(nameList);
				TimeMeasure avgTm = new TimeMeasure(0, TimeMeasure.NANOSECONDS);
				try {
					avgTm = computeTmVectorAverage(tmVec);
				} catch (ArrayIndexOutOfBoundsException e) {}
				input.put(name.toString(), avgTm);
			}
			testBase = solver.solve(input);
		} catch (SolvingException e) {
			
			// removed exception throwing..
			//throw new ExtractorException(e);
			Log.log[Resources.getVerboseFilter()].println("ERROR while solving : " + e.getMessage());
		}
		
	}
	
	
	private static TimeMeasure computeTmVectorAverage(Vector<TimeMeasure[]> tmVec) {
		// 2 possible behaviour : 
		// average over all arrays
		// average of average
		TimeMeasure[] tmAvgArray = new TimeMeasure[tmVec.size()];
		for (int i=0; i<tmAvgArray.length; i++) {
			tmAvgArray[i] = TimeMeasure.average(tmVec.elementAt(i));
		}

		return TimeMeasure.average(tmAvgArray);
	}
	

    /**
     * Parses the XML file resulting from the Manager tool.
     * @throws ExtractorException
     */
    private static void loadTestResultFile() throws ExtractorException {
    	FileInputStream fis = null;

    	Bench bench;

    	try {
    		fis = new FileInputStream(Resources.getInputFile());
    	} catch (FileNotFoundException e) {
    		throw new ExtractorException(e);
    	}
    	InputStreamReader reader = new InputStreamReader(fis);

    	try {
    		bench = Bench.unmarshal(reader);
    	} catch (MarshalException e) {
    		throw new ExtractorException (e);
    	} catch (ValidationException e) {
    		throw new ExtractorException (e);
    	}

    	for (int i=0; i<bench.getTestCount(); i++) {
    		populateTest(bench.getTest(i), i);
    	}
    }

    /**
     * Populates the KB using the input file.
     * @param test
     */
    private static void populateTest(Test test, int index) throws ExtractorException {
    	// populate reference test base
    	Log.log[Resources.getVerboseFilter()].println(2, "Loading reference cases...");
    	for (int i=0; i<test.getTestCaseCount(); i++) {
    		String refName = test.getTestCase(i).getName();
    		
    		if (((TestCase)test.getTestCase(i)).getReferenceName() != null)
    		  continue;
    		
    		// Skip in case of test with status!=1 
    		if (((TestCase)test.getTestCase(i)).getTestResult().getStatus() != 1)  {
    			Log.log[Resources.getVerboseFilter()].println("WARNING ! ; " + ((TestCase)test.getTestCase(i)).getReferenceName() + " has failed");
    			continue;
    		}
    		
    		// this should equals "empty" atm.
    		String refBenchedUnits = ((TestCase)test.getTestCase(i)).getBenchedUnit();
    		
    		Log.log[Resources.getVerboseFilter()].println(4, "New reference case : " + refName + "  benchedUnits:" + refBenchedUnits);
    		TimeMeasure[] tmArray = populateTime(test.getTestCase(i), null);
    		
    		TimeMeasure avg = TimeMeasure.average(tmArray);
    		TimeMeasure stdError = TimeMeasure.stdError(tmArray);
    		
    		Log.log[Resources.getVerboseFilter()].println("statistics for \"" + refName +"\" avg" + "=" + avg + " stderror="+stdError.getMeasure());
    		
    		// copying script field  from Test to TestCase 
//    		System.out.println("SCRIPT : " + test.getScript());
    		test.getTestCase(i).setScript(test.getScript());
    		
    		inputRefBase.put(refName, test.getTestCase(i));
    		refAvgBase.put(refName, avg);
    	}
    	
    	// populate test case base 
    	Log.log[Resources.getVerboseFilter()].println(2, "Loading test cases...");
    	for (int i=0; i<test.getTestCaseCount(); i++) {
    		if (((TestCase)test.getTestCase(i)).getReferenceName() == null)
      		  continue;
    		
    		// Skip in case of test with status!=1 
    		if (((TestCase)test.getTestCase(i)).getTestResult().getStatus() != 1)  {
    			Log.log[Resources.getVerboseFilter()].println("WARNING ! ; " + ((TestCase)test.getTestCase(i)).getReferenceName() + " has failed");
    			continue;
    		}
    		
   			populateTestCase(test.getTestCase(i), index);
    		inputTestBase.put(test.getTestCase(i).getName(), test.getTestCase(i));
    	}
    }
    
    /**
     * Fills the test base maps.
     * @param tc
     */
    private static void populateTestCase(TestCase tc, int index) {
    	// BJulien
//    	String[] splitNameArray = tc.getBenchedUnit().split("/");
    	String[] splitNameArray = tc.getName().split("/");
    	// EJulien
    	ArrayList<String> splitNameList = new ArrayList<String>();
    	// BJulien
    	for (int i=0; i<splitNameArray.length; i++) {
    		Log.log[Resources.getVerboseFilter()].println("populateTestCase : splitNameArray.length="+splitNameArray.length+"; splitNameArray["+i+"]="+splitNameArray[i]);
    		splitNameList.add(splitNameArray[i]);
    	}
//    	for (int i=0; i<splitNameArray.length; i++)
//    		splitNameList.add(splitNameArray[i]);
    	// EJulien
    	
    	String benchedUnits = tc.getBenchedUnit();
    	// BJulien
    	Log.log[Resources.getVerboseFilter()].println("populateTestCase : benchedUnits="+benchedUnits);
    	// EJulien
    	
    	Log.log[Resources.getVerboseFilter()].println("Loading Test case : " + splitNameList + " ("+benchedUnits+")");
    	Vector<TimeMeasure[]> tmVec = null;

    	// if test not already in base
    	if (testBase.get(splitNameList) != null) {
    		Log.log[Resources.getVerboseFilter()].println("Warning, test " + splitNameList + " is already in test base.");
    		tmVec = testBase.get(splitNameList);
    	} else
    		tmVec = new Vector<TimeMeasure[]>();

    		Log.log[Resources.getVerboseFilter()].println("REF : " + tc.getReferenceName());
    		TimeMeasure refavg = refAvgBase.get(tc.getReferenceName());
    		Log.log[Resources.getVerboseFilter()].println("refAvgBase = " + refAvgBase.get(tc.getReferenceName()));
    		TimeMeasure[] tmArray = populateTime(tc, refavg);
    		TimeMeasure avg = new TimeMeasure(0, TimeMeasure.NANOSECONDS);
    		TimeMeasure error = new TimeMeasure(0, TimeMeasure.NANOSECONDS);
    		try {
    			avg = TimeMeasure.average(tmArray);
    			error = TimeMeasure.stdError(tmArray);
    		} catch (ArrayIndexOutOfBoundsException e) {
    			Log.log[Resources.getVerboseFilter()].println("Missed : " + tc.getTestResult().getStatus()+
    					"  Message="+tc.getTestResult().getMessage());
    			missedTests.put(tc.getName(), ""+tc.getTestResult().getStatus());
    			return;
    		}
    		Log.log[Resources.getVerboseFilter()].println("TEST: \"" + splitNameList +"\" avg=" + avg + " stderror="+error.getMeasure());
    		
//    		for (TimeMeasure measure : tmArray) {
//				Log.log[LOG].println(measure.subtract(avg).getMeasure()/(double)avg.getMeasure() + " ");
//			}
//    		Log.log[LOG].println("");
    		tmVec.add(tmArray);

    		// BJulien
    		Vector <String> vBenchedUnits = new Vector<String>();
    		vBenchedUnits.add(benchedUnits);
    		benchedUnitBase.put(splitNameList, vBenchedUnits);
    		// EJulien
    		
    		testBase.put(splitNameList, tmVec);
    }

    /**
     * Build the TimeMeasure vector of a Case, substracting reference average value
     * if available (not null).
     * @param ct
     * @param refAvg
     * @return
     */
    private static TimeMeasure[] populateTime(lib.xml.test_result.TestCase tc, TimeMeasure refAvg) {
    	lib.xml.test_result.TimeMeasure[] tArray = tc.getTestResult().getTimeMeasure();
    	TimeMeasure[] tmArray = new TimeMeasure[tArray.length];

		if (refAvg == null) 
		  Log.log[Resources.getVerboseFilter()].println("Warning, test case without reference case :" + tc.getName());
    	for (int i=0; i<tArray.length; i++) {
          // loopsize retriving
    	  int X = tc.getX();

    	  if (refAvg != null)
    		 tmArray[i] = new TimeMeasure((tArray[i].getMeasure()-refAvg.getMeasure())/Util.loopSize((short)X),TimeMeasure.NANOSECONDS);
    	  else
    	     tmArray[i] = new TimeMeasure(tArray[i].getMeasure(),TimeMeasure.NANOSECONDS);
    	}
    	return tmArray;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	  tool = "extractor";
	  options = new String[][]{
	    {"i", "input", "1", "Populate the database with the XML result file given as argument. "},
		{"r", "resolve", "0", "Resolve the system of rules."},
	    {"p", "print", "0", "Prints knowledge base out."},
		{"o", "output", "1", "Output extractor results to XML file give as argument."}
	  };
	  lib.main.Main.main(args);
	  try {
	    loadTestResultFile();
	    if (resolve)
	      resolve();
	    saveExtractorResultFile();
	  } catch (ExtractorException e) {
	    e.printStackTrace();
		Log.fatal(e.getMessage());
	  }
	}

}