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

import java.util.Collections;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Metric.
 * 
 * @version $Revision$ $Date$
 */
public class Metric implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _unitList
     */
    private java.util.ArrayList _unitList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Metric() 
     {
        super();
        _unitList = new java.util.ArrayList();
    } //-- lib.xml.extractor_result.Metric()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addUnit
     * 
     * 
     * 
     * @param vUnit
     */
    public void addUnit(lib.xml.extractor_result.Unit vUnit)
        throws java.lang.IndexOutOfBoundsException
    {
        _unitList.add(vUnit);
    } //-- void addUnit(lib.xml.extractor_result.Unit) 

    /**
     * Method addUnit
     * 
     * 
     * 
     * @param index
     * @param vUnit
     */
    public void addUnit(int index, lib.xml.extractor_result.Unit vUnit)
        throws java.lang.IndexOutOfBoundsException
    {
        _unitList.add(index, vUnit);
    } //-- void addUnit(int, lib.xml.extractor_result.Unit) 

    /**
     * Method clearUnit
     * 
     */
    public void clearUnit()
    {
        _unitList.clear();
    } //-- void clearUnit() 

    /**
     * Method enumerateUnit
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateUnit()
    {
        return Collections.enumeration(_unitList);
    } //-- java.util.Enumeration enumerateUnit() 

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
     * Method getUnit
     * 
     * 
     * 
     * @param index
     * @return Unit
     */
    public lib.xml.extractor_result.Unit getUnit(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _unitList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (lib.xml.extractor_result.Unit) _unitList.get(index);
    } //-- lib.xml.extractor_result.Unit getUnit(int) 

    /**
     * Method getUnit
     * 
     * 
     * 
     * @return Unit
     */
    public lib.xml.extractor_result.Unit[] getUnit()
    {
        int size = _unitList.size();
        lib.xml.extractor_result.Unit[] mArray = new lib.xml.extractor_result.Unit[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (lib.xml.extractor_result.Unit) _unitList.get(index);
        }
        return mArray;
    } //-- lib.xml.extractor_result.Unit[] getUnit() 

    /**
     * Method getUnitCount
     * 
     * 
     * 
     * @return int
     */
    public int getUnitCount()
    {
        return _unitList.size();
    } //-- int getUnitCount() 

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
     * Method removeUnit
     * 
     * 
     * 
     * @param vUnit
     * @return boolean
     */
    public boolean removeUnit(lib.xml.extractor_result.Unit vUnit)
    {
        boolean removed = _unitList.remove(vUnit);
        return removed;
    } //-- boolean removeUnit(lib.xml.extractor_result.Unit) 

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
     * Method setUnit
     * 
     * 
     * 
     * @param index
     * @param vUnit
     */
    public void setUnit(int index, lib.xml.extractor_result.Unit vUnit)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _unitList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _unitList.set(index, vUnit);
    } //-- void setUnit(int, lib.xml.extractor_result.Unit) 

    /**
     * Method setUnit
     * 
     * 
     * 
     * @param unitArray
     */
    public void setUnit(lib.xml.extractor_result.Unit[] unitArray)
    {
        //-- copy array
        _unitList.clear();
        for (int i = 0; i < unitArray.length; i++) {
            _unitList.add(unitArray[i]);
        }
    } //-- void setUnit(lib.xml.extractor_result.Unit) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Metric
     */
    public static lib.xml.extractor_result.Metric unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.extractor_result.Metric) Unmarshaller.unmarshal(lib.xml.extractor_result.Metric.class, reader);
    } //-- lib.xml.extractor_result.Metric unmarshal(java.io.Reader) 

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
