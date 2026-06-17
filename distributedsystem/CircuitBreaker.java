package distributedsystem;


//  Circuit breaker:

//  A Circuit Breaker is a resilience pattern that prevents a service from repeatedly calling a failing dependency,
//  allowing the system to fail fast, recover gracefully, and protect itself from cascading failures.

//  Core Idea: Stop calling a dependency that is already failing. Instead, Fail fast, Return fallback, Retry later.
//  Think of it like an electrical circuit breaker, when there’s a fault, it cuts off power instead of letting the system burn.

//  Why Circuit Breaker Is Needed?
//  Service A calls Service B. Service B becomes slow or down. Threads pile up waiting. Connection pools exhaust.
//  CPU spikes. Entire system goes down. This is called a cascading failure.

//  Circuit Breaker States(Very Important):
//  1. Closed (Normal State): All requests go through. Failures are monitored. Error rate below threshold. Requests -> Service B.
//  2. Open (Failure State): Failure threshold exceeded. All requests fail immediately. No calls to Service B.
//  Requests ? FAIL FAST. It protects CPU, Threads, Network, Downstream services.
//  3. Half-Open (Recovery Test): After a cooldown period. Allow limited test requests.
//  If success ? Close. If failure ? Open again. Few test requests ? Service B.

//  Failure threshold:                    	% or count of failures
//  Timeout:                              	Max allowed response time
//  Sliding window:                       	Time / count window
//  Cooldown / sleep window:              	Wait before half-open
//  Success threshold:                    	Needed to close circuit
//  Fallback:                             	What to return when open

//  Fallback Strategies: When circuit is OPEN, return:
//  Cached data
//  Default response
//  Partial response
//  Graceful degradation
//  Error with retry-after

//  Common Pitfalls:
//  Wrong thresholds: Opens too aggressively Or never opens.
//  No fallback: Still causes user failure.
//  Retry storm: Retrying without CB worsens outage.
//  Ignoring slow failures: Latency matters, not just errors.
//  Shared circuit across unrelated calls: One failure affects all traffic.

//  How FAANG Implements Circuit Breakers:

//  Metrics-based (latency + error rate).
//  Combined with: Timeouts, Bulkheads, Rate limiting.
//  Integrated with observability.
//  Gradual recovery via half-open.
//  Per-dependency circuit breakers.


public class CircuitBreaker {

}
