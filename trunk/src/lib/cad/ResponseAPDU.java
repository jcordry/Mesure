package lib.cad;

import java.util.Arrays;

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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/ResponseAPDU.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class represents an APDU response, as specified in ISO7816.
 */
public class ResponseAPDU {
	
  /** Response data. */
  private byte[] data;
  
  /** Response status. */
  private int sw;
 
  /** 
   * Constructs a <tt>ResponseAPDU</tt> from a byte array containing the 
   * complete APDU contents (conditional body and trailed).
   * Note that the byte array is cloned to protect against subsequent 
   * modification.
   * 
   * @param rapdu the complete APDU response.
   * @throws <tt>NullPointerException</tt> if <tt>rapdu</tt> is null.
   * @throws <tt>IllegalArgumentException</tt> if <tt>apdu.length</tt> is less 
   *         than 2.
   */
  public ResponseAPDU(byte[] rapdu) {
    this(rapdu,0,rapdu.length);
  }
  
  /** 
   * Constructs a <tt>ResponseAPDU</tt> from a byte array containing the 
   * complete APDU contents (conditional body and trailed).
   * Note that the byte array is cloned to protect against subsequent 
   * modification.
   * 
   * @param rapdu data the byte array containing the data bytes of the response.
   * @param offset the offset in <tt>rapdu</tt> at which the data bytes of the 
   *        response begin.
   * @param length the number of the data bytes in the response.
   * @throws <tt>NullPointerException</tt> if <tt>rapdu</tt> is null.
   * @throws <tt>IllegalArgumentException</tt> if <tt>length</tt> is less 
   *         than 2.
   */
  public ResponseAPDU(byte[] rapdu, int offset, int length) {
	if (length < 2)
	  throw new IllegalArgumentException("Invalid APDU format");
	if (length > 2) {
	  data = new byte[length-2];
	  System.arraycopy(rapdu,offset,data,0,data.length);
	}
	sw = (((int)(rapdu[offset+length-2])&0xff)<<8) | 
	      ((int)(rapdu[offset+length-1])&0xff);
  }
  
  /** 
   * Returns a copy of the data bytes in the response body. 
   * If this APDU response as no body, this method returns a byte array with 
   * length zero.
   * 
   * @return a copy of the data bytes in the response body or the empty byte 
   *         array if this APDU response has no body.
   */
  public byte[] getData() {
    if (this.data == null)
	  return new byte[0];
	byte[] data = new byte[this.data.length];
	System.arraycopy(this.data,0,data,0,data.length);
	return data;
  }
  
  /**
   * Returns the status bytes <code>SW1</code> and <code>SW2</code> as a single 
   * status word. 
   * It is defined as (getSW1() << 8) | getSW2().
   * 
   * @return the value of the status word <code>SW</code>
   */
  public int getSW() {
    return sw;
  }

  /**
   * Returns the status byte <code>SW1</code>.
   * 
   * @return the value of the status byte <code>SW1</code> as a value between 
   *         0 and 255.
   */
  public int getSW1() {
    return (sw >> 8) & 0xff;
  }

  /**
   * Returns the status byte <code>SW2</code>.
   * 
   * @return the value of the status byte <code>SW2</code> as a value between 
   *         0 and 255.
   */
  public int getSW2() {
    return sw & 0xff;
  }

  /**
   * Returns the number of data bytes in the response body or 0 if this APDU 
   * response has no body. 
   * This call is equivalent to <code>getData().length</code>.
   * 
   * @return the number of data bytes in the response body or 0 if this APDU 
   *         has no body.
   */
  public int getLr() {
    if (data == null)
	  return 0;
    return data.length;
  }

  /**
   * Returns a copy of the bytes in this APDU response.
   * 
   * @return a copy of the bytes in this APDU response.
   */
  public byte[] getBytes() {
    byte[] rapdu = null;
    if (getLr() > 0) {
	  rapdu = new byte[data.length + 2];
	  System.arraycopy(data,0,rapdu,0,data.length);
	} 
    else
	  rapdu = new byte[2];
	rapdu[rapdu.length-2] = (byte)((sw >> 8) & 0xff);
	rapdu[rapdu.length-1] = (byte)(sw & 0xff);
	return rapdu;
  }

  /**
   * Returns a string representation of this APDU response.
   * 
   * @inheritDoc
   * @return a string representation of this APDU response.
   */
  public String toString() {
    StringBuffer str = new StringBuffer();
	str.append("<= SW1-SW2: " + Integer.toHexString(getSW()).toUpperCase());
	if (getLr() > 0) {
	  String sep = System.getProperty("line.separator");
	  str.append(sep);
	  str.append("   Data: ");
	  str.append(
	      CommandAPDU.byteArrayToString(data).replaceAll(sep,sep + "         "));
	}
	return str.toString(); 
  }

  /**
   * Compares the specified object with this APDU response for equality. 
   * Returns <code>true</true> if the given object is also a 
   * <code>ResponseAPDU</code> and its bytes are identical to the bytes 
   * in this <code>ResponseAPDU</code>.
   * 
   * @inheritDoc
   * @param obj the object to be compared for equality with this APDU 
   *            response
   * @return <code>true</code> if and only if the specified object is equal to
   *         this APDU response.
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof ResponseAPDU))
	  return false;
	return Arrays.equals(getBytes(),((ResponseAPDU)obj).getBytes());
  }
}
