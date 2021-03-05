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
 * Class Time.
 * 
 * @version $Revision$ $Date$
 */
public class Time implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _avg
     */
    private double _avg;

    /**
     * keeps track of state for field: _avg
     */
    private boolean _has_avg;

    /**
     * Field _sampleSize
     */
    private long _sampleSize;

    /**
     * keeps track of state for field: _sampleSize
     */
    private boolean _has_sampleSize;

    /**
     * Field _stdError
     */
    private double _stdError;

    /**
     * keeps track of state for field: _stdError
     */
    private boolean _has_stdError;

    /**
     * Field _trust
     */
    private double _trust;

    /**
     * keeps track of state for field: _trust
     */
    private boolean _has_trust;


      //----------------/
     //- Constructors -/
    //----------------/

    public Time() 
     {
        super();
    } //-- lib.xml.extractor_result.Time()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteAvg
     * 
     */
    public void deleteAvg()
    {
        this._has_avg= false;
    } //-- void deleteAvg() 

    /**
     * Method deleteSampleSize
     * 
     */
    public void deleteSampleSize()
    {
        this._has_sampleSize= false;
    } //-- void deleteSampleSize() 

    /**
     * Method deleteStdError
     * 
     */
    public void deleteStdError()
    {
        this._has_stdError= false;
    } //-- void deleteStdError() 

    /**
     * Method deleteTrust
     * 
     */
    public void deleteTrust()
    {
        this._has_trust= false;
    } //-- void deleteTrust() 

    /**
     * Returns the value of field 'avg'.
     * 
     * @return double
     * @return the value of field 'avg'.
     */
    public double getAvg()
    {
        return this._avg;
    } //-- double getAvg() 

    /**
     * Returns the value of field 'sampleSize'.
     * 
     * @return long
     * @return the value of field 'sampleSize'.
     */
    public long getSampleSize()
    {
        return this._sampleSize;
    } //-- long getSampleSize() 

    /**
     * Returns the value of field 'stdError'.
     * 
     * @return double
     * @return the value of field 'stdError'.
     */
    public double getStdError()
    {
        return this._stdError;
    } //-- double getStdError() 

    /**
     * Returns the value of field 'trust'.
     * 
     * @return double
     * @return the value of field 'trust'.
     */
    public double getTrust()
    {
        return this._trust;
    } //-- double getTrust() 

    /**
     * Method hasAvg
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasAvg()
    {
        return this._has_avg;
    } //-- boolean hasAvg() 

    /**
     * Method hasSampleSize
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSampleSize()
    {
        return this._has_sampleSize;
    } //-- boolean hasSampleSize() 

    /**
     * Method hasStdError
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasStdError()
    {
        return this._has_stdError;
    } //-- boolean hasStdError() 

    /**
     * Method hasTrust
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasTrust()
    {
        return this._has_trust;
    } //-- boolean hasTrust() 

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
     * Sets the value of field 'avg'.
     * 
     * @param avg the value of field 'avg'.
     */
    public void setAvg(double avg)
    {
        this._avg = avg;
        this._has_avg = true;
    } //-- void setAvg(double) 

    /**
     * Sets the value of field 'sampleSize'.
     * 
     * @param sampleSize the value of field 'sampleSize'.
     */
    public void setSampleSize(long sampleSize)
    {
        this._sampleSize = sampleSize;
        this._has_sampleSize = true;
    } //-- void setSampleSize(long) 

    /**
     * Sets the value of field 'stdError'.
     * 
     * @param stdError the value of field 'stdError'.
     */
    public void setStdError(double stdError)
    {
        this._stdError = stdError;
        this._has_stdError = true;
    } //-- void setStdError(double) 

    /**
     * Sets the value of field 'trust'.
     * 
     * @param trust the value of field 'trust'.
     */
    public void setTrust(double trust)
    {
        this._trust = trust;
        this._has_trust = true;
    } //-- void setTrust(double) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Time
     */
    public static lib.xml.extractor_result.Time unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.extractor_result.Time) Unmarshaller.unmarshal(lib.xml.extractor_result.Time.class, reader);
    } //-- lib.xml.extractor_result.Time unmarshal(java.io.Reader) 

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
