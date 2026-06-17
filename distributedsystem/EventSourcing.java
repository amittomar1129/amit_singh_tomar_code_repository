package distributedsystem;

//  Event Sourcing:

//  Event Sourcing is a design pattern where you do not store the current state directly. Instead,
// you
//  store every state change as an immutable event. By replaying these events, The current state is
// rebuilt .

//  Example:
//  Traditional way (State-based):
//  Balance = 1000
//  Balance = 1200
//  Balance = 900, Only the latest value exists.
//
//  Event Sourcing way:
//      AccountCreated
//  MoneyDeposited +200
//  MoneyWithdrawn -300
//  State = replay all events.

//  Why Use Event Sourcing?
//  Complete audit log.
//  Natural fit for event-driven systems.
//  Time travel / replay.
//  Easy integration with streaming.
//  Append-only -> high write throughput.
//  Debugging


//  Usage:
//  Financial systems
//  Auditing systems
//  Business workflows
//  Event-driven architecture
//  Debug & replay needed
//  Complex state transitions

//  Core Components:
//
//  1. Command: A request to perform an action.
//  2. Aggregate: The domain entity that handles commands and produces events.
//  3. Event Store: It stores all events in order. Append-only log, Source of truth.
//  4. Event Handlers: Listens to events and updates read models.
//  5. Projections (Read Models): A query-optimized view built from events.
//  6. Snapshot (Optimization): Stores the current state at a point in time

//  Write vs Read Flow:
//  Write Path:
//    Command -> Aggregate -> Validate -> Emit Event -> Store Event
//
//  Read Path:
//    Replay Events -> Build Projection -> Query Projection
//  This separation is usually combined with CQRS.

//  Event Sourcing Pitfalls:
//  Event versioning
//  Complexity
//  Rebuild cost
//  Eventual consistency
//  Storage growth

//  Snapshots (Optimization): Replaying millions of events is slow.
//  Solution: Snapshots
//  Events 1–1000 -> Snapshot
//  Events 1001–1100 -> Replay
//  Snapshots improve startup and rebuild time.

public class EventSourcing {}
