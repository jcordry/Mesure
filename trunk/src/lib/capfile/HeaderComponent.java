package lib.capfile;

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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/capfile/HeaderComponent.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 43 $
 * $LastChangedDate: 2006-10-16 17:17:37 +0200 (lun., 16 oct. 2006) $
 * $LastChangedBy: cpascal $
 */

import java.io.DataInputStream;
import java.io.IOException;

/**
 * An Header Component, as illustrated in Java Card platform Virtual Machine
 * specification.
 */
public class HeaderComponent extends Component {
 
  /** Magic number. **/
  final static int MAGIC = 0xdecaffed;
  
  /** Supported minor version. **/
  final static int MINOR_VERSION = 2;
  
  /** Supported major version. **/
  final static int MAJOR_VERSION = 2;
  
  // Flags
  public final static int ACC_INT    = 0x01;
  public final static int ACC_EXPORT = 0x02;
  public final static int ACC_APPLET = 0x04;

  /** Package info. **/
  private PackageInfo pkg;
  
  /**
   * Returns the package AID.
   * 
   * @return the package AID.
   */
  public byte[] getAID() {
    return pkg.getAID();
  }

  /**
   * Reads header component from input stream.
   *
   * @param in input stream to read from.
   * @throw <tt>CAPException</tt> for any problem while reading the stream.
   **/
  public Component parse(DataInputStream in) throws CAPException {
	int magic = 0;
	try {
      magic = in.readInt();
	} catch (IOException e) {
	  throw new CAPException(e);
	}
    if(magic != MAGIC)
      throw new CAPException("Wrong magic number: 0x"+Long.toHexString(magic));
    int minorVersion = 0;
    int majorVersion = 0;
    try {
      minorVersion = in.readUnsignedByte();
      majorVersion = in.readUnsignedByte();
    } catch (IOException e) {
	  throw new CAPException(e);
	}
    if(majorVersion != MAJOR_VERSION || minorVersion > MINOR_VERSION)
      throw new CAPException("CAP file version not supported: " + 
                             majorVersion + "." + minorVersion);
    try {
      in.readUnsignedByte();
      pkg = new PackageInfo().parse(in);
    } catch (IOException e) {
	  throw new CAPException(e);
	}
    return this;
  }
}