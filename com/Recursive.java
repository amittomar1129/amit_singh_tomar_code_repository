package com;

public class Recursive {
	int count;
	
	public String amit()
	{
		System.out.println("Hello Amit" + count);
		count++;
		
		if(count < 5)
		{
			System.out.println(amit() + " MM " +count );	
		}
		
		System.out.println("Bye Amit" + count);
		
		return "ret";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Recursive recursive = new Recursive();
		System.out.println(recursive.amit());
		
		
		
		
	}

}
