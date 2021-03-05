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
 * Class Bench.
 * 
 * @version $Revision$ $Date$
 */
public class Bench implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _atr
     */
    private java.lang.String _atr;

    /**
     * Field _cad
     */
    private java.lang.String _cad;

    /**
     * Field _chrono
     */
    private java.lang.String _chrono;

    /**
     * Field _testList
     */
    private java.util.ArrayList _testList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Bench() 
     {
        super();
        _testList = new java.util.ArrayList();
    } //-- lib.xml.test_result.Bench()


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
    public void addTest(lib.xml.test_result.Test vTest)
        throws java.lang.IndexOutOfBoundsException
    {
        _testList.add(vTest);
    } //-- void addTest(lib.xml.test_result.Test) 

    /**
     * Method addTest
     * 
     * 
     * 
     * @param index
     * @param vTest
     */
    public void addTest(int index, lib.xml.test_result.Test vTest)
        throws java.lang.IndexOutOfBoundsException
    {
        _testList.add(index, vTest);
    } //-- void addTest(int, lib.xml.test_result.Test) 

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
     * Returns the value of field 'atr'.
     * 
     * @return String
     * @return the value of field 'atr'.
     */
    public java.lang.String getAtr()
    {
        return this._atr;
    } //-- java.lang.String getAtr() 

    /**
     * Returns the value of field 'cad'.
     * 
     * @return String
     * @return the value of field 'cad'.
     */
    public java.lang.String getCad()
    {
        return this._cad;
    } //-- java.lang.String getCad() 

    /**
     * Returns the value of field 'chrono'.
     * 
     * @return String
     * @return the value of field 'chrono'.
     */
    public java.lang.String getChrono()
    {
        return this._chrono;
    } //-- java.lang.String getChrono() 

    /**
     * Method getTest
     * 
     * 
     * 
     * @param index
     * @return Test
     */
    public lib.xml.test_result.Test getTest(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _testList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (lib.xml.test_result.Test) _testList.get(index);
    } //-- lib.xml.test_result.Test getTest(int) 

    /**
     * Method getTest
     * 
     * 
     * 
     * @return Test
     */
    public lib.xml.test_result.Test[] getTest()
    {
        int size = _testList.size();
        lib.xml.test_result.Test[] mArray = new lib.xml.test_result.Test[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (lib.xml.test_result.Test) _testList.get(index);
        }
        return mArray;
    } //-- lib.xml.test_result.Test[] getTest() 

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
    public boolean removeTest(lib.xml.test_result.Test vTest)
    {
        boolean removed = _testList.remove(vTest);
        return removed;
    } //-- boolean removeTest(lib.xml.test_result.Test) 

    /**
     * Sets the value of field 'atr'.
     * 
     * @param atr the value of field 'atr'.
     */
    public void setAtr(java.lang.String atr)
    {
        this._atr = atr;
    } //-- void setAtr(java.lang.String) 

    /**
     * Sets the value of field 'cad'.
     * 
     * @param cad the value of field 'cad'.
     */
    public void setCad(java.lang.String cad)
    {
        this._cad = cad;
    } //-- void setCad(java.lang.String) 

    /**
     * Sets the value of field 'chrono'.
     * 
     * @param chrono the value of field 'chrono'.
     */
    public void setChrono(java.lang.String chrono)
    {
        this._chrono = chrono;
    } //-- void setChrono(java.lang.String) 

    /**
     * Method setTest
     * 
     * 
     * 
     * @param index
     * @param vTest
     */
    public void setTest(int index, lib.xml.test_result.Test vTest)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _testList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _testList.set(index, vTest);
    } //-- void setTest(int, lib.xml.test_result.Test) 

    /**
     * Method setTest
     * 
     * 
     * 
     * @param testArray
     */
    public void setTest(lib.xml.test_result.Test[] testArray)
    {
        //-- copy array
        _testList.clear();
        for (int i = 0; i < testArray.length; i++) {
            _testList.add(testArray[i]);
        }
    } //-- void setTest(lib.xml.test_result.Test) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Bench
     */
    public static lib.xml.test_result.Bench unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.test_result.Bench) Unmarshaller.unmarshal(lib.xml.test_result.Bench.class, reader);
    } //-- lib.xml.test_result.Bench unmarshal(java.io.Reader) 

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
