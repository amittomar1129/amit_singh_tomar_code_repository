package designprinciple.core.acyclicdependency;

//  Acyclic Dependency Principle:

//  Modules must never form cyclic dependencies. The dependency graph of your system must form a Directed Acyclic Graph (DAG).
//  Cycle: OrderService ? PaymentService ? NotificationService ? OrderService
//  Changing OrderService forces changes in Payment
//  Changing Payment affects Notification
//  Changing Notification breaks Order
//  This is architectural deadlock.

//  Fix:
//  Break the cycle by introducing an event boundary:
//
//  OrderService ? publishes OrderCreatedEvent
//  PaymentService ? consumes event
//  PaymentService ? publishes PaymentCompleteEvent
//  NotificationService ? consumes event
//  New graph is acyclic.

//  If a cycle is detected, they insert:
//  an API boundary
//  or an event layer
//  or a new abstraction

public class App {

}
