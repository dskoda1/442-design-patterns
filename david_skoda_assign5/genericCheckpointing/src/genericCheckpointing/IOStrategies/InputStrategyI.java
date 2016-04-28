package genericCheckpointing.IOStrategies;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import genericCheckpointing.util.Duo;


import genericCheckpointing.util.FileProcessorI;
import genericCheckpointing.util.SerializableObject;

public interface InputStrategyI {
	
	public Class parseOpeningObjectTags(FileProcessorI fpi);
	public void parseDataMembers(SerializableObject so,
			List<Duo<Field, Method>> fieldData, FileProcessorI fpi);
	public void parseClosingObjectTags(FileProcessorI fpi);

}
