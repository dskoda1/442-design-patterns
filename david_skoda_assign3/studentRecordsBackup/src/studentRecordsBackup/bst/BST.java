package studentRecordsBackup.bst;

import studentRecordsBackup.util.Logger;
import studentRecordsBackup.bst.Node;
import java.util.function.Consumer;
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
		Node newNode = new Node(bNumIn);
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
	*	Internal mapping function; takes a node and a consumer
	* function, and applies the function onto each node in order.
	**/
	private void traverseInOrder(Node n, Consumer<Node> fn){
		if(n != null){
			traverseInOrder(n.left, fn);
			fn.accept(n);	
			traverseInOrder(n.right, fn);
		}
	}


	/**
	*	Traverses the tree and prints to std out
	* each node in this order; uses an internal method to recursively
	* accomplish this.
	**/ 
	public void printTree(){
		this.traverseInOrder(this.head, (a -> System.out.println(a.toString())));
	}

	/**
	* Traverse the tree and get the sum of all nodes bnumbers.
	**/
	public int sumAllRecords(){
		this.sum = 0;
		this.traverseInOrder(this.head, (n -> this.sum += n.bNumber));
		return this.sum;
	}

	public String toString(){
		return "TODO: BST toString()";
	}

}

