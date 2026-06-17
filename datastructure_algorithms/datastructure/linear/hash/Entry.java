package datastructure_algorithms.datastructure.linear.hash;

import java.util.Objects;

public class Entry<K, V> {

  private K key;
  private V value;
  Entry<K, V> next;  // For handling collisions with chaining

  public Entry() {
  }

  public Entry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public Entry(K key, V value, Entry<K, V> next) {
    this.key = key;
    this.value = value;
    this.next = next;
  }

  public K getKey() {
    return key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public V getValue() {
    return value;
  }

  public void setValue(V value) {
    this.value = value;
  }

  public Entry<K, V> getNext() {
    return next;
  }

  public void setNext(Entry<K, V> next) {
    this.next = next;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Entry<?, ?> entry = (Entry<?, ?>) o;
    return Objects.equals(key, entry.key);
  }

  @Override
  public int hashCode() {
    int prime = 13;
    int mul = 11;
    if (key != null) {
      return prime * mul + key.hashCode();
    }
    return 0;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(String.format("{%s, %s}", key, value));
    Entry<K, V> next = this.getNext();
    while (next != null) {
      builder.append(String.format("-->{%s, %s}", next.getKey(), next.getValue()));
      next = next.getNext();
    }
    return builder.toString();
  }
}
