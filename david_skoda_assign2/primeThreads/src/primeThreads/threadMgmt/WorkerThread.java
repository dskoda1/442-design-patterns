
package primeThreads.threadMgmt;
import primeThreads.util.FileProcessor;
import primeThreads.util.IsPrime;
import primeThreads.store.Results;
import primeThreads.util.Logger;
public class WorkerThread implements Runnable  {
	private FileProcessor fp;
	private IsPrime prime;
	private Results res;

	public WorkerThread(FileProcessor fp, IsPrime prime, Results res){
		super();
		Logger.writeMessage("Constructor for WorkerThread Class called.",
				Logger.DebugLevel.CONSTRUCTOR);

		this.fp = fp;		
		this.prime = prime;
		this.res = res;
	}

	public void run() {
		Logger.writeMessage("Run method of WorkerThread Class called.",
				Logger.DebugLevel.RUN);

		// ...
	}


}
