package scripts.jcre.array_write;

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


import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

public class ArrayWriteAppletScript extends TemplateScript {

	/**
	 * Creates a new test script and builds the test cases.
	 */
	public ArrayWriteAppletScript() {
		short x = TestCase.X; // 10 // iterations on board
		short y = TestCase.Y; // iterations off board

		x = 100; // 100;
		y = 3; //5;

		// The empty test cases used to computes overhead
		addTestCase(new TestCase("BASTORE_REF",1,x,y));
		addTestCase(new TestCase("BASTORE_FIRST","ALOAD_5+SLOAD_4-SSTORE_3+x",2,x,y).setReference("BASTORE_REF"));
		addTestCase(new TestCase("BASTORE_SEQ","ALOAD_5+SLOAD_4-SSTORE_3 +x",3,x,y).setReference("BASTORE_REF"));
		addTestCase(new TestCase("BASTORE_RAND","ALOAD_5+SLOAD-4-SSTORE_3+x", 4,x,y).setReference("BASTORE_REF"));
		
		addTestCase(new TestCase("BASTORE_FIRST_TRANSACTED_COMMIT_REF", 5,x,y));
		addTestCase(new TestCase("BASTORE_FIRST_TRANSACTED_COMMIT","x",6,x,y).setReference("BASTORE_FIRST_TRANSACTED_COMMIT_REF"));
		
		addTestCase(new TestCase("BASTORE_SEQ_TRANSACTED_COMMIT_REF",7,x,y));
		addTestCase(new TestCase("BASTORE_SEQ_TRANSACTED_COMMIT","x",8,x,y).setReference("BASTORE_SEQ_TRANSACTED_COMMIT_REF"));
		
		addTestCase(new TestCase("BASTORE_RAND_TRANSACTED_COMMIT_REF",9,x,y));
		addTestCase(new TestCase("BASTORE_RAND_TRANSACTED_COMMIT","x",10,x,y).setReference("BASTORE_RAND_TRANSACTED_COMMIT_REF"));
		
		addTestCase(new TestCase("BASTORE_FIRST_TRANSACTED_ABORT_REF",11,x,y));
		addTestCase(new TestCase("BASTORE_FIRST_TRANSACTED_ABORT","x",12,x,y).setReference("BASTORE_FIRST_TRANSACTED_ABORT_REF"));
		
		addTestCase(new TestCase("BASTORE_SEQ_TRANSACTED_ABORT_REF",13,x,y));
		addTestCase(new TestCase("BASTORE_SEQ_TRANSACTED_ABORT","x",14,x,y).setReference("BASTORE_SEQ_TRANSACTED_ABORT_REF"));
		
		addTestCase(new TestCase("BASTORE_RAND_TRANSACTED_ABORT_REF",15,x,y));
		addTestCase(new TestCase("BASTORE_RAND_TRANSACTED_ABORT","x",16,x,y).setReference("BASTORE_RAND_TRANSACTED_ABORT_REF"));
		
		/* sastore tests */
		
		addTestCase(new TestCase("SASTORE_REF",17,x,y));
		addTestCase(new TestCase("SASTORE_FIRST","ALOAD_5+SLOAD_4-SSTORE_3+x",18,x,y).setReference("SASTORE_REF"));
		addTestCase(new TestCase("SASTORE_SEQ","ALOAD_5+SLOAD_4-SSTORE_3+x",19,x,y).setReference("SASTORE_REF"));
		addTestCase(new TestCase("SASTORE_RAND","ALOAD_5+SLOAD_4-SSTORE_3+x",20,x,y).setReference("SASTORE_REF"));
		
		addTestCase(new TestCase("SASTORE_FIRST_TRANSACTED_COMMIT_REF",21,x,y));
		addTestCase(new TestCase("SASTORE_FIRST_TRANSACTED_COMMIT","x",22,x,y).setReference("SASTORE_FIRST_TRANSACTED_COMMIT_REF"));
		
		addTestCase(new TestCase("SASTORE_SEQ_TRANSACTED_COMMIT_REF",23,x,y));
		addTestCase(new TestCase("SASTORE_SEQ_TRANSACTED_COMMIT","x",24,x,y).setReference("SASTORE_SEQ_TRANSACTED_COMMIT_REF"));
		
		addTestCase(new TestCase("SASTORE_RAND_TRANSACTED_COMMIT_REF",25,x,y));
		addTestCase(new TestCase("SASTORE_RAND_TRANSACTED_COMMIT","x",26,x,y).setReference("SASTORE_RAND_TRANSACTED_COMMIT_REF"));
		
		addTestCase(new TestCase("SASTORE_FIRST_TRANSACTED_ABORT_REF",27,x,y));
		addTestCase(new TestCase("SASTORE_FIRST_TRANSACTED_ABORT","x",28,x,y).setReference("SASTORE_FIRST_TRANSACTED_ABORT_REF"));
		
		addTestCase(new TestCase("SASTORE_SEQ_TRANSACTED_ABORT_REF",29,x,y));
		addTestCase(new TestCase("SASTORE_SEQ_TRANSACTED_ABORT","x",30,x,y).setReference("SASTORE_SEQ_TRANSACTED_ABORT_REF"));
		
		addTestCase(new TestCase("SASTORE_RAND_TRANSACTED_ABORT_REF",31,x,y));
		addTestCase(new TestCase("SASTORE_RAND_TRANSACTED_ABORT","x",32,x,y).setReference("SASTORE_RAND_TRANSACTED_ABORT_REF"));
		
		/* clean test */

		addTestCase(new TestCase("ARRAYWRITE_CLEANUP", "x", 33, x,y));
	}
}
