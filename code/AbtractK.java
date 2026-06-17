package code;

import javax.sound.midi.VoiceStatus;

public class AbtractK {
	
	public  String programName;
	protected  String programSecret;
	String programKey;
	private  int programId;
	
	public static String sprogramName;
	protected static String sprogramSecret;
	static String sprogramKey;
	private static int sprogramId;

	public void dot()
	{
		System.out.println("s");
		
		class Local {
			
		}
	}
	
	public class Amit {
		private int id;
		public int nm;
	}
	
	static class AmitS {
		private int idS;
		public int nmS;
	}
	
	public static void main(String... args)
	{
		AbtractK.Amit p = new AbtractK().new Amit();
		System.out.println(p.id);
		
		
		AbtractK.AmitS cca=new AbtractK.AmitS();
		
	}
	
	
	
}
