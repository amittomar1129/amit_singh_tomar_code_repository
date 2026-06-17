package distributedsystem;

//  CAP Theorem:

//  CAP theorem states that in the presence of a network partition (P), a distributed system must
// choose to be either:
//  C: Consistent- All nodes see the same data at the same time (linearizable behavior).
//  A: Available- Every request receives a response, even if some nodes are down.
//  You cannot guarantee C + A simultaneously during a partition.

//  CAP does not say you must choose only 2 out of 3. CAP says when a partition happens,
//  you must sacrifice either consistency or availability.

//  P — Partition Tolerance: A network partition means: Nodes can’t communicate.
//  Messages get dropped or delayed indefinitely.
//  A distributed system must tolerate partitions. Partition-tolerance is non-negotiable.

//  C — Consistency (Strong Consistency / Linearizability)
//  Guarantees: Reads always return the latest committed write, System behaves like there is only
// one node.
//  Cost: Higher write latency, Lower availability during failures.
//  Implementation techniques: Majority quorum writes, Leader election, Synchronous replication,
// Write fencing.

//  A — Availability
//  Every request gets A response within a bounded time even if nodes are down.
//  Cost: Clients may temporarily read stale or conflicting data
//  AP systems avoid: Blocking, Leader re-election delays, Majority quorum wait.

//  When to Choose What (Decision Matrix):

//  CP (Consistency): You prefer to fail requests rather than return inconsistent data.
//  Used for: Banking transactions, Inventory management, Payments, ACL / permissions, Metadata
// stores (Zookeeper, etcd)
//  Trade-off: High correctness, Lower availability, Client-visible failures during partitions

//  AP (Availability): You prefer to always return a response, even if it's stale.
//  Used for: Social media timelines, Caches, Feed systems, Shopping cart, Logging/analytics, Search
// indexes
//  Trade-off: High availability, Eventual consistency, Requires conflict resolution (CRDT, LWW)

//  “It depends on whether stale reads are acceptable.
//  If correctness and ordering matter, I choose CP.
//  If availability and performance matter more, I choose AP.
//  Real systems usually mix both.”

//  Real-World Databases:
//  System	                     CAP Choice	                        Reason
//  Zookeeper/etcd/Consul	          CP	            Strong consistency needed for leader election,
// locks
//  HBase/Spanner	                  CP	            Strong row consistency
//  MongoDB (default)	              CP	            Single-leader model with majority writes
//  Kafka	                          CP            	Partitions halt writes until leader election
//  Cassandra / DynamoDB	          AP            	Always writable; resolves conflicts later
//  Redis (clustered)         	Configurable	      Can be AP or CP depending on replication settings

//  Why C+A both not possible with network partition?

//  “Because if the nodes cannot communicate, they cannot guarantee consistent state.
//  To maintain consistency, at least one node must stop serving requests, which breaks
// availability.
//  To maintain availability, nodes must serve requests independently, which breaks consistency.
//  Therefore, during a partition you cannot guarantee both C and A at the same time.”

//  Type of Consistency:

//  Strong Consistency: Every read returns the latest successfully written value, as if there is
//  only a single copy of the data.
//  Appears as if the whole system is a single machine. eg. Google Spanner, Zookeeper etc.
//  Core Mechanism:
//  1. Single Leader (Primary) Model:
//  Step 1: Write goes to leader.
//  Step 2: Leader sends update to replicas.
//  Leader -> Follower A
//  Leader -> Follower B
//  Step 3: Wait for acknowledgment - Leader waits for: All replicas OR Majority (quorum).
//  Only then Write is considered committed.
//  Step 4: Read is served - Now Any node can safely return latest value or reads are routed to leader.
//  2. Consensus Protocols: Strong consistency relies on consensus algorithms like: Paxos, Raft.
//  3. Quorum-Based Commit
//  4. Read Mechanisms:  Option A: Read from Leader
//  Option B: Read from Followers (with guarantees)
//  Follower must Be up-to-date or validate freshness before serving
//  5. Write-Ahead Log (WAL): Leader:
//  Writes to log first
//  Replicates log entries
//  Followers apply in same order

//  Eventual Consistency: A system is eventually consistent if, after some time without applying new
// updates, all replicas
//  converge to the same state. Very high availability, Used for large-scale distributed systems
//  eg. DynamoDB, Cassandra (default mode), S3, CDN caches, DNS
//  If you update your profile picture, your friend may see old one for a few seconds,
//  but eventually everyone will see the new one.
//  Core Mechanism:
//  1. Write Happens (Fast): Client sends write, PUT X = 100. System writes to One node (leader or
//  any replica). No waiting for others -> low latency.
//  2. Async Replication (Key Idea): Happens in background. This is why inconsistency exists
// temporarily. At time T, Different users may see different values
//  3. Anti-Entropy / Sync: Nodes periodically sync.
//  Pull missing updates
//  Compare versions
//  Repair differences
//  Common techniques: Gossip protocols, Merkle trees
//  4. Conflict Detection: If concurrent writes happen:
//  Node A -> X = 100
//  Node B -> X = 120
//  System must detect.
//  5. Conflict Resolution Strategies:
//  a) Last Write Wins (LWW): Use timestamp. Latest wins.
//  b) Versioning: Detect concurrency. Merge intelligently.
//  c) Application Logic
//  7. Convergence: After sync + conflict resolution. System becomes consistent again.

//  Causal Consistency: If operation B is causally dependent on operation A, then everybody must see
// A before B.
//  If two operations are unrelated, they can be seen in different orders.
//  You post: “Hello", Then you comment: “Adding more info”
//  Everyone must see the post before the comment, but posts of different people don't require
// ordering.
//  This is stronger than eventual consistency, but weaker than strong consistency.
//  Usage:
//  Social Media Feeds (Comments must follow posts, Replies must follow comments)
//  Collaborative Applications (Google Docs-like systems, Real-time editors)
//  Messaging Systems, Event-Driven Architectures like Apache Kafka (Events often depend on previous
// events)
//  Distributed Caches (Cache updates depend on previous state)
//  Core Mechanism:
//  1. Track dependencies
//  2. Delay operations if dependencies are missing
//  3. Apply updates in causal order

//  Read-your-write: After a client writes data, that same client must always see their own latest
//  write, even if other replicas have not caught up. Guarantee is per-client, not global.
//  You update your profile picture — You must always see the new picture immediately, even if
//  others see the old one for some time.
//  Usage:
//  User Profile Updates
//  Messaging Systems (You always see your sent message)
//  E-commerce Cart (Cart reflects your action instantly)
//  Dashboard / Admin Panels
//  Draft / Content Creation (You save a draft, Reopen it, Must reflect latest version)
//  Core Mechanism:
//  Write -> Version -> Read Enforcement (Most Important):
//      Step 1: For Write. Server assigns a version = v5
//      Step 2: Client stores version, lastSeenVersion = v5
//      Step 3: Client sends version with read GET /data, Header: min-version = v5
//      If replica has: v5 or higher version then return or else lower -> don’t serve
//      Instead:  for wait. route to fresher replica OR, read from leader.
//  Mechanism 2: Read from Leader (Simplest). Trade-off: Higher latency, Less scalability.
//  Mechanism 3: Write-through Cache. Cache becomes source of truth for that session. Used heavily
// in real systems.
//  Mechanism 4: Session Stickiness: Route user to same replica where write happened.
//  Works if that replica has applied the write, Fails if Writes go to primary but reads go to
// replica.
//  Mechanism 5: Token / Session Context: Client carries a consistency token:
//  sessionToken = { lastWriteTimestamp: T5 }, Server ensures: response.timestamp >= T5
//  Mechanism 6: Client-side State: Very common in frontend-heavy systems.
//  User updates data. UI immediately reflects change. Backend syncs later.


//  Monotonic reads: If a client has read a value, the system guarantees that they will never see an
//  older value again. Reads never “go back in time.”
//  First read: your balance is $500, Next read will never show $450 even if a replica is stale
//  Usage:
//  News Feed / Timeline (see posts up to time T2 and You refresh, Feed is at least as fresh as what
// you already saw)
//  Email / Notifications
//  Banking / Balance Display
//  Logs / Monitoring Dashboards

//  Core Mechanism: Each read is at least as fresh as the previous read by that client/session.
//  Approach 1: Version Tracking - Each data item has a version v1 -> v2 -> v3
//  Server must return: version >= lastSeenVersion

//  Approach 2: Logical Timestamps - Each read tagged with timestamp T1 < T2 < T3
//  Client ensures that Next read timestamp >= previous

//  Approach 3: Replica Selection, In distributed systems, Different replicas have different lag.
//  Strategy - Always read from same or fresher replica.

//  Approach 4: Session Stickiness
//  Route all requests from a user/session to the same server or replica for a period of time.
//  How it works:
//  1. Load Balancer Level: Load balancer maintains mapping:  User -> Server
//  Set-Cookie: SERVER_ID=A, Next requests: Route -> Server A
//  IP Hashing, hash(user_ip) -> server
//  2. Application-Level Stickiness: Store session -> assigned replica. Route internally.
//  3. Database-Level Stickiness: Write -> Primary. User reads -> same replica OR primary.

//  Quorum consistency: In quorum-based systems, consistency depends on the number of replicas that
// must acknowledge:
//  N = total replicas, W = number of replicas that must confirm a write, R = number of replicas
//  that must respond to a read.
//  To achieve strong-ish consistency, R + W > N
//  Meaning: read and write quorums overlap ? at least one replica that has the latest write is
// always read.
//  Eg. Replicas (N = 3), Write to W = 2 nodes, Read from R = 2 nodes
//  Cassandra, You can choose:
//      ONE
//      QUORUM
//      ALL
//  Write: W=1 ? extremely fast, but eventual consistency
//  Write: W=QUORUM ? strongly consistent reads if R also quorum
//  Benefits:
//  Tunable consistency, High availability, Fast writes
//  Clients can choose consistency level per request

public class CapTheorem {}
