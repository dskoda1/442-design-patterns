package studentRecordsBackup.bst;

import studentRecordsBackup.util.Logger;
import java.util.ArrayList;

public class Node implements ObserverI, SubjectI{

	protected int bNumber;
	protected Node left;
	protected Node right;

	protected ArrayList<ObserverI> observers;

	public Node(){
		super();
		this.bNumber = 0;
		this.left = null;
		this.right = null;
		this.observers = new ArrayList<ObserverI>();
		Logger.writeMessage("Constructor for Node Class called. ",
			Logger.DebugLevel.CONSTRUCTOR); 
	}

	public Node(int bNumIn){
		super();
		this.bNumber = bNumIn;
		this.left = null;
		this.right = null;
		this.observers = new ArrayList<ObserverI>();
		Logger.writeMessage("Constructor for Node Class called. ",
			Logger.DebugLevel.CONSTRUCTOR); 
	}

	//TODO
	public void add(ObserverI obsIn){
		if(obsIn != null){
			observers.add(obsIn);
		}
	}

	//TODO
	public void remove(ObserverI obsIn){

	}

	//TODO
	public void notifyObservers(){

	}

	//TODO
	public void update(Object obj){

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

