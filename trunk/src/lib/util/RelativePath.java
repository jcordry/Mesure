package lib.util;

import java.io.*;
import java.util.*;

/**
 * This class provides functions used to generate a relative path from two
 * absolute paths.
 */
public class RelativePath {

  /**
   * Break a path down into individual elements and add to a list. 
   * ex. : if path is /a/b/c/d.txt, the breakdown will be [d.txt,c,b,a].
   * @param f input file
   * @return a list with the individual elements of the path in reverse order
   */
  private static List getPathList(File f) {
    List l = new ArrayList();
    try {
      File r = f.getCanonicalFile();
      while (r != null) {
        l.add(r.getName());
        r = r.getParentFile();
      }
    } catch (IOException e) {
      e.printStackTrace();
      l = null;
    }
    return l;
  }

  /**
   * Figure out a string representing the relative path of 'f' with respect to
   * 'home'.
   * @param home home path
   * @param f path of file
   */
  private static String matchPathLists(List home, List f) {
    // start at the beginning of the lists
    // iterate while both lists are equal
    String s = "";
    int i = home.size() - 1;
    int j = f.size() - 1;
    // first eliminate common root
    while ((i >= 0) && (j >= 0) && (home.get(i).equals(f.get(j)))) {
      i--;
      j--;
    }
    // for each remaining level in the home path, add a ..
    for (; i >= 0; i--) {
      s += "../";
    }
    // for each level in the file path, add the path
    for (; j >= 1; j--) {
      s += f.get(j) + "/";
    }
    // file name
    s += f.get(j);
    return s;
  }

  /**
   * Get relative path of file 'f' with respect to 'home' directory.
   * ex. :
   * home = /a/b/c 
   * f = /a/d/e/x.txt 
   * s = getRelativePath(home,f) = ../../d/e/x.txt
   * @param home base path
   * @param f file to generate path for
   * @return path from home to f as a string
   */
  public static String getRelativePath(File home, File f) {
    List homelist = getPathList(home);
    List filelist = getPathList(f);
    return matchPathLists(homelist, filelist);
  }

  /**
   * Tests the function.
   */
  public static void main(String args[]) {
    if (args.length != 2) {
      System.out.println("RelativePath <home> <file>");
      return;
    }
    System.out.println("home = " + args[0]);
    System.out.println("file = " + args[1]);
    System.out.println("path = "
        + getRelativePath(new File(args[0]), new File(args[1])));
  }
}
