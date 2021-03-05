package tools.profiler;

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

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import lib.chart.BarCharts;
import lib.chart.IChart;
import lib.chart.SpiderCharts;
import lib.log.Log;
import lib.xml.extractor_result.ExtractorResult;
import lib.xml.extractor_result.Metric;
import lib.xml.extractor_result.Unit;
import lib.xml.profiler_result.Base;
import lib.xml.profiler_result.Bytecode;
import lib.xml.profiler_result.BytecodeAverage;
import lib.xml.profiler_result.Bytecodes;
import lib.xml.profiler_result.Domain;
import lib.xml.profiler_result.Environnement;
import lib.xml.profiler_result.Method;
import lib.xml.profiler_result.MethodAverage;
import lib.xml.profiler_result.Methods;
import lib.xml.profiler_result.Results;
import lib.xml.test_result.Bench;

import org.w3c.dom.Document;

import tools.manager.config.ManagerConfig;

/**
 * This tool generates a html output file where the results are displayed
 */
public class Main extends lib.main.Main {
	private static Bench rawResults;
	private static ExtractorResult extractedResults;
	private static ManagerConfig managerConfig;			
	private static Results refResults;	
	
	private static Hashtable<String,Double> averageResult;
	
	private static int inputCount=0;
	
	/**
	 * Populates the profiler database with the useful XML files.
	 * @param args	the paths to the *.xml file.
	 * @throws ProfilerException
	 */
	public static void processInputs(String[] args) throws ProfilerException {
		assert (args!=null && args.length==4) :	"option -i (or -inputs) requires 4 paths for the XML input files.";
		Log.log[Resources.getVerboseFilter()].println(4,"Loading XML files: " + args[0]);
		
		setUsedObjects(args);
	}
	
	/**
	 * Populates the profiler database with the useful XML files.
	 * @param args	the paths to the *.xml file.
	 * @throws ProfilerException
	 */
	public static void processInput(String[] args) throws ProfilerException {
		assert (args!=null && args.length==1) :	"option -i (or -inputs) requires 4 paths for the XML input files.";
		Log.log[Resources.getVerboseFilter()].println(4,"Loading XML file: " + args[0]);
		
		setUsedObjects(args[0]);
		
		// get an idea of current call number
		inputCount ++;
	}
	
	/** 
	 * Sets the objects used to create the final database.
	 * @param filePaths	the paths of the *.xml files to be transformed into objects.
	 * @throws ProfilerException
	 */
	private static void setUsedObjects (String [] filePaths) throws ProfilerException{
		for(int index = 0; index < filePaths.length; index++ ){
			inputCount = index;
			setUsedObjects(filePaths[index]);
		}
	}
	
	/** 
	 * Sets the objects used to create the final database.
	 * @param filePaths	the paths of the *.xml files to be transformed into objects.
	 * @throws ProfilerException
	 */
	private static void setUsedObjects(String filePath) throws ProfilerException{
		// *.xml file to java object transformation (unmarshalling)
		if (filePath.equals("0")) {
			switch(inputCount){
			case 0: 
				Log.log[Resources.getVerboseFilter()].println(4,"Loading default raw results XML file: " + Resources.getRawResults());
				setRawResults();
				break;
			case 1: 
				Log.log[Resources.getVerboseFilter()].println(4,"Loading default extracted results XML file: " + Resources.getExtractedResults());
				setExtractedResults();
				break;
			case 2: 
				Log.log[Resources.getVerboseFilter()].println(4,"Loading default manager configuration XML file: " + Resources.getManagerConfig());
				setManagerConfig();
				break;
			case 3: 
				Log.log[Resources.getVerboseFilter()].println(4,"Loading default reference XML file: " + Resources.getXMLCoef());
				setRefResults();
				break;
			default: 
				break;
			}
		}
		else {
			switch(inputCount){
			case 0: 
				Log.log[Resources.getVerboseFilter()].println(4,"Loading raw results XML file: " + filePath);
				try {
					Resources.setRawResults(filePath);
				} catch (Exception e) {
					throw new ProfilerException(e);
				}
				setRawResults();
				break;	
			case 1: 
				Log.log[Resources.getVerboseFilter()].println(4,"Loading extracted results XML file: " + filePath);
				try {
					Resources.setExtractedResults(filePath);
				} catch (Exception e) {
					throw new ProfilerException(e);
				}
				setExtractedResults();
				break;
			case 2: 
				Log.log[Resources.getVerboseFilter()].println(4,"Loading manager configuration XML file: " + filePath);
				try {
					Resources.setManagerConfig(filePath);
				} catch (Exception e) {
					throw new ProfilerException(e);
				}
				setManagerConfig();
				break;
			case 3: 
				Log.log[Resources.getVerboseFilter()].println(4,"Loading reference XML file: " + filePath);
				try {
					Resources.setXMLCoef(filePath);
				} catch (Exception e) {
					throw new ProfilerException(e);
				}
				setRefResults();
				break;
			default: 
				break;
			}
		}
	}
	
	/**
	 * 
	 * 
	 * @param results
	 */
	private static void setAverageResults (Results results)
	{
		if (refResults == null)
			return;
		
		averageResult = new Hashtable<String,Double>();
		
		Enumeration bal = refResults.getBytecodeAverageList().enumerateBytecodeAverage();
		while (bal.hasMoreElements())
		{
			BytecodeAverage ba = (BytecodeAverage)bal.nextElement();
			averageResult.put(ba.getName().toLowerCase(),ba.getAvg());
		}
		
		Enumeration mal = refResults.getMethodAverageList().enumerateMethodAverage();
		while (mal.hasMoreElements())
		{
			MethodAverage ma = (MethodAverage)mal.nextElement();
			averageResult.put(ma.getName().toLowerCase(),ma.getAvg());
		}
		
		results.setBytecodeAverageList(refResults.getBytecodeAverageList());
		results.setMethodAverageList(refResults.getMethodAverageList());
	}
	
	/**
	 * Return the double value contained in averageResult Hashtable
	 * 
	 * @param s the key to search
	 * @return 0 if the value does not exist, the double value otherwise
	 */
	private static double getAverageResult(String s)
	{
		return averageResult.get(s.toLowerCase())==null?0:averageResult.get(s.toLowerCase()).doubleValue();
	}
	
	/**
	 * Sets the raw results object. 
	 * @param path The new path to the raw results file.
	 */
	private static void setRawResults() throws ProfilerException {
		try {
			rawResults = Bench.unmarshal(new FileReader(Resources.getRawResults()));
		} catch(Exception e){
			throw new ProfilerException(e);
		}
	}
	
	/**
	 * Sets the extracted results object.
	 * @param path The new path to the extracted results file.
	 */
	private static void setExtractedResults() throws ProfilerException {
		try{
			extractedResults = ExtractorResult.unmarshal(new FileReader(Resources.getExtractedResults()));;
		} catch(Exception e){
			throw new ProfilerException(e);
		}
	}
	
	/**
	 * Sets the manager configuration object.
	 * @param path	The new path to the manager configuration file.
	 */
	private static void setManagerConfig() throws ProfilerException {
		try{
			managerConfig = ManagerConfig.unmarshal(new FileReader(Resources.getManagerConfig()));;
		} catch(Exception e){
			throw new ProfilerException(e);	
		}
	}
	
	/**
	 * Sets the reference results object.
	 * @param path The new path to the reference results file.
	 */
	private static void setRefResults() throws ProfilerException {
		try{
			refResults = Results.unmarshal(new FileReader(Resources.getXMLCoef()));;
		} catch(Exception e){
			throw new ProfilerException(e);
		}
	}
	
	/**
	 * Sets the style used for the notes chart.
	 * @param args	the style
	 * @throws ProfilerException
	 */
	public static void processMarkStyle(String[] args) throws ProfilerException {
		assert (args!=null && args.length==1) : 
			"option -mcs (or -markschart) requires the chart style (Spider or Bar).";
		Log.log[Resources.getVerboseFilter()].println(4,"Marks Chart Style : " + args[0]);
		Resources.setMarkStyle(args[0]);
	}
	
	
	/**
	 * Sets the style used for the domaines charts.
	 * @param args	the style
	 * @throws ProfilerException
	 */
	public static void processDomainStyle(String[] args) throws ProfilerException {
		assert (args!=null && args.length==1) : 
			"option -dcs (or -domainschart) requires the chart style (Spider or Bar).";
		Log.log[Resources.getVerboseFilter()].println(4,"Domains Chart Style : " + args[0]);
		Resources.setDomainStyle(args[0]);
	}
	
	
	/**
	 * Sets the langage used for the output file.
	 * @param args	the langage.
	 * @throws ProfilerException
	 */
	public static void processLang(String[] args) throws ProfilerException {
		assert (args!=null && args.length==1) : 
			"option -l (or -lang) requires the language.";
		Log.log[Resources.getVerboseFilter()].println(4,"HTML Output langage : " + args[0]);
		Resources.setLang(args[0]);  
	}
	
	
	/**
	 * Sets the *.xml profiler database file location.
	 * @param args	the path to the *.xml profiler database.
	 * @throws ProfilerException
	 */
	public static void processDatabase(String[] args) throws ProfilerException {
		assert (args!=null && args.length==1) : 
			"option -db (or -database) requires a path for the XML output file.";
		Log.log[Resources.getVerboseFilter()].println(4,"Saving Database : " + args[0]);
		Resources.setDatabase(args[0]);  
	}
	
	/**
	 * Sets the stylesheet location.
	 * @param args	the path to the stylesheet.
	 * @throws ProfilerException
	 */
	public static void processStylesheet(String[] args) throws ProfilerException {
		assert (args!=null && args.length==1) : 
			"option -ss (or -stylesheet) requires a path for the XSL file.";
		Log.log[Resources.getVerboseFilter()].println(4,"Loading StyleSheet : " + args[0]);
		try {
			Resources.setStyleSheet(args[0]); 
		} catch (Exception e) {
			throw new ProfilerException(e);
		}
	}
	
	
	/**
	 * Sets the *.html output file location.
	 * @param args the path to the *.html output file.
	 * @throws ProfilerException
	 */
	public static void processOutput(String[] args) throws ProfilerException {
		assert (args!=null && args.length==1) : 
			"option -o (or -output) requires a path for the HTML output file.";
		Log.log[Resources.getVerboseFilter()].println(4,"Saving HTML file : " + args[0]);
		Resources.setHTMLOutput(args[0]);
	}
	
	private static void execute() throws ProfilerException {
		createXmlDatabase(Resources.getDatabase());
		createHTML(Resources.getDatabase(),Resources.getStyleSheet(),Resources.getHTMLOutput());
	}	
	/** 
	 * Calculates a given domaine note.
	 * @param bytecodes	the "bytecodes" object used to calculate the note.
	 * @param methods	the "methods" object used to calculate the note.
	 * @param coeffRes 		the reference "results" object.
	 * @param index_domain the index of the considered domain.
	 * @return the note.
	 */
	private static double setMark(Bytecodes bytecodes, Methods methods, Results coeffRes, int index_domain){
		
		Log.log[Resources.getVerboseFilter()].println(12, "Computing Mark ");
		
		// total occurence number calculation
		//this total must be the total of methods and bytecodes call in
		//reference application for the domain.
		//e.g. if not all methods and bytecodes included in a Domain are mesaured,
		//the mark for this domain will be very low (since each methods that not have been 
		//measured have a 0 mark).
		double sum_nbocc = 0;
		sum_nbocc += coeffRes.getDomain(index_domain).getBytecodes().getTotalocc();
		sum_nbocc += coeffRes.getDomain(index_domain).getMethods().getTotalocc();
		
		Log.log[Resources.getVerboseFilter()].println(12, "Occurence Number: " + sum_nbocc);		
//		 mark calculation
		double avg = 0.0;
		double ref = 0.0;
		for (int i=0; i<bytecodes.getBytecodeCount();i++){
			double nbocc = bytecodes.getBytecode(i).getNbocc();
			ref += (getAverageResult(bytecodes.getBytecode(i).getName().toLowerCase())*(nbocc/sum_nbocc));
			avg += (bytecodes.getBytecode(i).getAvg()*(nbocc/sum_nbocc));
		}
		
		for (int j=0; j<methods.getMethodCount();j++){
			double nbocc = methods.getMethod(j).getNbocc();	
			ref += (getAverageResult(methods.getMethod(j).getName().toLowerCase())*(nbocc/sum_nbocc));
			avg += (methods.getMethod(j).getAvg()*(nbocc/sum_nbocc));
		}
		
		return ref/avg;
	}
	
	/** 
	 * Creates and sets a "bytecodes" object.
	 * @param refRes		the reference "results" object.
	 * @param extractorRes	the extracted results.
	 * @param index				the index of the considered domain.
	 * @return	the set bytecodes.
	 */
	private static Bytecodes setBaseBytecodes (Results refRes, ExtractorResult extractorRes, int index){
		
		// empty "bytecodes" object creation
		Bytecodes bytecodes = new Bytecodes();
		
		Bytecodes coeffBytecodes = refRes.getDomain(index).getBytecodes();
		Metric extractedMetric = extractorRes.getMetric(0);
		
		Log.log[Resources.getVerboseFilter()].println(12,"Bytecode count for domain (" +index + ") " + coeffBytecodes.getBytecodeCount());
		
		//for each bytecode in the coefs file, we search if a test
		//is corresponding in the results...
		for(int i=0;i<coeffBytecodes.getBytecodeCount();i++)
		{
			//the current name of the method in coefs file
			String realName = coeffBytecodes.getBytecode(i).getName().toLowerCase();
			//useful for average calculus
			int divisor = 0;
			double totalTime = 0.0;
			
			for(int j=0;j<extractedMetric.getUnitCount();j++)
			{
				//for each method Unit in the results,
				//we get the tested method name
				Unit extractedUnit = extractedMetric.getUnit(j);
				String originalUnitScript = extractedUnit.getScript();
				if (originalUnitScript.startsWith("scripts.jcapi"))
					continue;
				
				String modifiedName = extractedUnit.getName()
										.replaceAll("[^_]{1,1}[0123456789]+","")
										.toLowerCase();

				//if the method Unit name matches with the regex,
				//we must increase divisor and totaltime.
				if (modifiedName.equals(realName))
				{
					divisor++;
					totalTime += extractedUnit.getTime().getAvg();
				}
			}
			
			//if divisor is not 0 (e.g. we have found at least one method
			//Unit in result for the current method in coef file
			if (divisor > 0)
			{
				//We create a Method object that contains the name the avg...
				Bytecode byth = new Bytecode();
				byth.setName(realName.toUpperCase());
				byth.setAvg(totalTime / divisor);
				byth.setNbocc(coeffBytecodes.getBytecode(i).getNbocc());
				bytecodes.addBytecode(byth);
			}
		}
		
		bytecodes.setTotalocc(refRes.getDomain(index).getBytecodes().getTotalocc());
		return bytecodes;
	}
	
	/** 
	 * Creates and sets a "methods" object.
	 * @param coeffRes		the reference "results" object.
	 * @param extractorRes	the extracted results.
	 * @param index				the index of the considered domain.
	 * @return	the set methods.
	 */
	private static Methods setBaseMethods (Results coeffRes, ExtractorResult extractorRes, int index){
		// empty "methods" object creation
		Methods methods = new Methods();
		
		Methods coeffMethods = coeffRes.getDomain(index).getMethods();
		Metric extractedMetric = extractorRes.getMetric(0);
		
		
		Log.log[Resources.getVerboseFilter()].println(12,"Method count for domain (" +index + ") " + coeffMethods.getMethodCount());
		
		//for each method in the coefs file, we search if a test
		//is corresponding in the results...
		for(int i=0;i<coeffMethods.getMethodCount();i++)
		{
			//the current name of the method in coefs file
			String realName = coeffMethods.getMethod(i).getName();
			//useful for average calculus
			int divisor = 0;
			double totalTime = 0.0;
			
			for(int j=0;j<extractedMetric.getUnitCount();j++)
			{
				//for each method Unit in the results,
				//we get the tested method name
				Unit extractedUnit = extractedMetric.getUnit(j);
				String originalUnitScript = extractedUnit.getScript();
				if (!originalUnitScript.startsWith("scripts.jcapi"))
					continue;
				String modifiedUnitScript = scriptFormatting(originalUnitScript);
				String originalUnitName = extractedUnit.getName();
				String modifiedUnitName = nameFormatting(originalUnitName);
				
				String modifiedCompletedName = modifiedUnitScript+"."+modifiedUnitName;
				
				//The string used as regular expression
				//is like "javacard.*framework.*ownerpin.*check" for the
				//javacard.framework.ownerpin.check method
				String regEx = realName.replaceAll("\\.",".*").toLowerCase();
				regEx = regEx.replaceAll(" with 2 parameters", "");
				regEx = regEx.replaceAll(" with 5 parameters", "iv");

				//if the method Unit name matches with the regex,
				//we must increase divisor and totaltime.
				if (modifiedCompletedName.matches(regEx))
				{
					divisor++;
					totalTime += extractedUnit.getTime().getAvg();
				}
			}
			
			//if divisor is not 0 (e.g. we have found at least one method
			//Unit in result for the current method in coef file
			if (divisor > 0)
			{
				//We create a Method object that contains the name the avg...
				Method meth = new Method();
				meth.setName(realName);
				meth.setAvg(totalTime / divisor);
				meth.setNbocc(coeffMethods.getMethod(i).getNbocc());
				methods.addMethod(meth);
			}
		}
		
		methods.setTotalocc(coeffMethods.getTotalocc());
		
		Log.log[Resources.getVerboseFilter()].println(12, "Methods vector countains : " + methods.getMethodCount() + " methods.");
		return methods;
		
	}

	private static String scriptFormatting(String s)
	{
		s = s.replaceFirst("scripts.jcapi.javacardx_","javacardx.");
		s = s.replaceFirst("scripts.jcapi.javacard_","javacard.");
		s = s.substring(0,s.lastIndexOf("."));
		return s.toLowerCase();
	}
	
	private static String nameFormatting(String s)
	{
		return s.replaceFirst("[_ \\(].*","").toLowerCase();
	}
	
	/** 
	 * Creates and sets a "domain" object.
	 * @param bytecodes 	the "bytecodes" object used to set the domain.
	 * @param methods		the "methodes" object used to set the domain.
	 * @param coeffRes 		the reference "results" object.
	 * @param index_domain the index of the considered domain.
	 * @return the set domain.
	 */
	private static Domain setBaseDomain (Bytecodes bytecodes, Methods methods, Results coeffRes, int index_domain) {
		// empty "domain" object creation
		Domain domain = new Domain();
		
		// the "domain" is set with the "bytecodes" and "methods" objects
		domain.setBytecodes(bytecodes);
		domain.setMethods(methods);
		
		// the "domain" note is set
		double mark = setMark (bytecodes, methods, refResults, index_domain); 
		domain.setMark(mark);
		
		Log.log[Resources.getVerboseFilter()].println(12, "Computed Mark for domain("+index_domain+")    is:  " + mark);
		
		// the "domain" name is set
		String transport_dom_name = null;
		String identity_dom_name = null;
		String financial_dom_name = null;
		
		if (Resources.getLanguage().equals("en")){
			transport_dom_name = "Transport";
			identity_dom_name = "Identity";
			financial_dom_name = "Banking";
		}
		else if (Resources.getLanguage().equals("fr")){
			transport_dom_name = "Transport";
			identity_dom_name = "Identite";
			financial_dom_name = "Bancaire";
		}
		else { // default = english
			transport_dom_name = "Transport";
			identity_dom_name = "Identity";
			financial_dom_name = "Banking";
		}
		
		switch(index_domain){
		case 0 : domain.setName(transport_dom_name);break;
		case 1 : domain.setName(identity_dom_name); break;
		case 2 : domain.setName(financial_dom_name); break;
		default: break;
		}
		
		return domain;
	}
	
	/** 
	 * Creates a *.xml file that will be the profiler database.
	 * @param pathDatabase	the path where the *.xml file will be saved.
	 * @throws ProfilerException
	 */
	private static void createXmlDatabase (File database) throws ProfilerException{	
		Log.log[Resources.getVerboseFilter()].println(4,"Populating Database : " + Resources.getDatabase());
		
		// empty "base" object creation
		Base base = new Base();
		
		// empty "results" object creation
		Results results = new Results();
		
		// gets all average available
		setAverageResults(results);
		
		Log.log[Resources.getVerboseFilter()].println(4,"Domain Chart Style : " + Resources.getDomainStyle());
		for (int i=0;i<refResults.getDomainCount(); i++){
			// a "domain" object is created and set
			Bytecodes bytecodes = setBaseBytecodes(refResults,extractedResults,i);
			Methods methods = setBaseMethods(refResults,extractedResults,i);
			Domain domain = setBaseDomain(bytecodes,methods,refResults,i);
			
			
			// a kind of charts is chosen for the domains charts
			IChart chart = null;
			if (Resources.getDomainStyle().equals("Bar"))
				chart = new BarCharts();
			else if (Resources.getDomainStyle().equals("Spider"))
				chart = new SpiderCharts();
			else
				chart = new BarCharts(); // default kind
			
			// charts of the considered domaine creation
			chart.createDomainCharts(bytecodes,methods,averageResult,i,Resources.getVerboseFilter());
			// the domaine is added
			results.addDomain(domain);     		
		} // end for
		
		// a kind of charts is chosen for the notes chart
		IChart mark = null;
		if (Resources.getMarkStyle().equals("Bar"))
			mark = new BarCharts();
		else if (Resources.getMarkStyle().equals("Spider"))
			mark = new SpiderCharts();
		else 
			mark = new BarCharts(); // default
		
		Log.log[Resources.getVerboseFilter()].println(4,"Mark Chart Style : " + Resources.getMarkStyle());
		
		// marks chart creation
		mark.createMarksChart(results,Resources.getVerboseFilter());
		
		// debug
//		results.getDomain(0).setMark(0.5);
//		results.getDomain(1).setMark(0.6);
//		results.getDomain(2).setMark(0.7);
		
		// global card mark calculation
		double mark_transport = results.getDomain(0).getMark();
		double mark_identity = results.getDomain(1).getMark();
		double mark_banking = results.getDomain(2).getMark();
		
		results.setMark((mark_transport+mark_banking+mark_identity)/results.getDomainCount());
		
		Log.log[Resources.getVerboseFilter()].println(4,"Transport mark: " + mark_transport);
		Log.log[Resources.getVerboseFilter()].println(4,"identity mark: " + mark_identity);
		Log.log[Resources.getVerboseFilter()].println(4,"Banking  mark: " + mark_banking);
		
		// empty "environnement" object creation
		Environnement env = new Environnement();
		// the environnement is set
		env.setHost(System.getProperty("os.name") + " " + System.getProperty("os.version"));
		env.setVMJava(System.getProperty("java.vm.name") + " " + System.getProperty("java.vm.version"));
		
		// the base is set
		base.setResults(results);
		base.setEnvironnement(env);
		base.setBench(rawResults);
		base.setManagerConfig(managerConfig);
		base.setExtractedResult(extractedResults);
		base.setLang(Resources.getLanguage());
		
		// java object to *.xml file transformation (marshalling)
		try{
			FileWriter writer = new FileWriter(database);     
			base.marshal(writer);
		} catch (Exception e){
			throw new ProfilerException (e);
		}            
	}
	
	/** 
	 * Create a *.html file that will display the results.
	 * @param xml	the path to the profiler database.
	 * @param xsl	the path to the stylesheet.
	 * @param html	the path where the output file will be saved.
	 * @throws ProfilerException
	 */
	private static void createHTML(File xml, File xsl, File html) throws ProfilerException{
		try{
			// Creation of the DOM source (Document Object Model)
			DocumentBuilderFactory fabriqueD = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructeur = fabriqueD.newDocumentBuilder();
			Log.log[Resources.getVerboseFilter()].println(4,"Loading Database : " + xml);
			Document document = constructeur.parse(xml);
			Source source = new DOMSource(document);
			
			// Creation of the output file
			Result result = new StreamResult(html);
			
			// Configuration of the Transformer 
			TransformerFactory fabriqueT = TransformerFactory.newInstance();
			Log.log[Resources.getVerboseFilter()].println(4,"Loading StyleSheet : " + xsl);
			StreamSource stylesource = new StreamSource(xsl);
			Transformer transformer = fabriqueT.newTransformer(stylesource);
			transformer.setOutputProperty(OutputKeys.METHOD, "html");
			
			// Transformation
			Log.log[Resources.getVerboseFilter()].println(4,"Saving HTML file : " + html);
			transformer.transform(source,result);
		}catch(Exception e){
			throw new ProfilerException (e);
		}
	}
	
	/**
	 * Main method. Instanciates this class, processes the command-line arguments, 
	 * and executes the tool.
	 */
	public static void main(String[] args) {
		tool = "profiler";
		options = new String[][] {
				{ "i", "inputs", "4", "Select the useful XML files for the database creation. The path order is : <raw results> <extracted results> <manager configuration> <reference results>. Put 0 for a path you don't want to change."},
				{ "mcs", "markStyle","1","Select the marks chart style. "},	
				{ "dcs", "domainStyle","1","Select the domains chart style. "},
				{ "lg", "lang", "1","Select the language of the *.html file. "},
				{ "db", "database", "1","Select the *.xml profiler database file location. "},
				{ "ss", "stylesheet", "1","Select the stylesheet location. "},
				{ "o", "output", "1", "Select the *.html output file location."}
		};
		lib.main.Main.main(args);
		try {
			execute();
		} catch (ProfilerException e) {
			e.printStackTrace();
			Log.fatal(e.getMessage());
		}
	}
}

