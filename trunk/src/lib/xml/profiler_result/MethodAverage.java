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
 * Class MethodAverage.
 * 
 * @version $Revision$ $Date$
 */
public class MethodAverage implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _avg
     */
    private double _avg;

    /**
     * keeps track of state for field: _avg
     */
    private boolean _has_avg;


      //----------------/
     //- Constructors -/
    //----------------/

    public MethodAverage() 
     {
        super();
    } //-- lib.xml.profiler_result.MethodAverage()


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
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return MethodAverage
     */
    public static lib.xml.profiler_result.MethodAverage unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.profiler_result.MethodAverage) Unmarshaller.unmarshal(lib.xml.profiler_result.MethodAverage.class, reader);
    } //-- lib.xml.profiler_result.MethodAverage unmarshal(java.io.Reader) 

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
