package scripts.jcapi.javacard_framework.util.arrayfillnonatomic;

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

/*
 * $HeadURL: svn+ssh://corentinboe@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/scripts/TemplateScript.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

import lib.cad.CADException;
import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

public class ArrayFillNonAtomicScript extends TemplateScript{
	
	/** The default number of executions to be performed on-card for a test case. */
	private final static int ON_CARD = 39;
	/** The default number of executions to be performed off-card for a test case. */
	private final static int OFF_CARD = TestCase.Y;

	/** The several length of comparison. */
	private short[] lengthRequire = new short[]{
			1,8,64
	};
	
	/** The source offset. */
	private short[] srcOff = new short[]{
			0,64
			};
	
	/** Indicates if the calibration reference test has been set. */
	private boolean isCalibrate = false;
	/** The name of the calibration reference test. */
	private String calibration = "";
	
	/** 
	 * Computes the different parameter.
	 * 
	 * @param len the comparison length
	 * @param src the source offset
	 * @return A byte array containing the several parameters.
	 */
	private byte[] makeParameters(int len, int src){
		byte[] ret = new byte[]{(byte)((lengthRequire[len]>>8)&0xFF),(byte)(lengthRequire[len]&0xFF),
				(byte)((srcOff[src]>>8)&0xFF),(byte)(srcOff[src]&0xFF)};
		return ret;
	}
	
	/**
	 * Generates different test from one test case (with different length and offset).
	 * 
	 * @param def the name of the test case
	 * @param numTest the number of the test case
	 * @param ref the reference test case
	 */
	private void generateTestCase(String def,int numTest,String ref){
		for (int i=0;i<srcOff.length;i++)
		{
			for (int k=0;k<lengthRequire.length;k++)
			{
				TestCase tmp = null;
				tmp=new TestCase(def+lengthRequire[k]+" Offset:"+srcOff[i]
				            ,numTest,ON_CARD,OFF_CARD,makeParameters(k,i),0
				).setReference(ref);
				
				if (!isCalibrate)
				{
					isCalibrate = true;
					calibration = def+lengthRequire[k]+" Offset:"+srcOff[i];
					tmp.forCalibration();
				}
				else tmp.setCalibration(calibration);
				tmp.setBenched("x+RETURN-POP");
				addTestCase(tmp);
			}
		}
	}
	
	/**
	 * Creates a new test script and builds the test cases.
	 * @throws CADException 
	 */
	public ArrayFillNonAtomicScript() throws CADException {
		//Overhead tests
		addTestCase(new TestCase("arrayFill_ref_PersnoTx",1,ON_CARD,OFF_CARD,
				makeParameters(0,0),0));
		addTestCase(new TestCase("arrayFill_ref_PersTx",2,ON_CARD,OFF_CARD,
				makeParameters(0,0),0));
		addTestCase(new TestCase("arrayFill_ref_Trans",3,ON_CARD,OFF_CARD,
				makeParameters(0,0),0));
		addTestCase(new TestCase("arrayFill_ref_Apdu",4,ON_CARD,OFF_CARD,
				makeParameters(0,0),0));
		
		//Card tests
		generateTestCase("arrayFillNonAtomic transient COD:",7,"arrayFill_ref_Trans");
		generateTestCase("arrayFillNonAtomic persistent outside of a transaction:",5,"arrayFill_ref_PersnoTx");
		generateTestCase("arrayFillNonAtomic APDU buffer:",8,"arrayFill_ref_Apdu");
		getTestCase("arrayFill_ref_Trans").setCalibration(calibration);
		getTestCase("arrayFill_ref_PersnoTx").setCalibration(calibration);
		getTestCase("arrayFill_ref_Apdu").setCalibration(calibration);
		
		isCalibrate = false;
		generateTestCase("arrayFillNonAtomic persistent inside a transaction:",6,"arrayFill_ref_PersTx");
		getTestCase("arrayFill_ref_PersTx").setCalibration(calibration);
	}
	
}
