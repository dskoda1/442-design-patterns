package studentRecordsBackup.bst;
import java.lang.IllegalArgumentException;

public interface ObserverI {
  public void update(Object obj) throws IllegalArgumentException;
}
