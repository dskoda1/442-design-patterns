package studentRecordsBackup.bst;
import studentRecordsBackup.util.OddEvenFilterI;


public interface SubjectI{
	public void add(ObserverI obsIn, OddEvenFilterI filterIn);
	public void remove(ObserverI obsIn);
	public void notifyObservers(int updateValue);
}
