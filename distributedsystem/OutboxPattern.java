package distributedsystem;

//  Outbox Pattern:

//  The Outbox Pattern processes database update and event publication happen atomically from the
//  service’s point of view without using distributed transactions
//  If data is committed, the event will eventually be published — no lost events, no double writes.

//  The “Dual Write” Problem without Outbox pattern:
//  1. Update DB.
//  2. Publish event to Kafka.
//  Failures:
//      DB succeeds, event fails ? lost event
//      Event succeeds, DB fails ? phantom event
//      Crash between steps ? inconsistent state
//  Outbox pattern solves this without 2PC.

//  Core Idea: Write events into the same database transaction as business data. Later, publish them asynchronously.
//  eg.    Service DB Transaction:
//        - Update business tables
//        - Insert event into OUTBOX table
//        COMMIT

//    Then Outbox Processor:
//        - Reads outbox table
//        - Publishes event to Kafka / PubSub
//        - Marks event as SENT

//  Event Publishing Options
//  1. Polling-based Publisher: Periodically scan outbox table, Simple, Higher DB load.
//  2. Log-based (CDC): Instead of reading the database table again and again, we read the database’s internal
//  transaction log to know what changed. Databases already write every change to a transaction log(WAL, binlog, redo log).
//  Use Debezium / WAL tailing, Near real-time, More complex, more scalable.
//  CDC Capturing every change once. Preserving order, Adding near-zero DB load.
//  FAANG prefers CDC-based outbox.

//  Log-based CDC Pitfalls:
//  Schema changes,
//  Log retention limits,
//  Large transactions,
//  Replaying history.

//  Mitigates CDC Pitfalls:
//  Schema registry,
//  Versioned events,
//  Offset tracking,
//  Idempotent consumers

//  Important Tradeoffs/Pitfalls:
//  Duplicate events: Consumers must be idempotent.
//  Eventual consistency: Event not published instantly.
//  Outbox table growth: Requires cleanup/TTL.
//  Ordering guarantees: Only per-aggregate ordering guaranteed
//  Operational complexity: CDC pipelines, monitoring

//  Real FAANG Usage:
//  Amazon: Order & inventory events
//  Uber: Ride lifecycle events
//  Netflix: Playback & billing events
//  LinkedIn: Activity feeds
//  Stripe: Event sourcing + outbox

public class OutboxPattern {

}
