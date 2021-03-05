/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.5</a>, using an XML
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
 * Class Measure.
 * 
 * @version $Revision$ $Date$
 */
public class Measure implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _avg
     */
    private long _avg;

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
     * Field _deviation
     */
    private long _deviation;

    /**
     * keeps track of state for field: _deviation
     */
    private boolean _has_deviation;

    /**
     * Field _trust
     */
    private long _trust;

    /**
     * keeps track of state for field: _trust
     */
    private boolean _has_trust;


      //----------------/
     //- Constructors -/
    //----------------/

    public Measure() 
     {
        super();
    } //-- lib.xml.extractor_result.Measure()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteAvg()
    {
        this._has_avg= false;
    } //-- void deleteAvg() 

    /**
     */
    public void deleteDeviation()
    {
        this._has_deviation= false;
    } //-- void deleteDeviation() 

    /**
     */
    public void deleteSampleSize()
    {
        this._has_sampleSize= false;
    } //-- void deleteSampleSize() 

    /**
     */
    public void deleteTrust()
    {
        this._has_trust= false;
    } //-- void deleteTrust() 

    /**
     * Returns the value of field 'avg'.
     * 
     * @return the value of field 'Avg'.
     */
    public long getAvg()
    {
        return this._avg;
    } //-- long getAvg() 

    /**
     * Returns the value of field 'deviation'.
     * 
     * @return the value of field 'Deviation'.
     */
    public long getDeviation()
    {
        return this._deviation;
    } //-- long getDeviation() 

    /**
     * Returns the value of field 'sampleSize'.
     * 
     * @return the value of field 'SampleSize'.
     */
    public long getSampleSize()
    {
        return this._sampleSize;
    } //-- long getSampleSize() 

    /**
     * Returns the value of field 'trust'.
     * 
     * @return the value of field 'Trust'.
     */
    public long getTrust()
    {
        return this._trust;
    } //-- long getTrust() 

    /**
     * Method hasAvg
     * 
     * 
     * 
     * @return true if at least one Avg has been added
     */
    public boolean hasAvg()
    {
        return this._has_avg;
    } //-- boolean hasAvg() 

    /**
     * Method hasDeviation
     * 
     * 
     * 
     * @return true if at least one Deviation has been added
     */
    public boolean hasDeviation()
    {
        return this._has_deviation;
    } //-- boolean hasDeviation() 

    /**
     * Method hasSampleSize
     * 
     * 
     * 
     * @return true if at least one SampleSize has been added
     */
    public boolean hasSampleSize()
    {
        return this._has_sampleSize;
    } //-- boolean hasSampleSize() 

    /**
     * Method hasTrust
     * 
     * 
     * 
     * @return true if at least one Trust has been added
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
     * @return true if this object is valid according to the schema
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
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
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
    public void setAvg(long avg)
    {
        this._avg = avg;
        this._has_avg = true;
    } //-- void setAvg(long) 

    /**
     * Sets the value of field 'deviation'.
     * 
     * @param deviation the value of field 'deviation'.
     */
    public void setDeviation(long deviation)
    {
        this._deviation = deviation;
        this._has_deviation = true;
    } //-- void setDeviation(long) 

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
     * Sets the value of field 'trust'.
     * 
     * @param trust the value of field 'trust'.
     */
    public void setTrust(long trust)
    {
        this._trust = trust;
        this._has_trust = true;
    } //-- void setTrust(long) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled lib.xml.extractor_result.Measure
     */
    public static lib.xml.extractor_result.Measure unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.extractor_result.Measure) Unmarshaller.unmarshal(lib.xml.extractor_result.Measure.class, reader);
    } //-- lib.xml.extractor_result.Measure unmarshal(java.io.Reader) 

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
