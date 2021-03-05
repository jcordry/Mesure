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
 * Class MethodAverageList.
 * 
 * @version $Revision$ $Date$
 */
public class MethodAverageList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _methodAverageList
     */
    private java.util.ArrayList _methodAverageList;


      //----------------/
     //- Constructors -/
    //----------------/

    public MethodAverageList() 
     {
        super();
        _methodAverageList = new java.util.ArrayList();
    } //-- lib.xml.profiler_result.MethodAverageList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addMethodAverage
     * 
     * 
     * 
     * @param vMethodAverage
     */
    public void addMethodAverage(lib.xml.profiler_result.MethodAverage vMethodAverage)
        throws java.lang.IndexOutOfBoundsException
    {
        _methodAverageList.add(vMethodAverage);
    } //-- void addMethodAverage(lib.xml.profiler_result.MethodAverage) 

    /**
     * Method addMethodAverage
     * 
     * 
     * 
     * @param index
     * @param vMethodAverage
     */
    public void addMethodAverage(int index, lib.xml.profiler_result.MethodAverage vMethodAverage)
        throws java.lang.IndexOutOfBoundsException
    {
        _methodAverageList.add(index, vMethodAverage);
    } //-- void addMethodAverage(int, lib.xml.profiler_result.MethodAverage) 

    /**
     * Method clearMethodAverage
     * 
     */
    public void clearMethodAverage()
    {
        _methodAverageList.clear();
    } //-- void clearMethodAverage() 

    /**
     * Method enumerateMethodAverage
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateMethodAverage()
    {
        return Collections.enumeration(_methodAverageList);
    } //-- java.util.Enumeration enumerateMethodAverage() 

    /**
     * Method getMethodAverage
     * 
     * 
     * 
     * @param index
     * @return MethodAverage
     */
    public lib.xml.profiler_result.MethodAverage getMethodAverage(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _methodAverageList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (lib.xml.profiler_result.MethodAverage) _methodAverageList.get(index);
    } //-- lib.xml.profiler_result.MethodAverage getMethodAverage(int) 

    /**
     * Method getMethodAverage
     * 
     * 
     * 
     * @return MethodAverage
     */
    public lib.xml.profiler_result.MethodAverage[] getMethodAverage()
    {
        int size = _methodAverageList.size();
        lib.xml.profiler_result.MethodAverage[] mArray = new lib.xml.profiler_result.MethodAverage[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (lib.xml.profiler_result.MethodAverage) _methodAverageList.get(index);
        }
        return mArray;
    } //-- lib.xml.profiler_result.MethodAverage[] getMethodAverage() 

    /**
     * Method getMethodAverageCount
     * 
     * 
     * 
     * @return int
     */
    public int getMethodAverageCount()
    {
        return _methodAverageList.size();
    } //-- int getMethodAverageCount() 

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
     * Method removeMethodAverage
     * 
     * 
     * 
     * @param vMethodAverage
     * @return boolean
     */
    public boolean removeMethodAverage(lib.xml.profiler_result.MethodAverage vMethodAverage)
    {
        boolean removed = _methodAverageList.remove(vMethodAverage);
        return removed;
    } //-- boolean removeMethodAverage(lib.xml.profiler_result.MethodAverage) 

    /**
     * Method setMethodAverage
     * 
     * 
     * 
     * @param index
     * @param vMethodAverage
     */
    public void setMethodAverage(int index, lib.xml.profiler_result.MethodAverage vMethodAverage)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _methodAverageList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _methodAverageList.set(index, vMethodAverage);
    } //-- void setMethodAverage(int, lib.xml.profiler_result.MethodAverage) 

    /**
     * Method setMethodAverage
     * 
     * 
     * 
     * @param methodAverageArray
     */
    public void setMethodAverage(lib.xml.profiler_result.MethodAverage[] methodAverageArray)
    {
        //-- copy array
        _methodAverageList.clear();
        for (int i = 0; i < methodAverageArray.length; i++) {
            _methodAverageList.add(methodAverageArray[i]);
        }
    } //-- void setMethodAverage(lib.xml.profiler_result.MethodAverage) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return MethodAverageList
     */
    public static lib.xml.profiler_result.MethodAverageList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.profiler_result.MethodAverageList) Unmarshaller.unmarshal(lib.xml.profiler_result.MethodAverageList.class, reader);
    } //-- lib.xml.profiler_result.MethodAverageList unmarshal(java.io.Reader) 

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
