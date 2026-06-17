package datastructure_algorithms.datastructure.linear.queue;

import java.util.Locale;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class LinkedTransferQueueExample {

  private static void startTransferThread(TransferQueue<String> queue, String element)
      throws InterruptedException {
    new Thread(() -> enqueueViaTransfer(queue, element)).start();

    // Wait a bit to let the thread enqueue the element
    Thread.sleep(2000);
    log("--> queue = " + queue);
  }

  private static void enqueueViaTransfer(TransferQueue<String> queue, String element) {
    log("Calling queue.transfer(%s)...", element);
    try {
      queue.transfer(element);
      log("Called queue.transfer(%s) --> queue = %s", element, queue);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void enqueueViaPut(TransferQueue<String> queue, String element)
      throws InterruptedException {
    log("Calling queue.put(%s)...", element);
    queue.put(element);
    log("Called queue.put(%s) --> queue = %s", element, queue);
  }

  private static void dequeueViaTake(TransferQueue<String> queue)
      throws InterruptedException {
    log("Calling queue.take() (queue = %s)...", queue);
    String element = queue.take();
    log("Called queue.take() returned %s --> queue = %s", element, queue);

    // Wait a bit to get the log output in a readable order
    Thread.sleep(2000);
  }

  private static void log(String format, Object... args) {
    System.out.printf(
        Locale.US, "[%s] %s \n",
        Thread.currentThread().getName(),
        String.format(format, args));
  }

  public static void main(String[] args) throws InterruptedException {
    TransferQueue<String> queue = new LinkedTransferQueue<>();

    // Start 2 threads calling queue.transfer(),
    startTransferThread(queue, "Amit1");
    startTransferThread(queue, "Amit2");

    System.out.println("######################1");
    // ... then put one element directly,
    enqueueViaPut(queue, "Amit3");

    System.out.println("######################2");
    // ... then start 2 more threads calling queue.transfer().
    startTransferThread(queue, "Amit4");
    startTransferThread(queue, "Amit5");

    System.out.println("######################3");
    // Now take all elements until the queue is empty
    while (!queue.isEmpty()) {
      dequeueViaTake(queue);
    }
  }

}
