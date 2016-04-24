package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import genericCheckpointing.util.FileProcessorI;


public class StoreRestoreHandler implements InvocationHandler {
	
	private FileProcessorI fp;
	
	
	public StoreRestoreHandler(){
		super();
		
	}
	
	public void openFile(FileProcessorI fpIn){
		fp = fpIn;
	}
	
	
	public Object invoke(Object proxy, Method method, Object[] args){
		
		
		return proxy;
	}
	
	
	
}