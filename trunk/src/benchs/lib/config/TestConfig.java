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
 * Class TestConfig.
 * 
 * @version $Revision: 324 $ $Date: 2007-07-26 10:24:01 +0200 (jeu., 26 juil. 2007) $
 */
public class TestConfig implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _package
     */
    private benchs.lib.config.Package _package;

    /**
     * Field _script
     */
    private java.lang.String _script;


      //----------------/
     //- Constructors -/
    //----------------/

    public TestConfig() 
     {
        super();
    } //-- benchs.lib.config.TestConfig()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'package'.
     * 
     * @return Package
     * @return the value of field 'package'.
     */
    public benchs.lib.config.Package getPackage()
    {
        return this._package;
    } //-- benchs.lib.config.Package getPackage() 

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
     * Sets the value of field 'package'.
     * 
     * @param _package
     * @param package the value of field 'package'.
     */
    public void setPackage(benchs.lib.config.Package _package)
    {
        this._package = _package;
    } //-- void setPackage(benchs.lib.config.Package) 

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
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return TestConfig
     */
    public static benchs.lib.config.TestConfig unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (benchs.lib.config.TestConfig) Unmarshaller.unmarshal(benchs.lib.config.TestConfig.class, reader);
    } //-- benchs.lib.config.TestConfig unmarshal(java.io.Reader) 

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
