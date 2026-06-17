package Java_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambda1 {

  public static void main(String[] args) {

    Stream.of("d2", "a2", "b1", "a3", "c")
        .sorted(
            (s1, s2) -> {
              return s1.compareTo(s2);
            })
        .filter(
            s -> {
              return s.startsWith("a");
            })
        .map(
            s -> {
              return s.toUpperCase();
            })
        .forEach(s -> System.out.println("forEach: " + s));

    System.out.println("--------------------------DDDDDDDDDDDDDDDDDDD");

    Stream<String> stream = Stream.of("d2", "a2", "a1", "b3", "c").filter(s -> s.startsWith("a"));

    stream.anyMatch(s -> s.startsWith("a")); // ok
    // stream.noneMatch(s -> true); // exception

    System.out.println("--------------------------");

    Supplier<Stream<String>> streamSupplier =
        () -> Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> s.startsWith("a"));

    streamSupplier.get().anyMatch(s -> true); // ok
    streamSupplier.get().noneMatch(s -> true); // ok

    System.out.println("--------------------------");
    List<Person> persons =
        Arrays.asList(
            new Person("Max", 18),
            new Person("Peter", 23),
            new Person("Pamela", 23),
            new Person("David", 12));
    List<Person> filtered =
        persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());

    System.out.println(filtered);

    System.out.println("--------------------------");

    Map<Integer, List<Person>> personsByAge =
        persons.stream().collect(Collectors.groupingBy(p -> p.age));

    personsByAge.forEach((age, p) -> System.out.println(age + "   " + p));

    Map<Integer, Long> personsByAges =
        persons.stream().collect(Collectors.groupingBy(p -> p.age, Collectors.counting()));

    System.out.println(personsByAges);

    System.out.println("--------------------------");
    Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));
    System.out.println(averageAge);

    System.out.println("--------------------------");

    IntSummaryStatistics ageSummary =
        persons.stream().collect(Collectors.summarizingInt(p -> p.age));
    System.out.println(ageSummary);

    System.out.println("--------------------------");

    String phrase =
        persons.stream()
            .filter(p -> p.age >= 18)
            .map(p -> p.name)
            .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

    System.out.println(phrase);

    System.out.println("--------------------------");

    Collector<Person, StringJoiner, String> personNameCollector =
        Collector.of(
            () -> new StringJoiner(" | "), // supplier
            (j, p) -> j.add(p.name.toUpperCase()), // BiConsumer
            (j1, j2) -> j1.merge(j2), // BinaryOperator
            StringJoiner::toString); // Function

    String names = persons.stream().collect(personNameCollector);

    System.out.println(names);

    System.out.println("--------------------------");
    System.out.println("--------------------------");
    System.out.println("--------------------------");
    System.out.println("--------------------------");
    System.out.println("--------------------------");
    System.out.println("--------------------------");
    System.out.println("--------------------------");
    System.out.println("--------------------------");
    System.out.println("--------------------------");
    System.out.println("--------------------------");
    System.out.println("--------------------------");
    System.out.println("--------------------------");

    List<String> list = new ArrayList<>();

    list.add("Hello");
    list.add("Hello");
    list.add("World");

    Map<String, Long> counted =
        list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Map<String, Long> countedf =
        list.stream().collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));

    System.out.println(counted);
    System.out.println(countedf);

    List<String> list1 = new ArrayList<>();
    list1.add("Amit");
    list1.add("Singh");
    list1.add("Tomar");
    String s = list1.stream().map(e -> e.toString()).reduce("", String::concat);
    System.out.println(s);
    String s1 = list1.stream().map(Object::toString).collect(Collectors.joining(","));
    System.out.println(s1);

    String o = list1.stream().filter(l -> l.equals("Amit")).map(l -> "Tomar").findAny().get();
    System.out.println(o);

    List<String> o1 =
        list1.stream().map(l -> l.equals("Amit") ? "Tomar" : l).collect(Collectors.toList());
    System.out.println(o1);
  }
}

class Person {

  String name;
  int age;

  Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
