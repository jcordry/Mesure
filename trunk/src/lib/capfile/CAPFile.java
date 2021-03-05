package lib.capfile;

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
 * $HeadURL: https://scm.gforge.inria.fr/svn/mesureprv/HEAD/src/lib/capfile/CAPFile.java $
 * Created: 1 septembre 2006
 * Author: TL 
 * $LastChangedRevision: 28 $
 * $LastChangedDate: 2006-09-13 15:44:36 +0200 (mer., 13 sept. 2006) $
 * $LastChangedBy: cpascal $
 */

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class CAPFile {

  /** The file name.*/
  private String fileName;
  
  /** The CAP file size. */
  private int size;
  
  /** The CAP file components. */
  private Component[] components;
	
  /**
   * Constructs a CAP file object.
   *
   * @param fileName the name of the file from which the CAP file will
   *        eventually be read.
   */
   public CAPFile(String fileName) {
     this.fileName = fileName;
     size = 0;
     components = new Component[256];
   }
	
   /**
    * Reads a CAP file representation from a jar file.
    *
    * @throw <tt>CAPException</tt> for any problem while reading the CAP file.
    **/
   public void read() throws CAPException
   {     
	 try {
	   ZipFile zipFile = new ZipFile(fileName);
       for(Enumeration jarEnum = zipFile.entries(); jarEnum.hasMoreElements();) { 
         ZipEntry zipEntry = (ZipEntry) jarEnum.nextElement();
         String name = zipEntry.getName();
         if(!name.endsWith(".cap") )
           continue;
         Component component = Component.read(
             new DataInputStream(zipFile.getInputStream(zipEntry)));
         component.setName(name.substring(name.lastIndexOf("/") + 1));
         components[component.getTag()] = component;
         size += component.getSize() + 3;
       }
	 } catch (IOException e) {
	   throw new CAPException(e);
	 }
   }
     
  /**
   * Returns the package AID.
   * 
   * @return the package AID.
   */
  public byte[] getPackageAID() {
    return ((HeaderComponent) components[Component.COMPONENT_Header]).getAID();
  }

  /**
   * Returns the applet AIDs. 
   * 
   * @return a byte array containing the applet AIDs.
   */
   public byte[][] getAppletAIDs() {
	 AppletComponent component = 
	     ((AppletComponent) components[Component.COMPONENT_Applet]);
	 if (component == null)
	   return new byte[0][0];
	 AppletInfo[] applets = component.getApplets();
	 byte[][] AIDs = new byte[applets.length][];
	 for (int i = 0; i < applets.length; i++) {
	   byte[] AID = applets[i].getAID();
	   AIDs[i] = new byte[AID.length];
	   for (int j = 0; j < AID.length; j++)
	     AIDs[i][j] = AID[j];
	 }
     return AIDs;
   }
   
   /**
    * Return the imported AIDs.
    * 
    * @return a byte array containing the AIDs of the imported packages.
    */
   public byte[][] getImportedAIDs() {
     ImportComponent component = 
	    ((ImportComponent) components[Component.COMPONENT_Import]);
	 PackageInfo[] imported = component.getPackages();
	 byte[][] AIDs = new byte[imported.length][];
	 for (int i = 0; i < imported.length; i++) {
	   byte[] AID = imported[i].getAID();
	   AIDs[i] = new byte[AID.length];
	   for (int j = 0; j < AID.length; j++)
	     AIDs[i][j] = AID[j];
	 }
	 return AIDs;
   }

   /**
    * Returns the static image size.
    * 
    * @return the static image size.
    */
   public int getStaticImageSize() {
     return ((StaticFieldComponent) components[Component.COMPONENT_StaticField]).getImageSize(); 
   }
   
   /**
    * Returns the cap file size.
    * 
    * @return the cap file size.
    */
   public int getSize() {
     return size;
   }
   
   /**
    * Returns the components.
    * 
    * @return the components.
    */
   public Component[] getComponents() {
     return components;
   }
   
}
