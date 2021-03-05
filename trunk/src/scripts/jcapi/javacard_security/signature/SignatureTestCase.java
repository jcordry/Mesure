package scripts.jcapi.javacard_security.signature;

import benchs.lib.templates.ByteArrayWriter;
import benchs.lib.templates.CryptoInfo;
import scripts.templates.TestCase;

/*
 * Copyright (c) 2007 Mesure Project
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
 * $HeadURL: svn+ssh://corentinboe@gforge.inria.fr/svn/mesureprv/src/benchs/jcapi/javacard_framework/util/applet/ArrayCopyTestApplet.java $
 * Created: 19 février 2007
 * Author: TL 
 * $LastChangedRevision: 1 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cboe $
 */

/**
 * A signature-related test case.
 */
public class SignatureTestCase extends TestCase {
	
  /**
   * Builds a new test case with the specified parameters.
   * The associated APDU command is a CASE 3 command.
   * 
   * @param name the test case name.
   * @param id the on-card test case identifier.
   * @param x the number of executions to be performed on-card for this 
   *          identified test case.
   * @param y the number of executions to be performed off-card for this
   *          identified test case.
   * @param algorithm the signature algorithm.
   * @param theMode the signature mode.
   * @param keyType the keyType.
   * @param keyLength the key length.
   * @param keyEncryption the key encryption.
   * @param inOffset the offset where to find the data to be signed.
   * @param inLength the length of the data to be signed.
   * @param sigOffset the offset where to store the signed data.
   */
  public SignatureTestCase(String name, int id, int x, int y,  
		  byte algorithm, byte theMode,
		  byte keyType, short keyLength, boolean keyEncryption,
		  short inOffset, short inLength, short sigOffset) {
	  super(name,id,x,y,0);
	  CryptoInfo info = new CryptoInfo(CryptoInfo.TYPE_SIGNATURE,algorithm,theMode,
	      keyType,keyLength,keyEncryption,inOffset,inLength,sigOffset,(short)0);
	  ByteArrayWriter writer = new ByteArrayWriter(CryptoInfo.getSize());
	  info.write(writer);
	  super.setData(writer.toByteArray());
  }
}