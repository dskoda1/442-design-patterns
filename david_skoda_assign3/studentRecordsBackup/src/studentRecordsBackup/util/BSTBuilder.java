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
        Node oddNode = new Node(bNum, this.FilterPredicates.get("Odd"));
        Node evenNode = new Node(bNum, this.FilterPredicates.get("Even"));
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
    trees.add(tree);
    trees.add(oddTree);
    trees.add(evenTree);

    return trees;
  }
}
