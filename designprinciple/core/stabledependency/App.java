package designprinciple.core.stabledependency;

//  Stable Dependency Principle:

//  A module should depend on modules at least as stable as itself.
//  In other words:
//  Unstable code should depend on stable code. Stable code must never depend on unstable code.
//  Dependencies must point from more volatile components ? to more stable components.

//  This ensures that when something changes, the change doesn’t ripple backward into more stable modules.

//  BirdController ? BirdService ? BirdRepository ? DatabaseDriver
//  Stable: BirdService (business rules)
//  Unstable: DB driver, SQL schema, ORM
//
//  Violation: If BirdService directly depends on DatabaseDriver
//  Changes in DB break core business logic, Testing becomes painful, Migration becomes nearly impossible

//  Fix:
//  Introduce stable interfaces:
//  BirdService ? BirdRepository (interface) ? PostgresRepo / MongoRepo / InMemoryRepo
//  Now:
//  Business logic stays stable.
//  Infrastructure can change freely.

public class App {

}
