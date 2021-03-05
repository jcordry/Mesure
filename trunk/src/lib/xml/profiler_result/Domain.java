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

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Domain.
 * 
 * @version $Revision$ $Date$
 */
public class Domain implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _mark
     */
    private double _mark;

    /**
     * keeps track of state for field: _mark
     */
    private boolean _has_mark;

    /**
     * Field _bytecodes
     */
    private lib.xml.profiler_result.Bytecodes _bytecodes;

    /**
     * Field _methods
     */
    private lib.xml.profiler_result.Methods _methods;


      //----------------/
     //- Constructors -/
    //----------------/

    public Domain() 
     {
        super();
    } //-- lib.xml.profiler_result.Domain()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteMark
     * 
     */
    public void deleteMark()
    {
        this._has_mark= false;
    } //-- void deleteMark() 

    /**
     * Returns the value of field 'bytecodes'.
     * 
     * @return Bytecodes
     * @return the value of field 'bytecodes'.
     */
    public lib.xml.profiler_result.Bytecodes getBytecodes()
    {
        return this._bytecodes;
    } //-- lib.xml.profiler_result.Bytecodes getBytecodes() 

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
     * Returns the value of field 'methods'.
     * 
     * @return Methods
     * @return the value of field 'methods'.
     */
    public lib.xml.profiler_result.Methods getMethods()
    {
        return this._methods;
    } //-- lib.xml.profiler_result.Methods getMethods() 

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
     * Sets the value of field 'bytecodes'.
     * 
     * @param bytecodes the value of field 'bytecodes'.
     */
    public void setBytecodes(lib.xml.profiler_result.Bytecodes bytecodes)
    {
        this._bytecodes = bytecodes;
    } //-- void setBytecodes(lib.xml.profiler_result.Bytecodes) 

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
     * Sets the value of field 'methods'.
     * 
     * @param methods the value of field 'methods'.
     */
    public void setMethods(lib.xml.profiler_result.Methods methods)
    {
        this._methods = methods;
    } //-- void setMethods(lib.xml.profiler_result.Methods) 

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
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Domain
     */
    public static lib.xml.profiler_result.Domain unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.profiler_result.Domain) Unmarshaller.unmarshal(lib.xml.profiler_result.Domain.class, reader);
    } //-- lib.xml.profiler_result.Domain unmarshal(java.io.Reader) 

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
