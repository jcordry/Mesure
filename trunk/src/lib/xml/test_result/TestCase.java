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
     * Field _data
     */
    private java.lang.String _data;

    /**
     * Field _apdu
     */
    private java.lang.String _apdu;

    /**
     * Field _benchedUnit
     */
    private java.lang.String _benchedUnit;

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
     * Field _valid
     */
    private boolean _valid;

    /**
     * keeps track of state for field: _valid
     */
    private boolean _has_valid;

    /**
     * Field _referenceName
     */
    private java.lang.String _referenceName;

    /**
     * Field _calibrationName
     */
    private java.lang.String _calibrationName;

    /**
     * Field _script
     */
    private java.lang.String _script;

    /**
     * Field _testResult
     */
    private lib.xml.test_result.TestResult _testResult;


      //----------------/
     //- Constructors -/
    //----------------/

    public TestCase() 
     {
        super();
    } //-- lib.xml.test_result.TestCase()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteValid
     * 
     */
    public void deleteValid()
    {
        this._has_valid= false;
    } //-- void deleteValid() 

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
     * Returns the value of field 'apdu'.
     * 
     * @return String
     * @return the value of field 'apdu'.
     */
    public java.lang.String getApdu()
    {
        return this._apdu;
    } //-- java.lang.String getApdu() 

    /**
     * Returns the value of field 'benchedUnit'.
     * 
     * @return String
     * @return the value of field 'benchedUnit'.
     */
    public java.lang.String getBenchedUnit()
    {
        return this._benchedUnit;
    } //-- java.lang.String getBenchedUnit() 

    /**
     * Returns the value of field 'calibrationName'.
     * 
     * @return String
     * @return the value of field 'calibrationName'.
     */
    public java.lang.String getCalibrationName()
    {
        return this._calibrationName;
    } //-- java.lang.String getCalibrationName() 

    /**
     * Returns the value of field 'data'.
     * 
     * @return String
     * @return the value of field 'data'.
     */
    public java.lang.String getData()
    {
        return this._data;
    } //-- java.lang.String getData() 

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
     * Returns the value of field 'referenceName'.
     * 
     * @return String
     * @return the value of field 'referenceName'.
     */
    public java.lang.String getReferenceName()
    {
        return this._referenceName;
    } //-- java.lang.String getReferenceName() 

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
     * Returns the value of field 'testResult'.
     * 
     * @return TestResult
     * @return the value of field 'testResult'.
     */
    public lib.xml.test_result.TestResult getTestResult()
    {
        return this._testResult;
    } //-- lib.xml.test_result.TestResult getTestResult() 

    /**
     * Returns the value of field 'valid'.
     * 
     * @return boolean
     * @return the value of field 'valid'.
     */
    public boolean getValid()
    {
        return this._valid;
    } //-- boolean getValid() 

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
     * Method hasValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasValid()
    {
        return this._has_valid;
    } //-- boolean hasValid() 

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
     * Sets the value of field 'apdu'.
     * 
     * @param apdu the value of field 'apdu'.
     */
    public void setApdu(java.lang.String apdu)
    {
        this._apdu = apdu;
    } //-- void setApdu(java.lang.String) 

    /**
     * Sets the value of field 'benchedUnit'.
     * 
     * @param benchedUnit the value of field 'benchedUnit'.
     */
    public void setBenchedUnit(java.lang.String benchedUnit)
    {
        this._benchedUnit = benchedUnit;
    } //-- void setBenchedUnit(java.lang.String) 

    /**
     * Sets the value of field 'calibrationName'.
     * 
     * @param calibrationName the value of field 'calibrationName'.
     */
    public void setCalibrationName(java.lang.String calibrationName)
    {
        this._calibrationName = calibrationName;
    } //-- void setCalibrationName(java.lang.String) 

    /**
     * Sets the value of field 'data'.
     * 
     * @param data the value of field 'data'.
     */
    public void setData(java.lang.String data)
    {
        this._data = data;
    } //-- void setData(java.lang.String) 

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
     * Sets the value of field 'referenceName'.
     * 
     * @param referenceName the value of field 'referenceName'.
     */
    public void setReferenceName(java.lang.String referenceName)
    {
        this._referenceName = referenceName;
    } //-- void setReferenceName(java.lang.String) 

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
     * Sets the value of field 'testResult'.
     * 
     * @param testResult the value of field 'testResult'.
     */
    public void setTestResult(lib.xml.test_result.TestResult testResult)
    {
        this._testResult = testResult;
    } //-- void setTestResult(lib.xml.test_result.TestResult) 

    /**
     * Sets the value of field 'valid'.
     * 
     * @param valid the value of field 'valid'.
     */
    public void setValid(boolean valid)
    {
        this._valid = valid;
        this._has_valid = true;
    } //-- void setValid(boolean) 

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
    public static lib.xml.test_result.TestCase unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.test_result.TestCase) Unmarshaller.unmarshal(lib.xml.test_result.TestCase.class, reader);
    } //-- lib.xml.test_result.TestCase unmarshal(java.io.Reader) 

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
