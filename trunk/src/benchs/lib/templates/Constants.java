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
 * $HeadURL: svn+ssh://corentinboe@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 46 $
 * $LastChangedDate: 2006-10-20 14:58:46 +0200 (ven., 20 oct. 2006) $
 * $LastChangedBy: meunier $
 */

/**
 * Some useful constants.
 */
public interface Constants {

    /** The default CLA value (0x80) used for the commands. */
    public final static byte MESURE_CLA = (byte) 0x80;
    
    /** The default INS value (0x10) used for set-up commands. */
    public final static byte SETUP_INS = (byte) 0x10;
    
    /** The default INS value (0x20) used for run commands. */
    public final static byte RUN_INS = (byte) 0x20;
    
    /** The default INS value (0x40) used for clean-up commands. */
    public final static byte CLEANUP_INS = (byte) 0x40;
    
    /** The default INS value (0x00) for CASE 1 APDU commands. */
    public final static byte CASE_1 = (byte) 0x00;
    
    /** The default INS value (0x02) for CASE 2 APDU commands. */
    public final static byte CASE_2  = (byte) 0x02;
    
    /** The default INS value (0x03) for CASE 3 APDU commands. */ 
    public final static byte CASE_3 = (byte) 0x04;
    
    /** The default INS value (0x04) for CASE 4 APDU commands. */
    public final static byte CASE_4 = (byte) 0x08;
}
