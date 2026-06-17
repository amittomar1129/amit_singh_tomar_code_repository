package datastructure_algorithms.datastructure.linear.hash;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface Map<K, V> {

  V put(K key, V value);

  V get(K key);

  boolean remove(K key);

  boolean replace(K key, V value);

  boolean replace(K key, V oldValue, V newValue);

  boolean containsKey(K key);

  boolean containsValue(V Value);

  int size();

  void clear();

  Set<K> keySet();

  Set<Entry> entrySet();

  List<V> values();

  void forEach(Consumer<Entry<K, V>> consumer);
}
