package studentRecordsBackup.util;

import studentRecordsBackup.util.OddEvenFilterI;
import java.lang.IllegalArgumentException;
import studentRecordsBackup.util.Logger;
public class OddFilter implements OddEvenFilterI{

  public OddFilter(){
    Logger.writeMessage("Constructor for OddFilter Class called.",
        Logger.DebugLevel.CONSTRUCTOR);
  }

  public boolean check(Object obj) throws IllegalArgumentException{
    if(obj instanceof Integer){
      return ((int) obj % 2) == 1 ? true : false;
    }
    else{
      throw new IllegalArgumentException("Argument " + 
          obj.toString() + " passed into OddFilter::Check() is "
          + "invalid."); 
    }
  }
}
