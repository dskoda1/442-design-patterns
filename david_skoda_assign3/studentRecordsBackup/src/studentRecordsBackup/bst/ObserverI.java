package studentRecordsBackup.bst;
import java.lang.IllegalArgumentException;
import java.util.function.Predicate;

public interface ObserverI {
  public void update(Object obj) throws IllegalArgumentException;
  public boolean test(int updateValue);
}
