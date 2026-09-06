package sqldatabase;

//  Transaction:
//  A transaction is a group of SQL operations that must be: Atomic, Consistent, Isolated, Durable. (ACID properties).
//  Example:
//  BEGIN;
//  UPDATE account SET balance = balance - 100 WHERE id = 1;
//  UPDATE account SET balance = balance + 100 WHERE id = 2;
//  COMMIT;

//  COMMIT: “Make my changes permanent and visible to others.” Until you COMMIT, your changes are visible only to you.
//  Can be undone using ROLLBACK.

//  ISOLATION LEVEL: Isolation level defines how much one transaction can see another transaction’s data
//  while both are running. “How isolated should my transaction be from others?”
//  Problems Isolation Levels Try to Prevent:

//  Problem	                        Meaning
//  Dirty Read                -
//  A dirty read occurs when one transaction reads data that has been modified by another transaction
//  but has not yet been committed. If the first transaction rolls back, the second transaction has
//  read data that never actually existed as committed data.

//  Non-Repeatable Read       -
//  A non-repeatable read occurs when a transaction reads the same row twice within the same transaction,
//  but gets different values because another transaction committed an update between the two reads.

//  Phantom Read	            -
//  A phantom read occurs when a transaction executes the same query twice, but the second execution
//  returns a different set of rows because another transaction inserted, deleted, or modified rows
//  that match the query condition and committed the change.

//  The 4 SQL Isolation Levels:
//  1. READ UNCOMMITTED (Lowest): Can read uncommitted data, Fast but unsafe.
//  Problem: Dirty Reads, Non-repeatable Read, Phantom Read.
//  T1 updates value but doesn’t commit
//  T2 reads that uncommitted value
//  If T1 rolls back -> data was fake.
//  Real-life analogy: Reading a Google Doc while someone is typing and hasn’t saved. You may read incomplete information.
//  When to use: Almost never, Only for: Fast analytics, Approximate reporting, Logs / metrics.
//  Most production databases do not support true READ UNCOMMITTED (MySQL treats it like READ COMMITTED)

//  2. READ COMMITTED (Most Common Default): Can only read committed data. Cannot read dirty data. Prevents Dirty Reads.
//  It allows Non-repeatable reads. Eg.
//  T1 reads value
//  T2 updates & commits
//  T1 reads again -> different value
//  Real-life analogy: Checking Amazon price, Price may change between refreshes.
//  When to use: Most OLTP systems, User profiles, Order status, Read-heavy systems.
//  Good balance between performance and consistency

//  3. REPEATABLE READ: Rows read by a transaction cannot change until it finishes.
//  Rows read once cannot change. Guarantees same result for same row.
//  It Prevents Dirty Reads and Non-repeatable Reads. It allows Phantom Reads (in some DBs).
//  T1 -> SELECT COUNT(*) FROM orders WHERE price > 1000 -> 10
//  T2 -> INSERT new order (price = 2000)
//  T1 -> SELECT COUNT(*) -> 11
//  New row appeared -> Phantom
//  Real-life analogy: You take a photo of a shelf. Items you saw won’t change. New items may be added later.
//  When to use: Financial calculations, Inventory checks, Account balances.
//  MySQL InnoDB uses next-key locks to prevent phantom reads even in REPEATABLE READ.


//  4. SERIALIZABLE (Highest Isolation): Transactions behave as if run one-by-one. Full isolation.
//  It prevents Dirty Reads, Non-repeatable Reads and Phantom Reads. Its Slow (locks a lot).
//  T1 -> SELECT COUNT(*) FROM seats WHERE available = true
//  T2 -> INSERT / UPDATE seats
//  T2 is blocked until T1 finishes
//  Real-life analogy: Single cashier in a bank, One customer at a time.
//  When to use: Seat booking (airlines), Stock trading, Critical financial operations.
//  Downside: Lowest performance, High contention, Possible deadlocks.

//  How to Set Isolation Level:
//  SET TRANSACTION ISOLATION LEVEL READ COMMITTED; or BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;

//  Real-Life Analogy (Very Important): Think of a Bank
//  READ UNCOMMITTED -> checking balance while teller is typing
//  READ COMMITTED -> see only finalized balance
//  REPEATABLE READ -> balance won’t change during your session
//  SERIALIZABLE -> one customer at a time



//  MVCC (Multi-Version Concurrency Control): MVCC allows multiple versions of the same row to exist so that
//  readers don’t block writers and writers don’t block readers.
//  MVCC improves concurrency by keeping multiple row versions and avoids read-write locks.
//  In short: Readers read old versions. Writers create new versions.
//  Why MVCC is needed?
//  Without MVCC: Reads block writes, Writes block reads, System becomes slow under load.
//  With MVCC: High concurrency, Better performance, Consistent reads.
//  Example: Initially, Account balance = 1000.
//  T1 -> SELECT balance (starts)
//  T2 -> UPDATE balance = 700 (COMMIT)
//  T1 -> SELECT balance again
//  What happens with MVCC:
//  T1 sees balance = 1000 both times.
//  T2 creates a new version of the row.
//  Old version remains until T1 finishes.
//  This is how REPEATABLE READ works internally
//  Real-life analogy: Google Docs version history, You keep editing. Others still see a stable snapshot.



//  Isolation vs Locking: Key Differences.
//  Aspect	                          Isolation Level                              	Locking
//  What	                           Visibility rules	                          Physical blocking
//  Purpose                          Consistency	                              Mutual exclusion
//  Scope	                           Logical	                                  Physical
//  Used by	                         Transactions	                              DB engine

//  Isolation level -> what anomalies are allowed.
//  Locks -> how DB enforces isolation.
//  Isolation is policy. Locks are mechanism. Isolation defines behavior, locking is one way to implement it.


//  Deadlocks: Two or more transactions wait on each other forever.
//  T1 -> locks Row A
//  T2 -> locks Row B
//  T1 -> wants Row B (wait)
//  T2 -> wants Row A (wait)
//  Real-life analogy: Two cars enter a one-lane bridge from opposite sides
//  How DB handles deadlocks: DB detects deadlock. Kills one transaction. Other proceeds.
//  DB Error like: Deadlock found when trying to get lock

//  How to Avoid Deadlocks:
//  1. Always lock resources in same order: Lock A -> Lock B, Never A -> B (T1), B -> A (T2).
//  2. Keep transactions short: No long computations inside transaction. No external calls (HTTP, RPC).
//  3. Use proper indexes: Avoid table scans. Reduce lock range.
//  4. Retry logic: Especially in distributed systems.
//  Deadlocks are unavoidable in concurrent systems, so databases detect them and abort one transaction. Applications should retry.



public class Transaction {

}
