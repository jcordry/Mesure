package lib.chart;

import java.io.File;
import java.util.Hashtable;

import lib.log.Log;
import lib.xml.profiler_result.Bytecode;
import lib.xml.profiler_result.Bytecodes;
import lib.xml.profiler_result.Method;
import lib.xml.profiler_result.Methods;
import lib.xml.profiler_result.Results;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import tools.profiler.Resources;

public abstract class IChart {
	
	/** Creates charts where bytecodes (or methods) averages execution time are plotted.
	 * 
	 * @param bytecodes	the "bytecodes" object used to get data about each bytecode.
	 * @param methods	the "methods" object used to get data about each method.
	 * @param indexdom	the index of the considered domain.
	 * @param LOG		the log of the tool.
	 */
	public void createDomainCharts (Bytecodes bytecodes, Methods methods,Hashtable<String,Double>average,int indexdom, int LOG){
    	
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int saveCase;

		Log.log[Resources.getVerboseFilter()].println(8, "Create Domain Chart for domain : " + indexdom);
		
    	Log.log[Resources.getVerboseFilter()].println(8, "Populates Bytecodes");
    	if (bytecodes.getBytecodeCount() == 0) {
    		Log.log[Resources.getVerboseFilter()].println(8, "   - NO bytecode");
    	}
		// the database is populated
    	for (int i=0; i<bytecodes.getBytecodeCount();i++){
    		Bytecode b = bytecodes.getBytecode(i);
    		double avg_ref = average.get(b.getName().toLowerCase())==null?0:average.get(b.getName().toLowerCase()).doubleValue();
    		double avg = b.getAvg();
    		String name = b.getName();
    		name = name.substring(((name.lastIndexOf(".")==-1)?0:name.lastIndexOf(".")),name.length());
    		
			Log.log[Resources.getVerboseFilter()].println(12, "methodname:" + name + " :   avgref : " + avg_ref + "    avg: + " + b.getAvg());

    		// a data is added to the database
    		addData(avg, avg_ref, name, dataset);
    	}
    	
    	// the vm chart is plotted
    	JFreeChart chart_vm = createChart(dataset);
    	// the vm chart is saved into a *.png file
    	saveCase = indexdom*2; 
		saveChart(chart_vm,saveCase,LOG);
    	
    	
    	// the database is cleared
    	dataset.clear();
    	
    	Log.log[Resources.getVerboseFilter()].println(8, "Populates Methods");
    	if (methods.getMethodCount() == 0) {
    		Log.log[Resources.getVerboseFilter()].println(8, "   - NO method");
    	}

    	// the database is populated
    	for (int j=0; j<methods.getMethodCount();j++){
    		Method m = methods.getMethod(j);
    		double avg_ref = average.get(m.getName().toLowerCase())==null?0:average.get(m.getName().toLowerCase()).doubleValue();
    		double avg = m.getAvg();
    		String name = m.getName();
    		name = name.substring(((name.lastIndexOf(".")==-1)?0:name.lastIndexOf(".")),name.length());

//    		Log.log[Resources.getVerboseFilter()].println(8, "add method : " + m.getName());
			Log.log[Resources.getVerboseFilter()].println(12, "methodname:" + name + " :   avgref : " + avg_ref + "    avg: + " + m.getAvg());
 			
    		// a data is added to the database
    		addData(avg, avg_ref, name, dataset);
    	}
    	
    	// the api chart is plotted
    	JFreeChart chart_api = createChart(dataset);
    	// the api chart is saved into a *.png file
    	saveCase = indexdom*2 + 1; 
    	saveChart(chart_api,saveCase,LOG);
	}
	
	
	/** Creates a chart where domain notes are plotted.
	 * 
	 * @param results	the "results" object used to get data about domains notes.
	 * @param LOG		the log of the tool.
	 */
	public void createMarksChart (Results results, int LOG){
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		Log.log[Resources.getVerboseFilter()].println(12, "Create Marks chart" );
				
		// the database is populated
		for (int i=0; i<results.getDomainCount();i++){
			double note_dom = results.getDomain(i).getMark();
			String name = results.getDomain(i).getName();
			name = name.substring(((name.lastIndexOf(".")==-1)?0:name.lastIndexOf(".")),name.length());
			
			Log.log[Resources.getVerboseFilter()].println(12, "Note for domain " + name + " is " + note_dom);
					
			// a data is added to the database
			addData(note_dom, 1, name, dataset);
		}
		
		// the chart is plotted
		JFreeChart chart_notes = createChart(dataset);
		// the chart is saved
		saveChart(chart_notes,6,LOG);
	}
	
	
	/** Adds a data to be plotted.
	 * 
	 * @param value 	the value of the data. 	
	 * @param basevalue the reference value of the data.
	 * @param name 		the name of the data.
	 * @param dataset 	the dataset in which the data will be added.
	 */
	public void addData(double value, double basevalue,String name, DefaultCategoryDataset dataset){
		String serie1 = null;
	  	String serie2 = null;
	  	
    	if (Resources.getLanguage().equals("fr")){
    		serie1 = "Résultat";
    	  	serie2 = "Référence";
    	}
    	else if (Resources.getLanguage().equals("en")){
    		serie1 = "Result";
    	  	serie2 = "Reference";
    	}
    	else { // default = english
    		serie1 = "Result";
    	  	serie2 = "Reference";
    	}
	  	
	  	
	  	dataset.setValue(value, serie1, name);
	  	dataset.setValue(basevalue, serie2, name);
	}
	
	
	/** Creates a chart of a specific kind.
	 * 
	 * @param dataset the dataset to be plotted.
	 * @return the created chart.
	 */
	public abstract JFreeChart createChart(DefaultCategoryDataset dataset);
	
	
	/** Save a chart into a *.png file.
	 * 
	 * @param chart the chart to be saved.
	 * @param i 	the "save case" selector.
	 * @param LOG 	the log of the tool.
	 */
	public void saveChart(JFreeChart chart, int i, int LOG){
		
		try{		  
			String chartsfolder = "../tmp/charts/";
			File chartfile = new File(chartsfolder);
			if (!chartfile.exists()){
				// directory creation
				chartfile.mkdir();
			}
			
			switch (i){
			case 0: ChartUtilities.saveChartAsPNG(new File(chartsfolder + "chart_vm_transport.png"), chart, 450, 326);
					Log.log[LOG].println("Ploting vm chart : " + chartsfolder + "chart_vm_transport.png"); break;
			case 1: ChartUtilities.saveChartAsPNG(new File(chartsfolder + "chart_api_transport.png"), chart, 450, 326);
					Log.log[LOG].println("Ploting api chart : " + chartsfolder + "chart_api_transport.png"); break;
			case 2: ChartUtilities.saveChartAsPNG(new File(chartsfolder + "chart_vm_identite.png"), chart, 450, 326);
					Log.log[LOG].println("Ploting vm chart : " + chartsfolder + "chart_vm_identite.png"); break;
			case 3: ChartUtilities.saveChartAsPNG(new File(chartsfolder + "chart_api_identite.png"), chart, 450, 326);
					Log.log[LOG].println("Ploting api chart : " + chartsfolder + "chart_api_identite.png"); break;
			case 4: ChartUtilities.saveChartAsPNG(new File(chartsfolder + "chart_vm_bancaire.png"), chart, 450, 326);
					Log.log[LOG].println("Ploting vm chart : " + chartsfolder + "chart_vm_bancaire.png"); break;
			case 5: ChartUtilities.saveChartAsPNG(new File(chartsfolder + "chart_api_bancaire.png"), chart, 450, 326);
					Log.log[LOG].println("Ploting api chart : " + chartsfolder + "chart_api_bancaire.png"); break;
			case 6: ChartUtilities.saveChartAsPNG(new File(chartsfolder + "notes_chart.png"), chart, 350, 250);
					Log.log[LOG].println("Ploting notes chart : " + chartsfolder + "notes_chart.png"); break;
			default:break;}
		}catch (Exception ioe){System.out.println("file creation failed"); };
	}
}