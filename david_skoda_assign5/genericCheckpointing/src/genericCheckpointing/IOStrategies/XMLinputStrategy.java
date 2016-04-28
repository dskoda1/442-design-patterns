package genericCheckpointing.IOStrategies;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import java.lang.Integer;

import genericCheckpointing.util.FileProcessorI;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.Duo;

public class XMLinputStrategy implements InputStrategyI {
	
	private static HashMap<String, Method> typeMapper;

	public XMLinputStrategy() {
		// TODO Auto-generated constructor stub
	}

	public Class parseOpeningObjectTags(FileProcessorI fpi) {
		// Read lines from the fpi for an object
		// This is the <DPSerialization> line
		String line = fpi.readLine();
		// this is the <complexType
		// xsi:type="genericCheckpointing.util.MyAllTypesX"> line
		line = fpi.readLine();
		Class c = getObjectClass(line);
		return c;
	}

	public void parseDataMembers(SerializableObject so,
			List<Duo<Field, Method>> fieldData, FileProcessorI fpi){

		List<String> lines = new ArrayList<>();
		String temp = "";
		while(!(temp = fpi.readLine()).equalsIgnoreCase("\t</complexType>")){
			lines.add(temp);
		}
		//Now we go through each line and set the data member on the 
		//object
		lines.stream()
			.forEach(line -> {
				//Try and find the first field data that matches
				Optional<Duo<Field, Method>> opt = fieldData.stream()
					.filter(d -> {
						Field f = (Field)d.l;
						return (line.indexOf(f.getName()) > -1);
					})
					.findFirst();
				if(opt.isPresent()){
					Duo<Field, Method> duo = opt.get();
					assignDataMember(so, duo, line);
				}else{
					System.err.println("Unable to find matching Field/Method duo for line: \n"
							+ line);
					System.exit(1);
				}
			});
	}
	
	public void parseClosingObjectTags(FileProcessorI fpi) {
		// Just read the last line of the object
		fpi.readLine();
	}
	
	public void assignDataMember(SerializableObject so, Duo<Field, Method> duo, String line){
		
		Field f = (Field)duo.l;
		Method m = (Method)duo.r;
		
		//Get the index of the value
		int valStartIndex = line.indexOf(">") + 1;
		int valEndIndex = line.indexOf("<", valStartIndex);

		Class dataMemberType = f.getType();
		String val = line.substring(valStartIndex, valEndIndex);
		Object obj = parseValue(val, dataMemberType);
		
		
		try{
			m.invoke(so, obj);
		}catch(IllegalAccessException iae){
			System.err.println("IllegalAccessException thrown while setting "
					+ f.getName() + " with method " + m.getName() + " on object "
					+ so.toString());
			iae.printStackTrace();
			System.exit(1);
		}catch(Exception e){
			System.err.println("Exception thrown while setting "
					+ f.getName() + " with method " + m.getName() + " on object "
					+ so.toString());
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private Object parseValue(String val, Class c){
		//Method m = c.getDeclaredMethod("parse" + c.get, parameterTypes)
		Object ret = null;
		String className = c.getName();
		//Only different for string
		if(className.indexOf("String") > -1){
			ret = val;
		}else if(className.equalsIgnoreCase("char")){
			ret = val.charAt(0);
		}else{
			//Capitalize the first letter of the type
			//className = Character.toUpperCase(className.charAt(0)) + className.substring(1);
			try{
				Method m = typeMapper.get(className);
				
				if(m != null){
					ret = m.invoke(null, val);
				}else{
					System.err.println("M is null");
					System.err.println(className);
				}
				
			}catch(IllegalAccessException ias){
				System.err.println("Illegal Access Exception thrown attempting to get " +
						"string parse method for class: " + className);
				ias.printStackTrace();
				System.exit(1);
			}catch(Exception e){
				System.err.println("Exception thrown attempting to get " +
						"string parse method for class: " + className);
				e.printStackTrace();
				System.exit(1);
			}
		}
		return ret;
		
		
	}

	private Class getObjectClass(String objectLine) {
		int index = objectLine.indexOf("xsi:type=\"") + 10;
		int endIndex = objectLine.indexOf("\"", index);
		String fqn = objectLine.substring(index, endIndex);
		Class os = null;
		try {
			os = Class.forName(fqn);
		} catch (ClassNotFoundException cnfe) {
			System.err
			.println("ClassNotFoundException caught getting class for name "
					+ fqn);
			cnfe.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			System.err
			.println("Exception caught getting class for name " + fqn);
			e.printStackTrace();
			System.exit(1);
		}
		return os;
	}
	
	static {
        typeMapper = new HashMap<>();
        try{
        Method m = Integer.class.getMethod("parseInt", String.class);
        typeMapper.put("int", m);
        
        m = Float.class.getMethod("parseFloat", String.class);
        typeMapper.put("float", m);
        
        m = Short.class.getMethod("parseShort", String.class);
        typeMapper.put("short", m);
        
        m = Long.class.getMethod("parseLong", String.class);
        typeMapper.put("long", m);
        
        m = Double.class.getMethod("parseDouble", String.class);
        typeMapper.put("double", m);
        
        }catch(Exception e){
        	System.err.println("Exception instantiating type mapper for parsing numeric values.");
        	e.printStackTrace();
        	System.exit(1);
        }
    }

}
