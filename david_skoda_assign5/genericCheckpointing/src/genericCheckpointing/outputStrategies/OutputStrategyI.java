package genericCheckpointing.outputStrategies;

import genericCheckpointing.util.SerializableObject;
import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import genericCheckpointing.util.Duo;


public interface OutputStrategyI {
	
	public String writeOpeningObjectTags(String fqn);
	public String writeClosingObjectTags();
	public String writeDataMembers(SerializableObject so,
			List<Duo<Field, Method>> fieldData);

}
