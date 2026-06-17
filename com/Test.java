package com;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Test {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("Amit", 88));
		list.add(new Student("Ram", 65));
		list.add(new Student("Krishna", 55));
		list.add(new Student("Atul", 36));
		list.add(new Student("Mohan", 33));
		
		
		
		Function<Student, String> f = s -> s.getMarks() >= 85 ? "A[DISTINCTION]" : s.getMarks() >= 65 ? "B[FIRST]" : 
			s.getMarks() >= 55 ? "C[SECOND]" : s.getMarks() >= 35 ? "D[THIRD]" : "E[FAILED]";
			
		for(Student s : list)	
			System.out.println(f.apply(s));
			
		
		
		
		/*System.out.println(Math.random());
		int d = (int)(Math.random()*10);
		System.out.println(d);
		
		Predicate<Integer> p1 = i-> i%2 == 0;
		Predicate<Integer> p2 = i-> i > 10;
		
		Integer[] i = {0,1,22,33,14,13,66,90,5,70};
		for(Integer i1 : i)
		{
			if(p1.negate().and(p2).test(i1))
				System.out.println(i1);
			
		}
		
		
		
		Thread t = new Thread( ()-> {
			for (int i1 = 0; i1 < 10; i1++) {
				System.out.println("Child Thread "+ i1);
		}});
		t.start();
		for (int i2 = 0; i2 < 10; i2++) {
			System.out.println("Main Thread "+ i2);
		}*/
		

	}
	
	

}
