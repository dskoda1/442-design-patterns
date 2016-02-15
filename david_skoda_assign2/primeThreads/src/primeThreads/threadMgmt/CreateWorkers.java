
package primeThreads.threadMgmt;

import primeThreads.util.FileProcessor;
import primeThreads.util.IsPrime;
import primeThreads.store.Results;
import primeThreads.util.Logger;

import java.util.ArrayList;
public class CreateWorkers  {

	private FileProcessor fp;
	private IsPrime prime;
	private Results res;

	public CreateWorkers(FileProcessor fp, IsPrime prime, Results res){
		super();
		Logger.writeMessage("Constructor for CreateWorkers Class called.",
				Logger.DebugLevel.CONSTRUCTOR);

		this.fp = fp;		
		this.prime = prime;
		this.res = res;
	}

	/**
	*	Creates the worker thread instances, starts them, and joins
	* them together.
	*	param: numThreads the number of worker threads to start
	*/
	public void startWorkers(int numThreads){

		ArrayList workers = new ArrayList<WorkerThread>();

		for(int i = 0; i < numThreads; i++){
			WorkerThread worker = new WorkerThread(this.fp, this.prime, this.res);
			Thread t = new Thread(worker);
			workers.add(worker);
		}

		

	}


}
