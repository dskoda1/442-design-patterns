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
    BST og = new BST();
    BST odd = new BST();
    BST even = new BST();

    String line;
    //Loop through the file here.
    try{
      while((line = this.fp.readLineFromFile()) != null){
        //Parse the B-Number
        int bNum = Integer.valueOf(line);

        Node ogNode = new Node(bNum);
        //Node oddNode = new Node(bNum, (n -> (n % 2) == 1 ? true : false)); 		
        //Node evenNode = new Node(bNum, (n -> (n % 2) == 0 ? true : false)); 		
        Node oddNode = new Node(bNum);
        ogNode.add(oddNode, new OddFilter());

        Node evenNode = new Node(bNum);
        ogNode.add(evenNode, new EvenFilter());

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
