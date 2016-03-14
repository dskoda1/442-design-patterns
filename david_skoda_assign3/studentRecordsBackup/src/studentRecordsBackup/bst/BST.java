package studentRecordsBackup.bst;

import studentRecordsBackup.util.Logger;
import studentRecordsBackup.bst.Node;

public class BST{

	private Node head;

	public BST(){
		super();
		Logger.writeMessage("Creating a BST", Logger.DebugLevel.CONSTRUCTOR);
		this.head = null;
	}

	public void insert(int bNumIn){
		this.insert(bNumIn, ""); 
	}
	public void insert(int bNumIn, String descIn){

		Node newNode = new Node(bNumIn, descIn);
		

		Logger.writeMessage("Inserting node into BST \n" + newNode.toString(),
			Logger.DebugLevel.INSERT);  
		

		this.insert(this.head, newNode); 

	}

	public void insert(Node comp, Node newNode){

		if(this.head == null){
			this.head = newNode;
			return;
		}else if(newNode.bNumber < comp.bNumber){
			//check for null, then go down left child
			if(comp.left != null){
				this.insert(comp.left, newNode);
			}else{
				//insert node
				comp.left = newNode;
			}
		}else{
			if(comp.right != null){
				this.insert(comp.right, newNode);
			}else{
				comp.right = newNode;
			}
		}
	}

	public void inOrderTraversal(){
		this.inOrderHelper(this.head);
	}
	
	public void inOrderHelper(Node n){
		if(n != null){
			this.inOrderHelper(n.left);
			System.out.println(n.toString());
			this.inOrderHelper(n.right);
		}
	}

	public String toString(){

		return "TODO: BST toString()";
	}

}

