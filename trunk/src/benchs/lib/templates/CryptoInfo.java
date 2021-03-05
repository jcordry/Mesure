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
 * This class represents a cryptographic object.
 */
public class CryptoInfo {
  public static final byte TYPE_CIPHER = (byte)1;
  public static final byte TYPE_SIGNATURE = (byte)2;
  public static final byte TYPE_DIGEST = (byte)3;
  public static final byte TYPE_RANDOM = (byte)4;	
	
  /** The type. */
  private byte type;
	
  /** The algorithm. */
  private byte algorithm;

  /** The mode. */
  private byte theMode;
  
  /** The key information. */
  private KeyInfo keyInfo;
  
  /** The offset where to find the input data. */
  private short inOffset;
  
  /** The length of the input data. */
  private short inLength;
  
  /** The offset where to store the output data. */
  private short outOffset;
  
  /** The length of the output data. */
  private short outLength;
  
  /** The 
  
  /**
   * Defines a new information structure.
   * 
   * @param type the type of cryptographic object
   * @param algorithm the algorithm.
   * @param theMode the mode.
   * @param keyType the key type.
   * @param keyLength the key length.
   * @param keyEncryption the key encryption.
   * @param inOffset the offset where to find the input data.
   * @param inLength the length of the input data.
   * @param outOffset the offset where are stored the output data.
   * @param outLength the length of the output data.
   */
  public CryptoInfo(byte type, byte algorithm, byte theMode, 
      byte keyType, short keyLength, boolean keyEncryption,
      short inOffset, short inLength, short outOffset, short outLength) {
	this.type = type;
	this.algorithm = algorithm;
	this.theMode = theMode;
	this.keyInfo = new KeyInfo(keyType,keyLength,keyEncryption);
	this.inOffset = inOffset;
	this.inLength = inLength;
	this.outOffset = outOffset;
	this.outLength = outLength;
  }
  
  /**
   * Defines a new empty information structure.
   */
  public CryptoInfo() {}

  /**
   * Reads the algorithm, the external access and the mode from input 
   * stream.
   *
   * @param reader the byte array reader to use.
   */
   public void setCryptoInfo(ByteArrayReader reader) {
	 type = reader.readByte();
     algorithm = reader.readByte();
     theMode = reader.readByte();
     inOffset = reader.readShort();
     inLength = reader.readShort();
     outOffset = reader.readShort();
     outLength = reader.readShort();
   }
  
   /**
    * Sets the key information to be used to build the <tt>Key</tt> object
    * to be passed to the <tt>init</tt> method.
    * 
    * @param keyInfo the key information.
    */
   public void setKeyInfo(KeyInfo keyInfo) {
     this.keyInfo = keyInfo;
   }
   
   /**
    * Writes information to output stream.
    * 
    * @param writer the byte array writer to use.
    */
   public void write(ByteArrayWriter writer) {
	 keyInfo.write(writer);
	 writer.writeByte(type);
     writer.writeByte(algorithm);
     writer.writeByte(theMode); 
     writer.writeShort(inOffset);
     writer.writeShort(inLength);
     writer.writeShort(outOffset);
     writer.writeShort(outLength);
   }
   
   /**
    * Returns the size of the cipher information.
    * 
    * @return the size in bytes of the key information.
    */
   public static short getSize() {
     return (short)(11 + KeyInfo.getSize());
   }
   
   /**
    * Sets the type of cryptographic object to be built.
    * 
    * @param type the type.
    */
   public void setType(byte type) {
     this.type = type;
   }
   
   /**
    * Returns the type of cryptographic object built.
    * 
    * @return the type.
    */
   public byte getType() {
     return type;
   }
   
   /**
    * Sets the algorithm to be passed to the <tt>getInstance</tt> 
    * method.
    * 
    * @param algorithm the algorithm.
    */
   public void setAlgorithm(byte algorithm) {
     this.algorithm = algorithm;
   }
   
   /**
    * Returns the algorithm passed to the <tt>getInstance</tt> method.
    * 
    * @return the algorithm.
    */
   public byte getAlgorithm() {
     return algorithm;
   }
   
   /**
    * Returns the mode to be passed to the <tt>init</tt> 
    * method.
    * 
    * @return the mode.
    */
   public byte getMode() {
     return theMode;
   }
   
   /**
    * Returns the key information to be used to build the <tt>Key</tt> object
    * to be passed to the <tt>init</tt> method.
    * 
    * @return the key information.
    */
   public KeyInfo getKeyInfo() {
     return keyInfo;
   }
   
   /**
    * Returns the offset where to find the input data.
    * 
    * @return the offset where to find the input data.
    */
   public short getInOffset() {
     return inOffset;
   }
   
   /**
    * Returns the length of the input data.
    * 
    * @return the length of the input data.
    */
   public short getInLength() {
     return inLength;
   }
   
   /**
    * Returns the offset where to store the output data.
    * 
    * @return the offset where to store the output data.
    */
   public short getOutOffset() {
     return outOffset;
   }
   
   /**
    * Returns the length of the output data.
    * 
    * @return the length of the output data.
    */
   public short getOutLength() {
     return outLength;
   }
}
