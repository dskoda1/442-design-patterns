package studentRecordsBackup.util;


import studentRecordsBackup.bst.BST;
import studentRecordsBackup.bst.Node;
import java.util.ArrayList;
import studentRecordsBackup.util.FileProcessor;
import studentRecordsBackup.util.Logger;
import java.lang.NumberFormatException;
import java.util.function.Predicate;
import java.util.HashMap;

public class BSTBuilder{

  private FileProcessor fp;
  private HashMap<String, Predicate<Integer>> FilterPredicates;

  public BSTBuilder(String fileNameIn){
    this.fp = new FileProcessor(fileNameIn);
    Logger.writeMessage("Constructor for BSTBuilder Class called.",
        Logger.DebugLevel.CONSTRUCTOR);
  
    this.FilterPredicates = new HashMap<String, Predicate<Integer>>()
    {{
        put("Odd", (n -> (n % 2) == 1 ? true : false));
        put("Even", (n -> (n % 2) == 0 ? true : false));
        put("Five", (n -> (n % 5) == 0 ? true : false));
    }};
  }
  /**
   *	Builds three trees, with 2 as observers and one as subject.
   *	This method iterates through the file the BSTBuilder class was
   *	given to read, and creates 3 nodes for each B-Number. One as the
   *	subject, and 2 as observers. 
   *	The first observer gets a filter predicate that tests for odd 
   *  values, and the second gets one that tests for
   * 	even values. These are passed in as lambda functions, and act like
   *	functional interfaces under the hood. They can be used just like an
   * 	interface by calling their 'test' method with an int, and they return
   * 	a boolean.
   *  @return an ArrayList of BST's.
   **/
  public ArrayList<BST> buildTrees()
  {
   
     //Create the array list, and the three trees.
    ArrayList<BST> trees = new ArrayList<BST>();
    BST tree = new BST();
    BST oddTree = new BST();
    BST evenTree = new BST();

    String line;
    //Loop through the file here.
    try{
      while((line = this.fp.readLineFromFile()) != null){
        //Parse the B-Number
        int bNum = Integer.valueOf(line);

        //Create the Nodes here.
        Node node = new Node(bNum);
    
        /*
        Retrieving the lambda functions here and passing them into the
        Node constructor to be saved. This can be extended to creating 
        the composition of multiple lambdas and the node wouldn't even
        be able to tell the difference.
        Currently the available lambdas are stored as
        a member of this class, but it could be worthwhile to explore other options
        available to store the lambdas for a project that had hundreds or thousands
        of filters for instance.
        */
        Node oddNode = new Node(bNum, this.FilterPredicates.get("Odd"));
        Node evenNode = new Node(bNum, this.FilterPredicates.get("Even"));
        /*Here we combine the Even and Five predicates into a logical And. So it
        would only be true for 10. 
 
        Node multiFilterNode = new Node(bNum, this.FilterPredicates.get("Even")
                                          .and(this.FilterPredicates.get("Five")));
        */
        node.add(oddNode);
        node.add(evenNode);

        //Insert the nodes into the appropriate trees here.
        tree.insert(node);
        oddTree.insert(oddNode);
        evenTree.insert(evenNode);


      }
    }
    catch(NumberFormatException nfe){
      System.err.println("A Number Format Exception was raised while "+
          "reading input file in BSTBuilder.java.\n" +
          nfe.getCause());
      nfe.printStackTrace();
    }catch(Exception e){
      e.printStackTrace();
    }

    //Add the trees to the array list before returning.
    trees.add(tree);
    trees.add(oddTree);
    trees.add(evenTree);

    return trees;
  }
}
