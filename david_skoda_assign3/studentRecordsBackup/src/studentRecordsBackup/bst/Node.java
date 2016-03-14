package studentRecordsBackup.bst;

import studentRecordsBackup.util.Logger;


public class Node implements ObserverI, SubjectI{

	protected int bNumber;
	private String description;
	protected Node left;
	protected Node right;

	public Node(){
		super();
		this.bNumber = 0;
		this.description = "";
		this.left = null;
		this.right = null;
	}

	public Node(int bNumIn, String descriptionIn){
		super();
		this.bNumber = bNumIn;
		this.description = descriptionIn;
		this.left = null;
		this.right = null;
		Logger.writeMessage("Constructor for Node Class called. ",
			Logger.DebugLevel.CONSTRUCTOR); 
	}

	//TODO
	public void add(ObserverI obsIn){

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

	public void setDescription(String descriptionIn){
		this.description = descriptionIn;
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
	public String getDescription(){
		return this.description;
	}

	public Node getLeftChild(){
		return this.left;
	}

	public Node getRightChild(){
		return this.right;
	}


	public String toString(){

		return "Node" +
			"\nB-number: " + bNumber + 
			"\nDescription: " + description;

	}

}

