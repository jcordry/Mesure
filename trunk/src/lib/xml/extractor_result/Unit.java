/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package lib.xml.extractor_result;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Unit.
 * 
 * @version $Revision$ $Date$
 */
public class Unit implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _config
     */
    private java.lang.String _config;

    /**
     * Field _result
     */
    private java.lang.String _result;

    /**
     * Field _reference
     */
    private java.lang.String _reference;

    /**
     * Field _script
     */
    private java.lang.String _script;

    /**
     * Field _benchedmode
     */
    private java.lang.String _benchedmode;

    /**
     * Field _time
     */
    private lib.xml.extractor_result.Time _time;


      //----------------/
     //- Constructors -/
    //----------------/

    public Unit() 
     {
        super();
    } //-- lib.xml.extractor_result.Unit()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'benchedmode'.
     * 
     * @return String
     * @return the value of field 'benchedmode'.
     */
    public java.lang.String getBenchedmode()
    {
        return this._benchedmode;
    } //-- java.lang.String getBenchedmode() 

    /**
     * Returns the value of field 'config'.
     * 
     * @return String
     * @return the value of field 'config'.
     */
    public java.lang.String getConfig()
    {
        return this._config;
    } //-- java.lang.String getConfig() 

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
     * Returns the value of field 'reference'.
     * 
     * @return String
     * @return the value of field 'reference'.
     */
    public java.lang.String getReference()
    {
        return this._reference;
    } //-- java.lang.String getReference() 

    /**
     * Returns the value of field 'result'.
     * 
     * @return String
     * @return the value of field 'result'.
     */
    public java.lang.String getResult()
    {
        return this._result;
    } //-- java.lang.String getResult() 

    /**
     * Returns the value of field 'script'.
     * 
     * @return String
     * @return the value of field 'script'.
     */
    public java.lang.String getScript()
    {
        return this._script;
    } //-- java.lang.String getScript() 

    /**
     * Returns the value of field 'time'.
     * 
     * @return Time
     * @return the value of field 'time'.
     */
    public lib.xml.extractor_result.Time getTime()
    {
        return this._time;
    } //-- lib.xml.extractor_result.Time getTime() 

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
     * Sets the value of field 'benchedmode'.
     * 
     * @param benchedmode the value of field 'benchedmode'.
     */
    public void setBenchedmode(java.lang.String benchedmode)
    {
        this._benchedmode = benchedmode;
    } //-- void setBenchedmode(java.lang.String) 

    /**
     * Sets the value of field 'config'.
     * 
     * @param config the value of field 'config'.
     */
    public void setConfig(java.lang.String config)
    {
        this._config = config;
    } //-- void setConfig(java.lang.String) 

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
     * Sets the value of field 'reference'.
     * 
     * @param reference the value of field 'reference'.
     */
    public void setReference(java.lang.String reference)
    {
        this._reference = reference;
    } //-- void setReference(java.lang.String) 

    /**
     * Sets the value of field 'result'.
     * 
     * @param result the value of field 'result'.
     */
    public void setResult(java.lang.String result)
    {
        this._result = result;
    } //-- void setResult(java.lang.String) 

    /**
     * Sets the value of field 'script'.
     * 
     * @param script the value of field 'script'.
     */
    public void setScript(java.lang.String script)
    {
        this._script = script;
    } //-- void setScript(java.lang.String) 

    /**
     * Sets the value of field 'time'.
     * 
     * @param time the value of field 'time'.
     */
    public void setTime(lib.xml.extractor_result.Time time)
    {
        this._time = time;
    } //-- void setTime(lib.xml.extractor_result.Time) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Unit
     */
    public static lib.xml.extractor_result.Unit unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.extractor_result.Unit) Unmarshaller.unmarshal(lib.xml.extractor_result.Unit.class, reader);
    } //-- lib.xml.extractor_result.Unit unmarshal(java.io.Reader) 

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
