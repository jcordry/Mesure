package benchs.jcre.array_read;

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

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;
import javacard.framework.*;

public class ArrayReadApplet extends TemplateApplet {

	/* arrays for baload */
	public static short BYTE_SIZE = 128;

	public static byte[] BYTE_SEQ = {(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, 
		(byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, 
		(byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, 
		(byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) 26, (byte) 27, 
		(byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, 
		(byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, 
		(byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, 
		(byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, 
		(byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) 62, 
		(byte) 63, (byte) 64, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, 
		(byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, 
		(byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, 
		(byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, 
		(byte) 91, (byte) 92, (byte) 93, (byte) 94, (byte) 95, (byte) 96, (byte) 97, 
		(byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, 
		(byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, 
		(byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, 
		(byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 123, (byte) 124, (byte) 125, 
		(byte) 126, (byte) 127, (byte) 0};


	public static byte[] BYTE_FIRST = {(byte) 0, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, 
		(byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, 
		(byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) 26, (byte) 27, (byte) 28, 
		(byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, 
		(byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, 
		(byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, 
		(byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, 
		(byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) 62, (byte) 63, 
		(byte) 64, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, 
		(byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, 
		(byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, 
		(byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 91, 
		(byte) 92, (byte) 93, (byte) 94, (byte) 95, (byte) 96, (byte) 97, (byte) 98, 
		(byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, 
		(byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, 
		(byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, 
		(byte) 120, (byte) 121, (byte) 122, (byte) 123, (byte) 124, (byte) 125, (byte) 126, 
		(byte) 127, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, 
		(byte) 7, (byte) 0};


	public static byte[] BYTE_RAND = {(byte) 22, (byte) 84, (byte) 70, (byte) 92, (byte) 103, (byte) 94, (byte) 86, 
		(byte) 91, (byte) 62, (byte) 51, (byte) 56, (byte) 10, (byte) 126, (byte) 1, 
		(byte) 50, (byte) 113, (byte) 85, (byte) 19, (byte) 101, (byte) 118, (byte) 125, 
		(byte) 69, (byte) 114, (byte) 109, (byte) 88, (byte) 42, (byte) 48, (byte) 76, 
		(byte) 15, (byte) 104, (byte) 98, (byte) 16, (byte) 29, (byte) 105, (byte) 79, 
		(byte) 54, (byte) 8, (byte) 61, (byte) 77, (byte) 112, (byte) 117, (byte) 124, 
		(byte) 6, (byte) 27, (byte) 66, (byte) 74, (byte) 25, (byte) 26, (byte) 3, 
		(byte) 41, (byte) 78, (byte) 120, (byte) 111, (byte) 31, (byte) 20, (byte) 44, 
		(byte) 30, (byte) 38, (byte) 97, (byte) 127, (byte) 2, (byte) 28, (byte) 24, 
		(byte) 35, (byte) 49, (byte) 119, (byte) 116, (byte) 93, (byte) 45, (byte) 90, 
		(byte) 36, (byte) 4, (byte) 121, (byte) 12, (byte) 72, (byte) 43, (byte) 71, 
		(byte) 63, (byte) 59, (byte) 21, (byte) 14, (byte) 57, (byte) 53, (byte) 58, 
		(byte) 37, (byte) 7, (byte) 73, (byte) 81, (byte) 33, (byte) 100, (byte) 67, 
		(byte) 115, (byte) 40, (byte) 68, (byte) 18, (byte) 65, (byte) 80, (byte) 17, 
		(byte) 83, (byte) 23, (byte) 39, (byte) 13, (byte) 5, (byte) 108, (byte) 123, 
		(byte) 89, (byte) 87, (byte) 60, (byte) 107, (byte) 95, (byte) 34, (byte) 64, 
		(byte) 102, (byte) 122, (byte) 96, (byte) 82, (byte) 0, (byte) 11, (byte) 47, 
		(byte) 75, (byte) 52, (byte) 55, (byte) 106, (byte) 110, (byte) 32, (byte) 46, 
		(byte) 9, (byte) 99};


	/* arrays for saload */
	public static short SHORT_SIZE = 128;

	public static short[] SHORT_FIRST =  {(short) 0, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7,
		(short) 8, (short) 9, (short) 10, (short) 11, (short) 12, (short) 13, (short) 14,
		(short) 15, (short) 16, (short) 17, (short) 18, (short) 19, (short) 20, (short) 21,
		(short) 22, (short) 23, (short) 24, (short) 25, (short) 26, (short) 27, (short) 28,
		(short) 29, (short) 30, (short) 31, (short) 32, (short) 33, (short) 34, (short) 35,
		(short) 36, (short) 37, (short) 38, (short) 39, (short) 40, (short) 41, (short) 42,
		(short) 43, (short) 44, (short) 45, (short) 46, (short) 47, (short) 48, (short) 49,
		(short) 50, (short) 51, (short) 52, (short) 53, (short) 54, (short) 55, (short) 56,
		(short) 57, (short) 58, (short) 59, (short) 60, (short) 61, (short) 62, (short) 63,
		(short) 64, (short) 65, (short) 66, (short) 67, (short) 68, (short) 69, (short) 70,
		(short) 71, (short) 72, (short) 73, (short) 74, (short) 75, (short) 76, (short) 77,
		(short) 78, (short) 79, (short) 80, (short) 81, (short) 82, (short) 83, (short) 84,
		(short) 85, (short) 86, (short) 87, (short) 88, (short) 89, (short) 90, (short) 91,
		(short) 92, (short) 93, (short) 94, (short) 95, (short) 96, (short) 97, (short) 98,
		(short) 99, (short) 100, (short) 101, (short) 102, (short) 103, (short) 104, (short) 105,
		(short) 106, (short) 107, (short) 108, (short) 109, (short) 110, (short) 111, (short) 112,
		(short) 113, (short) 114, (short) 115, (short) 116, (short) 117, (short) 118, (short) 119,
		(short) 120, (short) 121, (short) 122, (short) 123, (short) 124, (short) 125, (short) 126,
		(short) 127, (short) 1};

	public static short[] SHORT_SEQ = {(short) 1, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7,
		(short) 8, (short) 9, (short) 10, (short) 11, (short) 12, (short) 13, (short) 14,
		(short) 15, (short) 16, (short) 17, (short) 18, (short) 19, (short) 20, (short) 21,
		(short) 22, (short) 23, (short) 24, (short) 25, (short) 26, (short) 27, (short) 28,
		(short) 29, (short) 30, (short) 31, (short) 32, (short) 33, (short) 34, (short) 35,
		(short) 36, (short) 37, (short) 38, (short) 39, (short) 40, (short) 41, (short) 42,
		(short) 43, (short) 44, (short) 45, (short) 46, (short) 47, (short) 48, (short) 49,
		(short) 50, (short) 51, (short) 52, (short) 53, (short) 54, (short) 55, (short) 56,
		(short) 57, (short) 58, (short) 59, (short) 60, (short) 61, (short) 62, (short) 63,
		(short) 64, (short) 65, (short) 66, (short) 67, (short) 68, (short) 69, (short) 70,
		(short) 71, (short) 72, (short) 73, (short) 74, (short) 75, (short) 76, (short) 77,
		(short) 78, (short) 79, (short) 80, (short) 81, (short) 82, (short) 83, (short) 84,
		(short) 85, (short) 86, (short) 87, (short) 88, (short) 89, (short) 90, (short) 91,
		(short) 92, (short) 93, (short) 94, (short) 95, (short) 96, (short) 97, (short) 98,
		(short) 99, (short) 100, (short) 101, (short) 102, (short) 103, (short) 104, (short) 105,
		(short) 106, (short) 107, (short) 108, (short) 109, (short) 110, (short) 111, (short) 112,
		(short) 113, (short) 114, (short) 115, (short) 116, (short) 117, (short) 118, (short) 119,
		(short) 120, (short) 121, (short) 122, (short) 123, (short) 124, (short) 125, (short) 126,
		(short) 127, (short) 0};

	public static short[] SHORT_RAND = {(short) 61, (short) 108, (short) 30, (short) 121, (short) 114, (short) 32, (short) 101,
		(short) 45, (short) 9, (short) 49, (short) 31, (short) 34, (short) 46, (short) 60,
		(short) 55, (short) 83, (short) 90, (short) 115, (short) 21, (short) 63, (short) 41,
		(short) 109, (short) 26, (short) 44, (short) 35, (short) 42, (short) 43, (short) 124,
		(short) 8, (short) 67, (short) 120, (short) 81, (short) 126, (short) 72, (short) 14,
		(short) 38, (short) 87, (short) 104, (short) 66, (short) 4, (short) 37, (short) 78,
		(short) 117, (short) 102, (short) 16, (short) 82, (short) 56, (short) 95, (short) 99,
		(short) 119, (short) 97, (short) 80, (short) 91, (short) 69, (short) 48, (short) 86,
		(short) 112, (short) 89, (short) 57, (short) 111, (short) 75, (short) 64, (short) 47,
		(short) 106, (short) 23, (short) 13, (short) 39, (short) 73, (short) 24, (short) 74,
		(short) 12, (short) 7, (short) 22, (short) 125, (short) 93, (short) 105, (short) 20,
		(short) 68, (short) 53, (short) 71, (short) 84, (short) 6, (short) 15, (short) 96,
		(short) 1, (short) 17, (short) 77, (short) 98, (short) 70, (short) 11, (short) 29,
		(short) 27, (short) 18, (short) 103, (short) 19, (short) 88, (short) 107, (short) 110,
		(short) 36, (short) 5, (short) 123, (short) 25, (short) 10, (short) 127, (short) 76,
		(short) 58, (short) 113, (short) 94, (short) 116, (short) 122, (short) 118, (short) 51,
		(short) 65, (short) 92, (short) 85, (short) 40, (short) 33, (short) 28, (short) 0,
		(short) 3, (short) 2, (short) 52, (short) 62, (short) 54, (short) 79, (short) 50,
		(short) 100, (short) 59};

	public static boolean[] BOOL_RAND;


	public static short iterCount = 0;


	/**
	 * 
	 */
	ArrayReadApplet() {
		super();
	}


	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new ArrayReadApplet().register();
	}


	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_baload_ref();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_baload_first();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_baload_seq();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_baload_rand();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_baload_seq_transaction_commit_ref();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_baload_seq_transaction_commit();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_baload_seq_transaction_abort_ref();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_baload_seq_transaction_abort();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_saload_ref();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_saload_first();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_saload_seq();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_saload_rand();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_saload_seq_transaction_commit_ref();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_saload_seq_transaction_commit();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_saload_seq_transaction_abort_ref();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_saload_seq_transaction_abort();
					}
				},
				new ArrayReadTestCase() {
					public void run(byte[] apduBuffer) {
						clean();
					}
				}
		};
	}


	/* baload : load byte from array */
	/* ***************************** */
	/** Reference test 
	 * sload_3;
	 * sstore_3;
	 */


	void test_unit_baload_ref(){
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte[] L = BYTE_FIRST;
		for(;l0<l1;l0++){
			nothing = nothing;
		}
	}

	/** Test reading array[first] 
	 * aload 4;
	 * sload_3;
	 * baload;
	 * sstore_3;
	 */
	void test_unit_baload_first(){
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte[] L = BYTE_FIRST;
		for(;l0<l1;l0++){
			nothing = L[nothing];
		}
	}

	/** test reading array sequentially 
	 * aload 4;
	 * sload_3;
	 * baload;
	 * sstore_3;
	 */
	void test_unit_baload_seq(){
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte[] L = BYTE_SEQ;
		for(;l0<l1;l0++){
			nothing = L[nothing];
		}
	}

	/** test reading array randomly 
	 * aload 4;
	 * sload_3;
	 * baload;
	 * sstore_3;
	 */
	void test_unit_baload_rand(){
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte[] L = BYTE_RAND;
		for(;l0<l1;l0++){
			nothing = L[nothing];
		}
	}

	/* transactions */
	/** Reference test for transactions 
	 * sload_3;
	 * sstore_3;
	 */
	void test_unit_baload_seq_transaction_commit_ref(){
		JCSystem.beginTransaction();
		JCSystem.commitTransaction();
		short l0=0;
		short l1= iterCount;
		byte nothing = 0;
		byte[] tmp = BYTE_SEQ;
		for(;l0<l1;l0++){
			nothing = tmp[nothing];
		}
	}


	/** Test for commited transaction 
	 * aload 4;
	 * sload_3;
	 * baload;
	 * sstore_3;
	 */
	void test_unit_baload_seq_transaction_commit(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1= iterCount;
		byte nothing = 0;
		byte[] tmp = BYTE_SEQ;
		for(;l0<l1;l0++){
			nothing = tmp[nothing];
		}
		JCSystem.commitTransaction();
	}


	/** Transaction test for abort 
	 * aload 4;
	 * sload_3;
	 * baload;
	 * sstore_3;
	 */
	void test_unit_baload_seq_transaction_abort_ref(){
		JCSystem.beginTransaction();
		JCSystem.abortTransaction();
		short l0=0;
		short l1= iterCount;
		byte nothing = 0;
		byte[] tmp = BYTE_SEQ;
		for(;l0<l1;l0++){
			nothing = tmp[nothing];
		}
	}


	/** Transaction test for abort 
	 * aload 4;
	 * sload_3;
	 * baload;
	 * sstore_3;
	 */
	void test_unit_baload_seq_transaction_abort(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1= iterCount;
		byte nothing = 0;
		byte[] tmp = BYTE_SEQ;
		for(;l0<l1;l0++){
			nothing = tmp[nothing];
		}
		JCSystem.abortTransaction();
	}
	
	
	/* saload : load short from array */
	/* ***************************** */
	/** Reference test 
	 * sload_3;
	 * sstore_3;
	 */


	void test_unit_saload_ref(){
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short[] L = SHORT_FIRST;
		for(;l0<l1;l0++){
			nothing = nothing;
		}
	}

	/** Test reading array[first] 
	 * aload 4;
	 * sload_3;
	 * saload;
	 * sstore_3;
	 */
	void test_unit_saload_first(){
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short[] L = SHORT_FIRST;
		for(;l0<l1;l0++){
			nothing = L[nothing];
		}
	}

	/** test reading array sequentially 
	 * aload 4;
	 * sload_3;
	 * saload;
	 * sstore_3;
	 */
	void test_unit_saload_seq(){
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short[] L = SHORT_SEQ;
		for(;l0<l1;l0++){
			nothing = L[nothing];
		}
	}

	/** test reading array randomly 
	 * aload 4;
	 * sload_3;
	 * saload;
	 * sstore_3;
	 */
	void test_unit_saload_rand(){
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short[] L = SHORT_RAND;
		for(;l0<l1;l0++){
			nothing = L[nothing];
		}
	}

	/* transactions */
	/** Reference test for transactions 
	 * sload_3;
	 * sstore_3;
	 */
	void test_unit_saload_seq_transaction_commit_ref(){
		JCSystem.beginTransaction();
		JCSystem.commitTransaction();
		short l0=0;
		short l1= iterCount;
		short nothing = 0;
		short[] tmp = SHORT_SEQ;
		for(;l0<l1;l0++){
			nothing = tmp[nothing];
		}
	}


	/** Test for commited transaction 
	 * aload 4;
	 * sload_3;
	 * saload;
	 * sstore_3;
	 */
	void test_unit_saload_seq_transaction_commit(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1= iterCount;
		short nothing = 0;
		short[] tmp = SHORT_SEQ;
		for(;l0<l1;l0++){
			nothing = tmp[nothing];
		}
		JCSystem.commitTransaction();
	}


	/** Transaction test for abort 
	 * aload 4;
	 * sload_3;
	 * saload;
	 * sstore_3;
	 */
	void test_unit_saload_seq_transaction_abort_ref(){
		JCSystem.beginTransaction();
		JCSystem.abortTransaction();
		short l0=0;
		short l1= iterCount;
		short nothing = 0;
		short[] tmp = SHORT_SEQ;
		for(;l0<l1;l0++){
			nothing = tmp[nothing];
		}
	}


	/** Transaction test for abort 
	 * aload 4;
	 * sload_3;
	 * saload;
	 * sstore_3;
	 */
	void test_unit_saload_seq_transaction_abort(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1= iterCount;
		short nothing = 0;
		short[] tmp = SHORT_SEQ;
		for(;l0<l1;l0++){
			nothing = tmp[nothing];
		}
		JCSystem.abortTransaction();
	}
	
	

	void clean(){
		ArrayReadApplet.BYTE_RAND = null;
		ArrayReadApplet.SHORT_RAND = null;
		ArrayReadApplet.BOOL_RAND = null;
		ArrayReadApplet.SHORT_FIRST = null;
		ArrayReadApplet.SHORT_RAND = null;
		ArrayReadApplet.SHORT_SEQ = null;
	}

	/* ... */


	/* saload : load short from array */
	/* ****************************** */

	void test_unit_baload_rand_transient(){
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte[] L = JCSystem.makeTransientByteArray((short) BYTE_SIZE, JCSystem.CLEAR_ON_DESELECT );

		for(;l0<BYTE_SIZE;l0++){
			L[l0] = BYTE_RAND[l0];
		}

		l0 = 0;
		for(;l0<l1;l0++){
			nothing = L[nothing];
		}
	}

}


class ArrayReadTestCase extends TestCase{

	public ArrayReadTestCase() {
		super();
		setUseInnerCounter(true);
	}    

	public void setUp(byte[] buffer, short iterCount) {
		ArrayReadApplet.iterCount = iterCount;		
	}

	public void cleanUp(){
		ArrayReadApplet.BYTE_RAND = null;
		ArrayReadApplet.SHORT_RAND = null;
		ArrayReadApplet.BOOL_RAND = null;
		ArrayReadApplet.SHORT_FIRST = null;
		ArrayReadApplet.SHORT_RAND = null;
		ArrayReadApplet.SHORT_SEQ = null;
	}

	public void run() {}


	public void run(byte[] buffer){;}

}

