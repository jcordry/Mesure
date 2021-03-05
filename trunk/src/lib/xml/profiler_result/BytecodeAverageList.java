/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package lib.xml.profiler_result;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Collections;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class BytecodeAverageList.
 * 
 * @version $Revision$ $Date$
 */
public class BytecodeAverageList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _bytecodeAverageList
     */
    private java.util.ArrayList _bytecodeAverageList;


      //----------------/
     //- Constructors -/
    //----------------/

    public BytecodeAverageList() 
     {
        super();
        _bytecodeAverageList = new java.util.ArrayList();
    } //-- lib.xml.profiler_result.BytecodeAverageList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addBytecodeAverage
     * 
     * 
     * 
     * @param vBytecodeAverage
     */
    public void addBytecodeAverage(lib.xml.profiler_result.BytecodeAverage vBytecodeAverage)
        throws java.lang.IndexOutOfBoundsException
    {
        _bytecodeAverageList.add(vBytecodeAverage);
    } //-- void addBytecodeAverage(lib.xml.profiler_result.BytecodeAverage) 

    /**
     * Method addBytecodeAverage
     * 
     * 
     * 
     * @param index
     * @param vBytecodeAverage
     */
    public void addBytecodeAverage(int index, lib.xml.profiler_result.BytecodeAverage vBytecodeAverage)
        throws java.lang.IndexOutOfBoundsException
    {
        _bytecodeAverageList.add(index, vBytecodeAverage);
    } //-- void addBytecodeAverage(int, lib.xml.profiler_result.BytecodeAverage) 

    /**
     * Method clearBytecodeAverage
     * 
     */
    public void clearBytecodeAverage()
    {
        _bytecodeAverageList.clear();
    } //-- void clearBytecodeAverage() 

    /**
     * Method enumerateBytecodeAverage
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateBytecodeAverage()
    {
        return Collections.enumeration(_bytecodeAverageList);
    } //-- java.util.Enumeration enumerateBytecodeAverage() 

    /**
     * Method getBytecodeAverage
     * 
     * 
     * 
     * @param index
     * @return BytecodeAverage
     */
    public lib.xml.profiler_result.BytecodeAverage getBytecodeAverage(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _bytecodeAverageList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (lib.xml.profiler_result.BytecodeAverage) _bytecodeAverageList.get(index);
    } //-- lib.xml.profiler_result.BytecodeAverage getBytecodeAverage(int) 

    /**
     * Method getBytecodeAverage
     * 
     * 
     * 
     * @return BytecodeAverage
     */
    public lib.xml.profiler_result.BytecodeAverage[] getBytecodeAverage()
    {
        int size = _bytecodeAverageList.size();
        lib.xml.profiler_result.BytecodeAverage[] mArray = new lib.xml.profiler_result.BytecodeAverage[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (lib.xml.profiler_result.BytecodeAverage) _bytecodeAverageList.get(index);
        }
        return mArray;
    } //-- lib.xml.profiler_result.BytecodeAverage[] getBytecodeAverage() 

    /**
     * Method getBytecodeAverageCount
     * 
     * 
     * 
     * @return int
     */
    public int getBytecodeAverageCount()
    {
        return _bytecodeAverageList.size();
    } //-- int getBytecodeAverageCount() 

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
     * Method removeBytecodeAverage
     * 
     * 
     * 
     * @param vBytecodeAverage
     * @return boolean
     */
    public boolean removeBytecodeAverage(lib.xml.profiler_result.BytecodeAverage vBytecodeAverage)
    {
        boolean removed = _bytecodeAverageList.remove(vBytecodeAverage);
        return removed;
    } //-- boolean removeBytecodeAverage(lib.xml.profiler_result.BytecodeAverage) 

    /**
     * Method setBytecodeAverage
     * 
     * 
     * 
     * @param index
     * @param vBytecodeAverage
     */
    public void setBytecodeAverage(int index, lib.xml.profiler_result.BytecodeAverage vBytecodeAverage)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _bytecodeAverageList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _bytecodeAverageList.set(index, vBytecodeAverage);
    } //-- void setBytecodeAverage(int, lib.xml.profiler_result.BytecodeAverage) 

    /**
     * Method setBytecodeAverage
     * 
     * 
     * 
     * @param bytecodeAverageArray
     */
    public void setBytecodeAverage(lib.xml.profiler_result.BytecodeAverage[] bytecodeAverageArray)
    {
        //-- copy array
        _bytecodeAverageList.clear();
        for (int i = 0; i < bytecodeAverageArray.length; i++) {
            _bytecodeAverageList.add(bytecodeAverageArray[i]);
        }
    } //-- void setBytecodeAverage(lib.xml.profiler_result.BytecodeAverage) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return BytecodeAverageList
     */
    public static lib.xml.profiler_result.BytecodeAverageList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.profiler_result.BytecodeAverageList) Unmarshaller.unmarshal(lib.xml.profiler_result.BytecodeAverageList.class, reader);
    } //-- lib.xml.profiler_result.BytecodeAverageList unmarshal(java.io.Reader) 

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
