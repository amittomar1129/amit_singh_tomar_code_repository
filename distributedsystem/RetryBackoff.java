package distributedsystem;


//  Retry + Backoff(Distributed Systems):

//  Retry means automatically re-attempting a failed operation instead of failing immediately.
//  Failures are often transient, not permanent: Network blips, Temporary overload, Leader re-election
//  Cold starts, Short GC pauses. Retry improves resilience and availability.

//  Why Retry Alone Is Dangerous?
//  If you retry immediately and aggressively, you can:
//  ? Amplify failures
//  ? Overload an already struggling service
//  ? Create retry storms
//  ? Cause cascading failures
//  This is why Backoff is mandatory.

//  Backoff: Backoff means waiting before retrying, usually with increasing delays.
//  Retry + Backoff = “Try again, but more politely”

//  Retry: Retry immediately ? retry immediately ? retry immediately
//  Backoff: Retry ? wait ? retry ? wait more ? retry ? wait more

//  Types of Backoff (Interview Must-Know):
//  1. Fixed Backoff: Wait a constant time between retries. Retry after 100ms every time.
//  Simple but Can still overload services, Poor for large-scale systems. Rarely used at large scale.
//  2. Linear Backoff: Wait increases linearly. 100ms ? 200ms ? 300ms ? 400ms
//  Better than fixed but Still synchronized retries, Not aggressive enough under heavy load.
//  3. Exponential Backoff ? (Most Important): Delay doubles each time.
//  100ms ? 200ms ? 400ms ? 800ms ? 1.6s
//  Quickly reduces pressure, Self-throttling, Works well at scale. Default choice in distributed systems.
//  4. Exponential Backoff + Jitter: Add randomness to delay. Random(0, base * 2^attempt).
//  eg. Attempt 1 ? 120ms, Attempt 2 ? 310ms, Attempt 3 ? 650ms
//  Prevents thundering herd, Avoids synchronized retries, Critical at large scale. FAANG best practice.

//  Retry Pitfalls:
//  Retry storms
//  Infinite retries
//  Cascading failures
//  Retrying non-idempotent operations
//  Retrying permanent failures (4xx)

//  Retry + Idempotency: Only retry idempotent operations safely.
//  Without idempotency: Retry ? duplicate charge ? duplicate order ? data corruption.

//  Retry + Circuit Breaker (FAANG Combo): Retry alone is dangerous.
//  Correct pattern:
//          Retry (few attempts)
//             ?
//          Backoff + Jitter
//             ?
//          Circuit Breaker opens

//  When to Retry vs Not Retry:
//  Retry when:
//  ? Timeouts
//  ? Network errors
//  ? 5xx errors
//  ? Leader not available
//
//  Do NOT retry when:
//  ? Validation errors (400)
//  ? Auth failures (401/403)
//  ? Business rule violations
//  ? Permanent failures


//  Real-World FAANG Examples:
//  Google APIs: Exponential backoff + jitter
//  AWS SDKs: Built-in retry policies
//  Kafka consumers: Retry + DLQ
//  gRPC: Retry policies at client side
//  Payments: Retry only with idempotency keys


public class RetryBackoff {

}
