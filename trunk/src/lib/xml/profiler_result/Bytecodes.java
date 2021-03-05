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
 * Class Bytecodes.
 * 
 * @version $Revision$ $Date$
 */
public class Bytecodes implements java.io.Serializable {


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
     * Field _bytecodeList
     */
    private java.util.ArrayList _bytecodeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Bytecodes() 
     {
        super();
        _bytecodeList = new java.util.ArrayList();
    } //-- lib.xml.profiler_result.Bytecodes()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addBytecode
     * 
     * 
     * 
     * @param vBytecode
     */
    public void addBytecode(lib.xml.profiler_result.Bytecode vBytecode)
        throws java.lang.IndexOutOfBoundsException
    {
        _bytecodeList.add(vBytecode);
    } //-- void addBytecode(lib.xml.profiler_result.Bytecode) 

    /**
     * Method addBytecode
     * 
     * 
     * 
     * @param index
     * @param vBytecode
     */
    public void addBytecode(int index, lib.xml.profiler_result.Bytecode vBytecode)
        throws java.lang.IndexOutOfBoundsException
    {
        _bytecodeList.add(index, vBytecode);
    } //-- void addBytecode(int, lib.xml.profiler_result.Bytecode) 

    /**
     * Method clearBytecode
     * 
     */
    public void clearBytecode()
    {
        _bytecodeList.clear();
    } //-- void clearBytecode() 

    /**
     * Method deleteTotalocc
     * 
     */
    public void deleteTotalocc()
    {
        this._has_totalocc= false;
    } //-- void deleteTotalocc() 

    /**
     * Method enumerateBytecode
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateBytecode()
    {
        return Collections.enumeration(_bytecodeList);
    } //-- java.util.Enumeration enumerateBytecode() 

    /**
     * Method getBytecode
     * 
     * 
     * 
     * @param index
     * @return Bytecode
     */
    public lib.xml.profiler_result.Bytecode getBytecode(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _bytecodeList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (lib.xml.profiler_result.Bytecode) _bytecodeList.get(index);
    } //-- lib.xml.profiler_result.Bytecode getBytecode(int) 

    /**
     * Method getBytecode
     * 
     * 
     * 
     * @return Bytecode
     */
    public lib.xml.profiler_result.Bytecode[] getBytecode()
    {
        int size = _bytecodeList.size();
        lib.xml.profiler_result.Bytecode[] mArray = new lib.xml.profiler_result.Bytecode[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (lib.xml.profiler_result.Bytecode) _bytecodeList.get(index);
        }
        return mArray;
    } //-- lib.xml.profiler_result.Bytecode[] getBytecode() 

    /**
     * Method getBytecodeCount
     * 
     * 
     * 
     * @return int
     */
    public int getBytecodeCount()
    {
        return _bytecodeList.size();
    } //-- int getBytecodeCount() 

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
     * Method removeBytecode
     * 
     * 
     * 
     * @param vBytecode
     * @return boolean
     */
    public boolean removeBytecode(lib.xml.profiler_result.Bytecode vBytecode)
    {
        boolean removed = _bytecodeList.remove(vBytecode);
        return removed;
    } //-- boolean removeBytecode(lib.xml.profiler_result.Bytecode) 

    /**
     * Method setBytecode
     * 
     * 
     * 
     * @param index
     * @param vBytecode
     */
    public void setBytecode(int index, lib.xml.profiler_result.Bytecode vBytecode)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _bytecodeList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _bytecodeList.set(index, vBytecode);
    } //-- void setBytecode(int, lib.xml.profiler_result.Bytecode) 

    /**
     * Method setBytecode
     * 
     * 
     * 
     * @param bytecodeArray
     */
    public void setBytecode(lib.xml.profiler_result.Bytecode[] bytecodeArray)
    {
        //-- copy array
        _bytecodeList.clear();
        for (int i = 0; i < bytecodeArray.length; i++) {
            _bytecodeList.add(bytecodeArray[i]);
        }
    } //-- void setBytecode(lib.xml.profiler_result.Bytecode) 

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
     * @return Bytecodes
     */
    public static lib.xml.profiler_result.Bytecodes unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.profiler_result.Bytecodes) Unmarshaller.unmarshal(lib.xml.profiler_result.Bytecodes.class, reader);
    } //-- lib.xml.profiler_result.Bytecodes unmarshal(java.io.Reader) 

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
