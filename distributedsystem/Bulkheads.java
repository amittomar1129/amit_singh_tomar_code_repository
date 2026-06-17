package distributedsystem;


//  Bulkheads Pattern:

//  The Bulkhead pattern isolates parts of a system so that failure in one part does NOT bring down the entire system.
//  If one component fails or gets overloaded, the rest of the system continues to function.
//  The name comes from ship bulkheads: A leak in one compartment, Does not sink the whole ship.

//  Bulkheads limit the blast radius of failures by isolating resources
//  like threads, connections, or memory per component or dependency.

//  Why Bulkheads Are Needed (Real Problem)
//  Without Bulkheads:
//              User Requests
//                 ?
//              Service A
//                 ?
//              Shared Thread Pool
//                 ?
//              Slow Dependency (DB / Downstream API)
//  Slow dependency blocks threads, Thread pool exhausts, All requests fail, Cascading failure.

//  With Bulkheads:
//              Service A
//               ?? Thread Pool (Payments)
//               ?? Thread Pool (Orders)
//               ?? Thread Pool (Search)
//  If Payments fails: Only Payments threads are blocked. Orders & Search still work.

//  Types of Bulkheads:

//  1. Thread Pool Bulkhead (Most Common): Each dependency gets its own thread pool.
//      PaymentService ? ThreadPool-Payment
//      InventoryService ? ThreadPool-Inventory
//  ? Strong isolation
//  ? Higher resource usage
//  Used heavily in Java, Hystrix-like systems

//  2. Connection Pool Bulkhead: Limit DB or API connections per dependency.
//  DB Connections:
//      - Orders: 50
//      - Payments: 20
//  Prevents one query type from starving others.

//  3. Semaphore Bulkhead (Lightweight): Limit concurrent requests instead of threads.
//  Max 100 concurrent requests to Payment API.
//  ? Low overhead
//  ? Less isolation than thread pools
//  Common in async / reactive systems

//  4. Resource-Based Bulkhead:
//  Isolate: CPU, Memory, Disk, Network bandwidth
//  Examples:
//  Separate pods
//  Separate containers
//  Separate VMs
//  Very common in Kubernetes

//  5. Bulkheads in Microservices Architecture:
//  Service-Level Bulkhead: Separate microservices, Separate scaling units.
//  Instance-Level Bulkhead: Separate thread pools / queues inside service.
//  FAANG uses both.


//  Bulkheads + Other Resilience Patterns (Interview Gold): Bulkheads are never used alone.
//  Typical combo:
//  Bulkhead
//    + Timeout
//    + Retry with Backoff
//    + Circuit Breaker
//  Why:
//  Bulkhead limits damage
//  Timeout frees resources
//  Retry handles transient failures
//  Circuit breaker stops useless calls

//  Bulkheads in Kubernetes:
//  Separate deployments
//  Separate HPA(Horizontal Pod Autoscaler) policies (means different scaling rules for scale-up and scale-down)
//  Resource limits (CPU / memory)
//  Pod disruption budgets

//  Example:Separate HPA policies
//  payments-service (3 pods)
//  orders-service (10 pods)

//  Netflix:
//  Thread pool per downstream dependency
//  Prevents cascading failures
//  Popularized via Hystrix

//  Amazon:
//  Per-API quotas + connection pools
//  Cell-based architecture (macro-bulkheads)

//  Google:
//  Isolation via Borg / Kubernetes
//  Per-service SLO isolation


//  Pitfalls of Bulkheads:
//  Over-partitioning: Too many small pools ? underutilization;
//  Resource waste: Idle threads while others are overloaded
//  Increased latency: Queueing delays under load
//  Configuration complexity: Wrong limits can cause self-DoS
//  Hidden coupling: Shared DB can still break isolation

public class Bulkheads {

}
