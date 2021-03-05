package lib.cad;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.Vector;

import lib.capfile.CAPFile;
import lib.capfile.Component;

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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/LoadAPDU.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class represents the GlobalPlatform LOAD command.
 */
public class LoadAPDU extends CommandAPDU {

  /** Class byte (<tt>0x80</tt>). */
  public static final int CLA = 0x80;
	
  /** Instruction byte (<tt>0xE8</tt>). */
  public static final int INS = 0xE8;
  
  /** Last block P1 parameter (<tt>0x80</tt>). */
  public static final int P1_LAST = 0x80;
  
  /** More blocks P1 parameter (<tt>0x00</tt>. */
  public static final int P1_MORE = 0x00;
  
  /** Default P2 parameter (<tt>0x00</tt>). */
  public static final int P2 = 0x00;
  
  /** Load File data block tag (<tt>0xC4</tt>). */
  public static final byte LOAD_FILE_DATA_BLOCK = (byte)0xC4;
  
  /** Command name. */
  public static final String NAME = "LOAD";
  
  /** Load order for the CAP file components. */
  private static final int[] LOAD_ORDER =
  {
    Component.COMPONENT_Header,
    Component.COMPONENT_Directory,
	Component.COMPONENT_Import,
	Component.COMPONENT_Applet,
	Component.COMPONENT_Class,
	Component.COMPONENT_Method,
	Component.COMPONENT_StaticField,
	Component.COMPONENT_Export,
	Component.COMPONENT_ConstantPool,
	Component.COMPONENT_ReferenceLocation,
	Component.COMPONENT_Descriptor
  };

  /** 
   * Constructs a LOAD APDU command with the specified <tt>P1</tt> and 
   * <tt>P2</tt> parameters and the specified data. <tt>Le</tt> is set to 0.
   * 
   * @param p1 the parameter byte <tt>P1</tt>.
   * @param p2 the parameter byte <tt>P2</tt>.
   * @param data the byte array containing the data bytes of the command body.
   */
  public LoadAPDU(int p1, int p2, byte[] data) {
    super(CLA,INS,p1,p2,data,0);
    setName(NAME);
  }
  
  /**
   * Returns an array of <tt>LoadAPDU</tt> commands.
   * This array contains all the LOAD commands needed to load this CAP file. 
   * Note that it may be necessary to wrap the <tt>CLA</tt> byte of these 
   * commands, according to the security level.
   * 
   * @param max the maximum value for the <tt>Lc</tt> byte in each LOAD APDU
   *            command.
   */
  public static LoadAPDU[] getLoadCommandAPDU(CAPFile capfile, int max) {
	Component[] components = capfile.getComponents();
	ByteArrayOutputStream data = new ByteArrayOutputStream(max);
	int lc = 0;
	Vector commands = new Vector();
	
	// Write tag
    data.write(LOAD_FILE_DATA_BLOCK);
    lc++;	
    
    // Write length
    int capSize = capfile.getSize();
    if (capSize > 127) {
      byte[] size = BigInteger.valueOf(capSize).toByteArray();
	  if (size[0] == 0) {
	    byte[] temp = new byte[size.length - 1];
	    System.arraycopy(size,1,temp,0,temp.length);
	    size = temp;
	  }
	  data.write((0x80 | size.length));
	  data.write(size,0,size.length);
	  lc += (size.length + 1);
    } 
    else { 
      data.write(capSize);
	  lc++;
    }
	
	// Write components
	for (int i = 0; i < LOAD_ORDER.length; i++) {
      Component component = components[LOAD_ORDER[i]];
      // Applet Component or Import Component may be absent
      if (component == null)
        continue;
	  byte[] bytes = component.getBytes();
	  for (int j = 0; j < bytes.length; ) {
	    int size = bytes.length - j;
	    if (size + lc > max)
	      size = max - lc;
	    data.write(bytes,j,size);
	    LoadAPDU capdu = new LoadAPDU(P1_MORE,commands.size(),data.toByteArray());
	    capdu.setName(NAME + " [" + component.getName() + "]");
	    commands.add(capdu);
        lc = 0;
        data.reset();
	    j += size; 
	  }
	}
	
	((LoadAPDU) commands.lastElement()).setP1(P1_LAST);
	
	return (LoadAPDU[]) commands.toArray(new LoadAPDU[0]);
  }
}
