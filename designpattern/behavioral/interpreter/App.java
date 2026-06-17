package designpattern.behavioral.interpreter;

public class App {

	// Def:
	// It to defines a grammatical representation for a language and provides an interpreter to
	// evaluate this grammar or expression.
	// A class of problems occurs repeatedly in a well-defined and well-understood domain.
	// If the domain were characterized with a "language", then problems could be easily solved with an interpretation "engine".

	// This pattern performs upon a hierarchy of expressions. Each expression here is a Terminal or Non-Terminal.
	// A NonTerminalExpression may have one or more other AbstractExpressions associated in it,
	// therefore it can be recursively interpreted. It's worth to note that NonTerminalExpression is a composite.
	// In the end, the process of interpretation has to finish with a TerminalExpression that will return the result.
	// The NonTerminal uses a composite design pattern in general.

	// Example:  SQL parsing, symbol processing engine, Language interpreters etc.
	// Musicians are examples of Interpreters. The pitch of a sound and its duration can be represented
	// in musical notation. This notation provides the language of music. Musicians playing
	// the music from the score are able to reproduce the original pitch and duration of each sound represented.

	//	Advantage:
	//		Implementing the grammar is straightforward.
	//		Easier to change and extend the grammar.
	//		Existing expressions can be easily modified.

	//	Disadvantages:
	//  	Complex grammars are hard to maintain.
	//		For complex instructions, your interpreter could become unmanageable.

	public static void main(String[] args) {
		Engineer ajay = new Engineer(1001l, "Ajay", "Developer", Department.ENG, 75000);
		Engineer vijay = new Engineer(1002l, "Vijay", "Sr. Developer", Department.ENG, 90000);
		Engineer jay = new Engineer(1003l, "Jay", "Lead", Department.ENG, 100000);
		Engineer martin = new Engineer(1004l, "Martin", "QA", Department.ENG, 70000);
		Manager kim = new Manager(1005l, "Kim", "Manager", Department.ENG, 110000);
		Engineer andersen = new Engineer(1006l, "Andersen", "Developer", Department.ENG, 95000);
		Manager niels = new Manager(1007l, "Niels", "Sr. Manager", Department.ENG, 140000);
		Engineer robert = new Engineer(1008l, "Robert", "Developer", Department.ENG, 85000);
		Manager rachelle = new Manager(1009l, "Rachelle", "Product Manager", Department.ENG, 150000);
		Engineer shailesh = new Engineer(1010l, "Shailesh", "Engineer", Department.ENG, 80000);
		kim.manages(ajay);
		kim.manages(martin);
		kim.manages(vijay);
		niels.manages(jay);
		niels.manages(andersen);
		niels.manages(shailesh);
		rachelle.manages(kim);
		rachelle.manages(robert);
		rachelle.manages(niels);

		String contextString = "Desig:manager, Dept:eng, Manages:martin, salary:110000";
		Expression expression = ExpressionParser.parseExpression(contextString);
		System.out.println(expression);
		System.out.println(expression.interpret(kim));
		//System.out.printf("For '%s', %s: %s.\n", kim.getEmployeeName(), expression, expression.interpret(kim));

		System.out.println("=======================================================================\n");
		contextString = "Desig:developer, Dept:eng, salary:<=75000";
		expression = ExpressionParser.parseExpression(contextString);
		System.out.println(ajay);
		System.out.println(expression.interpret(ajay));
		System.out.println(andersen);
		System.out.println(expression.interpret(andersen));


	}

}
