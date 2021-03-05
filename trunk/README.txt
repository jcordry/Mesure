                            MESURE

This is Mesure, an open Java Card performance measurement tool.
A developement version is available through SVN at 
https://scm.gforge.inria.fr/svn/mesure (no authentication is
required)


                         INSTALLATION

MESURE requires the use of Java 6 to run.

http://java.sun.com/javase/downloads/index.jsp

You will also need to download and install a Java Card Developement
Kit (JCDK), for instance the reference JCDK by SUN :
http://java.sun.com/products/javacard/

You will need to copy the api21.jar from the JCDK into the lib
directory of the Mesure project.


                         DOCUMENTATION

Each command that is part of the measure project answers to the
-help/-h option with the appropriate options available.

The doc directory in the archive includes a User Guide.

For more on the way things work take a look at the documentation on
the website :
http://mesure.gforge.inria.fr


                       WHAT WE DO/DON'T

We measure the performance of some Java Card platforms that use
GlobalPlatform. We do not measure the performance of SIM cards. We
measurethe performance of some Java Card features. The I/O are out
of scope.

See the Functionalities in the documentation section of the website
for more details on what we mean to do here.


                         RUNNING MESURE

Edit the config/card/CardConfig.xml file to fit the needs of the
card you want to test.

Edit the config/manager/ManagerConfig.xml file. Make sure you have
the correct name of your reader. Testing can take a while depending
on the card you want to test. Try to limit the number of tests to 
those you need.

Run a shell and cd into the bin directory of the project. Make sure
Java 6 is in your path.

Run in this order (you need to run the .bat if you are using
windows) :
$ ./calibrate.sh

$ ./manager.sh

$ ./filter.sh

$ ./extractor.sh

$ ./profiler.sh

Alternatively, you might want to run ant :
First, you might want to edit the bin/build.xml file to set the
java6.home variable to your java 6 home directory.
Then, run :
$ ant
The program automaticaly performs the different operations in the
right order.


                      COPYRIGHT AND LICENCE

All files Copyright 2006, 2007 and 2008 by the Mesure Project
except as noted below.

All files are under the CeCILL Licence V2, which may be found in
the file Licence_CeCILL_V2-en.txt, with the following exceptions.

* lib/castor-1.0.3.jar is published under the Apache licence 2
  which may be found in lib/License-Apache2_0.txt.
  Copyright 2004-2005 Werner Guttmann

* lib/commons-logging-1.1.jar is published under the Apache licence
  2 which may be found in lib/License-Apache2_0.txt.
  Copyright 2001-2004 The Apache Software Foundation

* lib/jcommon-1.0.9.jar is published under the GNU Library or Lesser
  General Public License (LGPL).
  Copyright 2000-2006, by Object Refinery Limited and Contributors.

* lib/jfreechart-1.0.5.jar is published under the GNU Library or
  Lesser General Public License (LGPL).
  Copyright 2000-2007, by Object Refinery Limited and Contributors.

* lib/xerces-J_1.4.0.jar is published under the Apache licence 2
  which may be found in lib/License-Apache2_0.txt
  Copyright 1999-2000, by  The Apache Software Foundation.
