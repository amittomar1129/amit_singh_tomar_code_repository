package designpattern.structural.proxy;

public class App {

//	Proxy pattern is also known as Surrogate or Placeholder.
//	Simply, proxy means an object representing another object.
//	According to GoF, a Proxy Pattern "provides the control for accessing the original object".

//	Usage:
// 		It can be used in Virtual Proxy scenario.
//		Consider a situation where there are multiple database calls to extract huge size image.
//		Since this is an expensive operation so here we can use the proxy pattern which would create
//		multiple proxies and point to the huge size memory consuming object for further processing.
//		The real object gets created only when a client first requests/accesses the object and after
//		that we can just refer to the proxy to reuse the object. This avoids duplication of the object and hence saving memory.

//		It can be used in Protective Proxy scenario.
//		It acts as an authorization layer to verify that whether the actual user has access the appropriate
//		content or not. For example, a proxy server which provides restriction on internet access in office.
//		Only the websites and contents which are valid will be allowed and the remaining ones will be blocked.

//	 Local execution of a remote service (remote proxy). This is when the service object is located on a remote server.
//	 In this case, the proxy passes the client request over the network, handling all the nasty details of working with the network.

//	 Logging requests (logging proxy). This is when you want to keep a history of requests to the service object.
//	 The proxy can log each request before passing it to the service.	

//	Caching request results (caching proxy). This is when you need to cache results of client requests
//	and manage the life cycle of this cache, especially if results are quite large. The proxy can implement caching
//	for recurring requests that always yield the same results. The proxy may use the parameters of requests as the cache keys.

//	Advantage:
//		It provides the protection to the original object from the outside world.

//	Disadvantages:
//  	We have to write repeated code as the proxy is similar to the actual object. 
//		Developer must concurrently update the proxy object alongside the real object.

//	Problem:
//	The main problem it solves is to provide a level of indirection to another object so that we can add extra functionality 
//	to it without changing its implementation.
	
	public static void main(String[] args) throws Exception {

		Image image = new ProxyImage("example.jpg");
		// Image will be loaded from disk only when display() is called
		image.display();
		// Image will not be loaded again, as it has been cached in the Proxy
		image.display();

		// Second example
		CommandExecutor executor = new CommandExecutorProxy("Amit", "amit123");
		executor.runCommand("ls -ltr");
		executor.runCommand(" rm -rf abc.pdf");

	}
}
