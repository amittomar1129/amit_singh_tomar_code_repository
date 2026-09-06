package distributedsystem;

//  Partitioning / Sharding in Distributed Systems

//  Partitioning is a logical division of a database table into smaller pieces (partitions),
//  but all partitions still live within the same database system (same server or cluster).
//  To improves query performance and manageability.

//  Example: A users table is partitioned by country:
//
//  Partition 1 -> US users
//  Partition 2 -> India users
//  Partition 3 -> UK users
//  But all of this is still in the same database instance.











//  Sharding means splitting data into smaller, independent segments,
//  and distributing them across multiple servers/nodes. This is required when:
//  Data is too large for a single machine.
//  Query load is too high.
//  You need horizontal scaling.
//  You want lower latency by placing data closer to users.i

//  Sharding: Divide Big Dataset -> Spread Across Many Machines ? Each shard handles only a subset.

//  Adv:
//  Horizontal scalability (infinite scaling by adding more nodes)
//  Performance (lower latency because fewer records per shard)
//  Load distribution (prevent hot spotting)
//  Fault isolation (failure in one shard doesn’t take entire system down)
//  Cost efficiency (cheaper commodity hardware vs giant servers)

//  FAANG companies deal with petabytes of data, so sharding is unavoidable.

//  Key Goal of Sharding. Design a partitioning scheme where:

//  Data is balanced evenly.
//  Reads/Writes are distributed uniformly.
//  Cross-shard communication is minimized.
//  Scaling a shard does not require downtime.
//  Movement of data between shards is minimal.

//  Sharding Strategies (Most Important Section):

//  1. Range-Based Partitioning: Partition by numeric range, time, or lexicographic range.
//  Shard 1 ? user_id 1–1M
//  Shard 2 ? user_id 1M–2M
//  Shard 3 ? user_id 2M–3M
//  ? Pros: Efficient range queries (BETWEEN, time windows), Easy to implement
//  ? Cons: Hotspot problems (e.g., latest time range gets more writes), Rebalancing is expensive
//  Eg. HBase, Bigtable, Elasticsearch time-based indices

//  2. Hash-Based Partitioning: Apply a hash function to key ? assign to shard.
//  shard = hash(user_id) % num_shards
//  ? Pros: Best load distribution, Prevents hot spots, Fast lookup
//  ? Cons: Range queries become expensive (Hashing destroys natural ordering.) (need fan-out),
//  Hard to scale (adding shards changes hash results ? data moves), Rebalancing is Expensive
//  Eg. Cassandra, DynamoDB, MongoDB (hashed sharding)

//  3. Consistent Hashing (Dynamo style): Used when cluster nodes change frequently. Data is mapped on a hash ring.
//  shard = hash(user_id) % num_shards
//  ? Pros: When a node is added/removed, only ~1/N data moves, Great for dynamic scaling, Minimizes rebalancing
//  ? Cons: Implementation is more complex, Uneven distribution unless virtual nodes used
//  Eg. Cassandra, DynamoDB, Kafka partitioning, Redis Cluster

//  4. Directory-Based (Lookup Table) Partitioning: A service stores a mapping table.
//  user_id ? shard location
//  ? Pros: Very flexible, Can rebalance easily, Can support complex rules (tier-1 users on faster shards)
//  ? Cons: Lookup service becomes a single point of truth, Must be highly available (Zookeeper/Consul)
//  Eg. Instagram, Metadata services, MySQL Fabric

//  5. Geo-Sharding / Location-Based Partitioning: Partition by user’s geography.
//  Europe users in EU datacenter, US users in US-east and US-west, Asia users in Singapore/Tokyo
//  ? Pros: Super low latency, Compliance-friendly (GDPR), Reduce cross-region traffic
//  ? Cons: Cross-region queries become slow, Complex failover strategies
//  Eg. Facebook’s TAO, Uber (city-level sharding), Google Cloud Spanner (multi-region)












//  Must-Know Concepts About Sharding:
//  1. Hot Spotting: Hot Spotting occurs when a disproportionate amount of traffic (reads or writes)
//  gets directed to one shard, one server, or one partition in a distributed system.
//  Hot spotting usually happens because your partition key is not uniformly distributed.
//  eg. -Auto-increment primary key (RDBMS) id = 1, 2, 3, 4, …   your shard is hash(id) % 4
//  - Using user_id as range ? last shard gets 90% of traffic.
//  - Time-based ranges ? “latest” shard overloaded. Then all writes for current date (today) go to a single partition, causing hotspot.
//  - IoT Sensor Data: If all devices send data at the same time, and partition key is “timestamp”,
//  Solution: Hashing or hybrid partitioning.

//  2. Rebalancing: When a shard gets too large or too hot:
//  Split shard
//  Move partitions
//  Update routing layer
//  Consistent hashing helps reduce movement.

//  3. Cross-Shard Joins: In a sharded/partitioned database, data is split across multiple shards:
//  Shard 1    Shard 2    Shard 3   ...

//  A cross-shard join is a JOIN operation where the data involved in the query resides on multiple different shards.
//  Table: Orders is sharded by user_id
//  Table: Payments is sharded by payment_id
//  Cross-shard joins are extremely expensive. They are expensive because they require scatter-gather queries,
//  cross-network data transfers, and distributed query execution, which increases latency, tail risk, and load on the entire cluster.

//  Avoid Cross-Shard Joins:
//  1. Co-Sharding (Co-Partitioning): Shard tables using the same key so that related rows live on the same shard.
//  eg. Orders and Payments both sharded by user_id.
//  2. Denormalization (Copy the Data): Instead of joining: Orders JOIN Users, Embed the necessary user fields inside Orders.
//  3. Pre-computed materialized views: Compute JOINs ahead of time, not at query time.
//  4. Application-level JOINs: This is the most common approach in microservices.
//  Query shard 1 for Orders,  Query shard 2 for Payments, JOIN in application code

//  4. Global Secondary Index (GSI) Problems: A Global Secondary Index is an index whose key is different
//  from the primary partition key of the table.
//  eg. DynamoDB: GSI on email while table is partitioned by user_id.

//  A GSI must index rows that live across many shards, which means: The index itself is also sharded.
//  Every write must update multiple locations (table + index). Ordering and consistency become harder.
//  Used in DynamoDB, Cassandra.

//  GSIs look simple but create serious distributed systems challenges.

//  -Write Amplification: A single write to the base table becomes: Write to main partition. Additional writes to index partition(s).
//  Systems like Cassandra discourage secondary indexes for this reason.

//  -Global Index Hotspotting: GSIs often use low-cardinality fields as index keys, eg. country = "India",
//  status  = "ACTIVE" or category = "Sports".
//  This causes hot partitions because, All rows with the same key map to the same index shard. The index shard becomes overloaded.
//  DynamoDB uses write throttling, Cassandra sees high CPU, Bigtable sees tablet overload.

//  -Eventual Consistency: Most distributed systems allow GSIs to become eventually consistent.
//  Because updating the index atomically with the base row requires a distributed transaction ? expensive and slow.
//  So GSIs often use async propagation

//  -Expensive Backfills / Rebuilds: For large tables (100M–1B rows), rebuilding a GSI is extremely expensive.

//  -Query Fan-Out: If index is poorly partitioned:Query engine must scan multiple index partitions.
//  Fetch matching primary rows, Merge results, Network-bound operations ? slow.

//  -Write Ordering Problems: If writes arrive in this order: Update base table, Update index, Update base table again
//  Update index again. Network delays may reorder these updates.
//  Index may end up in an inconsistent state.

//  Distributed systems cannot guarantee: Global ordering, Synchronized updates, Atomic multi-shard writes
//  Unless using a distributed transaction (very expensive).

//  5. Multi-Shard Transactions: A multi-shard transaction is a transaction that reads/writes data
//  located on multiple different shards in a distributed database. Example:
//  User lives on shard A
//  Order lives on shard B
//  Inventory lives on shard C
//  Updating all three in one atomic transaction is a multi-shard transaction.

//  Distributed transactions require:
//  Two-phase commit (slow, not recommended)
//  Why Multi-Shard Transactions Are Hard (The Core Distributed Systems Problem):
//  different shards live on different machines, so coordinating a transaction requires:
//  Distributed locking, Distributed commit protocol, Distributed logging
//  Handling partial failures, Handling partitions

//  6. Routing Layer / Shard Locator:
//  There must be a layer that determines the shard:

//  Routing Layer, Its responsibilities:
//  Map key ? shard ID
//  Map shard ID ? physical node(s)
//  Handle rebalancing
//  Handle shard migrations
//  Handle failures
//  Maintain routing metadata
//  Cache the routing topology

//  a. Service-level routing (app calculates hash)
//  b. Database coordinator node (mongos, Cassandra coordinator)

//  All distributed systems use one of these two models:
//  Model 1: Algorithmic Shard Locator: Use a deterministic function to map key ? shard.
//  eg. Hash(key) % N, Consistent hashing, Range-based routing. This model has no central metadata server.
//  Model 2: Directory-Based Shard Locator (Metadata Service): A central metadata service keeps track of:
//  Shard ? node mapping, Node health, Replica locations, Leader/follower info.

//  Why Do FAANG Systems Prefer Directory-Based Routing? Because it supports:

//  1. Dynamic shard splitting: If a shard grows too large: Shard 8 ? split into 8a + 8b.
//  The directory updates mappings instantly, without client downtime.
//  2. Rebalancing: Move hot shards to new nodes seamlessly.
//  3. Leader elections: Directory knows who the current leader is.
//  4. Multi-region awareness: Directory stores region/zone of shards.


//  Real FAANG Examples to Impress Interviewers:
//  Netflix
//  Uses Cassandra (consistent hashing)
//  Avoids cross-shard transactions
//  Write-heavy workloads optimized via hash partitioning
//
//  Uber
//  City-based geo-sharding
//  Rider and Driver data partitioned by location
//
//  Facebook (TAO)
//  Causal consistency across geo-shards
//  Write-through caches with regional replication
//
//  Google Spanner
//  Combines range partitioning + synchronous replication
//  Automatically splits hot partitions

public class Partitioning {

}
