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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/CommandAPDU.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 356 $
 * $LastChangedDate: 2007-08-22 15:14:46 +0200 (mer., 22 août 2007) $
 * $LastChangedBy: jcordry $
 */

package lib.cad;

import java.util.Arrays;

/**
 * This class represents an APDU command, as specified in ISO7816.
 */
public class CommandAPDU {

  /** Command header. */
  private byte[] header;

  /** Command data. */
  private byte[] data;

  /** Command <tt>Le</tt> byte. */
  private int le;
  
  /** 
   * Constant to be used for <tt>le</tt> field if the command does not contain
   * an <tt>Le</tt> byte (case 1 or case 3 commands). */
  private final static int NO_LE = -1;
  
  /** Command name. */
  private String name;
  
  /**
   * Constructs an empty <tt>CommandAPDU</tt>.
   */
  private CommandAPDU() {
    this.header = new byte[4];
    le = NO_LE;
  }
  
  /**
   * Sets the <code>CLA</code> byte of this APDU command.
   * 
   * @param cla the new value for the class byte <code>CLA</code>.
   */
  public void setCLA(int cla) {
    header[0] = (byte)cla;
  }
  
  /**
   * Sets the <code>INS</code> byte of this APDU command.
   * 
   * @param ins the new value for the instruction byte <code>INS</code>.
   */
  public void setINS(int ins) {
    header[1] = (byte)ins;
  }
  
  /**
   * Sets the command data.
   * 
   * @param data the byte array containing the data bytes of the command body.
   * @throws <tt>NullPointerException</tt> if <tt>data</tt> is null.
   * @throws <tt>IllegalArgumentException</tt> if <tt>data.length</tt> is 
   *         greater than 65535.
   */
  public void setData(byte[] data) {
	setData(data,0,data.length);
  }
  
  /**
   * Sets the command data.
   * 
   * @param data the byte array containing the data bytes of the command body.
   * @param offset the offset in <tt>data</tt> at which the data bytes of the 
   *        command body begin.
   * @param length the number of the data bytes in the command body.
   * @throws <tt>NullPointerException</tt> if <tt>data</tt> is null and 
   *         <tt>length</tt> is not 0.
   * @throws <tt>IllegalArgumentException</tt>if <tt>offset</tt> or 
   *         <tt>length</tt> are negative or if <tt>offset + length</tt> are 
   *         greater than <tt>data.length</tt> or if <tt>length</tt> is greater 
   *         than 65535.
   */
  public void setData(byte[] data, int offset, int length) {
    if (length == 0)
      this.data = null;
    else {
      if (length > 65535)
        throw new IllegalArgumentException("Invalid Lc: " + data.length);
      this.data = new byte[length];
      try {
        System.arraycopy(data,offset,this.data,0,length);
      } catch (IndexOutOfBoundsException e) {
    	throw new IllegalArgumentException(e.getMessage());
      }
    }
  }
  
  /**
   * Sets the parameter byte <tt>P1</tt>.
   * 
   * @param p1 the new value for the parameter byte <tt>P1</tt>.
   */
  public void setP1(int p1) {
    header[2] = (byte)p1;
  }
  
  /**
   * Sets the parameter byte <tt>P2</tt>.
   * 
   * @param p1 the new value for the parameter byte <tt>P2</tt>.
   */
  public void setP2(int p2) {
    header[3] = (byte)p2;
  }
  
  /**
   * Sets the expected length for the APDU response.
   * 
   * @param le the number of expected data bytes in the APDU response.
   * @throws <tt>IllegalArgumentException</tt> if <tt>le</tt> is negative or
   *         greater than 65536.
   */
  public void setLe(int le) {
    if ((le < 0) || (le > 65536))
	  throw new IllegalArgumentException("Invalid Le: " + le);
	this.le = le;
  }
  
  /**
   * Sets the command name.
   * 
   * @param name to name to b give to this command.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Constructs a <tt>CommandAPDU</tt> from the four header bytes. 
   * This corresponds to a case 1 command, according to ISO7816.
   * 
   * @param cla the class byte <tt>CLA</tt>.
   * @param ins the instruction byte <tt>INS</tt>.
   * @param p1 the parameter byte <tt>P1</tt>.
   * @param p2 the parameter byte <tt>P2</tt>.
   */
  public CommandAPDU(int cla, int ins, int p1, int p2) {
    this();
    this.header[0] = (byte)cla;
    this.header[1] = (byte)ins;
    this.header[2] = (byte)p1;
    this.header[3] = (byte)p2;
  }

  /**
   * Constructs a <tt>CommandAPDU</tt> from the four header bytes and the 
   * expected response data length. 
   * This corresponds to a case 2, according to ISO7816. 
   * 
   * @param cla the class byte <tt>CLA</tt>.
   * @param ins the instruction byte <tt>INS</tt>.
   * @param p1 the parameter byte <tt>P1</tt>.
   * @param p2 the parameter byte <tt>P2</tt>.
   * @param le the number of expected data bytes in the APDU response.
   * @throws <tt>IllegalArgumentException</tt> if <tt>le</tt> is negative or
   *         greater than 65536.
   */
  public CommandAPDU(int cla, int ins, int p1, int p2, int le) {
    this(cla, ins, p1, p2);
    setLe(le);
  }
  
  /**
   * Constructs a <tt>CommandAPDU</tt> from the four header bytes and command 
   * data. 
   * This corresponds to a case 3, according to ISO7816.
   * Note that the data bytes are copied to protect against subsequent 
   * modification.
   * @param cla the class byte <tt>CLA</tt>.
   * @param ins the instruction byte <tt>INS</tt>.
   * @param p1 the parameter byte <tt>P1</tt>.
   * @param p2 the parameter byte <tt>P2</tt>.
   * @param data the byte array containing the data bytes of the command body.
   * @throws <tt>NullPointerException</tt> if <tt>data</tt> is null.
   * @throws <tt>IllegalArgumentException</tt> if <tt>data.length</tt> is 
   *         greater than 65535.
   */
  public CommandAPDU(int cla, int ins, int p1, int p2, byte[] data) {
    this(cla,ins,p1,p2);
    setData(data);
  }

  /**
   * Constructs a <tt>CommandAPDU</tt> from the four header bytes, command data, 
   * and expected response data length. 
   * This corresponds to a case 4, according to in ISO7816. 
   * Note that the data bytes are copied to protect against subsequent 
   * modification.
   * 
   * @param cla the class byte <tt>CLA</tt>.
   * @param ins the instruction byte <tt>INS</tt>.
   * @param p1 the parameter byte <tt>P1</tt>.
   * @param p2 the parameter byte <tt>P2</tt>.
   * @param le the number of expected data bytes in the APDU response.
   * @param data the byte array containing the data bytes of the command body.
   * @throws <tt>NullPointerException</tt> if <tt>data</tt> is null.
   * @throws <tt>IllegalArgumentException</tt> if <tt>le</tt> is negative or
   *         greater than 65536.
   * @throws <tt>IllegalArgumentException</tt> if <tt>data.length</tt> is 
   *         greater than 65535.
   */
  public CommandAPDU(int cla, int ins, int p1, int p2, byte[] data, int le) {
    this(cla,ins,p1,p2,data);
    setLe(le);
  }

  /** 
   * Constructs a <tt>CommandAPDU</tt> from a byte array containing the complete 
   * APDU contents (header and body).
   * Note that the apdu bytes are copied to protect against subsequent 
   * modification.
   * 
   * @param capdu the complete APDU command.
   * @throws <tt>NullPointerException</tt> if <tt>capdu</tt> is null.
   * @throws <tt>IndexOutOfBoundsException</tt> if copying would cause access of 
   *         data outside array bounds.
   * @throws <tt>IllegalArgumentException</tt> if <tt>capdu</tt> does not 
   *         contain a valid APDU command.
   */
  public CommandAPDU(byte[] capdu) {
	this(capdu[0],capdu[1],capdu[2],capdu[3]);
    if (capdu.length == 5)
      setLe(capdu[4]&0xff);
    else if (capdu.length > 5) {
      setData(capdu,5,capdu[4]&0xff);
      if (data.length + 5 == capdu.length)
        le = NO_LE;
      else if (data.length + 6 == capdu.length)
    	setLe(capdu[capdu.length -1]&0xff);
      else
        throw new IllegalArgumentException("Invalid APDU format");
    }
  }

  /**
	 * Returns the <code>CLA</code> byte of this APDU command.
	 * 
	 * @return the value of the class byte <code>CLA</code>.
	 */
  public int getCLA() {
    return header[0] & 0xFF;
  }

  /**
   * Returns the <code>INS</code> byte of this APDU command.
   * 
   * @return the value of the instruction byte <code>INS</code>.
   */
  public int getINS() {
    return header[1] & 0xFF;
  }
  
  /**
   * Returns the <code>P1</code> byte of this APDU command.
   * 
   * @return the value of the parameter byte <code>P1</code>.
   */
  public int getP1() {
    return header[2] & 0xFF;
  }


  /**
   * Returns the <code>P2</code> byte of this APDU command.
   * 
   * @return the value of the parameter byte <code>P2</code>.
   */
  public int getP2() {
    return header[3] & 0xFF;
  }
 
  /**
   * Returns the <code>Lc</code> byte of this APDU command.
   * 
   * @return the number of data bytes in this APDU command or 0 if this APDU has 
   *         no body. 
   *         This call is equivalent to <code>getData().length</code>.
   */
  public int getLc() {
    if (data == null)
	  return 0;
	return data.length;
  }
 
  /**
   * Returns a copy of the data bytes in this APDU command. 
   * If this APDU as no body, this method returns a byte array with length zero.
   * 
   * @return a copy of the data bytes in this APDU command or an empty byte 
   *         array if this APDU has no body.
   */
  public byte[] getData() {
    byte[] data;
	if (this.data == null)
	  data = new byte[0];
	else {
	  data = new byte[this.data.length];
	  System.arraycopy(this.data,0,data,0,data.length);
	}
	return data;
  }

  /**
   * Gets the <code>Le</code> byte of this APDU command.
   * 
   * @return the maximum number of expected data bytes in an APDU response.
   */
  public int getLe() {
    return le;
  }
  
  /**
   * Returns a copy of the bytes in this APDU command.
   * 
   * @return a copy of the bytes in this APDU command.
   */
  public byte[] getBytes() {
    byte[] capdu = null;
	switch (getCase()) {
	  case 1:
	    capdu = new byte[4];
	    System.arraycopy(header,0,capdu,0,4);
	    break;
	  case 2:
	    capdu = new byte[5];
	    System.arraycopy(header,0,capdu,0,4);
	    capdu[4] = (byte)le;
	    break;
	  case 3:
	    capdu = new byte[5 + data.length];
	    System.arraycopy(header,0,capdu,0,4);
	    capdu[4] = (byte)getLc();
	    System.arraycopy(data,0,capdu,5,data.length); 
	    break;
	  case 4:
		capdu = new byte[6 + data.length];
		System.arraycopy(header,0,capdu,0,4);
		capdu[4] = (byte)getLc();
		System.arraycopy(data,0,capdu,5,data.length); 
		capdu[capdu.length-1] = (byte)le;
	    break;
	  default:
		// should not occur
		break;
	}
	return capdu;
  }
  
  /**
   * Returns the header of this APDU command.
   * 
   * @return the command header.
   */
  public byte[] getHeader() {
    return header;
  }
  
  /**
   * Returns the case of this APDU command, according to ISO7816.
   * @return 1 for CASE 1 commands.
   * @return 2 for CASE 2 commands.
   * @return 3 for CASE 3 commands.
   * @return 4 for CASE 4 commands.
   */
  public byte getCase() {
    if (getLc() > 0) {
      if (le == NO_LE)
	    // Case 3 command
		return 3;
	  else
		// Case 4 command
		return 4;
	} else if (le == NO_LE)
      // Case 1 command
	  return 1;
    else
	  // Case 2 command
	  return 2;
  }
  
  /**
   * Returns the command name.
   * If the command name has not been set, the empty string is returned.
   * 
   * @return the command name.
   */
  public String getName() {
    if (name == null)
      return "";
    return name;
  }

  /**
   * Returns a string representation of this APDU command.
   * 
   * @inheritDoc
   * @return a string representation of this APDU command.
   */
  public String toString() {
	  StringBuffer s = new StringBuffer();
	  String sep = System.getProperty("line.separator");
	  if (name != null) {
	    s.append("=> " + name + sep);
	    s.append("   CLA INS P1 P2:");
	  }
	  else
	    s.append("=> CLA INS P1 P2:");
	  for(int i = 0; i < 4; i++)
	    s.append(" " + byteToHexString(header[i]));
	  if (getLc() > 0)
	    s.append(" Lc: " + byteToHexString((byte)getLc()));
	  if (le != NO_LE) 
		s.append(" Le: " + byteToHexString((byte)le));
	  if (getLc() > 0) {
	    s.append(sep + "   Data: ");
	    s.append(byteArrayToString(data).replaceAll(sep,sep + "         "));
	  }
	  return s.toString();
  }

  /**
   * Returns a string representation of this APDU command.
   * 
   * @inheritDoc
   * @return a string representation of this APDU command.
   */
  public String toHexString() {
	  StringBuffer s = new StringBuffer();
	  for(int i = 0; i < 4; i++)
	    s.append(byteToHexString(header[i]));
	  if (getLc() > 0)
	    s.append(byteToHexString((byte)getLc()));
	  if (le != NO_LE) 
		s.append(byteToHexString((byte)le));
	  if (getLc() > 0) {
	    s.append(byteArrayToString(data));
	  }
	  return s.toString();
  }
  
  /**
   * Returns a string representation of a byte in base 16.
   * @param b the byte to be represented.
   * @return a <tt>String</tt> object representing <tt>b</tt> in base 16.
   */
  static String byteToHexString(byte b) {
    StringBuffer s = new StringBuffer();
    if ((b & 0xff) < 0x10)
      s.append("0");
    s.append(Integer.toHexString(b & 0xff).toUpperCase());
    return s.toString();
  }

  /**
   * Returns a string representation of a byte array in base 16. 
   * @param data the byte array to be represented.
   * @return a <tt>String</tt> object representing <tt>data</tt> in base 16.
   */
  static String byteArrayToString(byte[] data) {
	StringBuffer s = new StringBuffer();
	int col = 1;
    String sep = System.getProperty("line.separator");
    for (int i = 0; i < data.length; i++) {
      if ((col%17) == 0) {
        s.append(sep);
        col = 1;
      }
      s.append(byteToHexString(data[i]) + " ");
      col++;
    }
    return s.toString();
  }

  /**
   * Compares the specified object with this APDU command for equality. 
   * Returns <code>true</true> if the given object is also a 
   * <code>CommandAPDU</code> and its bytes are identical to the bytes 
   * in this <code>CommandAPDU</code>.
   * 
   * @inheritDoc
   * @param obj the object to be compared for equality with this APDU 
   *            command
   * @return <code>true</code> if and only if the specified object is equal to
   *         this APDU command.
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof CommandAPDU))
	  return false;
    return Arrays.equals(getBytes(),((CommandAPDU)obj).getBytes());
  }
}
