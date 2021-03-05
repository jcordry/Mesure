package scripts.jcapi.javacardx_crypto.cipher.dofinal.rsa_pkcs1_decrypt_1024;

import javacard.security.KeyBuilder;
import javacardx.crypto.Cipher;
import lib.cad.CADException;

/*
 * Copyright (c) 2006-2007 Mesure Project
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

/**
 * The purpose of this test is to measure the performances of the 
 * <tt>Cipher.dofinal</tt> method on <tt>ALG_RSA_XXX</tt>
 * cipher.
 */
public class DoFinalScript extends scripts.jcapi.javacardx_crypto.cipher.dofinal.DoFinalScript{
	
	private final static byte[] ALGOS = {
		Cipher.ALG_RSA_PKCS1};
	private final static byte[] KEY_TYPES = {
		KeyBuilder.TYPE_RSA_CRT_PRIVATE, KeyBuilder.TYPE_RSA_PRIVATE};
	private final static short[] KEY_LENGTHS = {
	    KeyBuilder.LENGTH_RSA_1024};
	private final static byte[] MODES = {
	    Cipher.MODE_DECRYPT
	};
	
	public DoFinalScript() throws CADException {
		super(ALGOS,KEY_TYPES,KEY_LENGTHS,MODES);
	}
	
}
