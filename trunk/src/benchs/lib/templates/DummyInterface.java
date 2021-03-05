package benchs.lib.templates;

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
 * An interface used to compute overheads of API methods.
 */
public interface DummyInterface {
	
	//=========================================================================
	// Instance methods
	//=========================================================================
	
	/** A method without parameter that does nothing. */
	public void dummyMethodVoid();
	
	/** 
	 * A method without parameter that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 */
	public void dummyMethodShort();
	
	/** 
	 * A method with parameters that does nothing.
	 * 
	 * @param array a byte array.
	 * @param s a short. It could for example be an offset into <tt>array</tt>.
	 */
	public void dummyMethodVoid(byte[] array, short s);
	
	/** 
	 * A method with parameters that does nothing.
	 * This method is used when computing the overhead for API methods with a 
	 * return type.
	 * 
	 * @param array a byte array.
	 * @param s a short. It could for example be an offset into <tt>array</tt>.
	 */
	public void dummyMethodShort(byte[] array, short s);
	
	/** 
	 * A method with parameters that does nothing.
	 * 
	 * @param array a byte array.
	 * @param s1 a short. It could for example be an offset into <tt>array</tt>.
	 * @param s2 a short. It could for example be a length.
	 */
	public void dummyMethodVoid(byte[] array, short s1, short s2);
}