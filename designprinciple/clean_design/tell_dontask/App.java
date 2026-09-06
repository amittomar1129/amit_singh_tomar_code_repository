package designprinciple.clean_design.tell_dontask;

public class App {

//	Definition:
//	The Tell, Don't Ask Principle, To delegate an action to an object instead of asking an object for data.
//	When you apply this principle correctly, your programs can achieve better modularity and have a clear separation of concerns.

//	They are not the same, but Tell Don’t Ask is a practical application of the Objects vs Data Structures principle.
//	How They Are Connected
//	When you follow Objects vs Data Structures, you automatically end up doing Tell, Don’t Ask.
//	When you violate “Tell, Don’t Ask,” you are treating objects like data structures.

//	Tell, Don’t Ask is the enforcement mechanism of the Objects vs Data Structures principle.
//	When an object hides data and exposes behavior, you naturally tell it what to do instead of asking for its internals.
//	When you ask for data to make decisions outside, you're treating an object like a data structure.

//	Violating both principles:
//		double w = rectangle.getWidth();
//		double h = rectangle.getHeight();
//		rectangle.setArea(w * h);
//	ask for data, compute externally, treat rectangle as a struct
//
//	Following both principles:
//		rectangle.resizeToArea(targetArea);
//	tell the behavior, rectangle maintains its invariants

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
