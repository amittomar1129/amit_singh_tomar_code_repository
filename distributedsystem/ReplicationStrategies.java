package distributedsystem;

//  Replication Strategies:
//  Replication means keeping multiple copies of data across nodes to achieve:
//  High availability,
//  Scalability,
//  Low latency,
//  Fault tolerance,
//  Disaster recovery

//  But replication introduces challenges like consistency, conflicts, divergence, write amplification, and failover complexity

//  There are 4 major replication strategies:

//  1. Synchronous Replication (Strong Consistency):
//  A write is considered successful only after ALL replicas have stored the update.
//  Client -> Primary -> All Replicas -> ACK -> Client. Used in Google Spanner, Zookeeper / etcd (uses quorum but strongly consistent).

//  2. Asynchronous Replication (Eventual Consistency):
//  Primary returns success immediately, and replicas update later in background.
//  Used in DynamoDB, Cassandra, MongoDB (eventual), Redis replication.

//  3. Semi-Synchronous Replication (Bounded Staleness): A hybrid model.
//  Primary waits for at least 1 replica to acknowledge, then completes the write. Other replicas update asynchronously.
//  Guarantees at least one up-to-date standby.
//  Used in MySQL "semi-sync" replication, Postgres synchronous, Some Kafka replication modes.

//  4. Quorum-Based Replication (Tunable Consistency):
//  This is used in AP systems (Cassandra, DynamoDB) to balance consistency vs availability dynamically.
//  For a cluster with N replicas: Write quorum = W, Read quorum = R, If R + W > N, strong consistency is guaranteed.
//  Example:  N = 3 ? W = 2 ? R = 2 ? 2+2 > 3 ? Strong consistency.
//  Tunable consistency per request. High availability (choose lower quorums). Low latency (choose lower R, W).
//  Cons: Higher write amplification, Hot partitions possible, Write conflicts if W < majority.
//  Slower under heavy cross-region quorum.

//  5. Leader-Based Replication: One node is the Leader/Primary, All writes go to leader. Followers replicate changes.

//  6. Leaderless Replication (Dynamo-Style): Writes go to any node. Use quorum voting, conflict resolution, vector clocks.

//  7. Multi-Leader (Active–Active) Replication: Multiple leaders accept writes in different regions and replicate to others.

//  8. Geo-Replicated Replication: Across continents. Two approaches:
//  A: Local Writes Only (Fast)- Each region writes locally ? async cross-region.
//  B: Global Synchronous Writes (Slow)- Commit waits for multiple regions.
//  Used only with carefully engineered systems: Google Spanner (TrueTime).

//  9. Replication Topologies:
//  Star (Primary ? followers): Simple, but single failure point.
//  Chain Replication: Writes flow through ordered nodes ? provides strong consistency with high throughput.
//  Gossip-Based Replication: Nodes share updates by gossip. Used in Cassandra for membership + state.

public class ReplicationStrategies {

}
