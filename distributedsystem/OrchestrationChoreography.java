package distributedsystem;

//  Choreography:

//  Choreography is a distributed workflow pattern where there is no central coordinator.
//  Each service: Listens to events, Performs its local action, Emits another event.
//  No service “controls” the flow — the system evolves through events.
//  eg.
//  Order Service ? emits OrderCreated
//  Inventory Service ? listens ? reserves stock ? emits InventoryReserved
//  Payment Service ? listens ? charges ? emits PaymentCharged
//  Shipping Service ? listens ? creates shipment ? emits ShipmentCreated

//  Failure Handling:
//  If Payment fails:
//  Payment Service ? emits PaymentFailed
//  Inventory Service ? listens ? releases inventory
//  Order Service ? listens ? cancels order
//  Each service reacts independently.

//  Why Choreography Scales Well:
//  No single point of control
//  Services are loosely coupled
//  Easy horizontal scaling
//  Natural fit for Kafka / PubSub
//  Works well with eventual consistency
//  High throughput
//  Event-driven architecture
//  Simple workflows

//  When to Use Choreography: Notifications, Analytics pipelines, Activity feeds, Audit logs, Data pipelines.

//  Choreography Pitfalls:
//  Difficult debugging
//  Hard to visualize flow
//  Hidden coupling via events
//  Event versioning issues
//  Event explosion
//  Complex compensation logic
//  Ordering guarantees are tricky



//  Orchestration:

//  Orchestration is a pattern where a central coordinator (orchestrator) explicitly controls and directs
//  the execution order of multiple services in a distributed workflow.
//  One service knows what step comes next, who to call, and what to do on failure.
//  This is the opposite of Choreography, where services react to events without a central controller.

//  Orchestration ? “Do this ? then that ? if it fails, undo this”
//  Choreography ? “I react when something happens”

//  When to Use Orchestration: Payments, Order fulfillment, Booking systems, Subscription lifecycle, Resource provisioning.
//  Workflow is complex.
//  Order of steps matters.
//  Clear compensation logic needed.
//  Business rules change often.
//  Easier debugging is required.
//  Transactions span many services.

//  Pitfalls of Orchestration:
//  Orchestrator becomes a God Service
//  Single point of failure
//  Tight coupling between services
//  Extra latency (extra hop)
//  Hard to scale for very large workflows
//  State persistence complexity


public class OrchestrationChoreography {

}
