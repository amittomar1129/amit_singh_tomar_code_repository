package code;

public class Program1 {

	public static void foo(int... o) {
		System.out.println("Object");
	}
	public static void foo(Integer o) {
		System.out.println("Long");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		foo(10);
	}
}
