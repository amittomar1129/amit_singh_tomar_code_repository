package designpattern.creational.singleton;

// Spring scans classes annotated with @Service, @Component, or @Bean.
// It creates exactly one instance of each (Singleton scope by default).
// Then it injects dependencies automatically (constructor injection).
// The framework controls the lifecycle — you never call getInstance() manually.


// 1. Singleton Service - managed by framework
//@Service  // Spring automatically creates a single shared instance (Singleton scope)
//public class ConfigService {
//  private final String environment;
//
//  public ConfigService() {
//    this.environment = "Production";
//  }
//
//  public String getEnvironment() {
//    return environment;
//  }
//
//  public void printConfig() {
//    System.out.println("Environment: " + environment);
//  }
//}
//
//// 2. Client class that depends on the Singleton
//@Component
//class PaymentProcessor {
//
//  private final ConfigService configService;
//
//  // ? Dependency Injection through constructor
//  public PaymentProcessor(@Autowired ConfigService configService) {
//    this.configService = configService;
//  }
//
//  public void processPayment() {
//    configService.printConfig();
//    System.out.println("Processing payment in " + configService.getEnvironment() + " mode...");
//  }
//}
//
//@SpringBootApplication
//public class DIFrameworkSingleton {
//
//  private final PaymentProcessor paymentProcessor;
//
//  public DIFrameworkSingleton(PaymentProcessor paymentProcessor) {
//    this.paymentProcessor = paymentProcessor;
//  }
//
//  public static void main(String[] args) {
//    ApplicationContext ctx = SpringApplication.run(DIFrameworkSingleton.class, args);
//  }
//
//  @Override
//  public void run(String... args) {
//    paymentProcessor.processPayment();
//  }
//}

public class DIFrameworkSingleton {

}