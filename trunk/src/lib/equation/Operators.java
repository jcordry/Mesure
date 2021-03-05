package lib.equation;

public class Operators {
	public ExpNode neg(ExpNode arg) {
		if (arg instanceof NumberNode) {
			return new NumberNode( - ((NumberNode)arg).getNumberValue());
		} else if (arg instanceof TmArray) {
			TmArray tmArray = (TmArray) arg;
			
		}
		
		return arg;
	}
}
