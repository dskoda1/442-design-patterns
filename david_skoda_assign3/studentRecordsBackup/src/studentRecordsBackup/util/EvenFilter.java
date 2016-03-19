package studentRecordsBackup.util;

import studentRecordsBackup.util.OddEvenFilterI;
import java.lang.IllegalArgumentException;
import studentRecordsBackup.util.Logger;

public class EvenFilter implements OddEvenFilterI{

	public EvenFilter(){
		Logger.writeMessage("Constructor for EvenFilter Class called.",
				Logger.DebugLevel.CONSTRUCTOR);
	}

  public boolean check(Object obj){
    if(obj instanceof Integer){
      return ((int) obj % 2) == 0 ? true : false;
    }
    else{
      throw new IllegalArgumentException("Argument " +
        obj.toString() + " passed into EvenFilter::Check() is "
      + "invalid.");
    }
  }
}

