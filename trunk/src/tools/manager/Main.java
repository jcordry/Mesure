package tools.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.smartcardio.ATR;

import lib.cad.BadResponseStatusException;
import lib.cad.CAD;
import lib.cad.CommandAPDU;
import lib.cad.GPCAD;
import lib.cad.SelectAPDU;
import lib.cad.TimeCAD;
import lib.capfile.CAPFile;
import lib.chrono.Chronometer;
import lib.chrono.TimeMeasure;
import lib.loader.GPLoader;
import lib.log.Log;
import lib.main.ResourceException;
import lib.util.RelativePath;
import lib.util.Util;
import lib.xml.test_result.Bench;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import scripts.templates.NoResourceException;
import scripts.templates.TemplateScript;

import tools.calibrate.CalibrateException;
import tools.calibrate.CalibrateUtil;
import tools.manager.config.CalibrateMetric;
import tools.manager.config.ManagerConfig;
import tools.manager.config.Test;
import tools.manager.config.TestCase;
import tools.manager.config.Tests;

import benchs.lib.config.Applet;
import benchs.lib.config.TestConfig;

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
 * $HeadURL: svn+ssh://corentinboe@gforge.inria.fr/svn/mesureprv/src/benchs/engine/Manager.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 43 $
 * $LastChangedDate: 2006-10-16 17:17:37 +0200 (lun., 16 oct. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class defines the Manager tool.
 */
public class Main extends lib.main.Main {
	/** The config file of the manager */
	private static ManagerConfig config;
	
	/** The loader to be used to load, install and delete tests. */
	private static Vector<GPLoader> loaders;	
	
	/** The CAD to be used to perform card content management. */
	private static Vector<GPCAD> gpcads;
	
	/** The CAD to be used to perform measures. */
	private static Vector<TimeCAD> timecads;
	
	/** The tests to be executed. */
	private static Vector <Test> tests;
	
	/** XML root container */
	private static Bench bench;
	
	/** When true, switches from a global measurement to a calibration */
	private static boolean calibrate;
	
	/** The configs of the tests to be executed. */
	private static Vector <TestConfig> testConfigs;

	/** The index of the currently active CAD. */
	private static int activeCADIndex;
		
    private static void parseManagerConfigFile() throws ManagerException {
		// Parses the manager configuration file.
    	Log.log[Resources.getVerboseFilter()].println(4,"Parses the manager configuration file.");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(Resources.getManagerConfig());
		} catch (FileNotFoundException e) {
			throw new ManagerException(e);
		}
		InputStreamReader reader = new InputStreamReader(fis);
		try {
			config = ManagerConfig.unmarshal(reader);
		} catch (MarshalException e) {
			throw new ManagerException (e);
		} catch (ValidationException e) {
			throw new ManagerException (e);
		}
    }

    private static void buildCad(String cadName, String chronoName, String cardConfig) throws ManagerException {
    	CAD cad = ConfigurationHelper.buildCad(cadName);
    	GPCAD gpcad = ConfigurationHelper.buildGPCad(cad);
    	TimeCAD timecad = ConfigurationHelper.buildTimeCad(cad,chronoName);
    	GPLoader gploader = ConfigurationHelper.buildGPLoader(cardConfig, gpcad);

    	gpcads.add(gpcad);
    	timecads.add(timecad);
    	loaders.add(gploader);
    }
		
    private static void configureTests() {
      tests = new Vector <Test>();
      for (int i = 0; i < config.getTests().getTest().length; i++)
        tests.add(config.getTests().getTest()[i]);
    }
	
	/**
	 * For each test listed in the manager configuration file:
	 * <ul>
	 *   <li>Loads the related CAP file
	 *   <li>Installs the contained applets
	 *   <li>Executes the related script file
	 *   <li>Deletes the CAP file
	 * </ul>
	 * @throws <tt>ManagerException</tt> if a problem occurs when running the 
	 * test.
	 */
	private static void execute() throws ManagerException {
	  GPLoader loader = loaders.elementAt(activeCADIndex);	
	  TimeCAD timeCAD = timecads.elementAt(activeCADIndex);
	  String template = Resources.getTemplate();
	  CAPFile templateCapFile = new CAPFile(template);
	  ATR atr = null;
	  
  	  try {
        // Connects to the built CAD.
  	    atr = timeCAD.connect();
  	  
        // Reads the CAP files associated to the template applets.
	    Log.log[Resources.getVerboseFilter()].println(2,"> Reading the template " + template + "...");
	    templateCapFile.read();
			
		// Loads the CAP files associated to the template applets.
		Log.log[Resources.getVerboseFilter()].println(2,"> Loading the template " + template + "...");
		loader.selectAndAuthenticate();
		Chronometer chrono = timeCAD.getChronometer();
		chrono.start();
		loader.load(templateCapFile);
		TimeMeasure measure = chrono.stop();
		Log.log[Resources.getVerboseFilter()].println(2,"> The template was loaded in " + measure.toString());
		Log.log[Resources.getVerboseFilter()].println(2,"");
  	 } catch (Exception e) {
       throw new ManagerException(e);
  	 }
  	 
     // Builds XML result file
	 bench = new Bench();
	 bench.setCad(timeCAD.getName());
	 bench.setAtr(Util.byteArrayToHexString(atr.getBytes(),":"));
	 bench.setChrono(timeCAD.getChronometer().toString());

	 try {
       // Executes all tests
	   Log.log[Resources.getVerboseFilter()].println(2,"> Executing " + tests.size() + " tests...");
	   for (int i = 0; i < tests.size(); i++) {
	     execute(templateCapFile,null,testConfigs.get(i),tests.get(i).getTestCase());
	   }
	  } catch (Exception e) {
	    throw new ManagerException(e);
	  } finally {
	    try { 
	      // Saves XML result file.
    		Writer xmlWriter = new FileWriter(Resources.getXMLOutput());
	    	if (calibrate) {
	    		Tests t = new Tests ();
	    		t.setTest(tests.toArray(new Test[tests.size()]));
				config.setTests(t);
				File outputFolder = Resources.getXMLOutput().getParentFile();
				File inputFolder = Resources.getManagerConfig().getParentFile();
				if (!inputFolder.equals(outputFolder))
				  config.setCardConfig(
				     RelativePath.getRelativePath(outputFolder,new File(inputFolder,config.getCardConfig())));
	    		config.marshal(xmlWriter);
	    	} else {
	    		bench.marshal(xmlWriter);
	    	}
    		xmlWriter.close();
		  
          // Deletes the CAP files associated to the template applets.
		  Log.log[Resources.getVerboseFilter()].println(2,"> Deleting the template " + template + "...");
		  timeCAD.connect();
		  loader.selectAndAuthenticate();
		  loader.delete(templateCapFile);
		  
		  // Disconnects
		  timeCAD.disconnect();
	    } catch (Exception e) {
	      throw new ManagerException(e);
	    }
	  }
	}
	
	/**
	 * Unmarhals the configuration files for the tests.
	 * @throws <tt>ManagerException</tt> if a problem occurs when parsing a 
	 * test configuration file.
	 */
	private static void parseTestConfigFiles() throws ManagerException {
		testConfigs = new Vector<TestConfig>(tests.size());
		for (int i = 0; i < tests.size(); i++) {
			FileInputStream fis = null;
			// Parses the test
			try {
				fis = new FileInputStream(Resources.getTestConfig(tests.get(i).getTestConfig()));
			} catch (IOException e) {
			  throw new ManagerException(e);
			}
			InputStreamReader reader = new InputStreamReader(fis);
			try {
				testConfigs.add(TestConfig.unmarshal(reader));
			} catch (MarshalException e) {
				throw new ManagerException (e);
			} catch (ValidationException e) {
				throw new ManagerException (e);
			}
		}
	}
	
	/**
	 * Calibrates a test case.
	 * <ul>
	 *   <li>Gets some measurements on an initial loop size. 
	 *   <li>Increments on those measurements by adding some more
	 *   appropriate measurements according to the precision. 
	 *   <li>Produces an output file with the obtained appropriate calibration.
	 * </ul>
	 * @param script The script containing the test case to be calibrated.
	 * @param timeCAD The CAD to be used to perform the calibration.
	 * @param testCase the The test case to be calibrated.
	 * @throws <tt>ManagerException</tt> if a problem occurs during the calibration.
	 */
	private static void calibrate (TemplateScript script, TimeCAD timeCAD, TestCase testCase) 
	    throws ManagerException, NoResourceException {
		try {
		  String testCaseName = testCase.getName();
		  Log.log[Resources.getVerboseFilter()].println(2, "> Calibrating " + testCaseName + "..."); 
		  scripts.templates.TestCase tc = script.getTestCase(testCaseName);
		  if (!testCase.hasX())
		    testCase.setX(tc.getX());
		  if (!testCase.hasY())
		    testCase.setY(tc.getY());
		  if (!testCase.hasStartLoop())
		    testCase.setStartLoop(testCase.getX());
		  if (!testCase.hasMaxLoop())
		    testCase.setMaxLoop(tc.getXMax());
		  if (!testCase.hasMinLoop())
			testCase.setMinLoop(tc.getXMin());
		  Log.log[Resources.getVerboseFilter()].println(8, "> Number Y of executions to be performed off-card: " + testCase.getY());
		  Log.log[Resources.getVerboseFilter()].println(8, "> Searching for the number X of executions to be performed on-card " +
		      "with X belonging to [" + testCase.getMinLoop() + "," + testCase.getMaxLoop() + "], " +
		      "starting from X=" + testCase.getStartLoop());
		  Log.log[Resources.getVerboseFilter()].println(8, "> Precision: " + testCase.getPrecision());
		  
		  // To calibrate, we get some initial values first.
		  try {
			  CalibrateUtil cr = CalibrateUtil.getNewValues(
					  script,timeCAD,testCaseName,testCase.getStartLoop(),testCase.getY());
			  
			  cr.setMaxLoop(testCase.getMaxLoop());
			  cr.setMinLoop(testCase.getMinLoop());
			  // Then, we increment on those values depending on their precision, and the
			  // loopsize bounds
			  Vector <CalibrateUtil> v = new Vector<CalibrateUtil>();
			  v.add(cr);
			  CalibrateUtil resultCalibration = CalibrateUtil.getAppropriateCalibrateUtil(
					  v,testCase.getPrecision(),script,timeCAD,testCaseName,testCase.getY());
			  // Now v contains all the values that we got through the calibration
			  // We can make a new object with all of those values
			  cr = CalibrateUtil.makeNewCalibrateUtil(v);
			  // We can now compute the regression analysis
			  cr.computeRegression();
			  // We modify tests to reflect the results given by the calibration
			  calibrateProduceOutput(script,resultCalibration,cr,testCase);
		  } catch (CalibrateException e) {
			  // We need to use the default values for that test case
			  // And also for all those that depend on it.
			  int indexTest = findTestCaseRefMatchingTest(testCaseName);
			  TestCase [] listTest = reinitializeTestCasesReferingToACalibrateRef(script, testCase, indexTest);
			  tests.get(indexTest).setTestCase(listTest);
		  }
		}
		catch (NoResourceException e) {
		  throw e;
		}
		catch (Exception e) {
		  throw (new ManagerException (e));
		}
	}
	
	/**
	 * Sets a Tests object according to the result of a calibration. This Tests will eventually
	 * be marshalled into a ManagerConfig file:
	 * <ul>
	 *   <li>Finds the within the config the TestCases that have the given TestCase as a reference
	 *   <li>Sets all those TestCases in order to match the result of the calibration
	 *   <li>Adds those TestCases to the Tests object for the configuration of the upcoming benchmark 
	 * </ul>
	 * @param myScript the script which has been calibrated
	 * @param resultCalibration the result of the calibration with the appropriate loop size
	 * @param calibrateDataSet the whole set of data obtained from the calibration
	 * @param testCase the test case that has been calibrated
	 * @throws ManagerException
	 */
	private static void calibrateProduceOutput(TemplateScript myScript, CalibrateUtil resultCalibration,
			CalibrateUtil calibrateDataSet, TestCase testCase) throws ManagerException {
		String testCaseName = testCase.getName();
		int indexTest = findTestCaseRefMatchingTest(testCaseName);
		TestCase [] listTest =
			makeListTestCaseReferingToACalibrateRef(myScript, resultCalibration,
					calibrateDataSet, testCase, indexTest);
		tests.get(indexTest).setTestCase(listTest);
		Log.log[Resources.getVerboseFilter()].println(8, "tests.length="+tests.size());
	}
	
	/**
	 * Finds the index of the given test case within the tests field as well as in
	 * the testConfigs field
	 * @param testCaseName the name of the test case we are looking for
	 * @return the index of the test case
	 * @throws ManagerException
	 */
	public static int findTestCaseRefMatchingTest (String testCaseName) throws ManagerException {
		Log.log[Resources.getVerboseFilter()].println(8, "findTestCaseRefMatchingTest : testCaseName="+testCaseName);
		Test out = new Test();
		boolean found = false;
		int i;
		// We look for the reference within our tests
		for (i = 0; (i<tests.size()) && (!found); i++){
			out = config.getTests().getTest(i);
			String scriptName = testConfigs.get(i).getScript();
			try {
				Class c = Class.forName(scriptName);
				TemplateScript script = (TemplateScript) c.newInstance();
	
				if (out.getTestCase().length != 0) {
					for (int j = 0; j < out.getTestCase().length; j++) {
						if (testCaseName.compareTo(out.getTestCase(j).getName())==0) {
							found = true;
							break;
						}
					}
				} else {
					for (int j = 0; j < script.getTestCase().length; j++) {
						if (testCaseName.compareTo(script.getTestCase(j).getName())==0) {
							found = true;
							break;
						}
					}
				}
			} catch (Exception e) {
				throw new ManagerException(e);
			}
		}
		Log.log[Resources.getVerboseFilter()].println(8, "findTestCaseRefMatchingTest : out i="+(i-1));
		return(i-1);
	}
	
	/**
	 * Makes a array of TestCase those refering to the given reference all with their default configuration
	 * @param myScript
	 * @param refTestCase
	 * @param testIndex
	 * @return
	 * @throws ManagerException
	 */
	private static TestCase [] reinitializeTestCasesReferingToACalibrateRef(TemplateScript myScript,
			TestCase refTestCase, int testIndex) throws ManagerException {
		Log.log[Resources.getVerboseFilter()].println(8, "removeTestCaseReferingToACalibrateRef : testIndex="+testIndex);
		Vector <TestCase> relatedToRef = new Vector <TestCase> ();
		String testCaseName = refTestCase.getName();
		// We look for the testCases refering to the reference within the test we found
		Vector <TestCase> testCases = new Vector <TestCase>();
		for (int i = 0; i < tests.get(testIndex).getTestCase().length; i++) {
			testCases.add(tests.get(testIndex).getTestCase()[i]);
		}
		tests.get(testIndex).getTestCase();
		if (testCases.size() == 0) {
			String scriptName = testConfigs.get(testIndex).getScript();
			try {
				Class c = Class.forName(scriptName);
				TemplateScript script = (TemplateScript) c.newInstance();
				lib.xml.test_result.TestCase [] scriptTestCases = script.getTestCase();
				testCases = new Vector <TestCase>(scriptTestCases.length);
				for (int i = 0; i < testCases.size(); i++) {
					TestCase tmp =  new TestCase();
					tmp.setName(scriptTestCases[i].getName());
					testCases.set(i, tmp);
				}
				
			} catch (Exception e) {
				throw new ManagerException (e);
			}
		}
		// We need to modify the tests which refere to the calibration test
		for (int i = 0; i<testCases.size(); i++){
			try {
				Log.log[Resources.getVerboseFilter()].println(8, "reinitializeTestCaseReferingToACalibrateRef : testCases1[i].getName()="
						+testCases.get(i).getName());
				Log.log[Resources.getVerboseFilter()].println(8, "reinitializeTestCaseReferingToACalibrateRef : testCases1[i].getName()).getCalibrationName()="
						+myScript.getTestCase(testCases.get(i).getName()).getCalibrationName());
				if ((testCases.get(i).getName().compareTo(testCaseName) == 0)
						|| ((myScript.getTestCase(testCases.get(i).getName()).getCalibrationName() != null)
								&& (myScript.getTestCase(testCases.get(i).getName()).getCalibrationName().compareTo(testCaseName) == 0)))
				{
					TestCase aux = new TestCase();
					aux.setName(testCases.get(i).getName());
					relatedToRef.add(aux);
					Log.log[Resources.getVerboseFilter()].println(8, "reinitializeTestCaseReferingToACalibrateRef : add (reinitialized) : "
							+aux.getName());
				}
				else {
					// We need to keep in place all the tests that
					// do not refer to the calibration test case
					relatedToRef.add(testCases.get(i));
					Log.log[Resources.getVerboseFilter()].println(8, "reinitializeTestCaseReferingToACalibrateRef : add (not changed) : "
							+testCases.get(i).getName());
				}
			} catch (Exception e) {
				throw new ManagerException(e);
			}
		}
		return((TestCase [])relatedToRef.toArray(new TestCase[relatedToRef.size()]));
	}
	
	/**
	 * Builds an array of test cases that refer to a certain reference test case that has been calibrated 
	 * @param myScript
	 * @param resultCalibration the results of the calibration with the appropriate loop size
	 * @param calibrateDataSet the calibration generated data
	 * @param refTestCase the reference test case that has been calibrated
	 * @param testIndex the index of the test on which we opperate (with regard to tests and testconfigs)
	 * @return the array of test cases which are corrected to match the reference
	 * @throws ManagerException
	 */
	private static TestCase [] makeListTestCaseReferingToACalibrateRef(TemplateScript myScript, CalibrateUtil resultCalibration,
			CalibrateUtil calibrateDataSet, TestCase refTestCase, int testIndex) throws ManagerException {
		Log.log[Resources.getVerboseFilter()].println(8, "makeListTestCaseReferingToACalibrateRef : testIndex="+testIndex);
		Vector <TestCase> relatedToRef = new Vector <TestCase> ();
		String testCaseName = refTestCase.getName();
		// We look for the testCases refering to the reference within the test we found
		TestCase [] testCases = tests.get(testIndex).getTestCase();
		Log.log[Resources.getVerboseFilter()].println(8, "makeListTestCaseReferingToACalibrateRef : testCases.length="+testCases.length);
		if (testCases.length == 0) {
			String scriptName = testConfigs.get(testIndex).getScript();
			try {
				Class c = Class.forName(scriptName);
				TemplateScript script = (TemplateScript) c.newInstance();
				lib.xml.test_result.TestCase [] scriptTestCases = script.getTestCase();
				testCases = new TestCase[scriptTestCases.length];
				for (int i = 0; i < testCases.length; i++) {
					testCases[i] = new TestCase();
					testCases[i].setName(scriptTestCases[i].getName());
				}
				
			} catch (Exception e) {
				throw new ManagerException (e);
			}
		}
		// We need to modify the tests which refere to the calibration test
		for (int i = 0; i<testCases.length; i++){
			try {
				Log.log[Resources.getVerboseFilter()].println(8, "makeListTestCaseReferingToACalibrateRef : myScript.getName()="
						+myScript.getName());
				Log.log[Resources.getVerboseFilter()].println(8, "makeListTestCaseReferingToACalibrateRef : testCases1[i].getName()="
						+testCases[i].getName());
				Log.log[Resources.getVerboseFilter()].println(8, "makeListTestCaseReferingToACalibrateRef : testCases1[i].getName()).getCalibrationName()="
						+myScript.getTestCase(testCases[i].getName()).getCalibrationName());
				if ((testCases[i].getName().compareTo(testCaseName) == 0)
						|| ((myScript.getTestCase(testCases[i].getName()).getCalibrationName() != null)
								&& (myScript.getTestCase(testCases[i].getName()).getCalibrationName().compareTo(testCaseName) == 0))) {
					TestCase aux = new TestCase();
					aux.setName(testCases[i].getName());
					setTestCaseToCalibrationReference(aux,
							refTestCase, resultCalibration, calibrateDataSet);
					relatedToRef.add(aux);
					Log.log[Resources.getVerboseFilter()].println(8, "calibrationProduceOutput : add (changed) : "
							+aux.getName());
				} else {
					// We need to keep in place all the tests that
					// do not refer to the calibration test case
					relatedToRef.add(testCases[i]);
					Log.log[Resources.getVerboseFilter()].println(8, "calibrationProduceOutput : add (not changed) : "
							+testCases[i].getName());
				}
			} catch (Exception e) {
				throw new ManagerException(e);
			}
		}
		return((TestCase [])relatedToRef.toArray(new TestCase[relatedToRef.size()]));
	}
	
	/**
	 * Sets a test case according to a reference test case (in term of loop size ...)
	 * @param t the test case we want to set
	 * @param reference the reference test case which will be the basis for the new test case 
	 * @param results the calibration results with the apporpriate loop size
	 * @param dataSet the calibration generated data
	 * @return
	 */
	private static TestCase setTestCaseToCalibrationReference(TestCase t, TestCase reference,
			CalibrateUtil results, CalibrateUtil dataSet) {
		CalibrateMetric cm = new CalibrateMetric();
		cm.setA(dataSet.getAlphaRegression());
		cm.setB(dataSet.getBetaRegression());
		cm.setXmin(results.getPopulation()[0].getX());
		cm.setYmin(results.getPopulation()[0].getY());
		cm.setTmin(results.getPopulation()[0].getTestResult().getAvg());//!!!
		t.setCalibrateMetric(cm);
		t.setMaxLoop(reference.getMaxLoop());
		t.setMinLoop(reference.getMinLoop());
		t.setPrecision(reference.getPrecision());
		t.setStartLoop(reference.getStartLoop());
		t.setY(reference.getY());
		t.setX(results.getP2());
		return (t);
	}
	
	
	/**
	 * Loads and installs the CAP file identified in the specified configuration
	 * file, executes the related script, and deletes the CAP file
	 * 
	 * @param templateCapFile the template CAP file.
	 * @param script the test script to run.
	 * @param test the test configuration. 
	 * @param testCases the test cases to run.
	 * @throws <tt>ManagerException</tt> if a problem occurs when running the 
	 * test.
	 */
	private static void execute(CAPFile templateCapFile, TemplateScript script, TestConfig test, TestCase[] testCases) throws ManagerException {
		CAPFile capfile = null;
		GPLoader loader = loaders.elementAt(activeCADIndex);
		TimeCAD timeCAD = timecads.elementAt(activeCADIndex);
		String capFileName = test.getPackage().getCapfile();
		Vector<byte[]> AIDs = new Vector<byte[]>();
		NoResourceException noResources = null;
		
		try {
			// Reads the CAP file
		  Log.log[Resources.getVerboseFilter()].println(2,"> Getting the CAP file from resources:" + capFileName);
		  capFileName = Resources.getCAPFile(capFileName);
		  
		  Log.log[Resources.getVerboseFilter()].println(2,"> Reading the CAP file " + capFileName + "...");
		  capfile = new CAPFile(capFileName);
		  capfile.read();
			
          // Loads the CAP file
		  Log.log[Resources.getVerboseFilter()].println(2,"> Loading the CAP file " + capFileName + "...");
		  Chronometer chrono = timeCAD.getChronometer();
		  chrono.start();
		  loader.load(capfile);
		  TimeMeasure measure = chrono.stop();
		  Log.log[Resources.getVerboseFilter()].println(2,"> The CAP file " + capFileName + " was loaded in " + measure.toString());
		  Log.log[Resources.getVerboseFilter()].println(2,"");
		  byte[] pkg = capfile.getPackageAID();
		  AIDs.add(pkg);
		  
          // Installs contained applets
		  Log.log[Resources.getVerboseFilter()].println(2,"> Installing the contained applets...");
		  byte[][] applets = capfile.getAppletAIDs();
		  Applet[] instances = test.getPackage().getApplet();
		  byte[] selectAID = null;
		  for (int i = 0; i < applets.length; i++) {
		    Applet[] tmp = getInstances(instances,applets[i]); 
			for (int j = 0; j < tmp.length; j++) {
			  byte[] instanceAID =  Util.hexStringToByteArray(
			      tmp[j].getInstanceAid());
			  loader.install(pkg,applets[i],
						     instanceAID,
						     tmp[j].getPrivileges(),
						    Util.hexStringToByteArray(tmp[j].getParams()));
			  AIDs.add(0,instanceAID);
			  if (selectAID == null)
			    selectAID = instanceAID;
			}
			if (tmp.length == 0) {
			  loader.install(pkg,applets[i],applets[i],0,null);
			  AIDs.add(0,applets[i]);
			  if (selectAID == null)
			    selectAID = applets[i];
			}
		  }
		  
		  // Gets the script file
		  if (script == null) {
		    String scriptName = test.getScript();
		    Class c = Class.forName(scriptName);
		    script = (TemplateScript) c.newInstance();
		    script.setName(scriptName);
		    Log.log[Resources.getVerboseFilter()].println(2,"> Executing the script " + scriptName + " (" + (
		        testCases.length == 0 ? script.getTestCase().length : testCases.length) 
		        + " test cases)...");
		  }
		  
		  // prepare and terminate allow some extra work
		  boolean failed = false;
		  try {
		    Log.log[Resources.getVerboseFilter()].println(2, "> Preparing...");
			failed = !script.prepare(gpcads.elementAt(activeCADIndex), loader);
		  } catch (Exception e) {
		    e.printStackTrace();
			failed = true;
		  }
		  
		    if (! failed) {
			  CommandAPDU capdu = new SelectAPDU(selectAID);
			  try {
			    gpcads.elementAt(activeCADIndex).getCAD().sendAPDUAndVerify(capdu);
			  } catch (BadResponseStatusException e) {
//			    if (calibrate) {
//				  return;
//			    }
				throw new ManagerException(e);
			  }

			  // record date/time for test starting
			  if (script.getDate() == null) {
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			    String now = sdf.format(new Date());
			    script.setDate(now);
			  }

			  if (testCases.length == 0) {
				  if (calibrate) {
					  // If we want to calibrate the tests, we need to exclude those
					  // that are not dedicated to the calibration.
					  for (int i = 0; i < script.getTestCase().length;i++) {
						  scripts.templates.TestCase testCase = (scripts.templates.TestCase) script.getTestCase(i);
						  if (testCase.isForCalibration()) {
							  tools.manager.config.TestCase tc = new TestCase();
							  tc.setName(testCase.getName());
							  tc.setMaxLoop(testCase.getXMax());
							  tc.setMinLoop(testCase.getXMin());
							  calibrate(script,timeCAD,tc);
						  }
					  }
				  } else {
					  script.run(timeCAD);
				  }
			  } else  {
				  for (int i = 0; i < testCases.length;i++) {
					  if (calibrate) {
						  scripts.templates.TestCase testCase = script.getTestCase(testCases[i].getName());
						  if (testCase.isForCalibration()) {
						    calibrate(script,timeCAD,testCases[i]);
						  }
					  } else {
						  script.run(timeCAD,testCases[i].getName(),
								  testCases[i].hasX(),testCases[i].hasY(),
								  testCases[i].getX(),testCases[i].getY());
					  }
				  }
			  }
		  }
		  try {
			  Log.log[Resources.getVerboseFilter()].println(2, "> Terminating...");
			  script.terminate(gpcads.elementAt(activeCADIndex), loader);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }

		  if (calibrate) {}
		  else {
			  
			  // Adds the script to the bench for outputing - build new instances
			  lib.xml.test_result.Test xmlTest = new lib.xml.test_result.Test();
			  
			  boolean isValid = true;
			  for (int i=0; i<script.getTestCaseCount(); i++) {
				  
				  // builds xml. TestCase object
				  scripts.templates.TestCase otc = (scripts.templates.TestCase)script.getTestCase(i);
				  lib.xml.test_result.TestCase tc = new lib.xml.test_result.TestCase();
				  
				  tc.setName(otc.getName());
				  tc.setReferenceName(otc.getReferenceName());
				  tc.setCalibrationName(otc.getCalibrationName());
				  tc.setBenchedUnit(otc.getBenchedUnit());
				  tc.setData(otc.getData());
				  tc.setApdu(otc.getApdu());
				  if (otc.sentAsIs())
				    tc.setX(script.getTestCase(tc.getCalibrationName()).getX());
				  else
				    tc.setX(otc.getX());
				  
				  // builds xml. TestResult object
				  scripts.templates.TestResult otr = (scripts.templates.TestResult) otc.getTestResult();
				  lib.xml.test_result.TestResult tr = new lib.xml.test_result.TestResult();
				  tr.setAvg(otr.getAvg());
				  tr.setMessage(otr.getMessage());
				  tr.setStatus(otr.getStatus());
				  tr.setStdError(otr.getStdError());
				  
				  // tc is valid
				  if (tr.getStatus() == 1) {
					  // builds xml. TestMeasure objects
					  for (int j=0; j<otr.getTimeMeasureCount(); j++) {
						  lib.xml.test_result.TimeMeasure tm = new lib.xml.test_result.TimeMeasure();
						  lib.chrono.TimeMeasure otm = (lib.chrono.TimeMeasure)otr.getTimeMeasure(j);
						  
						  if (otm != null) {
							  // test failed, measure is null
							  tm.setMeasure(otm.getMeasure());
							  tr.addTimeMeasure(tm);
						  }
					  }
				  } else {
					  isValid = false;
				  }
				  tc.setTestResult(tr);
				  xmlTest.addTestCase(tc);
			  }
			  
			  xmlTest.setCount(script.getTestCaseCount());
			  xmlTest.setName(script.getName());
			  xmlTest.setDate(script.getDate());
			  xmlTest.setVersion(script.getVersion());   // TODO: still nothing in there
			  xmlTest.setValid(isValid);
			  xmlTest.setScript(script.getClass().getName());
			  
			  // bench is initialized at top level.
			  bench.addTest(xmlTest);
//			  bench.addTest(script);
		  }
		} catch (NoResourceException e) {
		  noResources = e;	  
		  Log.log[Resources.getVerboseFilter()].println(0,"Problem of resources while executing test. Force deletion." + e);
		} catch (Exception e) {
		  Log.log[Resources.getVerboseFilter()].println(0,"Exception while executing test. Force deletion." + e);
		} 
		finally {
		  Log.log[Resources.getVerboseFilter()].println(2,"> Deleting the CAP file " + capFileName + "...");
		  try {
            // Deletes the CAP file
			timeCAD.connect();
			loader.selectAndAuthenticate(); 
			for (int i = 0; i < AIDs.size(); i++)
			  loader.delete((byte[])AIDs.elementAt(i));
		  } catch (Exception e) {
		    throw new ManagerException(e); 
		  } 
		  if (noResources != null) {
		    try {
		      String template = Resources.getTemplate();
		      
              // Deletes the CAP files associated to the template applets.
			  Log.log[Resources.getVerboseFilter()].println(2,"> Deleting the template " + template + "...");
			  timeCAD.connect();
			  loader.selectAndAuthenticate();
			  loader.delete(templateCapFile);
			  
			  // Disconnects
			  timeCAD.disconnect();
			  
			  // Connects
			  timeCAD.connect();
			  
              // Loads the CAP files associated to the template applets.
			  Log.log[Resources.getVerboseFilter()].println(2,"> Loading the template " + template + "...");
			  loader.selectAndAuthenticate();
			  loader.load(templateCapFile);
			  
			  // Determines which test cases remain to be executed
			  scripts.templates.TestCase tc = noResources.getTestCase();
			  TestCase[] tcs;
			  if (testCases.length != 0) {
			    int i;
			    for (i = 0; i < testCases.length; i++)
			      if (tc.getName().equals(testCases[i].getName()))
			        break;
			    tcs = new TestCase[testCases.length - i];
			    for (int j = 0; j < tcs.length; j++)
			      tcs[j] = testCases[j + i];
			  } else {
			    int i;
			    for (i = 0; i < script.getTestCase().length; i++)
			      if (tc.getName().equals(script.getTestCase()[i].getName()))
			        break;
			    tcs = new TestCase[script.getTestCase().length - i];
			    for (int j = 0; j < tcs.length; j++) {
			      tcs[j] = new TestCase();
			      tcs[j].setName(script.getTestCase()[j+i].getName());
			    }
			  }
				  
			  // Executes the test
			  execute(templateCapFile,script,test,tcs);
		  	} catch (Exception e) {
		      throw new ManagerException(e);
		    }
		  } 
		}
	}
	
	/**
	 * Parses the -i option specifying the manager configuration file to be 
	 * used.
	 * 
	 * @param args the buffer containing the value for this option.
	 * @throw <tt>FileNotFoundException</tt> exception if the specified file 
	 *        does not exist.
	 */
	public static void processInput(String[] args) throws ResourceException {
	  assert (args!=null && args.length==1) : 
	      "option -i (or -input) requires a path for the XML input file.";
	  Resources.setManagerConfig(args[0]);
	}

	/**
	 * Parses the -xo option specifying the XML file to be used to store 
	 * results.
	 * 
	 * @param args the buffer containing the value for this option.
	 */
	public static void processXmlOutput(String[] args) {
	  assert (args!=null && args.length==1) : 
		"option -xo (or -xmloutput) requires a path for the XML output file.";
	  Resources.setXMLOutput(args[0]);
	}
	
	/**
	 * Parses the -cal option specifying the calibration cursor.
	 * 
	 * @param args the buffer containing the value for this option.
	 */
	public static void processSetCalibrate(String[] args){
		calibrate=true;
	}
	
	/**
	 * Parses the -to option specifying the CSV file to be used to store 
	 * results.
	 * 
	 * @param args the buffer containing the value for this option.
	 */
	public static void processCsvOutput(String[] args){
	  assert (args!=null && args.length==1) :
          "option -to (or -csvoutput) requires a path for the CSV output file.";
	  Resources.setCSVOutput(args[0]);
	}

	/**
	 * Builds a new instance of the <tt>Manager</tt> tool, configures it and 
	 * executes each test found in the configuration.
	 *  
	 * @param args the options to be passed to the tool.
	 */
	public static void main(String[] args) {
	  tool = "manager";
	  options = new String[][]{
			{"i" ,"input"     ,"1","The manager configuration file. " +
				"If not specified, the \"ManagerConfig.xml\" file provided in " +
			"the \"bin\" directory is used."},
			{"xo","xmlOutput" ,"1","The XML file containing the results. " +
			"If not specified, the \"tmp\\results.xml\" file is used."},
			{"to","csvOutput" ,"1","The text file containing the results. " +
			"If not specified, the \"tmp\\results.csv\" file is used."},
			{"cal","setCalibrate" ,"0","Switch to calibration"}
	  };
	  gpcads = new Vector<GPCAD>();
	  loaders = new Vector<GPLoader>();
	  timecads = new Vector<TimeCAD>();
	  
      // Add shutdown hook
      ShutdownHook shutdownHook = new ShutdownHook();
      Runtime.getRuntime().addShutdownHook(shutdownHook);
	  
      // Parse arguments
	  lib.main.Main.main(args);

	  try {
		// Configuration File
	    parseManagerConfigFile();
	    String cardConfig = config.getCardConfig();
	    if (!new File(cardConfig).isAbsolute())
	      cardConfig = Resources.getManagerConfig().getParent() + "/" + cardConfig;
	    
	    // CAD
	    buildCad(config.getCad(),config.getTimeProvider(),cardConfig);
	    activeCADIndex = 0;
	    
	    // Tests
	    configureTests();
	    parseTestConfigFiles();
	   
	    // Execution
	    execute();
	    
	    // Cleaning
		clean();
	  } catch (ManagerException e) {
		e.printStackTrace();
		Log.fatal(e.getMessage());
	  }
	}
	
	/**
	 * Returns instances of the specified applet.
	 * 
	 * @param instances the applet instances.
	 * @param appletAID the applet AID.
	 * @return the instances among <tt>instances</tt> that are instances of the
	 *         applet identified by <tt>appletAID</tt>. 
	 */
	private static Applet[] getInstances(Applet[] instances, byte[] appletAID) {
		Vector v = new Vector();
		for (int i = 0; i < instances.length; i++)
			if (Arrays.equals(Util.hexStringToByteArray(instances[i].getAid()),appletAID))
				v.add(instances[i]);  
		return (Applet[]) v.toArray(new Applet[0]);
	}
	
	private static void clean() {
	  File f = new File(Resources.getTmpFolder(),Resources.BENCHS);
	  if (f.exists())
	    Util.delete(f);
	}

	private static class ShutdownHook extends Thread {
	  public void run() {
	    clean();
	  }
	}
}

