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

import java.util.Collections;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class TestResult.
 * 
 * @version $Revision$ $Date$
 */
public class TestResult implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _message
     */
    private java.lang.String _message;

    /**
     * Field _status
     */
    private int _status;

    /**
     * keeps track of state for field: _status
     */
    private boolean _has_status;

    /**
     * Field _avg
     */
    private double _avg;

    /**
     * keeps track of state for field: _avg
     */
    private boolean _has_avg;

    /**
     * Field _stdError
     */
    private double _stdError;

    /**
     * keeps track of state for field: _stdError
     */
    private boolean _has_stdError;

    /**
     * Field _timeMeasureList
     */
    private java.util.ArrayList _timeMeasureList;


      //----------------/
     //- Constructors -/
    //----------------/

    public TestResult() 
     {
        super();
        _timeMeasureList = new java.util.ArrayList();
    } //-- lib.xml.test_result.TestResult()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addTimeMeasure
     * 
     * 
     * 
     * @param vTimeMeasure
     */
    public void addTimeMeasure(lib.xml.test_result.TimeMeasure vTimeMeasure)
        throws java.lang.IndexOutOfBoundsException
    {
        _timeMeasureList.add(vTimeMeasure);
    } //-- void addTimeMeasure(lib.xml.test_result.TimeMeasure) 

    /**
     * Method addTimeMeasure
     * 
     * 
     * 
     * @param index
     * @param vTimeMeasure
     */
    public void addTimeMeasure(int index, lib.xml.test_result.TimeMeasure vTimeMeasure)
        throws java.lang.IndexOutOfBoundsException
    {
        _timeMeasureList.add(index, vTimeMeasure);
    } //-- void addTimeMeasure(int, lib.xml.test_result.TimeMeasure) 

    /**
     * Method clearTimeMeasure
     * 
     */
    public void clearTimeMeasure()
    {
        _timeMeasureList.clear();
    } //-- void clearTimeMeasure() 

    /**
     * Method deleteAvg
     * 
     */
    public void deleteAvg()
    {
        this._has_avg= false;
    } //-- void deleteAvg() 

    /**
     * Method deleteStatus
     * 
     */
    public void deleteStatus()
    {
        this._has_status= false;
    } //-- void deleteStatus() 

    /**
     * Method deleteStdError
     * 
     */
    public void deleteStdError()
    {
        this._has_stdError= false;
    } //-- void deleteStdError() 

    /**
     * Method enumerateTimeMeasure
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateTimeMeasure()
    {
        return Collections.enumeration(_timeMeasureList);
    } //-- java.util.Enumeration enumerateTimeMeasure() 

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
     * Returns the value of field 'message'.
     * 
     * @return String
     * @return the value of field 'message'.
     */
    public java.lang.String getMessage()
    {
        return this._message;
    } //-- java.lang.String getMessage() 

    /**
     * Returns the value of field 'status'.
     * 
     * @return int
     * @return the value of field 'status'.
     */
    public int getStatus()
    {
        return this._status;
    } //-- int getStatus() 

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
     * Method getTimeMeasure
     * 
     * 
     * 
     * @param index
     * @return TimeMeasure
     */
    public lib.xml.test_result.TimeMeasure getTimeMeasure(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _timeMeasureList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (lib.xml.test_result.TimeMeasure) _timeMeasureList.get(index);
    } //-- lib.xml.test_result.TimeMeasure getTimeMeasure(int) 

    /**
     * Method getTimeMeasure
     * 
     * 
     * 
     * @return TimeMeasure
     */
    public lib.xml.test_result.TimeMeasure[] getTimeMeasure()
    {
        int size = _timeMeasureList.size();
        lib.xml.test_result.TimeMeasure[] mArray = new lib.xml.test_result.TimeMeasure[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (lib.xml.test_result.TimeMeasure) _timeMeasureList.get(index);
        }
        return mArray;
    } //-- lib.xml.test_result.TimeMeasure[] getTimeMeasure() 

    /**
     * Method getTimeMeasureCount
     * 
     * 
     * 
     * @return int
     */
    public int getTimeMeasureCount()
    {
        return _timeMeasureList.size();
    } //-- int getTimeMeasureCount() 

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
     * Method hasStatus
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasStatus()
    {
        return this._has_status;
    } //-- boolean hasStatus() 

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
     * Method removeTimeMeasure
     * 
     * 
     * 
     * @param vTimeMeasure
     * @return boolean
     */
    public boolean removeTimeMeasure(lib.xml.test_result.TimeMeasure vTimeMeasure)
    {
        boolean removed = _timeMeasureList.remove(vTimeMeasure);
        return removed;
    } //-- boolean removeTimeMeasure(lib.xml.test_result.TimeMeasure) 

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
     * Sets the value of field 'message'.
     * 
     * @param message the value of field 'message'.
     */
    public void setMessage(java.lang.String message)
    {
        this._message = message;
    } //-- void setMessage(java.lang.String) 

    /**
     * Sets the value of field 'status'.
     * 
     * @param status the value of field 'status'.
     */
    public void setStatus(int status)
    {
        this._status = status;
        this._has_status = true;
    } //-- void setStatus(int) 

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
     * Method setTimeMeasure
     * 
     * 
     * 
     * @param index
     * @param vTimeMeasure
     */
    public void setTimeMeasure(int index, lib.xml.test_result.TimeMeasure vTimeMeasure)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _timeMeasureList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _timeMeasureList.set(index, vTimeMeasure);
    } //-- void setTimeMeasure(int, lib.xml.test_result.TimeMeasure) 

    /**
     * Method setTimeMeasure
     * 
     * 
     * 
     * @param timeMeasureArray
     */
    public void setTimeMeasure(lib.xml.test_result.TimeMeasure[] timeMeasureArray)
    {
        //-- copy array
        _timeMeasureList.clear();
        for (int i = 0; i < timeMeasureArray.length; i++) {
            _timeMeasureList.add(timeMeasureArray[i]);
        }
    } //-- void setTimeMeasure(lib.xml.test_result.TimeMeasure) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return TestResult
     */
    public static lib.xml.test_result.TestResult unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.test_result.TestResult) Unmarshaller.unmarshal(lib.xml.test_result.TestResult.class, reader);
    } //-- lib.xml.test_result.TestResult unmarshal(java.io.Reader) 

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
