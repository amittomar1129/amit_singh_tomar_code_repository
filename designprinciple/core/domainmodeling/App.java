package designprinciple.core.domainmodeling;

//  Domain Modeling (Entities & Value Objects) Principle:

//  Domain modeling is about the structuring the code so it directly represents the real business domain,
//  with classes that encapsulate domain rules, not just data.

//  The two foundational building blocks are:
//  -Entities ? A domain object with a unique identity that persists over time, even if its attributes change.
//  Entities contain business logic. eg. User, Order, Payment, Shipment, Product, Playlist, Video
//  -Value Objects ? A domain object without identity, defined purely by its attributes.
//  Immutable, Replaceable rather than updated, Equality based on value, Self-contained logic.
//  eg. Money, Address, Coordinates, DateRange, Price, Name, GeoLocation, EmailAddress

//  ? BAD (Anemic Model):
//  class Order {
//    public String id;
//    public double amount;
//    public String currency;
//  }
//  No invariants. No rules. No behavior.

//  ? GOOD (Rich Entity + Value Objects):
//  class Order {
//    private OrderId id;
//    private Money total;
//    private List<OrderLine> lines;
//
//    public void addLine(Product product, int qty) {
//      lines.add(new OrderLine(product, qty));
//      recalcTotal();
//    }
//  }
//  Money is a Value Object. Order is an Entity. Invariants move inside the domain model.

//  How Entities and Value Objects Work Together?
//  class Address {
//    private final String line1;
//    private final String city;
//    private final String zip;
//  }
//  Address is a Value Object that is Immutable.
//  User entity updates address by replacing it:  user.updateAddress(new Address(lines…));
//  No mutation inside Address. No ripple effects. Safe invariants.

public class App {

}
