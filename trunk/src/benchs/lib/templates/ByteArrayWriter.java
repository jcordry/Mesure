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
 * It permits to write different elements serially in a byte array.
 */
public class ByteArrayWriter {
	
  /**
   * The buffer where data are stored.
   */
  private byte[] buffer;

  /**
   * The number of bytes in the buffer.
   */
  private short count;

  /**
   * Creates a new <tt>ByteArrayWriter</tt>.
   * 
   * @param size the initial size.
   * @throw <tt>NegativeArraySizeException</tt> if size is negative.
   */
  public ByteArrayWriter(short size) {
    if (size < 0)
      throw new NegativeArraySizeException();
    buffer = new byte[size];
    count = 0;
  }
   
  /**
   * Creates a new <tt>ByteArrayWriter</tt>.
   * @param buffer the buffer where to store data
   * @throw <tt>NegativeArraySizeException</tt> if size is negative.
   */
  public ByteArrayWriter(byte[] buffer) {
    this.buffer = buffer;
    count = 0;
  }

  /**
   * Writes the specified byte to the output buffer.
   * 
   * @param b the byte to be written.
   */
  public void write(short b) {
    count++;
    if (count > (short) buffer.length) {
      byte[] new_buffer = new byte[max((short)((short)buffer.length << 1), count)];
	  for (short s = 0; s < (short)(count - 1); s++)
	    new_buffer[s] = buffer[s];
      buffer = new_buffer;
    }
    buffer[(short)(count - 1)] = (byte) b;
  }

  /**
   * Writes <tt>length</tt> bytes from the specified byte array
   * starting at offset <tt>offset</tt> to the output buffer.
   * 
   * @param b the bytes to be written.
   * @param offset the start offset of the data.
   * @param length the number of bytes to write.
   * @throw <tt>IndexOutOfBoundsException</tt> if the specified offset is negative,
   * the specified length is negative or greater than the length of the specified
   * buffer, or if the length of the specified buffer is lower than the specified
   * offset plus the specified length.
   */
  public void write(byte[] b, short offset, short length) {
    if ((offset < 0)
      || (offset > (short) b.length)
      || (length < 0)
      || ((short) (offset + length) > (short) b.length))
      throw new IndexOutOfBoundsException();
    if (length == 0)
      return;
    count = (short) (count + length);
    if (count > (short) buffer.length) {
      byte[] new_buffer = new byte[max((short)((short)buffer.length << 1), count)];
	  for (short s = 0; s < (short)(count - length); s++)
	    new_buffer[s] = buffer[s];
      buffer = new_buffer;
    }
	for (short s = 0; s < length; s++)
	  buffer[(short)((short)(count - length) + s)] = b[(short)(offset + s)];
  }

  /**
   * Writes a byte to the output buffer.
   * 
   * @param i the byte to be written.
   */
  public void writeByte(short i) {
    byte b = (byte) i;
    write(b);
  }

  /**
   * Writes a short to the output buffer.
   * 
   * @param i the short to be written.
   */
  public void writeShort(short i) {
    write((short) (i >> 8));
    write((short) (i & 0xFF));
  }

  /**
   * Creates a newly allocated byte array. Its size is the current
   * size of the output buffer and the valid contents of the buffer
   * have been copied into it.
   * 
   * @return a copy of the output buffer.
   */
  public byte[] toByteArray() {
    byte[] new_buffer = new byte[count];
	for (short s = 0; s < count; s++)
	  new_buffer[s] = buffer[s];
    return new_buffer;
  }
  
  /**
   * Copies the output buffer into the specified buffer.
   * 
   * @param new_buffer the buffer where to copy the output data
   * @param offset the start offset of the data
   */
  public void toByteArray(byte[] new_buffer, short offset) {
	if ((offset < 0)
	  || (offset > (short) new_buffer.length)
	  || ((short) (offset + count) > (short) new_buffer.length))
	  throw new IndexOutOfBoundsException();	
	for (short s = 0; s < count; s++)
	  new_buffer[(short)(offset + s)] = buffer[s];
  }  
  
  /**
   * Resets the number of bytes in the output buffer to 0.
   */
  public void clear() {
    count = 0;
  }
  
  /**
   * Returns the greater of two short value.
   * 
   * @param a an argument
   * @param b another argument
   * @return the larger of a and b
   */
  private short max(short a, short b) {
  	if (a >= b)
  	  return a;
  	else
  	  return b;
  }
}