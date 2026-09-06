package distributedsystem;


//  Availability patterns: There are two complementary patterns to support high availability: fail-over and replication.

//  Active-passive: With active-passive fail-over, heartbeats are sent between the active and the
//  passive server on standby. If the heartbeat is interrupted, the passive server takes over
//  the active's IP address and resumes service.
//  Active-passive failover can also be referred to as master-slave failover.


//  Active-active:
//  In active-active, both servers are managing traffic, spreading the load between them.
//  If the servers are public-facing, the DNS would need to know about the public IPs of both servers.
//  If the servers are internal-facing, application logic would need to know about both servers.
//  Active-active failover can also be referred to as master-master failover.

//  Disadvantage(s): failover
//  Fail-over adds more hardware and additional complexity.
//  There is a potential for loss of data if the active system fails before any newly written data can be replicated to the passive.



//  Load Balancing:

//  Layer 4 load balancing: Layer 4 load balancers look for info at the transport layer to decide
// how to distribute requests.
//  It makes routing decisions based on IP address and port numbers, without inspecting the actual
// application data.
//  Layer 4 load balancers forward network packets to and from the upstream server, performing
// Network Address Translation (NAT).
//  Usage: Gaming servers, Video streaming, Messaging systems, Financial trading systems

//  High performance – very fast since it doesn’t inspect payloads
//  No content awareness – cannot route based on URLs, cookies, headers
//  Connection-based routing – decisions are made per TCP/UDP connection
//  Stateless or lightly stateful – tracks sessions at connection level only

//  Layer 7 load balancing: Layer 7 load balancers look at the application layer to decide how to
// distribute requests.
//  This can involve contents of the header, message, and cookies. Layer 7 load balancers terminate
// network traffic,
//  reads the message, makes a load-balancing decision, then opens a connection to the selected
// server.
//  For example, a layer 7 load balancer can direct video traffic to servers that host videos while
// directing
//  more sensitive user billing traffic to security-hardened servers.
//  Usage: API Gateways, Microservice, RateLimiter, Amazon Shopping Website, Kubernetes

//  Content-Based Routing
//  SSL/TLS Termination
//  Session Persistence (Sticky Sessions)
//  Advanced Traffic Management


//  Load Balancing Strategies:

//  1. Round Robin (RR): Each request goes to the next server in a circular list.

//  2. Weighted Round Robin (WRR): It distributes requests across servers proportionally to their
// assigned weights.
//  It is useful when backend servers have different capacities.

//  3. Least Connections: Route traffic to the server with the fewest active connections.

//  4. Least Latency: Send requests to the server with lowest observed latency
// + fewest connections.
//  Used in AWS ALB, Google Global LB.

//  5. Resource-Based Load Balancing: LB uses actual server metrics:
//  CPU load, Memory usage, Queue depth, Open file descriptors.
//  Used in: Kubernetes Metrics Server, Netflix Conductor.

//  6. Consistent Hashing (Key-Based Routing)

//  7. Client-Side Load Balancing: Client caches server list and chooses target. Requires consistent
// hash or service discovery.
//  Scales infinitely (no centralized LB bottleneck). Used in gRPC, Netflix Ribbon, Java
// microservices.

//  8. Server-Side Load Balancing: Traditional architecture: client ? LB ? server pool.
//  LB can become bottleneck, Costly at hyperscale.

//  9. Rate-Based / Token Bucket LB: Route based on: request rate, token bucket capacity, QoS per
// customer.
//  Used in multi-tenant systems.

//  10. Global Load Balancing: Used to route clients across regions or countries. Approaches:
//  -> DNS Load Balancing (GeoDNS): Return nearest IP based on user’s location.
//  -> Anycast Routing: All regions advertise the same IP via BGP, network routes to nearest.
//  -> Global LB with Health Checks: Route traffic only to healthy regions.

public class LoadBalancingStrategies {}
