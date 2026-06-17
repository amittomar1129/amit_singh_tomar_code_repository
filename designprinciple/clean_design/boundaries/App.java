package designprinciple.clean_design.boundaries;

public class App {

//	In the Middle Ages, people built castles surrounded by moats. The only way to access a castle was to cross a drawbridge.
//	They designed the castles this way to defend themselves against hostile forces and environments. Those fortifications
//	helped people survive in a harsh physical world and keep their independence from invaders.
//	Just as people needed to ensure their safety with boundaries the castle and the moat you need to make sure,

//	Definition:
//	A boundary is a layer that separates two code parts and knows about both of them while the code parts
//	become agnostic to each other.
//	A boundary layer usually plays a role of an "adapter" between two layers or code parts.
//	To create a boundary, you can use the Dependency Inversion technique.
//	Strong boundaries ? clean code ? scalable architecture ? less technical debt.

//	Why Code Needs Boundaries:
//	As you design a system, you combine a lot of things: clean code, legacy code, libraries, frameworks, configurations,
//	etc. You also communicate with the other systems or subsystems through an API.
//	As a result, a lot of different components become dependent on each other. Certain dependencies are beneficial
//	and necessary.
//	The bad news is that some of these dependencies might violate Clean Code or Clean Design Principles
//	and have expensive consequences. Boundaries help defend your code against undesirable dependencies that inevitably occur
//	when you design a system.

//  Without Boundaries:
//	A small change requires touching many files
//	Different teams break each other's modules
//	Refactoring becomes a nightmare
//	Systems become tightly coupled
//	CI/CD pipelines fail frequently

//	Example (Bad Boundary — Violates Principle)
// 	String city = order.getCustomer().getAddress().getCity();
//	The calling code: Enters the Order boundary, Then enters the Customer boundary Then enters the Address boundary
//  And depends on their internal structures. This is boundary leakage.

//	Example (Good Boundary — Follows Principle)
//	String city = order.getCustomerCity();
//	Order exposes a simple API that hides internal structure: Each class respects its own boundary.
//	String getCustomerCity() {
//		return customer.getAddress().getCity();
//	}

//	Another Example: Bad Service Boundary
//	User user = database.rawQuery(...).parse().clean().hydrate();

//	Good Service Boundary:
//	User user = userRepository.findById(id);
//	The repository forms a clean boundary, hiding:
//	SQL, connections, hydration, parsing

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
