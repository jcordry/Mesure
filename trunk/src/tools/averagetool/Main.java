package tools.averagetool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import lib.log.Log;
import lib.main.ResourceException;
import lib.xml.extractor_result.ExtractorResult;
import lib.xml.extractor_result.Metric;
import lib.xml.extractor_result.Unit;
import lib.xml.profiler_result.BytecodeAverage;
import lib.xml.profiler_result.BytecodeAverageList;
import lib.xml.profiler_result.Bytecodes;
import lib.xml.profiler_result.MethodAverage;
import lib.xml.profiler_result.MethodAverageList;
import lib.xml.profiler_result.Methods;
import lib.xml.profiler_result.Results;

public class Main extends lib.main.Main {
	
	private static Vector<String> regExMethodName = new Vector<String>();
	private static Vector<String> regExBytecodeName = new Vector<String>();
	private static Hashtable<String,Double> resultBytecode = new Hashtable<String,Double>();
	private static Hashtable<String,Double> resultMethod = new Hashtable<String,Double>();
	private static Hashtable<String,Integer> resultCount = new Hashtable<String,Integer>();
	private static Vector<ExtractorResult> extractedResults = new Vector<ExtractorResult>();
	private static Results coefsInput;
	private static Results coefsOutput;
	
	/**
	 * Parses the extracted.xml file
	 * 
	 * @throws AverageToolException
	 */
	private static void parseExtractedResults() throws AverageToolException 
	{
		Vector<File> tmp = Resources.getExtractedResults();
		for (int i=0;i<tmp.size();i++)
		{
			try{
				extractedResults.add(ExtractorResult.unmarshal(new FileReader(tmp.get(i))));
			}
			catch(FileNotFoundException e)
			{
				throw new AverageToolException(e);
			}
			catch(org.exolab.castor.xml.ValidationException e)
			{
				throw new AverageToolException(e);
			}
			catch(org.exolab.castor.xml.MarshalException e)
			{
				throw new AverageToolException(e);
			}
		}
	}
	
	/**
	 * Parses the coefs.xml file
	 * 
	 * @throws AverageToolException
	 */
	private static void parseXMLCoeff() throws AverageToolException 
	{
		try{
			coefsInput = Results.unmarshal(new FileReader(Resources.getXMLCoef()));
		}
		catch(FileNotFoundException e)
		{
			throw new AverageToolException(e);
		}
		catch(org.exolab.castor.xml.ValidationException e)
		{
			throw new AverageToolException(e);
		}
		catch(org.exolab.castor.xml.MarshalException e)
		{
			throw new AverageToolException(e);
		}
		
	}
	
	
	/**
	 * Populates the average tool with the useful XML files.
	 * @param args	the paths to the extracted.xml file.
	 * @throws AverageToolException
	 */
	public static void processInput(String[] args) throws AverageToolException 
	{
		assert (args!=null && args.length==1) :	"option -i (or -inputs) requires 1 path for the XML input file.";
		Log.log[Resources.getVerboseFilter()].println(4,"Loading XML file : " + args[0]);
		
		try{
			Resources.setExtractedResults(args[0]);
		}
		catch (ResourceException e)
		{
			throw new AverageToolException(e);
		}
	}
	
	/**
	 * Populates the average tool with the useful XML files.
	 * @param args	the paths to the coefs.xml file.
	 * @throws AverageToolException
	 */
	public static void processCoefFile(String[] args) throws AverageToolException 
	{
		assert (args!=null && args.length==1) :	"option -cf (or -coefFile) requires 1 path for the XML coeff file.";
		Log.log[Resources.getVerboseFilter()].println(4,"Loading XML file : " + args[0]);
		
		try{
			Resources.setXMLCoef(args[0]);
		}
		catch (ResourceException e)
		{
			throw new AverageToolException(e);
		}
	}
	
	/**
	 * Sets the *.xml output file location.
	 * @param args the path to the *.xml output file.
	 * @throws AverageToolException
	 */
	public static void processOutput(String[] args) throws AverageToolException 
	{
		assert (args!=null && args.length==1) : 
			"option -o (or -output) requires a path for the XML output file.";
		Log.log[Resources.getVerboseFilter()].println(4,"Saving XML file : " + args[0]);
		try{
			Resources.setXMLOutput(args[0]);
		}
		catch (ResourceException e)
		{
			throw new AverageToolException(e);
		}
	}
	
	/**
	 * Formats a script name
	 * 
	 * @param s the script name to format
	 * @return the script name formated
	 */
	private static String scriptFormatting(String s)
	{
		s = s.replaceFirst("scripts.jcapi.javacardx_","javacardx.");
		s = s.replaceFirst("scripts.jcapi.javacard_","javacard.");
		s = s.substring(0,s.lastIndexOf("."));
		return s.toLowerCase();
	}
	
	/**
	 * Formats a test name
	 * 
	 * @param s the test name to format
	 * @return the test name formated
	 */
	private static String nameFormatting(String s)
	{
		return s.replaceFirst("[_ \\(].*","").toLowerCase();
	}
	
	
	private static void initMethodsAndBytecodesName()
	{
		for (int i=0;i<coefsInput.getDomainCount();i++)
		{
			Methods coeffMethods = coefsInput.getDomain(i).getMethods();
			for(int j=0;j<coeffMethods.getMethodCount();j++)
			{
				String tmp = coeffMethods.getMethod(j).getName()
					.replaceAll("init with 2 parameters", "init")
					.replaceAll("init with 5 parameters", "initiv")
					.replaceAll("\\.",".*")
					.toLowerCase();
				
				if (!regExMethodName.contains(tmp))
					regExMethodName.add(tmp);
			}
			
			Bytecodes coeffBytecodes = coefsInput.getDomain(i).getBytecodes();
			for(int j=0;j<coeffBytecodes.getBytecodeCount();j++)
			{
				String tmp = coeffBytecodes.getBytecode(j).getName()
					.toLowerCase();
				if (!regExBytecodeName.contains(tmp))
					regExBytecodeName.add(tmp);
			}
		}
	}
	
	
	/**
	 * Adds a unit (gets the time of execution)
	 * 
	 * @param extractedUnit the Unit to add
	 */
	private static void addingUnit(Unit extractedUnit)
	{
		String name = extractedUnit.getName();
		String originalUnitScript = extractedUnit.getScript();
		
		if (originalUnitScript.startsWith("scripts.jcapi"))
		{			
			//for each method Unit in the results,
			//we get the tested method name
			String modifiedUnitScript = scriptFormatting(originalUnitScript);
			String originalUnitName = extractedUnit.getName();
			String modifiedUnitName = nameFormatting(originalUnitName);
			
			String modifiedCompletedName = modifiedUnitScript+"."+modifiedUnitName;
			
			
			//if the method Unit name matches with the regex,
			//we must increase divisor and totaltime.
			for (int i=0;i<regExMethodName.size();i++)
			{
				String regEx = regExMethodName.get(i);
				if (modifiedCompletedName.matches(regEx))
				{
					String realName = regEx.replaceAll("\\.\\*",".");
					if (realName.contains("initiv"))
						realName.replaceAll("initiv","init with 5 parameters");
					else if (realName.contains("init"))
						realName.replaceAll("init","init with 2 parameters");
					
					if (resultMethod.containsKey(realName))
					{
						Double tmp = resultMethod.get(realName);
						Integer tmpInt = resultCount.get(realName);
						resultMethod.put(realName,
								new Double(extractedUnit.getTime().getAvg()+tmp));
						resultCount.put(realName,new Integer(tmpInt+1));
					}
					else
					{
						resultMethod.put(realName,new Double(extractedUnit.getTime().getAvg()));
						resultCount.put(realName,new Integer(1));
					}
					break;
				}
			}
		}
		else 
		{
			String modifiedName = name
									.replaceAll("[^_]{1,1}[0123456789]+","")
									.toLowerCase();
			for (int i=0;i<regExBytecodeName.size();i++)
			{
				String regEx = regExBytecodeName.get(i);
				if (modifiedName.equals(regEx))
				{
					String realName = regEx.replaceAll("\\*","").toUpperCase(); 
					//System.out.println(realName);
					
					if (resultBytecode.containsKey(realName))
					{
						Double tmp = resultBytecode.get(realName);
						Integer tmpInt = resultCount.get(realName);
						resultBytecode.put(realName,
								new Double(extractedUnit.getTime().getAvg()+tmp));
						resultCount.put(realName,new Integer(tmpInt+1));
					}
					else
					{
						resultBytecode.put(realName,new Double(extractedUnit.getTime().getAvg()));
						resultCount.put(realName,new Integer(1));
					}
					break;
				}
			}
		}
		
	}
	
	/**
	 * Collects all results and calculate the average.
	 * 
	 * @throws AverageToolException
	 */
	private static void execute() throws AverageToolException
	{
		coefsOutput = new Results();
		initMethodsAndBytecodesName();
		
		//Collects resuls
		for(int i=0;i<extractedResults.size();i++)
		{
			ExtractorResult res = extractedResults.get(i);
			Metric extractedMetric = res.getMetric(0);
			for(int j=0;j<extractedMetric.getUnitCount();j++)
			{
				addingUnit(extractedMetric.getUnit(j));
			}
		}
		
		
		//put the different averages in coefs.xml
		Enumeration enu = resultBytecode.keys();
		BytecodeAverageList bal = new BytecodeAverageList();
		while (enu.hasMoreElements())
		{
			String name = (String)enu.nextElement();
			BytecodeAverage ba = new BytecodeAverage();
			ba.setName(name);
			ba.setAvg(resultBytecode.get(name)/resultCount.get(name));
			bal.addBytecodeAverage(ba);
		}
		coefsOutput.setBytecodeAverageList(bal);
		
		enu = resultMethod.keys();
		MethodAverageList mal = new MethodAverageList();
		while (enu.hasMoreElements())
		{
			String name = (String)enu.nextElement();
			//System.out.println(name);
			MethodAverage ma = new MethodAverage();
			ma.setName(name);
			ma.setAvg(resultMethod.get(name)/resultCount.get(name));
			mal.addMethodAverage(ma);
		}
		coefsOutput.setMethodAverageList(mal);
		
		
		for (int i=0;i<coefsInput.getDomainCount();i++)
			coefsOutput.addDomain(coefsInput.getDomain(i));
	}
	
	
	/**
	 * Saves the new calculated coefs.xml
	 * 
	 * @throws AverageToolException
	 */
	private static void saveCoefsFile() throws AverageToolException 
	{
		try {
			Writer xmlWriter = new FileWriter(Resources.getXMLOutput());
			coefsOutput.marshal(xmlWriter);
			xmlWriter.close();
		} catch (Exception e) {
			throw new AverageToolException(e);
		}		
		
	}
	
	public static void main(String[] args) 
	{
		tool = "averageTool";
		options = new String[][]{
				{"i" ,"input"     ,"1","The extracted file. " +
					"If not specified, the \"extracted.xml\" file provided in " +
				"the \"config\\averageTool\" directory is used."},
				{"cf","coefFile" ,"1","The XML file containing the coefficient. " +
				"If not specified, the \"config\\profiler\\coefs.xml\" file is used."},
				{"o","output" ,"1","The XML file containing the results. " +
				"If not specified, the \"tmp\\coefs.xml\" file is used."}
		};
		lib.main.Main.main(args);
		
		try {
			parseExtractedResults();
			parseXMLCoeff();
			
			execute();
			
			saveCoefsFile();
		} catch (AverageToolException e) {
			e.printStackTrace();
			Log.fatal(e.getMessage());
		}
	}
}
