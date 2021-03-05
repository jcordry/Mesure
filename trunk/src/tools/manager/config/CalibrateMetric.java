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
 * Class CalibrateMetric.
 * 
 * @version $Revision$ $Date$
 */
public class CalibrateMetric implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _xmin
     */
    private int _xmin = 16;

    /**
     * keeps track of state for field: _xmin
     */
    private boolean _has_xmin;

    /**
     * Field _ymin
     */
    private int _ymin = 30;

    /**
     * keeps track of state for field: _ymin
     */
    private boolean _has_ymin;

    /**
     * Field _tmin
     */
    private double _tmin = 10000000;

    /**
     * keeps track of state for field: _tmin
     */
    private boolean _has_tmin;

    /**
     * Field _a
     */
    private double _a = 0.0;

    /**
     * keeps track of state for field: _a
     */
    private boolean _has_a;

    /**
     * Field _b
     */
    private double _b = 0.0;

    /**
     * keeps track of state for field: _b
     */
    private boolean _has_b;


      //----------------/
     //- Constructors -/
    //----------------/

    public CalibrateMetric() 
     {
        super();
    } //-- tools.manager.config.CalibrateMetric()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteA
     * 
     */
    public void deleteA()
    {
        this._has_a= false;
    } //-- void deleteA() 

    /**
     * Method deleteB
     * 
     */
    public void deleteB()
    {
        this._has_b= false;
    } //-- void deleteB() 

    /**
     * Method deleteTmin
     * 
     */
    public void deleteTmin()
    {
        this._has_tmin= false;
    } //-- void deleteTmin() 

    /**
     * Method deleteXmin
     * 
     */
    public void deleteXmin()
    {
        this._has_xmin= false;
    } //-- void deleteXmin() 

    /**
     * Method deleteYmin
     * 
     */
    public void deleteYmin()
    {
        this._has_ymin= false;
    } //-- void deleteYmin() 

    /**
     * Returns the value of field 'a'.
     * 
     * @return double
     * @return the value of field 'a'.
     */
    public double getA()
    {
        return this._a;
    } //-- double getA() 

    /**
     * Returns the value of field 'b'.
     * 
     * @return double
     * @return the value of field 'b'.
     */
    public double getB()
    {
        return this._b;
    } //-- double getB() 

    /**
     * Returns the value of field 'tmin'.
     * 
     * @return double
     * @return the value of field 'tmin'.
     */
    public double getTmin()
    {
        return this._tmin;
    } //-- double getTmin() 

    /**
     * Returns the value of field 'xmin'.
     * 
     * @return int
     * @return the value of field 'xmin'.
     */
    public int getXmin()
    {
        return this._xmin;
    } //-- int getXmin() 

    /**
     * Returns the value of field 'ymin'.
     * 
     * @return int
     * @return the value of field 'ymin'.
     */
    public int getYmin()
    {
        return this._ymin;
    } //-- int getYmin() 

    /**
     * Method hasA
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasA()
    {
        return this._has_a;
    } //-- boolean hasA() 

    /**
     * Method hasB
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasB()
    {
        return this._has_b;
    } //-- boolean hasB() 

    /**
     * Method hasTmin
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasTmin()
    {
        return this._has_tmin;
    } //-- boolean hasTmin() 

    /**
     * Method hasXmin
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasXmin()
    {
        return this._has_xmin;
    } //-- boolean hasXmin() 

    /**
     * Method hasYmin
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasYmin()
    {
        return this._has_ymin;
    } //-- boolean hasYmin() 

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
     * Sets the value of field 'a'.
     * 
     * @param a the value of field 'a'.
     */
    public void setA(double a)
    {
        this._a = a;
        this._has_a = true;
    } //-- void setA(double) 

    /**
     * Sets the value of field 'b'.
     * 
     * @param b the value of field 'b'.
     */
    public void setB(double b)
    {
        this._b = b;
        this._has_b = true;
    } //-- void setB(double) 

    /**
     * Sets the value of field 'tmin'.
     * 
     * @param tmin the value of field 'tmin'.
     */
    public void setTmin(double tmin)
    {
        this._tmin = tmin;
        this._has_tmin = true;
    } //-- void setTmin(double) 

    /**
     * Sets the value of field 'xmin'.
     * 
     * @param xmin the value of field 'xmin'.
     */
    public void setXmin(int xmin)
    {
        this._xmin = xmin;
        this._has_xmin = true;
    } //-- void setXmin(int) 

    /**
     * Sets the value of field 'ymin'.
     * 
     * @param ymin the value of field 'ymin'.
     */
    public void setYmin(int ymin)
    {
        this._ymin = ymin;
        this._has_ymin = true;
    } //-- void setYmin(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return CalibrateMetric
     */
    public static tools.manager.config.CalibrateMetric unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (tools.manager.config.CalibrateMetric) Unmarshaller.unmarshal(tools.manager.config.CalibrateMetric.class, reader);
    } //-- tools.manager.config.CalibrateMetric unmarshal(java.io.Reader) 

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
