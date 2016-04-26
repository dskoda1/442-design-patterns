package genericCheckpointing.outputStrategies;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.Duo;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;


public class XMLoutputStrategy implements OutputStrategyI {

	public XMLoutputStrategy() {
	}
	
	public String writeOpeningObjectTags(String fqn){
		StringBuilder sb = new StringBuilder();
		sb.append("<DPSerialization>\n");
		sb.append("\t<complexType xsi:type=\"");
		sb.append(fqn);
		sb.append("\">\n");
		return sb.toString();
		
	}
	public String writeClosingObjectTags(){
		StringBuilder sb = new StringBuilder();
		sb.append("\t</complexType>\n");
		sb.append("</DPSerialization>");
		return sb.toString();
		
	}
	
	public String writeDataMembers(SerializableObject so,
			List<Duo<Field, Method>> fieldData){
		StringBuilder sb = new StringBuilder();
		
		fieldData.stream()
			.forEach(d -> {
				Field f = (Field)d.l;
				Method m = (Method)d.r;
				String s = writeDataMember(f.getName(), f.getType().getName(), get(m, so));
				sb.append(s);
			});
		
		return sb.toString();

	}
	
	private String get(Method m, SerializableObject so){
		String val = "";
		try{
			val = m.invoke(so).toString();
		}catch(Exception e){
			System.err.println("Exception caught while invoking getter method "
					+ m.getName() + " on object " + so);
			e.printStackTrace();
			System.exit(1);
		}
		return val;
	}
	
	private String writeDataMember(String name, String type, String value){
		
		return "\t\t<"+"name"+" xsi:type=\"xsd:"+type+"\">"+value+"</"+name+">\n";
	}
}
