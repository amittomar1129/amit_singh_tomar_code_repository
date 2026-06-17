package code;

import java.util.HashMap;
import java.util.HashSet;

public class Practise {

	public static void main(String... args)
	{
		AbtractK.Amit p = new AbtractK().new Amit();
		
		HashSet<Student> set =new HashSet<>();
		
		Student s1 = new Student();
		s1.setId(1);
		
		Student s2 = new Student();
		s2.setId(1);
		
		set.add(s1);
		set.add(s2);
		
		System.out.println(s1);
		System.out.println(s2);
	
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());	
		
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
		System.out.println(set);
		
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		
		HashMap<Student, String> m1 = new HashMap<>();
		m1.put(s1, "AMIT");
		m1.put(s2, "AMIT DD");
		
		System.out.println(m1);
		
	}

}
