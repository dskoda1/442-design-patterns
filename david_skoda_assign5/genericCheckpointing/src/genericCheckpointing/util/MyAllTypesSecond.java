package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject {
	
	private int myIntS;
	private String myStringS;
	private float myFloatS;
	private short myShortS;
	private char myCharS;
	
	public MyAllTypesSecond(){
	}
	
	
	public MyAllTypesSecond random(RandomGen rg){
			//Now assign all of these values
			myIntS = rg.randomInt();
			myStringS = rg.randomString();
			myFloatS = rg.randomFloat();
			myShortS = rg.randomShort();
			myCharS = rg.randomChar();
			return this;
	}
	
	/**
	 * My IntS
	 */
	public int get_myIntS(){
		return myIntS;
	}
	
	public void set_myIntS(int myIntSIn){
		myIntS = myIntSIn;
	}
	
	/**
	 * My String
	 */
	public String get_myStringS(){
		return myStringS;
	}
	
	public void set_myStringS(String myStringSIn){
		myStringS = myStringSIn;
	}
	
	/**
	 * My FloatS
	 */
	public float get_myFloatS(){
		return myFloatS;
	}
	
	public void set_myFloatS(float myFloatSIn){
		myFloatS = myFloatSIn;
	}
	
	/**
	 * My ShortS
	 */
	public long get_myShortS(){
		return myShortS;
	}
	
	public void set_myShortS(short myShortSIn){
		myShortS = myShortSIn;
	}
	
	/**
	 * My Char
	 */
	public char get_myCharS(){
		return myCharS;
	}
	
	public void set_myCharS(char myCharSIn){
		myCharS = myCharSIn;
	}
	
	@Override
	public String toString(){
		
		return "MyAllTypesSecond: " + "\n" +
				"myIntS: " + myIntS +  "\n" +
				"myStringS: " + myStringS +  "\n" +
				"myFloatS: " + myFloatS +  "\n" +
				"myShortS: " + myShortS +  "\n" +
				"myCharS: " + myCharS +  "\n";
	}
	@Override
	public boolean equals(Object obj){
		
		MyAllTypesSecond lhs = (MyAllTypesSecond) obj;
		if(	myIntS == lhs.myIntS &&
			myStringS.equals(lhs.myStringS) &&
			myFloatS == lhs.myFloatS &&
			myShortS == lhs.myShortS &&
			myCharS == lhs.myCharS){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		return (new Integer(myIntS).hashCode() + 
				myStringS.hashCode() +
				new Float(myFloatS).hashCode() + 
				new Short(myShortS).hashCode() + 
				Character.getNumericValue(myCharS));
	}
}