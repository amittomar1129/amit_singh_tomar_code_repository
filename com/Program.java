package com;

import java.util.LinkedHashMap;
import java.util.Map;

public class Program {

	public static void main(String[] args) {
		
		System.out.println("Hello Java");
	    String input = "AMITSINGHTOMARBANGALORES";
	    LinkedHashMap<Character, Integer> map =new LinkedHashMap<Character, Integer>();
	    
	    char[] charc = input.toCharArray();    
	    for(int i = 0 ; i < charc.length; i++)
	    {
	        char c = charc[i];
	        if(map.containsKey(c))
	        {
	            map.put(c, map.get(c) + 1);
	        }
	        else {
	          map.put(c, 1);
	        }
	    }
	    
	    for(Map.Entry<Character, Integer>  m : map.entrySet()  )
	    {
	      if(m.getValue() == 1)
	        {
	          System.out.println(m.getKey());
	          break;
	        }
	      
	    }
		// TODO Auto-generated method stub

	}

}
