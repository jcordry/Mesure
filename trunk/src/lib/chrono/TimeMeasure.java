package lib.chrono;

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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/chrono/TimeMeasure.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 327 $
 * $LastChangedDate: 2007-07-27 13:18:13 +0200 (ven., 27 juil. 2007) $
 * $LastChangedBy: cpascal $
 */

/**
 * A measure performed via a <code>Chronometer</code>.
 */
public class TimeMeasure extends lib.xml.test_result.TimeMeasure implements Comparable {
	
  public static final int NANOSECONDS  		= 1; 
  public static final int MILLISECONDS  	= 2;
  public static final int MICROSECONDS		= 3;
  public static final int SECONDS  			= 4;

  private static final String NANOSECONDS_SHORT		= "ns";
  private static final String MILLISECONDS_SHORT 	= "ms";
  private static final String MICROSECONDS_SHORT	= "µs";
  private static final String SECONDS_SHORT  		= "s";
  
  /** The unit of measure. */
  private int unit;
  
  /** 1 second. */
  public static final TimeMeasure ONE_SECOND = new TimeMeasure(1,SECONDS);
  
  /**
   * Constructs a new <tt>TimeMeasure</tt> from the specified value and unit of
   * measure.
   * 
   * @param measure the measure
   * @param unit the unit of measure
   */
  public TimeMeasure(double measure, int unit) {
	setMeasure(measure);
    checkUnit(unit);
    this.unit = unit;
  }
  
  /**
   * Checks that the specified unit is among the supported units of measure.
   * 
   * @param unit the unit of measure
   * @throws <tt>IllegalArgumentException</tt> exception if the unit of measure
   *         is not supported.
   * @see TimeMeasure#MILLISECONDS
   * @see TimeMeasure#NANOSECONDS
   * @see TimeMeasure#SECONDS
   */
  private static void checkUnit(int unit) throws IllegalArgumentException {
    switch(unit) {
      case(MILLISECONDS):
      case(NANOSECONDS):
      case(MICROSECONDS):
      case (SECONDS):
        break;
      default:
        throw new IllegalArgumentException("Invalid unit " + unit);
    }
  }

  /**
   * Return the unit of measure.
   * 
   * @return the unit of measure.
   * @see TimeMeasure#MILLISECONDS
   * @see TimeMeasure#NANOSECONDS
   * @see TimeMeasure#SECONDS
   */  
  public int getUnit() {
    return unit;
  }
  
  /**
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(Object obj) {
	TimeMeasure other = (TimeMeasure) obj;
	if (unit == other.getUnit())
	  return (int) (getMeasure() - other.getMeasure());
	else {
	  TimeMeasure converted = other.convertTo(unit);
	  return (int) (getMeasure() - converted.getMeasure());
	}
  }
  
  /**
   * Convert this measure to another unit.
   * 
   * @param unit the new unit of measure.
   * @return the converted measure, as a new <tt>TimeMeasure</tt> object.
   */
  public TimeMeasure convertTo(int unit) {
    checkUnit(unit);
    if (this.unit == unit)
      return new TimeMeasure(getMeasure(),this.unit);
    switch(this.unit) {
      case NANOSECONDS:
        switch(unit) {
          case MILLISECONDS:
            return new TimeMeasure(getMeasure()/1000000,MILLISECONDS);
          case MICROSECONDS:
        	return new TimeMeasure(getMeasure()/1000,MICROSECONDS);
          case SECONDS:
        	return new TimeMeasure(getMeasure()/1000000000,SECONDS);
          default:
            throw new IllegalStateException("Internal error (unit " + unit + ")");
          }
      case MILLISECONDS:
        switch(unit) {
          case NANOSECONDS:
            return new TimeMeasure(getMeasure()*1000000,NANOSECONDS);
          case(MICROSECONDS):
            return new TimeMeasure(getMeasure()*1000,MICROSECONDS);
          case SECONDS:
            return new TimeMeasure(getMeasure()/1000,SECONDS);
          default:
            throw new IllegalStateException("Internal error (unit " + unit + ")");
        }
      case(MICROSECONDS):
        switch(unit) {
          case(NANOSECONDS):
        	return new TimeMeasure(getMeasure()*1000,NANOSECONDS);
          case(MILLISECONDS):
            return new TimeMeasure(getMeasure()/1000,MILLISECONDS);
          case(SECONDS):
            return new TimeMeasure(getMeasure()/1000000,SECONDS);
          default:
            throw new IllegalStateException("Internal error (unit " + unit + ")");
        }
      case SECONDS:
        switch (unit) {
          case NANOSECONDS:
            return new TimeMeasure(getMeasure()*1000000000,NANOSECONDS);
          case MICROSECONDS:
        	return new TimeMeasure(getMeasure()*1000000,MICROSECONDS);
          case MILLISECONDS:
            return new TimeMeasure(getMeasure()*1000,MILLISECONDS);
          default:
            throw new IllegalStateException("Internal error (unit " + unit + ")");
        }
      default:
        throw new IllegalArgumentException("Invalid unit " + unit);
    }
  }
  
  /**
   * Adds two mesures. 
   * The returned measure has the unit of this <tt>TimeMeasure</tt> object.
   * 
   * @param obj the measure to be added to this measure.
   * @return the built <tt>TimeMeasure</tt> object.
   */
  public TimeMeasure add(Object obj) {
	TimeMeasure other = (TimeMeasure) obj;
    TimeMeasure converter = other.convertTo(unit);
    return new TimeMeasure(getMeasure()+converter.getMeasure(),unit);
  }
  
  /**
   * Subtracts two measures.
   * The returned measure has the unit of this <tt>TimeMeasure</tt> object.
   * 
   * @param obj the measure to be subtracted to this measure.
   * @return the built <tt>TimeMeasure</tt> object.
   */
  public TimeMeasure subtract(Object obj) {
	TimeMeasure other = (TimeMeasure) obj;
    TimeMeasure converter = other.convertTo(unit);
	return new TimeMeasure(getMeasure()-converter.getMeasure(),unit);
  }
  
  /**
   * Divides this measure. 
   * 
   * @param divisor the divisor by which this measure has to be divided.
   * @return the built <tt>TimeMeasure</tt> object.
   */
  public TimeMeasure divide(double divisor) {
    return new TimeMeasure(getMeasure()/divisor,unit);
  }

  /**
   * Multiply this measure. 
   * 
   * @param multiplier the multiplier by which this measure has to be multiplied.
   * @return the built <tt>TimeMeasure</tt> object.
   */
  public TimeMeasure multiply(double multiplier) {
	    return new TimeMeasure(getMeasure()*multiplier,unit);
  }
  
  /**
   * Return the square root of this measure. 
   * 
   * @return the built <tt>TimeMeasure</tt> object.
   */
  public TimeMeasure sqrt() {
	  return new TimeMeasure(Math.sqrt(getMeasure()),unit);
  }
  
  /**
   * Computes the average of several measures.
   * 
   * @param measures the measures to be used to compute the average.
   * @return the built <tt>TimeMeasure</tt> object.
   */
  public static TimeMeasure average(Object[] measures) {
    TimeMeasure measure = (TimeMeasure) measures[0];
    for (int i = 1; i < measures.length; i++) {
      measure = measure.add(measures[i]);
    }
    return measure.divide((double)measures.length);
  }
  
  /**
   * Return the std error of several measures. 
   * 
   * @param measures the measures to be used to compute the std error.
   * @return the built <tt>TimeMeasure</tt> object.
   */
  public static TimeMeasure stdError(Object[] measures) {
	  return variance(measures).sqrt();
  }
  
  /**
   * Computes the variance of several measures.
   * 
   * @param measures the measures to be used to compute the variance.
   * @return the built <tt>TimeMeasure</tt> object.
   */
  public static TimeMeasure variance(Object[] measures) {
    TimeMeasure average = average(measures);
    TimeMeasure measure = null;
    for (int i = 0; i < measures.length; i++) {
      TimeMeasure m = ((TimeMeasure)measures[i]).subtract(average);
      m = new TimeMeasure(m.getMeasure()*m.getMeasure(),m.unit);
      if (measure == null)
        measure = m;
      else
      	measure = measure.add(m);
    }
    return measure.divide(measures.length); 
  }
 
  /**
   * Returns the maximum of several measures.
   * 
   * @param measures the measures to be searched for.
   * @return the index of the maximum measure.
   */
  public static int worstErrorIndex(Object[] measures) {
	    TimeMeasure average = average(measures);
	    TimeMeasure worstMeasure = (TimeMeasure)measures[0];
	    int worstIndex = 0;
	    
	    for (int i = 1; i < measures.length; i++) {
	      TimeMeasure m = ((TimeMeasure)measures[i]).subtract(average);
	      if (m.compareTo(worstMeasure) > 0) {
	    	  worstMeasure = m;
	    	  worstIndex = i;
	      }
	    }
	    return worstIndex; 
  }
  
  /**
   * @see java.lang.Object#toString()
   */
  public String toString() {
    String s;
	switch(unit) {  
      case NANOSECONDS:
        s = NANOSECONDS_SHORT;
        break;
      case MILLISECONDS:
        s = MILLISECONDS_SHORT;
        break;
      case MICROSECONDS:
    	s = MICROSECONDS_SHORT;
    	break;
      case SECONDS:
    	s = SECONDS_SHORT;
    	break;
      default:
        s = "Unknown unit " + unit;
        break;
    }
	s = Double.toString(getMeasure()) + " " + s;
    return s;
  }
}
