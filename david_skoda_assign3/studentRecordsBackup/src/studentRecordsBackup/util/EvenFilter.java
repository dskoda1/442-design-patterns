package studentRecordsBackup.util;

import studentRecordsBackup.util.OddEvenFilterI;
import java.lang.IllegalArgumentException;

public class EvenFilter implements OddEvenFilterI{

  public boolean check(Object obj){
    if(obj instanceof Integer){
      return ((int) obj % 2) == 0 ? true : false;
    }
    else{
      throw new IllegalArgumentException("Argument " +
        obj.toString() + " passed into OddFilter::Check() is "
      + "invalid.");
    }
  }
}

