package benchs.jcapi.javacard_framework.ownerpin;

import javacard.framework.PINException;
import benchs.lib.templates.ByteArrayReader;
import benchs.lib.templates.ByteArrayWriter;

/*
 * Copyright (c) 2006 Mesure Project
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
 * Created: 11 octobre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class represents a Personal Identification Number.
 */
public class PINInfo {

  /** The PIN try limit. */
  private byte tryLimit;
  
  /** The maximum PIN size. */
  private byte maxSize;
  
  /** The reference PIN. */
  private byte[] reference;
  
  /** The presented PIN. */
  private byte[] presented;
  
  /** The PIN remaining tries.*/
  private byte remainingTries;
  
  /** A 4-byte reference PIN. */
  private byte[] reference4;
  
  /** A 8-byte reference PIN. */
  private byte[] reference8;
  
  /** A 4-byte presented PIN. */
  private byte[] presented4;
  
  /** A 8-byte presented PIN. */
  private byte[] presented8;
  
  /**
   * Defines a new PIN information structure.
   * 
   * @param tryLimit the PIN try limit.
   * @param maxSize the maximum PIN size.
   * @param reference the reference PIN.
   * @param presented the presented PIN.
   * @param remainingTries the PIN remaining tries.
   * @throw <tt>PINException</tt> exception if <tt>tryLimit < 1</tt>,
   * or if <tt>maxSize < 1</tt>, or if <tt>reference.length > maxSize</tt>.
   */
  public PINInfo(byte tryLimit, byte maxSize, 
      byte[] reference, byte[] presented, byte remainingTries) {
    if (tryLimit < 1)
      PINException.throwIt(PINException.ILLEGAL_VALUE);
	this.tryLimit = tryLimit;
	if (maxSize < 1)
	  PINException.throwIt(PINException.ILLEGAL_VALUE);
    this.maxSize = maxSize;
    if (reference == null)
      this.reference = new byte[0];
    else {
      if (reference.length > maxSize)
        PINException.throwIt(PINException.ILLEGAL_VALUE);
      this.reference = reference;
    }
    if (presented == null)
      this.presented = new byte[0];
    else
      this.presented = presented;
    this.remainingTries = remainingTries;
  }
  
  /**
   * Defines a new empty PIN information structure.
   */
  public PINInfo() {
    reference4 = new byte[4];
    reference8 = new byte[8];
    presented4 = new byte[4];
    presented8 = new byte[8];
  }

  /**
   * Reads PIN information from input stream.
   *
   * @param reader the byte array reader to use.
   * @return this <tt>PINInfo</tt> object.
   */
   public PINInfo read(ByteArrayReader reader) {
     tryLimit = reader.readByte();
     maxSize = reader.readByte();
     byte length = reader.readByte();
     if (length == 4)
       reference = reference4;
     else if (length == 8)
       reference = reference8;
     else
       PINException.throwIt(PINException.ILLEGAL_VALUE);
     reader.read(reference,(short)0,length);
     length = reader.readByte();
     if (length == 4)
       presented = presented4;
     else if (length == 8)
       presented = presented8;
     else
       PINException.throwIt(PINException.ILLEGAL_VALUE);
     reader.read(presented,(short)0,length);
     remainingTries = reader.readByte();
     return this;
   }
   
   /**
    * Writes PIN information to output stream.
    * 
    * @param writer the byte array writer to use.
    */
   public void write(ByteArrayWriter writer) {
     writer.writeByte(tryLimit);
     writer.writeByte(maxSize);
     writer.writeByte((short)reference.length);
     writer.write(reference,(short)0,(short)reference.length);
     writer.writeByte((short)presented.length);
     writer.write(presented,(short)0,(short)presented.length);
     writer.writeByte(remainingTries);
   }
   
   /**
    * Returns the size of the PIN information.
    * 
    * @return the size in bytes of the PIN information.
    */
   public short getSize() {
     return (short) (((short) 5 + (short)reference.length) + 
    		                      (short)presented.length);
   }
   
   /**
    * Returns the PIN try limit to be passed to the <tt>OwnerPIN</tt> 
    * constructor.
    * 
    * @return the PIN try limit.
    * @see OwnerPIN#OwnerPIN(byte, byte)
    */
   public byte getTryLimit() {
     return tryLimit;
   }
   
   /**
    * Returns the maximum PIN size to be passed to the <tt>OwnerPIN</tt>
    * constructor.
    * 
    * @return the maximum PIN size.
    * @see OwnerPIN#OwnerPIN(byte, byte)
    */
   public byte getMaxSize() {
     return maxSize;
   }
   
   /**
    * Returns the reference PIN to be passed to the <tt>OwnerPIN.update</tt>
    * method.
    * 
    * @return the reference PIN.
    * @see OwnerPIN#update(byte[], short, byte)
    */
   public byte[] getReferencePIN() {
     return reference;
   }
   
   /**
    * Returns the PIN to be passed to the <tt>OwnerPIN.check</tt> method.
    * 
    * @return the presented PIN.
    * @see OwnerPIN#check(byte[], short, byte)
    */
   public byte[] getPresentedPIN() {
     return presented;
   }
   
   /**
    * Returns the number of times that the PIN is to be presented using the
    * <tt>OwnerPIN.check</tt> method.
    * 
    * @return the number of tries.
    */
   public byte getTriesRemaining() {
     return remainingTries;
   }
   
   /**
    * Updates the PIN try limit.
    * 
    * @param tryLimit the new PIN try limit.
    */
   public void setTryLimit(byte tryLimit) {
     this.tryLimit = tryLimit;
   }
}
