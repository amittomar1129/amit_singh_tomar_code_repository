package sqldatabase;

//  ACID Principles: ACID defines the guarantees provided by a database transaction.
//  A transaction = a sequence of operations treated as one logical unit.

//  1. Atomicity – “All or Nothing”: A transaction must either complete fully or have no effect at all.
//  Example- Bank transfer:
//  100 deducted from A ?
//  System crashes before crediting B ?
//  Without atomicity ? money lost
//  With atomicity ? database rolls back deduction from A
//  How DB ensures this? Undo logs / Write-Ahead Logging (WAL)

//  2. Consistency – “Rules are never broken”: A transaction must move the database from one valid state to another valid state.
//  Consistency ensures database constraints and invariants are preserved before and after a transaction.
//  All constraints must hold:
//  Primary key
//  Foreign key
//  Unique
//  Business rules

//  Consistency is NOT guaranteed by DB alone. Database guarantees only structural consistency.
//  Database enforces constraints, Application must ensure business logic.
//  ACID consistency = constraints + invariants

//  Real-life example
//  Rules:
//  Bank balance >= 0
//  Transaction:
//  Withdraw ?1000 from an account with ?500
//  ? Invalid state -> transaction fails
//  ? DB remains consistent


//  3. Isolation – “Transactions don’t interfere”: Concurrent transactions should behave as if executed serially.
//  Even if transactions run concurrently, result should be as if they ran one by one.

//  Real-life example:
//  Two users withdrawing ?100 from same account with ?150 balance
//  Without isolation:
//  Both see balance = 150
//  Both withdraw 100
//  Final balance = -50 ?
//
//  With isolation:
//  One transaction completes first
//  Second sees updated balance and fails

//  Problems Isolation Prevents: Dirty Read, Non-repeatable Read, Phantom Read.
//  How DB implements isolation? Option 1 -> Locks - Row locks, Table locks, Option 2 -> MVCC (Modern DBs)

//  4. DURABILITY: Once a transaction commits, data will not be lost, even after: Crash, Power failure, Restart.
//  Real-life example:
//  Payment success message shown to user
//  Server crashes immediately after
//  After restart:
//  Payment record still exists ?

//  How DB ensures durability?
//  Write-Ahead Logging (WAL)
//  fsync to disk
//  Replication
//  Steps:
//  Write to log
//  Flush log to disk
//  Commit acknowledged

//  Performance tradeoff:
//  Strong durability -> slower writes
//  Async commit -> faster but risk data loss

//  ACID works best in single-node or tightly coupled systems. Distributed systems often relax
//  some ACID properties to achieve scalability and availability.

public class Acid {

}
