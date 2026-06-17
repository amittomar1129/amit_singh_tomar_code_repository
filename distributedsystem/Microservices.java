package distributedsystem;


//  Microservices vs Distributed Systems:

//  Distributed System: A distributed system is a system where multiple independent computers coordinate
//  over a network to achieve a common goal.

//  Microservices Architecture: Microservices is an architectural style where an application is composed of small,
//  independently deployable services that communicate over the network.
//  Microservices are one way to build a distributed system — but not all distributed systems are microservices.

//  Every microservices system is a distributed system, but not every distributed system is a microservices architecture.

//  “Microservices are an architectural style built on top of distributed systems principles optimizing for organizational scalability.”

//  Most outages in microservices are not caused by business logic bugs, but by distributed system failures that
//  were not explicitly designed for.

//  “Microservices and distributed systems are related but not the same. A distributed system is any system
//  composed of multiple nodes coordinating over a network. Microservices is an architectural style where an
//  application is decomposed into independently deployable services. All microservices systems are distributed
//  systems, but many distributed systems, like databases or message queues, are not microservices. Microservices
//  inherit distributed system challenges such as latency, partial failures, and consistency, but they optimize
//  primarily for team autonomy and deployment velocity rather than formal correctness.”

//                  Distributed Systems
//                   |__ Databases (Cassandra, Spanner)
//                   |__ Distributed caches (Redis cluster)
//                   |__ Messaging systems (Kafka)
//                   |__ Distributed file systems (HDFS)
//                   |__ Microservices architectures
//  Both involve:
//  Network communication
//  Partial failures
//  Latency & retries
//  Consistency trade-offs
//  Observability challenges
//  Scalability concerns
//  Microservices inherit distributed system problems.

//  Key Differences:
//  Aspect	                            Distributed System	                            Microservices
//  Purpose                   	Solve a distributed computing problem	            Structure an application
//  Scope	                      Very broad	                                      Application-level
//  Focus                     	Coordination, correctness	                        Team autonomy, deployment
//  Abstraction                 System-level                                      Architecture-level
//  Failure handling            Formal (quorums, consensus)                       Practical (timeouts, retries)
//  Data model                	Shared / replicated	                              Owned per service
//  Consistency	                Strongly defined models	                          Eventual consistency
//  Operational cost            High                                              Very high




//  Monolith: A monolith is a single deployable unit where all business logic runs in one process and is deployed together.

//  SOA (Service-Oriented Architecture): SOA is an architecture where services communicate over a network,
//  typically via an enterprise integration layer, and often share data models.

//  When Monolith Is the Right Choice: Early-stage products, Small teams, Low scale, Rapid iteration.

//  What SOA Tried to Solve: Break monoliths, Enable reuse across teams, Standardize communication.
//  Characteristics: SOAP / XML, Enterprise Service Bus (ESB), Shared schemas.
//  Why SOA Failed at Scale: Heavy tooling, Central bottlenecks, Slow evolution, Shared databases.
//  “SOA decomposed systems technically, but not organizationally.”

//  Hidden Costs of Microservices:
//  Network failures,
//  Partial failures,
//  Latency,
//  Eventual consistency,
//  Complex debugging
//  Heavy observability needs. Microservices trade technical simplicity for organizational scalability.


//  When NOT to Use Microservices: Do NOT use microservices if:
//  Team < 15 engineers
//  No CI/CD maturity
//  No strong SRE(Site Reliability Engineering) culture
//  No observability platform
//  Business logic tightly coupled


//  Site Reliability Engineering (SRE) is a software engineering approach that focuses on making systems
//  reliable, scalable, and efficient using automation, measurement, and engineering instead of manual ops.
//  SRE = Software Engineers + Reliability + Production systems.

public class Microservices {

}
