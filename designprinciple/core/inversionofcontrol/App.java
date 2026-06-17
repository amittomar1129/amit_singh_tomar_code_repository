package designprinciple.core.inversionofcontrol;


//  Inversion of Control / Dependency Injection Principle:

//  Inversion of Control means the high-level module does not control its dependencies — they are provided externally.
//  This decouples business logic from concrete implementations.
//  Dependency Injection is a technique to achieve IoC by injecting dependencies via constructors, setters, or frameworks.
//  This keeps systems testable, decoupled, replaceable, and easy to evolve at scale.

//  ? Without IoC — tightly coupled
//  class OrderService {
//    private EmailService email = new EmailService(); // hard-coded
//  }
//  Cannot replace EmailService with SMS.
//  Hard to test; must hit real email.
//  Violation of DIP (high-level module depends on low-level)


//  ? With IoC + DI — loosely coupled
//  class OrderService {
//    private final NotificationService notifier;
//
//    OrderService(NotificationService notifier) {
//      this.notifier = notifier;
//    }
//  }
//  Now:
//  You can inject EmailNotifier, SMSNotifier, MockNotifier.
//  OrderService doesn’t care how notification happens.
//  Swappable implementations, Unit test friendly.

public class App {

}
