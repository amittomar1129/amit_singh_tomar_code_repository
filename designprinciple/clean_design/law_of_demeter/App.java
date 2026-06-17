package designprinciple.clean_design.law_of_demeter;

public class App {

//	Definition:
//	An Object should only talk to its immediate friends/neighbors, not strangers. An object should avoid calling methods
//	on objects returned by other methods. Avoid chaining like a.getB().getC().doSomething() because it creates deep coupling.
//	LoD is not about style — it’s about boundaries and coupling.
//	The Law of Demeter (LoD), also known as the Principle of Least Knowledge, that puts restrictions on interactions
//	between program modules.
//	A method should only communicate with:
//	The object itself (this), Its own fields, Objects passed as parameters, Objects it creates
//	Its direct dependencies (through interfaces), Nothing beyond that.

//	NOT allowed (violations): When your code accesses “strangers”:
//	Calling methods on a returned object, Deep chains of calls.
//	Reaching into internal structure of another object.

//	user.getAddress().getCity().getPincode().validate(); This is a hard violation.
//  user.validatePincode(); Correct way

//	In distributed systems, LoD becomes "minimize fan-out per request".
//	Big systems fail when:
//	one service calls another, which calls another, which calls another
//	This creates deep dependency chains ? high latency, cascading failures, change ripple.
//	Example anti-pattern in microservices:
//	API Gateway ? Feed Service ? User Service ? Social Graph ? Ads ? Feature Store

//	Advantages:
//	latency, failure probability, cost, coupling, LoD encourages flat architecture.

//	Microservice Fan-Out Explosion:
//	? 1 request ? 7 services ? 13 downstream calls
//	Creates:	high latency, hard-to-debug failures

//	"Your service is calling 5 more services. How do you reduce fan-out?"
//	I reduce fan-out by flatten the topology using aggregation boundaries, moving dependencies upstream, caching,
//	materialized views, and pushing async fan-out into event pipelines so the service only makes 1–2 direct calls instead of 5.

//	Instead of,
//	Feed Service ? User Service
//             ? Feature Service
//             ? Engagement Service
//             ? Social Service
//             ? Ads Service
//	I create:
//	UserProfileAggregator
//	ContentMetaAggregator
//	FeatureBundlerService

//	Move Fan-Out into Asynchronous Pipelines:
//	Instead of calling:
//		Social Service
//		Interest Service
//		Recommendation Service
//		Engagement service
//  synchronously, log an event like: UserViewEvent And let downstream services process it asynchronously.

//	Push logic down into the Dependency Itself:
//		If A calls B, C, D:
//	Ask:
//			“Can B call C and D internally instead of A doing it?”
//	Example:
//			? A ? B
//			? A ? C
//			? A ? D
//
//			? B ? C
//			? B ? D
//			? A ? B
//	This is actual enforcement of Law of Demeter.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
