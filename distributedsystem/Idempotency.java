package distributedsystem;

//  Idempotency Pattern:

//  Performing the same operation multiple times produces the same result as performing it once.
//  In distributed systems, this is not optional — it’s mandatory.


//  Real-Life Example: Pressing “ON” on a light switch: ON ? ON ? ON, Result is still ON,
// Idempotent.
//  Pressing “ADD ?100” to bank balance:  +100 ? +100 ? +100, Not idempotent.

//  Why Idempotency Is Critical in Distributed Systems? Because retries are unavoidable.
//  Client retries
//  Load balancer retries
//  Message redelivery
//  Server restarts
//  Network timeouts
//  At-least-once delivery (Kafka, SQS)

//  How Idempotency Is Achieved?




//  1. Idempotency Key (Most Common): Client sends a unique key per logical operation.
//  POST /payments
//  Idempotency-Key: abc-123
//  Server logic:
//      IF key already processed
//      RETURN stored result
//          ELSE
//      process
//      store result with key
//  Used by: Stripe, Amazon, Google APIs.

//  good idempotency key looks like:
//  Globally unique (very low collision probability)
//  Opaque (the key has no meaningful business information embedded in it)
//  Stable across retries
//  Short-lived (TTL applied)

//  How idempotency keys are generated in real systems:
//  1. Client-generated (most common, recommended): ULID (time-sortable), Random high-entropy string.
//  2. Derived from request (deterministic key): Sometimes systems derive the key from request content
//  eg. hash(user_id + amount + timestamp_bucket)  Risk: Hard to guarantee uniqueness
//  3. Server-issued token (less common): Client asks server -> “give me operation token”
//  Use when you want strict control

//  Scope of uniqueness:
//  Key should be unique per: user? or API endpoint? or globally?
//  Most systems scope it like:
//      (user_id, idempotency_key)

//  In systems like Stripe:
//  Client generates a new key for each new payment attempt
//  Retries reuse the same key (that make operation to idempotent)
//  Keys expire after a TTL (e.g., 24 hours)

//  UPDATE account
//  SET balance = balance - 100
//  WHERE user_id = 1;
//
//  Two identical requests come at the same time.
//
//  Result:
//      1000 -> 900 -> 800 (Incorrect)
//  Because both requests execute independently.

//  If we use idempotency key in where clause, This still a wrong solution.
//  WHERE user_id = 1 AND idempotency_key = 123;
//  Database does not track “this key was already used”.

//  Solution: “Claim before doing work”.

//  Create a table
//  CREATE TABLE payment_requests (
//      user_id INT,
//      idempotency_key VARCHAR,
//      status VARCHAR,
//      UNIQUE(user_id, idempotency_key)
//  );

//  Try to CLAIM the request
//  INSERT INTO payment_requests (user_id, idempotency_key, status)
//  VALUES (1, '123', 'IN_PROGRESS');
//  We use database uniqueness as a lock.

//  Request A executes INSERT.
//  DB checks uniqueness -> no record
//  INSERT succeeds
//  Request A becomes owner

//  Request B
//  Executes same INSERT
//  DB checks uniqueness -> record already exists

//  Now only owner does the update
//  Request A:
//  UPDATE account
//  SET balance = balance - 100
//  WHERE user_id = 1;
//  balance = 900, Final Result (Correct)

//  Very Important:
//  This is fundamentally correct.
//  This is exactly the industry-standard approach.
//  But here’s the catch This works only if you make it atomic, durable, and retry-safe.

//  Handle INSERT failure correctly.
//  When INSERT fails:
//  DO NOT UPDATE
//  Instead:
//  Fetch existing record


//  Handle crash (VERY IMPORTANT at scale)
//  Problem:
//  INSERT -> IN_PROGRESS
//  crash happens
//
//  Now all retries see:
//  IN_PROGRESS forever
//
//      Solution:
//  Add:
//  created_at timestamp
//  Then:
//  IF IN_PROGRESS is older than X seconds -> retry

//  This above idempotency solution guarantees that Same request is executed only once and
//  it prevents duplicate payments, retries causing double deduction.

//  Let`s take a real scenario:
//  Two DIFFERENT requests
//  Request A -> deduct 100 (key=123)
//  Request B -> deduct 200 (key=456)
//  Without versioning, UPDATE account SET balance = balance - X;
//  Race condition: balance = 1000
//  A reads 1000 -> writes 900
//  B reads 1000 -> writes 800
//  Final: 800 Incorrect (should be 700)
//  Both are valid, different operations but still final result is incorrect or data lost.
//  We can solve this problem by adding versioning through optimistic locking.

//  But versioning (optimistic locking) guarantees Concurrent modification of the same data by different requests.
//  Explained in below point separately.






//  2. Optimistic Locking: Versioning is a mechanism. Optimistic locking is a strategy that uses that mechanism.
//  Versioning and optimistic locking alone cannot guarantee idempotency because they only detect write conflicts
//  and do not prevent re-execution of the same request.
//  With Optimistic Locking: Both requests run.
//
//      UPDATE account
//      SET balance = balance - 100, version = version + 1
//      WHERE user_id = 1 AND version = 1;

//  Request A:
//  version = 1 -> matches
//  updates:
//  balance = 900
//  version = 2

//  Request B:
//  tries with version = 1
//  fails (0 rows updated)

//  So far looks correct (balance = 900) but here is the twist.

//  What will Request B do after failure?
//  retry()

//  Retry flow:
//  Request B:
//  re-reads balance = 900, version = 2
//  runs again:
//  UPDATE ... WHERE version = 2;
//  succeeds
//  Final result 800 (Incorrect)

//  When CAN optimistic locking help? It is useful for:
//  preventing race conditions on updates
//  ensuring consistency of state

//  Solution for this problem: Idempotency + Optimistic Locking

//  High-level architecture: You combine two layers:
//  Layer 1 — Idempotency (gatekeeper)
//    Ensures only one request is allowed to execute
//  Layer 2 — Optimistic Locking (safety)
//    Ensures correct state transition under concurrency

//  idempotency must work at “request start time” but Optimistic locking works at “commit time”
//  Idempotency must be preventive (stop duplicate work before it starts)
//  Optimistic locking is reactive (detects conflict after work is done)


//  Create a table
//  CREATE TABLE payment_requests (
//      user_id INT,
//      idempotency_key VARCHAR,
//      status VARCHAR,
//      response VARCHAR,
//      version INT,
//      UNIQUE(user_id, idempotency_key)
//  );

//  Try to claim the request (atomic)
//  INSERT INTO idempotency_requests(user_id, key, status, version)
//  VALUES (1, '123', 'IN_PROGRESS', 1);
//  Request A
//  Insert succeeds -> becomes owner
//  Request B
//    Insert fails -> duplicate key

//  Now Request A continues performs business logic. Updating with optimistic locking.

//  UPDATE account
//      SET balance = balance - 100,
//          version = version + 1
//  WHERE user_id = 1 AND version = 1;

//  Store final result

//  UPDATE idempotency_requests
//      SET status = 'SUCCESS',
//          response = '{balance: 900}',
//          version = version + 1
//  WHERE user_id = 1 AND key = '123';

//  What Request B does when insert fails:?

//  SELECT * FROM idempotency_requests
//  WHERE user_id = 1 AND key = '123';

//  Case 1: status = IN_PROGRESS
//    wait / retry / poll
//  Case 2: status = SUCCESS
//    return stored response
//
//  No duplicate deduction

//  Final result: balance = 900 (Correct)
//  Even with concurrency, retries, failures

//  Critical edge case (interview gold):
//  What if system crashes after status = IN_PROGRESS
//  Next request sees:
//  IN_PROGRESS (stuck)

//  Solution:
//  add timestamp + timeout
//  allow retry if stale

//  Important: How Google implemented it in their apps?

//  Core philosophy at Google: “Make every request uniquely identifiable and use atomic conditional writes
//  to ensure exactly-once effect.”

//  They rely heavily on:
//  strongly consistent storage (like Spanner)
//  conditional mutations
//  request IDs (idempotency keys)

//  Storage systems used:
//  Google Cloud Spanner -> strongly consistent, globally distributed
//  Bigtable -> high throughput
//  Google Cloud Pub/Sub -> event-driven systems

//  How Google handles idempotency (core pattern):

//  Every request has a unique request ID
//  POST /payment
//  Request-Id: 123
//      This is your idempotency key.

//  Atomic “claim” using conditional write
//  In Spanner-like system:
//  Insert row IF NOT EXISTS (request_id = 123)

//  What happens?
//  First request -> succeeds
//  Second request -> fails
//
//   Exactly same idea as:
//    UNIQUE(request_id)

//  Key point: Google relies on transactional guarantees of Spanner instead of application-level locking.

//  Full transaction (important):
//  In Google Cloud Spanner, this is done in a single transaction:
//
//  BEGIN TRANSACTION
//      1. Insert request_id = 123 (fail if exists)
//      2. Update account balance
//      3. Store result
//  COMMIT

//  Why this is powerful because Spanner gives external consistency and serializability.
//  only one transaction succeeds, others abort automatically

//  What happens under concurrency: Two requests come at same time:
//  Request A
//  transaction starts
//  insert request_id succeeds
//  updates balance -> 900
//  commit

//  Request B
//  transaction starts
//  insert fails (already exists)
//  transaction aborts
//  Then:
//  it reads existing result
//  returns same response

//  Final result: 900 (Correct)

//  Interestingly, Google often doesn’t need explicit “version column” optimistic locking
//  because Spanner already provides transactional conflict detection.


//  Key Difference using for update vs google approach:

//  SELECT * FROM idempotency_table
//  WHERE key = '123'
//  FOR UPDATE;
//
//  This is pessimistic locking.
//
//  What it means
//  You lock the row first
//  Other transactions must wait
//  Only one thread proceeds at a time

//  Problem with this approach (at scale) This works, but has drawbacks:

//  1. Blocking: Other requests wait (block), Threads pile up
//  2. Deadlocks possible: Multiple locks -> deadlock scenarios
//  3. Poor scalability: High contention -> performance drops
//  4. Long transactions: Lock is held until commit
//  Not ideal for: network calls, payment API

//  Google-style approach is Instead of locking Use atomic conditional write (fail-fast).

//  Behavior:
//  First request -> succeeds
//  Second request -> fails immediately
//    No waiting
//    No blocking







//  3. Natural Idempotency (State-Based): Natural Idempotency means an operation is idempotent by
// design, without needing idempotency keys, request deduplication tables, extra coordination logic.
//  Repeating the same operation naturally produces the same final state.
//      ? PUT /balance = 1000
//      ? POST /balance/deposit +100

//  4. Deduplication Table: A Deduplication Table is a persistent data store used to ensure
//  idempotency by tracking whether a request (or event/message) has already been processed.
//  Used in event/message processing.
//  ProcessedEvents Table:
//  (event_id, processed_at)
//    If event_id exists ? skip.

//  5. Conditional Writes (Compare-and-Set): Conditional writes ensure that a write to a database
// only happens if a specific
//  condition is true.
//  eg. UPDATE order
//  SET status = PAID
//  WHERE status != PAID
//  Prevents double transitions.


//  -------------------------------------------------------------





//  Few Important Edge Cases:

//  1. What about external side effects?
//  Example:
//    payment gateway called
//    DB update fails
//
//  You may charge user but not record it
//
//  Fix:
//  external systems must also support idempotency.
//  or use reconciliation / retry workflows.


//  2. Returning same response: When a client retries with the same idempotency key.
//  Client expects exactly the same outcome as the first request.
//  Client retries -> expects same result.
//  Not just “no duplicate effect”, but also:
//  same status
//  same response body
//  same transaction reference

// Fix: Store response in table and if Insert failed fetch from table status "SUCCESS" and return response.

















//  Idempotency in Messaging (Kafka / SQS): Processing the same message multiple times produces the
//  same result as processing it once. Assume every message can be delivered more than once. Design
//  consumers to be idempotent.

//  Solution: Event ID + dedup store, Idempotent handlers, Transactional consumers.

//  Each message has a unique ID: eventId / messageId / transactionId.
//  Consumer: Check if ID exists in dedup store, If yes ? ignore, If no ? process + store ID.
//  Storage: DB table, Redis, RocksDB (Kafka Streams)

//  Idempotency in Sagas (Very Important): Every Saga step must be idempotent because:
//  Steps can be retried. Compensations can be retried.


//  Common Pitfalls:
//  Assuming network failures won’t happen.
//  Using POST without idempotency keys.
//  Forgetting idempotency in consumers.
//  Dedup table growing infinitely.
//  Not expiring idempotency keys.
//  Side effects outside transaction.

public class Idempotency {}
