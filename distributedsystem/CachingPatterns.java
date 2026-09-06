package distributedsystem;

//  Caching Patterns (Distributed System Architecture):

//  Caching is used to: Reduce latency, Reduce load on DB / downstream services, Improve throughput.
// Reduce cost
//  But caching introduces staleness, consistency, invalidation, eviction, hot spotting, and memory
// pressure problems.

//  Let’s go through each pattern.

//  1. Cache Aside: App first checks cache ? if miss ? fetch from DB ? put in cache ?
// return.
//  Simple and widely used, Works well with read-heavy workloads.
//  Stale data possible if DB updates but cache invalidation fails.
//  Used in: Redis as cache-aside in 80% of microservices, Netflix, Uber services.

//  4. Read-Through Cache: The app never talks to the DB directly. Cache layer fetches on miss,
//  Cache stores it and return to app. App doesn’t need to handle DB fallback logic.
//  Cache becomes central dependency, Harder to scale.
//  Very popular in enterprise Java, Spring, Hazelcast, Coherence.
//  High read / low write
//  Expensive DB queries
//  User profile / session data (Frequently accessed, rarely changing)
//  Product catalog / inventory (High read traffic and DB protected from repeated reads)
//  Precomputed recommendations
//  API response caching (microservices)

//  2. Write-Through Cache: Write goes to cache AND DB, usually synchronously. Cache and DB always
// consistent, No stale reads.
//  Higher write latency, DB still receives all writes. Cache stores even cold (unused) data.
//  Used in: Distributed user profile systems, Persistent session stores

//  3. Write-Behind (Write-Back) Cache: Write goes to cache only ? async background flush to DB.
//  Very low write latency, DB write load dramatically reduced, Good for batch updates.
//  Data loss if cache crashes, Most complex to maintain, Hard to preserve ordering.
//  Used in: High-frequency write systems, Analytics pipelines, Kafka-style asynchronous commit
// scenarios.

//  14. Write-Around Cache: Writes go directly to DB ? not cached. Reads eventually bring them into
// cache.
//  Useful when writes are frequent but reads are infrequent.
//  E-commerce order systems, Orders written frequently, Read occasionally later.
//  Social media activity streams (Writes are huge (likes, clicks, events))
//  IoT / event ingestion (Massive write throughput)

//  5. Refresh-Ahead Cache: Cache predicts expiration and refreshes before TTL(Time-to-live).
//  Avoids cache misses for frequently accessed keys, Best for hot keys.
//  Wrong predictions waste capacity, Adds refresh-storm risk.
//  Used in: Recommendation systems, Pre-computed leaderboards.

//  6. Distributed Cache (Shared Cache): Multiple app servers use a shared cache like
// Redis/Memcached.
//  Consistent view across instances,
//  Multiple microservices need same data
//  Simple to scale horizontally.
//  Session management (stateless services) (Store sessions centrally, Any instance can serve the request)
//  Network latency, Cache cluster becomes bottleneck.
//  High-scale read traffic (You need sub-millisecond access).
//  Rate limiting & counters

//  7. Local Cache (In-Memory Cache per Node): Each application node has its own in-memory cache.
//  Extremely fast (no network hops), Ideal for session caching.

//  Not suitable for global consistency.
//  Each node has stale data issues,
//  Memory pressure increases,
//  Cold start problem (New instance starts with empty cache)
//  Usage:
//  Microservices(Combine local + distributed cache) (high QPS systems)
//  API gateways (Cache auth tokens, routing rules)
//  Config / feature flags (Read frequently, updated rarely)
//  Database query results (short TTL) (Avoid repeated queries within same instance)

//  8. Two-Level Cache (Local + Remote) L1 + L2: Check local cache, Fallback to Redis (distributed
// cache),
//  Fallback to DB. Prevents L2 overload during spikes.
//  L1 reduces pressure on L2
//  L2 reduces pressure on DB
//  This reduces Redis load massively. Used by: Netflix, Uber, Airbnb.
//  Usage:
//  Hot key access patterns (Same keys accessed repeatedly)
//  Ultra high-QPS systems, Even hitting Redis for every request is costly

//  9. Content Delivery Network (CDN) Caching: CDNs cache static content at hundreds of locations
// globally.
//  Millisecond latency improvements,
//  Reduces origin load by 90%,
//  Great for static content, video, images.
//  Long propagation delay for invalidations, Hard with dynamic content.

//  10. Request Coalescing (Stampede Prevention): If thousands of requests hit the same missing key:
// Without protection ? DB meltdown.
//  With request coalescing: Only one request loads the data ? others wait. Used in: Twitter
// timeline services, Pinterest image metadata.

//  11. Negative Caching: Store “not found” responses temporarily to avoid repeated DB hits.
//  If a product ID does not exist ? cache “null” for 5 minutes. Prevents brute-force DB scans.

//  12. Bloom Filters to Prevent Cache Miss Storms: Bloom filter used to quickly tell if key exists
// or not.
//  If Bloom says “not exists”, ? skip cache ? skip DB ? return 404 instantly.
//  Used by: Facebook HBase row existence checks, LinkedIn Voldemort.

//  13. Shard-Aware Caching: Cache is partitioned using same shard key as DB. Prevents cross-shard
// lookups.
//  Used in: Cassandra row cache, DynamoDB DAX.

//  Cache Invalidation Patterns: Cache invalidation is the hardest thing in distributed systems.
//  Three policies:
//  A. Delete on Write (Most common): When DB is updated ? delete cache key.
//  B. Update Cache Immediately: When DB updates ? also update cache
//  C. Invalidate via Events: DB change events (CDC) ? cache invalidates/updates asynchronously.
// Used in Kafka CDC ?
//  Redis cache invalidation, DynamoDB Streams ? Cache updates.

//  Cache Eviction Policies: When the cache is full and new items arrive, what should we remove?

//  LRU — Least Recently Used: Evict the item that has not been used for the longest time. Used in
//  Redis, Memcached.
//  Browser caching (Recently visited pages stay in memory)
//  Microservices caching (Frequently accessed entities cached)
//  Local caches (very common)
//  Hot data changes frequently

//  LFU — Least Frequently Used: Evict the item that is least accessed overall.
//  CDN / content caching (Popular content stays cached longer)
//  Database / query caching (Frequently requested queries should stay cached)
//  E-commerce hot items (Popular products accessed repeatedly)

//  FIFO — First-In First-Out: Evict oldest item based on arrival time, regardless of usage.
//  Streaming / pipeline workloads (Data flows in order and is processed once)
//  Queue-like systems (Tasks processed in arrival order)

//  Random Eviction (RAND): Evict a random item. Used in Redis (volatile-random),High-performance
// in-memory systems.
//  Ultra-low overhead systems (You want minimal CPU + memory overhead), Useful in very
//  high-throughput systems
//  Very large caches (Tracking LRU/LFU becomes expensive at scale)

//  MRU — Most Recently Used: Evict the most recently accessed item.
//  Imagine reading a huge log file: You read line 1 -> never revisit
//                                    Read line 2 -> never revisit

//  ARC — Adaptive Replacement Cache: A self-tuning hybrid of LRU and LFU. It Maintains: LRU list
// (recency) and LFU list (frequency)
//  Automatically shifts capacity between them based on access patterns.
//  Storage systems / filesystems (major use case), Mix of: sequential reads (scan workloads)
//  and repeated reads (hot data)
//  Database buffer caches (DB deals with hot rows (frequent access) and recent queries

//  SLRU — Segmented LRU: Separate cache into:
//  Probation segment -> new entries and Protected segment -> recently used entries.
//  Eviction happens from the probation segment first.

//  TinyLFU (Used in High-Performance Caches Like Caffeine): Use a small, approximate frequency
//  counter (Count-Min Sketch) to estimate frequency.
//  Great for massive caches. Used in Caffeine (Java’s best cache), LinkedIn systems.
//  Usage: Distributed caches (high QPS), Need high hit ratio with limited memory
//  Microservices / API caching (Mixed workload: hot endpoints and random requests)
//  Trade-offs: Approximate (not exact), Frequency is estimated
//  More complex than LRU, Harder to implement
//  Slight CPU overhead For maintaining sketch

//  Hybrid Eviction Policies (Production-grade):
//  LRU + TTL: Evict least recently used OR expired. Common in Redis/Memcached.
//  LFU + TTL: Favored for stable hot keys (e.g., product catalog).
//  Segmented LRU (SLRU) + TTL: CDN-style behavior.
//  Admission + Eviction Policy: Reject items that will likely not be re-used. Used in Caffeine and
// CDNs.

//  Cache Hit Ratio Optimization Guide: Cache Hit Ratio = hits / (hits + misses)

//  The higher the hit ratio ? the lower the latency ? the lower the DB load ? the cheaper the system.
//  A Lead engineer must know how to maximize hit ratio.
//  1. Choose the Right Eviction Policy
//  2. Optimize TTL (Time to Live) and Expiry Policies: TTL too short ? data expires too fast ? high
//  miss ratio. TTL too long ? stale data & memory pressure ? low effective hit ratio.
//  Rule of thumb: Frequent updates -> short TTL, Rarely updated data -> long TTL
//  3. Request Coalescing
//  4. Warm Up Your Cache (Cache Preloading)
//  5. Avoid “Never-Reused” Items Flooding the Cache
//  6. Partition Cache Correctly (Shard Awareness)
//  7. Increase Effective Memory (But Smartly)

public class CachingPatterns {}
