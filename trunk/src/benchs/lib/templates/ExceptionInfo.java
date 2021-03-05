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
 * $HeadURL: svn+ssh://cpascal@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 41 $
 * $LastChangedDate: 2006-10-10 16:57:32 +0200 (mar., 10 oct. 2006) $
 * $LastChangedBy: cpascal $
 */

import javacard.framework.APDUException;
import javacard.framework.CardException;
import javacard.framework.CardRuntimeException;
import javacard.framework.ISOException;
import javacard.framework.PINException;
import javacard.framework.SystemException;
import javacard.framework.TransactionException;
import javacard.framework.UserException;
import javacard.security.CryptoException;

/**
 * This class represents a Java Card exception.
 */
public class ExceptionInfo
{ 
  /** The type to be used for <tt>java.lang.Throwable</tt> exceptions. */
  public final static byte JAVA_LANG_THROWABLE = 0x00;
  
  /** 
   * The type to be used for <tt>java.lang.ArithmeticException</tt> 
   * exceptions. 
   */
  public final static byte JAVA_LANG_ARITHMETICEXCEPTION = 0x01;
  
  /** 
   * The type to be used for <tt>java.lang.ArrayIndexOutOfBoundsException</tt> 
   * exceptions. 
   */
  public final static byte JAVA_LANG_ARRAYINDEXOUTOFBOUNDSEXCEPTION = 0x02;
  
  /** 
   * The type to be used for <tt>java.lang.ArrayStoreException</tt>
   * exceptions. 
   */
  public final static byte JAVA_LANG_ARRAYSTOREEXCEPTION = 0x03;
  
  /** 
   * The type to be used for <tt>java.lang.ClassCastException</tt> exceptions. 
   */
  public final static byte JAVA_LANG_CLASSCASTEXCEPTION = 0x04;
  
  /** The type to be used for <tt>java.lang.Exception</tt> exceptions. */
  public final static byte JAVA_LANG_EXCEPTION = 0x05;
  
  /** 
   * The type to be used for <tt>java.lang.IndexOutOfBoundsException</tt>
   * exceptions. 
   */
  public final static byte JAVA_LANG_INDEXOUTOFBOUNDSEXCEPTION = 0x06;
  
  /** 
   * The type to be used for <tt>java.lang.NegativeArraySizeException</tt>
   *  exceptions.
   */
  public final static byte JAVA_LANG_NEGATIVEARRAYSIZEEXCEPTION = 0x07;
  
  /** 
   * The type to be used for <tt>java.lang.NullPointerException</tt>
   * exceptions. 
   */
  public final static byte JAVA_LANG_NULLPOINTEREXCEPTION = 0x08;
  
  /** 
   * The type to be used for <tt>java.lang.RuntimeException</tt> exceptions. 
   */
  public final static byte JAVA_LANG_RUNTIMEEXCEPTION = 0x09;
  
  /** 
   * The type to be used for <tt>java.lang.SecurityException</tt> exceptions. 
   */
  public final static byte JAVA_LANG_SECURITYEXCEPTION = 0x0A;
  
  /** The type to be used for <tt>java.io.IOException</tt> Java Card 
   *  exceptions. */
  public final static byte JAVA_IO_IOEXCEPTION = 0x0B;

  /** The type to be used for <tt>java.rmi.RemoteException</tt> Java Card 
   *  exceptions. */
  public final static byte JAVA_RMI_REMOTEEXCEPTION = 0x0C;
  
  /** 
   * The type to be used for <tt>javacard.framework.APDUException</tt> 
   * exceptions. 
   */
  public final static byte JAVACARD_FRAMEWORK_APDUEXCEPTION = 0x20;
  
  /** 
   * The type to be used for <tt>javacard.framework.CardException</tt>
   * exceptions. 
   */
  public final static byte JAVACARD_FRAMEWORK_CARDEXCEPTION = 0x21;
  
  /** 
   * The type to be used for <tt>javacard.framework.CardRuntimeException</tt> 
   * exceptions. 
   */
  public final static byte JAVACARD_FRAMEWORK_CARDRUNTIMEEXCEPTION = 0x22;
  
  /** 
   * The type to be used for <tt>javacard.framework.ISOException</tt> 
   * exceptions. 
   */
  public final static byte JAVACARD_FRAMEWORK_ISOEXCEPTION = 0x23;
  
  /** 
   * The type to be used for <tt>javacard.framework.PINException</tt> 
   * exceptions. 
   */
  public final static byte JAVACARD_FRAMEWORK_PINEXCEPTION = 0x24;
  
  /** 
   * The type to be used for <tt>javacard.framework.SystemException</tt>
   * exceptions. 
   */
  public final static byte JAVACARD_FRAMEWORK_SYSTEMEXCEPTION = 0x25;
  
  /** 
   * The type to be used for <tt>javacard.framework.TransactionException</tt> 
   * exceptions. 
   */
  public final static byte JAVACARD_FRAMEWORK_TRANSACTIONEXCEPTION = 0x26;
  
  /** 
   * The type to be used for <tt>javacard.framework.UserException</tt>
   * Java Card exceptions. 
   */
  public final static byte JAVACARD_FRAMEWORK_USEREXCEPTION = 0x27;
  
  /** 
   * The type to be used for <tt>javacard.security.CryptoException</tt> 
   * exceptions. 
   */
  public final static byte JAVACARD_SECURITY_CRYPTOEXCEPTION = 0x30;

  /** 
   * The type to be used for <tt>javacard.framework.service.ServiceException</tt>
   * exceptions. 
   */
  public final static byte JAVACARD_FRAMEWORK_SERVICE_SERVICEEXCEPTION = 0x40;
  
  /** The exception type. */
  private byte exception_type;
  
  /** 
   * The Java Card exception reason code, or 0 for a <tt>java.lang</tt>,
   * <tt>java.rmi</tt> or <tt>java.io</tt> exception. 
   */
  private short reason;
  
  /**
   * Writes the exception information.
   * 
   * @param writer the writer to use.
   **/
  public void write(ByteArrayWriter writer) {
    writer.writeByte(exception_type);
    writer.writeShort(reason);
  }
  
  /** 
   * Sets the type and the reason of the exception.
   * The type will be the type of the exception specified as parameter and the
   * reason will be the reason of the exception specified as parameter or 0 
   * for a <tt>java.lang</tt>, <tt>java.rmi</tt> or <tt>java.io</tt> exception. 
   * 
   * @param exception the exception to be used to update the type and the reason
   *        of this exception information.
   */
  public void setException(Throwable exception) {
	reason = 0;
	if (exception instanceof TransactionException) {
	  exception_type = JAVACARD_FRAMEWORK_TRANSACTIONEXCEPTION;
	  reason = ((TransactionException) exception).getReason();
	}
	else if (exception instanceof SystemException) {
	  exception_type = JAVACARD_FRAMEWORK_SYSTEMEXCEPTION;
	  reason = ((SystemException) exception).getReason();
	}
	else if (exception instanceof PINException) {
	  exception_type = JAVACARD_FRAMEWORK_PINEXCEPTION;
	  reason = ((PINException) exception).getReason();
	}
	else if (exception instanceof ISOException) {
	  exception_type = JAVACARD_FRAMEWORK_ISOEXCEPTION;
	  reason = ((ISOException) exception).getReason();
	}
	else if (exception instanceof CryptoException) {
	  exception_type = JAVACARD_SECURITY_CRYPTOEXCEPTION;
	  reason = ((CryptoException) exception).getReason();
	}
	else if (exception instanceof APDUException) {
	  exception_type = JAVACARD_FRAMEWORK_APDUEXCEPTION;
	  reason = ((APDUException) exception).getReason();
	}
	else if (exception instanceof CardRuntimeException) {
	  exception_type = JAVACARD_FRAMEWORK_CARDRUNTIMEEXCEPTION;
	  reason = ((CardRuntimeException) exception).getReason();
	}
	else if (exception instanceof SecurityException)
	  exception_type = JAVA_LANG_SECURITYEXCEPTION;
	else if (exception instanceof NullPointerException)
	  exception_type = JAVA_LANG_NULLPOINTEREXCEPTION;
	else if (exception instanceof NegativeArraySizeException)
	  exception_type = JAVA_LANG_NEGATIVEARRAYSIZEEXCEPTION;
	else if (exception instanceof ArrayIndexOutOfBoundsException)
	  exception_type = JAVA_LANG_ARRAYINDEXOUTOFBOUNDSEXCEPTION;
	else if (exception instanceof IndexOutOfBoundsException)
	  exception_type = JAVA_LANG_INDEXOUTOFBOUNDSEXCEPTION;
	else if (exception instanceof ClassCastException)
	  exception_type = JAVA_LANG_CLASSCASTEXCEPTION;
	else if (exception instanceof ArrayStoreException)
	  exception_type = JAVA_LANG_ARRAYSTOREEXCEPTION;
	else if (exception instanceof ArithmeticException)
	  exception_type = JAVA_LANG_ARITHMETICEXCEPTION;
	else if (exception instanceof RuntimeException)
	  exception_type = JAVA_LANG_RUNTIMEEXCEPTION;
	else if (exception instanceof UserException) {
	  exception_type = JAVACARD_FRAMEWORK_USEREXCEPTION;
	  reason = ((UserException) exception).getReason();
	}
	else if (exception instanceof CardException) {
	  exception_type = JAVACARD_FRAMEWORK_CARDEXCEPTION;
	  reason = ((CardException) exception).getReason();
	}
	else if (exception instanceof Exception)
	  exception_type = JAVA_LANG_EXCEPTION;
	else
	  exception_type = JAVA_LANG_THROWABLE;
  } 
  
  /**
   * Returns the size of the exception information.
   * 
   * @return the size in bytes of the exception information.
   */
  public static short getSize() {
    return (short)3;
  }
}
