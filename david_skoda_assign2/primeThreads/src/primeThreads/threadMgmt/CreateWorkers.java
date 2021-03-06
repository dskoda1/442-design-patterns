
package primeThreads.threadMgmt;

import primeThreads.util.FileProcessor;
import primeThreads.util.IsPrime;
import primeThreads.store.StoreDataI;
import primeThreads.util.Logger;
import java.util.ArrayList;
public class CreateWorkers  {

	private FileProcessor fp;
	private IsPrime prime;
	private StoreDataI si;

	public CreateWorkers(FileProcessor fpIn, IsPrime primeIn, StoreDataI siIn){
		super();
		Logger.writeMessage("Constructor for CreateWorkers Class called.",
				Logger.DebugLevel.CONSTRUCTOR);

		this.fp = fpIn;		
		this.prime = primeIn;
		this.si = siIn;
	}

	/**
	*	Creates the worker thread instances, starts them, and joins
	* them together.
	*	@param numThreads the number of worker threads to start
	*/
	public void startWorkers(int numThreads){

		ArrayList<Thread> workers = new ArrayList<Thread>();

		for(int i = 0; i < numThreads; i++){
			WorkerThread worker = new WorkerThread(this.fp, this.prime, this.si);
			Thread t = new Thread(worker);
			workers.add(t);
			t.start();
		}
		try{
		for(Thread t : workers){

			t.join();
		}
		}catch(InterruptedException ie){
			System.err.println("Caught Interrupted Exception upon attempting " +
			"joins of existing threads.");
			ie.printStackTrace();
		}

	}
	@Override
	public String toString(){

		return "CreateWorkers toString: " + 
		"\nFileProcessor: " + this.fp.toString() +
		"\nIsPrime: " + this.prime.toString() + 
		"\nStoreDataI: " + this.si.toString() + 
		"\nEnd CreateWorkers toString\n";

	}
}
