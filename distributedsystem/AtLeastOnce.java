package distributedsystem;

//  At-least-once vs Exactly-once semantics:

//  When systems exchange messages (Kafka, SQS, Pub/Sub, RabbitMQ), failures are inevitable.
//  How many times can a message be delivered and processed? This leads to delivery semantics.

//  At-least-once Semantics: A message is delivered one or more times.
//  Message is never lost. Message may be duplicated.
//  How It Works (Simple Flow): Producer sends message. Consumer processes message.
//  Consumer sends ACK, If ACK is not received ? broker retries delivery.
//  If the consumer crashes after processing but before ACK, the message is delivered again.
//  “At-least-once requires idempotent consumers to avoid side effects.”

//  Exactly-once Semantics: A message is delivered and processed exactly once — no loss, no duplicates. Much harder to implement
//  In distributed systems, true exactly-once delivery is impossible due to: Network partitions, Crashes, Uncertain acknowledgements.
//  How Exactly-once Is Achieved(Practically):
//  Using coordination + state Transactional writes,
//  Offset management,
//  Deduplication,
//  Atomic commit.

//  Kafka Exactly-once (Example): Kafka uses:
//  Idempotent producer.
//  Transactional producer.
//  Read-process-write in a single transaction.
//        Read message
//        Process
//        Write output
//        Commit offset + output atomically

//  Exactly-once semantics are expensive and fragile, so most large-scale systems prefer at-least-once + idempotency.

//  At-least-once delivery combined with idempotent processing is usually preferred at scale. Exactly-once semantics
//  are difficult to guarantee in distributed systems and typically rely on tight coupling and transactions, which reduce
//  scalability and availability.

public class AtLeastOnce {

}
