package benchs.lib.templates;

/*
 * Copyright (c) 2007 Mesure Project
 * 
 * This software is a computer program whose purpose is to measure 
 * the performances of Java Card platforms.
 *
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 * 
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 * 
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

/*
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/benchs/jcre/overhead/applet/ComTestApplet.java $
 * Created: 19 février 2007
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class represents a key.
 */
public class KeyInfo {
  /** The public part of an RSA CRT key pair. */
  public final static byte TYPE_RSA_CRT_PUBLIC = 9;	

  /** The key type. */
  private byte keyType;
  
  /** The key length. */
  private short keyLength;
  
  /** The key encryption. */
  private boolean keyEncryption;
  
  /**
   * Defines a new key information structure.
   * 
   * @param keyType the keyType.
   * @param keyLength the key length.
   * @param keyEncryption the key encryption.
   */
  public KeyInfo(byte keyType, short keyLength, boolean keyEncryption) {
	this.keyType = keyType;
	this.keyLength = keyLength;
	this.keyEncryption = keyEncryption;
  }
  
  /**
   * Defines a new empty key information structure.
   */
  public KeyInfo() {}

  /**
   * Reads key information from input stream.
   *
   * @param reader the byte array reader to use.
   * @return this <tt>KeyInfo</tt> object.
   */
   public KeyInfo read(ByteArrayReader reader) {
     keyType = reader.readByte();
     keyLength = reader.readShort();
     keyEncryption = (reader.readByte() == 1);
     return this;
   }
   
   /**
    * Writes key information to output stream.
    * 
    * @param writer the byte array writer to use.
    */
   public void write(ByteArrayWriter writer) {
     writer.writeByte(keyType);
     writer.writeShort(keyLength);
     writer.writeByte(keyEncryption ? (byte)1 : (byte)0);
   }
   
   /**
    * Returns the size of the key information.
    * 
    * @return the size in bytes of the key information.
    */
   public static short getSize() {
     return (short)4;
   }
   
   /**
    * Sets the key type to be passed to the <tt>KeyBuilder.buildKey</tt> 
    * method.
    * 
    * @param keyType the key type.
    */
   public void setKeyType(byte keyType) {
     this.keyType = keyType;
   }
   
   /**
    * Returns the key type to be passed to the <tt>KeyBuilder.buildKey</tt> 
    * method.
    * 
    * @return the key type.
    * @see KeyBuilder#buildKey(byte, short, boolean)
    */
   public byte getKeyType() {
     return keyType;
   }
   
   /**
    * Sets the key length to be passed to the <tt>KeyBuilder.buildKey</tt> 
    * method.
    * 
    * @param keyLength the key length.
    */
   public void setKeyLength(short keyLength) {
     this.keyLength = keyLength;
   }
   
   /**
    * Returns the key length to be passed to the <tt>KeyBuilder.buildKey</tt> 
    * method.
    * 
    * @return the key length.
    * @see KeyBuilder#buildKey(byte, short, boolean)
    */
   public short getKeyLength() {
     return keyLength;
   }
   
   /**
    * Sets the key encryption to be passed to the <tt>KeyBuilder.buildKey</tt> 
    * method.
    * 
    * @param keyEncryption the key encryption.
    */
   public void setKeyEncryption(boolean keyEncryption) {
     this.keyEncryption = keyEncryption;
   }
   
   /**
    * Returns the key encryption to be passed to the <tt>KeyBuilder.buildKey</tt> 
    * method.
    * 
    * @return the key encryption.
    * @see KeyBuilder#buildKey(byte, short, boolean)
    */
   public boolean getKeyEncryption() {
     return keyEncryption;
   }
}
