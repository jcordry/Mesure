package lib.equation;

public class UnaryMinusNode extends ExpNode implements Visitable {

	ExpNode operand;
	
	public UnaryMinusNode(ExpNode arg) {
		super();
		operand = arg;
	}
	
	public ExpNode getValue() {
		
		if (operand instanceof NumberNode) {
			NumberNode numNode = (NumberNode)operand;
			return new NumberNode(-numNode.getNumberValue());
		} else if (operand instanceof TmArray) {
			TmArray tmArray = (TmArray)operand;
			return tmArray.neg();
		} else {
			return this;
		}
	}
	
//	public ExpNode add(ExpNode arg)  {
//		if( arg instanceof UnaryMinusNode ) {
//			ExpNode res = this.operand.add(((UnaryMinusNode)arg).operand);
//			return new UnaryMinusNode(res);
//		}
//		
//	}
	
	@Override
	public String toString() {
		return "-" + operand.toString();
	}

}
