package studentRecordsBackup.bst;

import studentRecordsBackup.util.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.function.Predicate;
import java.lang.IllegalArgumentException;

public class Node implements ObserverI, SubjectI{

  protected int bNumber;
  protected Node left;
  protected Node right;
  //Keep a hash map of predicate/observer pairs
  protected HashMap<Predicate<Integer>, ObserverI> observers;

  public Node(){
    super();
    this.bNumber = 0;
    this.left = null;
    this.right = null;
    this.observers = new HashMap();
    Logger.writeMessage("Constructor for Node Class called. ",
        Logger.DebugLevel.CONSTRUCTOR); 
  }
  public Node(int bNumIn){
    super();
    this.bNumber = bNumIn;
    this.left = null;
    this.right = null;
    this.observers = new HashMap(); 
    Logger.writeMessage("Constructor for Node Class called. ",
        Logger.DebugLevel.CONSTRUCTOR); 
  }
  /**
   *	Add an observer and its corresponding filter predicate into 
   *	this nodes observers map
   *	@param obsIn the observer to save
   *	@param filterIn the filter to use as a key for the observer, a
   *	predicate function which can be tested with .test and takes an int
   **/
  @Override public void add(ObserverI obsIn, Predicate<Integer> filterIn){
    if(obsIn != null && filterIn != null){
      observers.put(filterIn, obsIn);
    }
  }
  /**
   *  Remove an observer from this nodes observers.
   *	@param obsIn the observer to remove.
   *  @deprecated This assignment does not handle removing observers.
   **/
  @Deprecated @Override public void remove(ObserverI obsIn){}

  /**
   *	Go through this nodes observers and notify the ones
   *	that want the update.
   *	This method iterates over this nodes observers key set
   *	which is a bunch of predicate functions, and uses their 
   * 	test method to decide whether or not to send the update to
   * 	the corresponding observer.
   *  @param updateValue the int value to update nodes with
   **/
  @Override public void notifyObservers(int updateValue){
    for(Predicate<Integer> filter : this.observers.keySet()){
      if(filter.test(updateValue)){
        this.observers.get(filter).update(updateValue);
      }
    } 
  }

  /**
   *	Update this nodes bNumber.
   *	Parses an object into an int and adds it to this nodes
   *	bNumber. Also sends a message to the logger with the 
   *	UPDATE debug level.
   *	@param obj the value (int) to use.
   *	@throws ive an InvalidArgumentException if obj is not an
   *	instanceof Integer.
   **/
  @Override public void update(Object obj) throws IllegalArgumentException{
    if(obj instanceof Integer){
      int updateValue = (int)obj; 

      Logger.writeMessage("Updating a node from old value of "
          + this.bNumber + " to " + (this.bNumber + updateValue), 
          Logger.DebugLevel.UPDATE);
      this.bNumber += updateValue;

    }else{
      throw new IllegalArgumentException("Object passed into Node::Update" + 
          " not parseable into an int. Check your usage.");

    }	
  }


  @Override public boolean equals(Object obj){
    if(obj instanceof Node){
      return ( ((Node) obj).bNumber == this.bNumber);
    }else{
      return false;
    }
  }

  public void setBnumber(int bNumIn){
    this.bNumber = bNumIn;
  }

  public void setLeftChild(Node leftIn){
    this.left = leftIn;
  }

  public void setRightChild(Node rightIn){
    this.right = rightIn;
  }

  public int getBnumber(){
    return this.bNumber;
  }

  public Node getLeftChild(){
    return this.left;
  }

  public Node getRightChild(){
    return this.right;
  }


  @Override public String toString(){
    return "B-Number: " + bNumber; 
  }

}

