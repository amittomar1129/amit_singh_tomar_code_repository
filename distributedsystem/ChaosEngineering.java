package distributedsystem;


//  Chaos Engineering:

//  Chaos Engineering is the practice of intentionally injecting failures into a system to verify that it
//  behaves as expected under real-world conditions.
//  Goal: Build confidence in a system’s ability to withstand turbulence in production.
//  Key insight: You can’t prove reliability without testing failure.
//  Chaos is controlled, hypothesis-driven experimentation.

//  SLI (Service Level Indicator): A metric that tells how your system is performing.
//  Request success rate, Error rate
//  Latency (p95, p99)
//  Throughput

//  SLO (Service Level Objective): A target/goal for your SLI. This defines acceptable performance.
//  SLA (Service Level Agreement): What you promise (legally/business-wise). A contract with users/customers.
//  Penalties if violated
//  Compensation terms

//  3. Core Principles (Netflix Model)
//  3.1 Define Steady State: Identify key SLIs (latency, error rate, throughput)
//  3.2 Hypothesize: “If instance X fails, SLI Y remains within SLO”
//  3.3 Inject Realistic Failures: Kill nodes, Add latency, Drop packets
//  3.4 Observe & Learn: Verify steady state holds, Fix weaknesses

//  Common Chaos Experiments:

//  Infrastructure Level: Kill VM / pod, AZ outage, Disk full.
//  Network Level: Latency injection, Packet loss, DNS failure.
//  Application Level: Dependency timeouts, Error injection, Rate limit failures.

//  Chaos Engineering vs Fault Injection:
//  Fault injection: technique
//  Chaos engineering: discipline
//  Chaos = fault injection + metrics + learning.

public class ChaosEngineering {

}
