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
 * Class Environnement.
 * 
 * @version $Revision$ $Date$
 */
public class Environnement implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _host
     */
    private java.lang.String _host;

    /**
     * Field _VMJava
     */
    private java.lang.String _VMJava;


      //----------------/
     //- Constructors -/
    //----------------/

    public Environnement() 
     {
        super();
    } //-- lib.xml.profiler_result.Environnement()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'host'.
     * 
     * @return String
     * @return the value of field 'host'.
     */
    public java.lang.String getHost()
    {
        return this._host;
    } //-- java.lang.String getHost() 

    /**
     * Returns the value of field 'VMJava'.
     * 
     * @return String
     * @return the value of field 'VMJava'.
     */
    public java.lang.String getVMJava()
    {
        return this._VMJava;
    } //-- java.lang.String getVMJava() 

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
     * Sets the value of field 'host'.
     * 
     * @param host the value of field 'host'.
     */
    public void setHost(java.lang.String host)
    {
        this._host = host;
    } //-- void setHost(java.lang.String) 

    /**
     * Sets the value of field 'VMJava'.
     * 
     * @param VMJava the value of field 'VMJava'.
     */
    public void setVMJava(java.lang.String VMJava)
    {
        this._VMJava = VMJava;
    } //-- void setVMJava(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Environnement
     */
    public static lib.xml.profiler_result.Environnement unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.profiler_result.Environnement) Unmarshaller.unmarshal(lib.xml.profiler_result.Environnement.class, reader);
    } //-- lib.xml.profiler_result.Environnement unmarshal(java.io.Reader) 

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
