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
 * Class TestCase.
 * 
 * @version $Revision$ $Date$
 */
public class TestCase implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _x
     */
    private int _x;

    /**
     * keeps track of state for field: _x
     */
    private boolean _has_x;

    /**
     * Field _y
     */
    private int _y;

    /**
     * keeps track of state for field: _y
     */
    private boolean _has_y;

    /**
     * Field _minLoop
     */
    private int _minLoop;

    /**
     * keeps track of state for field: _minLoop
     */
    private boolean _has_minLoop;

    /**
     * Field _maxLoop
     */
    private int _maxLoop;

    /**
     * keeps track of state for field: _maxLoop
     */
    private boolean _has_maxLoop;

    /**
     * Field _startLoop
     */
    private int _startLoop = _x;

    /**
     * keeps track of state for field: _startLoop
     */
    private boolean _has_startLoop;

    /**
     * Field _precision
     */
    private double _precision = 2;

    /**
     * keeps track of state for field: _precision
     */
    private boolean _has_precision;

    /**
     * Field _calibrateMetric
     */
    private tools.manager.config.CalibrateMetric _calibrateMetric;


      //----------------/
     //- Constructors -/
    //----------------/

    public TestCase() 
     {
        super();
    } //-- tools.manager.config.TestCase()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteMaxLoop
     * 
     */
    public void deleteMaxLoop()
    {
        this._has_maxLoop= false;
    } //-- void deleteMaxLoop() 

    /**
     * Method deleteMinLoop
     * 
     */
    public void deleteMinLoop()
    {
        this._has_minLoop= false;
    } //-- void deleteMinLoop() 

    /**
     * Method deletePrecision
     * 
     */
    public void deletePrecision()
    {
        this._has_precision= false;
    } //-- void deletePrecision() 

    /**
     * Method deleteStartLoop
     * 
     */
    public void deleteStartLoop()
    {
        this._has_startLoop= false;
    } //-- void deleteStartLoop() 

    /**
     * Method deleteX
     * 
     */
    public void deleteX()
    {
        this._has_x= false;
    } //-- void deleteX() 

    /**
     * Method deleteY
     * 
     */
    public void deleteY()
    {
        this._has_y= false;
    } //-- void deleteY() 

    /**
     * Returns the value of field 'calibrateMetric'.
     * 
     * @return CalibrateMetric
     * @return the value of field 'calibrateMetric'.
     */
    public tools.manager.config.CalibrateMetric getCalibrateMetric()
    {
        return this._calibrateMetric;
    } //-- tools.manager.config.CalibrateMetric getCalibrateMetric() 

    /**
     * Returns the value of field 'maxLoop'.
     * 
     * @return int
     * @return the value of field 'maxLoop'.
     */
    public int getMaxLoop()
    {
        return this._maxLoop;
    } //-- int getMaxLoop() 

    /**
     * Returns the value of field 'minLoop'.
     * 
     * @return int
     * @return the value of field 'minLoop'.
     */
    public int getMinLoop()
    {
        return this._minLoop;
    } //-- int getMinLoop() 

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
     * Returns the value of field 'precision'.
     * 
     * @return double
     * @return the value of field 'precision'.
     */
    public double getPrecision()
    {
        return this._precision;
    } //-- double getPrecision() 

    /**
     * Returns the value of field 'startLoop'.
     * 
     * @return int
     * @return the value of field 'startLoop'.
     */
    public int getStartLoop()
    {
        return this._startLoop;
    } //-- int getStartLoop() 

    /**
     * Returns the value of field 'x'.
     * 
     * @return int
     * @return the value of field 'x'.
     */
    public int getX()
    {
        return this._x;
    } //-- int getX() 

    /**
     * Returns the value of field 'y'.
     * 
     * @return int
     * @return the value of field 'y'.
     */
    public int getY()
    {
        return this._y;
    } //-- int getY() 

    /**
     * Method hasMaxLoop
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxLoop()
    {
        return this._has_maxLoop;
    } //-- boolean hasMaxLoop() 

    /**
     * Method hasMinLoop
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMinLoop()
    {
        return this._has_minLoop;
    } //-- boolean hasMinLoop() 

    /**
     * Method hasPrecision
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasPrecision()
    {
        return this._has_precision;
    } //-- boolean hasPrecision() 

    /**
     * Method hasStartLoop
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasStartLoop()
    {
        return this._has_startLoop;
    } //-- boolean hasStartLoop() 

    /**
     * Method hasX
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasX()
    {
        return this._has_x;
    } //-- boolean hasX() 

    /**
     * Method hasY
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasY()
    {
        return this._has_y;
    } //-- boolean hasY() 

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
     * Sets the value of field 'calibrateMetric'.
     * 
     * @param calibrateMetric the value of field 'calibrateMetric'.
     */
    public void setCalibrateMetric(tools.manager.config.CalibrateMetric calibrateMetric)
    {
        this._calibrateMetric = calibrateMetric;
    } //-- void setCalibrateMetric(tools.manager.config.CalibrateMetric) 

    /**
     * Sets the value of field 'maxLoop'.
     * 
     * @param maxLoop the value of field 'maxLoop'.
     */
    public void setMaxLoop(int maxLoop)
    {
        this._maxLoop = maxLoop;
        this._has_maxLoop = true;
    } //-- void setMaxLoop(int) 

    /**
     * Sets the value of field 'minLoop'.
     * 
     * @param minLoop the value of field 'minLoop'.
     */
    public void setMinLoop(int minLoop)
    {
        this._minLoop = minLoop;
        this._has_minLoop = true;
    } //-- void setMinLoop(int) 

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
     * Sets the value of field 'precision'.
     * 
     * @param precision the value of field 'precision'.
     */
    public void setPrecision(double precision)
    {
        this._precision = precision;
        this._has_precision = true;
    } //-- void setPrecision(double) 

    /**
     * Sets the value of field 'startLoop'.
     * 
     * @param startLoop the value of field 'startLoop'.
     */
    public void setStartLoop(int startLoop)
    {
        this._startLoop = startLoop;
        this._has_startLoop = true;
    } //-- void setStartLoop(int) 

    /**
     * Sets the value of field 'x'.
     * 
     * @param x the value of field 'x'.
     */
    public void setX(int x)
    {
        this._x = x;
        this._has_x = true;
    } //-- void setX(int) 

    /**
     * Sets the value of field 'y'.
     * 
     * @param y the value of field 'y'.
     */
    public void setY(int y)
    {
        this._y = y;
        this._has_y = true;
    } //-- void setY(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return TestCase
     */
    public static tools.manager.config.TestCase unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (tools.manager.config.TestCase) Unmarshaller.unmarshal(tools.manager.config.TestCase.class, reader);
    } //-- tools.manager.config.TestCase unmarshal(java.io.Reader) 

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
