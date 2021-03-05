package benchs.jcapi.javacard_security.rsaprivatecrtkey.setx;

import javacard.framework.JCSystem;
import javacard.framework.Util;
import javacard.security.KeyBuilder;
import benchs.lib.templates.KeyTemplateApplet;
import benchs.lib.templates.TestCase;

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
 * $HeadURL: $
 * Created: 14 mai 2007
 * Author: TL 
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

/**
 * The purpose of this test is to measure the performances of the 
 * <tt>RSAPrivateCrtKey.setX</tt> methods.
 */
public class SetXTestApplet extends KeyTemplateApplet {
  /** The buffer where to store the key data. */
  private byte[] data;
  
  /** The buffer where to store transient data. */
  private byte[] cod;
  
  /** The length of the key data. */
  private short length;
  
  /** 
   * @see javacard.framework.Applet#install(byte[], short, byte) 
   */
  public static void install(byte[] bArray, short bOffset, byte bLength) {
    new SetXTestApplet().register();
  }
  
  protected SetXTestApplet() {	
    cod = JCSystem.makeTransientByteArray((short)256,JCSystem.CLEAR_ON_DESELECT);
  }
  
  /**
   * @see TestCase#getTestCases()
   */
  public TestCase[] getTestCases() {
    return new TestCase[] {
      // Dummy case used to compute the overhead 	
      new TestCase() {
        public void run(byte[] apduBuffer) {
		  dummyInterface.dummyMethodVoid(data,(short)0,length);
		}
	  },	
    		
	  // Set the DP1 parameter
	  // from a persistent array
      new TestCase() {
        public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  switch (keyInfo.getKeyLength()) {
	        case KeyBuilder.LENGTH_RSA_1024:
	    	  data = rsa128P1;
	          break;
	        case KeyBuilder.LENGTH_RSA_2048:
	    	  data = rsa256P1;
	    	  break;
	        case KeyBuilder.LENGTH_RSA_512:
	    	  data = rsa64P1;
	    	  break;
	        case KeyBuilder.LENGTH_RSA_768:
	    	  data = rsa96P1;
	    	  break;
	        default:
	    	  break;
	      }
		  length = (short)data.length;
        }		
		public void run(byte[] apduBuffer) {
	      rsaPrivateCrtKey.setDP1(data,(short)0,length);
        }
	  },
	  
	  // Set the DP1 parameter
	  // from a transient array
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
	      init(apduBuffer);
		  switch (keyInfo.getKeyLength()) {
	        case KeyBuilder.LENGTH_RSA_1024:
	    	  data = rsa128P1;
	          break;
	        case KeyBuilder.LENGTH_RSA_2048:
	    	  data = rsa256P1;
	    	  break;
	        case KeyBuilder.LENGTH_RSA_512:
	    	  data = rsa64P1;
	    	  break;
	        case KeyBuilder.LENGTH_RSA_768:
	    	  data = rsa96P1;
	    	  break;
	        default:
	    	  break;
	      }	
		  length = (short)data.length;
	      Util.arrayCopyNonAtomic(data,(short)0,cod,(short)0,length);
	      data = cod;
	    }		
	    public void run(byte[] apduBuffer) {
	      rsaPrivateCrtKey.setDP1(data,(short)0,length);
	    }
	  },
	  
	  // Set the DQ1 parameter
	  // from a persistent array
      new TestCase() {
        public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  switch (keyInfo.getKeyLength()) {
		    case KeyBuilder.LENGTH_RSA_1024:
		      data = rsa128Q1;
		      break;
		    case KeyBuilder.LENGTH_RSA_2048:
		      data = rsa256Q1;
		      break;
		    case KeyBuilder.LENGTH_RSA_512:
		      data = rsa64Q1;
		      break;
		    case KeyBuilder.LENGTH_RSA_768:
		      data = rsa96Q1;
		      break;
		    default:
		      break;
		  }
		  length = (short)data.length;
        }		
		public void run(byte[] apduBuffer) {
	      rsaPrivateCrtKey.setDQ1(data,(short)0,length);
        }
	  },
	  
	  // Set the DQ1 parameter
	  // from a transient array
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
	      init(apduBuffer);
		  switch (keyInfo.getKeyLength()) {
		    case KeyBuilder.LENGTH_RSA_1024:
		      data = rsa128Q1;
		      break;
		    case KeyBuilder.LENGTH_RSA_2048:
		      data = rsa256Q1;
		      break;
		    case KeyBuilder.LENGTH_RSA_512:
		      data = rsa64Q1;
		      break;
		    case KeyBuilder.LENGTH_RSA_768:
		      data = rsa96Q1;
		      break;
		    default:
		      break;
		  }
		  length = (short)data.length;
	      Util.arrayCopyNonAtomic(data,(short)0,cod,(short)0,length);
	      data = cod;
	    }		
	    public void run(byte[] apduBuffer) {
	      rsaPrivateCrtKey.setDQ1(data,(short)0,length);
	    }
	  },
	  
	  // Set the P parameter
	  // from a persistent array
      new TestCase() {
        public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  switch (keyInfo.getKeyLength()) {
		    case KeyBuilder.LENGTH_RSA_1024:
		      data = rsa128P;
		      break;
		    case KeyBuilder.LENGTH_RSA_2048:
		      data = rsa256P;
		      break;
		    case KeyBuilder.LENGTH_RSA_512:
		      data = rsa64P;
		      break;
		    case KeyBuilder.LENGTH_RSA_768:
		      data = rsa96P;
		      break;
		    default:
		      break;
		  }
		  length = (short)data.length;
        }		
		public void run(byte[] apduBuffer) {
	      rsaPrivateCrtKey.setP(data,(short)0,length);
        }
	  },
	  
	  // Set the P parameter
	  // from a transient array
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
	      init(apduBuffer);
		  switch (keyInfo.getKeyLength()) {
		    case KeyBuilder.LENGTH_RSA_1024:
		      data = rsa128P;
		      break;
		    case KeyBuilder.LENGTH_RSA_2048:
		      data = rsa256P;
		      break;
		    case KeyBuilder.LENGTH_RSA_512:
		      data = rsa64P;
		      break;
		    case KeyBuilder.LENGTH_RSA_768:
		      data = rsa96P;
		      break;
		    default:
		      break;
		  }
		  length = (short)data.length;
	      Util.arrayCopyNonAtomic(data,(short)0,cod,(short)0,length);
	      data = cod;
	    }		
	    public void run(byte[] apduBuffer) {
	      rsaPrivateCrtKey.setP(data,(short)0,length);
	    }
	  },
	  
	  // Set the PQ parameter
	  // from a persistent array
      new TestCase() {
        public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  switch (keyInfo.getKeyLength()) {
		    case KeyBuilder.LENGTH_RSA_1024:
		      data = rsa128PQ;
		      break;
		    case KeyBuilder.LENGTH_RSA_2048:
		      data = rsa256PQ;
		      break;
		    case KeyBuilder.LENGTH_RSA_512:
		      data = rsa64PQ;
		      break;
		    case KeyBuilder.LENGTH_RSA_768:
		      data = rsa96PQ;
		      break;
		    default:
		      break;
		  }
		  length = (short)data.length;
        }		
		public void run(byte[] apduBuffer) {
	      rsaPrivateCrtKey.setPQ(data,(short)0,length);
        }
	  },
	  
	  // Set the PQ parameter
	  // from a transient array
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
	      init(apduBuffer);
		  switch (keyInfo.getKeyLength()) {
		    case KeyBuilder.LENGTH_RSA_1024:
		      data = rsa128PQ;
		      break;
		    case KeyBuilder.LENGTH_RSA_2048:
		      data = rsa256PQ;
		      break;
		    case KeyBuilder.LENGTH_RSA_512:
		      data = rsa64PQ;
		      break;
		    case KeyBuilder.LENGTH_RSA_768:
		      data = rsa96PQ;
		      break;
		    default:
		      break;
		  }
		  length = (short)data.length;
	      Util.arrayCopyNonAtomic(data,(short)0,cod,(short)0,length);
	      data = cod;
	    }		
	    public void run(byte[] apduBuffer) {
	      rsaPrivateCrtKey.setPQ(data,(short)0,length);
	    }
	  },
	  
	  // from a persistent array
      new TestCase() {
        public void setUp(byte[] apduBuffer) {
		  init(apduBuffer);
		  switch (keyInfo.getKeyLength()) {
		    case KeyBuilder.LENGTH_RSA_1024:
		      data = rsa128Q;
		      break;
		    case KeyBuilder.LENGTH_RSA_2048:
		      data = rsa256Q;
		      break;
		    case KeyBuilder.LENGTH_RSA_512:
		      data = rsa64Q;
		      break;
		    case KeyBuilder.LENGTH_RSA_768:
		      data = rsa96Q;
		      break;
		    default:
		      break;
		  }
		  length = (short)data.length;
        }		
		public void run(byte[] apduBuffer) {
	      rsaPrivateCrtKey.setQ(data,(short)0,length);
        }
	  },
	  
	  // Set the Q parameter
	  // from a transient array
	  new TestCase() {
	    public void setUp(byte[] apduBuffer) {
	      init(apduBuffer);
		  switch (keyInfo.getKeyLength()) {
		    case KeyBuilder.LENGTH_RSA_1024:
		      data = rsa128Q;
		      break;
		    case KeyBuilder.LENGTH_RSA_2048:
		      data = rsa256Q;
		      break;
		    case KeyBuilder.LENGTH_RSA_512:
		      data = rsa64Q;
		      break;
		    case KeyBuilder.LENGTH_RSA_768:
		      data = rsa96Q;
		      break;
		    default:
		      break;
		  }
		  length = (short)data.length;
	      Util.arrayCopyNonAtomic(data,(short)0,cod,(short)0,length);
	      data = cod;
	    }		
	    public void run(byte[] apduBuffer) {
	      rsaPrivateCrtKey.setQ(data,(short)0,length);
	    }
	  }
    };
  }
}