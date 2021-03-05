package scripts.templates;

import lib.cad.CADException;

/*
 * Copyright (c) 2007 Mesure Project
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
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/BadResponseStatusException.java $
 * Created: 21 novembre 2007
 * Author: TL 
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: cpascal $
 */


/**
 * Exception thrown to indicate that a <tt>SystemException</tt> exception with
 * reason code <tt>NO_RESOURCES</tt> was thrown by the card.
 */
public class NoResourceException extends CADException {
	
	/** The test case that has thrown this exception. */
	private TestCase testCase;

	public NoResourceException(TestCase testCase) {
	  super();
	  this.testCase = testCase;
	}
	
	/**
	 * Returns the test case that has thrown this exception.
	 * 
	 * @return the associated <tt>TestCase</tt> object.
	 */
	public TestCase getTestCase() {
	  return testCase;
	}
}
