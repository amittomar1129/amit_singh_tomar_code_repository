package Java_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lambda {

  public Lambda() throws NullPointerException {
    System.out.println("k");
  }

  private static String iM() throws NullPointerException {
    System.out.println("IM");
    return "Imm";
  }

  public static void main(String[] args) throws Exception {

    /*Inter i = () -> { System.out.println("AMIT");
    return "Amit";
    };*/

    Lambda lambda = new Lambda();
    Inter il = Lambda::new;

    System.out.println(il.process());

    ArrayList<Integer> list = new ArrayList<>();
    list.add(12);
    list.add(22);
    list.add(12);
    list.add(432);
    list.add(22);
    list.add(4);
    list.add(6);

    Stream<Integer> str = Stream.of(11, 22, 32, 12, 55, 99, 88, 97, 34, 9);

    List ss = str.map(s -> s + 1).collect(Collectors.toList());
    System.out.println(ss);

    // str.forEach(System.out::println);

    int sum = Stream.of(11, 12, 34, 12).map(l -> l + 1).mapToInt(Integer::intValue).sum();
    Integer d = Stream.of(11, 12, 34, 12).collect(Collectors.summingInt(Integer::intValue));
    System.out.println("-------------" + sum);
    System.out.println("-------------" + d);

    Stream.of("a1", "a2", "a3")
        .map(s -> s.substring(1))
        .mapToInt(Integer::parseInt)
        .min()
        .ifPresent(System.out::println);

    List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

    myList.stream()
        .filter(s -> s.startsWith("c"))
        .map(String::toUpperCase)
        .sorted((d1, d2) -> d2.compareTo(d1))
        .forEach(System.out::println);

    Arrays.asList("a1", "a2", "a3").stream().findFirst().ifPresent(System.out::println);

    IntStream.range(1, 4).forEach(System.out::println);

    Arrays.stream(new int[] {1, 2, 3}).map(n -> 2 * n + 1).average().ifPresent(System.out::println);

    Stream.of("a1", "a2", "a3")
        .map(s -> s.substring(1))
        .mapToInt(Integer::parseInt)
        .max()
        .ifPresent(System.out::println);

    IntStream.range(1, 4).mapToObj(i -> "a" + i).forEach(System.out::println);

    Stream.of(1.0, 2.0, 3.0)
        .mapToInt(Double::intValue)
        .mapToObj(i -> "a" + i)
        .forEach(System.out::println);

    System.out.println("-----------------------");

    Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(
            s -> {
              System.out.println("filter: " + s);
              return true;
            })
        .forEach(System.out::println);

    System.out.println("-----------------------");

    Stream.of("d2", "a2", "b1", "b3", "c")
        .map(
            s -> {
              System.out.println("map: " + s);
              return s.toUpperCase();
            })
        .anyMatch(
            s -> {
              System.out.println("anyMatch: " + s);
              return s.startsWith("B");
            });

    System.out.println("-----------------------");

    Stream.of("d2", "a2", "b1", "b3", "c")
        .map(
            s -> {
              System.out.println("map: " + s);
              return s.toUpperCase();
            })
        .filter(
            s -> {
              System.out.println("filter: " + s);
              return s.startsWith("A");
            })
        .forEach(s -> System.out.println("forEach: " + s));

    System.out.println("-----------------------");

    Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(
            s -> {
              return s.startsWith("a");
            })
        .map(
            s -> {
              return s.toUpperCase();
            })
        .forEach(s -> System.out.println("forEach: " + s));

    List<String> list1 = new ArrayList<>();
    list1.add("Amit");
    list1.add("Singh");
    list1.add("Tomar");
    Optional<String> reduce = list1.stream().reduce((a, b) -> a + b);
    System.out.println(reduce.get());
    String s1 = list1.stream().collect(Collectors.joining(","));
    System.out.println(s1);

    String o = list1.stream().filter(l -> l.equals("Amit")).map(l -> "Tomar").findAny().get();
    System.out.println(o);
  }
}
