package benchs.jcre.array_write;

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


public class ArrayWriteApplet extends TemplateApplet {

	/*
	 * arrays for bastore 
	 */

	public static short BYTE_SIZE = 128;

	public static byte[] BYTE_FIRST = {(byte) 0, (byte) 55, (byte) 36, (byte) 35, (byte) 94, (byte) 30, (byte) 59, (byte) 69,
		(byte) 58, (byte) 38, (byte) 72, (byte) 8, (byte) 13, (byte) 103, (byte) 97,
		(byte) 111, (byte) 49, (byte) 27, (byte) 123, (byte) 1, (byte) 41, (byte) 12,
		(byte) 0, (byte) 120, (byte) 22, (byte) 86, (byte) 17, (byte) 32, (byte) 68,
		(byte) 76, (byte) 50, (byte) 11, (byte) 122, (byte) 20, (byte) 89, (byte) 43,
		(byte) 117, (byte) 121, (byte) 78, (byte) 110, (byte) 80, (byte) 93, (byte) 47,
		(byte) 2, (byte) 3, (byte) 90, (byte) 29, (byte) 19, (byte) 10, (byte) 44,
		(byte) 125, (byte) 62, (byte) 67, (byte) 40, (byte) 61, (byte) 79, (byte) 118,
		(byte) 65, (byte) 57, (byte) 53, (byte) 91, (byte) 46, (byte) 6, (byte) 31,
		(byte) 74, (byte) 109, (byte) 116, (byte) 25, (byte) 84, (byte) 82, (byte) 7,
		(byte) 85, (byte) 26, (byte) 127, (byte) 102, (byte) 126, (byte) 114, (byte) 92,
		(byte) 83, (byte) 23, (byte) 52, (byte) 28, (byte) 14, (byte) 34, (byte) 115,
		(byte) 39, (byte) 42, (byte) 15, (byte) 33, (byte) 18, (byte) 104, (byte) 112,
		(byte) 119, (byte) 56, (byte) 88, (byte) 5, (byte) 77, (byte) 106, (byte) 64,
		(byte) 45, (byte) 9, (byte) 87, (byte) 75, (byte) 60, (byte) 107, (byte) 113,
		(byte) 95, (byte) 124, (byte) 66, (byte) 16, (byte) 73, (byte) 21, (byte) 105,
		(byte) 70, (byte) 54, (byte) 100, (byte) 48, (byte) 37, (byte) 51, (byte) 4,
		(byte) 108, (byte) 99, (byte) 96, (byte) 63, (byte) 81, (byte) 101, (byte) 71,
		(byte) 98};

	public static byte[] BYTE_SEQ = {(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7,
		(byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14,
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
		(byte) 127, (byte) 0};

	public static byte[] BYTE_RAND = {(byte) 25, (byte) 76, (byte) 51, (byte) 6, (byte) 58, (byte) 5, (byte) 106,
		(byte) 38, (byte) 124, (byte) 91, (byte) 54, (byte) 45, (byte) 41, (byte) 37,
		(byte) 104, (byte) 12, (byte) 125, (byte) 22, (byte) 11, (byte) 73, (byte) 75,
		(byte) 80, (byte) 43, (byte) 113, (byte) 48, (byte) 127, (byte) 90, (byte) 71,
		(byte) 83, (byte) 97, (byte) 49, (byte) 99, (byte) 92, (byte) 74, (byte) 78,
		(byte) 19, (byte) 81, (byte) 117, (byte) 100, (byte) 1, (byte) 13, (byte) 86,
		(byte) 31, (byte) 18, (byte) 8, (byte) 85, (byte) 87, (byte) 60, (byte) 44,
		(byte) 119, (byte) 107, (byte) 40, (byte) 62, (byte) 17, (byte) 24, (byte) 110,
		(byte) 34, (byte) 70, (byte) 126, (byte) 77, (byte) 123, (byte) 115, (byte) 114,
		(byte) 122, (byte) 64, (byte) 9, (byte) 32, (byte) 101, (byte) 116, (byte) 35,
		(byte) 95, (byte) 88, (byte) 63, (byte) 20, (byte) 28, (byte) 0, (byte) 82,
		(byte) 15, (byte) 102, (byte) 55, (byte) 30, (byte) 33, (byte) 118, (byte) 7,
		(byte) 103, (byte) 42, (byte) 36, (byte) 46, (byte) 56, (byte) 120, (byte) 29,
		(byte) 98, (byte) 47, (byte) 53, (byte) 79, (byte) 108, (byte) 23, (byte) 57,
		(byte) 26, (byte) 109, (byte) 68, (byte) 94, (byte) 61, (byte) 59, (byte) 67,
		(byte) 72, (byte) 3, (byte) 27, (byte) 39, (byte) 112, (byte) 96, (byte) 89,
		(byte) 111, (byte) 69, (byte) 105, (byte) 4, (byte) 52, (byte) 84, (byte) 65,
		(byte) 10, (byte) 121, (byte) 2, (byte) 93, (byte) 21, (byte) 66, (byte) 50,
		(byte) 14, (byte) 16};

	/* 
	 * arrays for sastore 
	 */

	public static short SHORT_SIZE = 256;

	public static short[] SHORT_FIRST = {(short) 0, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7,
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

	public static short[] SHORT_RAND = {(short) 0, (short) 128, (short) 129, (short) 130, (short) 131, (short) 132, (short) 133, (short) 134,
		(short) 135, (short) 136, (short) 137, (short) 138, (short) 139, (short) 140, (short) 141,
		(short) 142, (short) 143, (short) 144, (short) 145, (short) 146, (short) 147, (short) 148,
		(short) 149, (short) 150, (short) 151, (short) 152, (short) 153, (short) 154, (short) 155,
		(short) 156, (short) 157, (short) 158, (short) 159, (short) 160, (short) 161, (short) 162,
		(short) 163, (short) 164, (short) 165, (short) 166, (short) 167, (short) 168, (short) 169,
		(short) 170, (short) 171, (short) 172, (short) 173, (short) 174, (short) 175, (short) 176,
		(short) 177, (short) 178, (short) 179, (short) 180, (short) 181, (short) 182, (short) 183,
		(short) 184, (short) 185, (short) 186, (short) 187, (short) 188, (short) 189, (short) 190,
		(short) 191, (short) 192, (short) 193, (short) 194, (short) 195, (short) 196, (short) 197,
		(short) 198, (short) 199, (short) 200, (short) 201, (short) 202, (short) 203, (short) 204,
		(short) 205, (short) 206, (short) 207, (short) 208, (short) 209, (short) 210, (short) 211,
		(short) 212, (short) 213, (short) 214, (short) 215, (short) 216, (short) 217, (short) 218,
		(short) 219, (short) 220, (short) 221, (short) 222, (short) 223, (short) 224, (short) 225,
		(short) 226, (short) 227, (short) 228, (short) 229, (short) 230, (short) 231, (short) 232,
		(short) 233, (short) 234, (short) 235, (short) 236, (short) 237, (short) 238, (short) 239,
		(short) 240, (short) 241, (short) 242, (short) 243, (short) 244, (short) 245, (short) 246,
		(short) 247, (short) 248, (short) 249, (short) 250, (short) 251, (short) 252, (short) 253,
		(short) 254, (short) 255, (short) 1, (short) 2, (short) 3, (short) 4, (short) 5,
		(short) 6, (short) 7, (short) 8, (short) 9, (short) 10, (short) 11, (short) 12,
		(short) 13, (short) 14, (short) 15, (short) 16, (short) 17, (short) 18, (short) 19,
		(short) 20, (short) 21, (short) 22, (short) 23, (short) 24, (short) 25, (short) 26,
		(short) 27, (short) 28, (short) 29, (short) 30, (short) 31, (short) 32, (short) 33,
		(short) 34, (short) 35, (short) 36, (short) 37, (short) 38, (short) 39, (short) 40,
		(short) 41, (short) 42, (short) 43, (short) 44, (short) 45, (short) 46, (short) 47,
		(short) 48, (short) 49, (short) 50, (short) 51, (short) 52, (short) 53, (short) 54,
		(short) 55, (short) 56, (short) 57, (short) 58, (short) 59, (short) 60, (short) 61,
		(short) 62, (short) 63, (short) 64, (short) 65, (short) 66, (short) 67, (short) 68,
		(short) 69, (short) 70, (short) 71, (short) 72, (short) 73, (short) 74, (short) 75,
		(short) 76, (short) 77, (short) 78, (short) 79, (short) 80, (short) 81, (short) 82,
		(short) 83, (short) 84, (short) 85, (short) 86, (short) 87, (short) 88, (short) 89,
		(short) 90, (short) 91, (short) 92, (short) 93, (short) 94, (short) 95, (short) 96,
		(short) 97, (short) 98, (short) 99, (short) 100, (short) 101, (short) 102, (short) 103,
		(short) 104, (short) 105, (short) 106, (short) 107, (short) 108, (short) 109, (short) 110,
		(short) 111, (short) 112, (short) 113, (short) 114, (short) 115, (short) 116, (short) 117,
		(short) 118, (short) 119, (short) 120, (short) 121, (short) 122, (short) 123, (short) 124,
		(short) 125, (short) 126, (short) 127};



	public static short iterCount = 0;


	/**x
	 * 
	 */
	ArrayWriteApplet() {
		super();
	}


	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new ArrayWriteApplet().register();
	}	    


	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_ref();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_first();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_seq();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_rand();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_first_transacted_commit_ref();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_first_transacted_commit();
					}		
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_seq_transacted_commit_ref();

					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_seq_transacted_commit();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_rand_transacted_commit_ref();

					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_rand_transacted_commit();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_first_transacted_abort_ref();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_first_transacted_abort();
					}		
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_seq_transacted_abort_ref();

					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_seq_transacted_abort();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_rand_transacted_abort_ref();

					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_bastore_rand_transacted_abort();
					}
				}, /* sastore tests */
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_ref();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_first();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_seq();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_rand();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_first_transacted_commit_ref();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_first_transacted_commit();
					}		
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_seq_transacted_commit_ref();

					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_seq_transacted_commit();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_rand_transacted_commit_ref();

					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_rand_transacted_commit();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_first_transacted_abort_ref();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_first_transacted_abort();
					}		
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_seq_transacted_abort_ref();

					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_seq_transacted_abort();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_rand_transacted_abort_ref();

					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						test_unit_sastore_rand_transacted_abort();
					}
				},
				new ArrayWriteTestCase() {
					public void run(byte[] apduBuffer) {
						clean();
					}
				}
		};
	}


	/* bastore */
	/* ******* */
	
	/** Reference test 
		aload 5;
		sload_3;
		baload;
		sstore 4;
		sload 4;
		sstore_3;
		sload 4;
		sstore_3;
	 */
	void test_unit_bastore_ref(){
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;	
		byte index;
		byte[] L = BYTE_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			nothing = index;
			nothing = index;
		}
	}


	/** Test writing array[first]
	 	aload 5;
		sload_3;
		baload;
		sstore 4;
		aload 5;
		sload_3;
		sload 4;
		bastore;
		sload 4;
		sstore_3;
	 */
	void test_unit_bastore_first(){
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_FIRST;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing array sequentially 
	 	aload 5;
		sload_3;
		baload;
		sstore 4;
		aload 5;
		sload_3;
		sload 4;
		bastore;
		sload 4;
		sstore_3;
	 */
	void test_unit_bastore_seq(){
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_SEQ;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing array sequentially 
	  	aload 5;
		sload_3;
		baload;
		sstore 4;
		aload 5;
		sload_3;
		sload 4;
		bastore;
		sload 4;
		sstore_3;
	 */
	void test_unit_bastore_rand(){
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}
	
	/** Test writing first element of a byte array in transaction reference*/
	/** commit test */
	void test_unit_bastore_first_transacted_commit_ref(){
		JCSystem.beginTransaction();
		JCSystem.commitTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_FIRST;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing first element of a byte array in transaction */
	/** commit test */
	void test_unit_bastore_first_transacted_commit(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_FIRST;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.commitTransaction();
	}

	/** Test writing byte array sequencially in transaction reference*/
	/** commit test */
	void test_unit_bastore_seq_transacted_commit_ref(){
		JCSystem.beginTransaction();
		JCSystem.commitTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_SEQ;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing byte array sequencially in transaction */
	/** commit test */
	void test_unit_bastore_seq_transacted_commit(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_SEQ;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.commitTransaction();
	}

	/** Test writing byte array randomly in transaction reference*/
	/** commit test */
	void test_unit_bastore_rand_transacted_commit_ref(){
		JCSystem.beginTransaction();
		JCSystem.commitTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing byte array randomly in transaction */
	/** commit test */
	void test_unit_bastore_rand_transacted_commit(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.commitTransaction();
	}

	/** Test writing first element of byte array in transaction reference */
	/** abort test */
	void test_unit_bastore_first_transacted_abort_ref(){
		JCSystem.beginTransaction();
		JCSystem.abortTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_FIRST;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing first element of byte array in transaction */
	/** abort test */
	void test_unit_bastore_first_transacted_abort(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_FIRST;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.abortTransaction();
	}

	/** Test writing byte array sequencially in transaction reference */
	/** abort test */
	void test_unit_bastore_seq_transacted_abort_ref(){
		JCSystem.beginTransaction();
		JCSystem.abortTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_SEQ;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing byte array sequencially in transaction */
	/** abort test */
	void test_unit_bastore_seq_transacted_abort(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_SEQ;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.abortTransaction();
	}

	/** Test writing byte array randomly in transaction reference */
	/** abort test */
	void test_unit_bastore_rand_transacted_abort_ref(){
		JCSystem.beginTransaction();
		JCSystem.abortTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing byte array randomly in transaction */
	/** abort test */
	void test_unit_bastore_rand_transacted_abort(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		byte nothing = 0;
		byte index;
		byte[] L = BYTE_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.abortTransaction();
	}
	
	/* sastore */
	/* ******* */
	
	/** Reference test 
	 	aload 5;
		sload_3;
		saload;
		sstore 4;
		sload 4;
		sstore_3;
		sload 4;
		sstore_3;
	 */
	void test_unit_sastore_ref(){
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;	
		short index;
		short[] L = SHORT_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			nothing = index;
			nothing = index;
		}
	}


	/** Test writing array[first]
	 	load 5;
		sload_3;
		saload;
		sstore 4;
		aload 5;
		sload_3;
		sload 4;
		sastore;
		sload 4;
		sstore_3;
	 */
	void test_unit_sastore_first(){
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_FIRST;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing array sequentially 
	 	aload 5;
		sload_3;
		saload;
		sstore 4;
		aload 5;
		sload_3;
		sload 4;
		sastore;
		sload 4;
		sstore_3;
	 */
	void test_unit_sastore_seq(){
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_SEQ;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing array sequentially 
	  	aload 5;
		sload_3;
		saload;
		sstore 4;
		aload 5;
		sload_3;
		sload 4;
		sastore;
		sload 4;
		sstore_3;
	 */
	void test_unit_sastore_rand(){
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}
	
	/** Test writing first element of a short array in transaction reference*/
	/** commit test */
	void test_unit_sastore_first_transacted_commit_ref(){
		JCSystem.beginTransaction();
		JCSystem.commitTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_FIRST;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing first element of a short array in transaction */
	/** commit test */
	void test_unit_sastore_first_transacted_commit(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_FIRST;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.commitTransaction();
	}

	/** Test writing short array sequencially in transaction reference*/
	/** commit test */
	void test_unit_sastore_seq_transacted_commit_ref(){
		JCSystem.beginTransaction();
		JCSystem.commitTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_SEQ;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing short array sequencially in transaction */
	/** commit test */
	void test_unit_sastore_seq_transacted_commit(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_SEQ;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.commitTransaction();
	}

	/** Test writing short array randomly in transaction reference*/
	/** commit test */
	void test_unit_sastore_rand_transacted_commit_ref(){
		JCSystem.beginTransaction();
		JCSystem.commitTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing short array randomly in transaction */
	/** commit test */
	void test_unit_sastore_rand_transacted_commit(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.commitTransaction();
	}

	/** Test writing first element of short array in transaction reference */
	/** abort test */
	void test_unit_sastore_first_transacted_abort_ref(){
		JCSystem.beginTransaction();
		JCSystem.abortTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_FIRST;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing first element of short array in transaction */
	/** abort test */
	void test_unit_sastore_first_transacted_abort(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_FIRST;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.abortTransaction();
	}

	/** Test writing short array sequencially in transaction reference */
	/** abort test */
	void test_unit_sastore_seq_transacted_abort_ref(){
		JCSystem.beginTransaction();
		JCSystem.abortTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_SEQ;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing short array sequencially in transaction */
	/** abort test */
	void test_unit_sastore_seq_transacted_abort(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_SEQ;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.abortTransaction();
	}

	/** Test writing short array randomly in transaction reference */
	/** abort test */
	void test_unit_sastore_rand_transacted_abort_ref(){
		JCSystem.beginTransaction();
		JCSystem.abortTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
	}

	/** Test writing short array randomly in transaction */
	/** abort test */
	void test_unit_sastore_rand_transacted_abort(){
		JCSystem.beginTransaction();
		short l0=0;
		short l1 = iterCount;
		short nothing = 0;
		short index;
		short[] L = SHORT_RAND;
		for(;l0<l1;l0++){
			index = L[nothing];
			L[nothing] = index;
			nothing = index;
		}
		JCSystem.abortTransaction();
	}
	
	void clean(){
		BYTE_FIRST = null;
		BYTE_SEQ = null;
		BYTE_RAND = null;
		SHORT_FIRST = null;
		SHORT_SEQ = null;
		SHORT_RAND = null;

	}
}



class ArrayWriteTestCase extends TestCase{

	public ArrayWriteTestCase() {
		super();
		setUseInnerCounter(true);
	}
	public void setUp(byte[] buffer, short iterCount) {
		ArrayWriteApplet.iterCount = iterCount;
	}

	public void cleanUp(){

		ArrayWriteApplet.BYTE_RAND = null;
	}

	public void run() {}

	public void run(byte[] buffer){}

}