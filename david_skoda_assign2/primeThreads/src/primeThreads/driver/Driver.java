
package primeThreads.driver;

//Import relevant classes here
import primeThreads.util.FileProcessor;
import primeThreads.store.StdoutDisplayInterface;
import primeThreads.store.Results;

public class Driver{

	public static void main(String args[]) {

		//Command line argument verification
		if(args.length != 4){
			throw new IllegalArgumentException("PrimeThreads requires three"
	+	" arguments to be passed in at runtime.");
		};
		String fileName = args[0];
		int numThreads = 0, debugLevel = 0;
		//Verify number of threads argument
		try{
			numThreads = Integer.valueOf(args[1]);
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
			System.out.println("Exception caught parsing argument 2: Number of Threads.");
			System.out.println("Stack Trace: " );
			throw new IllegalArgumentException("Argument 2 must be a string " +
			"that can be parsed into an int, and between 1-5, inclusive.");
		}finally{
			if(numThreads < 1 || numThreads > 5){
				System.out.println("Number of threads argument passed not in valid range.");
				throw new IllegalArgumentException("Argument 2 must be a string " +
				"that can be parsed into an int, and between 1-5, inclusive.");
			}	
		}

		//Verify debug level argument
		try{
			debugLevel = Integer.valueOf(args[2]);
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
			System.out.println("Exception caught parsing argument 3: Debug Level.");
			System.out.println("Stack Trace: " );
			throw new IllegalArgumentException("Argument 3 must be a string " +
			"that can be parsed into an int, and between 0-4, inclusive.");
		}finally{
			if(debugLevel < 0 || debugLevel > 4){
				System.out.println("Debug level argument passed not in valid range.");
				throw new IllegalArgumentException("Argument 3 must be a string " +
				"that can be parsed into an int, and between 0-4, inclusive.");
			}	
		}
	
		//Begin actual driver sequence
		FileProcessor fp = new FileProcessor(fileName);
		System.out.println(fp.readLineFromFile());



	} // end main(...)

} // end public class Driver

