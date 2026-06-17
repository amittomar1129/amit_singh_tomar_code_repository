package designprinciple.core.designfortestability;

//  Design for Testability Principle:

//  Make it easy to isolate components, observe behavior, control dependencies, and assert outcomes.
//  The more testable your system is, the more confidently it can evolve.

//  Bad design (hard to test):
//
//  public void sendNotification(User u) {
//    EmailService.send(u.getEmail()); // static call
//    Logger.log("notification sent");
//  }
//
//  class NotificationService {
//    private final EmailService email;
//    private final Logger logger;
//    private final Clock clock;
//
//    public NotificationService(EmailService email, Logger logger, Clock clock) {
//      this.email = email;
//      this.logger = logger;
//      this.clock = clock;
//    }
//
//    public void send(User u) {
//      email.send(u.getEmail());
//      logger.info("Sent at " + clock.now());
//    }
//  }
//  Email is mockable
//  Logger is mockable
//  Time is deterministic
//  Behavior is isolated

public class App {

}
