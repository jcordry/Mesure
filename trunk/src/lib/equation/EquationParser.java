package lib.equation;

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
 * $HeadURL: svn+ssh://meunier@gforge.inria.fr/svn/mesureprv/src/benchs/lib/templates/applets/TemplateApplet.java $
 * Created: 22 decembre 2006
 * Author: POPS 
 * $LastChangedRevision: 35 $
 * $LastChangedDate: 2006-12-22 17:10:44 +0200 (ven., 29 sept. 2006) $
 * $LastChangedBy: meunier $
 */
import java.util.Scanner;

/**
 * Basic Equation grammar
 * <equ> ::= <expr> = <expr>
 * <expr> ::= [ "-" ] <term> [ ("+" | "-") <term> ] ... 
 * <term> ::= <factor> [ ( "*" | "/" ) <factor> ] ...
 * <factor> ::= <number> | <litteral> | "(" <expr> ")"
 * <litteral> = [a-zA-Z_]+
 * <number> = Scanner.getNextDouble()
 */

public class EquationParser {

	private final boolean debug = true;

	public void test() {
		ExpNode s1 = new NumberNode(1);
		ExpNode s2 = new NumberNode(2);
		ExpNode s3 = new NumberNode(3);
		ExpNode l1 = new LitteralNode("x");
		ExpNode add1 = new BinOpNode('+', s1, s2);
		ExpNode v1 = ((BinOpNode)add1).getValue();

//		ExpNode add2 = new BinOp('+', add1, s3);
		ExpNode add3 = new BinOpNode('+', s1, l1);
		ExpNode v3 = ((BinOpNode)add3).getValue();

		System.out.println("v1:"+v1.toString());
		System.out.println("v2:"+v3.toString());

		ExpNode v4 = v1.add(s3);
		System.out.println("v4:"+v4.toString());

//		ExpNode t = new BinOp('+', new BinOp('+', new ScalarNode(16), new LitteralNode("SLOAD_1 BC")), new BinOp('+', new ScalarNode(16), new LitteralNode("SADD_BC")));
//		System.out.println("t="+t);

		ExpNode t2 = new BinOpNode('+', new BinOpNode('+', new NumberNode(16), new NumberNode(10)), new BinOpNode('+', new NumberNode(16), new NumberNode(10)));
		System.out.println("t2="+t2.getValue());

		ExpNode e1 = new BinOpNode('=', add3, s1);
		System.out.println("e1="+e1.getValue());
	}

	class ParseError extends Exception {
		// Represents a syntax error found in the user's input.
		ParseError(String message) {
			super(message);
		}
	} // end nested class ParseError

	
	/**
	 * @param scanner
	 * @return
	 * @throws ParseError
	 */
	public ExpNode equationTree(Scanner scanner) throws ParseError {
		ExpNode equ = expressionTree(scanner);
		if (scanner.hasNext("[=]")) {
			scanner.next("[=]");
			ExpNode right = expressionTree(scanner);
			equ = new BinOpNode('=', equ, right);
		}
		return equ;
	}


	/**
	 * @param scanner
	 * @return
	 * @throws ParseError
	 */
	private ExpNode expressionTree(Scanner scanner) throws ParseError {
		boolean negative;  // True if there is a leading minus sign.
		negative = false;

		if (scanner.hasNext("-")) {
			scanner.next("-");
			negative = true;
		}
		ExpNode exp;   // The expression tree for the expression.
		exp = termTree(scanner);  // Start with the first term.
		if (negative)
		exp = new UnaryMinusNode(exp);
		
		while ( true ) {
			String op;
			if (scanner.hasNext("[+]"))
				op = scanner.next("[+]");
			else if (scanner.hasNext("[-]"))
				op = scanner.next("[-]");
			else
				break;
			ExpNode nextTerm = termTree(scanner);
			exp = new BinOpNode(op.charAt(0), exp, nextTerm);
		}
		return exp;
	}

	private ExpNode termTree(Scanner scanner) throws ParseError {
		ExpNode term;  // The expression tree representing the term.
		term = factorTree(scanner);
		while ( true ) {
			String op;
			if (scanner.hasNext("[*]"))
				op = scanner.next("[*]");
			else if (scanner.hasNext("[/]"))
				op = scanner.next("[/]");
			else
				break;
			ExpNode nextFactor = factorTree(scanner);
			term = new BinOpNode(op.charAt(0),term,nextFactor);
		}
		return term;
	}

	private ExpNode factorTree(Scanner scanner) throws ParseError {
		if ( scanner.hasNextDouble() ) {
			return new NumberNode(scanner.nextDouble());
		} else 
			if ( scanner.hasNext("[(]")) {
				scanner.next("[(]");
				ExpNode exp = expressionTree(scanner);
				if ( ! scanner.hasNext("[)]") )
					throw new ParseError("Missing right parenthesis.");
				scanner.next("[)]");
				return exp;
			} else if (scanner.hasNext()){
				return new LitteralNode(scanner.next());
			}
			else if ( scanner.hasNext("\n") )
				throw new ParseError("End-of-line encountered in the middle of an expression.");
			else if ( scanner.hasNext("[)]") )
				throw new ParseError("Extra right parenthesis.");
			else if ( scanner.hasNext("[+-*/=]") ) //TODO: should define an "operators" vector or stuff
				throw new ParseError("Misplaced operator.");
			else
				throw new ParseError("Unexpected character \"" + scanner.next() + "\" encountered.");

	}

	class BasicVisitor implements Visitor {
		public void visit(ExpNode expNode) {
			if (expNode instanceof BinOpNode) {
				BinOpNode binOpNode = (BinOpNode) expNode;
				visit(binOpNode);
			}
			if (expNode instanceof NumberNode) {
				NumberNode numberNode = (NumberNode) expNode;
				visit(numberNode);
			}
			if (expNode instanceof LitteralNode) {
				LitteralNode littNode = (LitteralNode) expNode;
				visit(littNode);
			}
		}
		public void visit(LitteralNode littNode) {
//			System.out.println("visiting littnode : " + littNode);
		}
		public void visit(NumberNode numNode) {
//			System.out.println("visiting numnode : " + numNode);
		}
		public void visit(BinOpNode binOp) {
//			System.out.println("visiting binop: "+ binOp);
		}
		
	}
	
	class FindLitteralVisitor extends BasicVisitor {
		public String id;
		public boolean found=false;

		public FindLitteralVisitor(String id) {
			this.id = id;
		}
		
		public void visit(LitteralNode littNode) {
			System.out.println("visiting littnode : " + littNode);
			if (littNode.getLitteralValue().equals(id))
				found = true;
		}
	}

	class SimplifierVisitor extends BasicVisitor {

		public void visit(BinOpNode binOp) {
			
			switch (binOp.getOp()) {
			case '+':
//				if (binOp.getLeft(). == binOp.getRight().getClass())
//					System.out.println("same class !");
				break;
			}
		}

	}
	
	// isolate litteral in left part of equation
	class IsolationVisitor extends FindLitteralVisitor {

		private char moveOperator(char op) {
			switch (op) {
			case '+': return '-';
			case '-': return '+';
			case '*': return '/';
			case '/': return '*';
			}
			return '?';
		}

		public IsolationVisitor(String id) {
			// TODO Auto-generated constructor stub
			super(id);
		}
		private boolean lookUpOneSide(BinOpNode equ, BinOpNode thisSide) {
			ExpNode otherSide = null;
			if (equ.getRight() == thisSide)
				otherSide = equ.getLeft();
			else
				otherSide = equ.getRight();

			// operator at thisSide
			char op = thisSide.getOp();
			ExpNode opLeft = thisSide.getLeft();
			ExpNode opRight = thisSide.getRight();

			// locate Litteral in Binary operator located at left of thisSide
			FindLitteralVisitor finder = new FindLitteralVisitor(id);
			opLeft.accept(finder);
			boolean atLeft = finder.found;
			System.out.println("atLeft: " + atLeft);
			finder.found = false;
			opRight.accept(finder);
			boolean atRight = finder.found;
			System.out.println("atRight: " + atRight);

			if (! (atRight || atLeft) ) {
				System.out.println("id not found.");
				return false;
			}
			if (atRight && atLeft) {
				System.out.println("Can't handle 2 sides yet !");
				return false;
			}

			// build node that will be moved to the other side of '='
			BinOpNode newNode = new BinOpNode(moveOperator(op), otherSide, (atLeft)?opRight:opLeft);

			if (thisSide == equ.getLeft()) {
				equ.setRight(newNode);
				if (atLeft)
					equ.setLeft(opLeft);
				else
					equ.setLeft(opRight);
			} else {
				equ.setLeft(newNode);
				if (atLeft)
					equ.setRight(opLeft);
				else
					equ.setRight(opRight);
			}
			System.out.println("NEW TREE : " + equ);
			return true;
		}

		public void visit(BinOpNode binOp) {
			System.out.println("");
			System.out.println("visiting binop (active): "+ binOp);

			// look
			if (binOp.getOp() == '=') {
				BinOpNode equ = binOp;
				ExpNode equLeft = binOp.getLeft();
				ExpNode equRight = binOp.getRight();

				// look up in left part of the equation member is
				for (int i=0; i<5; i++)
				if (equ.getLeft() instanceof BinOpNode) {
					FindLitteralVisitor v = new FindLitteralVisitor(this.id);
					equ.getLeft().accept(v);
					if (!v.found)
						break;
					
					lookUpOneSide(equ, (BinOpNode)equ.getLeft());
				}

//				for (int i=0; i<5; i++)
//				if (equ.getRight() instanceof BinOpNode) {
//					FindLitteralVisitor v = new FindLitteralVisitor(this.id);
//					equ.getRight().accept(v);
//					if (!v.found)
//						break;
//					
//					lookUpOneSide(equ, (BinOpNode)equ.getRight());
//				}
			}
		}
	}
	private String cleanupEquation(String old) {
		String s = new String(old);
		s = s.replace("=", " = ");
		s = s.replace("+", " + ");
		s = s.replace("-", " - ");
		s = s.replace("*", " * ");
		s = s.replace("/", " / ");
		s = s.replace("(", " ( ");
		s = s.replace(")", " ) ");
		s = s.replace(".", ",");
		return s; 
	}

	private void test2(String s) {
		EquationParser sp = new EquationParser();
		Scanner scanner = new Scanner(cleanupEquation(s));

		System.out.println(s);
		try  {
			ExpNode eNode = sp.equationTree(scanner);
			System.out.println("equation:" + eNode);
			FindLitteralVisitor visitor = new FindLitteralVisitor("x");
			eNode.accept(visitor);
			System.out.println("x is found : " + visitor.found);

			IsolationVisitor isol = new IsolationVisitor("x");
			eNode.accept(isol);
			System.out.println("tree=" + eNode);
			
			System.out.println("value=" + eNode.getValue());
			
			SimplifierVisitor vs = new SimplifierVisitor();
			eNode.accept(vs);
			System.out.println("simplified =" + eNode.getValue());
			
		} catch (ParseError e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EquationParser parser = new EquationParser();
		parser.test2(args[0]);
	}

}
