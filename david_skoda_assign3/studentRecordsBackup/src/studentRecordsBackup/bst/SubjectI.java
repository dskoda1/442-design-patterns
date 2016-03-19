package studentRecordsBackup.bst;
import studentRecordsBackup.util.OddEvenFilterI;
import java.util.function.Predicate;

public interface SubjectI{
  public void add(ObserverI obsIn, Predicate<Integer> filterIn);
  public void remove(ObserverI obsIn);
  public void notifyObservers(int updateValue);
}
