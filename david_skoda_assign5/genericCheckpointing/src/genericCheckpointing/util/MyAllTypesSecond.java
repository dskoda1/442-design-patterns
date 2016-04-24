package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject {
	
	private int myIntS;
	private String myStringS;
	private float myFloatS;
	private short myShortS;
	private char myCharS;
	
	public MyAllTypesSecond(){
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
}