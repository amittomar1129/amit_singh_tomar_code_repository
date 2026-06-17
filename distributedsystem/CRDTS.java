package distributedsystem;

//  CRDTs (Conflict-Free Replicated Data Types):
//  Nodes can update independently.
//  Accept concurrent updates.
//  Automatically converge without coordination
//  replicas merge deterministically.

//  “CRDTs are replicated data types designed to converge automatically under concurrent updates without coordination.
//  They rely on mathematically commutative, associative, and idempotent merge functions.
//  This allows replicas to accept updates independently and reconcile later. This makes CRDTs ideal for
//  highly available, partition-tolerant systems, though they are unsuitable for enforcing global invariants.”

//  CRDTs enable: High availability, Offline writes, Low latency, Partition tolerance.

//  Core CRDT Properties (Must Know):
//  1. Convergence: All replicas eventually reach the same state.
//  2. Commutativity: Order of updates does not matter.
//  3. Associativity & Idempotence: Merges can be repeated and re-ordered safely.
//  These properties guarantee eventual consistency without conflicts.

//  State-Based CRDTs (CvRDTs): Each replica maintains full local state. Replicas periodically exchange state.
//  States are merged using a deterministic merge function. Merge function is: Commutative, Associative, Idempotent.
//  eg. G-Counter(Grow-Only Counter, use for Page views, metrics)

//  Operation-Based CRDTs (CmRDTs): Nodes broadcast operations. Operations must commute. Reliable delivery required.
//  eg. PN-Counter((Increment + Decrement, use for Likes / unlikes)


public class CRDTS {

}
