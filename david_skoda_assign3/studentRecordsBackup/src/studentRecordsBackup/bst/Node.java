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
  //Keep a hash set of observers
  protected HashSet<ObserverI> observers;
  //Keep the filter as a data member
  protected Predicate<Integer> filter;

  public Node(){
    super();
    this.bNumber = 0;
    this.left = null;
    this.right = null;
    this.observers = new HashSet();
    Logger.writeMessage("Constructor for Node Class called. ",
        Logger.DebugLevel.CONSTRUCTOR); 
  }
  public Node(int bNumIn){
    super();
    this.bNumber = bNumIn;
    this.left = null;
    this.right = null;
    this.observers = new HashSet(); 
    Logger.writeMessage("Constructor for Node Class called. ",
        Logger.DebugLevel.CONSTRUCTOR); 
  }
  public Node(int bNumIn, Predicate<Integer> filterIn){
    super();
    this.bNumber = bNumIn;
    this.left = null;
    this.right = null;
    this.filter = filterIn;
    this.observers = new HashSet(); 
    Logger.writeMessage("Constructor for Node Class called. ",
        Logger.DebugLevel.CONSTRUCTOR); 
  }
  /**
   *	Add an observer this nodes observers map
   *	@param obsIn the observer to save
   **/
  @Override public void add(ObserverI obsIn){
    if(obsIn != null){ 
      observers.add(obsIn);
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
   *  This method iterates over the hashMap keys(which are predicates)
   *  and filters based on which ones pass the test. It then maps the
   *  corresponding observer over the predicate, and last calls the 
   *  update method on them.
   *  @param updateValue the int value to update nodes with
   **/
  @Override public void notifyObservers(int updateValue){
    //Get the key set
    this.observers
      //Transform it into a stream
      .stream()
      //Filter them based on the test
      .filter(obs -> obs.test(updateValue))
      //Call the update method onto each observer
      .forEach(obs -> obs.update(updateValue));
  }
  /**
  * Run this nodes filter predicate with the given updateValue.
  * @param updateValue the int value to test against
  * @return a boolean returned from the predicate.
  **/
  @Override public boolean test(int updateValue){
    return this.filter.test(updateValue);
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

