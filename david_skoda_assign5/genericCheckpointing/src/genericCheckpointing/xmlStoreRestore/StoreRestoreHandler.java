package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import genericCheckpointing.util.FileProcessorI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.Duo;
import genericCheckpointing.outputStrategies.OutputStrategyI;
import genericCheckpointing.outputStrategies.XMLoutputStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.lang.StringBuilder;
import java.util.Arrays;


public class StoreRestoreHandler implements InvocationHandler {

	private FileProcessorI fpi;
	private OutputStrategyI osi;
	private String fileName;

	public StoreRestoreHandler(String fileNameIn) {
		super();
		fileName = fileNameIn;
		fpi = new FileProcessor(fileNameIn, true);
	}
	
	public void closeFile(){
		fpi.close();
	}


	/**
	 * method
	 * 
	 * getClass().getName() gets the FQN
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws ClassNotFoundException, IllegalAccessException {
		//Save the class name, and the class object for quick access
		String className = args[0].getClass().getName();
		Class myFirst = Class.forName(className);
		//Break here between reading objects from file or writing to file
		if (method.toString().indexOf("write") > -1) {
			//First create the appropriate output strategy, and save it as instance member
			createOutputStrategy(args[1].toString());
			//Now generate the output string from the object
			String out = generateOutputString((SerializableObject) args[0]);
			//Now write out to file.
			fpi.writeLine(out, fileName);
		} else {
			System.out.println("in read");

		}
		return proxy;
	}

	public String generateOutputString(SerializableObject so) {
		StringBuilder sb = new StringBuilder();
		
		//get the opening tags for the object
		sb.append(osi.writeOpeningObjectTags(so.getClass().getName()));
		
		//Create a list of Field, Method duos to be send to the osi
		Field[] fields = so.getClass().getDeclaredFields();
		List<Duo<Field, Method>> fieldData = new ArrayList<Duo<Field, Method>>();
		
		//Stream through the fields, inserting into field data new Duos
		Arrays.stream(fields)
		 	  .forEach(field -> fieldData.add(
		 			  new Duo(field, getGetterMethod(field, so))));
		
		//write the data members to the sb
		sb.append(osi.writeDataMembers(so, fieldData));
		
		//get the closing tags
		sb.append(osi.writeClosingObjectTags());
		return sb.toString();
	}

	public void createOutputStrategy(String wireFormat) {
		try {
			Class os = Class.forName("genericCheckpointing.outputStrategies."
					+ wireFormat + "outputStrategy");
			osi = (OutputStrategyI) os.newInstance();
		} catch (Exception e) {
			System.err
					.println("Exception thrown instantiating OutputStrategy object.");
			e.printStackTrace();
			System.exit(1);
		}

	}
	
	public Method getGetterMethod(Field f, SerializableObject so){
		Method m = null;
		try{
			m = so.getClass().getMethod("get_" + f.getName());
		}catch(Exception e){
			System.err.println("Exception thrown while getting a method for field "
					+ f.getName() + " of object " + so.getClass().getName());
			e.printStackTrace();
			System.exit(1);
		}
		return m;
	}

}