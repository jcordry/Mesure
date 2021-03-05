/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package tools.manager.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class ManagerConfig.
 * 
 * @version $Revision$ $Date$
 */
public class ManagerConfig implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _cad
     */
    private java.lang.String _cad = "PCSC";

    /**
     * Field _timeProvider
     */
    private java.lang.String _timeProvider;

    /**
     * Field _cardConfig
     */
    private java.lang.String _cardConfig;

    /**
     * Field _tests
     */
    private tools.manager.config.Tests _tests;


      //----------------/
     //- Constructors -/
    //----------------/

    public ManagerConfig() 
     {
        super();
        setCad("PCSC");
    } //-- tools.manager.config.ManagerConfig()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'cardConfig'.
     * 
     * @return String
     * @return the value of field 'cardConfig'.
     */
    public java.lang.String getCardConfig()
    {
        return this._cardConfig;
    } //-- java.lang.String getCardConfig() 

    /**
     * Returns the value of field 'tests'.
     * 
     * @return Tests
     * @return the value of field 'tests'.
     */
    public tools.manager.config.Tests getTests()
    {
        return this._tests;
    } //-- tools.manager.config.Tests getTests() 

    /**
     * Returns the value of field 'timeProvider'.
     * 
     * @return String
     * @return the value of field 'timeProvider'.
     */
    public java.lang.String getTimeProvider()
    {
        return this._timeProvider;
    } //-- java.lang.String getTimeProvider() 

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
     * Sets the value of field 'cad'.
     * 
     * @param cad the value of field 'cad'.
     */
    public void setCad(java.lang.String cad)
    {
        this._cad = cad;
    } //-- void setCad(java.lang.String) 

    /**
     * Sets the value of field 'cardConfig'.
     * 
     * @param cardConfig the value of field 'cardConfig'.
     */
    public void setCardConfig(java.lang.String cardConfig)
    {
        this._cardConfig = cardConfig;
    } //-- void setCardConfig(java.lang.String) 

    /**
     * Sets the value of field 'tests'.
     * 
     * @param tests the value of field 'tests'.
     */
    public void setTests(tools.manager.config.Tests tests)
    {
        this._tests = tests;
    } //-- void setTests(tools.manager.config.Tests) 

    /**
     * Sets the value of field 'timeProvider'.
     * 
     * @param timeProvider the value of field 'timeProvider'.
     */
    public void setTimeProvider(java.lang.String timeProvider)
    {
        this._timeProvider = timeProvider;
    } //-- void setTimeProvider(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ManagerConfig
     */
    public static tools.manager.config.ManagerConfig unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (tools.manager.config.ManagerConfig) Unmarshaller.unmarshal(tools.manager.config.ManagerConfig.class, reader);
    } //-- tools.manager.config.ManagerConfig unmarshal(java.io.Reader) 

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
