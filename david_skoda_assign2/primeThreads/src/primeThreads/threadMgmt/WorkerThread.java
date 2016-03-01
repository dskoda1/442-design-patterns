
package primeThreads.threadMgmt;
import primeThreads.util.FileProcessor;
import primeThreads.util.IsPrime;
import primeThreads.store.StoreDataI;
import primeThreads.util.Logger;

public class WorkerThread implements Runnable  {
	private FileProcessor fp;
	private IsPrime prime;
	private StoreDataI si;

	public WorkerThread(FileProcessor fpIn, IsPrime primeIn, StoreDataI siIn){
		super();
		Logger.writeMessage("Constructor for WorkerThread Class called.",
				Logger.DebugLevel.CONSTRUCTOR);

		this.fp = fpIn;		
		this.prime = primeIn;
		this.si = siIn;
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

	@Override
	public String toString(){

    return "WorkerThread toString: " +
    "\nFileProcessor: " + this.fp.toString() +
    "\nIsPrime: " + this.prime.toString() +
    "\nStoreDataI: " + this.si.toString() +
    "\nEnd WorkerThread toString\n";

  }

}
