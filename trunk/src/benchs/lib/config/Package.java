/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package benchs.lib.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Collections;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Package.
 * 
 * @version $Revision: 324 $ $Date: 2007-07-26 10:24:01 +0200 (jeu., 26 juil. 2007) $
 */
public class Package implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _capfile
     */
    private java.lang.String _capfile = "0";

    /**
     * Field _appletList
     */
    private java.util.ArrayList _appletList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Package() 
     {
        super();
        setCapfile("0");
        _appletList = new java.util.ArrayList();
    } //-- benchs.lib.config.Package()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addApplet
     * 
     * 
     * 
     * @param vApplet
     */
    public void addApplet(benchs.lib.config.Applet vApplet)
        throws java.lang.IndexOutOfBoundsException
    {
        _appletList.add(vApplet);
    } //-- void addApplet(benchs.lib.config.Applet) 

    /**
     * Method addApplet
     * 
     * 
     * 
     * @param index
     * @param vApplet
     */
    public void addApplet(int index, benchs.lib.config.Applet vApplet)
        throws java.lang.IndexOutOfBoundsException
    {
        _appletList.add(index, vApplet);
    } //-- void addApplet(int, benchs.lib.config.Applet) 

    /**
     * Method clearApplet
     * 
     */
    public void clearApplet()
    {
        _appletList.clear();
    } //-- void clearApplet() 

    /**
     * Method enumerateApplet
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateApplet()
    {
        return Collections.enumeration(_appletList);
    } //-- java.util.Enumeration enumerateApplet() 

    /**
     * Method getApplet
     * 
     * 
     * 
     * @param index
     * @return Applet
     */
    public benchs.lib.config.Applet getApplet(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _appletList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (benchs.lib.config.Applet) _appletList.get(index);
    } //-- benchs.lib.config.Applet getApplet(int) 

    /**
     * Method getApplet
     * 
     * 
     * 
     * @return Applet
     */
    public benchs.lib.config.Applet[] getApplet()
    {
        int size = _appletList.size();
        benchs.lib.config.Applet[] mArray = new benchs.lib.config.Applet[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (benchs.lib.config.Applet) _appletList.get(index);
        }
        return mArray;
    } //-- benchs.lib.config.Applet[] getApplet() 

    /**
     * Method getAppletCount
     * 
     * 
     * 
     * @return int
     */
    public int getAppletCount()
    {
        return _appletList.size();
    } //-- int getAppletCount() 

    /**
     * Returns the value of field 'capfile'.
     * 
     * @return String
     * @return the value of field 'capfile'.
     */
    public java.lang.String getCapfile()
    {
        return this._capfile;
    } //-- java.lang.String getCapfile() 

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
     * Method removeApplet
     * 
     * 
     * 
     * @param vApplet
     * @return boolean
     */
    public boolean removeApplet(benchs.lib.config.Applet vApplet)
    {
        boolean removed = _appletList.remove(vApplet);
        return removed;
    } //-- boolean removeApplet(benchs.lib.config.Applet) 

    /**
     * Method setApplet
     * 
     * 
     * 
     * @param index
     * @param vApplet
     */
    public void setApplet(int index, benchs.lib.config.Applet vApplet)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _appletList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _appletList.set(index, vApplet);
    } //-- void setApplet(int, benchs.lib.config.Applet) 

    /**
     * Method setApplet
     * 
     * 
     * 
     * @param appletArray
     */
    public void setApplet(benchs.lib.config.Applet[] appletArray)
    {
        //-- copy array
        _appletList.clear();
        for (int i = 0; i < appletArray.length; i++) {
            _appletList.add(appletArray[i]);
        }
    } //-- void setApplet(benchs.lib.config.Applet) 

    /**
     * Sets the value of field 'capfile'.
     * 
     * @param capfile the value of field 'capfile'.
     */
    public void setCapfile(java.lang.String capfile)
    {
        this._capfile = capfile;
    } //-- void setCapfile(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Package
     */
    public static benchs.lib.config.Package unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (benchs.lib.config.Package) Unmarshaller.unmarshal(benchs.lib.config.Package.class, reader);
    } //-- benchs.lib.config.Package unmarshal(java.io.Reader) 

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
