package distributedsystem;

//  Tracing (OpenTelemetry):

//  Distributed Tracing is a technique to track a single request as it flows across
//  multiple microservices, processes and infrastructure components.
//  It answers:  “What happened to my request, where did it go, how long did each step take, and where did it fail?”
//  Without tracing, a microservices system is a black box.

//  Core Tracing Concepts:
//  1. Trace: A trace represents the entire lifecycle of a request.
//      Example: User ? API Gateway ? Auth ? Orders ? Payment ? Inventory, All of this together = one trace.
//  2. Span: A span represents one unit of work.
//      Examples: HTTP request, DB query, Cache lookup, Kafka publish.
//      Each span has: start time, end time, duration, metadata (tags)
//  3. Parent–Child Relationship: Spans form a tree:
//            API Request (root span)
//             |- Auth call
//             |- Order validation
//             |- Payment call
//             |   | DB write
//             |- Inventory check
//  This hierarchy is crucial for latency analysis.
//  4. Trace Context Propagation: Every request has tracing headers: trace_id, span_id.
//  These headers propagate across: HTTP, gRPC, Message queues, Async jobs.
//  Without propagation -> trace breaks.


//  OpenTelemetry:
//  OpenTelemetry is: An open standard, A set of APIs, SDKs, and agents, For traces, metrics, and logs.
//  It is vendor-neutral. OTel doesn’t store data — it collects and exports it.

//  OpenTelemetry Architecture (High Level):
//        Application
//         |__ OpenTelemetry API
//              |__ OpenTelemetry SDK
//                   |__ Exporters
//                        |__ OpenTelemetry Collector
//                             |__ Observability Backends

//  1. OpenTelemetry API (Instrumentation Layer): Language-specific interfaces, Used by application code and libraries.
//  Defines what telemetry looks like (not how it’s exported). API has no logic — only interfaces.
//  2. OpenTelemetry SDK (Processing Layer): Concrete implementation of the API, Used by applications (not libraries).
//  Sampling decisions, Span creation lifecycle, Metric aggregation, Context propagation, Export scheduling.
//  3. Instrumentation (Very Important): Instrumentation is how telemetry is produced. It has 2 types.
//  a) Automatic Instrumentation: No code changes, Uses: Bytecode agents, Language runtime hooks, Middleware interception.
//  eg. HTTP servers, gRPC, DB clients, Messaging systems
//  b) Manual Instrumentation: Developers explicitly create spans/metrics.
//  eg. start span "processOrder"
//      |__ call payment service
//  end span
//  4. Exporters (Output Layer): Convert telemetry to backend formats, Send data over network.
//  5. OpenTelemetry Collector (Control Plane): The collector is the central processing and policy engine.
//  Receive telemetry from services. Batch, retry, compress. Apply sampling. Filter attributes. Route to multiple backends.

//  Request Flow (Simple):
//  Client Request -> API Gateway (span) -> Service A (span) -> Service B (span) -> DB (span) -> Response
//  All spans share same trace_id.
//  Imagine: 1 million requests/sec, Each request touches 10 services, Each trace has ~50 spans.
//      That’s 50 million spans/sec ->
//      -> Too expensive, too slow, too noisy.
//  Sampling answers: Which requests are worth tracing?

//  Sampling (VERY IMPORTANT): At FAANG scale, you cannot trace everything.
//  Sampling is the technique of recording only a subset of requests/traces instead of every single one, to:

//  Reduce performance overhead, Reduce storage cost, Keep tracing scalable at high QPS.
//  Types:
//  Head-based sampling(Early Sampling):
//  Decision is made at the start of the request. Simple, cheap. Might miss rare failures.
//  Request arrives, SDK generates a trace ID, Sampling algorithm runs, Decision is stored in trace context.
//  All downstream services follow it. Simple, Works well with sidecars but You don’t know if request will fail yet,
//  May miss rare errors. It has two types:
//  a) Probabilistic Sampling: Sample a fixed percentage of requests. Example: 1% of all traffic.
//  b) Rule-Based Sampling: Sample based on attributes (endpoint, method, tenant).
//  Example: Checkout -> 100%, Health checks -> 0%
//  Why Head Sampling Alone Is Not Enough (Key Insight)?
//  Head sampling is blind to outcomes. Example: Error rate = 0.1%, Sampling rate = 1%, Most failures are never captured.

//  Tail-based sampling(Smart Sampling): Decision is made after the request completes. Decide after seeing full trace.
//  Capture slow requests, Capture failed requests, Expensive but powerful.
//  Collect all spans temporarily, See latency, errors, status, Decide to keep or drop.
//  eg. Keep 100% of errors, Keep slow requests (> 1s), Keep rare endpoints, Sample normal traffic at 0.1%
//  High signal quality, Great for debugging, Captures rare failures.
//  High memory usage, More complex, Needs buffering.

//  Hybrid Sampling (FAANG Preferred): Head sampling for scale, Tail sampling for errors & latency.
//      eg. Head sample at 1%, Tail sample: - 100% errors, - 100% P99 latency.
//  This balances: Cost, Signal, Debuggability.

//  Common Sampling Strategies:
//  1. Probability Sampling: Sample 1 out of N requests. Simple and Uniform.
//  2. Rate-Limited Sampling: Max 100 traces/sec, Protects backend.
//  3. Rule-Based Sampling: If status = 500 ? keep, If latency > 2s ? keep, If endpoint = /payment ? keep. Very common in FAANG.
//  4. Adaptive Sampling: Sampling rate changes dynamically based on traffic volume.
//  High QPS ? lower rate
//  Low QPS ? higher rate
//  Used in production systems.

//  Sampling + High Cardinality (Critical Link): Sampling is mandatory when you have:
//  user_id, order_id, session_id, request_id
//  High cardinality × no sampling = system meltdown.

//  Sampling Propagation: Once a trace is sampled: The sampling decision is propagated. Downstream services must respect it.
//  This ensures: ? Full trace visibility, ? No partial traces.

//  In observability (metrics, tracing, logging), a tag (label) is metadata attached to telemetry data.
//  Cardinality = number of unique values a tag can have.
//  Simple definition: High cardinality tags are tags whose values change frequently and can grow very large in number.
//  eg. High cardinality tag,    user_id = 12345, 67891, 99872, ... Millions of possible values ? ? high cardinality.


//  How FAANG Uses Tracing in Practice:
//  Latency breakdown (p95, p99)
//  SLO/SLA validation
//  Root cause analysis
//  Dependency mapping
//  Capacity planning
//  Canary analysis

//  Common Tracing Pitfalls:
//  Performance overhead
//  High cardinality tags
//  Over-instrumentation
//  Sampling hides rare bugs
//  Async boundaries losing context
//  Broken context propagation

public class OpenTelemetry {

}
