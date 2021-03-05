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
 * $HeadURL: svn+ssh://meunier@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 8 decembre 2006
 * Author: POPS 
 * $LastChangedRevision: 35 $
 * $LastChangedDate: 2006-09-29 17:10:44 +0200 (ven., 29 sept. 2006) $
 * $LastChangedBy: meunier $
 */

package scripts.jcre.engine.invoke;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

public class InvokeScript extends TemplateScript {

	/**
	 * Creates a new test script and builds the test cases.
	 */
	public InvokeScript() {
		short x = (short)TestCase.X; // defaults:10
		short y = 1; //TestCase.Y;
//		int i = 1;

		x = 130; // 180 // 0;//^2 iteration on board
		y = 1; // off board

		int[] indices = new int[64];
		for (int i=0; i<indices.length; i++) indices[i]=i;
		
		/** 
		 * INVOKE+THROW+RETURN test suite 
		 */
		
		addTestCase(new TestCase("INVOKERETTHROW_REF", indices[1], x, y));
		
		addTestCase(new TestCase("INVOKESTATIC_PKG+RETURN", "x",  indices[2], x, y).setReference("INVOKERETTHROW_REF"));
		
		addTestCase(new TestCase("INVOKESTATIC_PKG+RETURN+noCATCH", "x",  indices[3], x, y).setReference("INVOKERETTHROW_REF"));
		
		addTestCase(new TestCase("INVOKESTATIC_PKG+THROW+CATCH", "x",  indices[4], x, y).setReference("INVOKERETTHROW_REF"));
		
		addTestCase(new TestCase("2*INVOKESTATIC_PKG+THROW+CATCH+RETURN", "x",  indices[5], x, y).setReference("INVOKERETTHROW_REF"));
		addTestCase(new TestCase("INVOKESTATIC_PKG+THROW+CATCH+RETURN", "x", indices[6], x, y).setReference("INVOKERETTHROW_REF"));
		
		addTestCase(new TestCase("THROW+CATCH", "x", indices[7], x, y).setReference("INVOKERETTHROW_REF"));

		addTestCase(new TestCase("INVOKE_REF",  indices[8], x, y));
		
		addTestCase(new TestCase("INVOKESTATIC_PUB+RETURN", "x", indices[9], x, y).setReference("INVOKE_REF"));
		addTestCase(new TestCase("INVOKESPECIAL_PRIV+RETURN", "x", indices[10], x, y).setReference("INVOKE_REF"));
		addTestCase(new TestCase("INVOKEVIRTUAL_PUB+RETURN", "x", indices[11], x, y).setReference("INVOKE_REF"));
	}
}
