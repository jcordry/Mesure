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
 * Class Results.
 * 
 * @version $Revision$ $Date$
 */
public class Results implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _mark
     */
    private double _mark;

    /**
     * keeps track of state for field: _mark
     */
    private boolean _has_mark;

    /**
     * Field _bytecodeAverageList
     */
    private lib.xml.profiler_result.BytecodeAverageList _bytecodeAverageList;

    /**
     * Field _methodAverageList
     */
    private lib.xml.profiler_result.MethodAverageList _methodAverageList;

    /**
     * Field _domainList
     */
    private java.util.ArrayList _domainList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Results() 
     {
        super();
        _domainList = new java.util.ArrayList();
    } //-- lib.xml.profiler_result.Results()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDomain
     * 
     * 
     * 
     * @param vDomain
     */
    public void addDomain(lib.xml.profiler_result.Domain vDomain)
        throws java.lang.IndexOutOfBoundsException
    {
        if (!(_domainList.size() < 3)) {
            throw new IndexOutOfBoundsException();
        }
        _domainList.add(vDomain);
    } //-- void addDomain(lib.xml.profiler_result.Domain) 

    /**
     * Method addDomain
     * 
     * 
     * 
     * @param index
     * @param vDomain
     */
    public void addDomain(int index, lib.xml.profiler_result.Domain vDomain)
        throws java.lang.IndexOutOfBoundsException
    {
        if (!(_domainList.size() < 3)) {
            throw new IndexOutOfBoundsException();
        }
        _domainList.add(index, vDomain);
    } //-- void addDomain(int, lib.xml.profiler_result.Domain) 

    /**
     * Method clearDomain
     * 
     */
    public void clearDomain()
    {
        _domainList.clear();
    } //-- void clearDomain() 

    /**
     * Method deleteMark
     * 
     */
    public void deleteMark()
    {
        this._has_mark= false;
    } //-- void deleteMark() 

    /**
     * Method enumerateDomain
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDomain()
    {
        return Collections.enumeration(_domainList);
    } //-- java.util.Enumeration enumerateDomain() 

    /**
     * Returns the value of field 'bytecodeAverageList'.
     * 
     * @return BytecodeAverageList
     * @return the value of field 'bytecodeAverageList'.
     */
    public lib.xml.profiler_result.BytecodeAverageList getBytecodeAverageList()
    {
        return this._bytecodeAverageList;
    } //-- lib.xml.profiler_result.BytecodeAverageList getBytecodeAverageList() 

    /**
     * Method getDomain
     * 
     * 
     * 
     * @param index
     * @return Domain
     */
    public lib.xml.profiler_result.Domain getDomain(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _domainList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (lib.xml.profiler_result.Domain) _domainList.get(index);
    } //-- lib.xml.profiler_result.Domain getDomain(int) 

    /**
     * Method getDomain
     * 
     * 
     * 
     * @return Domain
     */
    public lib.xml.profiler_result.Domain[] getDomain()
    {
        int size = _domainList.size();
        lib.xml.profiler_result.Domain[] mArray = new lib.xml.profiler_result.Domain[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (lib.xml.profiler_result.Domain) _domainList.get(index);
        }
        return mArray;
    } //-- lib.xml.profiler_result.Domain[] getDomain() 

    /**
     * Method getDomainCount
     * 
     * 
     * 
     * @return int
     */
    public int getDomainCount()
    {
        return _domainList.size();
    } //-- int getDomainCount() 

    /**
     * Returns the value of field 'mark'.
     * 
     * @return double
     * @return the value of field 'mark'.
     */
    public double getMark()
    {
        return this._mark;
    } //-- double getMark() 

    /**
     * Returns the value of field 'methodAverageList'.
     * 
     * @return MethodAverageList
     * @return the value of field 'methodAverageList'.
     */
    public lib.xml.profiler_result.MethodAverageList getMethodAverageList()
    {
        return this._methodAverageList;
    } //-- lib.xml.profiler_result.MethodAverageList getMethodAverageList() 

    /**
     * Method hasMark
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMark()
    {
        return this._has_mark;
    } //-- boolean hasMark() 

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
     * Method removeDomain
     * 
     * 
     * 
     * @param vDomain
     * @return boolean
     */
    public boolean removeDomain(lib.xml.profiler_result.Domain vDomain)
    {
        boolean removed = _domainList.remove(vDomain);
        return removed;
    } //-- boolean removeDomain(lib.xml.profiler_result.Domain) 

    /**
     * Sets the value of field 'bytecodeAverageList'.
     * 
     * @param bytecodeAverageList the value of field
     * 'bytecodeAverageList'.
     */
    public void setBytecodeAverageList(lib.xml.profiler_result.BytecodeAverageList bytecodeAverageList)
    {
        this._bytecodeAverageList = bytecodeAverageList;
    } //-- void setBytecodeAverageList(lib.xml.profiler_result.BytecodeAverageList) 

    /**
     * Method setDomain
     * 
     * 
     * 
     * @param index
     * @param vDomain
     */
    public void setDomain(int index, lib.xml.profiler_result.Domain vDomain)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _domainList.size())) {
            throw new IndexOutOfBoundsException();
        }
        if (!(index < 3)) {
            throw new IndexOutOfBoundsException();
        }
        _domainList.set(index, vDomain);
    } //-- void setDomain(int, lib.xml.profiler_result.Domain) 

    /**
     * Method setDomain
     * 
     * 
     * 
     * @param domainArray
     */
    public void setDomain(lib.xml.profiler_result.Domain[] domainArray)
    {
        //-- copy array
        _domainList.clear();
        for (int i = 0; i < domainArray.length; i++) {
            _domainList.add(domainArray[i]);
        }
    } //-- void setDomain(lib.xml.profiler_result.Domain) 

    /**
     * Sets the value of field 'mark'.
     * 
     * @param mark the value of field 'mark'.
     */
    public void setMark(double mark)
    {
        this._mark = mark;
        this._has_mark = true;
    } //-- void setMark(double) 

    /**
     * Sets the value of field 'methodAverageList'.
     * 
     * @param methodAverageList the value of field
     * 'methodAverageList'.
     */
    public void setMethodAverageList(lib.xml.profiler_result.MethodAverageList methodAverageList)
    {
        this._methodAverageList = methodAverageList;
    } //-- void setMethodAverageList(lib.xml.profiler_result.MethodAverageList) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Results
     */
    public static lib.xml.profiler_result.Results unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.profiler_result.Results) Unmarshaller.unmarshal(lib.xml.profiler_result.Results.class, reader);
    } //-- lib.xml.profiler_result.Results unmarshal(java.io.Reader) 

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
