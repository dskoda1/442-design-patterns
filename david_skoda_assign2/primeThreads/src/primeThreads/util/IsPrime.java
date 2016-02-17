
package primeThreads.util;

public class IsPrime {


	public IsPrime(){
		super();
		Logger.writeMessage("Constructor for IsPrime Class called.",
        Logger.DebugLevel.CONSTRUCTOR);
	}

	/**
	*	Takes in a number and checks if its prime.
	*	For this assignment only concerned if its even or odd.
	*	@param val Value to check for primeness(odd for this assignment)
	*	@return Boolean value if val was prime(odd)
	*/
	public boolean checkIfPrime(int val){
		return (val % 2 == 0) ? false : true;
	}	

}
