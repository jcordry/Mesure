/* Generated By:JJTree: Do not edit this line. SimpleNode.java */

package tools.extractor.ast;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class SimpleNode implements Node {
  protected Node parent;
  protected Node[] children;
  protected int id;
  protected EquationAST parser;

  public SimpleNode(int i) {
    id = i;
//     System.out.println("##"+i);
  }

  public SimpleNode(EquationAST p, int i) {
    this(i);
    parser = p;
  }

  public void jjtOpen() {
  }

  public void jjtClose() {
  }
  
  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public String toString() { return EquationASTTreeConstants.jjtNodeName[id]; }
  public String toString(String prefix) { return prefix + toString(); }
  
  /* Override this method if you want to customize how the node dumps
   out its children. */
  public void dump(String prefix) {
	  System.out.println(toString(prefix));
	  if (children != null) {
		  for (int i = 0; i < children.length; ++i) {
			  SimpleNode n = (SimpleNode)children[i];
			  if (n != null) {
				  n.dump(prefix + " ");
			  }
		  }
	  }
  }
  
  public Double evaluate() throws ASTException {
	  throw new ASTException("Simple Node");
  }
  
  public static Hashtable <String, Double> symbtab = new Hashtable<String, Double>();
  
  public static void dumpHash () {
	  System.out.println("**************dumpHash**************");
	  Set<String> keySet = symbtab.keySet();
	  Iterator<String> it = keySet.iterator();
	  while (it.hasNext()){
		  String name = it.next();
		  System.out.println(name + symbtab.get(name));
	  }
	  System.out.println("**************dumpHash**************");
  }
  
  public double solve(String xname, Node t1) throws SolvingException {
	  try {
		  t1.evaluate();
		  return(0);
	  } catch (ASTException e) {
		  throw new SolvingException(e.getMessage());
	  }
  }
  
}

