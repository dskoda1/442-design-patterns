package studentRecordsBackup.bst;

import studentRecordsBackup.util.Logger;
import studentRecordsBackup.bst.Node;

public class BST{

	private Node head;
	private int sum;
	public BST(){
		super();
		Logger.writeMessage("Creating a BST", Logger.DebugLevel.CONSTRUCTOR);
		this.head = null;
	}
	/**
	 *	Method which inserts a new node given only a B-number;
	 *	It calls an overloaded version of insert with "" as the description. 
	 *	@param bNumIn the b-number to insert as a new node.
	 **/
	public void insert(int bNumIn){
		this.insert(bNumIn, ""); 
	}
	/**
	 *	Method which triggers an insert into the BST;
	 *	It calls an overloaded version of insert with "" as the description. 
	 *	@param bNumIn the b-number for the new node.
	 *	@param descIn the description for the new node
	 **/
	public void insert(int bNumIn, String descIn){

		Node newNode = new Node(bNumIn, descIn);
		Logger.writeMessage("Inserting node into BST \n" + newNode.toString(),
				Logger.DebugLevel.INSERT);  
		this.insert(this.head, newNode); 
	}
	/**
	*	Internal insert method that recursively finds where to insert.
	* @param comp The node being compared for insert positioning. 	
	*	@param newNode the node being inserted into the tree.
	*/
	private void insert(Node comp, Node newNode){

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

	/**
	*	Traverses the tree in an in-order fashion, prints to std out
	* each node in this order; uses an internal method to recursively
	* accomplish this.
	**/ 
	public void inOrderTraversal(){
		this.inOrderHelper(this.head);
	}

	/**
	*	Internal helper for doing an in order traversal of the tree.
	* @param n the node to print out and explore
	**/
	private void inOrderHelper(Node n){
		if(n != null){
			this.inOrderHelper(n.left);
			System.out.println(n.toString());
			this.inOrderHelper(n.right);
		}
	}

	/**
	* Traverse the tree and get the sum of all nodes bnumbers.
	**/
	public int sumAllRecords(){
		this.sum = 0;
		this.sumRecordsHelper(this.head);	
		return this.sum;
	}
	
	/**
	* Internal helper function for summing up all the nodes.
	* @param n the node to explore and sum.
	**/
	private void sumRecordsHelper(Node n){
		if(n != null){
			this.sumRecordsHelper(n.left);			
			this.sum += n.bNumber;
			this.sumRecordsHelper(n.right);			
		}
	}

	public String toString(){

		return "TODO: BST toString()";
	}

}

