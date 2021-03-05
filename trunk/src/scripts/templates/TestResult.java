package scripts.templates;

import lib.chrono.TimeMeasure;

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
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 6 octobre 2006
 * Author: TL 
 * $LastChangedRevision: 35 $
 * $LastChangedDate: 2006-09-29 17:10:44 +0200 (ven., 29 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * Each test results should be encapsulated using this class.
 */
public class TestResult extends lib.xml.test_result.TestResult {
  
  /** The status indicating that the test has not been executed. */
  public final static int NOT_EXECUTED = 0;
	
  /** The status indicating that the test execution succeeded. */
  public final static int PASSED = 1;
  
  /** The status indicating that the test execution failed. */
  public final static int FAILED = 2;
  
  /** The status indicating that a <tt>SystemException</tt> with reason code
   *  <tt>NO_RESOURCES</tt> was thrown when setting up the test case. */
  public final static int NO_RESOURCES = 3;
 
  /**
   * Builds a new test result.
   * 
   * @param status the status.
   * @param message the detailed message.
   * @param measures the measures.
   */  
  public TestResult(int status, String message, TimeMeasure[] measure) {
    setStatus(status);
    setMessage(message);
    setTimeMeasure(measure);
  }
}
