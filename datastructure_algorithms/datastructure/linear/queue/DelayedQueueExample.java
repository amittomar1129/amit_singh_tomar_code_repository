package datastructure_algorithms.datastructure.linear.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedQueueExample {

  public static void main(String[] args) throws InterruptedException {
    DelayQueue<DelayedElement<String>> bQeque1 = new DelayQueue<>();
    bQeque1.offer(new DelayedElement<>("Amit1", 5000L));
    bQeque1.offer(new DelayedElement<>("Amit2", 1000L));
    bQeque1.offer(new DelayedElement<>("Amit3", 3000L));
    bQeque1.offer(new DelayedElement<>("Amit4", 4000L));
    bQeque1.offer(new DelayedElement<>("Amit5", 6000L));
    System.out.println(bQeque1);
    bQeque1.stream().iterator().forEachRemaining(d -> System.out.println(d) );
    System.out.println(bQeque1.take());
    System.out.println(bQeque1.take());
    System.out.println(bQeque1.take());
    System.out.println(bQeque1.take());
  }
}

class DelayedElement<E> implements Delayed {

  private final E element;
  private final long delayMillis;
  private final long expiration;

  DelayedElement(E element, long delayMillis) {
    this.element = element;
    this.delayMillis = delayMillis;
    this.expiration = System.currentTimeMillis() + delayMillis;
  }

  /**
   * @param unit the time unit
   */
  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(this.expiration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
  }

  /**
   * @param o the object to be compared.
   */
  @Override
  public int compareTo(Delayed o) {
    DelayedElement<?> other = (DelayedElement<?>) o;
    return Long.compare(this.expiration, other.expiration);
  }

  @Override
  public String toString() {
    return String.format("{%s, %dms}", element, delayMillis);
  }
}
