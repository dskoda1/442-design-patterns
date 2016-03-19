package studentRecordsBackup.bst;

import studentRecordsBackup.util.Logger;
import studentRecordsBackup.bst.Node;
import java.util.function.Consumer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BST{

	private Node head;
	private int sum;
	private int max;

	/**
	 *	Constructs an empty BST.
	 **/
	public BST(){
		super();
		this.head = null;
		Logger.writeMessage("Constructor for BST Class called.",
				Logger.DebugLevel.CONSTRUCTOR);
	}
	/**
	 *	Method which inserts a new node given only a B-number.
	 *	It calls the internal insert method after creating
	 * the new Node.
	 *	@param bNumIn the b-number to insert as a new node.
	 **/
	public void insert(int bNumIn){
		Node newNode = new Node(bNumIn);
		Logger.writeMessage("Inserting node into BST \n" + newNode.toString(),
				Logger.DebugLevel.INSERT);  
		this.insert(this.head, newNode); 
	}

	/**
	 *	This inserts a given node into the tree.
	 *	It calls the internal insert method with the proper
	 * arguments.
	 *	@param newNode the node to insert.
	 **/
	public void insert(Node newNode){
		this.insert(this.head, newNode);
		Logger.writeMessage("Inserting node into BST \n" + newNode.toString(),
				Logger.DebugLevel.INSERT);  
	}

	/**
	 *	Internal insert method that recursively finds where to insert.
	 * @param comp The node being compared for insert positioning. 	
	 *	@param newNode the node being inserted into the tree.
	 */
	private void insert(Node comp, Node newNode){

		if(this.head == null){
			this.head = newNode;
			this.max = newNode.bNumber;
			return;
		}else if(newNode.bNumber < comp.bNumber){
			//check for null, then go down left child
			if(comp.left != null){
				this.insert(comp.left, newNode);
			}else{
				//insert node
				comp.left = newNode;
				if(this.max < newNode.bNumber)
					this.max = newNode.bNumber;
			}
		}else{
			if(comp.right != null){
				this.insert(comp.right, newNode);
			}else{
				comp.right = newNode;
				if(this.max < newNode.bNumber)
					this.max = newNode.bNumber;
			}
		}
	}


	/**
	 *	Internal mapping method; takes a node and a consumer
	 * function, and applies the function onto each node in order.
	 * @param n the node to explore
	 * @param fn the consumer function (Single param, no return val) 
	 * 	to apply to the node and pass recursively to it's children.
	 **/
	private void inOrderMap(Node n, Consumer<Node> fn){
		if(n != null){
			inOrderMap(n.left, fn);
			fn.accept(n);	
			inOrderMap(n.right, fn);
		}
	}

	/**
	 *	Updates the relevant nodes with the given updateValue.
	 *	This calls the internal inOrderMap method, and if the node passes
	 *	its observer check predicate (filter) then gets its bNumber
	 * incremented. It also notifies any observers with the value to update.
	 *	@param updateValue the int value to increment bNumber by potentially.
	 *	@return void
	 **/
	public void updateNodes(int updateValue){
		this.inOrderMap(this.head, (node -> { 
					if(this.max == node.bNumber){
						node.update(updateValue);
						node.notifyObservers(updateValue);
						this.max += updateValue;
					}
					node.update(updateValue);
					node.notifyObservers(updateValue);
					})); 
	}

	/**
	 * Print out all the nodes of this tree in order. 
	 * Uses the internal inOrderMap method to create a list
	 * of all nodes in the tree. Then creates a stream with said
	 * list to put together all of the nodes values into a single 
	 * string.
	 * @return the string containing all of the nodes together.
	 **/ 
	public String printInOrder(){
		List<Node> nodes = new ArrayList();
		this.inOrderMap(this.head, (n -> nodes.add(n)));
		//xyz.stream() returns a collection which we can then chain
		//function calls onto, in order to refine the original collection
		//down to what we want. This example only uses map which calls a
		//function on each node in the stream, and returns the nodes toString
		//value. We then join the stream back together with newline characters.
		return nodes.stream()
			.map(node -> node.toString())
			.collect(Collectors.joining("\n"));		
	}
	/**
	 * Traverse the tree and savethe sum of all nodes bnumbers.
	 * Uses the internal inOrderMap method.
	 * @return a string representing the sum of all bnumbers in this tree.
	 **/
	public String printBSum(){
		this.sum = 0;
		this.inOrderMap(this.head, (n -> this.sum += n.bNumber));
		return "The sum of all B-Numbers is: " +  this.sum + ".";
	}

	public String toString(){
		return "TODO: BST toString()";
	}

}

