package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

// import the other types used in this file

public class Driver {

	public static void main(String[] args) {

		validateArgLength(args.length, 1);

		// FIXME: read the value of checkpointFile from the command line
		ProxyCreator pc = new ProxyCreator();

		// create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler
		StoreRestoreHandler srh = new StoreRestoreHandler();
		srh.openFile(new FileProcessor(args[0]));

		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] {
				StoreI.class, RestoreI.class }, srh);
		// new StoreRestoreHandler() );

		// FIXME: invoke a method on the handler instance to set the file name
		// for checkpointFile and open the file
		// Done above

		MyAllTypesFirst myFirst;
		MyAllTypesSecond mySecond;

		 //create a vector to store the objects being serialized
//		 for (int i=0; i<NUM_OF_OBJECTS; i++) {
//		 // FIXME: create these object instances correctly.
//		 myFirst = new MyAllTypesFirst(...);
//		 mySecond = new MyAllTypesSecond(..);
//		
//		 // FIXME: store myFirst and mySecond in the vector
//		 ((StoreI) cpointRef).writeObj(myFirst, "XML");
//		 ((StoreI) cpointRef).writeObj(mySecond, "XML");
//		
//		 }

		// SerializableObject myRecordRet;

		// create a vector to store the returned ojects
		// for (int j=0; j<2*NUM_OF_OBJECTS; j++) {
		//
		// myRecordRet = ((RestoreI) cpointRef).readObj("XML");
		// // FIXME: store myRecordRet in the vector
		// }

		// FIXME: invoke a method on the handler to close the file (if it hasn't
		// already been closed

		// FIXME: compare and confirm that the serialized and deserialzed
		// objects are equal

	}

	private static void validateArgLength(int argsLength, int expectedLength)
			throws IllegalArgumentException {
		if (argsLength > expectedLength) {
			throw new IllegalArgumentException(
					"GenericCheckpointing requires one"
							+ " argument to be passed in at runtime.\n"
							+ "More than one were passed into the execution of this program.\n"
							+ "This could be a result of extra default args set in your ant buildfile.\n"
							+ "Ant usage: \n\t"
							+ "ant -buildfile src/build.xml run -Darg0=<input file> \n");
			// +
			// "-Darg1=<output file> -Darg2=<Num Iterations> -Darg3=<Search String>\n");
		}
		if (argsLength < expectedLength) {
			throw new IllegalArgumentException(
					"GenericCheckpointing requires one"
							+ " arguments to be passed in at runtime.\n"
							+ "You have passed in less than one argument. Please check your usage.\n"
							+ "If you are passing one arguments and using ant, ensure that your buildfile "
							+ "has 1 argument set in the run command. Otherwise they will silently not be passed in.\n\n"
							+ "Ant usage: \n\t"
							+ "ant -buildfile src/build.xml run -Darg0=<input file> \n");
			// +
			// "-Darg1=<output file> -Darg2=<Num Iterations> -Darg3=<Search String>\n");
		}
	}

}
