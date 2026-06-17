package distributedsystem;


//  CQRS (Command Query Responsibility Segregation) Pattern: Use different models for writing data and reading data.
//  CQRS separates write operations (commands) from read operations (queries), allowing each side to be optimized independently.

//  Traditional (Non-CQRS) Model: API -> Service -> Single Database. Same tables for reads & writes.
//  Problems at scale: Read traffic overloads write DB, Complex joins hurt performance, Schema becomes messy.
//  Hard to scale independently.

//                    CQRS Model:
//                        Commands (Writes)         Queries (Reads)
//                        ------------------        ----------------
//                          Command API               Query API
//                             ?                         ?
//                          Write Model              Read Model
//                             ?                         ?
//                          Write DB                 Read DB

//  Commands: A command represents an intent to change state. CreateOrder, CancelOrder
//  Queries: A query fetches data without modifying it. GetOrder, ListOrders

//  How Data Sync Happens: Step-by-step flow:
//    1. Command updates Write DB
//    2. Event is published (e.g., OrderCreated)
//    3. Read model listens to event
//    4. Read DB is updated
//  Write DB ? Event ? Read DB
//  This means: Reads are eventually consistent

//  Why CQRS is Powerful:
//  1. Independent Scaling: Scale reads horizontally, Protect writes from heavy read traffic.
//  2. Optimized Models:
//  Write DB -> normalized, transactional
//  Read DB -> denormalized, fast
//  3. Clean Business Logic: Commands enforce invariants, Queries stay simple.
//  4. Natural Fit for Microservices: Different teams own read & write models, Better autonomy

//  CQRS Pitfalls:
//  Increased complexity
//  Eventual consistency
//  Data duplication
//  Harder debugging
//  Schema versioning challenges

//  FAANG-Style Real Examples:
//  Amazon: Orders (writes) vs order views (reads)
//  Netflix: Viewing events ? recommendation reads
//  Uber: Ride state changes vs ride views
//  LinkedIn: Post creation vs feed reads
//  Google: Resource provisioning

//  CQRS + Event Sourcing(Common Combo):
//  CQRS is often paired with Event Sourcing, but:
//      CQRS ? Event Sourcing
//  With Event Sourcing:
//    Write model stores events
//    Read model builds projections from events
//  Without Event Sourcing:
//    Write model stores current state
//    Events are still emitted


public class CQRS {

}
