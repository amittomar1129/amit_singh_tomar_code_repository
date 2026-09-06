package Java_8;

//  A Stream is a sequence of elements supporting functional-style operations (filter, map, reduce)
//  that does not store data and does not modify the source.

//  Why streams are not good for stateful operations?
//  Streams expect stateless lambdas. Shared mutable state breaks parallelism and correctness.

//  Difference between map() and flatMap()?
//  map() -> one-to-one transformation.
//  flatMap() -> flattens nested structures.
//  //map
//  List<Integer> lengths =
//  names.stream().map(String::length).toList();
//
//  //flatMap
//  List<String> words =
//      sentences.stream()
//          .flatMap(s -> Arrays.stream(s.split(" ")))
//          .toList();

//  What is lazy evaluation in streams?
//  Intermediate operations (map, filter) do nothing until a terminal operation (collect, forEach)
// is called.

//  Difference between forEach() and forEachOrdered()?
//  forEach() -> order not guaranteed in parallel streams.
//  forEachOrdered() -> preserves encounter order.
//  stream.parallel().forEach(System.out::println);
//  stream.parallel().forEachOrdered(System.out::println);

//  groupingBy vs partitioningBy?
//  groupingBy: multiple keys Map<K, List<T>>
//  partitioningBy boolean key Map<Boolean, List<T>>
//  partitioningBy(e -> e.getSalary() > 50000)

//  How do you find duplicates using streams?
//  Set<Integer> seen = new HashSet<>();
//  Set<Integer> duplicates =
//      list.stream()
//          .filter(x -> !seen.add(x))
//          .collect(Collectors.toSet());

//  How to sort using streams?
//    list.stream().sorted().toList();
//    list.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).toList();

//  How to handle exceptions in lambda?
//      list.forEach(x -> {
//                try {
//                  riskyMethod(x);
//                } catch (Exception e) {
//                  throw new RuntimeException(e);
//                }
//          });
//  lambdas don’t allow checked exceptions directly because they must conform to the method
// signature of the
//  functional interface and Java’s checked exceptions are part of that contract.
//  Example:
//  interface Processor {
//    void process() throws IOException;
//  }
//  A lambda assigned to this can throw IOException.
//  A lambda cannot throw broader checked exceptions than the functional interface allows.

//  Why Runtime Exceptions Are Allowed?
//  Unchecked exceptions are NOT part of method signature.
//  Don’t violate interface contracts.

//  How to convert Stream to Map?
//  Map<Integer, String> map = list.stream().collect(Collectors.toMap(User::getId,User::getName));
//  Handling duplicate keys: (toMap(k, v, (a,b) -> a))

//  Can streams modify source collection?
//  No, Streams are immutable views.

//  Streams are for readability, not magic performance.

//  Know WHEN NOT to use Streams?
//  A. You need complex control flow. Streams are bad for: break/continue, early exit based on
// multiple conditions, deeply nested logic.
//  Bad with streams
//  list.stream().forEach(x -> {
//          if (x == 10) return; // does NOT break stream
//      });
//  Better with loop
//      for (int x : list) {
//          if (x == 10) break;
//      }
//  B. When performance is critical & predictable.
//  Streams add: lambda allocation, iterator overhead, boxing/unboxing.
//  // Faster
//  for (int i = 0; i < arr.length; i++) sum += arr[i];
//
//  // Slower
//  Arrays.stream(arr).sum();
//  C. When debugging is important: Stepping through lambdas is harder than loops.
//  Streams for clarity, loops for control & performance.

//  Performance Trade-offs:
//    Scenario	                        Better
//  Simple iteration	                for-loop
//  Transformation pipelines	        streams
//  Large CPU-bound tasks	            parallel stream (carefully)
//  IO / DB / network	                No streams
//  Prefer primitive streams (IntStream, LongStream) when possible.

//  Memory pressure:
//  Streams create intermediate objects: filter -> map -> collect. Each step can allocate memory.

//  Parallel Stream Pitfalls: Parallel streams are dangerous when:
//  A. Shared mutable state
//  int sum = 0;
//  list.parallelStream().forEach(x -> sum += x); // race condition
//  “Parallel streams require stateless lambdas.”
//  Parallel streams is Bad for DB calls, REST calls, file IO. “Parallel streams can starve other
// threads in the JVM.”
//  “I use parallel streams only for large, CPU-bound, stateless workloads.”

//  orElse vs orElseGet
//  optional.orElse(expensiveCall());         // always executes
//  optional.orElseGet(this::expensiveCall);  // lazy

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreams {

  public static void main(String[] args) {

    // Find Minimum character length word from the list
    ArrayList<String> list = new ArrayList();
    list.add("Amit");
    list.add("Ram");
    list.add("Suresh");
    list.add("JP Morgan");
    list.add("Google");
    list.add("Shyam");

    Map.Entry<String, Integer> stringIntegerEntry = list.stream()
        .collect(Collectors.toMap(ele -> ele, v -> v.length())).entrySet().stream()
        .min((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue())).get();

    String minWord = list.stream()
        .min((a, b) -> Integer.compare(a.length(), b.length()))
        .orElse(null);

    System.out.println(minWord);

    //  First non-repeated character in a string:

    String str = "aabbcccdllmafllaa";
    char ch =
        str
            .chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(e -> e.getValue() == 1)
            .findFirst()
            .get()
            .getKey();
    System.out.println(ch);

    //  Word frequency in a sentence:

    String sentence = "My name is amit";
    Map<String, Long> freq =
        Arrays.stream(sentence.split(" "))
            .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    System.out.println(freq);

    //  First Non-Repeating Character:

    char firstNonRepeatingChar =
        "amitaaaokmi"
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    System.out.println(firstNonRepeatingChar);

    //  Sort List by Frequency (Descending):

    List<Integer> sortedNumbersByFrequency =
        IntStream.of(1, 2, 1, 3, 3, 3, 5, 5, 5, 5, 6, 6, 6, 6, 6)
            .boxed()
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
            .flatMap(e -> Collections.nCopies(e.getValue().intValue(), e.getKey()).stream())
            .collect(Collectors.toList());
    System.out.println(sortedNumbersByFrequency);

    //  Group Words by Anagram (Words in the same group are anagrams):

    Map<String, List<String>> anagramMap =
        Stream.of("eat", "tea", "tan", "ate", "nat", "bat")
            .collect(
                Collectors.groupingBy(
                    word ->
                        word.chars()
                            .mapToObj(c -> (char) c)
                            .sorted()
                            .collect(
                                StringBuilder::new, StringBuilder::append, StringBuilder::append)
                            .toString()));
    System.out.println(anagramMap);

    //  Sort employees by salary desc, then name
    List<String> skillA = new ArrayList<>();
    skillA.add("A");
    skillA.add("A1");
    List<String> skillB = new ArrayList<>();
    skillB.add("B");
    skillB.add("B1");

    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee("john", 100d, "Tech", skillA));
    employees.add(new Employee("ram", 200d, "HR", skillB));
    employees.add(new Employee("amit", 200d, "Tech", skillA));
    employees.add(new Employee("amit", 400d, "HR", skillA));
    employees.add(new Employee("amit", 900d, "Fin", skillB));

    List<Employee> sortedList =
        employees.stream()
            .sorted(
                Comparator.comparing(Employee::getSalary)
                    .reversed()
                    .thenComparing(Employee::getName))
            .collect(Collectors.toList());
    System.out.println(sortedList);
    // OR
    List<Employee> sortedList1 =
        employees.stream()
            .sorted(
                Comparator.comparing((Employee e) -> e.salary)
                    .reversed()
                    .thenComparing(p -> p.getName()))
            .collect(Collectors.toList());
    System.out.println(sortedList1);

    //  Find max salary employee:

    Optional<Employee> max = employees.stream().max(Comparator.comparing(Employee::getSalary));
    System.out.println(max.get());

    //  Convert list to map (handle duplicates):

    Map<String, Employee> listToMap =
        employees.stream()
            .collect(Collectors.toMap(e -> e.getName(), v -> v, (oldVal, newVal) -> oldVal));
    System.out.println(listToMap);

    //  Sum all Salaries:

    int sumSal = employees.stream().map(Employee::getSalary).mapToInt(Double::intValue).sum();
    System.out.println(sumSal);

    //  Partition numbers into even & odd:

    ArrayList<Integer> integers = new ArrayList<>();
    integers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));

    Map<Boolean, List<Integer>> partitioned =
        IntStream.of(1, 5, 3, 65, 7, 2, 89, 90, 32, 54, 12, 72, 16)
            .boxed()
            .collect(
                Collectors.partitioningBy(
                    n -> n % 2 == 0, Collectors.toCollection(LinkedList::new)));
    System.out.println(partitioned);
    // Boxed method converts IntStream class to Stream<Integer> class

    //  Average salary per department:

    Map<String, Double> avgSalPerDepartment =
        employees.stream()
            .collect(
                Collectors.groupingBy(
                    Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
    System.out.println(avgSalPerDepartment);

    //  Highest paid employee per department:

    Map<String, Employee> highesPaidSalPerDep =
        employees.stream()
            .collect(
                Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                        Optional::get)));
    System.out.println(highesPaidSalPerDep);

    //  Extract unique skills from employees:

    Set<String> skills =
        employees.stream().flatMap(e -> e.getSkills().stream()).collect(Collectors.toSet());
    System.out.println(skills);

    //  Sum using reduce:

    Optional<Integer> reduceSum = IntStream.of(2, 3, 1, 54, 15).boxed().reduce((a, b) -> a + b);
    System.out.println(reduceSum.get());

    //  Longest string:

    Optional<String> maxString =
        Stream.of("Amit", "Tomar", "My").max(Comparator.comparing(String::length));
    System.out.println(maxString.get());

    //  Concatenate strings with comma:

    String joining = Stream.of("Amit", "Tomar", "My").collect(Collectors.joining(","));
    System.out.println(joining);

    //  Second-highest number:

    Integer nHighest =
        IntStream.of(1, 43, 2, 3, 76, 8, 9, 3, 5, 112, 432)
            .boxed()
            .sorted(Comparator.comparingInt(Integer::intValue).reversed())
            .skip(2)
            .findFirst()
            .get();
    // OR
    IntStream.of(1, 43, 2, 3, 76, 8, 9, 3, 5, 112, 432)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .skip(2)
        .findFirst()
        .get();

    System.out.println(nHighest);

    //  Sort Map by its values in non-ascending order:

    Map<String, Integer> map = new HashMap<>();
    map.put("Amit", 99);
    map.put("Sumit", 99);
    map.put("Ram", 12);
    map.put("Krishna", 55);
    map.put("Shiva", 65);
    map.put("Durga", 32);

    Map<String, Integer> sortedMap =
        map.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    System.out.println(sortedMap);

    //  Sort Map by its values in non-ascending order then its Key in ascending order:

    Map<String, Integer> sortedMap1 =
        map.entrySet().stream()
            .sorted(
                Map.Entry.<String, Integer>comparingByValue()
                    .reversed()
                    .thenComparing(Map.Entry.comparingByKey()))
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    System.out.println(sortedMap1);

    //  Problem:

    List<Integer> collect1 =
        IntStream.of(1, 1, 3, 2, 2, 4, 4, 4, 5, 5, 6, 6, 6, 6, 7)
            .boxed()
            .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Comparator.comparing(Map.Entry<Integer, Long>::getValue).reversed())
            .collect(
                Collectors.toMap(
                    s -> s.getKey(), d -> d.getValue(), (oldV, newV) -> oldV, LinkedHashMap::new))
            .entrySet()
            .stream()
            .map(
                a ->
                    IntStream.range(0, a.getValue().intValue())
                        .boxed()
                        .map(b -> a.getKey())
                        .collect(Collectors.toList()))
            .flatMap(List::stream)
            .collect(Collectors.toList());
    System.out.println(collect1);

    //   Grouping by provided collection classes:

    String string = "abcdsasanmkans";
    HashMap<Character, LinkedList> collect2 =
        string
            .chars()
            .mapToObj(c -> (char) c)
            .collect(
                Collectors.groupingBy(
                    Character::charValue,
                    LinkedHashMap::new,
                    Collectors.toCollection(LinkedList::new)));
    System.out.println(collect2);

    //  Map each character to all indexes where it appears.:

    Map<Character, List<Integer>> result =
        IntStream.range(0, string.length())
            .boxed()
            .collect(Collectors.groupingBy(i -> string.charAt(i), Collectors.toList()));
    System.out.println(result);

    //  Custom Collector: A Custom Collector is used when built-in collectors (toList, groupingBy,
    // partitioningBy, etc.)
    //  cannot express your aggregation logic cleanly or efficiently.
    //  Collector Interface- A collector has 4 core components: Collector<T, A, R>
    //      T	-> Stream element
    //      A	-> Mutable accumulator
    //      R	-> Final result
    //  And 5 methods:
    //      supplier() creates a new mutable container.
    //      accumulator() adds each stream element.
    //      combiner() merges partial results (parallel streams).
    //      finisher() is skipped here because accumulator == result.

    //   Example: Group employees by department and calculate average salary.

    Collector<Employee, AvgSalary, Map<String, Double>> avgSalaryCustomCollector =
        Collector.of(
            AvgSalary::new,
            (avgSal, e) -> avgSal.add(e),
            (a, b) -> {
              a.merge(b);
              return a;
            },
            AvgSalary::avg);

    Map<String, Double> collect = employees.stream().collect(avgSalaryCustomCollector);
    System.out.println(collect);
  }

  static class Employee {

    public String name;
    public Double salary;
    public String department;
    public List<String> skills;

    public Employee(String name, Double salary) {
      this.name = name;
      this.salary = salary;
    }

    public Employee(String name, Double salary, String department) {
      this.name = name;
      this.salary = salary;
      this.department = department;
    }

    public Employee(String name, Double salary, String department, List<String> skills) {
      this.name = name;
      this.salary = salary;
      this.department = department;
      this.skills = skills;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Double getSalary() {
      return salary;
    }

    public void setSalary(Double salary) {
      this.salary = salary;
    }

    public String getDepartment() {
      return department;
    }

    public void setDepartment(String department) {
      this.department = department;
    }

    public List<String> getSkills() {
      return skills;
    }

    public void setSkills(List<String> skills) {
      this.skills = skills;
    }

    @Override
    public String toString() {
      return "Employee{"
          + "name='"
          + name
          + '\''
          + ", salary="
          + salary
          + ", department="
          + department
          + '}';
    }
  }

  static class AvgSalary {

    private Map<String, Stats> depStat = new HashMap<>();

    static class Stats {

      private double totalSal;
      private int count;

      public double getTotalSal() {
        return totalSal;
      }

      public void setTotalSal(double totalSal) {
        this.totalSal = totalSal;
      }

      public int getCount() {
        return count;
      }

      public void setCount(int count) {
        this.count = count;
      }
    }

    public void add(Employee employee) {
      Stats empStat = depStat.getOrDefault(employee.getDepartment(), new Stats());
      empStat.totalSal += employee.getSalary();
      empStat.count++;
      depStat.put(employee.getDepartment(), empStat);
    }

    void merge(AvgSalary other) {
      for (Map.Entry<String, Stats> otherMap : other.depStat.entrySet()) {
        if (depStat.containsKey(otherMap.getKey())) {
          Stats existing = depStat.get(otherMap.getKey());
          Stats newStat = otherMap.getValue();
          existing.setTotalSal(existing.getTotalSal() + newStat.getTotalSal());
          existing.setCount(existing.getCount() + 1);
          depStat.put(otherMap.getKey(), existing);
        } else {
          depStat.put(otherMap.getKey(), otherMap.getValue());
        }
      }
    }

    public Map<String, Double> avg() {
      Map<String, Double> output = new HashMap<>();
      for (Map.Entry<String, Stats> map : depStat.entrySet()) {
        output.put(map.getKey(), map.getValue().totalSal / map.getValue().count);
      }
      return output;
    }
  }
}
