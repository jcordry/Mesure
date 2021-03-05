package lib.capfile;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/capfile/Component.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 219 $
 * $LastChangedDate: 2007-03-29 10:41:48 +0200 (jeu., 29 mars 2007) $
 * $LastChangedBy: cpascal $
 */

/**
 * A CAP file component, as illustrated in Java Card platform Virtual Machine
 * specification.
 */
public class Component {
	
  /** The Header Component. */
  public static final int COMPONENT_Header = 0x01;

  /** The Directory Component. */
  public static final int COMPONENT_Directory = 0x02;

  /** The Applet Component. */
  public static final int COMPONENT_Applet = 0x03;

  /** The Import Component. */
  public static final int COMPONENT_Import = 0x04;

  /** The Constant Pool Component. */
  public static final int COMPONENT_ConstantPool = 0x05;

  /** The Class Component. */
  public static final int COMPONENT_Class = 0x06;

  /** The Method Component. */
  public static final int COMPONENT_Method = 0x07;

  /** The Static Field Component. */
  public static final int COMPONENT_StaticField = 0x08;

  /** The Reference Location Component. */
  public static final int COMPONENT_ReferenceLocation = 0x09;

  /** The Export Component. */
  public static final int COMPONENT_Export = 0x0A;

  /** The Descriptor Component. */
  public static final byte COMPONENT_Descriptor = 0x0B;
  
  /** The Debug Component. */
  public static final byte COMPONENT_Debug = 0x12;
	
  /** The component tag. */
  private int tag;
  
  /** The component size. */
  private int size;
  
  /** The component info. */
  private byte[] info;
  
  /** The component name. */
  private String name;
  
  /** 
   * Returns the component tag. 
   * 
   * @return the component tag.
   */
  public int getTag() {
    return tag;
  }
  
  /**
   * Returns the component size.
   * 
   * @return the component size.
   */
  public int getSize() {
    return size;
  }
  
  /**
   * Returns the component name.
   * 
   * @return the component name.
   */
  public String getName() {
    if (name == null)
      return "";
    return name;
  }
  
  /**
   * Returns the complete contents of this component, including the tag and
   * size items.
   * 
   * @return a byte array containing the component bytes.
   */
  public byte[] getBytes() {
    byte[] temp = new byte[info.length + 3];
    temp[0] = (byte)tag;
    temp[1] = (byte)(size >> 8);
    temp[2] = (byte)(size & 0xFF);
    for (int i = 0; i < info.length; i++)
      temp[3+i] = info[i];
    return temp;
  }
  
  /**
   * Builds a <tt>Component</tt> from data read from the specified input stream.
   * 
   * @param in the input stream from which component data will be read.
   * @throws <tt>CAPException</tt> if an I/O error occurs or if the component is 
   * not well-formed. 
   */
  public static Component read(DataInputStream in) throws CAPException {
    Component component = null;
    int tag = 0;
    try {
      tag = in.readUnsignedByte();
    } catch (IOException e) {
      throw new CAPException(e);
    }
    
    switch( tag ){
    case COMPONENT_Header:
      component = new HeaderComponent();
      break;
    case COMPONENT_Applet:
      component = new AppletComponent();
      break;
    case COMPONENT_StaticField:
      component = new StaticFieldComponent();
      break;
    case COMPONENT_Import:
      component = new ImportComponent();
      break;
    case COMPONENT_Directory:
    case COMPONENT_ConstantPool:
    case COMPONENT_Class:
    case COMPONENT_Method:
    case COMPONENT_ReferenceLocation:
    case COMPONENT_Export:
    case COMPONENT_Descriptor:
      component = new Component();
      break;	
    case COMPONENT_Debug:
      break;
    default:
      throw new CAPException("Unknown component tag: "+tag);
    }

    // Fully read component
    component.tag = tag;
    try {
      component.size = in.readUnsignedShort();
      component.info = new byte[component.size];
      in.readFully(component.info);
    } catch (IOException e) {
      throw new CAPException(e);
    }
    // Check that all bytes are read
    try {
      // Read one extra byte
      in.readByte();
      // If no exception is raised, it means the end of stream was not reached
      throw new CAPException(
        "Remaining unread bytes after reading component " + tag);
    }
    // Discard exception if end of stream was reached
    catch(EOFException e ){}
    catch(IOException e) {
      throw new CAPException(e);
    }

    // Parse component information
    DataInputStream info_in = new DataInputStream(new ByteArrayInputStream(component.info));
    component.parse(info_in);
    
   
    return component;
  }
  
  /**
   * General parse method that should be overriden by each CAP file component.
   * 
   * @param in the input stream from which component data will be parsed.
   * @throws <tt>IOException</tt> if an I/O error occurs or if the component is 
   *         not well-formed. 
   */
  public Component parse(DataInputStream in) throws CAPException {
    return this;
  }
  
  /**
   * Sets the component name.
   * If the component name has not been set, the empty string is returned.
   * 
   * @param name the name to give to this component.
   */
  public void setName(String name) {
    this.name = name;
  }

}
