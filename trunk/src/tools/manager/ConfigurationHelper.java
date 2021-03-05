package tools.manager;

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
 * $HeadURL: svn+ssh://corentinboe@gforge.inria.fr/svn/mesureprv/src/benchs/engine/Manager.java $
 * Created: 21 février 2007
 * Author: TL 
 * $LastChangedRevision: 43 $
 * $LastChangedDate: 2006-10-16 17:17:37 +0200 (lun., 16 oct. 2006) $
 * $LastChangedBy: cpascal $
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import lib.cad.CAD;
import lib.cad.CADException;
import lib.cad.GPCAD;
import lib.cad.JSR268CAD;
import lib.cad.TimeCAD;
import lib.chrono.Chronometer;
import lib.loader.GPLoader;
import lib.log.Log;

/**
 * Useful class to configure the manager.
 */
public class ConfigurationHelper {

	/**
	 * Builds the CAD corresponding to the specified name.
	 * 
	 * @param cadName the name of the CAD to be built.
	 * @throws <tt>ConfigurationHelperException</tt> exception if a problem occurs
	 *         when building the CAD.
	 */
    public static CAD buildCad(String cadName) throws ManagerException {
    	CAD cad = null;
    	// Builds the CAD.
    	Log.log[Resources.getVerboseFilter()].println(4, "Builds the underlying CAD (" + cadName + ")...");
    	int sep = cadName.indexOf(":");
    	// Case 1: No class is specified. The default JSR268CAD class is instantiated.
    	if (sep < 0)
    	  cad = new JSR268CAD(cadName);
    	// Case 2: The class to be instantiated is specified.
    	else {
    	  try {
    		// Extracts the name of the class to be instantiated.
    	    String cadClassName = cadName.substring(0,sep);
    	    // Gets the class
    		Class cadClass = Class.forName(cadClassName);
    		// Checks the type of this class
    		if (!CAD.class.isAssignableFrom(cadClass))
    		  throw new ManagerException(
    		      cadClassName + " is not a subclass of " + CAD.class.getName());
    		// Gets the constructor of this class
    		Constructor constructor = cadClass.getConstructor(
    		    new Class[]{String.class});
    		// Extracts the parameters to be passed to this constructor
    		cadName = cadName.substring(sep+1).trim();
    		if (cadName.length() == 0)
    		  cadName = null;
    		Object[] params = new Object[]{cadName};
    		// Instantiates the class with the passed parameters
    		cad = (CAD)constructor.newInstance(params);
    	  } catch (ClassNotFoundException e) {
    	    throw new ManagerException(e);
    	  } catch (NoSuchMethodException e) {
    		throw new ManagerException(e);
    	  } catch (IllegalAccessException e) {
    		throw new ManagerException(e);
    	  } catch (InvocationTargetException e) {
    		throw new ManagerException(e);
    	  } catch (InstantiationException e) {
    		throw new ManagerException(e);
    	  }
    	}
    	return cad;
    }
    
    /**
     * Builds a <tt>GPCAD</tt> object from the specified CAD.
     * 
     * @param cad the underlying CAD.
     * @return the built <tt>GPCAD</tt> object.
     */
    public static GPCAD buildGPCad(CAD cad) {
    	Log.log[Resources.getVerboseFilter()].println(4, "Builds the GlobalPlatform CAD...");
    	GPCAD gpcad = new GPCAD(cad);

    	return gpcad;
    }
    

    /**
     * Builds a <tt>TimeCAD</tt> object from the specified CAD.
     * 
     * @param cad the underlying CAD.
     * @param chronoName the name of the chronometer to be used.
     * @return the built <tt>TimeCAD</tt> object.
     * @throws <tt>ConfigurationHelperException</tt> exception if an exception
     *         occurs when building the CAD.
     */
    public static TimeCAD buildTimeCad(CAD cad, String chronoName) throws ManagerException {
    	// Builds the time provider.
    	Log.log[Resources.getVerboseFilter()].println(4, "Gets the chronometer (" + chronoName + ")...");
    	TimeCAD timecad;
    	Chronometer chrono = null;
    	// Case 1: No chronometer is specified. 
    	if (chronoName == null) {
    	  // Case 1a: The CAD is able to perform time measures.
    	  if (cad instanceof Chronometer) {
    	    chrono = (Chronometer) cad;
    	    timecad = new TimeCAD(cad,chrono); 
    	  }
          // Case 1b: The default system chronometer is used.
    	  else
    	    timecad = new TimeCAD(cad);
    	}
    	// Case 2: The name of the chronometer to be used is specified.
    	else {
    	  try {
    		// Gets the class corresponding to this chronometer
    	    Class chronoClass = Class.forName(chronoName);
    	    // Checks the type of this class
    		if (!Chronometer.class.isAssignableFrom(chronoClass))
    		  throw new ManagerException(
    		      chronoName + " does not implement " + Chronometer.class.getName());
    		// Instantiates this class.
    		chrono = (Chronometer) chronoClass.newInstance();
    	  } catch (ClassNotFoundException e) {
    	    throw new ManagerException(e);
    	  } catch (IllegalAccessException e) {
    		throw new ManagerException(e);
    	  } catch (InstantiationException e) {
    		throw new ManagerException(e);
    	  }
    	  // Builds a TimeCAD using this chronometer
    	  timecad = new TimeCAD(cad,chrono);
    	}
    	return timecad;
    }
    
    /**
     * Builds a GlobalPlatform loader using the specified CAD and configuration 
     * file.
     * 
     * @param configFileName the name of the configuration file to be used.
     * @param gpcad the underlying CAD.
     * @return the built <tt>GPLoader</tt> object.
     * @throws <tt>ConfigurationHelperException</tt> exception if a problem 
     *         occurs when building the loader.
     */
    public static GPLoader buildGPLoader(String configFileName, GPCAD gpcad) throws ManagerException {
    	GPLoader gploader; 
    	// Builds the loader.
    	Log.log[Resources.getVerboseFilter()].println(4, "Builds the GlobalPlatform loader...");
    	try {
    		gploader = new GPLoader(configFileName, gpcad);
    	} catch (CADException e) {
    		throw new ManagerException(e);
    	}
    	return gploader;
    }
}
