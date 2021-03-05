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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/cad/GetStatusAPDU.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */


/**
 * This class represents the GlobalPlatform GET STATUS command.
 */
public class GetStatusAPDU extends CommandAPDU {

  /** Class byte (<tt>0x80</tt>). */
  public static final int CLA = 0x80;

  /** Instruction byte (<tt>0xF2</tt>). */
  public static final int INS = 0xF2;
	  
  /** P1 parameter for Issuer Security Domain only (<tt>0x80</tt>). */
  public static final int P1_ISD = 0x80;
  
  /** P1 parameter for Applications and Security Domains only (<tt>0x40</tt>). */
  public static final int P1_APP = 0x40;
  
  /** P1 parameter for Executable Load Files (<tt>0x20</tt>). */
  public static final int P1_EXEC = 0x20;
  
  /** 
   * P1 parameter for Executable Load Files and their Executable Modules only 
   * (<tt>0x10</tt>). 
   */
  public static final int P1_EXEC_MOD = 0x10;
  
  /** P2 parameter for first or all occurrences (<tt>0x00</tt>). */
  public static final int P2 = 0x00;
  
  /** P2 parameter for next occurrence(s) (<tt>0x01</tt>). */
  public static final int P2_NEXT = 0x01;
  
  /** Command name. */
  public static final String NAME = "GET STATUS";

  /**
   * Constructs a GET STATUS APDU command with the specified <tt>P1</tt> and 
   * <tt>P2</tt> parameters. 
   * 
   * @param p1 the parameter byte <tt>P1</tt>.
   * @param p2 the parameter byte <tt>P2</tt>. 
   */ 
  public GetStatusAPDU(int p1, int p2) {
    this(p1,p2,null);
  }

  /**
   * Constructs a GET STATUS APDU command with the specified <tt>P1</tt> and 
   * <tt>P2</tt> parameters and the specified search criteria. 
   * 
   * @param p1 the parameter byte <tt>P1</tt>.
   * @param p2 the parameter byte <tt>P2</tt>. 
   * @paral searchCriteria the search criteria.
   */ 
  public GetStatusAPDU(int p1, int p2, byte[] searchCriteria) {
    super(CLA,INS,p1,p2,0);
    if (searchCriteria == null)
      searchCriteria = new byte[0];
    byte[] data = new byte[2 + searchCriteria.length];
    data[0] = (byte)0x4F;
    data[1] = (byte)searchCriteria.length;
    System.arraycopy(searchCriteria,0,data,2,searchCriteria.length);
    setData(data);
    setName(NAME);
  }
}
