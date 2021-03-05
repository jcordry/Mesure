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
package scripts.jcre.engine.field;

import scripts.templates.TemplateScript;
import scripts.templates.TestCase;

/**
 * Class responsible for benchmarking type conversion related bytecodes
 */
public class FieldScript extends TemplateScript {

	public FieldScript() {
		short x = 128;
		short y = 3;// TestCase.Y;
		
		short[] indices = {0 /*unused*/, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

		addTestCase(new TestCase("FIELD_REF", indices[1], 
				x, y));

		addTestCase(new TestCase("GETSTATICS", "x+SSTORE2",indices[2], 
				x, y).setReference("FIELD_REF"));
		addTestCase(new TestCase("GETSTATICB", "x+SSTORE2",indices[3], 
				x, y).setReference("FIELD_REF"));
		addTestCase(new TestCase("GETFIELDSTHIS", "x+SSTORE3",indices[4], 
				x, y).setReference("FIELD_REF"));
		addTestCase(new TestCase("GETFIELDS", "x+ALOAD4+SSTORE3",indices[5], 
				x, y).setReference("FIELD_REF"));
		addTestCase(new TestCase("GETFIELDBTHIS", "x+SSTORE3",indices[6], 
				x, y).setReference("FIELD_REF"));
		addTestCase(new TestCase("GETFIELDB", "x+ALOAD4+SSTORE3",indices[7], 
				x, y).setReference("FIELD_REF"));
		addTestCase(new TestCase("GETFIELDATHIS", "x+ALOAD4+SSTORE",indices[8], 
				x, y).setReference("FIELD_REF"));
		addTestCase(new TestCase("GETFIELDA", "x+ALOAD3+SSTORE",indices[9], 
				x, y).setReference("FIELD_REF"));

		addTestCase(new TestCase("PUTSTATICA", "x+ACONSTNULL",indices[10], 
				x, y).setReference("FIELD_REF"));
		addTestCase(new TestCase("PUTFIELDS", "x+ALOAD4+SCONST0",indices[11], 
				x, y).setReference("FIELD_REF"));
		addTestCase(new TestCase("PUTFIELDB", "x+ALOAD4+SCONST0",indices[12], 
				x, y).setReference("FIELD_REF"));
		addTestCase(new TestCase("PUTFIELDA", "x+ALOAD4+ACONSTNULL",indices[13], 
				x, y).setReference("FIELD_REF"));
	}
}
