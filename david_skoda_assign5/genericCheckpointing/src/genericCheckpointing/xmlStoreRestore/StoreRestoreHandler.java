package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import genericCheckpointing.util.FileProcessorI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.Duo;
import genericCheckpointing.IOStrategies.OutputStrategyI;
import genericCheckpointing.IOStrategies.InputStrategyI;
import genericCheckpointing.IOStrategies.XMLoutputStrategy;
import genericCheckpointing.IOStrategies.XMLinputStrategy;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
import java.util.Arrays;

public class StoreRestoreHandler implements InvocationHandler {

	private FileProcessorI fpi;
	private OutputStrategyI osi;
	private InputStrategyI isi;
	private String fileName;

	/**
	 * Constructor
	 * 
	 * @param fileNameIn
	 *            to create a fileprocessor for.
	 */
	public StoreRestoreHandler(String fileNameIn) {
		super();
		fileName = fileNameIn;
		fpi = new FileProcessor(fileNameIn);
	}

	/**
	 * Close the file processor.
	 */
	public void closeFile() {
		fpi.close();
	}

	/**
	 * This method performs either a read or a write of an object. For
	 * serialization, it creates the output strategy, then generates the output
	 * string, and the writes to the file For deserialization, it creates the
	 * input strategy, then asks that strat object for an object from the file,
	 * then returns the deserialized object.
	 * 
	 * @param proxy
	 *            the dynamic proxy on which a method was called
	 * @param method
	 *            the method, either 'readObj' or 'writeObj'
	 * @param args
	 *            for 'readObj', only one argument, the wireFormat. for
	 *            'writeObj', two args, the object to serialize, and the
	 *            wireFormat.
	 * @return if writing, returns proxy, else if reading, returns the
	 *         deserialized object.
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws ClassNotFoundException, IllegalAccessException {
		
		// Break here between reading objects from file or writing to file
		if (method.toString().indexOf("write") > -1) {
			// Save the class name, and the class object for quick access
			String className = args[0].getClass().getName();
			Class myFirst = Class.forName(className);
			// First create the appropriate output strategy, and save it as
			// instance member
			createOutputStrategy(args[1].toString());
			// Now generate the output string from the object
			String out = generateOutputString((SerializableObject) args[0]);
			// Now write out to file.
			fpi.writeLine(out, fileName);
		} else {
			// Create the appropriate outputStrategy and save it as an instance
			createInputStrategy(args[0].toString());
			// Create an object from some input in the strategy
			Class objectClass = isi.parseOpeningObjectTags(fpi);
			
			//Get the fields and setter methods
			Field[] fields = objectClass.getDeclaredFields();
			List<Duo<Field, Method>> fieldData = new ArrayList<>();

			//Create the object here
			SerializableObject so = createObject(objectClass);
			
			//Stream through the fields, inserting into field data new Duos
			Arrays.stream(fields)
			.forEach(field -> fieldData.add(
					new Duo(field, getMethods(field, so, "set"))));
			
			isi.parseDataMembers(so, fieldData, fpi);
			isi.parseClosingObjectTags(fpi);
			
			return so;
		}
		return proxy;
	}

	/**
	 * 
	 * @param so
	 * @return
	 */
	private String generateOutputString(SerializableObject so) {
		StringBuilder sb = new StringBuilder();

		//get the opening tags for the object
		sb.append(osi.writeOpeningObjectTags(so.getClass().getName()));

		//Create a list of Field, Method duos to be send to the osi
		Field[] fields = so.getClass().getDeclaredFields();
		List<Duo<Field, Method>> fieldData = new ArrayList<>();

		//Stream through the fields, inserting into field data new Duos
		Arrays.stream(fields)
		.forEach(field -> fieldData.add(
				new Duo(field, getMethods(field, so, "get"))));

		//write the data members to the sb
		sb.append(osi.writeDataMembers(so, fieldData));

		//get the closing tags
		sb.append(osi.writeClosingObjectTags());
		return sb.toString();
	}

	/**
	 * 
	 * @param f
	 * @param so
	 * @return
	 */
	public Method getMethods(Field f, SerializableObject so, String type) {
		Method m = null;
		try {
			if(type.equalsIgnoreCase("set")){
				m = so.getClass().getMethod(type + "_" + f.getName(), f.getType());
			}else{
				m = so.getClass().getMethod(type + "_" + f.getName());
			}
		} catch (Exception e) {
			System.err
			.println("Exception thrown while getting a method for field "
					+ f.getName()
					+ " of object "
					+ so.getClass().getName());
			e.printStackTrace();
			System.exit(1);
		}
		return m;
	}
	
	
	public SerializableObject createObject(Class c){
		SerializableObject so = null;
		try{
			so = (SerializableObject) c.newInstance();
		}catch(Exception e){
			System.err.println("Exception creating object from class " + c);
			e.printStackTrace();
			System.exit(1);
		}
		return so;
	}
	/**
	 * 
	 * @param wireFormat
	 */
	public void createOutputStrategy(String wireFormat) {
		try {
			Class os = Class.forName("genericCheckpointing.IOStrategies."
					+ wireFormat + "outputStrategy");
			osi = (OutputStrategyI) os.newInstance();
		} catch (Exception e) {
			System.err
			.println("Exception thrown instantiating OutputStrategy object.");
			e.printStackTrace();
			System.exit(1);
		}

	}
	/**
	 * 
	 * @param wireFormat
	 */
	public void createInputStrategy(String wireFormat) {
		try {
			Class os = Class.forName("genericCheckpointing.IOStrategies."
					+ wireFormat + "inputStrategy");
			isi = (InputStrategyI) os.newInstance();
		} catch (Exception e) {
			System.err
			.println("Exception thrown instantiating InputStrategy object.");
			e.printStackTrace();
			System.exit(1);
		}

	}

	

}