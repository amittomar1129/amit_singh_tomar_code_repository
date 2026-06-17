import java.util.ArrayList;
import java.util.SequencedCollection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;


public class JavaFeatures {

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    //  Creating virtual thread in three ways
    Thread.startVirtualThread(
        () -> {
          System.out.println("Virtual Thread 1");
        });

    Thread vt =
        Thread.ofVirtual()
            .name("my-worker")
            .unstarted(
                () -> {
                  System.out.println("Virtual Thread 2");
                });
    vt.start();

    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      Future<?> task1 = executor.submit(() -> System.out.println("Virtual Thread 3"));
      executor.submit(() -> System.out.println("Virtual Thread 4"));

      // Wait for the task to complete
      task1.get();

      System.out.println("Program Completed !!");
    } // Automatically waits for tasks to finish

    System.out.println("Main");

    String st = "Amit";
    int a = 12;
    String s = STR."Hello \{st} \{a} \{a}";
    System.out.println(s);





  }

}


