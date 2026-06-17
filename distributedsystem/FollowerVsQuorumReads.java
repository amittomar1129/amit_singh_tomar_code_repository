package distributedsystem;


//  Reads from Followers vs Quorum Reads:

//  The Core Problem Being Solved: In replicated systems:
//  Data is copied across multiple nodes.
//  Writes go to a leader (or coordinator).
//  Reads can go to different places.
//  Key tension:
//    Lower latency vs stronger consistency

//  Reads from Followers (Replica Reads): Read directly from a replica / follower, Leader not involved.
//  Why Systems Do This: Reduce read latency, Scale read throughput, Reduce load on leader.
//  Example: MySQL read replicas, Cassandra (RF > 1, CL=ONE), Dynamo-style systems.
//  Consistency Properties: Eventual consistency, Reads can be stale, No guarantee of read-your-writes.
//  Follower reads trade correctness for performance.
//  When FAANG Uses It: Feeds, Analytics, Recommendations, Caches. L6 signal: say “safe when staleness is acceptable”.

//  Quorum Reads: Read from multiple replicas. Return value agreed upon by quorum.
//  Classic rule: R + W > N  Where: R = read replicas, W = write replicas, N = total replicas.
//  Consistency Properties: Stronger consistency, Read-your-writes, No stale reads (under assumptions)
//  Example: Cassandra (CL=QUORUM), DynamoDB (strong read), Spanner (internally).
//  Quorum reads sacrifice latency and availability for correctness.
//  Trade-offs: Higher latency, More network calls, Lower availability under partitions.

//  Real-World Systems Mix Both:
//  Hot path -> follower reads
//  Sensitive path -> quorum or leader reads

public class FollowerVsQuorumReads {

}
