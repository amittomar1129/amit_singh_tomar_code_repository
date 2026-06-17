package distributedsystem;

//  Distributed Scheduler:
//  A distributed scheduler is a system that decides when and where tasks/jobs should run across
//  multiple machines, It handles scale, failures, fairness, and resource constraints.
//  eg. Kubernetes scheduler, Apache Airflow, Job queues, cron at scale.

//  Why Do We Need a Distributed Scheduler?
//  Single-node schedulers fail because: Jobs > single machine capacity, Workers fail.
//  Need HA, retries, fairness.
//  Distributed scheduler enables: Horizontal scale, Fault tolerance , Resource-aware placement

//  Control Plane vs Data Plane:
//  Control Plane:	It`s a brain that decides what to run where.
//  It must be centralized and strongly consistent.
//  Job admission (accept/reject)
//  Scheduling decisions (node selection)
//  Resource allocation (CPU, memory)
//  Priority & fairness
//  Queue management
//  Retry / backoff policies


//  Data Plane:	Actual task execution or actually does the work. It`s massive, execution-heavy.
//  Must be distributed, scalable and highly available.

//  Scheduling Models:
//  1. Centralized Scheduler: A single logical scheduler (control plane) makes all scheduling
// decisions for the cluster. Centralized schedulers trade scalability for optimality. It`s having a
// global view enables better decisions. But requires replication, caching, and batching to avoid becoming a bottleneck.
//  Used by: Kubernetes (leader-based)
//  Pros: Better placement decisions, Easier fairness
//  Cons: Bottleneck, Needs HA

//  2. Distributed / Decentralized Scheduler: A decentralized scheduler distributes scheduling
// decisions across multiple nodes instead of relying on a single central scheduler.
//  Decentralized scheduling trades global optimality for scalability.
//  systems rely on optimistic concurrency and conflict resolution instead of strict coordination.
//  Pros: Better scalability, Lower latency
//  Cons: Conflicting decisions, Harder consistency
//  most real systems are hybrid, not pure.

//  Scheduling Policies (Interview Favorite): You don’t need algorithms — you need intent.
//  Common policies:
//  1. First Come First Serve (FCFS): Tasks are scheduled in arrival order. Simple, Fair.
//  Long tasks block short ones (bad latency).
//  2. Shortest Job First (SJF): Run smallest/fastest tasks first.
//  Minimizes average waiting time.
//  Starvation of long tasks.
//  3. Priority Scheduling: Each task has a priority. High -> Medium -> Low
//  Critical jobs
//  Starvation of low priority
//  4. Round Robin (Time Slicing): Each task gets a fixed time slice (quantum) to run, and
//  then the CPU/scheduler moves to the next task in a circular order.
//  Scheduler maintains a queue
//  Pick first task
//  Run for quantum
//  If not finished in time slice -> pause and move to end of queue
//  Repeat
//  5. Fair Scheduling: Fair Scheduling ensures that every job gets a fair share of system resources
// over time.
//  fair resource distribution.
//  6. Dominant Resource Fairness (DRF): Fairness across multiple resources (CPU, memory).
//  if A uses more CPU
//  and B uses more memory
//  DRF balances both fairly
//  7. Bin Packing (Resource Optimization): Pack tasks onto as few machines as possible to maximize
//  resource utilization and reduce waste.
//  Reduce cost, Improve utilization. Used in: Kubernetes
//  8. Least Loaded: Assign task to least busy node.
//  9. Constraint-Based Scheduling: Respect rules like:
//  Node Affinity / anti-affinity
//  Region constraints
//  Hardware requirements

//  Node Affinity lets you tell the scheduler that “Run this job only on nodes that match certain rules.”

//  Resource Awareness:
//  Schedulers must consider: CPU, Memory, Disk, GPU, Locality, Affinity / anti-affinity.

//  Failure Handling (This Is Heavily Tested):
//  Worker Failures: Detect via heartbeats, Reschedule tasks, Requires idempotency.
//  Scheduler Failures: Leader election, State stored externally, New leader resumes.
//  Distributed schedulers assume at-least-once execution.

//  Exactly-Once Execution (Trick Question): Exactly-once is extremely hard in distributed
//  schedulers and usually avoided.
//  Reality: At-least-once + idempotent tasks Or checkpoints

//  Time & Clock Issues (Often Overlooked):
//  Schedulers rely on: Logical time (events), Not wall-clock time.

//  Backpressure & Admission Control: Schedulers must protect themselves:
//  Limit job submissions
//  Reject or delay work
//  Scheduler overload is worse than worker overload.
//  Queue thresholds

public class DistributedScheduler {}
