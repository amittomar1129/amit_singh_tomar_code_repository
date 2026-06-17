package designprinciple.solid.dependency_inversion;

public class App {

//	Imagine a large house with one room that you would like to redecorate. You want to remove the wallpaper and paint
//	the room a fresh color. However, when you start to take the wallpaper down, you find you must rebuild the entire house.
//	This situation sounds absurd because it's unrealistic. A house is designed so that changing a small detail like
//	wallpaper never requires changes to the entire structure.
// 	Sometimes code is poorly designed, and minor changes to details can force major changes on an entire system.
// 	In these situations, the negative impacts are significant. The Dependency Inversion Principle helps you avoid this kind
// 	of issue and equips you to design better code that minimizes the impacts of changes.

//	Definition:
//	"High-level modules should not depend on low-level modules. Both should depend on abstractions. Abstractions should not
//	depend up	on details. Details should depend upon abstractions."
//	Low-level modules those containing more specific details generally change more often than high-level modules.
//	Applying the DIP, Prevents changes to low-level modules from impacting and forcing changes to high-level modules.

//	Advantage of applying DIP:
//	Reduce code rigidity, Decrease coupling between parts of code, and Separate components cleanly,
//  which improves your code’s maintainability and readability.

//	Applying the Dependency Inversion Principle:
//	Consider your app contains two modules: Module A and Module B. Module A—the high-level module—to Module B—the low-level module.
//	In this design, Module A calls function x in Module B, which creates a source code dependency.
//	This dependency means that Module A must have Module B declared in the code to call Module B. As a result, if you make any changes
//	to Module B, you’ll also need to recompile, redeploy, verify, and change Module A.
//	The design violates the Dependency Inversion Principle.
//	When you apply the DIP, you ensure that both Module A and Module B depend on an abstraction which in this case is Interface C.
//	In this redesign, Module A uses Interface C in the code and calls “function x” of Interface C. Module B also derives
//	from Interface C to implement “function x.”
//	The source code dependency is now inverted, which means that changes to Module B do not require any changes to Module A.
//	the code is more flexible, modular, testable, and maintainable.

//	Violates the DIP:
//	One anti-pattern that violates the DIP is a database dependency.
//	In this application design, the Presentation layer depends on the Business layer. Then the Business layer depends on
//	the Data Access layer, which depends on a specific Database.
//	It violates the DIP because the database is the storage mechanism of the system, and it is a detail. High-level
//	business logic should not depend on this detail. The violation results in the following concerns:
//	1. A hard dependency on the database.
//	2. Decentralized business logic between the Business layer and Database, which can be hard to debug and maintain over time
//	3. The challenge of scalability.
//	4. An impossibility of Unit Testing all the Business logic, which cannot be integrated with CI build.

//	Solution to database dependency anti pattern:
//	To avoid this anti-pattern,
//	The Business logic should not be dependent on the database or dependent on the schema or tables in the database.
//	It also should not be concerned with whether the systems are backed with Oracle, MySQL, or a File system.
//	These are all elements you can abstract away to keep the architecture	as clean as possible.


//	Low Level Class: (Violates DIP)
//	class EmailSender {
//		void sendEmail(String msg) { ... }
//	}
//	High Level Class:
//	class NotificationService {
//		private EmailSender emailSender = new EmailSender();
//
//		void send(String msg) {
//			emailSender.sendEmail(msg);
//		}
//	}


//	Fix:
//	Create an abstraction:
//	interface MessageSender {
//		void send(String msg);
//	}
//
//	Low-level classes implement interface (details ? depend on abstraction)
//	class EmailSender implements MessageSender {
//		@Override
//		public void send(String msg) { ... }
//	}
//
//	class SmsSender implements MessageSender {
//		@Override
//		public void send(String msg) { ... }
//	}
//
//	High-level class depends on abstraction:
//	class NotificationService {
//		private final MessageSender sender;
//
//		NotificationService(MessageSender sender) {
//			this.sender = sender;
//		}
//
//		void send(String msg) {
//			sender.send(msg);
//		}
//	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
