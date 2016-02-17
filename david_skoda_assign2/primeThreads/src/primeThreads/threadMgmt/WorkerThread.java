
package primeThreads.threadMgmt;
import primeThreads.util.FileProcessor;
import primeThreads.util.IsPrime;
import primeThreads.store.StoreDataI;
import primeThreads.util.Logger;

public class WorkerThread implements Runnable  {
	private FileProcessor fp;
	private IsPrime prime;
	private StoreDataI si;

	public WorkerThread(FileProcessor fp, IsPrime prime, StoreDataI si){
		super();
		Logger.writeMessage("Constructor for WorkerThread Class called.",
				Logger.DebugLevel.CONSTRUCTOR);

		this.fp = fp;		
		this.prime = prime;
		this.si = si;
	}

	/**
	*	Run routine, reads lines from file until no more,
	*	and stores the values in the store if it passes a prime check
	*/
	public void run() {
		Logger.writeMessage("Run method of WorkerThread Class called.",
				Logger.DebugLevel.RUN);
		
		String line = null;

		while((line = fp.readLineFromFile()) != null){
			//Check if prime
			if(prime.checkIfPrime(Integer.valueOf(line))){
				//Save value
				si.insert(Integer.valueOf(line));
			}
		}	
	}
}
