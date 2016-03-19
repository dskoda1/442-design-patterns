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
    BST og = new BST();
    BST odd = new BST();
    BST even = new BST();

    String line;
    //Loop through the file here.
    try{
      while((line = this.fp.readLineFromFile()) != null){
        //Parse the B-Number
        int bNum = Integer.valueOf(line);

        //Create the Nodes here.
        Node ogNode = new Node(bNum);
        Node oddNode = new Node(bNum);
        Node evenNode = new Node(bNum);

        //Add the oddNode with a lambda filter predicate testing for odd values.
        //ogNode.add(oddNode, this.FilterPredicates.get("Odd")); 
        ogNode.add(oddNode,(n -> (n % 2) == 1 ? true : false)); 

        //Add the evenNode with a lambda filter predicate testing for even values.
        ogNode.add(evenNode, this.FilterPredicates.get("Even"));

        //Insert the nodes into the appropriate trees here.
        og.insert(ogNode);
        odd.insert(oddNode);
        even.insert(evenNode);


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
    trees.add(og);
    trees.add(odd);
    trees.add(even);

    return trees;
  }
}
