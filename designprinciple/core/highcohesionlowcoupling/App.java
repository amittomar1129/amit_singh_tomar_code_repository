package designprinciple.core.highcohesionlowcoupling;

//  High Cohesion ? Each module does one thing extremely well.
//  Its internal pieces belong together, support one responsibility, and change for the same reason.

//  A class/module/system has high cohesion when:
//      All responsibilities are closely related.
//      All functions operate on shared domain concepts.
//      Changing one feature affects only one module.
//      Understanding the module doesn’t require reading other modules.

//  Low Coupling ? Modules know as little as possible about each other.
//  Changing one should rarely force changes in another.

//  Two modules have low coupling when:
//    They don't depend on each other's internal details
//    They communicate through simple, stable interfaces
//    They can be developed, tested, and deployed independently
//    Changing internals of one does not break the other


//  ? Low Cohesion, High Coupling
//  class UserManager {
//    // Authentication
//    void login() {}
//    void logout() {}
//
//    // Payments
//    void deductCredits(int amount) {}
//
//    // Notifications
//    void sendPromoEmail() {}
//
//    // Analytics
//    void trackUserEvent() {}
//  }
//  Unrelated responsibilities, Changes in analytics break auth, Everyone depends on this "god class"

//  ? High Cohesion, Low Coupling
//  class AuthService {
//    void login() {}
//    void logout() {}
//  }
//
//  class WalletService {
//    void deduct(int credits) {}
//  }
//
//  class NotificationService {
//    void sendEmail() {}
//  }
//
//  class AnalyticsService {
//    void recordEvent() {}
//  }
//  Services focus on one domain ? high cohesion, They depend only on interfaces ? low coupling,
//  Changing one does not break others

public class App {

}
