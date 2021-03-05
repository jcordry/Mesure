package lib.log;

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
 * $HeadURL: svn+ssh://meunier@scm.gforge.inria.fr/svn/mesureprv/src/lib/cad/JSR268CAD.java $
 * Created:  1 septembre 2006
 * Author: POPS 
 * $LastChangedRevision: 23 $
 * $LastChangedDate: 2006-09-08 13:44:52 +0200 (Fri, 08 Sep 2006) $
 * $LastChangedBy: meunier $
 */

/**
 * Use this class to check the Log services
 */

public class Check {

	public final static int CHECKLOG = Log.getASpecificLogEntry();
	public final static int LOG2 = Log.getASpecificLogEntry(10);
	public final static int LOG3 = Log.getASpecificLogEntry(2,"[LOG3]");
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		assert args.length == 0 : "You must call Check without any arguments";
		Log.out.println("Check fonctionnalities of the log package.");
		Log.out.println("Step 1 : check Log.out");
		Log.out.print("string 0 ");
		Log.out.print(false);
		Log.out.print((byte)2);
		Log.out.print((short)3);
		Log.out.print((char)'D');
		Log.out.print((int)5);
		Log.out.print((float)6.0);
		Log.out.print((long)7);
		Log.out.print((double)8.0);
		Log.out.println("String ln");
		Log.out.enter();
		Log.out.println("an Entred String");
		Log.out.println(false);
		Log.out.println((byte)2);
		Log.out.println((short)3);
		Log.out.println((char)'D');
		Log.out.println((int)5);
		Log.out.println((float)6.0);
		Log.out.println((long)7);
		Log.out.println((double)8.0);
		Log.out.exit();
		Log.out.println("an Exited String");
		Log.out.enter();
		Log.out.println("another Entred String");
		Log.out.enter(2);
		Log.out.println("a deeper String");
		Log.out.println(false);
		Log.out.println((byte)2);
		Log.out.println((short)3);
		Log.out.println((char)'D');
		Log.out.println((int)5);
		Log.out.println((float)6.0);
		Log.out.println((long)7);
		Log.out.println((double)8.0);
		Log.out.exit(2);
		Log.out.println("exit(2) String");
		Log.out.exit();
		Log.out.println("back to the begin String");
		Log.out.println("Step 2 : check Log.log[CHECKLOG]");
		Log.log[CHECKLOG].setHeader("[CHECKLOG]");
		Log.log[CHECKLOG].print(1,"string 0 ");
		Log.log[CHECKLOG].print(2,false);
		Log.log[CHECKLOG].print(3,(byte)2);
		Log.log[CHECKLOG].print(4,(short)3);
		Log.log[CHECKLOG].print(5,(char)'D');
		Log.log[CHECKLOG].print(6,(int)5);
		Log.log[CHECKLOG].print(7,(float)6.0);
		Log.log[CHECKLOG].print(8,(long)7);
		Log.log[CHECKLOG].print(9,(double)8.0);
		Log.log[CHECKLOG].println(10,"String ln");
		Log.log[CHECKLOG].enter();
		Log.log[CHECKLOG].println(11,"an Entred String");
		Log.log[CHECKLOG].println(12,false);
		Log.log[CHECKLOG].println(13,(byte)2);
		Log.log[CHECKLOG].println(14,(short)3);
		Log.log[CHECKLOG].println(15,(char)'D');
		Log.log[CHECKLOG].println(16,(int)5);
		Log.log[CHECKLOG].println(17,(float)6.0);
		Log.log[CHECKLOG].println(18,(long)7);
		Log.log[CHECKLOG].println(19,(double)8.0);
		Log.log[CHECKLOG].exit();
		Log.log[CHECKLOG].println(20,"an Exited String");
		Log.log[CHECKLOG].enter();
		Log.log[CHECKLOG].println(21,"another Entred String");
		Log.log[CHECKLOG].enter(2);
		Log.log[CHECKLOG].println(22,"a deeper String");
		Log.log[CHECKLOG].println(23,false);
		Log.log[CHECKLOG].println(24,(byte)2);
		Log.log[CHECKLOG].println(25,(short)3);
		Log.log[CHECKLOG].println(26,(char)'D');
		Log.log[CHECKLOG].println(27,(int)5);
		Log.log[CHECKLOG].println(28,(float)6.0);
		Log.log[CHECKLOG].println(29,(long)7);
		Log.log[CHECKLOG].println(30,(double)8.0);
		Log.log[CHECKLOG].exit(2);
		Log.log[CHECKLOG].println(31,"exit(2) String");
		Log.log[CHECKLOG].exit();
		Log.log[CHECKLOG].println(32,"back to the begin String");
		Log.out.println("Step 3 : check filter");
		Log.log[LOG2].print(1,"string 0 ");
		Log.log[LOG2].print(2,false);
		Log.log[LOG2].print(3,(byte)2);
		Log.log[LOG2].print(4,(short)3);
		Log.log[LOG2].print(5,(char)'D');
		Log.log[LOG2].print(6,(int)5);
		Log.log[LOG2].print(7,(float)6.0);
		Log.log[LOG2].print(8,(long)7);
		Log.log[LOG2].print(9,(double)8.0);
		Log.log[LOG2].println(10,"String ln");
		Log.log[LOG2].enter();
		Log.log[LOG2].println(11,"an Entred String");
		Log.log[LOG2].println(12,false);
		Log.log[LOG2].println(13,(byte)2);
		Log.log[LOG2].println(14,(short)3);
		Log.log[LOG2].println(15,(char)'D');
		Log.log[LOG2].println(16,(int)5);
		Log.log[LOG2].println(17,(float)6.0);
		Log.log[LOG2].println(18,(long)7);
		Log.log[LOG2].println(19,(double)8.0);
		Log.log[LOG2].exit();
		Log.log[LOG2].println(20,"an Exited String");
		Log.log[LOG2].enter();
		Log.log[LOG2].println(21,"another Entred String");
		Log.log[LOG2].enter(2);
		Log.log[LOG2].println(22,"a deeper String");
		Log.log[LOG2].println(23,false);
		Log.log[LOG2].println(24,(byte)2);
		Log.log[LOG2].println(25,(short)3);
		Log.log[LOG2].println(26,(char)'D');
		Log.log[LOG2].println(27,(int)5);
		Log.log[LOG2].println(28,(float)6.0);
		Log.log[LOG2].println(29,(long)7);
		Log.log[LOG2].println(30,(double)8.0);
		Log.log[LOG2].exit(2);
		Log.log[LOG2].println(31,"exit(2) String");
		Log.log[LOG2].exit();
		Log.log[LOG2].println(32,"back to the begin String");
		Log.out.println("Step 4 : check enterleaving");
		Log.log[LOG2].setHeader("[LOG2]");
		Log.log[LOG3].print(1,"string 0 ");
		Log.log[LOG3].print(2,false);
		Log.log[LOG2].print(3,(byte)2);
		Log.log[LOG2].print(4,(short)3);
		Log.log[LOG3].print(5,(char)'D');
		Log.log[LOG3].print(6,(int)5);
		Log.log[LOG2].print(7,(float)6.0);
		Log.log[LOG2].print(8,(long)7);
		Log.log[LOG3].print(9,(double)8.0);
		Log.log[LOG2].println(10,"String ln");
		Log.log[LOG2].enter();
		Log.log[LOG3].enter();
		Log.log[LOG3].println(11,"an Entred String");
		Log.log[LOG2].println(12,false);
		Log.log[LOG3].println(13,(byte)2);
		Log.log[LOG2].println(14,(short)3);
		Log.log[LOG3].println(15,(char)'D');
		Log.log[LOG2].println(16,(int)5);
		Log.log[LOG3].println(17,(float)6.0);
		Log.log[LOG2].println(18,(long)7);
		Log.log[LOG3].println(19,(double)8.0);
		Log.log[LOG2].exit();
		Log.log[LOG3].exit();
		Log.log[LOG2].println(20,"an Exited String");
		Log.log[LOG3].enter();
		Log.log[LOG3].println(21,"another Entred String");
		Log.log[LOG2].enter(2);
		Log.log[LOG2].println(22,"a deeper String");
		Log.log[LOG3].println(23,false);
		Log.log[LOG2].println(24,(byte)2);
		Log.log[LOG3].println(25,(short)3);
		Log.log[LOG2].println(26,(char)'D');
		Log.log[LOG3].println(27,(int)5);
		Log.log[LOG2].println(28,(float)6.0);
		Log.log[LOG3].println(29,(long)7);
		Log.log[LOG2].println(30,(double)8.0);
		Log.log[LOG2].exit(2);
		Log.log[LOG2].println(31,"exit(2) String");
		Log.log[LOG3].exit();
		Log.log[LOG3].println(32,"back to the begin String");
		Log.out.println("check completed");

	}

}
