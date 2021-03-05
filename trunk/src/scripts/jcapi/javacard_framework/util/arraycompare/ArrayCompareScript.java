package scripts.jcapi.javacard_framework.util.arraycompare;

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

public class ArrayCompareScript extends TemplateScript{
	
	/** The default number of executions to be performed on-card for a test case. */
	private final static int ON_CARD = 36;
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
	/** The destination offset */
	private short[] destOff = new short[]{
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
	 * @param dest the destination offset
	 * @param off the offset of the difference (this parameter is usefull just
	 * 			  for the test case n°8...)
	 * @return A byte array containing the several parameters.
	 */
	private byte[] makeParameters(int len, int src, int dest, int off){
		byte[] ret = new byte[]{(byte)((lengthRequire[len]>>8)&0xFF),(byte)(lengthRequire[len]&0xFF),
				(byte)((srcOff[src]>>8)&0xFF),(byte)(srcOff[src]&0xFF),
				(byte)((destOff[dest]>>8)&0xFF),(byte)(destOff[dest]&0xFF),
				(byte)((off>>8)&0xFF),(byte)(off&0xFF)};
		return ret;
	}
	
	/**
	 * Generates different test from one test case (with different length and offset).
	 * 
	 * @param def the name of the test case
	 * @param numTest the number of the test case
	 * @param diff indicates if the comparison must fail (this parameter is usefull just
	 * 			  for the test case n°8...)
	 * @param ref the reference test case
	 */
	private void generateTestCase(String def,int numTest,boolean diff,String ref){
		for (int i=0;i<srcOff.length;i++)
		{
			for(int j=0;j<((srcOff[i]==0)?destOff.length:1);j++)
			{
				for (int k=0;k<lengthRequire.length;k++)
				{
					TestCase tmp = null;
					if (diff)
						tmp = new TestCase(def+" length:"+lengthRequire[k]+" srcOff:"+srcOff[i]+
								" destOff:"+destOff[j],numTest,ON_CARD,OFF_CARD,
								makeParameters(k,i,j,lengthRequire[k]-1),0
						);
					else
						tmp= new TestCase(def+" length:"+lengthRequire[k]+" srcOff:"+srcOff[i]+
								" destOff:"+destOff[j],numTest,ON_CARD,OFF_CARD,
								makeParameters(k,i,j,0),0
						);
					tmp.setBenched("x+RETURN-POP");
					tmp.setReference(ref);
					if (!isCalibrate)
					{
						isCalibrate = true;
						calibration = def+" length:"+lengthRequire[k]+" srcOff:"+srcOff[i]+
						" destOff:"+destOff[j];
						tmp.forCalibration();
					}
					else 
					  tmp.setCalibration(calibration);
					addTestCase(tmp);
				}
			}
		}
	}
	
	/**
	 * Creates a new test script and builds the test cases.
	 * @throws CADException 
	 */
	public ArrayCompareScript() throws CADException {
		//Overhead tests
		addTestCase(new TestCase("arrayCompareRef_PersPers",1,ON_CARD,OFF_CARD,
				makeParameters(0,0,0,0),0));
		addTestCase(new TestCase("arrayCompareRef_TransTrans",2,ON_CARD,OFF_CARD,
				makeParameters(0,0,0,0),0));
		addTestCase(new TestCase("arrayCompareRef_ApduApdu",3,ON_CARD,OFF_CARD,
				makeParameters(0,0,0,0),0));
		addTestCase(new TestCase("arrayCompareRef_ApduTrans",4,ON_CARD,OFF_CARD,
				makeParameters(0,0,0,0),0));
		addTestCase(new TestCase("arrayCompareRef_ApduPers",5,ON_CARD,OFF_CARD,
				makeParameters(0,0,0,0),0));
		addTestCase(new TestCase("arrayCompareRef_TransPers",6,ON_CARD,OFF_CARD,
				makeParameters(0,0,0,0),0));
		addTestCase(new TestCase("arrayCompareRef_PersApdu",7,ON_CARD,OFF_CARD,
				makeParameters(0,0,0,0),0));
		
		//Card tests
		generateTestCase("arrayCompare two identical transient array:",9,false,"arrayCompareRef_TransTrans");
		generateTestCase("arrayCompare two identical persistent array:",8,false,"arrayCompareRef_PersPers");
		generateTestCase("arrayCompare from transient to persistent array:",14,false,"arrayCompareRef_TransPers");
		getTestCase("arrayCompareRef_PersPers").setCalibration(calibration);
		getTestCase("arrayCompareRef_TransTrans").setCalibration(calibration);
		getTestCase("arrayCompareRef_TransPers").setCalibration(calibration);
		
		isCalibrate = false;
		//generateTestCase("arrayCompare two different persistent array:",10,true,"arrayCompareRef_PersPers");
		
		generateTestCase("arrayCompare two APDU buffer:",11,false,"arrayCompareRef_ApduApdu");
		generateTestCase("arrayCompare from persistent to APDU buffer:",15,false,"arrayCompareRef_PersApdu");
		generateTestCase("arrayCompare from APDU buffer to transient array:",12,false,"arrayCompareRef_ApduTrans");
		generateTestCase("arrayCompare from APDU buffer to persistent array:",13,false,"arrayCompareRef_ApduPers");
		getTestCase("arrayCompareRef_PersApdu").setCalibration(calibration);
		getTestCase("arrayCompareRef_ApduApdu").setCalibration(calibration);
		getTestCase("arrayCompareRef_ApduTrans").setCalibration(calibration);
		getTestCase("arrayCompareRef_ApduPers").setCalibration(calibration);
		
		
	}
	
}
