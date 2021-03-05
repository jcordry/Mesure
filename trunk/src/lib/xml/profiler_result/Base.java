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
 * Class Base.
 * 
 * @version $Revision$ $Date$
 */
public class Base implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _lang
     */
    private java.lang.String _lang = "en";

    /**
     * Field _managerConfig
     */
    private java.lang.Object _managerConfig;

    /**
     * Field _bench
     */
    private java.lang.Object _bench;

    /**
     * Field _results
     */
    private java.lang.Object _results;

    /**
     * Field _extractedResult
     */
    private java.lang.Object _extractedResult;

    /**
     * Field _environnement
     */
    private lib.xml.profiler_result.Environnement _environnement;


      //----------------/
     //- Constructors -/
    //----------------/

    public Base() 
     {
        super();
        setLang("en");
    } //-- lib.xml.profiler_result.Base()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bench'.
     * 
     * @return Object
     * @return the value of field 'bench'.
     */
    public java.lang.Object getBench()
    {
        return this._bench;
    } //-- java.lang.Object getBench() 

    /**
     * Returns the value of field 'environnement'.
     * 
     * @return Environnement
     * @return the value of field 'environnement'.
     */
    public lib.xml.profiler_result.Environnement getEnvironnement()
    {
        return this._environnement;
    } //-- lib.xml.profiler_result.Environnement getEnvironnement() 

    /**
     * Returns the value of field 'extractedResult'.
     * 
     * @return Object
     * @return the value of field 'extractedResult'.
     */
    public java.lang.Object getExtractedResult()
    {
        return this._extractedResult;
    } //-- java.lang.Object getExtractedResult() 

    /**
     * Returns the value of field 'lang'.
     * 
     * @return String
     * @return the value of field 'lang'.
     */
    public java.lang.String getLang()
    {
        return this._lang;
    } //-- java.lang.String getLang() 

    /**
     * Returns the value of field 'managerConfig'.
     * 
     * @return Object
     * @return the value of field 'managerConfig'.
     */
    public java.lang.Object getManagerConfig()
    {
        return this._managerConfig;
    } //-- java.lang.Object getManagerConfig() 

    /**
     * Returns the value of field 'results'.
     * 
     * @return Object
     * @return the value of field 'results'.
     */
    public java.lang.Object getResults()
    {
        return this._results;
    } //-- java.lang.Object getResults() 

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
     * Sets the value of field 'bench'.
     * 
     * @param bench the value of field 'bench'.
     */
    public void setBench(java.lang.Object bench)
    {
        this._bench = bench;
    } //-- void setBench(java.lang.Object) 

    /**
     * Sets the value of field 'environnement'.
     * 
     * @param environnement the value of field 'environnement'.
     */
    public void setEnvironnement(lib.xml.profiler_result.Environnement environnement)
    {
        this._environnement = environnement;
    } //-- void setEnvironnement(lib.xml.profiler_result.Environnement) 

    /**
     * Sets the value of field 'extractedResult'.
     * 
     * @param extractedResult the value of field 'extractedResult'.
     */
    public void setExtractedResult(java.lang.Object extractedResult)
    {
        this._extractedResult = extractedResult;
    } //-- void setExtractedResult(java.lang.Object) 

    /**
     * Sets the value of field 'lang'.
     * 
     * @param lang the value of field 'lang'.
     */
    public void setLang(java.lang.String lang)
    {
        this._lang = lang;
    } //-- void setLang(java.lang.String) 

    /**
     * Sets the value of field 'managerConfig'.
     * 
     * @param managerConfig the value of field 'managerConfig'.
     */
    public void setManagerConfig(java.lang.Object managerConfig)
    {
        this._managerConfig = managerConfig;
    } //-- void setManagerConfig(java.lang.Object) 

    /**
     * Sets the value of field 'results'.
     * 
     * @param results the value of field 'results'.
     */
    public void setResults(java.lang.Object results)
    {
        this._results = results;
    } //-- void setResults(java.lang.Object) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Base
     */
    public static lib.xml.profiler_result.Base unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.profiler_result.Base) Unmarshaller.unmarshal(lib.xml.profiler_result.Base.class, reader);
    } //-- lib.xml.profiler_result.Base unmarshal(java.io.Reader) 

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
