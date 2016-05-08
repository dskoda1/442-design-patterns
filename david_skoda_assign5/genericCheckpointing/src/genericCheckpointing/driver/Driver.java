package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.RandomGen;

import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

import java.util.Vector;

// import the other types used in this file

public class Driver {

	public static void main(String[] args) {

		boolean printProgress = false;
		
		//Do some cmd line validation
		validateArgLength(args.length, 3);
		String mode = args[0];
		int NUM_OF_OBJECTS = validateNumParam(args[1], 0, Integer.MAX_VALUE,
				"Exception caught parsing argument 2: NUM_OF_OBJECTS",
				"Argument 2 must be a parseabble string between 0 and Int.max");
		
		if(!mode.equalsIgnoreCase("serdeser") && !mode.equalsIgnoreCase("deser")){
			throw new IllegalArgumentException("Unrecognized option for argument 1: Mode.\n"
					+ "Options are either : 'serdeser', or 'deser'.");
			//System.exit(1);
		}

			
		ProxyCreator pc = new ProxyCreator();
		StoreRestoreHandler srh = new StoreRestoreHandler();
		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] {
				StoreI.class, RestoreI.class }, srh);

		
		//Create the vectors for comparison
		Vector<SerializableObject> preSer = new Vector<>();
		Vector<SerializableObject> postSer = new Vector<>();
		

		//First create random objects and then serialize them
		//This is where the object creation and output to file happens
		if(mode.equalsIgnoreCase("serdeser"))
		{	//Create the objects needed
			
			MyAllTypesFirst myFirst;
			MyAllTypesSecond mySecond;
			RandomGen rg = new RandomGen();
			srh.openFileForWriting(args[2]);
			//time the deserialization
			if(printProgress)System.out.println("Serializing..");
			
			long startTime = System.nanoTime();
			int j = 10;
			// create a vector to store the objects being serialized
			for (int i = 0; i < NUM_OF_OBJECTS; i++) {
				//Generate random objects
				myFirst = (new MyAllTypesFirst()).random(rg);
				mySecond = (new MyAllTypesSecond()).random(rg);
				
				//Save into array
				preSer.add(myFirst);
				preSer.add(mySecond);

				//Serialize out to file
				((StoreI) cpointRef).writeObj(myFirst, "XML");
				((StoreI) cpointRef).writeObj(mySecond, "XML");
				
				//For pretty printing progress
				if(i % (NUM_OF_OBJECTS / 10) == 0 ){
					if(printProgress)System.out.println("\t" + j + "%");
					j += 10;
				}
			}
			//Calculate the time and pretty print it
			long finishTime = System.nanoTime();
			long totalTime = (finishTime - startTime);
			if(printProgress)System.out
			.println("The total time it took to serialize "+NUM_OF_OBJECTS * 2+
					" randomized objects is: "
					+ totalTime/1000000 + " ms.");
			
			//Close the file handle
			srh.closeFile();
		}
		//Now we want to read from the file in both modes. Save into a vector
		if(mode.equalsIgnoreCase("deser") || mode.equalsIgnoreCase("serdeser")){

			//Open the file up for reading
			srh.openFileForReading(args[2]);
			
			//Prety print and prep vars for timing
			if(printProgress)System.out.println("Deserializing..");
			long startTime = System.nanoTime();
			int j = 10;
			
			for (int i=0; i<2*NUM_OF_OBJECTS; i++) {
				
				//Read an object from the file and add to vector
				postSer.add(((RestoreI) cpointRef).readObj("XML"));
				
				//Print progress
				if(i % (NUM_OF_OBJECTS / 5) == 0 ){
					if(printProgress)System.out.println("\t" + j + "%");
					j += 10;
				}
			}
			//Calc timing for progress
			long finishTime = System.nanoTime();
			long totalTime = (finishTime - startTime);
			if(printProgress)System.out
			.println("The total time it took to deserialize "+NUM_OF_OBJECTS * 2+
					" objects is: "
					+ totalTime/1000000 + " ms.");
			
			//Close the file out
			srh.closeFile();
		}
		
		//Only for ser and deser, compare objects
		if(mode.equalsIgnoreCase("serdeser")){
			int wrongCount = 0;
			//compare the vectors here
			for(int i = 0; i < preSer.size(); ++i){
				if(!preSer.elementAt(i).equals(postSer.elementAt(i))){
					wrongCount++;
				}
			}
			System.out.println(wrongCount + " mismatched objects.");
		}
		
		if(mode.equalsIgnoreCase("deser")){
			//Print out each of the objects in the vector in this mode.
			postSer.stream()
				.forEach(obj -> System.out.println(obj));
			
			
		}
		
		
		
	}
	private static void validateArgLength(int argsLength, int expectedLength)
			throws IllegalArgumentException {
		if (argsLength > expectedLength) {
			throw new IllegalArgumentException(
					"GenericCheckpointing requires three"
							+ " argument to be passed in at runtime.\n"
							+ "More than three were passed into the execution of this program.\n"
							+ "This could be a result of extra default args set in your ant buildfile.\n"
							+ "Ant usage: \n\t"
							+ "ant -buildfile src/build.xml run -Darg0=<mode> -Darg1=<NumOfObjects> -Darg2=<input file> \n");
		}
		if (argsLength < expectedLength) {
			throw new IllegalArgumentException(
					"GenericCheckpointing requires three"
							+ " arguments to be passed in at runtime.\n"
							+ "You have passed in less than three argument. Please check your usage.\n"
							+ "If you are passing three arguments and using ant, ensure that your buildfile "
							+ "has 3 argument's set in the run command. Otherwise they will silently not be passed in.\n\n"
							+ "Ant usage: \n\t"
							+ "ant -buildfile src/build.xml run -Darg0=<mode> -Darg1=<NumOfObjects> -Darg2=<input file> \n");
		}
	}

	private static int validateNumParam(String param, int min, int max,
			String exCaught, String exMessage) throws IllegalArgumentException {
		int retVal = 0;
		try {
			retVal = Integer.valueOf(param);
		} catch (NumberFormatException nfe) {
			System.err.println(exCaught);
			System.err.println("Stack Trace: ");
			nfe.printStackTrace();
			throw new IllegalArgumentException(exMessage);
		} finally {
			if (retVal < min || retVal > max) {
				System.err.println("Argument passed not in valid range.");
				throw new IllegalArgumentException(exMessage);
			}
		}
		return retVal;
	}

}
