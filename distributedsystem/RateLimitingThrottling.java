package distributedsystem;

//  Rate Limiting & Throttling:
//  Rate limiting and throttling are traffic control mechanisms used to protect
//  distributed systems from overload, abuse, cascading failures, and unpredictable client behavior.

//  They appear in every major distributed system: API gateways, databases, CDNs,  caches, message queues, and microservices.

//  Rate Limiting (Client-Facing Control):
//  Rate limiting controls how many requests a client is allowed to make to a service in a given time window.
//  eg. “User can make 100 requests per minute.”
//  Protect system capacity, Prevent abuse, Control fair usage, Control cost, API monetization.

//  Rate Limiting Algorithms:
//  1. Token Bucket:  You have a bucket that stores tokens. Each token represents permission to process 1 request.
//  Tokens are added to the bucket at a fixed rate (e.g., 100 tokens/second). The bucket has a maximum capacity (burst limit).
//  Each incoming request:
//  Consumes 1 token if available ? ALLOWED
//  If no tokens are available ? REJECTED or DELAYED
//  Used by: AWS API Gateway, Google Cloud Endpoints, Envoy, Nginx.

//  2. Leaky Bucket: Imagine a bucket with water pouring in at variable speed (incoming requests).
//  A small hole at the bottom where water leaks out at a fixed constant rate (allowed throughput).
//  If too much water arrives too quickly. Bucket overflows. Overflowing water is discarded ? representing rejected requests.
//  Bucket has a fixed capacity (B). Incoming requests are enqueued (put in the bucket).
//  If the queue is full ? request is dropped/rejected. Requests are processed at a fixed rate R.
//  If incoming traffic > leak rate. Queue fills ? overload ? drop traffic.
//  Why It’s Called “Traffic Shaping”?
//  Because it shapes the input traffic into a predictable pattern before sending it downstream.

//  3. Fixed Window Counter: It rate-limits requests by counting how many requests come in during a fixed, discrete time window.
//  such as 1 second, 1 minute, 1 hour.

//  4. Sliding Window Log: It tracks the exact timestamp of every request, then decides whether to allow or block based
//  on the number of requests in the last X time period.
//  Receive new request. Read the log associated with the user:
//  It’s simply a list/queue of timestamps.
//  Remove all timestamps older than (now – window)
//  If log_size < max_requests: Accept request, Insert current timestamp.
//  Else: Reject request.
//  Why It’s the Most Accurate Rate Limiter?
//  Because it checks actual requests in the last T seconds. Doesn’t depend on window boundaries.
//  It is memory-heavy and CPU expensive.

//  5. Sliding Window Counter: The Sliding Window Counter is a hybrid approach combining:
//  Fixed Window Counter (fast, low memory) and Sliding Window Log (accurate).
//  It offers better accuracy than Fixed Window but lower memory/cost than Sliding Window Log.
//  A request is allowed/denied based on a weighted combination of: The current window count and The previous window count.
//  eg. Limit: 100 requests per minute.
//  Window: 1 minute
//  Windows: W_current: 10:01:00 ? 10:01:59, W_previous: 10:00:00 ? 10:00:59
//  For each request, compute: Effective requests = (weight_previous × count_previous) + count_current
//  If effective_requests < limit, allow.
//  weight_previous: How much of the previous window still overlaps with the last T seconds.
//  If window = 1 minute, and you're 15 seconds into the current minute, then 45 seconds of the previous minute still overlap.
//  weight_previous = overlapping_time / window_size = 45 / 60 = 0.75
//  So, We should count 75% of the previous window’s requests because 75% of that window still overlaps
//  with the sliding 60-second period.


//  Rate Limiting Patterns:
//  1. Per-user rate limiting: Different limits for each user.
//  2. Per-IP rate limiting: Protects from bots or scrapers.
//  3. Per-API key limiting: Common in SaaS products.
//  4. Geo-based or region-based limits: Stop attacks from specific regions.




//  Throttling (Server-Facing Control): Throttling controls how many requests the server is willing to process right now
//  based on real-time load. eg. “Server is at 90% CPU ? throttle traffic by rejecting or queuing requests.”

//  Avoid overload,
//  Prevents service from crashing.
//  Graceful degradation,
//  Drop low-priority traffic first.
//  Survive traffic burst,	Cold-start scenarios, flash sales, viral traffic.

//  Throttling Strategies:
//  1. Hard Throttling: Reject immediately
//  ? return 429, 503, or custom “Backpressure” response.
//
//  2. Soft Throttling: Queue requests temporarily.
//  ? if queue full ? reject. Used in Kafka, RabbitMQ, Redis
//
//  3. Priority Throttling: Drop low-priority traffic first:
//  Health checks
//  Background tasks
//  Free tier customers
//  Used by: AWS SQS, Azure EventHub, Google Cloud Pub/Sub

//  4. Adaptive Throttling: Uses service health to auto-adjust limits. It dynamically adjusts how many requests a service
//  should accept or reject based on real-time system health.
//  eg. Allowed rate = (success_count / total_count) * MaxRate

//  Throttling Patterns:

//  1. Tail-Drop / Head-Drop: Decide whether to drop newest or oldest requests.
//  2. Deadline-aware throttling: If request already too close to timeout, drop it.
//  3. Load-shedding: Drop traffic when queue is full or CPU is high.
//  4. Reactive backpressure: Tell upstream to slow down.



//  Rate limiting = external contract
//  Throttling = internal survival mechanism

//  Layer               	                             Uses Rate Limit?                	Uses Throttling?
//  API Gateway (CloudFront, Apigee, Kong)	            Yes                                	Rare
//  Load Balancer (Nginx/Envoy)	                        Yes                                 Yes
//  Microservices	                                      No	                                Yes
//  Databases	                                          No	                                Yes
//  Message queues	                                    No	                                Yes

//  Real-World Examples (FAANG Interview Gold)
//  ? Redis: Uses rate-limiting in front of Redis via NGINX or API gateway.
//  Redis itself does throttling internally to avoid blocking.
//
//  ? Kafka: Throttles producers based on broker load.
//  Rejects requests if partitions are slow.
//
//  ? DynamoDB: Throttles internally at partition level.
//  Gives ProvisionedThroughputExceededException.
//
//  ? Google Cloud Pub/Sub:
//  Automatically applies backpressure when subscribers lag.

//  Example Scenario (Very Common in FAANG Interviews):
//  A spike of requests hits your microservice. What happens?
//  Step 1 – API Gateway applies rate limits.
//  Free users: max 10 req/sec
//  Paid users: 100 req/sec
//  Enterprise: 1000 req/sec
//  Excess requests ? 429 Too Many Requests.
//  Step 2 – Service monitors itself.
//  CPU hits 80% ? trigger throttling rule.
//  ? Drop low-priority traffic
//  ? Queue or degrade
//  ? Protect critical flows
//  ? Maintain latency   SLOs

//  "How will you prevent overload?"
//  Answer:
//  ? I will apply rate limits at API gateway.
//  ? based on user-level quotas (per-token, per-IP, per-service).
//  ? and use adaptive throttling inside the service.
//  ? triggered by queue length + CPU + latency SLOs.
//  ? ensuring graceful degradation.
//  ? without violating SLA for premium customers.



public class RateLimitingThrottling {

}
