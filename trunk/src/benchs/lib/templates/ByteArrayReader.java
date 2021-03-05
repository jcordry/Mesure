package benchs.lib.templates;


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
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 11 octobre 2006
 * Author: TL 
 * $LastChangedRevision: 35 $
 * $LastChangedDate: 2006-09-29 17:10:44 +0200 (ven., 29 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This tool helps developers handling byte arrays.
 * It provides a reader that allows to retrieve each element of the byte 
 * array, in sequence, assuming that the element type is known.
 */
public class ByteArrayReader {
	
  /** The array of bytes to be read. */
  private byte[] buffer;

  /** The index of the next character to be read from the buffer. */
  private short current_offset;

  /**
   * Creates a <tt>ByteArrayReader</tt>.
   * 
   * @param buffer the input buffer.
   * @param offset the offset in the buffer of the first byte to read.
   */
  public ByteArrayReader(byte[] buffer, short offset) {
	byte[] new_buffer = new byte[buffer.length];
	for (short s = 0; s < new_buffer.length; s++)
	  new_buffer[s] = buffer[s];
	this.buffer = new_buffer;
	current_offset = offset;
  }

  /**
   * Reads the next byte of data from the input buffer.
   * The value byte is returned as a <tt>short</tt> in the range <tt>0</tt> to 
   * <tt>255</tt>.
   * If no byte is available because the end of the buffer has been reached,
   * the value <tt>-1</tt> is returned.
   * 
   * @return the next byte of data, or <tt>-1</tt> if the end of the
   * buffer has been reached.
   */
  public short read() {
    return (current_offset < buffer.length)
      ? (short) (buffer[current_offset++] & 0xff)
      : -1;
  }

  /**
   * Reads up to <tt>length</tt> bytes of data into an array of bytes
   * from the input buffer.
   * If no byte is available because the end of the buffer has been reached,
   * the value <tt>-1</tt> is returned.
   * Otherwise, the  number <tt>k</tt> of bytes read is returned.
   * 
   * @param buffer the buffer into which the data is read.
   * @param offset the start offset of the data.
   * @param length the maximum number of bytes read.
   * @return the total number of bytes read into the buffer or <code>-1</code>.
   * @throw <tt>NullPointerException</tt> if the specified buffer is null.
   * @throw <tt>IndexOutOfBoundsException</tt> if the specified offset is negative,
   * the specified length is negative or greater than the length of the specified
   * buffer, or if the length of the specified buffer is lower than the specified
   * offset plus the specified length.
   */
  public short read(byte[] b, short offset, short length) {
    if (b == null)
      throw new NullPointerException();
    if ((offset < 0)
      || (offset > (short) b.length)
      || (length < 0)
      || ((short) (offset + length) > (short) b.length))
      throw new IndexOutOfBoundsException();
    if (current_offset >= buffer.length)
      return -1;
    if ((short)(current_offset + length) > buffer.length)
      length = (short) ((short) buffer.length - current_offset);
    if (length == 0)
      return 0;
	for (short s = 0; s < length; s++)
	  b[(short)(offset + s)] = buffer[(short)(current_offset + s)];
    current_offset = (short) (current_offset + length);
    return length;
  }

  /**
   * Reads the next byte of data from the input buffer as a signed 8-bit
   * byte.
   * 
   * @return the next byte of data.
   * @throw <tt>ArrayIndexOutOfBoundsException</tt> if the end of the buffer has
   * been reached.
   */
  public byte readByte() {
    short b = read();
    if (b < 0)
      throw new ArrayIndexOutOfBoundsException();
    return (byte) b;
  }

  /**
   * Reads the next byte of data from the input buffer as an unsigned 8-bit
   * byte.
   * 
   * @return the next byte of data.
   * @throw <tt>ArrayIndexOutOfBoundsException</tt> if the end of the buffer has
   * been reached.
   */
  public short readUnsignedByte() {
    short b = read();
    if (b < 0)
      throw new ArrayIndexOutOfBoundsException();
    return b;
  }

  /**
   * Reads the next two bytes of data from the input buffer as a signed 16-bit
   * short.
   * 
   * @return the next two bytes of data.
   * @throw <tt>ArrayIndexOutOfBoundsException</tt> if the end of the buffer has
   * been reached.
   */
  public short readShort() {
    short b1 = read();
    short b2 = read();
    if ((short) (b1 | b2) < 0)
      throw new ArrayIndexOutOfBoundsException();
    return (short) ((short) (b1 << 8) + b2);
  }
  
  /**
   * Resets the input buffer to <tt>buffer</tt> and the current offset to 
   * <tt>offset</tt>.
   * 
   * @param buffer the input buffer.
   * @param offset the offset in the buffer of the first byte to read.
   */
  public void reset(byte[] buffer, short offset) {
    if (this.buffer.length != buffer.length) {
      byte[] new_buffer = new byte[buffer.length];
	  this.buffer = new_buffer;
    }
    for (short s = 0; s < this.buffer.length; s++)
	  this.buffer[s] = buffer[s];
	current_offset = offset;
  }
}