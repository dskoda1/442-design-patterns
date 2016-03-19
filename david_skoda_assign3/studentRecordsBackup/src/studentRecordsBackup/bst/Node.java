package studentRecordsBackup.bst;

import studentRecordsBackup.util.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

import studentRecordsBackup.util.OddEvenFilterI;
import java.util.function.Predicate;
import java.lang.IllegalArgumentException;

public class Node implements ObserverI, SubjectI{

  protected int bNumber;
  protected Node left;
  protected Node right;
  protected OddEvenFilterI filter;
  protected HashMap<OddEvenFilterI, ObserverI> observers;

  public Node(){
    super();
    this.bNumber = 0;
    this.left = null;
    this.right = null;
    this.filter = null;
    this.observers = new HashMap();
    Logger.writeMessage("Constructor for Node Class called. ",
        Logger.DebugLevel.CONSTRUCTOR); 
  }
  public Node(int bNumIn){
    super();
    this.bNumber = bNumIn;
    this.left = null;
    this.right = null;
    this.filter = null;
    this.observers = new HashMap(); 
    Logger.writeMessage("Constructor for Node Class called. ",
        Logger.DebugLevel.CONSTRUCTOR); 
  }

  /**
   *	Add an observer and its corresponding filter into this nodes observers
   * map
   *	@param obsIn the observer to save
   *	@param filterIn the filter to use as a key for the observer
   **/
  @Override public void add(ObserverI obsIn, OddEvenFilterI filterIn){
    if(obsIn != null && filterIn != null){
      observers.put(filterIn, obsIn);
    }
  }
  /**
   *	Remove an observer from this nodes observers.
   *	@param obsIn the observer to remove.
   * @deprecated This assignment does not handle removing observers.
   **/
  @Deprecated @Override public void remove(ObserverI obsIn){}

  /**
   *	Go through this nodes observers and notify the ones
   *	that want the update.
   *	This method iterates over this nodes observers key set
   *	which produces some number of filters, and if the filter 
   *	check method passes then it calls the update method with
   *	the update value.
   * @param updateValue the int value to update nodes with
   **/
  @Override public void notifyObservers(int updateValue){
    for(OddEvenFilterI filter: this.observers.keySet()){
      if(filter.check(updateValue)){
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

