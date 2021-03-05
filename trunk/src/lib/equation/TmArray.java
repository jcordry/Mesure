package lib.equation;

import lib.chrono.TimeMeasure;

public class TmArray extends ExpNode implements Visitable {
	TimeMeasure[] value;
	
	public TmArray(TimeMeasure[] vec) {
		value = vec;
	}
	
	public ExpNode Value() {
		return this;
	}
	
	public TimeMeasure[] getTmVecValue() {
		return value;
	}

	public ExpNode add(NumberNode arg) {
		NumberNode numArg = (NumberNode) arg;
		TimeMeasure tm = new TimeMeasure((long)numArg.getNumberValue(),1);
		for (int i=0; i<value.length; i++) {
			value[i] = value[i].add(tm);
		}
		return this;
	}
	
	public ExpNode add(TmArray arg) {
		TimeMeasure[] argTmArray = arg.getTmVecValue();
		for (int i=0; i<value.length; i++) {
			value[i].add(argTmArray[i]);
		}
		return this;
	}

	public ExpNode add(ExpNode arg) {
		if (arg instanceof TmArray) {
			return this.add((TmArray) arg);
		} else if (arg instanceof NumberNode) {
			return this.add((NumberNode)arg);
		} else {
			return new BinOpNode('+', this, arg);
		}
	}

	public ExpNode sub(NumberNode arg) {
		NumberNode numArg = (NumberNode) arg;
		TimeMeasure tm = new TimeMeasure((long)numArg.getNumberValue(),1);
		for (int i=0; i<value.length; i++) {
			value[i] = value[i].subtract(tm);
		}
		return this;
	}
	
	public ExpNode sub(TmArray arg) {
		TimeMeasure[] argTmArray = arg.getTmVecValue();
		for (int i=0; i<value.length; i++) {
			value[i].subtract(argTmArray[i]);
		}
		return this;
	}

	public ExpNode sub(ExpNode arg) {
		if (arg instanceof TmArray) {
			return this.sub((TmArray) arg);
		} else if (arg instanceof NumberNode) {
			return this.sub((NumberNode) arg);
		} else {
			return new BinOpNode('-', this, arg);
		}
	}

	public ExpNode div(NumberNode arg) {
		double argValue = arg.getNumberValue();
		for (int i=0; i<value.length; i++) {
			value[i] = value[i].divide(argValue);
		}
		return this;
	}
	
	public ExpNode div(TmArray arg) {
		TimeMeasure[] argTmArray = arg.getTmVecValue();
		for (int i=0; i<value.length; i++) {
			value[i].divide(argTmArray[i].getMeasure());
		}
		return this;
	}

	public ExpNode div(ExpNode arg) {
		if (arg instanceof TmArray) {
			return this.div((TmArray) arg);
		} else if (arg instanceof NumberNode) {
			return this.div((NumberNode) arg);
		} else {
			return new BinOpNode('/', this, arg);
		}
	}

	public ExpNode mul(NumberNode arg) {
		double argValue = arg.getNumberValue();
		for (int i=0; i<value.length; i++) {
			value[i] = value[i].multiply(argValue);
		}
		return this;
	}
	
	public ExpNode mul(TmArray arg) {
		TimeMeasure[] argTmArray = arg.getTmVecValue();
		for (int i=0; i<value.length; i++) {
			value[i].divide(argTmArray[i].getMeasure());
		}
		return this;
	}

	public ExpNode mul(ExpNode arg) {
		if (arg instanceof TmArray) {
			return this.mul((TmArray) arg);
		} else if (arg instanceof NumberNode) {
			return this.mul((NumberNode) arg);
		} else {
			return new BinOpNode('*', this, arg);
		}
	}
	
	public ExpNode neg() {
		return (ExpNode)null;
	}
	
	@Override
	public String toString() {
		return "avg:"+TimeMeasure.average(this.value);
	}
	
	@Override
	public ExpNode getValue() {
		return this;
	}

}
