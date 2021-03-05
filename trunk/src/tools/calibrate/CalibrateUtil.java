package tools.calibrate;

/*
 * Copyright (c) 2006 Mesure Project
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

import java.util.Vector;

import scripts.templates.NoResourceException;
import scripts.templates.TemplateScript;
import benchs.lib.templates.Util;
import lib.cad.CADException;
import lib.cad.TimeCAD;
import lib.chrono.TimeMeasure;
import lib.log.Log;
import lib.xml.test_result.TestCase;
import lib.xml.test_result.TestResult;

/**
 * This class gives some usefull statistical tools to perform the calibration
 * and to compute the regression analysis. The basis for this class is the
 * lib.xml.test_result.TestCase object
 */
public class CalibrateUtil {
	/**
	 * Log specific to this class.
	 */
	private static final int LOG = lib.log.Log.getASpecificLogEntry("CALIBRATE");

	/**
	 * The population of measures on which we compute
	 */
	private TestCase [] population;
	
	/**
	 * The average value of the population
	 */
	private double avg;
	
	/**
	 * the standard deviation on the population
	 */
	private double stdDeviation;
	
	/**
	 * The loop factor of the linear regression analysis ie the B in the following equation :
	 * Y=B*X+A
	 * Y being the measured temporal performance,
	 * X being the loopSize,
	 * A being the basic overhead.
	 */
	private double betaRegression;

	/**
	 * The constant of the linear regression analysis ie the A in the following equation :
	 * Y=B*X+A
	 * Y being the measured temporal performance,
	 * X being the loopSize,
	 * B being the loop related factor.
	 */
	private double alphaRegression;
	
	/**
	 * The maximum number of loops to be performed within a test.
	 * As of 07.07, this the loop size is the short value of P2*P2,
	 * P2 being a byte. The maximum loop size is therefore 181 :
	 * 181*181=32761 which is the maximum squarre byte value coded as
	 * a positive short. The biggest positive short is 32767.
	 */
	public final static int X_MAX = 181;
	
	/**
	 * The maximum number of loops to be performed within a test.
	 */
	private int maxLoop = X_MAX;
	
	/**
	 * The minimum number of loops to be performed within a test 
	 */
	private int minLoop;
	
	
	/**
	 * Creates a CalibrateUtil for a given population.
	 * @param population a population of <tt>TestCase</tt> objects.
	 */
	public CalibrateUtil(TestCase[] population) {
	  this.population = population;
	  computeAvg();
	  computeStdDeviation();
	}

	/**
	 * @return Returns the alphaRegression.
	 */
	public double getAlphaRegression() {
		return alphaRegression;
	}


	/**
	 * @return Returns the betaRegression.
	 */
	public double getBetaRegression() {
		return betaRegression;
	}

	/**
	 * @return Returns the avg.
	 */
	public double getAvg() {
		return avg;
	}

	/**
	 * @return Returns the population.
	 */
	public TestCase [] getPopulation() {
		return population;
	}

	/**
	 * @return Returns the stdDerivation.
	 */
	public double getStdDeviation() {
		return stdDeviation;
	}
	
	public CalibrateUtil() {
		super();
	}

	/**
	 * @return Computes the average of the population values
	 */
	public double computeAvg() {
		avg = 0.0;
		int nbtest = 0;
		for (int i = 0; i < population.length; i++) {
			for (int j = 0; j < population[i].getTestResult().getTimeMeasure().length; j++) {
				avg+=population[i].getTestResult().getTimeMeasure(j).getMeasure();;
				nbtest++;
			}
		}
		avg=avg/nbtest;
		Log.log[LOG].println(8, "Population Average: "+avg);
		return(avg);
	}
	
	/**
	 * Get the loopSize for a given X (aka P2).
	 * That is X*X, as of 06.07
	 * @param X
	 * @return
	 */
	public int getLoopSize(int X) {
		return(Util.loopSize((short)X));
	}
	
	/**
	 * Performs a regression analysis on the population.
	 * Computes the alphaRegression (A) and the betaRegression (B) elements in the equation
	 * Y=B*X+A
	 * To solve this, we need to solve :
	 * B = Sum((x_i-X)*(y_i-Y))/Sum((x_i-X)*(x_i-X))
	 * A = Y-X*B
	 * where X is the average number of loops
	 * Y is the average time measured
	 */
	public void computeRegression() {
		computeStdDeviation();
		double aux1 = 0.0;
		double aux2 = 0.0;
		double avgLoopSize = 0.0;
		int sizePop = 0;
		for (int i = 0; i < population.length; i++) {
			sizePop += population[i].getTestResult().getTimeMeasure().length;
			avgLoopSize += population[i].getTestResult().getTimeMeasure().length*getLoopSize(population[i].getX());
			// X*X*(#measures)
		}
		avgLoopSize = avgLoopSize/((double)sizePop);
		for (int i = 0; i < population.length; i++) {
			TestResult tr = population[i].getTestResult();
			for (int j = 0; j < tr.getTimeMeasure().length; j++) {
				Log.log[LOG].println(9, "TIMEMEASURE : " + tr.getTimeMeasure()[j].getMeasure() + ".... X:"+getLoopSize(population[i].getX()));
				double xi = getLoopSize(population[i].getX());
				double yi = (tr.getTimeMeasure()[j].getMeasure());
				aux1 += (yi-avg) * (xi-avgLoopSize);
				aux2 += (xi-avgLoopSize) * (xi-avgLoopSize);
				Log.log[LOG].println(9, "computeRegression; xi : " + xi
						+ "\tx : " + avgLoopSize
						+ "\tyi : " + yi
						+ "\ty : " + avg);
				Log.log[LOG].println(9, "a : " + (xi-avgLoopSize)
						+ "\tb : " + (yi-avg) +
						"\ta*a : " + ((xi-avgLoopSize)*(xi-avgLoopSize)) +
						"\ta*b : " + ((xi-avgLoopSize)*(yi-avg)) + ")");
				Log.log[LOG].println(9, "t1 : " + (xi-avgLoopSize)*(yi-avg) + "\tt2 : "+(xi-avgLoopSize)*(xi-avgLoopSize));
				Log.log[LOG].println(9, "aux1 : " + aux1 + "\taux2 : "+aux2+"\n\n");
			}
		}
		betaRegression = aux1/aux2;
		alphaRegression = avg - (betaRegression * avgLoopSize);
		Log.log[LOG].println(8, "ALPHA : " + alphaRegression+"; BETA : "+ betaRegression+"\n\n");
	}
	
	
	/**
	 * Computes the standard deviation
	 * @return the standard deviation that is :
	 * squarreRoot((Sum((x_i-X)*(x_i*X)))/N)
	 * Where X is the average time measured
	 */
	public double computeStdDeviation() {
		computeAvg();
		double aux = 0;
		for (int i = 0; i < population.length; i++) {
			for (int j = 0; j < population[i].getTestResult().getTimeMeasure().length; j++) {
				aux += (population[i].getTestResult().getTimeMeasure(j).getMeasure()-avg)*(population[i].getTestResult().getTimeMeasure(j).getMeasure()-avg);
			}
		}
		stdDeviation = java.lang.Math.sqrt((1.0/((double)population.length)) * aux);
		return(stdDeviation);
	}
	
	/**
	 * Verifies if the population is precise enough for a given threashold.
	 * @param threashold
	 * @return true if the precision of the population is enough 
	 */
	private boolean isPreciseEnough(double threashold) {
		PrecisionCursor p = new PrecisionCursor(threashold);
		return (p.isPreciseEnough(avg, stdDeviation));
	}
	
	/**
	 * appends several Measure arrays into just one.
	 * @param set : the array of array of Measures to be appended
	 * @return the CalibrateUtil with a population corresponding to all the arrays.
	 */
	private static CalibrateUtil makeNewPopulation(TestCase [] [] set) {
		int length = 0;
		for (int i = 0; i < set.length; i++) {
			length+=set[i].length;
		}
		TestCase [] population = new TestCase[length];
		int index = 0;
		for (int i = 0; i < set.length; i++) {
			System.arraycopy(set[i], 0, population, index, set[i].length);
			index += set[i].length;
		}
		return (new CalibrateUtil(population));
	}
	
	
	/**
	 * Makes one CalibrateUtil object out of a Vector of CalibrateUtils.
	 * The populations of the different objects are mixed into the the new one.
	 * @param statSet
	 * @return the CalibrateUtil that contains all the populations
	 */
	public static CalibrateUtil makeNewCalibrateUtil(Vector <CalibrateUtil> statSet) {
		TestCase [] [] measureSet = new TestCase [statSet.size()][statSet.get(0).getPopulation().length]; // !!! check
		for (int i = 0; i < statSet.size(); i++) {
			measureSet[i] = statSet.get(i).getPopulation();
		}
		return (makeNewPopulation(measureSet));
	}
	
	/**
	 * Binary search throughout a loop size ordered measure array to find the most appropriate loopsize (depending
	 * on a given threshold) 
	 * @param statSet
	 * @param precisionCursor
	 * @return
	 */
	private static int getAppropriateLoopSize(CalibrateUtil [] statSet, double precisionCursor) {
		Log.log[LOG].println(6, "getAppropriateLoopSize");
		int p = 0;
		int low = 0;
		int high = statSet.length-1;
		while (low < high) {
			p = low+((high-low)/2);
			Log.log[LOG].println(6, "HIGH : "+high+" LOW : "+low+" P : "+
					p+" precisionCursor : "+statSet[p].getStdDeviation() +
					"," +precisionCursor);
			if ((statSet[p].isPreciseEnough(precisionCursor)) && (statSet[p].isAvgAboveOneSec())) {
				high = p;
			} else {
				low = p+1;
			}
		}
		return(low);
	}
	
	/**
	 * Returns true if the average of the population ie above 1 second
	 * @return
	 */
	private boolean isAvgAboveOneSec() {
		// !!! Nanoseconds is not accurate
		// We should try to get the actual time unit !!!
		return ((new TimeMeasure(getAvg(), TimeMeasure.NANOSECONDS)).compareTo(TimeMeasure.ONE_SECOND) > 0);
		// !!! We could also change the ONE_SECOND for tmin !!!
	}
	
	
	/**
	 * Calibrates the specified test case.
	 * Returns the index within the specified vector of tries, for which the given 
	 * precision is respected.
	 * 
	 * @param v The vector of <tt>CalibrateUtil</tt> object. It has to be ordered in term of loop size and 
	 * the population of each entry has the same loop size.
	 * @param precisionCursor The expected precision.
	 * @param script The script containing the test case to be calibrated.
	 * @param timeCAD The CAD to be used to calibrate the test case.
	 * @param testCaseName The name of the test case to be calibrated.
	 * @param Y The number of executions to be performed off-card when calibrating the test case.
	 * @return an index in the <tt>v</tt> vector.
	 * @throws <tt>ManagerException</tt> if a problem occurs during calibration.
	 */
	private static int searchAppropriateLoopSize (
	    Vector <CalibrateUtil> v, double precisionCursor,TemplateScript script, TimeCAD timeCAD,
	    String testCaseName, int Y) 
	    throws CalibrateException, NoResourceException {
	  Log.log[LOG].println(6,"> Searching for the appropriate loop size...");
	  for (int i = 0; i < v.size(); i++)
	    Log.log[LOG].println(8, "searchAppropriateLoopSize i=" +i+" "+ v.get(i).toString());
		
		int index = getAppropriateLoopSize(v.toArray(new CalibrateUtil[v.size()]), precisionCursor); // the appropriate index within the Vector
		int P2Index = (int) v.get(index).getPopulation()[0].getX(); // the appropriate P2 within the Vector
		Log.log[LOG].println(8, "searchAppropriateLoopSize P2Index="+P2Index);
		Log.log[LOG].println(8, "searchAppropriateLoopSize index="+index
				+"; v.size()-1="+(v.size()-1)
				+"; precisionCursor="+v.get(index).isPreciseEnough(precisionCursor)
				+"; avgAboveOneSec="+v.get(index).isAvgAboveOneSec());
		// If we are at one the bounds of the Vector
		if (((index == 0) && (v.get(index).isPreciseEnough(precisionCursor)) // and if the population is too precise
				) || ((index == v.size()-1) &&
						(! ((v.get(index).isPreciseEnough(precisionCursor))
								&& (v.get(index).isAvgAboveOneSec()))))) { // or if the population is too imprecise
			// then we need to get some new values
			if (P2Index >= v.get(index).maxLoop) { // actually, this should never be > but allways ==
				return (index);
			}
			if (P2Index <= v.get(index).minLoop || (P2Index == 1)) { // actually, this should never be < but allways ==
				Log.log[LOG].println(8, "searchAppropriateLoopSize index="+index+"; precisionCursor="+precisionCursor);
				return (index);
			}
			if ((v.get(index).isPreciseEnough(precisionCursor)) && (index == 0)&& (v.get(index).isAvgAboveOneSec())) {
				int P2valuesToBeInserted = Math.max(P2Index-(P2Index/2), v.get(index).minLoop);
				v.insertElementAt(getNewValues(script, timeCAD, testCaseName, P2valuesToBeInserted, Y), index);
			} else if ((v.get(index).isPreciseEnough(precisionCursor)) && (index == 0)) {
				// If we are too precise and under 1s, we should take the first value that is
				// above 1s, if this doesn't exist (it should be the case), we need to create it first
					v.add(getNewValues(script, timeCAD, testCaseName, Math.min(P2Index+(P2Index/2), v.get(index).maxLoop), Y));
				} else {
					int P2valuesToBeInserted = Math.min(P2Index+(P2Index/2), v.get(index).maxLoop);
					v.insertElementAt(getNewValues(script, timeCAD, testCaseName, P2valuesToBeInserted, Y), index+1);
				}
			return (searchAppropriateLoopSize(v, precisionCursor, script, timeCAD, testCaseName, Y));
		}
		int direction;
		if (v.get(index).isPreciseEnough(precisionCursor) && (v.get(index).isAvgAboveOneSec())) {
			direction=-1;
		} else {
			direction=1;
		}
		Log.log[LOG].println(8, "searchAppropriateLoopSize direction="+direction);
		int P2OtherIndex = (int) v.get(index+direction).getPopulation()[0].getX();
		Log.log[LOG].println(8, "searchAppropriateLoopSize P2Index="+P2Index+"; P2Other="+P2OtherIndex);

		if (P2Index == P2OtherIndex) {
			return(index);
		}
		if (P2Index+direction == P2OtherIndex) {
			return(index);
		} else {
			v.insertElementAt(getNewValues(script, timeCAD, testCaseName, (P2Index+P2OtherIndex)/2, Y), index+direction+1);
			return (searchAppropriateLoopSize(v, precisionCursor, script, timeCAD, testCaseName, Y));
		}
	}

	/**
	 * Execute the specified test case with the specified loop sizes.
	 * @param script The script containing the test case to be calibrated.
	 * @param timeCAD The CAD to be used to perform the calibration.
	 * @param testCaseName The name of the test case to be calibrated.
	 * @param X The number of executions to be performed on-card.
	 * @param Y The number of  executions to be performed off-card.
	 * @return A <tt>CalibrateUtil</tt> object remembering the parameters and the results.
	 * @throws <tt>ManagerException</tt> if a problem occurs during the execution.
	 */
	public static CalibrateUtil getNewValues(TemplateScript script, TimeCAD timeCAD, String testCaseName, int X, int Y) 
	  throws CalibrateException, NoResourceException {
		Log.log[LOG].println("> Measuring the execution time for X=" + X + " and Y=" + Y + "...");
//		try {
			script.noCheck();
			scripts.templates.TestCase tc = null;
			try {
				script.run(timeCAD,testCaseName,true,true,X,Y);
				tc = (scripts.templates.TestCase) script.getTestCase(testCaseName);
			} catch (NoResourceException e) {
				throw e;
			} catch (CADException e) {
				throw new CalibrateException (e);
			}
			Log.log[LOG].println("> Average measure for X=" + X + 
			    " and Y=" + Y + ": " + tc.getTestResult().getAvg());
			if (tc.getTestResult().getAvg() == 0) {
				throw new CalibrateException("Applet not selectable.");
			}
			lib.xml.test_result.TestCase newtc = new TestCase();
			newtc.setName(testCaseName);
			newtc.setReferenceName(tc.getReferenceName());
			newtc.setCalibrationName(tc.getCalibrationName());
			newtc.setBenchedUnit(tc.getBenchedUnit());
			newtc.setData(tc.getData());
			newtc.setApdu(tc.getApdu());
			newtc.setTestResult(tc.getTestResult());
			newtc.setX(X);
			newtc.setY(Y);
			CalibrateUtil cu = new CalibrateUtil(new TestCase[]{newtc});
			return(cu);
//		} catch (Exception e) {
//			Log.log[LOG].println(0, "Exception while executing test. Force deletion." + e);
//			throw new CalibrateException(e);
//		}
	}
	/**
	 * returns the CalibrateUtil with the smaller loop size with a standard deviation good enough for the given precisionCursor
	 * @param v the Vector that has to be sorted in term of loop size. Each measure of a given entry has to have
	 * the same loop size  
	 * @param precisionCursor the standard deviation which serves as a reference
	 * @return the CalibrateUtil that matches precisionCursor
	 */
	public static CalibrateUtil getAppropriateCalibrateUtil(
	    Vector <CalibrateUtil> v, double precisionCursor, TemplateScript script, TimeCAD timeCAD, 
	    String outTestCase, int Y) 
	    throws CalibrateException, NoResourceException {
	  int appropriateLoopSizeRank = searchAppropriateLoopSize(v,precisionCursor,script,timeCAD,outTestCase,Y);
	  return(v.get(appropriateLoopSizeRank));
	}
	
	/**
	 * method that returns the P2. All the measures within the CalibrateUtil
	 * have to be for the same loop size  
	 * @return the P2 value (aka X)
	 */
	public int getP2() {
		int appropriateP2 = population[0].getX();
		if (appropriateP2 >= maxLoop) {
			appropriateP2 = maxLoop;
		}
		if (appropriateP2 <= minLoop) {
			appropriateP2 = minLoop;
		}
		return(appropriateP2);
	}
	
	public static int getFirstValueWithAvgAbove1sec(Vector <CalibrateUtil> v) throws Exception {
		int i = 0;
		for (; i <v.size(); i++) {
			if (v.get(i).isAvgAboveOneSec()) {break;}
		}
		if (i==v.size()) {
			if (v.get(i-1).isAvgAboveOneSec()) {
				return (i-1);
			} else {
				throw new Exception("getFirstValueWithAvgAbove1sec");
			}
		}
		return (i);
	}
	
	public String toString (){
		StringBuffer sb = new StringBuffer();
		sb.append("POPULATION : [");
		for (int i = 0; i < population.length; i++) {
			for (int j = 0; j < population[i].getTestResult().getTimeMeasure().length; j++) {
				sb.append(population[i].getX()+" - "+population[i].getTestResult().getTimeMeasure(j)+"; ");				
			}
		}
		sb.append("]\nAVG : " + avg + "\nSTDDEV : " + stdDeviation + "\nBETA : "+betaRegression+"\nALPHA : "+alphaRegression+"\n"+
				"log10(mu/precisionCursor)="+Math.log10(avg/stdDeviation)+"\n");
		return(sb.toString());
	}

	public int getMaxLoop() {
		return maxLoop;
	}

	public void setMaxLoop(int maxLoop) {
		this.maxLoop = maxLoop;
	}

	public int getMinLoop() {
		return minLoop;
	}

	public void setMinLoop(int minLoop) {
		this.minLoop = minLoop;
	}
}


