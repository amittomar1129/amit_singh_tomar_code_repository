package datastructure_algorithms.datastructure.linear.queue;

import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {

  private static void enqueue(BlockingQueue<String> queue, String element) {
    log("Calling queue.put(%s) (queue = %s)...", element, queue);
    try {
      queue.put(element);
      log("Called queue.put(%s) (queue = %s)", element, queue);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void dequeue(BlockingQueue<String> queue) {
    log("    Calling queue.take() (queue = %s)...", queue);
    try {
      String element = queue.take();
      log("Called queue.take() returned %s (queue = %s)", element, queue);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void log(String message, Object... args) {
    System.out.printf(Locale.US, "[%s] %s \n", Thread.currentThread().getName(),
        String.format(message, args));
  }

  public static void main(String[] args) throws InterruptedException {
    BlockingQueue<String> queue = new SynchronousQueue<>(false);

    // Start 3 producing threads
    for (int i = 0; i < 3; i++) {
      String element = String.format("Amit%d", i);
      new Thread(() -> enqueue(queue, element)).start();
      Thread.sleep(2000);
    }
    log("##################1");

    // Start 6 consuming threads
    for (int i = 0; i < 6; i++) {
      new Thread(() -> dequeue(queue)).start();
      Thread.sleep(2000);
    }
    System.out.println("##################2");
    // Start 3 more producing threads
    for (int i = 3; i < 6; i++) {
      String element = String.format("Amit%d", i); // Assign to an effectively final variable
      new Thread(() -> enqueue(queue, element)).start();
      Thread.sleep(2000);
    }
  }
}
