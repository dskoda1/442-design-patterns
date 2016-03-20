package studentRecordsBackup.util;


import studentRecordsBackup.util.OddEvenFilterI;
import studentRecordsBackup.util.OddFilter;
import studentRecordsBackup.util.EvenFilter;
import studentRecordsBackup.bst.BST;
import studentRecordsBackup.bst.Node;
import java.util.ArrayList;
import studentRecordsBackup.util.FileProcessor;
import studentRecordsBackup.util.Logger;
import java.lang.NumberFormatException;
public class BSTBuilder{

  private FileProcessor fp;

  public BSTBuilder(String fileNameIn){
    this.fp = new FileProcessor(fileNameIn);
    Logger.writeMessage("Constructor for BSTBuilder Class called.",
        Logger.DebugLevel.CONSTRUCTOR);

  }

  /**
   *	Builds three trees, with 2 as observers and one as subject.
   *	This method iterates through the file the BSTBuilder class was
   *	given to read, and creates 3 nodes for each B-Number. One as the
   *	subject, and two as observers. One observer gets an OddFilter,
   *	and the other gets an EvenFilter.
   * @return an ArrayList of BST's.
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

        Node node = new Node(bNum);
        Node oddNode = new Node(bNum);
        node.add(oddNode, new OddFilter());

        Node evenNode = new Node(bNum);
        node.add(evenNode, new EvenFilter());

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
