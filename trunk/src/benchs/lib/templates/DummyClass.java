package benchs.lib.templates;

import javacard.security.Key;

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
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 25 janvier 2007
 * Author: TL 
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

/**
 * A class used to compute overheads of API methods.
 */
public class DummyClass implements DummyInterface {
	
	//=========================================================================
	// Instance methods
	//=========================================================================
	
	/** A method without parameter that does nothing. */
	public void dummyMethodVoid() {}
	
	/** 
	 * A method without parameter that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 */
	public void dummyMethodShort() {}
	
	/** 
	 * A method with parameters that does nothing.
	 * 
	 * @param array a byte array.
	 * @param s a short. It could for example be an offset into <tt>array</tt>.
	 */
	public void dummyMethodVoid(byte[] array, short s) {}
	
	/** 
	 * A method with parameters that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 * 
	 * @param array a byte array.
	 * @param s a short. It could for example be an offset into <tt>array</tt>.
	 */
	public void dummyMethodShort(byte[] array, short s) {}
	
	/** 
	 * A method with parameters that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 * 
	 * @param array a byte array.
	 * @param s a short. It could for example be an offset into <tt>array</tt>.
	 * @param b a byte. It could for example be a length.
	 * @return a short.
	 */
	public void dummyMethodShort(byte[] array, short s, byte b) {}
	
	/** 
	 * A method with parameters that does nothing.
	 * 
	 * @param array a byte array.
	 * @param s1 a short. It could for example be an offset into <tt>array</tt>.
	 * @param s2 a short. It could for example be a length.
	 */
	public void dummyMethodVoid(byte[] array, short s1, short s2) {}
	
	/**
	 * A method without parameters that does nothing.
	 * 
	 * @param key a key.
	 * @param b a byte.
	 */
	public void dummyMethodVoid(Key key, byte b) {}
	
	/**
	 * A method without parameters that does nothing.
	 * 
	 * @param key a key.
	 * @param b a byte.
	 * @param array a byte array.
	 * @param s1 a short. It could for example be an offset into <tt>array</tt>.
	 * @param s2 a short. It could for example be a length.
	 */
	public void dummyMethodVoid(Key key, byte b, byte[] array, short s1, short s2) {}
	
	/** 
	 * A method with parameters that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 * 
	 * @param array1 a source byte array.
	 * @param s1 a short. It could for example be an offset into <tt>array1</tt>.
	 * @param s2 a short. It could for example be a length.
	 * @param array2 a destination byte array.
	 * @param s3 a short. It could for example be an offset into <tt>array2</tt>.
	 */
	public void dummyMethodShort(byte[] array1, short s1, short s2, byte[] array2, short s3) {} 
	
	/** 
	 * A method with parameters that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 * 
	 * @param array1 a source byte array.
	 * @param s1 a short. It could for example be an offset into <tt>array1</tt>.
	 * @param s2 a short. It could for example be a length.
	 * @param array2 a destination byte array.
	 * @param s3 a short. It could for example be an offset into <tt>array2</tt>.
	 * @param s4 a short. It could for example be a length.
	 */
	public void dummyMethodShort(byte[] array1, short s1, short s2, byte[] array2, short s3, short s4) {}
	
	//=========================================================================
	// Static Methods
	//=========================================================================
	
	/** 
	 * A method with parameters that does nothing.
	 * 
	 * @param b1 a byte.
	 * @param b2 a byte.
	 */
	public static void dummyMethodStaticVoid(byte b1, byte b2) {}
	
	/** 
	 * A method with parameters that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 * 
	 * @param b1 a byte.
	 * @param b2 a byte.
	 */
	public static void dummyMethodStaticShort(byte b1, byte b2) {}
	
	/** 
	 * A method with parameters that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 * 
	 * @param array1 a source byte array.
	 * @param s1 a short. It could for example be an offset into <tt>array1</tt>.
	 * @param array2 a destination byte array.
	 * @param s2 a short. It could for example be an offset into <tt>array2</tt>/
	 * @param s3 a short. It could for example be a length.
	 */
	public static void dummyMethodStaticShort(byte[] array1, short s1, byte[] array2, short s2, short s3) {}
	
	/** 
	 * A method with parameters that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 * 
	 * @param array a byte array.
	 * @param s1 a short. It could for example be an offset into <tt>array</tt>.
	 * @param s2 a short. It could for example be a length.
	 */
	public static void dummyMethodStaticShort(byte[] array1, short s1, short s2) {}
	
	/** 
	 * A method with parameters that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 * 
	 * @param array a byte array.
	 * @param s1 a short. It could for example be an offset into <tt>array</tt>.
	 * @param s2 a short. It could for example be a length.
	 * @param b a byte. It could for example be a value.
	 */
	public static void dummyMethodStaticShort(byte[] array, short s1, short s2, byte b) {}
	
	/** 
	 * A method with parameters that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 * 
	 * @param array a byte array.
	 * @param s a short. It could for example be an offset into <tt>array</tt>.
	 */
	public static void dummyMethodStaticShort(byte[] array, short s) {}
	
	/** 
	 * A method with no parameter that does nothing.
	 * 
	 */
	public static void dummyMethodStaticVoid() {}
	
	/** 
	 * A method with no parameter that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 */
	public static void dummyMethodStaticShort() {}
}