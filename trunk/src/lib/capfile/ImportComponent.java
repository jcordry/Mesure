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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/capfile/ImportComponent.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

import java.io.DataInputStream;
import java.io.IOException;

/**
 * An Import Component, as illustrated in Java Card platform Virtual Machine
 * specification.
 */
public class ImportComponent extends Component {

  /** The table of imported packages. **/
  private PackageInfo[] packages;
  
  /** 
   * Returns the imported packages.
   * 
   * @return an array containing the imported packages.
   */
  public PackageInfo[] getPackages() {
    return packages;
  }

  /**
   * Reads import component from input stream.
   *
   * @param in input stream to read from.
   * @throws <tt>CAPException</tt> for any problem while reading the stream.
   **/
  public Component parse(DataInputStream in) throws CAPException
  {
	try {
      int count = in.readUnsignedByte();
      packages = new PackageInfo[count];
      for(int i = 0; i < count; i++)
        packages[i] = new PackageInfo().parse(in);
	} catch (IOException e) {
	  throw new CAPException(e);
	}
    return this;
  }

}
