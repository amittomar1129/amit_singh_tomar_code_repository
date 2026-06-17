package designpattern.creational.abstractfactory;

public class App {

//	Known as Kit.
//	Abstract Factory pattern is almost similar to Factory Pattern that it's more like factory of factories.
//	Abstract Factory lets a class returns a factory of classes. So, this is the reason that Abstract Factory Pattern is one 
//	level higher than the Factory Pattern.
// 	When the system needs to be independent of how its object are created, composed, and represented
// 	and family of related objects has to be used together.

//	Advantage:
//		It isolates the client code from concrete (implementation) classes.
//		It represents object families and promotes consistency among objects.

//	Disadvantages:
//  	This can result in decreased performance if the creation of objects is a performance bottleneck.
//		Introduce an extra level of abstraction and indirection, which can make the code harder to understand and debug.
//		Not Ideal for Simple Systems.
	
//	Problem:
//	Imagine that you’re creating a furniture shop simulator. Your code consists of classes that represent:
//	A family of related products, say: Chair + Sofa + CoffeeTable.
//	Several variants of this family. For example, products Chair + Sofa + CoffeeTable are available
//	in these variants: Modern, Victorian, ArtDeco.


	
	public static void main(String[] args) throws Exception {

		AbstractFactory bankFactory = GetAbstractFactory.getFactory("Bank");
		
		Bank bank = bankFactory.getBank("HDFC");
		System.out.println(bank.getBankName());
		
		AbstractFactory loanFactory = GetAbstractFactory.getFactory("Loan");
		Loan loan = loanFactory.getLoan("Home");
		loan.calculateLoanPayment(2000000, 5);
	}
}
