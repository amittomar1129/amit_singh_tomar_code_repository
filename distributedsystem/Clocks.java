package distributedsystem;


//  Clock in Distributed System:

//  In distributed systems, there is no reliable global clock.
//  Physical clocks suffer from drift, skew, and adjustments, so they cannot be used for correctness.

//  Why Time Is Hard in Distributed Systems: In a distributed system,
//  There is no single global clock, Each machine has its own local clock, Messages experience variable network delay
//  Machines pause, restart, drift.
//  You can’t reliably know when something happened globally — only in what order.

//  Physical Clocks (Real Time): A physical clock represents wall-clock time on a machine.
//  Each machine has its own physical clock — there is no global clock. Example: 2025-01-01 10:30:15.123 UTC.

//  Why Physical Time Cannot Be Trusted: Event A happened before Event B But timestamps show:
//  B: 10:00:01
//  A: 10:00:02, This causes Wrong ordering, Data corruption, Incorrect conflict resolution.
//  Clock Drift: Clocks run at slightly different speeds.
//  Clock Skew: Difference between clocks at a moment in time.

//  Synchronizing Physical Clocks (NTP):
//  Network Time Protocol (NTP): NTP (Network Time Protocol) is a protocol used to synchronize physical (wall-clock) time across
//  machines in a distributed system. Synchronizes clocks to reference servers, Accuracy: ~1–10 ms (best case).
//  NTP improves clocks but does not eliminate time uncertainty.

//  Logical Time (The Real Solution): Distributed systems avoid relying on physical time and instead track ordering.

//  Logical Clocks (Lamport Clocks):
//  A Lamport Clock is a logical counter.
//  Each process assign timestamps to events.
//  Each event increments a counter, Messages carry the counter, Captures happens-before ordering.
//  Rules: Increment clock on each event, On message receive: clock = max(local, received) + 1.
//  Guarantees: If A happened before B -> timestamp(A) < timestamp(B).
//  Use case: Ordering Events, Distributed logs.

//  Example: Initially, P1 clock = 0, P2 clock = 0
//  P1 internal event, P1 = 1, P1 sends message P1 = 2, Message timestamp = 2.
//  P2 receives message, P2 = max(0, 2) + 1 = 3
//  Result: Send event (2) -> Receive event (3), Causality preserved.

//  Vector Clocks: Lamport clocks preserve ordering, but they cannot detect concurrency.
//  A vector clock is an array of logical counters, each one per process (or node).
//  Each node keeps a vector of counters,
//  Tracks causality precisely. “A vector clock captures what I know about everyone else.”
//  Detect concurrent events, Resolve conflicts accurately.
//  Trade-offs: Vector size grows with number of nodes, High metadata overhead,
//  Use case: Dynamo-style databases, Conflict resolution systems.

//  Example: Initially, P1: [0,0] P2: [0,0]
//  P1 internal event, P1: [1,0], P1 sends message, P1: [2,0] -> sends [2,0]
//  P2 receives, max([0,0], [2,0]) = [2,0], increment P2 -> [2,1], Causality preserved.
//  Concurrent Example, P1 event: [3,1] P2 event: [1,3]



//  Hybrid Logical Clocks (HLC) (FAANG Favorite):
//  Logical clocks lack real time, Physical clocks lack correctness.
//  HLC Combines Both (timestamp, counter). Physical time as base, Logical counter for ordering.
//  Benefits: Monotonic, Close to real time, Correct ordering.
//  Used by: Google Spanner (in spirit), CockroachDB, Modern databases.

//  Atomic Clock: Atomic clocks provide highly accurate physical time.
//  Google Spanner use (via TrueTime) to achieve globally consistent ordering of transactions.
//  Extremely accurate time, Enables global ordering, Useful for strong consistency.
//  Core principle: Atomic clocks use a fixed natural frequency.
//  Cesium-133 atom vibrates at 9,192,631,770 cycles per second. This defines 1 second in SI units.
//  Time is measured by counting oscillations.
//  GPS satellites
//  Financial systems
//  Scientific research
//  Distributed databases (like Google Spanner)
//  Trade-offs: Expensive hardware, Complex to integrate

//  TrueTime (Google Spanner): TrueTime is a globally synchronized clock API that returns time as an interval, not a point.
//  It provides time as an interval [earliest, latest].
//  Why It Works: Uses GPS + atomic clocks, Machines periodically sync to masters, Track uncertainty bounds.
//  Trade-off: Must wait out uncertainty, Adds latency.


public class Clocks {

}
