package distributedsystem;

//  Consensus Algorithms:
//  Consensus is the process by which multiple distributed nodes agree on a single value or sequence of values,
//  even in the presence of failures. Goal: All non-faulty nodes agree on the same decision.

//  Failure Models: Crash failures, Network partitions failure handled by Raft, Paxos.


//  Raft: Raft ensures multiple nodes agree on the same ordered log despite failures.
//  It decomposes consensus into understandable parts.
//  More implementable, Explicit about leadership.
//  Problem Raft Solves: Strong consistency, Fault tolerance, Deterministic state machine replication.

//  Core Concepts:
//  Node Roles
//  Leader – handles all client requests.
//  Follower – passive, responds to leader.
//  Candidate – requests votes during election.
//  At any time: At most one leader per term.

//  Terms: Logical time units, Monotonically increasing, Used to detect stale leaders.

//  Leader Election (Step-by-Step):
//  All nodes begin as followers.
//  They expect periodic heartbeats from a leader. Heartbeats are just empty AppendEntries RPCs.
//  Each follower runs a randomized election timeout.
//  If heartbeat is received, Reset timer -> stay follower.

//  If timeout expires, Node assumes leader is dead -> starts election to become a leader.
//  The node increments its term. Votes for itself. Switches to be a candidate. Sends vote requests to all nodes.
//  Each node can vote only once per term.

//  Candidate’s term >= follower’s term otherwise -> reject
//  Candidate’s log must be at least as up-to-date as follower’s.

//  A candidate is up-to-date if it has Higher lastLogTerm or if same term but higher/equal index.
//  This prevents outdated nodes from becoming leader.

//  If candidate receives: majority = floor(N/2) + 1 votes. It becomes Leader.
//  Immediately, Sends heartbeats to all followers. Establishes authority.


//  Paxos: It solves Agreement on a value even if nodes crash. A value is chosen when a majority of acceptors agree on it.
//  Paxos solves agreement under crash failures.
//  Paxos is correct but hard to understand and implement.
//  1. Proposer: Suggests a value.
//  2. Acceptor: Votes on proposals, Must obey strict rules.
//  3. Learner: Learns the chosen value.

//  Two Phases of Paxos:
//  Phase 1 — Prepare (Promise Phase):
//  Proposer selects proposal number n.
//  Sends Prepare(n) to acceptors.
//  Acceptors respond with a promise not to accept proposals < n, The highest proposal they already accepted (if any).
//  Acceptors promise exclusivity.
//  Phase 2 — Accept (Accept Phase): Proposer chooses value:
//  If acceptors responded prior accepted values then pick highest-numbered one.
//  Or else propose its own value. Sends Accept(n, value). Acceptors accept if they haven’t promised higher n.

//  Paxos Variants:
//  1. Single-Decree Paxos: Chooses one value.
//  2. Multi Paxos: Reuses leader, Avoids Phase 1 repeatedly, Used in real systems.
//  3. Fast Paxos: Lower latency, More complexity.



//  Performance Characteristics (L6 Focus)
//  Latency: Write latency = 1 round-trip to majority
//  Throughput: Limited by leader, Leader can become bottleneck
//  Scalability: Small clusters (3–7 nodes) recommended



public class ConsensusAlgorithms {

}
