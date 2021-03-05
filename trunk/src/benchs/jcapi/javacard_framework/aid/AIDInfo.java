package benchs.jcapi.javacard_framework.aid;

import javacard.framework.SystemException;
import benchs.lib.templates.ByteArrayReader;
import benchs.lib.templates.ByteArrayWriter;

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
 * $HeadURL: $
 * Created: 11 mai 2007
 * Author: TL 
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: cpascal $
 */

/**
 * This class represents an Application IDentifier (AID).
 */
public class AIDInfo {
  
  /** The AID. */
  private byte[] aid;
  
  /** The starting offset in the <tt>aid</tt> array. */
  private short offset;
  
  /** The length of the AID in the <tt>aid</tt> array. */
  private byte length;
  
  /** The offset in the 
  
  /**
   * Defines a new AID information structure.
   * 
   * @param aid the AID.
   * @param offset the offset.
   * @param length the length.
   */
  public AIDInfo(byte[] aid, short offset, byte length) {
    this.aid = aid;
    this.offset = offset;
    this.length = length;
  }
  
  /**
   * Defines a new empty AID information structure.
   */
  public AIDInfo() {}

  /**
   * Reads AID information from input stream.
   *
   * @param reader the byte array reader to use.
   * @return this <tt>AIDInfo</tt> object.
   * @throw <tt>SystemException</tt> exception if <tt>length < 5</tt>,
   * or if <tt>length > 16</tt>.
   */
   public AIDInfo read(ByteArrayReader reader) {
     byte length = reader.readByte();
     if ((length < 5) || (length > 16))
       SystemException.throwIt(SystemException.ILLEGAL_VALUE);
     aid = new byte[length];
     reader.read(aid,(short)0,length);
     offset = reader.readShort();
     length = reader.readByte();
     return this;
   }
   
   /**
    * Writes AID information to output stream.
    * 
    * @param writer the byte array writer to use.
    */
   public void write(ByteArrayWriter writer) {
     writer.writeByte((short)aid.length);
     writer.write(aid,(short)0,(short)aid.length);
     writer.writeShort(offset);
     writer.writeByte(length);
   }
   
   /**
    * Returns the size of the AID information.
    * 
    * @return the size in bytes of the AID information.
    */
   public short getSize() {
     return (short)(4 + (short)aid.length);
   }
   
   /**
    * Returns the AID to be passed to the <tt>AID.<init></tt>
    * method.
    * 
    * @return the aid.
    * @see AID#AID(byte[], short, byte)
    */
   public byte[] getAID() {
     return aid;
   }
   
   /**
    * Return the offset to be passed to the following methods:
    * <ul>
    *   <li><tt>AID.equals(byte[],short,byte)</tt>
    *   <li><tt>AID.getBytes</tt>
    *   <li><tt>AID.partialEquals</tt>
    * </ul>
    * 
    * @return the offset.
    * @see AID#equals(byte[], short, byte)
    * @see AID#getBytes(byte[], short)
    * @see AID#partialEquals(byte[], short, byte)
    */
   public short getOffset() {
     return offset;
   }
   
   /**
    * Return the length to be passed to the following methods:
    * <ul>
    *   <li><tt>AID.equals(byte[],short,byte)</tt>
    *   <li><tt>AID.partialEquals</tt>
    * </ul>
    * 
    * @return the length.
    * @see AID#equals(byte[], short, byte)
    * @see AID#partialEquals(byte[], short, byte)
    */
   public byte getLength() {
     return length;
   }
}
