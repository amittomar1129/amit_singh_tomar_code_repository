package designpattern.behavioral.interpreter;

public class OrExpression implements Expression {
	
	private Expression exp1;
	private Expression exp2;

	public OrExpression(Expression exp1, Expression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public boolean interpret(Employee context) {
		return exp1.interpret(context) || exp2.interpret(context);
	}
	
	@Override
	public String toString() {
		return "OrExpression with exp1 is "+this.exp1+" and exp2 is "+this.exp2;
	}

}
