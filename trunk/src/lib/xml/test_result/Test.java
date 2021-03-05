/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package lib.xml.test_result;

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
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _date
     */
    private java.lang.String _date;

    /**
     * Field _version
     */
    private java.lang.String _version;

    /**
     * Field _count
     */
    private int _count;

    /**
     * keeps track of state for field: _count
     */
    private boolean _has_count;

    /**
     * Field _valid
     */
    private boolean _valid;

    /**
     * keeps track of state for field: _valid
     */
    private boolean _has_valid;

    /**
     * Field _script
     */
    private java.lang.String _script;

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
    } //-- lib.xml.test_result.Test()


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
    public void addTestCase(lib.xml.test_result.TestCase vTestCase)
        throws java.lang.IndexOutOfBoundsException
    {
        _testCaseList.add(vTestCase);
    } //-- void addTestCase(lib.xml.test_result.TestCase) 

    /**
     * Method addTestCase
     * 
     * 
     * 
     * @param index
     * @param vTestCase
     */
    public void addTestCase(int index, lib.xml.test_result.TestCase vTestCase)
        throws java.lang.IndexOutOfBoundsException
    {
        _testCaseList.add(index, vTestCase);
    } //-- void addTestCase(int, lib.xml.test_result.TestCase) 

    /**
     * Method clearTestCase
     * 
     */
    public void clearTestCase()
    {
        _testCaseList.clear();
    } //-- void clearTestCase() 

    /**
     * Method deleteCount
     * 
     */
    public void deleteCount()
    {
        this._has_count= false;
    } //-- void deleteCount() 

    /**
     * Method deleteValid
     * 
     */
    public void deleteValid()
    {
        this._has_valid= false;
    } //-- void deleteValid() 

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
     * Returns the value of field 'count'.
     * 
     * @return int
     * @return the value of field 'count'.
     */
    public int getCount()
    {
        return this._count;
    } //-- int getCount() 

    /**
     * Returns the value of field 'date'.
     * 
     * @return String
     * @return the value of field 'date'.
     */
    public java.lang.String getDate()
    {
        return this._date;
    } //-- java.lang.String getDate() 

    /**
     * Returns the value of field 'name'.
     * 
     * @return String
     * @return the value of field 'name'.
     */
    public java.lang.String getName()
    {
        return this._name;
    } //-- java.lang.String getName() 

    /**
     * Returns the value of field 'script'.
     * 
     * @return String
     * @return the value of field 'script'.
     */
    public java.lang.String getScript()
    {
        return this._script;
    } //-- java.lang.String getScript() 

    /**
     * Method getTestCase
     * 
     * 
     * 
     * @param index
     * @return TestCase
     */
    public lib.xml.test_result.TestCase getTestCase(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _testCaseList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (lib.xml.test_result.TestCase) _testCaseList.get(index);
    } //-- lib.xml.test_result.TestCase getTestCase(int) 

    /**
     * Method getTestCase
     * 
     * 
     * 
     * @return TestCase
     */
    public lib.xml.test_result.TestCase[] getTestCase()
    {
        int size = _testCaseList.size();
        lib.xml.test_result.TestCase[] mArray = new lib.xml.test_result.TestCase[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (lib.xml.test_result.TestCase) _testCaseList.get(index);
        }
        return mArray;
    } //-- lib.xml.test_result.TestCase[] getTestCase() 

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
     * Returns the value of field 'valid'.
     * 
     * @return boolean
     * @return the value of field 'valid'.
     */
    public boolean getValid()
    {
        return this._valid;
    } //-- boolean getValid() 

    /**
     * Returns the value of field 'version'.
     * 
     * @return String
     * @return the value of field 'version'.
     */
    public java.lang.String getVersion()
    {
        return this._version;
    } //-- java.lang.String getVersion() 

    /**
     * Method hasCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasCount()
    {
        return this._has_count;
    } //-- boolean hasCount() 

    /**
     * Method hasValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasValid()
    {
        return this._has_valid;
    } //-- boolean hasValid() 

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
    public boolean removeTestCase(lib.xml.test_result.TestCase vTestCase)
    {
        boolean removed = _testCaseList.remove(vTestCase);
        return removed;
    } //-- boolean removeTestCase(lib.xml.test_result.TestCase) 

    /**
     * Sets the value of field 'count'.
     * 
     * @param count the value of field 'count'.
     */
    public void setCount(int count)
    {
        this._count = count;
        this._has_count = true;
    } //-- void setCount(int) 

    /**
     * Sets the value of field 'date'.
     * 
     * @param date the value of field 'date'.
     */
    public void setDate(java.lang.String date)
    {
        this._date = date;
    } //-- void setDate(java.lang.String) 

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Sets the value of field 'script'.
     * 
     * @param script the value of field 'script'.
     */
    public void setScript(java.lang.String script)
    {
        this._script = script;
    } //-- void setScript(java.lang.String) 

    /**
     * Method setTestCase
     * 
     * 
     * 
     * @param index
     * @param vTestCase
     */
    public void setTestCase(int index, lib.xml.test_result.TestCase vTestCase)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _testCaseList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _testCaseList.set(index, vTestCase);
    } //-- void setTestCase(int, lib.xml.test_result.TestCase) 

    /**
     * Method setTestCase
     * 
     * 
     * 
     * @param testCaseArray
     */
    public void setTestCase(lib.xml.test_result.TestCase[] testCaseArray)
    {
        //-- copy array
        _testCaseList.clear();
        for (int i = 0; i < testCaseArray.length; i++) {
            _testCaseList.add(testCaseArray[i]);
        }
    } //-- void setTestCase(lib.xml.test_result.TestCase) 

    /**
     * Sets the value of field 'valid'.
     * 
     * @param valid the value of field 'valid'.
     */
    public void setValid(boolean valid)
    {
        this._valid = valid;
        this._has_valid = true;
    } //-- void setValid(boolean) 

    /**
     * Sets the value of field 'version'.
     * 
     * @param version the value of field 'version'.
     */
    public void setVersion(java.lang.String version)
    {
        this._version = version;
    } //-- void setVersion(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Test
     */
    public static lib.xml.test_result.Test unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.test_result.Test) Unmarshaller.unmarshal(lib.xml.test_result.Test.class, reader);
    } //-- lib.xml.test_result.Test unmarshal(java.io.Reader) 

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
