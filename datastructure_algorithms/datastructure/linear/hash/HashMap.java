package datastructure_algorithms.datastructure.linear.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class HashMap<K, V> implements Map<K, V> {

  private int size;
  private Entry<K, V>[] data;
  private static final int LOAD_FACTOR = 70;
  private static int CURRENT_CAPACITY = 10;

  public HashMap() {
    this.data = new Entry[CURRENT_CAPACITY];
  }

  private int hashing(int hashcode) {
    int bucketLocation = hashcode % CURRENT_CAPACITY;
    return bucketLocation;
  }

  /**
   *
   */
  @Override
  public V put(K key, V value) {
    if (key == null) {
      throw new NullPointerException("Insertion of null keys are not allowed.");
    }
    int location = hashing(key.hashCode());
    Entry<K, V> newElement = new Entry(key, value);
    if (data[location] != null) {
      Entry<K, V> element = data[location];
      while (element.getNext() != null) {
        element = element.getNext();
      }
      element.setNext(newElement);
    } else {
      data[location] = newElement;
      size++;
    }
    ensureCapacity();
    return value;
  }

  /**
   *
   */
  @Override
  public V get(K key) {
    if (key == null) {
      throw new NullPointerException("key is null.");
    }
    int location = hashing(key.hashCode());
    if (location > 0 && location < CURRENT_CAPACITY) {
      Entry<K, V> foundEntry = this.data[location];
      if (foundEntry != null) {
        while (foundEntry.getNext() != null) {
          foundEntry = foundEntry.getNext();
        }
        return foundEntry.getValue();
      }
    }
    return null;
  }

  /**
   *
   */
  @Override
  public boolean remove(K key) {
    if (key == null) {
      return false;
    }
    int location = hashing(key.hashCode());
    if (location > 0 && location < CURRENT_CAPACITY) {
      this.data[location] = null;
      size--;
      return true;
    }
    return false;
  }

  /**
   *
   */
  @Override
  public boolean replace(K key, V value) {
    return false;
  }

  /**
   *
   */
  @Override
  public boolean replace(K key, V oldValue, V newValue) {
    return false;
  }

  /**
   *
   */
  @Override
  public boolean containsKey(K key) {
    return false;
  }

  /**
   *
   */
  @Override
  public boolean containsValue(V Value) {
    return false;
  }

  /**
   *
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   *
   */
  @Override
  public void clear() {
    this.data = new Entry[CURRENT_CAPACITY];
  }

  /**
   *
   */
  @Override
  public Set<K> keySet() {
    HashSet<K> set = new HashSet<>();
    if (size == 0) {
      return set;
    }
    for (int i = 0; i < this.data.length; i++) {
      if (data[i] != null) {
        set.add(data[i].getKey());
      }
    }
    return set;
  }

  /**
   *
   */
  @Override
  public Set<Entry> entrySet() {
    HashSet<Entry> set = new HashSet<>();
    if (size == 0) {
      return set;
    }
    for (int i = 0; i < this.data.length; i++) {
      if (data[i] != null) {
        set.add(data[i]
        );
      }
    }
    return set;
  }

  /**
   *
   */
  @Override
  public List<V> values() {
    List<V> list = new ArrayList<>();
    if (size == 0) {
      return list;
    }
    for (int i = 0; i < this.data.length; i++) {
      if (data[i] != null) {
        list.add(data[i].getValue());
      }
    }
    return list;
  }

  /**
   *
   */
  @Override
  public void forEach(Consumer<Entry<K, V>> consumer) {
    for (int i = 0; i < data.length; i++) {
      if (data[i] != null) {
        consumer.accept(data[i]);
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("HashMap[ elements:");
    Arrays.stream(data).filter(element -> element != null)
        .forEach(entry -> builder.append(" ").append(entry).append(","));
    builder.append(String.format(" size: %s]", size));
    return builder.toString();
  }

  private void ensureCapacity() {
    if (size >= CURRENT_CAPACITY * LOAD_FACTOR / 100) {
      CURRENT_CAPACITY = CURRENT_CAPACITY * 2 + 1;
      Entry<K, V>[] newData = new Entry[CURRENT_CAPACITY];
      for (int i = 0; i < data.length; i++) {
        if (this.data[i] != null) {
          int newLocation = hashing(this.data[i].getKey().hashCode());
          newData[newLocation] = this.data[i];
        }
      }
      this.data = newData;
    }
  }


  public static void main(String[] args) {
    java.util.HashMap<String, Integer> m = new java.util.HashMap();
    m.put("amit2", 200);
    m.put("amit3", 300);
    m.put("amit1", 100);
    m.put("amit1", 1900);

    HashMap<String, Integer> map = new HashMap();
    System.out.println(map);
    map.put("amit1", 100);
    map.put("amit2", 200);
    map.put("amit3", 300);
    map.put("amit4", 400);
    map.put("amit1", 900);
    map.put("amit1", 700);
    map.put("amit5", 300);
    System.out.println(map);
    System.out.println(map.get("amit1"));
    System.out.println(map.remove(null));
    System.out.println(map);
    Set<String> keySet = map.keySet();
    System.out.println(keySet);
    List<Integer> values = map.values();
    System.out.println(values);
    Set<Entry> entrySet = map.entrySet();
    System.out.println(entrySet);
    map.forEach(element -> System.out.println(element.getKey()));
  }
}
