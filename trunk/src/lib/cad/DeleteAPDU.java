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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/DeleteAPDU.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class represents the GlobalPlatform DELETE command.
 */
public class DeleteAPDU extends CommandAPDU {

  /** Class byte (<tt>0x80</tt>). */
  public static final int CLA = 0x80;

  /** Instruction byte (<tt>0xE4</tt>). */
  public static final int INS = 0xE4;

  /** Default P1 parameter (<tt>0x00</tt>). */
  public static final int P1 = 0x00;

  /** Default P2 parameter (<tt>0x00</tt>). */
  public static final int P2 = 0x00;
  
  /** Delete object and related object P2 parameter (<tt>0x80</tt>). */
  public static final int P2_ALL = 0x80;

  /** Executable Load File or Application AID Tag (<tt>0x4F</tt>). */
  public static final byte AID = 0x4F;
  
  /** Command name. */
  public static final String NAME = "DELETE";

  /** 
   * Constructs a DELETE APDU command with the specified <tt>P1</tt> and 
   * <tt>P2</tt> parameters and the specified data. <tt>Le</tt> is set to 1.
   * 
   * @param p2 the parameter byte <tt>P2</tt>.
   * @param aid the byte array containing the data bytes of the command body.
   */
  public DeleteAPDU(int p2, byte[] aid) {
    super(CLA,INS,P1,p2,1);
    byte[] data = new byte[2 + aid.length];
    data[0] = AID;
    data[1] = (byte)aid.length;
    System.arraycopy(aid,0,data,2,aid.length);
    setData(data);
    setName(NAME + " " + byteArrayToString(aid));
  }
  /** 
   * Constructs a DELETE APDU command with the default <tt>P1</tt> and 
   * <tt>P2</tt> parameters and the specified data. <tt>Le</tt> is set to 1.
   * 
   * @param aid the byte array containing the data bytes of the command body.
   */
  public DeleteAPDU(byte[] aid) {
    this(P2,aid);
  }
}
