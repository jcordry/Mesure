/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package lib.loader.config;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class MutualAuthenticate.
 * 
 * @version $Revision$ $Date$
 */
public class MutualAuthenticate implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _div
     */
    private java.lang.String _div = "none";

    /**
     * Field _scp
     */
    private java.lang.String _scp = "1";

    /**
     * Field _secLevel
     */
    private byte _secLevel = 1;

    /**
     * keeps track of state for field: _secLevel
     */
    private boolean _has_secLevel;

    /**
     * Field _kenc
     */
    private java.lang.String _kenc = "0x40:0x41:0x42:0x43:0x44:0x45:0x46:0x47:0x48:0x49:0x4A:0x4B:0x4C:0x4D:0x4E:0x4F";

    /**
     * Field _kmac
     */
    private java.lang.String _kmac = "0x40:0x41:0x42:0x43:0x44:0x45:0x46:0x47:0x48:0x49:0x4A:0x4B:0x4C:0x4D:0x4E:0x4F";

    /**
     * Field _kmc
     */
    private java.lang.String _kmc;

    /**
     * Field _keyVersion
     */
    private short _keyVersion = 0;

    /**
     * keeps track of state for field: _keyVersion
     */
    private boolean _has_keyVersion;

    /**
     * Field _keyIndex
     */
    private short _keyIndex = 0;

    /**
     * keeps track of state for field: _keyIndex
     */
    private boolean _has_keyIndex;

    /**
     * Field _hostChallenge
     */
    private java.lang.String _hostChallenge;


      //----------------/
     //- Constructors -/
    //----------------/

    public MutualAuthenticate() 
     {
        super();
        setDiv("none");
        setScp("1");
        setKenc("0x40:0x41:0x42:0x43:0x44:0x45:0x46:0x47:0x48:0x49:0x4A:0x4B:0x4C:0x4D:0x4E:0x4F");
        setKmac("0x40:0x41:0x42:0x43:0x44:0x45:0x46:0x47:0x48:0x49:0x4A:0x4B:0x4C:0x4D:0x4E:0x4F");
    } //-- lib.loader.config.MutualAuthenticate()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteKeyIndex
     * 
     */
    public void deleteKeyIndex()
    {
        this._has_keyIndex= false;
    } //-- void deleteKeyIndex() 

    /**
     * Method deleteKeyVersion
     * 
     */
    public void deleteKeyVersion()
    {
        this._has_keyVersion= false;
    } //-- void deleteKeyVersion() 

    /**
     * Method deleteSecLevel
     * 
     */
    public void deleteSecLevel()
    {
        this._has_secLevel= false;
    } //-- void deleteSecLevel() 

    /**
     * Returns the value of field 'div'.
     * 
     * @return String
     * @return the value of field 'div'.
     */
    public java.lang.String getDiv()
    {
        return this._div;
    } //-- java.lang.String getDiv() 

    /**
     * Returns the value of field 'hostChallenge'.
     * 
     * @return String
     * @return the value of field 'hostChallenge'.
     */
    public java.lang.String getHostChallenge()
    {
        return this._hostChallenge;
    } //-- java.lang.String getHostChallenge() 

    /**
     * Returns the value of field 'kenc'.
     * 
     * @return String
     * @return the value of field 'kenc'.
     */
    public java.lang.String getKenc()
    {
        return this._kenc;
    } //-- java.lang.String getKenc() 

    /**
     * Returns the value of field 'keyIndex'.
     * 
     * @return short
     * @return the value of field 'keyIndex'.
     */
    public short getKeyIndex()
    {
        return this._keyIndex;
    } //-- short getKeyIndex() 

    /**
     * Returns the value of field 'keyVersion'.
     * 
     * @return short
     * @return the value of field 'keyVersion'.
     */
    public short getKeyVersion()
    {
        return this._keyVersion;
    } //-- short getKeyVersion() 

    /**
     * Returns the value of field 'kmac'.
     * 
     * @return String
     * @return the value of field 'kmac'.
     */
    public java.lang.String getKmac()
    {
        return this._kmac;
    } //-- java.lang.String getKmac() 

    /**
     * Returns the value of field 'kmc'.
     * 
     * @return String
     * @return the value of field 'kmc'.
     */
    public java.lang.String getKmc()
    {
        return this._kmc;
    } //-- java.lang.String getKmc() 

    /**
     * Returns the value of field 'scp'.
     * 
     * @return String
     * @return the value of field 'scp'.
     */
    public java.lang.String getScp()
    {
        return this._scp;
    } //-- java.lang.String getScp() 

    /**
     * Returns the value of field 'secLevel'.
     * 
     * @return byte
     * @return the value of field 'secLevel'.
     */
    public byte getSecLevel()
    {
        return this._secLevel;
    } //-- byte getSecLevel() 

    /**
     * Method hasKeyIndex
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasKeyIndex()
    {
        return this._has_keyIndex;
    } //-- boolean hasKeyIndex() 

    /**
     * Method hasKeyVersion
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasKeyVersion()
    {
        return this._has_keyVersion;
    } //-- boolean hasKeyVersion() 

    /**
     * Method hasSecLevel
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecLevel()
    {
        return this._has_secLevel;
    } //-- boolean hasSecLevel() 

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
     * Sets the value of field 'div'.
     * 
     * @param div the value of field 'div'.
     */
    public void setDiv(java.lang.String div)
    {
        this._div = div;
    } //-- void setDiv(java.lang.String) 

    /**
     * Sets the value of field 'hostChallenge'.
     * 
     * @param hostChallenge the value of field 'hostChallenge'.
     */
    public void setHostChallenge(java.lang.String hostChallenge)
    {
        this._hostChallenge = hostChallenge;
    } //-- void setHostChallenge(java.lang.String) 

    /**
     * Sets the value of field 'kenc'.
     * 
     * @param kenc the value of field 'kenc'.
     */
    public void setKenc(java.lang.String kenc)
    {
        this._kenc = kenc;
    } //-- void setKenc(java.lang.String) 

    /**
     * Sets the value of field 'keyIndex'.
     * 
     * @param keyIndex the value of field 'keyIndex'.
     */
    public void setKeyIndex(short keyIndex)
    {
        this._keyIndex = keyIndex;
        this._has_keyIndex = true;
    } //-- void setKeyIndex(short) 

    /**
     * Sets the value of field 'keyVersion'.
     * 
     * @param keyVersion the value of field 'keyVersion'.
     */
    public void setKeyVersion(short keyVersion)
    {
        this._keyVersion = keyVersion;
        this._has_keyVersion = true;
    } //-- void setKeyVersion(short) 

    /**
     * Sets the value of field 'kmac'.
     * 
     * @param kmac the value of field 'kmac'.
     */
    public void setKmac(java.lang.String kmac)
    {
        this._kmac = kmac;
    } //-- void setKmac(java.lang.String) 

    /**
     * Sets the value of field 'kmc'.
     * 
     * @param kmc the value of field 'kmc'.
     */
    public void setKmc(java.lang.String kmc)
    {
        this._kmc = kmc;
    } //-- void setKmc(java.lang.String) 

    /**
     * Sets the value of field 'scp'.
     * 
     * @param scp the value of field 'scp'.
     */
    public void setScp(java.lang.String scp)
    {
        this._scp = scp;
    } //-- void setScp(java.lang.String) 

    /**
     * Sets the value of field 'secLevel'.
     * 
     * @param secLevel the value of field 'secLevel'.
     */
    public void setSecLevel(byte secLevel)
    {
        this._secLevel = secLevel;
        this._has_secLevel = true;
    } //-- void setSecLevel(byte) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return MutualAuthenticate
     */
    public static lib.loader.config.MutualAuthenticate unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (lib.loader.config.MutualAuthenticate) Unmarshaller.unmarshal(lib.loader.config.MutualAuthenticate.class, reader);
    } //-- lib.loader.config.MutualAuthenticate unmarshal(java.io.Reader) 

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
