package distributedsystem;


//  A timeout is a maximum time a system waits for an operation (API call, DB query, RPC, message ack)
//  to complete before treating it as a failure. Timeouts convert slow failures into fast failures.
//  In distributed systems: Failures are inevitable, Waiting forever is worse than failing.

//  Why Timeouts Are Mandatory (Not Optional):
//  In case of cascading failure. FAANG principle: Every remote call must have a timeout.

//  Types of Timeouts:
//  1. Connection Timeout: Time to establish a connection.
//  Example: TCP handshake timeout.
//  Too large ? slow failures
//  Too small ? false failures
//  2. Read / Response Timeout: Time waiting for response after request sent. Most important timeout.
//  3. Request / End-to-End Timeout: Total time allowed for entire operation. Common in API gateways.
//  4. Idle Timeout: Idle Timeout is the maximum time a connection is allowed to remain inactive
//  (no data sent or received) before the system automatically closes it.
//  If nothing happens on a connection for N seconds ? connection is terminated.

//  Timeouts in a Call Chain: Client ? API Gateway ? Service A ? Service B ? DB
//  Golden rule: Timeouts must decrease as you go downstream.
//  Example:
//  Client: 2s
//  Gateway: 1.5s
//  Service A: 1s
//  Service B: 500ms
//  DB: 200ms
//  Otherwise, upstream may timeout while downstream keeps working ? wasted work.

//  Timeout Pitfalls:
//  No Timeout: Worst mistake ? infinite waits
//  Too Large Timeout: Threads blocked, Retry storms, Latency amplification
//  Too Small Timeout: False failures, Retries overload healthy services
//  Same Timeout Everywhere: Breaks end-to-end SLAs, Causes orphan work

//  Timeouts + Retries (Correct Way): Timeout alone is not enough.
//  Correct combination:
//                            Timeout
//                            ? Retry (limited)
//                            ? Exponential Backoff
//                            ? Circuit Breaker

//  Timeouts and Idempotency: Retries triggered by timeouts can cause: Duplicate writes, Double charges, Inconsistent state.
//  Therefore: Retries must be idempotent
//  Timeouts and idempotency are tightly coupled.


public class Timeouts {

}
