/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package lib.xml.test_result;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class TimeMeasure.
 * 
 * @version $Revision$ $Date$
 */
public class TimeMeasure implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _measure
     */
    private double _measure;

    /**
     * keeps track of state for field: _measure
     */
    private boolean _has_measure;


      //----------------/
     //- Constructors -/
    //----------------/

    public TimeMeasure() 
     {
        super();
    } //-- lib.xml.test_result.TimeMeasure()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteMeasure
     * 
     */
    public void deleteMeasure()
    {
        this._has_measure= false;
    } //-- void deleteMeasure() 

    /**
     * Returns the value of field 'measure'.
     * 
     * @return double
     * @return the value of field 'measure'.
     */
    public double getMeasure()
    {
        return this._measure;
    } //-- double getMeasure() 

    /**
     * Method hasMeasure
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMeasure()
    {
        return this._has_measure;
    } //-- boolean hasMeasure() 

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
     * Sets the value of field 'measure'.
     * 
     * @param measure the value of field 'measure'.
     */
    public void setMeasure(double measure)
    {
        this._measure = measure;
        this._has_measure = true;
    } //-- void setMeasure(double) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return TimeMeasure
     */
    public static lib.xml.test_result.TimeMeasure unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.test_result.TimeMeasure) Unmarshaller.unmarshal(lib.xml.test_result.TimeMeasure.class, reader);
    } //-- lib.xml.test_result.TimeMeasure unmarshal(java.io.Reader) 

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
