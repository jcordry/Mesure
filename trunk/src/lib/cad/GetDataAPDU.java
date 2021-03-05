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
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/lib/cad/GetStatusAPDU.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

/**
 * This class represents the GlobalPlatform GET DATA command.
 */
public class GetDataAPDU extends CommandAPDU {

  /** Class byte (<tt>0x80</tt>). */
  public static final int CLA = 0x80;

  /** Instruction byte (<tt>0xCA</tt>). */
  public static final int INS = 0xCA;
		
  /** P1-P2 parameters for Card Production Life Cycle (<tt>0x9F7F</tt>). */
  public static final int TAG_CARD_PRODUCTION_LIFE_CYCLE = 0x9F7F;	
	  
  /** Command name. */
  public static final String NAME = "GET DATA";

  /**
   * Constructs a GET STATUS APDU command with the specified <tt>P1-P2</tt> 
   * parameters. 
   * 
   * @param p1p2 the parameter bytes <tt>P1-P2</tt>.
   */ 
  public GetDataAPDU(int p1p2) {
    super(CLA, INS,(p1p2>>8)&0xFF,p1p2&0xFF,0);
    String name = NAME;
    switch (p1p2) {
      case TAG_CARD_PRODUCTION_LIFE_CYCLE:
        name = name + " [Card Production Life Cycle]";
        break;
      default:
    	break;
    }
    setName(name);
  }
}
