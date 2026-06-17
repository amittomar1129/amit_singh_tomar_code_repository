package distributedsystem;


//  Microservice Anti-patterns:
//  Microservice anti-patterns are design and operational practices that increase coupling, complexity,
//  and failure risk, effectively negating the advantages of microservices.

//  Anti-Patterns Happen when Teams split systems too early, Lack of SRE & observability maturity,
//  Poor service boundary design, Treat microservices as “small monoliths”.

//  Microservices fail not because they are complex, but because teams underestimate distributed system costs.

//  Most Common Microservice Anti-Patterns:

//  1. Distributed Monolith: Services are separated but deployed together, share databases and require coordination changes.
//  “If services must be deployed together, it’s a distributed monolith, not microservices.”

//  2. Shared Database: Multiple services read/write the same database. Tight coupling via schema, Hard to evolve independently.

//  3. Synchronous-Only Communication: All communication is blocking RPC. Leads to One slow service blocks many, Retry storms.
//  fix: Event-driven design, Timeouts + circuit breakers.

//  4. Chatty Services: Too many synchronous service-to-service calls. Leads to High latency, Cascading failures.
//  fix: Coarse-grained APIs (Instead of making many small calls, the client makes one bigger call.), Asynchronous messaging.

//  5. Over-Splitting Services: Too many tiny services with unclear boundaries. High operational overhead, Slow development.
//  fix:  Start with a modular monolith, Split only when necessary.

//  7. Tight API Coupling: Breaking API changes force multiple services to update.
//  fix: Backward-compatible APIs, Consumer-driven contracts.

//  8. Ignoring Failure Handling: No timeouts, Infinite retries, No circuit breakers. Leads to Cascading failures, Large outages.

//  9. Poor Observability: No distributed tracing, Logs without correlation. Incidents are hard to debug.
//  fix: Metrics, logs, tracing (OpenTelemetry)

//  6. No Clear Service Ownership: No single team owns a service end-to-end. Slow incident response, Poor reliability.






public class MicroserviceAntipatterns {

}
