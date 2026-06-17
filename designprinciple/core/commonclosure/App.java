package designprinciple.core.commonclosure;

//  Common Closure Principle:

//  “Classes that change for the same reason should be grouped together in the same module/package.”
//  It ensures that a single change affects only one module, not many.

//  ? Bad Design (Violates CCP)
//  Package: customer-management
//  Includes: Customer, CustomerValidator, PaymentProcessor, InvoiceGenerator

//  Good Design (Follows CCP)
//  /customer
//     - Customer
//     - CustomerValidator
//
//  /payments
//     - PaymentProcessor
//     - PaymentRules
//
//  /invoice
//     - InvoiceGenerator


public class App {

}
