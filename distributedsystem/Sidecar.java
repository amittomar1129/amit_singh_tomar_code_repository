package distributedsystem;

//  Sidecar Pattern:
//  The Sidecar Pattern is a deployment pattern where a helper process (sidecar) runs alongside
//  a main service instance to provide cross-cutting infrastructure concerns without changing the
// service code.
//  The application focuses on business logic. The sidecar handles networking, security,
// observability, and resilience.

//  They are co-located, deployed together, and scaled together.
//  Think of a motorcycle with a sidecar: Motorcycle = Application service, Sidecar = Infrastructure
// helper
//  They move together, but have different responsibilities.

//  What Does a Sidecar Typically Do? Sidecars usually handle non-business concerns:
//  Traffic routing
//  Load balancing
//  Retries & timeouts
//  Circuit breaking
//  mTLS (encryption)
//  Authentication / authorization
//  Metrics & logging
//  Distributed tracing
//  Rate limiting
//  Feature flags (sometimes)

//  Without Sidecar: Each service must implement these non-business requirements that results in
// Code duplication,
//  Inconsistent behavior, Hard to update.

//  With Sidecar: Service A only calls localhost, Sidecar intercepts traffic, Applies policies
// automatically.
//                              [ Service A ] ? [ Sidecar Proxy ]
//                                                ?
//                                            Network
//                                                ?
//                          [ Sidecar Proxy ] ? [ Service B ]

//  Request Flow (Very Simple):
//  Service A sends request to localhost.
//  Sidecar intercepts the request.
//  Applies: mTLS, Retry, Timeout.
//  Forwards request to Service B’s sidecar.
//  Service B processes request.
//  Response flows back through sidecars.

//  Sidecar in Kubernetes (Very Important):
//  In Kubernetes: One Pod, Two containers (App container, Sidecar container (e.g., Envoy)).
//  They share: Network namespace, IP, Lifecycle. This is why service meshes work so well on K8s.

//  Why Sidecar Pattern is Powerful:
//  Removes infra logic from business code.
//  Standardizes networking behavior.
//  Enables zero-trust security.
//  Improves reliability without code changes.
//  Allows centralized policy control.
//  This is core to Service Mesh architecture.

//  Sidecar Pattern Pitfalls (Must Mention):
//  Increased latency (extra hop)
//  Operational complexity
//  Higher resource usage
//  Harder local debugging
//  Configuration drift if unmanaged

//  How FAANG Mitigates Sidecar Downsides:
//  Lightweight proxies (Envoy)
//  Shared config via control plane
//  Gradual rollout
//  Selective sidecar injection
//  Traffic sampling
//  Advanced observability tooling

//  Real FAANG Usage:
//  Google: Borg sidecars ? Anthos Service Mesh
//  Netflix: Sidecars for resilience
//  Amazon: App Mesh
//  Meta: Proxy-based networking
//  Uber: Envoy-based service mesh

//  Service Mesh = Sidecar Pattern + Control Plane.

//  Control Plane:
//  Control Plane is the part of a distributed system that decides “how the system should behave”,
//  but does NOT handle user traffic directly.
//  The control plane is responsible for Service Discovery, Traffic Management Rules, Security &
//  Identity (mTLS), Resilience Policies, Configuration Distribution, Observability Configuration.
//  while the data plane executes those decisions on real requests.


//  Control Plane vs Data Plane (Very Important):
//  Aspect	                                Control Plane                         	Data Plane
//  Handles user requests	                  ? No	                                  ? Yes
//  Makes decisions	                        ? Yes	                                  ? No
//  Latency sensitive	                      ? No	                                  ? Yes
//  Failure impact	                        Degraded behavior	                      Outage
//      Examples	                          Istiod, Kubernetes API	                Envoy sidecars

//  Control Plane in Service Mesh (Context):
//                          ????????????????
//                          ? Control Plane ?
//                          ? (Istiod)      ?
//                          ????????????????
//                                 ? pushes config
//                      ???????????????????????
//                      ?                     ?
//              ????????????????     ????????????????
//              ? Envoy Sidecar?     ? Envoy Sidecar?
//              ? (Data Plane) ?     ? (Data Plane) ?
//              ????????????????     ????????????????
//  The control plane tells sidecars what to do.

//  Service Mesh uses the Sidecar pattern, but they are not the same thing.
//  Think of sidecar as a building block, and service mesh as a full system built using that block.

//  Sidecar = One assistant sitting next to a driver
//  Service Mesh = Entire traffic control system (signals, rules, monitoring) across the city
//  Service mesh uses sidecars to implement distributed networking concerns

//  Service Mesh Pattern:

//  A Service Mesh is an infrastructure layer that manages service-to-service communication in a
// microservices system without putting networking logic into application code.

//  It provides: Traffic management, Security (mTLS), Reliability (retries, timeouts, circuit
// breakers), Observability (metrics, logs, traces).

//  Implementing this inside each service leads to:
//  Code duplication,
//  Inconsistent behavior,
//  Language lock-in,
//  Operational complexity.

//  Core Architecture:
//  1. Data Plane (Sidecars): The data plane is made of sidecar proxies (usually Envoy) deployed
// next to each service instance.
//  Every request must pass through the sidecar. Sidecars = traffic cops for microservices.
//  2. Control Plane: The control plane manages Configuration, Policy, Certificates, Sidecar
//  behavior. It does NOT handle user traffic.
//  3. Request Flow (Simple): Step-by-Step Request Flow.
//  Service A sends request. Request intercepted by Sidecar A.
//  Sidecar A: Applies routing rules. Checks retries, timeouts. Encrypts via mTLS. Request sent over
// network.
//  Sidecar B: Decrypts, Applies inbound policies, Service B receives request.

//  Key Capabilities (L4/L5 Focus):
//  1. Traffic Management: The service mesh controls how requests flow between services.
//  Capabilities: Dynamic routing, Traffic splitting, Retries, Timeouts, Circuit breaking, Load
// balancing.
//  Mesh can say: 90% traffic ? Service B v1, 10% traffic ? Service B v2 (canary).
//  Real use cases: Canary deployments, Blue-green deployments, A/B testing, Gradual rollouts.

//  2. Reliability Patterns (Built-in): “Service mesh improves system resilience by handling
// failures at the infrastructure level.”
//  Mesh prevents cascading failures. Capabilities: Circuit breakers, Retries with backoff,
// Timeouts, Rate limiting, Fault injection.

//  3. Security (Zero Trust): Mesh enforces service-to-service security by default.
//  “Service mesh enforces zero-trust security using mTLS and service identities.”
//  Capabilities: Mutual TLS (mTLS)
//  Automatic certificate rotation
//  Service identity
//  Policy enforcement
//  Every service is authenticated and encrypted by default.

//  4. Observability: Mesh provides deep visibility into service communication.
//  “Service mesh gives uniform metrics, logs, and traces across all services.”
//  eg. Metrics, Tracing, Logging.

//  Real-World FAANG Usage:
//  Google ? Traffic Director (inspired by Istio)
//  Meta ? Service mesh-like proxy layers
//  Netflix ? Envoy-based infrastructure
//  Uber ? Envoy + custom mesh
//  Amazon ? App Mesh

//  When to Use Service Mesh: Use when:
//  50+ microservices
//  High traffic
//  Strong security requirements
//  Platform teams exist
//  Multi-language stack

//  Pitfalls:
//  Operational complexity
//  Latency overhead
//  Steep learning curve
//  Debugging proxy issues
//  Control plane failures
//  Overkill for small systems

public class Sidecar {}
