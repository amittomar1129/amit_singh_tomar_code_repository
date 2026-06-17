package distributedsystem;

//  Health Check:
//  A health check is a mechanism by which a service reports its ability to safely handle traffic at a given moment.

//  Why Health Checks Matter at Scale?
//  In large distributed systems:
//  Services fail partially, not fully,
//  Dependencies fail independently,
//  Deployments happen continuously.

//  Health checks enable:
//  Safe traffic routing,
//  Fast failure detection,
//  Automated recovery,
//  Zero-downtime deployments.

//  Types of Health Checks (Core Concept):
//  1. Liveness Check – Is this process alive or stuck? If liveness fails -> restart the process.
//  It checks process responsiveness, Deadlocks, Infinite loops, Fatal errors.
//  It does not check Downstream dependencies, Databases, External APIs.
//  Used by: Kubernetes, Process supervisors

//  2. Readiness Check – “Should I send traffic?” Can this instance safely handle requests? Determines traffic eligibility.
//  It checks Critical dependencies (DB, cache), Warm-up completed, Config loaded, Connection pools ready.
//  Used by Load balancers, Service mesh, Kubernetes Services.
//  If readiness fails -> remove from traffic, don’t restart.

//  3. Startup Checks (Modern systems): Has the app finished starting?
//  Used for slow-starting services. Prevents premature liveness failures.

//  Shallow vs Deep Health Checks: Shallow (Preferred for Liveness) -> Thread running, Event loop responsive.
//  Deep (Selective, Readiness only): Can accept requests, Critical dependency available, Configuration loaded.

//  Health Checks vs Dependency Checks:
//  Bad practice -> health = DB up AND Cache up AND Auth service up.
//  It can lead to Cascading failures, Thundering herd restarts, Global outages.

//  Correct Approach -> Health reflects local ability to serve.
//  Dependencies handled via: Timeouts, Circuit breakers, Graceful degradation.
//  Health checks should not amplify failures. That means when a system is already struggling,
//  your monitoring/health-check mechanisms should not make things worse by adding extra load,
//  cascading retries, or triggering unnecessary failovers.

//  Health Checks in Load Balancing: Health checks are continuous, not one-time. Flapping health causes instability.

//  Health Checks vs Monitoring (Very Important):
//  Health Check: purpose is traffic safety, Frequency is High, Simple/less complex, output as binary.
//  Monitoring: purpose is Diagnosis, output as Metrics, Frequency is lower, rich complexity.
//  Health checks are not dashboards.


//  Common Failure Patterns (Interview Gold):
//  Over-strict Health Checks: Fail due to transient dependency blips. Cause mass instance removal.
//  Dependency-Coupled Health: One DB issue -> entire fleet unhealthy.
//  Slow Health Endpoints:  Block threads, Create self-inflicted outages.


public class HealthCheck {}
