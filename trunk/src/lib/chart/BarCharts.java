package lib.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarCharts extends IChart{

	/** Creates a Bar chart.
	 * 
	 * @param dataset the dataset to be plotted.
	 * @return the created chart.
	 */
	public JFreeChart createChart(DefaultCategoryDataset dataset){
	
		JFreeChart barchart = ChartFactory.createBarChart(
      			null, // title
      			null, // categories
      			null, // values
      			dataset, 
      			PlotOrientation.HORIZONTAL , 
      			true, 
      			false, 
      			false);
		
		return barchart;
	}
}
