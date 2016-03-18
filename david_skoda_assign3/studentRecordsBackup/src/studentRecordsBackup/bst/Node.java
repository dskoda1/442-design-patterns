package studentRecordsBackup.bst;

import studentRecordsBackup.util.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

import studentRecordsBackup.util.OddEvenFilterI;
import java.util.function.Predicate;


public class Node implements ObserverI, SubjectI{

	protected int bNumber;
	protected Node left;
	protected Node right;
	protected OddEvenFilterI filter;
	private Predicate<Integer> filterFn;
	protected HashMap<OddEvenFilterI, ObserverI> observers;
	//protected ArrayList<ObserverI> observers;
	protected HashSet<ObserverI> observersHS;
	
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
	/*
		 public Node(int bNumIn, Predicate<Integer> filterIn){
		 super();
		 this.bNumber = bNumIn;
		 this.left = null;
		 this.right = null;
		 this.filterFn = null;
		 this.observers = new ArrayList<ObserverI>();
		 Logger.writeMessage("Constructor for Node Class called. ",
		 Logger.DebugLevel.CONSTRUCTOR); 
		 }
	 */
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

	public Node(int bNumIn, OddEvenFilterI filterIn){
		super();
		this.bNumber = bNumIn;
		this.left = null;
		this.right = null;
		this.filter = filterIn;
		this.observers = new HashMap(); 
		Logger.writeMessage("Constructor for Node Class called. ",
				Logger.DebugLevel.CONSTRUCTOR); 
	}

	@Override
		public void add(ObserverI obsIn, OddEvenFilterI filterIn){
			if(obsIn != null && filterIn != null){
				observers.put(filterIn, obsIn);
			}
		}

	@Override
		public void remove(ObserverI obsIn){

		}

	@Override
		public void notifyObservers(int updateValue){
			for(OddEvenFilterI filter: this.observers.keySet()){
				if(filter.check(updateValue)){
					this.observers.get(filter).update(updateValue);
				}
			} 
		}
	@Override
		public void update(Object obj){
			if(obj instanceof Integer){
				//Check filter function
				int updateValue = (int)obj; 
		//		if(this.filter.check(updateValue)){
					Logger.writeMessage("Updating a node from old value of "
							+ this.bNumber + " to " + (this.bNumber + updateValue), 
							Logger.DebugLevel.UPDATE);
					this.bNumber += updateValue;
			//	}	

			}else{

			}	
		}


	@Override
	public boolean equals(Object obj){
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


	public String toString(){

		return "Node" +
			"\nB-number: " + bNumber; 

	}

}

