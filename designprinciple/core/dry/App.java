package designprinciple.core.dry;


//  DRY means: Never duplicate knowledge.
//  Most people think DRY = “don’t write the same code twice.”

//  FAANG interprets DRY as:
//  Avoid multiple representations of the same business rule.
//  Avoid duplicated logic across services.
//  Avoid duplicated schemas / data definitions.
//  Avoid duplicated configurations.
//  Avoid duplicated transformations


//  ? BAD (violates DRY):
//  Three different services check fraud risk with slightly different rules:
//  PaymentsService: score > 60
//  CheckoutService: score > 55
//  WalletService: score > 70
//  Now when policy changes ? you must update 3 places ? high bug chance.
//
//  ? GOOD (DRY applied):
//  Central FraudEngine microservice exposes one policy. All consumers call it.

//  Over-abstraction violates DRY too:
//  Duplicated knowledge is bad. Duplicated code may be fine if abstraction makes the system harder to understand.
//  “The Rule of Three” is commonly used:
//    If logic appears once ? OK
//    If twice ? think
//    If three times ? extract a reusable component.

//  “How do you avoid duplicated logic in a large microservice ecosystem?”
//  Answer: Use shared domain libraries, proto schemas, centralized decision engines, and API contracts to
//  ensure single sources of truth.
//
//  “Your team and another team both implemented different rate limiters — what do you do?”
//  Answer: Consolidate them into a common Rate Limiting Service; remove logic duplication.
//
//  “How do you keep business rules consistent across regions?”
//  Answer: Build a Policy Engine or Config Service; avoid copying logic into each service.

public class App {

}
