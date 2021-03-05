/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package lib.loader.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Load.
 * 
 * @version $Revision$ $Date$
 */
public class Load implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _lc
     */
    private byte _lc = 127;

    /**
     * keeps track of state for field: _lc
     */
    private boolean _has_lc;

    /**
     * Field _nonVolatileCode
     */
    private boolean _nonVolatileCode = false;

    /**
     * keeps track of state for field: _nonVolatileCode
     */
    private boolean _has_nonVolatileCode;


      //----------------/
     //- Constructors -/
    //----------------/

    public Load() 
     {
        super();
    } //-- lib.loader.config.Load()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteLc
     * 
     */
    public void deleteLc()
    {
        this._has_lc= false;
    } //-- void deleteLc() 

    /**
     * Method deleteNonVolatileCode
     * 
     */
    public void deleteNonVolatileCode()
    {
        this._has_nonVolatileCode= false;
    } //-- void deleteNonVolatileCode() 

    /**
     * Returns the value of field 'lc'.
     * 
     * @return byte
     * @return the value of field 'lc'.
     */
    public byte getLc()
    {
        return this._lc;
    } //-- byte getLc() 

    /**
     * Returns the value of field 'nonVolatileCode'.
     * 
     * @return boolean
     * @return the value of field 'nonVolatileCode'.
     */
    public boolean getNonVolatileCode()
    {
        return this._nonVolatileCode;
    } //-- boolean getNonVolatileCode() 

    /**
     * Method hasLc
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasLc()
    {
        return this._has_lc;
    } //-- boolean hasLc() 

    /**
     * Method hasNonVolatileCode
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasNonVolatileCode()
    {
        return this._has_nonVolatileCode;
    } //-- boolean hasNonVolatileCode() 

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
     * Sets the value of field 'lc'.
     * 
     * @param lc the value of field 'lc'.
     */
    public void setLc(byte lc)
    {
        this._lc = lc;
        this._has_lc = true;
    } //-- void setLc(byte) 

    /**
     * Sets the value of field 'nonVolatileCode'.
     * 
     * @param nonVolatileCode the value of field 'nonVolatileCode'.
     */
    public void setNonVolatileCode(boolean nonVolatileCode)
    {
        this._nonVolatileCode = nonVolatileCode;
        this._has_nonVolatileCode = true;
    } //-- void setNonVolatileCode(boolean) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Load
     */
    public static lib.loader.config.Load unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.loader.config.Load) Unmarshaller.unmarshal(lib.loader.config.Load.class, reader);
    } //-- lib.loader.config.Load unmarshal(java.io.Reader) 

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
