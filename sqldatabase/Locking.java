package sqldatabase;

//  Database Lock: Why Locking Exists?
//  Goal -> maintain ACID, especially Isolation and Consistency.
//  Without locks
//  T1 reads X
//  T2 updates X
//  T1 writes back stale value -> DATA CORRUPTION
//  Locking ensures: Only safe interleavings of transactions.

//  Core Locking Concepts:
//  Lock Granularity: Where the lock is applied: Database -> Table -> Page -> Row.
//  Smaller granularity: better concurrency, higher overhead.
//  Larger granularity: worse concurrency, lower overhead.
//  Databases dynamically choose lock granularity based on workload.

//  Lock Types:
//  1. Shared Lock (S): a lock that allows multiple transactions to read the same data safely.
//  Many readers allowed. No writers allowed. Reads must see stable data. Writes must not modify data while it’s being read.
//  Shared locks ensure read consistency by blocking writers but allowing concurrent readers, with lock duration
//  controlled by isolation level.
//  Example:
//  SELECT balance FROM accounts WHERE id = 10;
//  Transaction behavior:
//  Acquire S lock on row
//  Read data
//  Release S lock (depends on isolation level)
//  Compatibility: Existing lock S, New S lock Allowed. But new X lock Blocked. S + S -> OK but S + X -> NOT OK.

//  2. Exclusive Lock (X): a lock that gives one transaction full control over data.
//  Core rule: One writer only. No readers. No other writers. Data must not change while being modified.
//  Eg. Exclusive lock taken during data modification: INSERT, UPDATE, DELETE. X + S -> NOT OK, X + X -> NOT OK.

//  3. Intent Locks (IS / IX): Why Intent Locks Exist, Problem: How can DB safely mix table-level locks and row-level locks?
//  Without intent locks: Before locking table -> must scan all rows to check conflicts, Too slow. Solution: Intent Locks.
//  Intent locks act as: "I plan to lock something inside this object".
//  A lightweight lock placed on a higher level object to indicate that a transaction intends to acquire a lower-level lock.
//  Intent lock always lives above the real lock.
//  Intent Shared (IS): "I intend to take Shared (S) locks on rows"
//  Intent Exclusive (IX):  "I intend to take Exclusive (X) locks on rows".
//  Example: SELECT * FROM users WHERE id = 5; Locking sequence: IS lock on table users -> S lock on row id=5.
//  UPDATE users SET name='A' WHERE id=5; Locking sequence: IX lock on table users -> X lock on row id=5.
//  IS + IS -> OK, IS + IX -> OK, IS + S -> OK, IS + X -> Not OK
//  IX + IS -> OK, IX + IX -> OK, IX + S -> NOT OK, IX + X -> NOT OK

//  4. Isolation Levels (Locking Behavior):
//    Level	                            Guarantees	                        Locks
//    READ UNCOMMITTED	            dirty reads allowed	                almost no locks
//    READ COMMITTED	              no dirty reads	                    short S locks
//    REPEATABLE READ	              no non-repeatable reads             S locks held longer
//    SERIALIZABLE	                full isolation	                    range locks
//  “Higher isolation -> stronger locks -> less concurrency.”

//  5. Row-Level Locking vs MVCC: How do we allow multiple transactions to read and write data safely?
//  Two major approaches: Row-Level Locking (pessimistic), MVCC (optimistic for reads).
//  Row-Level Locking: Row-Level Locking -> database locks individual rows using S and X locks.
//  MVCC: MVCC -> database keeps multiple versions of each row.
//  Core Idea - Readers never block writers. Writers never block readers.
//  How It Works:
//  Read:
//  Read snapshot of data (timestamp based)
//  NO S lock
//  Write:
//  Create new row version
//  Acquire X lock to prevent other writers
//  Example: T1 -> reads row version V1
//  T2 -> updates row -> creates V2
//  T1 continues reading V1

//  6. Deadlocks:
//  T1 holds A -> waits for B
//  T2 holds B -> waits for A
//  No progress possible.
//  How Databases Handle Deadlocks?
//  Detection: build wait-for graph. find cycles.
//  Resolution: abort one transaction (victim).
//      “Databases prefer deadlock detection over prevention.”

//  7. Lock Escalation: a database optimization where many fine-grained locks are replaced with a coarser-grained lock.
//  Example: Many row locks -> one table lock, Reduce lock management overhead.
//  When threshold exceeded: Row locks released -> Table lock acquired.
//  When Does Lock Escalation Trigger? Number of locks threshold. Memory pressure. Lock manager heuristics.

//  8. Optimistic vs Pessimistic Locking: Multiple transactions want to access the same data, How do we prevent conflicts?
//  Two strategies: Pessimistic Locking, Optimistic Locking.
//  Pessimistic Locking -> Assume conflicts will happen. Strategy: Lock first -> work -> unlock.
//  Pros: Strong consistency, Simple mental model, No lost updates.
//  Cons: Blocking, Deadlocks, Lower concurrency.

//  Optimistic Locking -> assume conflicts are rare. Strategy: Work first -> validate -> commit.
//  How It Works?  Uses:
//  Version column
//  Timestamp
//  Checksum
//  Flow:
//  T1 reads version=5
//  T2 reads version=5
//  T1 updates WHERE version=5 -> success
//  T2 updates WHERE version=5 -> 0 rows -> conflict

//  SQL: UPDATE account
//       SET balance=900, version=version+1
//       WHERE id=1 AND version=5;
//  Pros: No blocking, High concurrency, Scales well.
//  Cons: Retries needed, Conflict handling logic, Not good for high contention.

//  SQL databases use multi-granularity locking with shared, exclusive, and intent locks to enforce isolation.
//  Modern engines often use MVCC to reduce read-write contention. Deadlocks are detected dynamically and resolved
//  by aborting a transaction. Higher isolation increases lock duration and reduces concurrency.


public class Locking {

}
