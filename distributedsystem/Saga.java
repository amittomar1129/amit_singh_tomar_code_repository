package distributedsystem;


//  Saga Pattern:

//  2-Phase Commit: 2-Phase Commit is a distributed transaction protocol that ensures
//  atomicity across multiple databases or services.
//  Either all participants commit the transaction or all participants roll back — no partial commits.
//  It tries to extend ACID transactions across distributed systems.

//  Core Components:
//  1. Transaction Coordinator: Orchestrates the commit protocol.
//  2. Participants (Cohorts): Databases or services involved in the transaction.

//  Phase 1: Prepare (Voting Phase): Coordinator asks all participants: “Can you commit?”
//  Each participant: Performs the transaction locally.
//  Writes data to undo/redo logs.
//  Locks the required rows/resources.
//  Responds: YES (prepared) or NO (abort)
//  No one commits yet.

//  Phase 2: Commit (Decision Phase):
//  Case 1: All participants say YES
//  Coordinator sends: COMMIT, Participants: Commit transaction, Release locks.
//  Case 2: Any participant says NO
//  Coordinator sends: ROLLBACK, Participants: Roll back transaction, Release locks.

//  The BIG Problem: Blocking. What if the Coordinator crashes?
//  All participants replied YES.
//  Coordinator crashes before sending COMMIT.
//  Participants: Are in prepared state, Hold locks, Cannot commit, Cannot rollback
//  They block indefinitely waiting for coordinator. This is why 2PC is called a blocking protocol.
//  2PC is not recommended because 2PC violates all three Availability, Scalability, Fault tolerance.

//  Usage: 2PC is not dead, just limited. Used when:
//  Very small number of participants.
//  Strong consistency required.
//  Short-lived transactions.
//  Systems like: Distributed databases, Banking cores, Some internal DB clusters, MySQL XA, Oracle distributed transactions.

//  3-Phase Commit improve 2PC but still not widely used.

//  3-Phase Commit: 2-Phase Commit results Blocking when the coordinator crashes.
//  3PC adds an extra phase so that participants never wait indefinitely and can make progress without the coordinator.
//  3PC tries to ensure: Non-blocking behavior, Timeout-based decisions, Better availability.

//  3PC only works correctly if: Bounded network delays, Reliable failure detection (timeouts), No network partitions.
//  These assumptions do not hold in real distributed systems.
//  Phase 1: CanCommit? (Voting Phase).  Phase 2: PreCommit (Preparation Phase). Phase 3: Commit (Final Phase).
//  Non-blocking because: Before PreCommit Abort. In PreCommit	Commit after timeout. After Commit	Already committed.

//  SAGA:
//  In a monolith, a transaction spans: BEGIN ? multiple DB operations ? COMMIT / ROLLBACK.
//  In microservices: Each service has its own database. No shared ACID transaction.
//  Since 2-Phase Commit (2PC) is slow, blocking, brittle under failures, avoided by FAANG.
//  Saga pattern replaces ACID transactions with eventual consistency + compensations.

//  A sequence of local transactions across multiple services, where each step publishes an event.
//  If a step fails, compensating transactions undo the previously completed steps.
//  Key ideas are No global lock, No distributed commit, Failures are handled explicitly.
//  Saga guarantees Atomicity at business level, Eventual consistency, No global locks, High availability.

//  Example:
//  Order Service
//  Inventory Service
//  Payment Service
//  Shipping Service
//  Steps:
//  1. Create Order
//  2. Reserve Inventory
//  3. Charge Payment
//  4. Create Shipment
//  Each step commits locally.
//  Failure Case:
//  If Payment fails after inventory is reserved:
//      ? Compensate Inventory (release stock)
//      ? Cancel Order
//  No rollback at DB level — only business-level undo.

//  Two Saga Models (VERY IMPORTANT):

//  1. Choreography-based Saga (Event-Driven): No central coordinator.
//  Each service listens to events, performs action, emits next event.
//  Flow:         OrderCreated
//                   ?
//                InventoryReserved
//                   ?
//                PaymentCharged
//                   ?
//                OrderCompleted
//  If Payment fails:
//                PaymentFailed
//                   ?
//                InventoryReleased
//                   ?
//                OrderCancelled
//  Pros: Loose coupling, High scalability, No single point of failure.
//  Cons: Hard to visualize flow, Complex debugging, Event explosion.

//  2. Orchestration-based Saga: A Saga Orchestrator controls the flow.
//  Flow:       Saga Service
//                 ?
//              Inventory Service
//                 ?
//              Payment Service
//                 ?
//              Shipping Service
//  If any step fails: Orchestrator triggers compensations in reverse order.
//  Pros: Clear flow, Easier debugging, Centralized logic.
//  Cons: Orchestrator can become complex, Extra hop latency.

//  Compensation Transactions:
//  A compensation: is NOT a rollback, is a business undo, may not fully revert state.
//  Compensations must be: idempotent, retriable, well-tested.

//  Common Saga Pitfalls(common hidden problems or mistakes):
//  Forgetting compensations.
//  Non-idempotent compensations.
//  Infinite retry loops.
//  Event ordering issues.
//  Not handling duplicate events



public class
Saga {

}
