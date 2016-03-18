package studentRecordsBackup.util;


import studentRecordsBackup.util.OddEvenFilterI;
import studentRecordsBackup.util.OddFilter;
import studentRecordsBackup.util.EvenFilter;
import studentRecordsBackup.bst.BST;
import studentRecordsBackup.bst.Node;
import java.util.ArrayList;
import studentRecordsBackup.util.FileProcessor;

public class BSTBuilder{

	private FileProcessor fp;

	public BSTBuilder(String fileNameIn){
		this.fp = new FileProcessor(fileNameIn);
	}

	public ArrayList<BST> buildTrees()
	{
		ArrayList<BST> trees = new ArrayList<BST>();
		BST og = new BST();
		BST odd = new BST();
		BST even = new BST();
		String line;
		while((line = this.fp.readLineFromFile()) != null){
			int bNum = Integer.valueOf(line);


			Node ogNode = new Node(bNum);
			//Node oddNode = new Node(bNum, (n -> (n % 2) == 1 ? true : false)); 		
			//Node evenNode = new Node(bNum, (n -> (n % 2) == 0 ? true : false)); 		
			Node oddNode = new Node(bNum);
			Node evenNode = new Node(bNum);

			ogNode.add(oddNode, new OddFilter());
			ogNode.add(evenNode, new EvenFilter());

			og.insert(ogNode);
			odd.insert(oddNode);
			even.insert(evenNode);


		}

		trees.add(og);
		trees.add(odd);
		trees.add(even);

		return trees;
	}





}
