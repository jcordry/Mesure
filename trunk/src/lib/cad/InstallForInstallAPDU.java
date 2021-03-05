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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/InstallForInstallAPDU.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class represents the GlobalPlatform INSTALL[for install] command.
 */
public class InstallForInstallAPDU extends CommandAPDU {

  /** Class byte (<tt>0x80</tt>). */
  public static final int CLA = 0x80;

  /** Instruction byte (<tt>0xE6</tt>). */
  public static final int INS = 0xE6;

  /** Default P1 parameter (<tt>0x0C</tt>). */
  public static final int P1 = 0x0C;

  /** Default P2 parameter (<tt>0x00</tt>). */
  public static final int P2 = 0x00;
  
  /** Application Specific Parameters tag (<tt>0xC9</tt>). */
  private static final byte APP_PARAMS = (byte)0xC9;
  
  /** Command name. */
  public static final String NAME = "INSTALL [for install]";
  
  /** 
   * Constructs an INSTALL[for install] APDU command with the default <tt>P1</tt>
   * parameter and the specified data. <tt>P2</tt> and <tt>Le</tt> are set to 0.
   * 
   * @param packageAID the byte array containing the package AID identifying the
   *                    package to which the applet to be instanciated belongs.
   * @param appletAID the byte array containing the applet AID identifying 
   *                      applet to be instantiated.
   * @param instanceAID the byte array containing the instance AID identifying 
   *                       the instance to be installed.
   * @param privileges the privileges granted to this instance .
   * @param params the byte array containing the installation 
   *               parameters to be passed to this instance.
   */
  public InstallForInstallAPDU(byte[] packageAID, 
                               byte[] appletAID, 
                               byte[] instanceAID, 
                               int privileges, 
                               byte[] params) {
    this(P1,packageAID,appletAID,instanceAID,privileges,params);
  }

  /** 
   * Constructs an INSTALL[for install] APDU command with the specified <tt>P1</tt>
   * parameter and the specified data. <tt>P2</tt> and <tt>Le</tt> are set to 0.
   * 
   * @param p1 the parameter byte <tt>P1</tt>.
   * @param packageAID the byte array containing the package AID identifying the
   *                    package to which the applet to be instanciated belongs.
   * @param appletAID the byte array containing the applet AID identifying 
   *                      applet to be instantiated.
   * @param instanceAID the byte array containing the instance AID identifying 
   *                       the instance to be installed.
   * @param privileges the privileges granted to this instance.
   * @param params the byte array containing the installation 
   *                          parameters to be passed to this instance.
   */
  public InstallForInstallAPDU(int p1, 
                               byte[] packageAID, 
                               byte[] appletAID, 
                               byte[] instanceAID, 
                               int privileges, 
                               byte[] params) {
    super(CLA,INS,p1,P2,0);
    int length = 9;
    if (packageAID != null) 
      length += packageAID.length;
    if (appletAID != null) 
      length += appletAID.length;
    if (instanceAID != null) 
      length += instanceAID.length;
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
    offset++;

    // sets the applet AID
    if ((appletAID != null) && (appletAID.length != 0)) {
      data[offset] = (byte)appletAID.length;
      System.arraycopy(appletAID,0,data,offset+1,data[offset]&0xFF);
      offset += data[offset]&0xFF;
    }
    offset++;

    // sets the instance AID
    if ((appletAID != null) && (appletAID.length != 0)) {
      data[offset] = (byte)appletAID.length;
      System.arraycopy(appletAID,0,data,offset+1,data[offset]&0xFF);
      offset += data[offset]&0xFF;
    }
    offset++;

    // sets the instance privileges
    data[offset++] = 1;
    data[offset++] = (byte)privileges;

    // sets the parameters
    if (params == null)
      params = new byte[0];
    data[offset++] = (byte)(params.length + 2);
    data[offset++] = APP_PARAMS;
    data[offset] = (byte)params.length;
    System.arraycopy(params,0,data,offset+1,data[offset]&0xFF);
    
    setData(data);
    setName(NAME);
  }

} 
