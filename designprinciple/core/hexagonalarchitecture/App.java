package designprinciple.core.hexagonalarchitecture;


//  Also called: Ports & Adapters, Onion Architecture, Clean Architecture.

//  Business logic is the center. Everything else (DB, UI, APIs, queues, external services) is plugged in using ports and adapters.

//  Hexagonal/Clean Architecture isolates business logic from frameworks using Ports and Adapters.
//  The core defines interfaces (ports), and infrastructure implements them (adapters).
//  This enables independent evolution of domain rules, easy testing, replacement of databases/external services, and decoupled teams.
//  It creates stable boundaries and reduces cognitive load — essential for large-scale FAANG systems.”

//  Domain Layer:
//  class Payment {
//    boolean validate();
//  }
//
//  Infrastructure Layer:
//  class StripeAdapter implements PaymentGateway { … }
//  class MySQLPaymentRepository implements PaymentRepository { … }
//
//  Application Layer:
//  class ProcessPayment {
//    PaymentRepository repo;
//    PaymentGateway gateway;
//
//    PaymentResult execute(PaymentRequest req) {
//      payment = new Payment(req.data);
//      payment.validate();
//      result = gateway.charge(req.amount, req.method);
//      repo.save(payment, result);
//      return result;
//    }
//  }
//  The system is now: testable without Stripe, replaceable to PayPal, DB switchable without breaking business logic,
//  safe to evolve.



public class App {

}
