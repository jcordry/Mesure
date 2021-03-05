package scripts.jcapi.javacard_framework.util.setshort;

import lib.cad.CADException;
import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

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



/**
 * This class is dedicated to measurements of the <tt>Util.setShort</tt> method.
 */
public class SetShortScript extends TemplateScript{
	
	/** The default number of executions to be performed on-card for a test case. */
	private final static int ON_CARD = 46;
	/** The default number of executions to be performed off-card for a test case. */
	private final static int OFF_CARD = TestCase.Y;
	
	/** The offset. */
	private short[] offset = new short[]{
			0,64
	};
	
	/** Indicates if the calibration reference test has been set. */
	private boolean isCalibrate = false;
	/** The name of the calibration reference test. */
	private String calibration = "";
	
	/**
	 * Generates different test from one test case (with different offset).
	 * 
	 * @param def the name of the test case
	 * @param numTest the number of the test case
	 * @param ref the reference test case
	 */
	private void generateTestCase(String def,int numTest,String ref){
				for (int i=0;i<offset.length;i++)
				{
					TestCase tmp = null;
					tmp=new TestCase(def+offset[i],numTest,ON_CARD,OFF_CARD,
							makeParameters(offset[i]),0
					).setReference(ref);
					
					if (!isCalibrate)
					{
						isCalibrate = true;
						calibration = def+offset[i];
						tmp.forCalibration();
					}
					else tmp.setCalibration(calibration);
					tmp.setBenched("x+RETURN-POP");
					addTestCase(tmp);
				}
	}
	
	/** 
	 * Computes the different parameter.
	 * 
	 * @param off the source offset
	 * @return A byte array containing the several parameters.
	 */
	private byte[] makeParameters(int off){
		byte[] ret = new byte[]{(byte)((off>>8)&0xFF),(byte)(off&0xFF)};
		return ret;
	}

	
	/**
	 * Creates a new test script and builds the test cases.
	 * @throws CADException 
	 */
	public SetShortScript() throws CADException {
		//Overhead tests
		addTestCase(new TestCase("setShort_ref_PersnoTx",1,ON_CARD,OFF_CARD,makeParameters(0),0));
		addTestCase(new TestCase("setShort_ref_PersTx",2,ON_CARD,OFF_CARD,makeParameters(0),0));
		addTestCase(new TestCase("setShort_ref_Trans",3,ON_CARD,OFF_CARD,makeParameters(0),0));
		addTestCase(new TestCase("setShort_ref_Apdu",4,ON_CARD,OFF_CARD,makeParameters(0)
				,0));
		
		//Card tests
		generateTestCase("setShort in a transient array at offset:"
				,7,"setShort_ref_Trans");
		generateTestCase("setShort in a persitent array outside of a transaction at offset:"
					,5,"setShort_ref_PersnoTx");
		generateTestCase("setShort in apdu at offset:"
					,8,"setShort_ref_Apdu");
		getTestCase("setShort_ref_PersnoTx").setCalibration(calibration);
		getTestCase("setShort_ref_Trans").setCalibration(calibration);
		getTestCase("setShort_ref_Apdu").setCalibration(calibration);
		
		isCalibrate = false;
		generateTestCase("setShort in a persistent array inside a transaction at offset:"
				,6,"setShort_ref_PersTx");
		getTestCase("setShort_ref_PersTx").setCalibration(calibration);
		
	}
	
}
