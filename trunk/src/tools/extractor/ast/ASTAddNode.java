/* Generated By:JJTree: Do not edit this line. ASTAddNode.java */

package tools.extractor.ast;

public class ASTAddNode extends SimpleNode {
  public ASTAddNode(int id) {
    super(id);
  }

  public ASTAddNode(EquationAST p, int id) {
    super(p, id);
  }

  public Double evaluate() throws ASTException {
    double c1 = Double.valueOf(((SimpleNode)jjtGetChild(0)).evaluate());
    double c2 = Double.valueOf(((SimpleNode)jjtGetChild(1)).evaluate());
    return(new Double(c1+c2));
  }

  public double solve(String xname, Node t1) throws SolvingException {
      dump("ADD0 ");
      Double leftVal = null;
      try {
	  leftVal = ((SimpleNode)jjtGetChild(0)).evaluate();
      } catch (ASTException e) {
	  if ((xname.compareTo(e.getMessage()) == 0)
	      || (e.getMessage().compareTo("x") == 0)) {
	      ASTSubtractNode t2 = new ASTSubtractNode(2);
	      try {
		  Double rightVal = ((SimpleNode)jjtGetChild(1)).evaluate();
		  ASTCstNode t3 = new ASTCstNode(6);
		  t3.setVal(Double.valueOf(rightVal));
		  t1.jjtSetParent(t2);
		  t2.jjtAddChild(t1, 0);
		  t3.jjtSetParent(t2);
		  t2.jjtAddChild(t3, 1);
		  t2.dump("ADD1 ");
		  jjtGetChild(0).dump("a  ");
		  return(jjtGetChild(0).solve(xname, t2));
	      } catch (ASTException e1) {
		  throw new SolvingException(e1.getMessage());
	      }
	  } else {
	      throw new SolvingException(e.getMessage());
	  }
      }
      
      ASTSubtractNode t2 = new ASTSubtractNode(2);
      ASTCstNode t3 = new ASTCstNode(6);
      t3.setVal(Double.valueOf(leftVal));
      t3.jjtSetParent(t2);
      t1.jjtSetParent(t2);
      t2.jjtAddChild(t1, 0);
      t2.jjtAddChild(t3, 1);
      t2.dump("ADD2 ");
      return(jjtGetChild(1).solve(xname, t2));
  }

}
