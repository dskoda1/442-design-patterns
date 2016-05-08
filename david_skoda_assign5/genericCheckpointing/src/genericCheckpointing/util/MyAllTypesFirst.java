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
	public int get_myInt(){
		return myInt;
	}
	
	public void set_myInt(int myIntIn){
		myInt = myIntIn;
	}
	
	/**
	 * My String
	 */
	public String get_myString(){
		return myString;
	}
	
	public void set_myString(String myStringIn){
		myString = myStringIn;
	}
	
	/**
	 * My Double
	 */
	public double get_myDouble(){
		return myDouble;
	}
	
	public void set_myDouble(double myDoubleIn){
		myDouble = myDoubleIn;
	}
	
	/**
	 * My Long
	 */
	public long get_myLong(){
		return myLong;
	}
	
	public void set_myLong(long myLongIn){
		myLong = myLongIn;
	}
	
	/**
	 * My Char
	 */
	public char get_myChar(){
		return myChar;
	}
	
	public void set_myChar(char myCharIn){
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
	
	@Override
	public boolean equals(Object obj){
		MyAllTypesFirst lhs = (MyAllTypesFirst) obj;
		if(	myInt == lhs.myInt &&
			myString.equals(lhs.myString) &&
			myDouble == lhs.myDouble &&
			myLong == lhs.myLong &&
			myChar == lhs.myChar){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		return (new Integer(myInt).hashCode() + 
				myString.hashCode() +
				new Double(myDouble).hashCode() + 
				new Long(myLong).hashCode() + 
				Character.getNumericValue(myChar));
	}
	
}