Design Patterns Summary Table (FAANG-Level)
Pattern Name	            When to Use                                      	        Real-World Example / Use Case
Creational:

Singleton		  Need global shared resource (e.g., config manager, DB connection).	Database connection pool, Logger.
Factory Method	  When exact object type is unknown until runtime.                    	Shape factory, Notification factory.
Abstract Factory  When system should be independent of object creation.	                UI themes (Dark/Light mode widgets).
Builder	Construct Building HTTP requests                                                Pizza order builder.
Prototype	Create When object creation is costly or repetitive.	                    Game object cloning, Document templates.

Structural:

Adapter	          Integrating legacy code or incompatible APIs.	Power plug converter,   Payment gateway adapter.
Bridge	          When both abstraction and implementation should evolve separately.	Payment system (PayPal/Stripe + Online/Subscription).
Composite	      Represent tree-like structures.	                                    File-system folders and files.
Decorator	      When extending behavior without modifying existing code.	            Adding toppings to pizza, Filters in streams.
Facade	          When client needs easy interface for complex API.                 	JDBC facade, Home Theater Controller.
Flyweight	      When there are many similar, immutable objects.	                    Character glyphs in text rendering.
Proxy	       	  For lazy loading, access control, or caching.                     	Virtual proxy for images, Security proxy.

Behavioral
Chain of
Responsibility	  When multiple objects could handle a request.	                        Logging framework, Event handling pipelines.
Command	          When you need to queue, undo, or log actions.	                        Text editor undo/redo, GUI button commands.
Interpreter	      When designing mini-languages or expression evaluators.	            Regex engine, Math expression parser.
Iterator	      When working with various data structures uniformly.	                Java Iterator / Iterable interface.
Mediator	      Reduce coupling between interacting objects.	                        Chatroom server, Air traffic control.
Memento	          Implement undo/redo functionality.	                                Text editor snapshot, Game save state.
Observer	      When multiple objects depend on another.	                            Event listeners, Stock price updates.
State	          When an object changes behavior at runtime.	                        Traffic light system, Media player states.
Strategy	      When you need runtime algorithm selection.	                        Sorting strategies, Payment calculation.
Template Method	  When multiple algorithms share structure.	                            Data parsing, Report generation.
Visitor	          When operations on object structure change frequently.                AST traversal, Document export.

--------------------------------------------------
Bonus: High-Frequency Patterns in FAANG Interviews
Pattern	                Why It’s Asked	                                                    Example Question
Singleton	    Tests thread-safety and lazy initialization.	              “How would you implement a thread-safe Singleton?”
Factory	        Evaluates abstraction and OCP (Open/Closed Principle).	      “Design a Notification system for multiple channels.”
Builder	        Assesses handling of complex object creation.	              “Design a PizzaBuilder API.”
Strategy	    Tests polymorphism and runtime flexibility.	                  “Switch sorting algorithms dynamically.”
Observer	    Core concept in event-driven systems.	                      “Design an event listener model.”
Decorator	    Tests extensibility without modifying code.	                  “Add functionality dynamically to coffee shop items.”
Proxy	        Evaluates understanding of lazy loading & access control.	  “Implement a caching proxy for an image loader.”
Command	        Often used in undo/redo systems.	                          “Design a text editor with undo support.”
Bridge	        Tests separation of abstraction and implementation.	          “Design a payment system with multiple gateways.”
