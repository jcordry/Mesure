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
public class NumberNode extends ExpNode implements Visitable {
	public double value;
	public NumberNode(double value) {
		this.value = value;
	}

	public double getNumberValue() {
		double v = value;
		return value;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public ExpNode getValue() {
		return this;
	}
	@Override
	public ExpNode add(ExpNode arg) {
		if (arg instanceof NumberNode) {
			return new NumberNode(this.getNumberValue() + ((NumberNode)arg).getNumberValue());
		}
		return  new BinOpNode('+', this, arg);
	}
	@Override
	public ExpNode sub(ExpNode arg) {
		if (arg instanceof NumberNode) 
			return new NumberNode(this.getNumberValue() - ((NumberNode)arg).getNumberValue()); 
		return  new BinOpNode('-', this, arg);
	}
	@Override
	public ExpNode mul(ExpNode arg) {
		if (arg instanceof NumberNode) 
			return new NumberNode(this.getNumberValue() * ((NumberNode)arg).getNumberValue()); 
		return  new BinOpNode('*', this, arg);
	}
	@Override
	public ExpNode div(ExpNode arg) {
		if (arg instanceof NumberNode) 
			return new NumberNode(this.getNumberValue() / ((NumberNode)arg).getNumberValue()); 
		return  new BinOpNode('/', this, arg);
	}
	public ExpNode neg() {
		return new NumberNode(-this.getNumberValue());
	}
	
	@Override
	public String toString() {
		return ""+this.value;
	}
}
