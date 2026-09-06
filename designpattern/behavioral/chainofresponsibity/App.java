package designpattern.behavioral.chainofresponsibity;

public class App {

//  Sender sends a request to a chain of objects. The request can be handled by any object in the chain.
//	Upon receiving a request, Each handler decides either to process the request or to pass it to the next handler in the chain.
//	For example, an ATM uses the Chain of Responsibility design pattern in money giving process.
//	When more than one object can handle a request and the handler is unknown.
//	When the group of objects that can handle the request must be specified in dynamic way.

//	Advantage:
//		Reduces the coupling.
//		Adds flexibility while assigning the responsibilities to objects.

//	Disadvantages:
//  	The request must be received not guarantee.
//		The performance of the system will be affected, but also in the code debugging is not easy may cause cycle call.

//	Problem:
//	Imagine that you’re working on an online ordering system. You want to restrict access to the system so only 
//	authenticated users can create orders. Also, users who have administrative permissions must have full access to all orders.
//	After a bit of planning, you realized that these checks must be performed sequentially.
//	It authenticates a user to the system whenever it receives a request that contains the user’s credentials.
//	However, if those credentials aren’t correct and authentication fails, there’s no reason to proceed with any other checks.	

//  During the next few months, you implemented several more of those sequential checks. eg.
//	One of your colleagues suggested that it’s unsafe to pass raw data straight to the ordering system. So you added an extra validation step to 
//	sanitize the data in a request.	
//	Later, somebody noticed that the system is vulnerable to brute force password cracking. To negate this, you promptly added a check that filters 
//	repeated failed requests coming from the same IP address.
//	Someone else suggested that you could speed up the system by returning cached results on repeated requests containing the same data.
//	Hence, you added another check which lets the request pass through to the system only if there’s no suitable cached response.
//	The code of the checks, which had already looked like a mess, The system became very hard to comprehend and expensive to maintain. You struggled with the code 
//	for a while, until one day you decided to refactor the whole thing.

// Solution:
//	The Chain of Responsibility relies on transforming particular behaviors into stand-alone objects called handlers. 
//	In our case, each check should be extracted to its own class with a single method that performs the check.
//	In addition to processing a request, handlers pass the request further along the chain. The request travels along the chain until all handlers 
//	have had a chance to process it.
//	Here’s the best part: a handler can decide not to pass the request further down the chain and effectively stop any further processing.

	public static void main(String[] args) {
		Logger loggerChain = getLogger();

		loggerChain.logMessage(Logger.INFO, "This is an information.");
		loggerChain.logMessage(Logger.DEBUG, "This is an debug level information.");
		loggerChain.logMessage(Logger.ERROR, "This is an error information.");
	}

	private static Logger getLogger() {

		Logger errorLogger = new ErrorLogger(Logger.ERROR);
		Logger debugLogger = new DebugLogger(Logger.DEBUG);
		Logger infoLogger = new InfoLogger(Logger.INFO);

		errorLogger.setNextLogger(debugLogger);
		debugLogger.setNextLogger(infoLogger);

		return errorLogger;
	}
}
