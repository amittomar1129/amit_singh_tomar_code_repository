package Java_8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class java11 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String as = "  ";
    System.out.println(as.isBlank());
    System.out.println(as.isEmpty());

    String lines = "asma\nknsa" + "kmaknksan" + "mkmask";
    lines.lines().forEach(System.out::println);

    String str = " JD ";
    System.out.print("Start");
    System.out.print(str.strip());
    System.out.println("End");

    System.out.print("Start");
    System.out.print(str.stripLeading());
    System.out.println("End");

    System.out.print("Start");
    System.out.print(str.stripTrailing());
    System.out.println("End");

    String strs = "=".repeat(2);
    System.out.println(strs); // prints ==

    ArrayList<Integer> list = new ArrayList<>();
    list.add(12);
    list.add(22);
    list.add(12);
    list.add(432);
    list.add(22);
    list.add(4);
    list.add(6);

    Stream<Integer> s = Stream.of(11, 22, 32, 12, 55, 99, 88, 97, 34, 9);

    List ss = s.map(l -> l + 1).collect(Collectors.toList());
    System.out.println(ss);
  }
}
