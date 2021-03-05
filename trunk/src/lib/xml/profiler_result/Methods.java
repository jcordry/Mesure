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
 * Class Methods.
 * 
 * @version $Revision$ $Date$
 */
public class Methods implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _totalocc
     */
    private int _totalocc;

    /**
     * keeps track of state for field: _totalocc
     */
    private boolean _has_totalocc;

    /**
     * Field _methodList
     */
    private java.util.ArrayList _methodList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Methods() 
     {
        super();
        _methodList = new java.util.ArrayList();
    } //-- lib.xml.profiler_result.Methods()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addMethod
     * 
     * 
     * 
     * @param vMethod
     */
    public void addMethod(lib.xml.profiler_result.Method vMethod)
        throws java.lang.IndexOutOfBoundsException
    {
        _methodList.add(vMethod);
    } //-- void addMethod(lib.xml.profiler_result.Method) 

    /**
     * Method addMethod
     * 
     * 
     * 
     * @param index
     * @param vMethod
     */
    public void addMethod(int index, lib.xml.profiler_result.Method vMethod)
        throws java.lang.IndexOutOfBoundsException
    {
        _methodList.add(index, vMethod);
    } //-- void addMethod(int, lib.xml.profiler_result.Method) 

    /**
     * Method clearMethod
     * 
     */
    public void clearMethod()
    {
        _methodList.clear();
    } //-- void clearMethod() 

    /**
     * Method deleteTotalocc
     * 
     */
    public void deleteTotalocc()
    {
        this._has_totalocc= false;
    } //-- void deleteTotalocc() 

    /**
     * Method enumerateMethod
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateMethod()
    {
        return Collections.enumeration(_methodList);
    } //-- java.util.Enumeration enumerateMethod() 

    /**
     * Method getMethod
     * 
     * 
     * 
     * @param index
     * @return Method
     */
    public lib.xml.profiler_result.Method getMethod(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _methodList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (lib.xml.profiler_result.Method) _methodList.get(index);
    } //-- lib.xml.profiler_result.Method getMethod(int) 

    /**
     * Method getMethod
     * 
     * 
     * 
     * @return Method
     */
    public lib.xml.profiler_result.Method[] getMethod()
    {
        int size = _methodList.size();
        lib.xml.profiler_result.Method[] mArray = new lib.xml.profiler_result.Method[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (lib.xml.profiler_result.Method) _methodList.get(index);
        }
        return mArray;
    } //-- lib.xml.profiler_result.Method[] getMethod() 

    /**
     * Method getMethodCount
     * 
     * 
     * 
     * @return int
     */
    public int getMethodCount()
    {
        return _methodList.size();
    } //-- int getMethodCount() 

    /**
     * Returns the value of field 'totalocc'.
     * 
     * @return int
     * @return the value of field 'totalocc'.
     */
    public int getTotalocc()
    {
        return this._totalocc;
    } //-- int getTotalocc() 

    /**
     * Method hasTotalocc
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasTotalocc()
    {
        return this._has_totalocc;
    } //-- boolean hasTotalocc() 

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
     * Method removeMethod
     * 
     * 
     * 
     * @param vMethod
     * @return boolean
     */
    public boolean removeMethod(lib.xml.profiler_result.Method vMethod)
    {
        boolean removed = _methodList.remove(vMethod);
        return removed;
    } //-- boolean removeMethod(lib.xml.profiler_result.Method) 

    /**
     * Method setMethod
     * 
     * 
     * 
     * @param index
     * @param vMethod
     */
    public void setMethod(int index, lib.xml.profiler_result.Method vMethod)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _methodList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _methodList.set(index, vMethod);
    } //-- void setMethod(int, lib.xml.profiler_result.Method) 

    /**
     * Method setMethod
     * 
     * 
     * 
     * @param methodArray
     */
    public void setMethod(lib.xml.profiler_result.Method[] methodArray)
    {
        //-- copy array
        _methodList.clear();
        for (int i = 0; i < methodArray.length; i++) {
            _methodList.add(methodArray[i]);
        }
    } //-- void setMethod(lib.xml.profiler_result.Method) 

    /**
     * Sets the value of field 'totalocc'.
     * 
     * @param totalocc the value of field 'totalocc'.
     */
    public void setTotalocc(int totalocc)
    {
        this._totalocc = totalocc;
        this._has_totalocc = true;
    } //-- void setTotalocc(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Methods
     */
    public static lib.xml.profiler_result.Methods unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.profiler_result.Methods) Unmarshaller.unmarshal(lib.xml.profiler_result.Methods.class, reader);
    } //-- lib.xml.profiler_result.Methods unmarshal(java.io.Reader) 

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
