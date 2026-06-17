package distributedsystem;

//  Backpressure Handling in Distributed Systems:

//  Backpressure is a mechanism by which a system signals upstream components to slow down when downstream components are overloaded.
//  Goal: Prevent overload, cascading failures, and system collapse.
//  Key insight: Backpressure is a stability mechanism, not an optimization.

//  Where Backpressure Appears: Service-to-service calls, Message queues, Streaming pipelines, Databases, APIs.

//  Core Backpressure Strategies:
//  1. Queue-Based Backpressure: Bounded queues, When full, reject or block producers.
//  eg. Thread pool queues, Kafka consumer lag. Simple but risks latency spikes.

//  2. Pull-Based Backpressure (Preferred): Consumer explicitly requests data. Producer sends only when requested.
//  Pull-based backpressure lets the consumer requests only what it can handle, preventing overload and improving system stability.
//  Often combined with batching, rate limiting, adaptive throttling
//  eg. TCP flow control, Reactive Streams, gRPC streaming.

//  3. Credit-Based Backpressure: consumer grants explicit “credits” (tokens) to the producer, and the producer is allowed to send
//  only that many messages.
//  Efficient and explicit.
//  eg. HTTP/2, Netty, Messaging systems

//  Backpressure in Microservices:
//  Timeouts
//  Circuit breakers
//  Concurrency limits
//  Adaptive throttling (Allow as many requests as the system can safely handle right now instead of 1000 requests/sec always)
//  Backpressure must propagate end-to-end.

public class BackpressureHandling {

}
