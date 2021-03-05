/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package tools.manager.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Collections;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Test.
 * 
 * @version $Revision$ $Date$
 */
public class Test implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _testConfig
     */
    private java.lang.String _testConfig;

    /**
     * Field _testCaseList
     */
    private java.util.ArrayList _testCaseList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Test() 
     {
        super();
        _testCaseList = new java.util.ArrayList();
    } //-- tools.manager.config.Test()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addTestCase
     * 
     * 
     * 
     * @param vTestCase
     */
    public void addTestCase(tools.manager.config.TestCase vTestCase)
        throws java.lang.IndexOutOfBoundsException
    {
        _testCaseList.add(vTestCase);
    } //-- void addTestCase(tools.manager.config.TestCase) 

    /**
     * Method addTestCase
     * 
     * 
     * 
     * @param index
     * @param vTestCase
     */
    public void addTestCase(int index, tools.manager.config.TestCase vTestCase)
        throws java.lang.IndexOutOfBoundsException
    {
        _testCaseList.add(index, vTestCase);
    } //-- void addTestCase(int, tools.manager.config.TestCase) 

    /**
     * Method clearTestCase
     * 
     */
    public void clearTestCase()
    {
        _testCaseList.clear();
    } //-- void clearTestCase() 

    /**
     * Method enumerateTestCase
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateTestCase()
    {
        return Collections.enumeration(_testCaseList);
    } //-- java.util.Enumeration enumerateTestCase() 

    /**
     * Method getTestCase
     * 
     * 
     * 
     * @param index
     * @return TestCase
     */
    public tools.manager.config.TestCase getTestCase(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _testCaseList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (tools.manager.config.TestCase) _testCaseList.get(index);
    } //-- tools.manager.config.TestCase getTestCase(int) 

    /**
     * Method getTestCase
     * 
     * 
     * 
     * @return TestCase
     */
    public tools.manager.config.TestCase[] getTestCase()
    {
        int size = _testCaseList.size();
        tools.manager.config.TestCase[] mArray = new tools.manager.config.TestCase[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (tools.manager.config.TestCase) _testCaseList.get(index);
        }
        return mArray;
    } //-- tools.manager.config.TestCase[] getTestCase() 

    /**
     * Method getTestCaseCount
     * 
     * 
     * 
     * @return int
     */
    public int getTestCaseCount()
    {
        return _testCaseList.size();
    } //-- int getTestCaseCount() 

    /**
     * Returns the value of field 'testConfig'.
     * 
     * @return String
     * @return the value of field 'testConfig'.
     */
    public java.lang.String getTestConfig()
    {
        return this._testConfig;
    } //-- java.lang.String getTestConfig() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Method removeTestCase
     * 
     * 
     * 
     * @param vTestCase
     * @return boolean
     */
    public boolean removeTestCase(tools.manager.config.TestCase vTestCase)
    {
        boolean removed = _testCaseList.remove(vTestCase);
        return removed;
    } //-- boolean removeTestCase(tools.manager.config.TestCase) 

    /**
     * Method setTestCase
     * 
     * 
     * 
     * @param index
     * @param vTestCase
     */
    public void setTestCase(int index, tools.manager.config.TestCase vTestCase)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _testCaseList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _testCaseList.set(index, vTestCase);
    } //-- void setTestCase(int, tools.manager.config.TestCase) 

    /**
     * Method setTestCase
     * 
     * 
     * 
     * @param testCaseArray
     */
    public void setTestCase(tools.manager.config.TestCase[] testCaseArray)
    {
        //-- copy array
        _testCaseList.clear();
        for (int i = 0; i < testCaseArray.length; i++) {
            _testCaseList.add(testCaseArray[i]);
        }
    } //-- void setTestCase(tools.manager.config.TestCase) 

    /**
     * Sets the value of field 'testConfig'.
     * 
     * @param testConfig the value of field 'testConfig'.
     */
    public void setTestConfig(java.lang.String testConfig)
    {
        this._testConfig = testConfig;
    } //-- void setTestConfig(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Test
     */
    public static tools.manager.config.Test unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (tools.manager.config.Test) Unmarshaller.unmarshal(tools.manager.config.Test.class, reader);
    } //-- tools.manager.config.Test unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
