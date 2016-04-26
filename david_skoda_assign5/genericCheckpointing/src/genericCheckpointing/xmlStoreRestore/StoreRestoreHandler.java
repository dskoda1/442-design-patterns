package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import genericCheckpointing.util.FileProcessorI;
import genericCheckpointing.outputStrategies.OutputStrategyI;
import genericCheckpointing.outputStrategies.XMLoutputStrategy;


public class StoreRestoreHandler implements InvocationHandler {
	
	private FileProcessorI fp;
	
	
	public StoreRestoreHandler(){
		super();
		
	}
	
	public void openFile(FileProcessorI fpIn){
		fp = fpIn;
	}
	
	/**
	 * method 
	 * 
	 * getClass().getName() gets the FQN
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws ClassNotFoundException, IllegalAccessException{

//		OutputStrategyI os = new XMLoutputStrategy();
//		
		String className = args[0].getClass().getName();
		Class<?> myFirst = Class.forName(className);
//		System.out.println(myFirst);
		
		if(method.toString().indexOf("write") > -1){
			System.out.println("in write");
			for(Method m : myFirst.getDeclaredMethods()){
				System.out.println(m);
			}
			
			
//			for(Field f : args[0].getClass().getDeclaredFields()){
//				System.out.println(f.get(args[0]));
//			}
		}else{
			System.out.println("in read");
			
		}
		
		
		
		//System.out.println("Proxy is " + proxy);
		//System.out.println("Method name is " + method);
		//System.out.println("Args are " + args[0].getClass());
		
		return proxy;
	}
	
	
	
}