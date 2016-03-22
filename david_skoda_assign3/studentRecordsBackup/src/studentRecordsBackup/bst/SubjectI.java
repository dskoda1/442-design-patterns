package studentRecordsBackup.bst;
import java.util.function.Predicate;

public interface SubjectI{
  public void add(ObserverI obsIn);
  public void remove(ObserverI obsIn);
  public void notifyObservers(int updateValue);
}
