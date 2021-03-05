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
 * Class Tests.
 * 
 * @version $Revision$ $Date$
 */
public class Tests implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _testList
     */
    private java.util.ArrayList _testList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Tests() 
     {
        super();
        _testList = new java.util.ArrayList();
    } //-- tools.manager.config.Tests()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addTest
     * 
     * 
     * 
     * @param vTest
     */
    public void addTest(tools.manager.config.Test vTest)
        throws java.lang.IndexOutOfBoundsException
    {
        _testList.add(vTest);
    } //-- void addTest(tools.manager.config.Test) 

    /**
     * Method addTest
     * 
     * 
     * 
     * @param index
     * @param vTest
     */
    public void addTest(int index, tools.manager.config.Test vTest)
        throws java.lang.IndexOutOfBoundsException
    {
        _testList.add(index, vTest);
    } //-- void addTest(int, tools.manager.config.Test) 

    /**
     * Method clearTest
     * 
     */
    public void clearTest()
    {
        _testList.clear();
    } //-- void clearTest() 

    /**
     * Method enumerateTest
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateTest()
    {
        return Collections.enumeration(_testList);
    } //-- java.util.Enumeration enumerateTest() 

    /**
     * Method getTest
     * 
     * 
     * 
     * @param index
     * @return Test
     */
    public tools.manager.config.Test getTest(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _testList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (tools.manager.config.Test) _testList.get(index);
    } //-- tools.manager.config.Test getTest(int) 

    /**
     * Method getTest
     * 
     * 
     * 
     * @return Test
     */
    public tools.manager.config.Test[] getTest()
    {
        int size = _testList.size();
        tools.manager.config.Test[] mArray = new tools.manager.config.Test[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (tools.manager.config.Test) _testList.get(index);
        }
        return mArray;
    } //-- tools.manager.config.Test[] getTest() 

    /**
     * Method getTestCount
     * 
     * 
     * 
     * @return int
     */
    public int getTestCount()
    {
        return _testList.size();
    } //-- int getTestCount() 

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
     * Method removeTest
     * 
     * 
     * 
     * @param vTest
     * @return boolean
     */
    public boolean removeTest(tools.manager.config.Test vTest)
    {
        boolean removed = _testList.remove(vTest);
        return removed;
    } //-- boolean removeTest(tools.manager.config.Test) 

    /**
     * Method setTest
     * 
     * 
     * 
     * @param index
     * @param vTest
     */
    public void setTest(int index, tools.manager.config.Test vTest)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _testList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _testList.set(index, vTest);
    } //-- void setTest(int, tools.manager.config.Test) 

    /**
     * Method setTest
     * 
     * 
     * 
     * @param testArray
     */
    public void setTest(tools.manager.config.Test[] testArray)
    {
        //-- copy array
        _testList.clear();
        for (int i = 0; i < testArray.length; i++) {
            _testList.add(testArray[i]);
        }
    } //-- void setTest(tools.manager.config.Test) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Tests
     */
    public static tools.manager.config.Tests unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (tools.manager.config.Tests) Unmarshaller.unmarshal(tools.manager.config.Tests.class, reader);
    } //-- tools.manager.config.Tests unmarshal(java.io.Reader) 

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
