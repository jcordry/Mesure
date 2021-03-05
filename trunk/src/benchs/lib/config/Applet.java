/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package benchs.lib.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Applet.
 * 
 * @version $Revision: 324 $ $Date: 2007-07-26 10:24:01 +0200 (jeu., 26 juil. 2007) $
 */
public class Applet implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _aid
     */
    private java.lang.String _aid;

    /**
     * Field _instanceAid
     */
    private java.lang.String _instanceAid;

    /**
     * Field _privileges
     */
    private byte _privileges = 0;

    /**
     * keeps track of state for field: _privileges
     */
    private boolean _has_privileges;

    /**
     * Field _params
     */
    private java.lang.String _params;


      //----------------/
     //- Constructors -/
    //----------------/

    public Applet() 
     {
        super();
    } //-- benchs.lib.config.Applet()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deletePrivileges
     * 
     */
    public void deletePrivileges()
    {
        this._has_privileges= false;
    } //-- void deletePrivileges() 

    /**
     * Returns the value of field 'aid'.
     * 
     * @return String
     * @return the value of field 'aid'.
     */
    public java.lang.String getAid()
    {
        return this._aid;
    } //-- java.lang.String getAid() 

    /**
     * Returns the value of field 'instanceAid'.
     * 
     * @return String
     * @return the value of field 'instanceAid'.
     */
    public java.lang.String getInstanceAid()
    {
        return this._instanceAid;
    } //-- java.lang.String getInstanceAid() 

    /**
     * Returns the value of field 'params'.
     * 
     * @return String
     * @return the value of field 'params'.
     */
    public java.lang.String getParams()
    {
        return this._params;
    } //-- java.lang.String getParams() 

    /**
     * Returns the value of field 'privileges'.
     * 
     * @return byte
     * @return the value of field 'privileges'.
     */
    public byte getPrivileges()
    {
        return this._privileges;
    } //-- byte getPrivileges() 

    /**
     * Method hasPrivileges
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasPrivileges()
    {
        return this._has_privileges;
    } //-- boolean hasPrivileges() 

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
     * Sets the value of field 'aid'.
     * 
     * @param aid the value of field 'aid'.
     */
    public void setAid(java.lang.String aid)
    {
        this._aid = aid;
    } //-- void setAid(java.lang.String) 

    /**
     * Sets the value of field 'instanceAid'.
     * 
     * @param instanceAid the value of field 'instanceAid'.
     */
    public void setInstanceAid(java.lang.String instanceAid)
    {
        this._instanceAid = instanceAid;
    } //-- void setInstanceAid(java.lang.String) 

    /**
     * Sets the value of field 'params'.
     * 
     * @param params the value of field 'params'.
     */
    public void setParams(java.lang.String params)
    {
        this._params = params;
    } //-- void setParams(java.lang.String) 

    /**
     * Sets the value of field 'privileges'.
     * 
     * @param privileges the value of field 'privileges'.
     */
    public void setPrivileges(byte privileges)
    {
        this._privileges = privileges;
        this._has_privileges = true;
    } //-- void setPrivileges(byte) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Applet
     */
    public static benchs.lib.config.Applet unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (benchs.lib.config.Applet) Unmarshaller.unmarshal(benchs.lib.config.Applet.class, reader);
    } //-- benchs.lib.config.Applet unmarshal(java.io.Reader) 

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
