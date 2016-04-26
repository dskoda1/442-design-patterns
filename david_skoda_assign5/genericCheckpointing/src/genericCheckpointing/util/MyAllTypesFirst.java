package genericCheckpointing.util;


public class MyAllTypesFirst extends SerializableObject {
	
	private int myInt;
	private String myString;
	private double myDouble;
	private long myLong;
	private char myChar;
	
	
	
	public MyAllTypesFirst(){
	}
	
	public MyAllTypesFirst random(RandomGen rg){
			//Now assign all of these values
			myInt = rg.randomInt();
			myString = rg.randomString();
			myDouble = rg.randomDouble();
			myLong = rg.randomLong();
			myChar = rg.randomChar();
			return this;
	}
	
	
	/**
	 * My Int
	 */
	public int getMyInt(){
		return myInt;
	}
	
	public void setMyInt(int myIntIn){
		myInt = myIntIn;
	}
	
	/**
	 * My String
	 */
	public String getMyString(){
		return myString;
	}
	
	public void setMyString(String myStringIn){
		myString = myStringIn;
	}
	
	/**
	 * My Double
	 */
	public double getMyDouble(){
		return myDouble;
	}
	
	public void setMyDouble(double myDoubleIn){
		myDouble = myDoubleIn;
	}
	
	/**
	 * My Long
	 */
	public long getMyLong(){
		return myLong;
	}
	
	public void setMyLong(long myLongIn){
		myLong = myLongIn;
	}
	
	/**
	 * My Char
	 */
	public char getMyChar(){
		return myChar;
	}
	
	public void setMyChar(char myCharIn){
		myChar = myCharIn;
	}
	
	@Override
	public String toString(){
		
		return "MyAllTypesFirst: " + "\n" +
				"myInt: " + myInt +  "\n" +
				"myString: " + myString +  "\n" +
				"myDouble: " + myDouble +  "\n" +
				"myLong: " + myLong +  "\n" +
				"myChar: " + myChar +  "\n";
	}
	
}