package designprinciple.solid.openclose;

import java.time.LocalDate;
import java.util.HashSet;

public class App {

//	Assume a car with a broken windshield. Automotive manufacturers design cars so that repairing the windshield does not
//	require you to make changes to another part of the vehicle, like the engine.
//	The two components do not connect, so changing one component does not require a modification to the other.
//	Developers do not always apply this logic to their code. As a result, they do not isolate the components, and changes to one
//	feature might require changes to the source code of other features. The impacts of these changes are not ideal, and
//	they create unnecessary complications.

//	Definition:
//	Software entities should be open for extension and closed for modification.
//	“Open for extension. This means that the behavior of the module can be extended. As the requirements of the application change,
//	we are able to extend the module with new behaviors that satisfy those changes. In other words, we are able to change
//	what the module does.”
//	“Closed for modification. Extending the behavior of a module does not result in changes to the source or binary code of the module.
//	The binary executable version of the module, whether in a linkable library, a DLL, or a Java .jar, remains untouched.”

//	Common patterns to apply OCP include Strategy, Template, Factory, and Polymorphism.

//	Violating the OCP: Shotgun Surgery Anti-Pattern:
//	Shotgun Surgery happens when a single change in the system requires modifying many different classes/files or
//  widespread changes in an application signifies this anti-pattern.

//	Achieve OCP: Use an abstraction to compose functionality.
//	1. A well-designed API of the module or component
//	2. A well-defined function, class, or interface
//	3. An implemented design pattern
//	4. A domain-specific language (DSL) or architectural decision

// Example:
// 	A mixer is a kitchen tool that has different attachments, like wire beaters, a bread hook, or a flat beater.
// 	Each one has a different purpose and functionality. As long as the attachment fits into the attachment hub on the mixer,
// 	you can plug it in and use it.
//	Classes define extension points where future functionality can hook into the existing code and provide new behaviors.
//	The following are different types of extension points:
//	1. Virtual (not final) methods / Abstract methods
//	3. Interface inheritance
//	4. Parameter to methods
//	5. Higher-order methods (Takes another function as an argument or Returns a function as its output)
//	6. Setter of another class

//	Challenges of applying OCP:
//	1. Difficult Decision: One of the biggest challenges of implementing the OCP is making right decisions:
//	where to apply an abstraction, what part of the code to change, and when you should leave space for flexibility.
//	The product backlog enables you to review features and look for similarities and relations, which helps you prepare your
//	current features properly.
//	2. Continuous Refactoring
//	3. Prepare for Changes: Several practices help you prepare for changes early in development:
//	-Release early and often get the code in front of customers and users as quickly and as often as possible.
//	-Start with developing the most important features.
//	-Develop features before infrastructure.
//	-Show features to stakeholders.
//	-Adhere to a short development cycle—instead of weeks, aim for days.
//	-Write tests first.

//	Avoiding Over-Design:
//	Too much abstraction has negative impacts. First, abstraction is expensive. Also, too much abstraction can make application code
//	difficult to understand and maintain. Rather than transparency, you might get the opposite effect.

//  Bad Design (Violates OCP):
//	class PaymentProcessor {
//		void pay(String paymentType, double amount) {
//			if (paymentType.equals("CREDIT_CARD")) processCreditCard(amount);
//			if (paymentType.equals("UPI")) processUPI(amount);
//			if (paymentType.equals("PAYPAL")) processPaypal(amount);
//		}
//	}
//	Adding Apple Pay ? You must modify the class ? breaks OCP.


//	Correct OCP Design (Extensible):
//		interface PaymentMethod {
//			void pay(double amount);
//		}

//	Implement for each payment type
//		class CreditCardPayment implements PaymentMethod {
//			public void pay(double amount) {
//				// logic
//			}
//		}
//
//		class UPIPayment implements PaymentMethod {
//			public void pay(double amount) {
//				// logic
//			}
//		}
//
//		class PaypalPayment implements PaymentMethod {
//			public void pay(double amount) {
//				// logic
//			}
//		}

//	Adding a new payment type (Apple Pay)
//	class ApplePayPayment implements PaymentMethod {
//		public void pay(double amount) {
//			// apple pay logic
//		}
//	}

//	PaymentProcessor uses polymorphism
//		class PaymentProcessor {
//			private final PaymentMethod method;
//
//			PaymentProcessor(PaymentMethod method) {
//				this.method = method;
//			}
//
//			void process(double amount) {
//				method.pay(amount);
//			}
//		}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}
}
