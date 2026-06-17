package code;

import code.Program;

public class Data {
	
	
	public String getBook(String str) throws Exception
	{
		try{
			System.out.println("Amit");
			//throw new Exception("EX");
			return "TRY";
		}
		catch(Exception e)
		{
			System.out.println("catch");
			//throw new Exception("EXEXXS");
			return "CATCH";
		} 
		finally 
		{
			System.out.println("finally");
			//throw new Exception("EXEX catch");
			return "FINALLY";
		}
		
		//return "SpringBoot";
	}
	

	public static void main(String[] args) {
		
		Data program = new Data();
		String k;
		try {
			k = program.getBook("");
			System.out.println(k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	

}
