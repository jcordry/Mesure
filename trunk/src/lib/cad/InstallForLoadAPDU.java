package lib.cad;

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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/InstallForLoadAPDU.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class represents the GlobalPlatform INSTALL[for load] command.
 */
public class InstallForLoadAPDU extends CommandAPDU {

  /** Class byte (<tt>0x80</tt>). */
  public static final int CLA = 0x80;

  /** Instruction byte (<tt>0xE6</tt>). */
  public static final int INS = 0xE6;

  /** Default P1 parameter (<tt>0x02</tt>). */
  public static final int P1 = 0x02;

  /** Default P2 parameter (<tt>0x00</tt>). */
  public static final int P2 = 0x00;
  
  /** Command name. */
  public static final String NAME = "INSTALL [for load]";
  
  /** 
   * Constructs an INSTALL[for load] APDU command with the default <tt>P1</tt>
   * and <tt>P2</tt> parameters and the specified data. <tt>Le</tt> is set to 0.
   * 
   * @param packageAID the byte array containing the package AID identifying the
   *                    package to be loaded. This package will be loaded under
   *                    the Issuer Security Domain.
   * @param params the byte array containing the loading parameters.
   */
  public InstallForLoadAPDU(byte[] packageAID, byte[] params) {
    this(null,packageAID,params);
  }

  /** 
   * Constructs an INSTALL[for load] APDU command with the default <tt>P1</tt>
   * and <tt>P2</tt> parameters and the specified data. <tt>Le</tt> is set to 0.
   * 
   * @param packageAID the byte array containing the package AID identifying the
   *                    package to be loaded.
   * @param domainAID the byte array containing the applet AID identifying 
   *                  the security domain under which this package has to be 
   *                  loaded.
   * @param params the byte array containing the loading parameters.
   */
  public InstallForLoadAPDU(byte[] packageAID, 
                            byte[] domainAID, 
                            byte[] params) {
    super(CLA,INS,P1,P2,0);
    int length = 5;
    if (packageAID != null) 
      length += packageAID.length;
    if (domainAID != null) 
      length += domainAID.length;
    if (params != null) 
      length += params.length;
    byte[] data = new byte[length];
    int offset = 0;

    // sets the package AID
    if ((packageAID != null) && (packageAID.length != 0)) {
      data[offset] = (byte)packageAID.length;
      System.arraycopy(packageAID,0,data,offset+1,data[offset]&0xFF);
      offset += data[offset]&0xFF;
    }
    offset += 1;

    // sets the security domain AID
    if ((domainAID != null) && (domainAID.length != 0)) {
      data[offset] = (byte)domainAID.length;
      System.arraycopy(domainAID,0,data,offset+1,data[offset]&0xFF);
      offset += data[offset]&0xFF;
    }
    offset += 1;

    // skips load file block hash
    offset += 1;

    // sets parameters
    if ((params != null) && (params.length != 0)) {
      data[offset] = (byte)params.length;
      System.arraycopy(params,0,data,offset+1,data[offset]&0xFF);
      offset += data[offset]&0xFF;
    }
    offset += 1;

    // skips token
    offset += 1;
    
    setData(data);
    setName(NAME);
  }
}
