package scripts.templates;

import lib.cad.CADException;
import lib.cad.GPCAD;
import lib.cad.TimeCAD;
import lib.loader.GPLoader;
import lib.xml.test_result.Test;

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
 * $LastChangedRevision: 43 $
 * $LastChangedDate: 2006-10-16 17:17:37 +0200 (lun., 16 oct. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class should be extended by all test scripts.
 */
public abstract class TemplateScript extends Test {
  private boolean check;
	
  /**
   * Creates a new test script without any test case.
   */
  public TemplateScript() {
    check = true;
  }
 
  /**
   * Runs all the test cases.
   * 
   * @param cad the CAD to use to run the test case.
   * @see TemplateScript#run(TimeCAD, int)
   */
  public void run(TimeCAD cad) throws CADException {
	for (int i = 0; i < getTestCaseCount(); i++) {
      TestCase c = (TestCase) getTestCase(i);
      checkTestCase(c);
      c.run(cad);
	}
  }
  
  /**
   * Runs the identified test case with the specified parameters.
   * 
   * @param cad the CAD to use to run the test case.
   * @param name the name of the test case to be run.
   * @param x the number of executions to be performed on-card for this test case.
   * @param y the number of executions to be performed off-card for this test case.
   * @see TestCase#run(TimeCAD)
   * @throws <tt>CADException</tt> if no test case is registered with this 
   *         name.
   */
  public final void run(TimeCAD cad, String name, boolean hasX, boolean hasY, int x, int y) 
      throws CADException {
    TestCase c = getTestCase(name);
    if (hasX)
      c.setX(x);
    if (hasY)
      c.setY(y);
    checkTestCase(c);
    c.run(cad);
  }
  
  /**
   * Searches for the test case with the specified name.
   * 
   * @param name the name of the test case to be searched for.
   * @return the <tt>TestCase</tt> object registered with this name.
   * @throws <tt>CADException</tt> if no test case is registered with this 
   *         name.
   */
  public TestCase getTestCase(String name) throws CADException {
    lib.xml.test_result.TestCase[] cases = getTestCase();
	TestCase c = null;
	for (int i = 0; i < cases.length; i++)
	  if (((TestCase) cases[i]).getName().equalsIgnoreCase(name)) {
	    c = (TestCase) cases[i];
	    break;
	  }
	if (c == null)
	  throw new CADException("Testcase " + name + "does not exist in test " + getName());
	return c;
  }
  
  /**
   * Indicates that no check has to be performed before running a test case.
   * This method is useful when calibrating a test case.
   */
  public void noCheck() {
    check = false;
  }
  
  /**
   * Checks that the specified test case and its reference test case have
   * the same number of executions to be performed on-card and have the
   * same command case.
   * 
   * @param c the test case to be checked.
   * @throws <tt>CADException</tt> if differences are detected.
   */
  private void checkTestCase(TestCase c) throws CADException {
     if (!check || c.sendAsIs)
       return;
	 if (!c.isReference() && 
	     (c.getX() != getTestCase(c.getReferenceName()).getX()))
	   throw new CADException("The number x of executions to be performed on-card " +
			    		      "for this test case " + c.getName() + 
			    		      " and its reference test case " + c.getReferenceName() +
			    		      " must be the same!");
	 if (!c.isReference() &&
		  (c.getCase() != getTestCase(c.getReferenceName()).getCase()))
	   throw new CADException("The command case for this test case " + c.getName() +
			                  " and its reference test case " + c.getReferenceName() +
			                  " must be the same!");
	 if (c.getX() > c.getXMax())
	   throw new CADException("The number x of executions to be performed on-card " +
   		                      "for this test case " + c.getName() + 
   		                      " must be lower than the maximum (" + c.getXMax() +
   		                      ")!");
	 if (c.getX() < c.getXMin())
	   throw new CADException("The number x of executions to be performed on-card " +
   		                      "for this test case " + c.getName() + 
   		                      " must be greater than the minimum (" + c.getXMin() +
   		                      ")!");
  }
	  
  /**
   * Resets all the results of the test cases.
   * 
   * @see TemplateScript#reset(int)
   */
  public final void reset() {
    for (int i = 0; i < getTestCaseCount(); i++)
      reset(i);
  }
  
  /**
   * Resets the result of the identified test case.
   * 
   * @see TestCase#reset()
   */
  public final void reset(int id) {
    ((TestCase)getTestCase(id)).reset();
  }
  
  /**
   * Add TestCases following the pattern, referenceTest+some tests, autocomputing
   * applet indices.
   * 
   * @param runThisTest
   * @param curIndex
   * @param caseNames
   * @return number of test case to skip
   */
  public int genericTestRun(boolean runThisTest, 
		  int firstAppletIndex, 
		  String[] refConfig, String[] testCaseNames, boolean[] activation, 
		  int x1, int y1) {
	  
	  int appletIndex = firstAppletIndex;
	  int testCount = 0;

	  if (runThisTest) {

		  // reference name, reference id
		  if (activation[0]) {
			  addTestCase(new TestCase(refConfig[0],appletIndex,x1,y1));
		  }
		  appletIndex++;

		  String equation = null;

		  // reference id, time equation
		  for (int i=0; (2*i)<testCaseNames.length; i++) {

			  // name
			  String name = testCaseNames[2*i];

			  // benchunits
			  equation = testCaseNames[2*i+1];
			  if (activation[i+1])
				  addTestCase(new TestCase(name, equation, appletIndex, x1, y1).setReference(refConfig[0]));
			  appletIndex++;
		  }
	  }
	  testCount += 1 + testCaseNames.length /2 ;
	  return firstAppletIndex + testCount;
  }
  
  /**
   * This method allows some custom operations before loading our bench applet.
   * See. MemSetupApplet and DirtyMemSetupApplet for an example if usage.
   * 
   * @param cad
   * @param loader
   * @return true if everyting gone fine
   * @throws Exception
   */
  public boolean prepare(GPCAD cad, GPLoader loader) throws Exception {
	  return true;
  }
  
  /**
   * Responsible for cleaning up the mess caused by prepare()
   * 
   * @param cad
   * @param loader
   * @throws Exception
   */
  public void terminate(GPCAD cad, GPLoader loader)  throws Exception {
	  
  }

}