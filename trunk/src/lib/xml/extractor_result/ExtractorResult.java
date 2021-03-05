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
 * Class ExtractorResult.
 * 
 * @version $Revision$ $Date$
 */
public class ExtractorResult implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _metricList
     */
    private java.util.ArrayList _metricList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ExtractorResult() 
     {
        super();
        _metricList = new java.util.ArrayList();
    } //-- lib.xml.extractor_result.ExtractorResult()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addMetric
     * 
     * 
     * 
     * @param vMetric
     */
    public void addMetric(lib.xml.extractor_result.Metric vMetric)
        throws java.lang.IndexOutOfBoundsException
    {
        _metricList.add(vMetric);
    } //-- void addMetric(lib.xml.extractor_result.Metric) 

    /**
     * Method addMetric
     * 
     * 
     * 
     * @param index
     * @param vMetric
     */
    public void addMetric(int index, lib.xml.extractor_result.Metric vMetric)
        throws java.lang.IndexOutOfBoundsException
    {
        _metricList.add(index, vMetric);
    } //-- void addMetric(int, lib.xml.extractor_result.Metric) 

    /**
     * Method clearMetric
     * 
     */
    public void clearMetric()
    {
        _metricList.clear();
    } //-- void clearMetric() 

    /**
     * Method enumerateMetric
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateMetric()
    {
        return Collections.enumeration(_metricList);
    } //-- java.util.Enumeration enumerateMetric() 

    /**
     * Method getMetric
     * 
     * 
     * 
     * @param index
     * @return Metric
     */
    public lib.xml.extractor_result.Metric getMetric(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _metricList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (lib.xml.extractor_result.Metric) _metricList.get(index);
    } //-- lib.xml.extractor_result.Metric getMetric(int) 

    /**
     * Method getMetric
     * 
     * 
     * 
     * @return Metric
     */
    public lib.xml.extractor_result.Metric[] getMetric()
    {
        int size = _metricList.size();
        lib.xml.extractor_result.Metric[] mArray = new lib.xml.extractor_result.Metric[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (lib.xml.extractor_result.Metric) _metricList.get(index);
        }
        return mArray;
    } //-- lib.xml.extractor_result.Metric[] getMetric() 

    /**
     * Method getMetricCount
     * 
     * 
     * 
     * @return int
     */
    public int getMetricCount()
    {
        return _metricList.size();
    } //-- int getMetricCount() 

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
     * Method removeMetric
     * 
     * 
     * 
     * @param vMetric
     * @return boolean
     */
    public boolean removeMetric(lib.xml.extractor_result.Metric vMetric)
    {
        boolean removed = _metricList.remove(vMetric);
        return removed;
    } //-- boolean removeMetric(lib.xml.extractor_result.Metric) 

    /**
     * Method setMetric
     * 
     * 
     * 
     * @param index
     * @param vMetric
     */
    public void setMetric(int index, lib.xml.extractor_result.Metric vMetric)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _metricList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _metricList.set(index, vMetric);
    } //-- void setMetric(int, lib.xml.extractor_result.Metric) 

    /**
     * Method setMetric
     * 
     * 
     * 
     * @param metricArray
     */
    public void setMetric(lib.xml.extractor_result.Metric[] metricArray)
    {
        //-- copy array
        _metricList.clear();
        for (int i = 0; i < metricArray.length; i++) {
            _metricList.add(metricArray[i]);
        }
    } //-- void setMetric(lib.xml.extractor_result.Metric) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ExtractorResult
     */
    public static lib.xml.extractor_result.ExtractorResult unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.xml.extractor_result.ExtractorResult) Unmarshaller.unmarshal(lib.xml.extractor_result.ExtractorResult.class, reader);
    } //-- lib.xml.extractor_result.ExtractorResult unmarshal(java.io.Reader) 

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
