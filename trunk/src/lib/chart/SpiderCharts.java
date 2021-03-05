package lib.chart;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.DefaultCategoryDataset;


public class SpiderCharts extends IChart{

	/** Creates a SpiderWeb chart.
	 * 
	 * @param dataset the dataset to be plotted.
	 * @return the created chart.
	 */
	public JFreeChart createChart(DefaultCategoryDataset dataset){
	
		SpiderWebPlot spider = new SpiderWebPlot(dataset);
		JFreeChart spiderchart = new JFreeChart(spider);
		
		return spiderchart;
	}
}
