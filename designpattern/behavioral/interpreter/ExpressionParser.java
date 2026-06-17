package designpattern.behavioral.interpreter;

public class ExpressionParser {

	protected static final String NAME = "name";
	protected static final String DESIG = "desig";
	protected static final String DEPT = "dept";
	protected static final String MANAGES = "manages";
	protected static final String SALARY = "salary";

	public static Expression parseExpression(String contextString)
	{
		Expression expression = null;
		String[] tokens = contextString.split(",");
		for(String token : tokens)
		{
			String[] words = token.trim().split(":");
			String key = words[0];
			String value = words[1];
			Expression getExpression = getExpression(key, value);
			if(null == expression)
				expression = getExpression;
			else
				expression = new AndExpression(expression, getExpression);
		}
		return expression;
	}

	private static Expression getExpression(String key, String value) {
		switch(key.toLowerCase()) {
		case ExpressionParser.NAME :  return new NameExpression(value);
		case ExpressionParser.DESIG : return new DesignationExpression(value);
		case ExpressionParser.DEPT : return new DepartmentExpression(value);
		case ExpressionParser.MANAGES : return new ManagerOfExpression(value);
		case ExpressionParser.SALARY : return new SalaryExpression(value);
		default : return null;
		}
	}

}
