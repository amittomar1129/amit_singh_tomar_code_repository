package datastructure_algorithms.datastructure.linear.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class App {

  // Queue: is used to keep the elements that are processed in the First In First Out (FIFO) manner.
  // It is an ordered list of objects, where insertion of elements occurs at the end of the list, and
  // removal of elements occur at the beginning of the list. Java Queue supports all methods of Collection interface.
  // All Queues which are available in java.util package are Unbounded Queues and Queues which are available in
  // java.util.concurrent package are Bounded Queues.

  // Deques: are queues but they support element insertion and removal at both ends. All Deques are not thread-safe.
  // BlockingQueues: BlockingQueues are used to implement Producer/Consumer based applications.
  // BlockingQueues do not accept null elements. If we perform any null related operation, it throws NullPointerException.
  // BlockingQueues are thread-safe.
  // ConcurrentLinkedQueue is an unbounded thread-safe Queue based on linked nodes.

  // If it is required to have a thread safe implementation, PriorityBlockingQueue is an available option.
  // PriorityQueue, ArrayBlockingQueue and LinkedList are the implementations that are used most frequently.

  // Types of Queue:
  // Queue is an interface and Child classes are >>> PriorityQueue.
  // Child interface of Queue are >>> BlockingQueue, Deque, BlockingDeque, TransferQueue.
  // BlockingQueue >>> PriorityBlockingQueue, LinkedBlockingQueue.
  // Deque >>> ArrayDeque, LinkedList.
  // BlockingDeque >>> LinkedBlockingDeque.

  // add vs offer
  // add throws IllegalStateException if we add on full queue or capacity restriction.
  // offer return false in such case.
  // remove vs poll
  // poll throws NoSuchElementException if we remove on empty queue offer return null in such case.

  public static void main(String[] args) throws InterruptedException {

    Queue queue = new PriorityQueue();
    queue.add("Z");
    queue.add("A");
    queue.add("DC");
    queue.add("Amit");
    queue.add("DA");
    queue.add("M");
    System.out.println(queue);
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());


    LinkedBlockingDeque<String> queue1 = new LinkedBlockingDeque<>();
    queue1.offer("Amit1");
    queue1.offer("Amit2");
    queue1.offer("Amit3");
    queue1.offer("Amit4");
    System.out.println(queue1.poll());
    System.out.println(queue1.poll());
    System.out.println(queue1.poll());
    System.out.println(queue1);


    BlockingQueue bQeque2 = new LinkedBlockingQueue<String>();

    Deque deque1 = new ArrayDeque();
    Deque deque2 = new LinkedList();
    BlockingDeque bDeque3 = new LinkedBlockingDeque();
    ConcurrentLinkedDeque<String> strings = new ConcurrentLinkedDeque<>();
  }
}
