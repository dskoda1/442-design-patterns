package primeThreads.store;

import primeThreads.util.Logger;
import java.util.Vector;
import java.util.Iterator;

public class Results implements StdoutDisplayInterface, StoreDataI {

	// appropriate data structure as private data member
	private Vector store;

	// appropriate method to save prime number to the data structure
	public void insert(Object obj){
		//Cast object to int for this implementation
		int val = (int) obj;
		Logger.writeMessage("Value being inserted into store: " + val,
				Logger.DebugLevel.ADDVAL);	
		this.store.addElement(val);
	}
	public Results(){
		super();
		Logger.writeMessage("Constructor for Results Class called.", 
				Logger.DebugLevel.CONSTRUCTOR);
		this.store = new Vector<Integer>();
	}

	/**
	*	WriteSumToScreen: calls the internal sumValues helper method
	*	and prints the output to Sys.out
	*/
	public void writeSumToScreen() {
		int sum = this.sumValues();
		System.out.println("The sum of all the prime numbers is: " + sum);
	}
	/**
	*	Internal helper method that sums the data member vector
	*	and returns the result.
	*	@return an int representing the sum of vector data
	*/
	private int sumValues(){

		Iterator i = this.store.iterator();
		int sum = 0;
		while(i.hasNext()){
			sum += (int) i.next();
		}
		return sum;
	}
} 


